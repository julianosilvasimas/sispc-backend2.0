package com.prolagos.sispcbackend.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.prolagos.sispcbackend.bots.GerenciadorDeBots;
import com.prolagos.sispcbackend.bots.Volumes;
import com.prolagos.sispcbackend.domain.cad_rpa_robos;
import com.prolagos.sispcbackend.services.CadRpaService;

import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping(value="/cadrpa")
public class CadRpaResource {
	public CadRpaResource() {}
	
	@Autowired
	private CadRpaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<cad_rpa_robos> find(@PathVariable Integer id) {
		cad_rpa_robos obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<cad_rpa_robos>> findAll() {
		List<cad_rpa_robos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	


	@Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;
 
    @Value("${spring.datasource.url}")
    private String url;
    
    @GetMapping("/datasource")
	public String configurations() { 
        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("password", password);
        response.put("url", url);
        return response.toString();
    }
    
    @Autowired
    ThreadPoolTaskExecutor threadPool;
    GerenciadorDeBots gerentedebots = new GerenciadorDeBots();
    
	@RequestMapping(value="/startbot/{id}", method=RequestMethod.GET)
	public String startBot(@PathVariable Integer id)  {
		String Status="";
		String d = configurations();
		List<Future<String>> futureList = new ArrayList<>();
		
		//INSTANCIAR QUAL ROBOT VAI SER INICIADO
		if(id==1 && gerentedebots.vol.getStatus().contains("Pronto")) {
	        gerentedebots = new GerenciadorDeBots(id, url, username, password);
	        Status ="{ \"status\": \"Executando\" }";
		}
        Future<String> result = threadPool.submit(gerentedebots);
        futureList.add(result);
		return gson.toJson(Status);
	}
	
	
	Gson gson = new Gson();    
	@RequestMapping(value="/statusbot/{id}", method=RequestMethod.GET)
	public String statusBot(@PathVariable Integer id) throws IOException, JSONException {
		String Status ="{ \"status\": \"Falha\" }";
		if(id==1) {
			Status= gerentedebots.vol.getStatus();
		}
		return gson.toJson(Status);
	}
	
	
	

}
