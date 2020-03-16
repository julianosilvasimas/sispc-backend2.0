package com.prolagos.sispcbackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prolagos.sispcbackend.domain.cad_rpa_robos;
import com.prolagos.sispcbackend.services.CadRpaService;

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
	


//	@Value("${spring.datasource.username}")
//    private String username;
// 
//    @Value("${spring.datasource.password}")
//    private String password;
// 
//    @Value("${spring.datasource.url}")
//    private String url;
//    
    
	
	

}
