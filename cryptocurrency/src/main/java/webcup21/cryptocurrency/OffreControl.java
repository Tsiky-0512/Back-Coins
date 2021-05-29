package webcup21.cryptocurrency;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import databases.ConnectDB;
import entities.Entreprise;
import entities.Offre;
import entities.Participation;
import helper.Function;
import helper.General;

@CrossOrigin
@RestController
public class OffreControl {
	@GetMapping("/listOffre")
	public Offre[] login() throws Exception{
		General appel = new General();
		String sql = "Select * from offre";
		Connection c = ConnectDB.connectToDb();
		Offre[] ret = appel.find(c,Offre.class,sql);
		c.close();
		return ret;
	}
	
	@PostMapping("/HistoriqueParticipation")
	public Participation[] historiqueParticipation(@RequestBody Entreprise input) throws Exception {
		General appel = new General();
		String sql = "Select id_Participation,id_Investisseur,id_Offre,id_option,sum(part_insert+part_last) as part_last , date_participation from participation id_Entreprise="+input.getId_Entreprise();
		Connection c = ConnectDB.connectToDb();
		Participation[] ret = appel.find(c,Participation.class,sql);
		c.close();
		return ret;
	}
	
	@PostMapping("/insertOffre")
	public void insertOffre(@RequestBody Offre input) throws SQLException {
		General appel = new General();
		String sql = "INSERT INTO Option_Offre(id_Entreprise,nom_Offre,type_Offre,etat) VALUES("+input.getId_Entreprise()+",'"+input.getNom_Offre()+"','"+input.getType_Offre()+"','valable')";
		Connection c = ConnectDB.connectToDb();
		appel.insertOrDelete(c, sql);
		c.close();
	}
	
	@PostMapping("/TotaliteEntreprisen")
	public Map<Integer[],Double> totaliteEntreprisen(@RequestBody Entreprise input) throws Exception { 
		Function appel = new Function();
		return appel.totalite(input);
	}
	
}
