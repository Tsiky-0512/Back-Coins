package IDea.backend.metier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;
import IDea.backend.entities.Entreprise;
import IDea.backend.entities.Investisseur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Login {
	private static final long token_validity =  2*60*60*100*120;
	private static final String token_secret = "idea";


	public static void verificationForm(Map<Object, Object> form) throws Exception {
		for (Map.Entry<Object, Object> item: form.entrySet()) {
			if (item.getKey()==null && item.getValue()==null) {
				throw new Exception("Veuillez bien remplir la formulaire !");
			}
		}
	}
	
	public static String createJWT(String nom,String password) {
	    long nowMillis = System.currentTimeMillis();
	    Date expiration = new Date(nowMillis+token_validity);
	    
	    String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, token_secret)
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(expiration)
				.claim("nom",nom)
				.claim("password",password)
				.compact();
		return token;
	}
	
	public static void checkToken(String authHeader,HttpServletRequest request)throws Exception{
		String[] authHeaderArr = authHeader.split("Bearer");
		if(authHeaderArr.length>1 && authHeaderArr[1]!=null) {
			String token = authHeaderArr[1];
			try {
				Claims claims = Jwts.parser().setSigningKey(token_secret)
						.parseClaimsJws(token).getBody();
				request.setAttribute("id", Integer.parseInt(claims.get("id").toString()));
			} catch (Exception e) {
				throw new Exception("Token invalid/expired");
			}
		}else {
			throw new Exception("Token invalid/expired");
		}
	}
	
	public void checkPassword(String password,String password_database) throws Exception {
		if(!BCrypt.checkpw(password,password_database)) {
			throw new Exception("invalid password");
		}
	}
	
	public <T> T findByPassword(String nom,String password,Class temp) throws Exception {
		Connection conn = null;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			T[] result = (T[]) Access.query(conn,temp, "SELECT * FROM "+temp.getSimpleName()+" where nom_"+temp.getSimpleName()+"="+nom);
			conn.commit();
			return result[0];
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw new Exception(e.getMessage());
		}finally {
			conn.close();
		}
		
	}
	
	public Class getType(String type) {
		if (type.compareTo("entreprise")==0) {
			return Entreprise.class;
		}else {
			return Investisseur.class;
		}
	}
	
	
	
	public Map<String, Object> Login(String name,String password,String type){
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			String token = "";
			long id = -1;
			if (type.compareTo("entreprise")==0) {
				Entreprise[] en = this.findByPassword(name,password,Entreprise.class);
				this.checkPassword(password,en[0].getMdp());
				token = this.createJWT(name,password);
				id = en[0].getId_Entreprise();
			}else {
				Investisseur[] invess = this.findByPassword(name,password,Investisseur.class);
				this.checkPassword(password,invess[0].getMdp());
				token = this.createJWT(name,password);
				id = invess[0].getId_Investisseur();
			}
			
			response.put("token", token);
			response.put("name", name);
			response.put("id", id);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	public Map<String, Object> Inscription(Object temp) throws SQLException{
		Map<String, Object> response = new HashMap<String, Object>();
		Class t= temp.getClass();
		String name_class = t.getSimpleName();
		try {
			long id = -1;
			String name_user = "";
			String password = "";
			String token = "";
			if (name_class.compareTo("Entreprise")==0) {
				Entreprise en = (Entreprise) temp;
				en.insert(en);
				id = en.getId_Entreprise();
				name_user = en.getNom_Entreprise();
				password = en.getMdp();
			}else {
				Investisseur en = (Investisseur) temp;
				en.insert(en);
				id = en.getId_Investisseur();
				name_user = en.getNom_Investisseur()+" "+en.getPrenom_Investisseur();
				password = en.getMdp();
			}
			token = this.createJWT(name_user,password);
			response.put("token", token);
			response.put("name", name_user);
			response.put("id", id);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message",e.getMessage());
		}
		return response;
	
		
	}
}
