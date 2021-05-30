package IDea.backend.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import IDea.backend.database.Access;
import IDea.backend.database.Connexion;

public class Detail_offre {
	long id_Detail_Offre ;
    long id_Offre ;
    long id_Option ;
    Double valeur ;
    Double pourcentage ;
    Double pourcentage_Interet ;
    String delai ;
    String propos ;
    
    
	public Detail_offre(long id_Detail_Offre, long id_Offre, long id_Option, Double valeur, Double pourcentage,
			Double pourcentage_Interet, String delai, String propos) {
		super();
		this.id_Detail_Offre = id_Detail_Offre;
		this.id_Offre = id_Offre;
		this.id_Option = id_Option;
		this.valeur = valeur;
		this.pourcentage = pourcentage;
		this.pourcentage_Interet = pourcentage_Interet;
		this.delai = delai;
		this.propos = propos;
	}


	public Detail_offre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId_Detail_Offre() {
		return id_Detail_Offre;
	}


	public void setId_Detail_Offre(long id_Detail_Offre) {
		this.id_Detail_Offre = id_Detail_Offre;
	}


	public long getId_Offre() {
		return id_Offre;
	}


	public void setId_Offre(long id_Offre) {
		this.id_Offre = id_Offre;
	}


	public long getId_Option() {
		return id_Option;
	}


	public void setId_Option(long id_Option) {
		this.id_Option = id_Option;
	}


	public Double getValeur() {
		return valeur;
	}


	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}


	public Double getPourcentage() {
		return pourcentage;
	}


	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}


	public Double getPourcentage_Interet() {
		return pourcentage_Interet;
	}


	public void setPourcentage_Interet(Double pourcentage_Interet) {
		this.pourcentage_Interet = pourcentage_Interet;
	}


	public String getDelai() {
		return delai;
	}


	public void setDelai(String delai) {
		this.delai = delai;
	}


	public String getPropos() {
		return propos;
	}


	public void setPropos(String propos) {
		this.propos = propos;
	}
	
	
	public List<Detail_offre> findBy(String req) throws SQLException{
        Connection co = null;
        PreparedStatement st = null;
        List<Detail_offre> array = new ArrayList<Detail_offre>();
        try {
            co = Connexion.Connection();
            st = co.prepareStatement(req);
            ResultSet result = st.  executeQuery();
            while(result.next()) array.add(new Detail_offre((long)result.getInt("id_Detail_Offre"), (long)result.getInt("id_Offre"),(long)result.getInt("id_Option"),result.getDouble("valeur"),result.getDouble("pourcentage"),result.getDouble("pourcentage_Interet"),result.getString("delai"),result.getString("propos")));
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(st != null) st.close();
            if(co != null) co.close();
        }
        return array;
    }
	
	
}
