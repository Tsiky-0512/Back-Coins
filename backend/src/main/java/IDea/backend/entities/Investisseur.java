package IDea.backend.entities;

import java.sql.Connection;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;

public class Investisseur {
	private Long  id_Investisseur ;
	private String nom_Investisseur ;
	private String prenom_Investisseur ;
	private String email;
	private String mdp ;
	
	public Investisseur(Long id_Investisseur, String nom_Investisseur, String prenom_Investisseur, String email,
			String mdp) {
		super();
		this.id_Investisseur = id_Investisseur;
		this.nom_Investisseur = nom_Investisseur;
		this.prenom_Investisseur = prenom_Investisseur;
		this.email = email;
		this.mdp = mdp;
	}

	public Investisseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId_Investisseur() {
		return id_Investisseur;
	}

	public void setId_Investisseur(Long id_Investisseur) {
		this.id_Investisseur = id_Investisseur;
	}

	public String getNom_Investisseur() {
		return nom_Investisseur;
	}

	public void setNom_Investisseur(String nom_Investisseur) {
		this.nom_Investisseur = nom_Investisseur;
	}

	public String getPrenom_Investisseur() {
		return prenom_Investisseur;
	}

	public void setPrenom_Investisseur(String prenom_Investisseur) {
		this.prenom_Investisseur = prenom_Investisseur;
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
	
	public void insert(Investisseur e) throws Exception {
		String hashedPassword = BCrypt.hashpw(e.getMdp(), BCrypt.gensalt(10));
		Connection conn = null;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			Access.execute(conn,"INSERT INTO Investisseur(nom_Investisseur,prenom_Investisseur,email,mdp) VALUES ('"+e.getNom_Investisseur()+"','"+e.getPrenom_Investisseur()+"','"+e.getEmail()+"','"+hashedPassword+"')");
			conn.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
			conn.rollback();
			throw e2;
		}finally {
			conn.close();
		}
	}
	
	
	
}
