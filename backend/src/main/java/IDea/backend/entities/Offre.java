package IDea.backend.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;

public class Offre {
	int id_Offre;
	int id_Entreprise;
	String nom_Offre;
	String type_Offre;
	boolean etat;
	
	
	
	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Offre(int id_Offre, int id_Entreprise, String nom_Offre, String type_Offre, boolean etat) {
		super();
		this.id_Offre = id_Offre;
		this.id_Entreprise = id_Entreprise;
		this.nom_Offre = nom_Offre;
		this.type_Offre = type_Offre;
		this.etat = etat;
	}
	
	public void setEtat(boolean etat) {this.etat = etat;}
	public void setId_Entreprise(int id_Entreprise) {this.id_Entreprise = id_Entreprise;}
	public void setId_Offre(int id_Offre) {this.id_Offre = id_Offre;}
	public void setNom_Offre(String nom_Offre) {this.nom_Offre = nom_Offre;}
	public void setType_Offre(String type_Offre) {this.type_Offre = type_Offre;}
	
	public int getId_Entreprise() {return id_Entreprise;}
	public int getId_Offre() {return id_Offre;}
	public String getNom_Offre() {return nom_Offre;}
	public String getType_Offre() {return type_Offre;}
	public boolean getEtat() {return this.etat;}
	
	public Offre[] find(String sql) throws Exception {
		Connection conn = null;
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			Offre[] ret = Access.query(conn,Offre.class,sql);
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
	
	public Offre getLastIdOffre(Connection conn) throws Exception {
		String sql = "SELECT MAX(id_Offre) AS id_Offre FROM Offre";
		try {
			conn = Connexion.Connection();
			conn.setAutoCommit(false);
			Offre[] ret = Access.query(conn,Offre.class,sql);
			conn.commit();
			return ret[0];
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public int getSommeOffre(Connection conn,long id_entreprise) throws Exception {
		String sql = "SELECT COUNT(ID_OFFRE) AS id_Offre FROM Offre where id_entreprise="+id_entreprise;
		Double resultat = 0.0;
		PreparedStatement st = null;
		int count_offre = 0
;		try {
            st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while(result.next())  count_offre = result.getInt("id_Offre");
            result.close();
            return count_offre;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
       
	}
	
}
