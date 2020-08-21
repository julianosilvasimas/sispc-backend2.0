package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Energia_Gestal;
import com.prolagos.sispcbackend.domain.Apprpa_Energia_GestalLog;
import com.prolagos.sispcbackend.domain.Cad_Energia_Gestal;
import com.prolagos.sispcbackend.services.AppRpaGestalService;
import com.prolagos.sispcbackend.services.CadGestalService;
import com.prolagos.sispcbackend.services.IndicadoresService;

@RestController
@RequestMapping({ "/energia" })
public class GestalResource {
	
	@Autowired
    private CadGestalService service;
	
	@Autowired
    private AppRpaGestalService service2;
    

    
	//DADOS DO MEDIDOR
	
    @RequestMapping(value = { "/cadastro/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Cad_Energia_Gestal> find1(@PathVariable Integer id) {
        Cad_Energia_Gestal obj = service.find(id);
        return (ResponseEntity<Cad_Energia_Gestal>)ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = { "/cadastro" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Cad_Energia_Gestal>> findAll1() {
        final List<Cad_Energia_Gestal> list = this.service.findAll();
        return (ResponseEntity<List<Cad_Energia_Gestal>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(value = { "/cadastro/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Cad_Energia_Gestal obj, @PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {
    	if(id==0) {
            obj.setIdEquipamento(null);
            obj = this.service.insert(obj);
    	}else {
            obj.setIdEquipamento(id);
            obj = this.service.update(obj);
    	}
    	
    	return ResponseEntity.noContent().build();
    }
    
    
    
    //DADOS DE MEDIÇÕES
    
    @RequestMapping(value = { "/item/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Apprpa_Energia_Gestal> find2(@PathVariable Integer id) {
        Apprpa_Energia_Gestal obj = service2.find(id);
        return (ResponseEntity<Apprpa_Energia_Gestal>)ResponseEntity.ok().body(obj);
    }
    
    
    @RequestMapping(value = { "/data/{data1}/{data2}/{classif}" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Energia_Gestal>> find3(@PathVariable String data1, @PathVariable String data2, @PathVariable Integer classif){
        final List<Apprpa_Energia_Gestal> list = this.service2.paraData(data1,data2, classif);
        return (ResponseEntity<List<Apprpa_Energia_Gestal>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/item" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Energia_Gestal>> findAll2() {
        final List<Apprpa_Energia_Gestal> list = this.service2.findAll();
        return (ResponseEntity<List<Apprpa_Energia_Gestal>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/item/paraaprovar" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Energia_Gestal>> paraAprovar() {
        final List<Apprpa_Energia_Gestal> list = this.service2.paraAprovar();
        return (ResponseEntity<List<Apprpa_Energia_Gestal>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/item/paraaprovar/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Energia_Gestal>> paraAprovar(@PathVariable Integer id) {
    	Cad_Energia_Gestal obj = service.find(id);
        final List<Apprpa_Energia_Gestal> list = this.service2.paraAprovar2(obj);
        return (ResponseEntity<List<Apprpa_Energia_Gestal>>)ResponseEntity.ok().body(list);
    }
    
    
    

    @RequestMapping(value = { "/item" },method = { RequestMethod.POST })
    public ResponseEntity<Apprpa_Energia_Gestal> insert(@RequestBody Apprpa_Energia_Gestal obj) {
    	Apprpa_Energia_Gestal objReal = this.service2.pelaData(obj.getUnidade(),obj.getDataIndicador());
    	
    	if(objReal!=null) {
    		Apprpa_Energia_GestalLog log= new Apprpa_Energia_GestalLog(
            		obj.getDataIndicador(),
            		objReal,
            		objReal.getAtivoConsumido(),
            		obj.getAtivoConsumido(),
            		obj.getAprovador()
        		);
        	service2.insertLog(log);
        	
        	objReal.setAtivoConsumidoRS(obj.getAtivoConsumidoRS());
        	objReal.setAtivoConsumido(obj.getAtivoConsumido());
        	objReal.setComentario(obj.getComentario());
        	objReal.setAprovacao(obj.getAprovacao());
        	objReal.setAprovador(obj.getAprovador());
            obj = this.service2.update(objReal);
        	
    	}else {
    		obj = this.service2.insert(obj);
    	}
        return  (ResponseEntity<Apprpa_Energia_Gestal>)ResponseEntity.ok().body(obj);
    }
    
    
    
    
    
    
    
    
    
	@Autowired
    private IndicadoresService serviceIndicador;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	
    //APROVAR / REROVAR INDICADORES
    @RequestMapping(value = { "/item/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<AppWeb_Ind_ExeIndicadores> updateItem(@RequestBody Apprpa_Energia_Gestal obj, @PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {
        AppWeb_Ind_ExeIndicadores obj2 = null;    
        AppWeb_Ind_ExeIndicadores obj3 = null;    
        
        
    	Apprpa_Energia_Gestal objReal = service2.find(id);

        Apprpa_Energia_GestalLog log= new Apprpa_Energia_GestalLog(
        		obj.getDataIndicador(),
        		objReal,
        		objReal.getAtivoConsumido(),
        		obj.getAtivoConsumido(),
        		obj.getAprovador()
    		);
    	service2.insertLog(log);
    	
    	
    	objReal.setAtivoConsumidoRS(obj.getAtivoConsumidoRS());
    	objReal.setAtivoConsumido(obj.getAtivoConsumido());
    	objReal.setComentario(obj.getComentario());
    	objReal.setAprovacao(obj.getAprovacao());
    	objReal.setAprovador(obj.getAprovador());
    	
    	
        obj = this.service2.update(objReal);
     
        //VARIAVEIS
        String date = obj.getDataIndicador();
        LocalDate localDate = LocalDate.parse(date, formatter);
        Integer indicadorKw = obj.getUnidade().getIndicadorKw();
        Integer indicadorKwm3 = obj.getUnidade().getIndicadorkwm3();
        Integer indicadorRs = obj.getUnidade().getIndicadorRs();
        Integer indicadorRsm3 = obj.getUnidade().getIndicadorrsm3();
        Integer indicadoVolume = obj.getUnidade().getIndicadorVolume();
        AppWeb_Ind_ExeIndicadores volume = null;
        
        if(obj.getAprovacao()!=null) {
	        if(obj.getAprovacao()>=1 && indicadoVolume != null) {
		        volume = serviceIndicador.findByDataInd(indicadoVolume, localDate);
	        }
	        
	        
	        
	        //INDICADOR DE KW
	        if(obj.getAprovacao()>=1 && indicadorKw != null) {
	            obj2 = serviceIndicador.findByDataInd(indicadorKw, localDate);
	            obj2.setRealizado((double) Math.round(obj.getAtivoConsumido()));
	            obj2.setColaborador(obj.getAprovador());
	            obj2.setComentario(obj.getComentario());
	            serviceIndicador.update(obj2);
	        }
	        
	        //INDICADOR DE KW/M3
	        if(obj.getAprovacao()>=1 && indicadorKwm3 != null) {
	            obj2 = serviceIndicador.findByDataInd(indicadorKwm3, localDate);
	            Double kwm3 = (double) Math.round(obj.getAtivoConsumido()/volume.getRealizado());
	            obj2.setRealizado(kwm3);
	            obj2.setColaborador(obj.getAprovador());
	            obj2.setComentario(obj.getComentario());
	            serviceIndicador.update(obj2);
	        }
	        
	        //INDICADOR DE RS
	        if(obj.getAprovacao()>=1 && indicadorRs != null) {
	            obj2 = serviceIndicador.findByDataInd(indicadorRs, localDate);
	            obj2.setRealizado((double) Math.round((obj.getAtivoConsumidoRS())));
	            obj2.setColaborador(obj.getAprovador());
	            obj2.setComentario(obj.getComentario());
	            serviceIndicador.update(obj2);
	        }
	        
	        //INDICADOR DE RS/M3
	        if(obj.getAprovacao()>=1 && indicadorRsm3 != null) {
	            obj2 = serviceIndicador.findByDataInd(indicadorRsm3, localDate);
	            Double rsm3 = (double) Math.round(obj.getAtivoConsumidoRS()/volume.getRealizado());
	            obj2.setRealizado(rsm3);
	            obj2.setColaborador(obj.getAprovador());
	            obj2.setComentario(obj.getComentario());
	            serviceIndicador.update(obj2);
	        }
        }
        return (ResponseEntity<AppWeb_Ind_ExeIndicadores>)ResponseEntity.ok().body(obj2);
    }
    
    
    

    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
   
    

}
