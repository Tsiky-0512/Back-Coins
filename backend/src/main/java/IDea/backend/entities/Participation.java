package IDea.backend.entities;

import java.sql.Connection;
import java.sql.Timestamp;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;

public class Participation {
	int id_Participation;
    int id_Investisseur;
    int id_Offre;
    int id_option;
    double part_last;
    double part_insert;
    Timestamp date_participation;
    

    public Participation(int id_Participation, int id_Investisseur, int id_Offre, int id_option, double part_last,
			double part_insert, Timestamp date_participation) {
		super();
		this.id_Participation = id_Participation;
		this.id_Investisseur = id_Investisseur;
		this.id_Offre = id_Offre;
		this.id_option = id_option;
		this.part_last = part_last;
		this.part_insert = part_insert;
		this.date_participation = date_participation;
	}
    
        
	public Participation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setId_Investisseur(int id_Investisseur) {this.id_Investisseur = id_Investisseur;}
    public void setId_Offre(int id_Offre) {this.id_Offre = id_Offre;}
    public void setId_option(int id_option) {this.id_option = id_option;}
    public void setId_Participation(int id_Participation) {this.id_Participation = id_Participation;}
    public void setPart_insert(double part_insert) {this.part_insert = part_insert;}
    public void setPart_last(double part_last) {this.part_last = part_last;}
    public void setDate_participation(Timestamp date_participation) {this.date_participation = date_participation;}
    
    public int getId_Investisseur() {return id_Investisseur;}
    public int getId_Offre() {return id_Offre;}
    public int getId_option() {return id_option;}
    public int getId_Participation() {return id_Participation;}
    public double getPart_insert() {return part_insert;}
    public double getPart_last() {return part_last;}
    public Timestamp getDate_participation() {return date_participation;}
    
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
}
