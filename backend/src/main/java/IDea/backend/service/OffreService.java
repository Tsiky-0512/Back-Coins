package IDea.backend.service;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IDea.backend.database.Access;
import IDea.backend.entities.Detail_offre;
import IDea.backend.entities.Entreprise;
import IDea.backend.entities.Offre;
import IDea.backend.entities.Participation;
import IDea.backend.helper.Function;
import IDea.backend.metier.Login;

@CrossOrigin
@RestController
@RequestMapping("/offre")
public class OffreService {
	@GetMapping("/listOffre")
	public Map<String,Object> listOffre() throws Exception{
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			Offre[] liste_offre = new Offre().find("SELECT * FROM OFFRE");
			response.put("data", liste_offre);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	@GetMapping("/detail_offre")
	public Map<String,Object> detail_offre(@RequestBody Map<String, Object> body) throws Exception{
		Map<String,Object> response = new HashMap<String, Object>();
		int id_offre = (int) body.get("id_offre");
		try {
			String req = "SELECT * FROM DETAIL_OFFRE WHERE ID_OFFRE="+id_offre;
			System.out.println(req);
			List<Detail_offre> liste_offre =  new Detail_offre().findBy(req);
			response.put("data", liste_offre);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/historique_participation")
	public Map<String,Object> historiqueParticipation(@RequestBody Entreprise input) throws Exception {
		String sql = "Select id_Participation,id_Investisseur,id_Offre,id_option,sum(part) as part , date_participation from participation id_Entreprise="+input.getId_Entreprise();
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			Participation[] liste_offre = new Participation().find(sql);
			response.put("data", liste_offre);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/participer")
	public Map<String,Object> participer(HttpServletRequest request,@RequestBody Map<String,Object> body,@RequestHeader(name = "Authorization") String authHeader) throws Exception {
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			Login.checkToken(authHeader, request);
			new Participation().insert(new Participation(0, (int) body.get("id_Investisseur"), (int) body.get("id_Offre"), (int) body.get("id_option"), (int) body.get("part"),new Timestamp(System.currentTimeMillis())));
			response.put("message", "Congrats!");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/insertOffre")
	public Map<String,Object> insertOffre(@RequestBody Map<String, Object> body) throws SQLException {
		Map<String, Object> response = new HashMap<String, Object>();
		String sql = "INSERT INTO Offre(id_Entreprise,nom_Offre,type_Offre,etat) VALUES("+(long) body.get("id_entreprise")+",'"+(String) body.get("nom_offre")+"','"+(String) body.get("nom_offre")+"','valable')";
		
		Connection conn = null;
		try {
			conn.setAutoCommit(false);
			Offre offer = new Offre().getLastIdOffre(conn);
			String sql2 = "INSERT INTO Detail_Offre(id_offre,id_option,valeur,pourcentage,pourcentage_interet,delai) VALUES ("+offer.getId_Offre()+","+body.get("id_option")+","+body.get("valeur")+","+body.get("pourcentage")+","+body.get("pourcentage_interet")+","+body.get("delai")+")";
			Access.execute(conn, sql);
			Access.execute(conn, sql2);
			conn.commit();
			response.put("message", "offer option inserted !");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getMessage());
		}finally {
			conn.close();
		}
		return response;
	}
	
	@PostMapping("/TotaliteEntreprisen")
	public Map<Integer[],Double> totaliteEntreprisen(@RequestBody Entreprise input) throws Exception { 
		Function appel = new Function();
		return 	appel.totalite(input);
	}
}
