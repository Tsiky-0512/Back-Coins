package entities;

public class Investisseur {
	int id_Investisseur;
	String nom_Investisseur;
	String prenom_Investisseur;
	String email;
	String mdp;
	
	public void setEmail(String email) {this.email = email;}
	public void setId_Investisseur(int id_Investisseur) {this.id_Investisseur = id_Investisseur;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	public void setNom_Investisseur(String nom_Investisseur) {this.nom_Investisseur = nom_Investisseur;}
	public void setPrenom_Investisseur(String prenom_Investisseur) {this.prenom_Investisseur = prenom_Investisseur;}
	
	public String getEmail() {return email;}
	public String getMdp() {return mdp;}
	public int getId_Investisseur() {return id_Investisseur;}
	public String getNom_Investisseur() {return nom_Investisseur;}
	public String getPrenom_Investisseur() {return prenom_Investisseur;}
	
}
