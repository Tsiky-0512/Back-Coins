package helper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import databases.ConnectDB;
import entities.Entreprise;
import entities.Offre;
import entities.Participation;

public class Function {
	
	public List<Double> statistic(double vola, double taux,String partition){
		if(partition.compareTo("annual")==0) {
			return this.annual(vola, taux);
		}else if(partition.compareTo("weekly")==0) {
			return this.hebdo(vola, taux);
		}else {
			return this.trim(vola, taux);
		}
	}
	
	public List<Double> annual(Double vola,double taux){
		List<Double> ret = new ArrayList<Double>();
		double temp = vola;
		for(int i=0 ; i<12 ; i++) {
			temp = temp + ( temp * ((taux/12)*100));
			ret.add(temp);
		}
		return ret;
	}
	
	public List<Double> hebdo(Double vola,double taux){
		List<Double> ret = new ArrayList<Double>();
		double temp = vola;
		for(int i=0 ; i<52 ; i++) {
			temp = temp + ( temp * ((taux/52)*100));
			ret.add(temp);
		}
		return ret;
	}
	
	public List<Double> trim(Double vola,double taux){
		List<Double> ret = new ArrayList<Double>();
		double temp = vola;
		for(int i=0 ; i<4 ; i++) {
			temp = temp + ( temp * ((taux/4)*100));
			ret.add(temp);
		}
		return ret;
	}
	
	public Map<Integer[],Double> totalite(Entreprise input) throws Exception {
		Map<Integer[],Double> map=new HashMap<Integer[],Double>();  
		General appel = new General();
		String sql = "select * from Offre where id_Entreprise = "+input.getId_Entreprise();
		Connection c = ConnectDB.connectToDb();
		Offre[] off = appel.find(c,Offre.class,sql);
		for(int i=0 ; i<off.length ; i++) {
			sql = "Select id_Investisseur, sum(part_insert+part_last) as part_last from participation where id_Offre = "+off[i].getId_Offre() + " group by id_Investisseur";
			Participation[] ret = appel.find(c,Participation.class,sql);
			for(int j=0 ; j<ret.length ; j++) {
				Integer[] intTemp = new Integer[2];
				intTemp[0] = ret[i].getId_Investisseur();
				intTemp[1] = off[i].getId_Offre();
				double temp = ret[i].getPart_last();
				map.put(intTemp,temp);  
			}
		}
		c.close();
		return map;
	}
	
	public double[] moneyEntreprise(Entreprise input) throws Exception {
		Map<Integer[],Double> map=new HashMap<Integer[],Double>();  
		General appel = new General();
		String sql = "select * from Offre where id_Entreprise = "+input.getId_Entreprise();
		Connection c = ConnectDB.connectToDb();
		Offre[] off = appel.find(c,Offre.class,sql);
		for(int i=0 ; i<off.length ; i++) {
			sql = "Select sum(part_insert+part_last) as part_last from participation where id_Offre = "+off[i].getId_Offre() + " group by id_Investisseur";
			Participation[] ret = appel.find(c,Participation.class,sql);
			for(int j=0 ; j<ret.length ; j++) {
				Integer[] intTemp = new Integer[2];
				intTemp[0] = ret[i].getId_Investisseur();
				intTemp[1] = off[i].getId_Offre();
				double temp = ret[i].getPart_last();
				map.put(intTemp,temp);  
			}
		}
		c.close();
		return map;
	}
}
