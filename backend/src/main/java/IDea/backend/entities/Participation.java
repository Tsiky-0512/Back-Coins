package IDea.backend.entities;

import java.sql.Connection;
import java.sql.Timestamp;

import org.mindrot.jbcrypt.BCrypt;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;

public class Participation {
	int id_Participation;
    int id_Investisseur;
    int id_Offre;
    int id_option;
    double part;
    Timestamp date_participation;
	
    public Participation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Participation(int id_Participation, int id_Investisseur, int id_Offre, int id_option, double part,
			Timestamp date_participation) {
		super();
		this.id_Participation = id_Participation;
		this.id_Investisseur = id_Investisseur;
		this.id_Offre = id_Offre;
		this.id_option = id_option;
		this.part = part;
		this.date_participation = date_participation;
	}

	public int getId_Participation() {
		return id_Participation;
	}

	public void setId_Participation(int id_Participation) {
		this.id_Participation = id_Participation;
	}

	public int getId_Investisseur() {
		return id_Investisseur;
	}

	public void setId_Investisseur(int id_Investisseur) {
		this.id_Investisseur = id_Investisseur;
	}

	public int getId_Offre() {
		return id_Offre;
	}

	public void setId_Offre(int id_Offre) {
		this.id_Offre = id_Offre;
	}

	public int getId_option() {
		return id_option;
	}

	public void setId_option(int id_option) {
		this.id_option = id_option;
	}

	public double getPart() {
		return part;
	}

	public void setPart(double part) {
		this.part = part;
	}

	public Timestamp getDate_participation() {
		return date_participation;
	}

	public void setDate_participation(Timestamp date_participation) {
		this.date_participation = date_participation;
	}
	
	public Participation[] getListInvestissement(Connection conn,long id8investisseur) throws Exception {
		String sql = "SELECT * FROM Participation where id_Investisseur="+id_Investisseur;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			Participation[] ret = Access.query(conn,Participation.class,sql);
			conn.commit();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public Participation[] find(String sql) throws Exception {
		Connection conn = null;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			Participation[] ret = Access.query(conn,Participation.class,sql);
			conn.commit();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw new Exception(e.getMessage());
		}finally {
			conn.close();
		}
	}
	
	
	
	
	public void insert(Participation e) throws Exception {
		Connection conn = null;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			String req = "INSERT INTO Participation(id_Investisseur,id_Offre,id_option,part,date_participation) VALUES ("+e.getId_Investisseur()+","+e.getId_Offre()+","+e.getId_option()+","+e.getPart()+",now())";
			System.out.println(req);
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
    
    
}
