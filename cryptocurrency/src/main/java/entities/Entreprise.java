package entities;

public class Entreprise {
	int id_Entreprise;
	String nom_Entreprise;
	String responsable;
	String contact;
	String email;
	String mdp;
	
	public void setContact(String contact) {this.contact = contact;}
	public void setEmail(String email) {this.email = email;}
	public void setId_Entreprise(int id_Entreprise) {this.id_Entreprise = id_Entreprise;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	public void setNom_Entreprise(String nom_Entreprise) {this.nom_Entreprise = nom_Entreprise;}
	public void setResponsable(String responsable) {this.responsable = responsable;}
	
	public String getContact() {return contact;}
	public String getEmail() {return email;}
	public int getId_Entreprise() {return id_Entreprise;}
	public String getMdp() {return mdp;}
	public String getNom_Entreprise() {return nom_Entreprise;}
	public String getResponsable() {return responsable;}
	
}
