package entities;

public class Offre {
	int id_Offre;
	int id_Entreprise;
	String nom_Offre;
	String type_Offre;
	boolean etat;
	
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
	
}
