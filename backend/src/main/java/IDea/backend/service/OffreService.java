package IDea.backend.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IDea.backend.database.Access;
import IDea.backend.entities.Entreprise;
import IDea.backend.entities.Offre;
import IDea.backend.entities.Participation;
import IDea.backend.helper.Function;

@CrossOrigin
@RestController
@RequestMapping("")
public class OffreService {
	@GetMapping("/listOffre")
	public Map<String,Object> listOffre() throws Exception{
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			Offre[] liste_offre = new Offre().find("SELECT * FROM OFFRE");
			response.put("data", liste_offre);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/historique_participation")
	public Map<String,Object> historiqueParticipation(@RequestBody Entreprise input) throws Exception {
		String sql = "Select id_Participation,id_Investisseur,id_Offre,id_option,sum(part_insert+part_last) as part_last , date_participation from participation id_Entreprise="+input.getId_Entreprise();
		Map<String,Object> response = new HashMap<String, Object>();
		try {
			Participation[] liste_offre = new Participation().find(sql);
			response.put("data", liste_offre);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			response.put("message",e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/insertOffre")
	public Map<String,Object> insertOffre(@RequestBody Offre input) throws SQLException {
		Map<String, Object> response = new HashMap<String, Object>();
		String sql = "INSERT INTO Option_Offre(id_Entreprise,nom_Offre,type_Offre,etat) VALUES("+input.getId_Entreprise()+",'"+input.getNom_Offre()+"','"+input.getType_Offre()+"','valable')";
		Connection conn = null;
		try {
			conn.setAutoCommit(false);
			Access.execute(conn, sql);
			conn.commit();
			response.put("message", "offer option inserted !");
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getMessage());
		}finally {
			conn.close();
		}
		return response;
	}
	
	@PostMapping("/TotaliteEntreprisen")
	public Map<Integer[],Double> totaliteEntreprisen(@RequestBody Entreprise input) throws Exception { 
		Function appel = new Function();
		return appel.totalite(input);
	}
}
