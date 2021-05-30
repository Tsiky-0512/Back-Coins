package IDea.backend.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;

public class Entreprise {
	private Long id_Entreprise ;
	private String nom_Entreprise ;
	private String responsable ;
	private String contact  ;
	private String email  ;
	private String mdp ;
	
    public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Entreprise(Long id_Entreprise, String nom_Entreprise, String responsable, String contact, String email,
			String mdp) {
		super();
		this.id_Entreprise = id_Entreprise;
		this.nom_Entreprise = nom_Entreprise;
		this.responsable = responsable;
		this.contact = contact;
		this.email = email;
		this.mdp = mdp;
	}

	public Long getId_Entreprise() {
		return id_Entreprise;
	}

	public void setId_Entreprise(Long id_Entreprise) {
		this.id_Entreprise = id_Entreprise;
	}

	public String getNom_Entreprise() {
		return nom_Entreprise;
	}

	public void setNom_Entreprise(String nom_Entreprise) {
		this.nom_Entreprise = nom_Entreprise;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	public void insert(Entreprise e) throws Exception {
		String hashedPassword = BCrypt.hashpw(e.getMdp(), BCrypt.gensalt(10));
		Connection conn = null;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			String req = "INSERT INTO Entreprise(nom_Entreprise,responsable,contact,email,mdp) VALUES ('"+e.getNom_Entreprise()+"','"+e.getResponsable()+"','"+e.getContact()+"','"+e.getEmail()+"','"+hashedPassword+"')";
			Access.execute(conn,req);
			conn.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
			conn.rollback();
			throw e2;
			
		}finally {
			conn.close();
		}
	}
	
	public Map<String, Double> getCoins(Connection co,long id_entreprise) throws SQLException {
        PreparedStatement st = null;
        Map<String, Double> array = new HashMap<String, Double>();
        String req = "SELECT *  FROM TOTALITE_VOLA_NOM where id_entreprise="+id_entreprise;
        Double resultat = 0.0;
        try {
            st = co.prepareStatement(req);
            ResultSet result = st.  executeQuery();
            while(result.next()) array.put(result.getString("nom_entreprise"),result.getDouble("totalite_vola"));
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
	}
	
	
	
	public Map<String, Integer> getParticipants(Connection co,long id_entreprise) throws SQLException {
        PreparedStatement st = null;
        Map<String, Integer> array = new HashMap<String, Integer>();
        String req = "select * from EFFECTIFS_PARTICIPANTS_DETAILS where id_entreprise="+id_entreprise;
        Double resultat = 0.0;
        try {
            st = co.prepareStatement(req);
            ResultSet result = st.  executeQuery();
            while(result.next()) array.put(result.getString("nom_entreprise"),result.getInt("effectifs_investisseur"));
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
		
	}
	
	
	
	
}
