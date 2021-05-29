package entities;

import java.sql.Timestamp;

public class Detail_Offre {
	int id_Detail_Offre;
    int id_Offre;
    int id_Option;
    double valeur;
    double pourcentage;
    double pourcebtage_Interet;
    Timestamp delai;
    String propos;
    
    public void setDelai(Timestamp delai) {this.delai = delai;}
    public void setId_Detail_Offre(int id_Detail_Offre) {this.id_Detail_Offre = id_Detail_Offre;}
    public void setId_Offre(int id_Offre) {this.id_Offre = id_Offre;}
    public void setId_Option(int id_Option) {this.id_Option = id_Option;}
    public void setPourcebtage_Interet(double pourcebtage_Interet) {this.pourcebtage_Interet = pourcebtage_Interet;}
    public void setPourcentage(double pourcentage) {this.pourcentage = pourcentage;}
    public void setPropos(String propos) {this.propos = propos;}
    public void setValeur(double valeur) {this.valeur = valeur;}
    
    public Timestamp getDelai() {return delai;}
    public int getId_Detail_Offre() {return id_Detail_Offre;}
    public int getId_Offre() {return id_Offre;}
    public int getId_Option() {return id_Option;}
    public double getPourcebtage_Interet() {return pourcebtage_Interet;}
    public double getPourcentage() {return pourcentage;}
    public String getPropos() {return propos;}
    public double getValeur() {return valeur;}
    
}
