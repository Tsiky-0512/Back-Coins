package IDea.backend.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IDea.backend.entities.Entreprise;
import IDea.backend.entities.Investisseur;
import IDea.backend.metier.Login;


@CrossOrigin
@RestController
@RequestMapping("")
public class UserService {
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Map<Object, Object> body) throws SQLException{
		String type = (String) body.get("type");
		String name = (String) body.get("name");
		String password = (String) body.get("pasword");
		Map<String,Object> response  = new Login().Login(name, password, type);
		return response;
	}
	
	
	@PostMapping("/inscription_entreprise")
	public Map<String, Object> Inscription(@RequestBody Map<Object,Object> body) throws SQLException{
	Entreprise enp = new Entreprise((long) 0, (String) body.get("nom"), (String) body.get("responsable"),  (String) body.get("contact"),  (String) body.get("email"),  (String) body.get("mdp"));
		Map<String,Object> response  = new Login().Inscription(enp);
		return response;
		
	}
	
	@PostMapping("/inscription_investisseur")
	public Map<String, Object> Inscription_invessitisseur(@RequestBody Map<Object,Object> body) throws SQLException{
		Investisseur enp = new Investisseur((long)0,(String) body.get("nom"),(String) body.get("prenom"),(String) body.get("email"),(String) body.get("mdp"));
		Map<String,Object> response  = new Login().Inscription(enp);
		return response;
	}
	
}
