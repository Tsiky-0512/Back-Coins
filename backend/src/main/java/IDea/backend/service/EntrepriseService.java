package IDea.backend.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IDea.backend.database.Connexion;
import IDea.backend.entities.Detail_offre;
import IDea.backend.entities.Entreprise;
import IDea.backend.entities.Offre;

@CrossOrigin
@RestController
@RequestMapping("/entreprise")
public class EntrepriseService {
	
	@GetMapping("/etat")
	public Map<String,Object> etat(@RequestBody Map<String, Object> body) throws Exception{
		Map<String,Object> response = new HashMap<String, Object>();
		int id_entreprise = (int) body.get("id_entreprise");
		try {
			Entreprise en = new Entreprise();
			Connection conn = Connexion.Connection();
			int count_offre = new Offre().getSommeOffre(conn,id_entreprise);
			
			Map<String, Double> map = en.getCoins(conn, id_entreprise);
			Entry<String, Double> entry = map. entrySet(). iterator(). next();
			String key = entry. getKey();
			
			Map<String, Integer> map2 = en.getParticipants(conn, id_entreprise);
			Entry<String, Integer> entry2 = map2. entrySet(). iterator(). next();
			
			Double value = entry. getValue();
			int value2 = entry2. getValue();
			System.out.println("dddddddddddddd"+value2);
			
			response.put("nom_entreprise", key);
			response.put("Bitcoins", value);
			response.put("Participants", value2);
			response.put("Offre", count_offre);
			response.put("message", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message",e.getMessage());
		}
		return response;
	}
}
