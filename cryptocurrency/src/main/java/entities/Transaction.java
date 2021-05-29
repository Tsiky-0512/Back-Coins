package entities;

import java.sql.Timestamp;

public class Transaction {
	int id_Transaction;
    int id_Investisseur;
    String description;
    double valeur;
    Timestamp last_update;
    
    public void setDescription(String description) {this.description = description;}
    public void setId_Investisseur(int id_Investisseur) {this.id_Investisseur = id_Investisseur;}
    public void setId_Transaction(int id_Transaction) {this.id_Transaction = id_Transaction;}
    public void setLast_update(Timestamp last_update) {this.last_update = last_update;}
    public void setValeur(double valeur) {this.valeur = valeur;}
    
    public String getDescription() {return description;}
    public int getId_Investisseur() {return id_Investisseur;}
    public int getId_Transaction() {return id_Transaction;}
    public Timestamp getLast_update() {return last_update;}
    public double getValeur() {return valeur;}
}
