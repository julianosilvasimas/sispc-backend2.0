package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
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

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.dto.EsgotoPreenchimentosDTO;
import com.prolagos.sispcbackend.dto.EsgotoPreenchimentosDTO2;
import com.prolagos.sispcbackend.dto.IndDiariosEsgotoDTO;
import com.prolagos.sispcbackend.services.ApprpaEsgotoIndicadoresService;
import com.prolagos.sispcbackend.services.ApprpaEsgotoService;
import com.prolagos.sispcbackend.services.ApprpaEsgotoUnidadesService;
import com.prolagos.sispcbackend.services.IndicadoresService;

@RestController
@RequestMapping({ "/appesgoto" })
public class ApprpaEsgotoResource {
	
	@Autowired
    private ApprpaEsgotoService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Apprpa_Esgoto_Preenchimentos> find(@PathVariable Integer id) {
        Apprpa_Esgoto_Preenchimentos obj = service.find(id);
        return (ResponseEntity<Apprpa_Esgoto_Preenchimentos>)ResponseEntity.ok().body(obj);
    }
    

    
    //IndDiariosEsgotoDTO
	@Autowired
    private IndicadoresService serviceIndicadoresDiarios;
	

    @RequestMapping(value = { "/atualizarIndicadoresDiarios" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> updateIndDiarios(@RequestBody IndDiariosEsgotoDTO obj) throws UnsupportedEncodingException, EmailException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(obj.getDatas(), formatter);
    	AppWeb_Ind_ExeIndicadores obj2 = serviceIndicadoresDiarios.findByDataInd(obj.getIndicadordiario(),date);
    	obj2.setRealizado(obj.getValor());
    	obj2.setColaborador(obj.getUsuario());
        obj2 = this.serviceIndicadoresDiarios.update2(obj2);
    	return ResponseEntity.noContent().build();
    }
	

//    @RequestMapping(value = { "/unidades" },method = { RequestMethod.GET })
//    public ResponseEntity<List<Apprpa_Esgoto_Unidades>> findUnidades() {
//        final List<Apprpa_Esgoto_Unidades> list = this.service.findUnidades();
//        return (ResponseEntity<List<Apprpa_Esgoto_Unidades>>)ResponseEntity.ok().body(list);
//    }
//    
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>> findAll() {
        final List<Apprpa_Esgoto_Preenchimentos> list = this.service.findAll();
        return (ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>>)ResponseEntity.ok().body(list);
    }

	@Autowired
    private ApprpaEsgotoIndicadoresService service2;
    
    @RequestMapping( method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Apprpa_Esgoto_Preenchimentos obj) throws UnsupportedEncodingException, EmailException {
        obj.setId(null);
        obj = this.service.insert(obj);
    	return ResponseEntity.noContent().build();

    }


    @RequestMapping(value = { "/atualizarvalor/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody EsgotoPreenchimentosDTO2 obj, @PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {
		Apprpa_Esgoto_Preenchimentos objeto = new Apprpa_Esgoto_Preenchimentos();
		Apprpa_Esgoto_Indicadores objeto2 = new Apprpa_Esgoto_Indicadores();
		Apprpa_Esgoto_Unidades objeto3 = new Apprpa_Esgoto_Unidades();
		
		
    	if(id==0) {
    		objeto.setId(null);
    		objeto.setDataIndicador(obj.getData());
    		objeto.setUsuario(obj.getUsuario());
    		objeto.setValor(obj.getValor());
    		objeto.setIndicador(service2.find(obj.getIndicador()));
    		objeto.setUnidade(service3.find(obj.getUnidade()));
            this.service.insert(objeto);
    	}else if(obj.getId()>0){
    		objeto = service.find(obj.getId());
    		 
    		objeto.setUsuario(obj.getUsuario());
    		objeto.setValor(obj.getValor());
    		
            this.service.updatePorDataEIndicador(objeto);
    	}
    	
    	return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Apprpa_Esgoto_Preenchimentos obj, @PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {
        obj.setId(id);
    	Apprpa_Esgoto_Unidades unidade = service3.find(obj.getUnidade().getId());
        obj.setUnidade(unidade);
        Apprpa_Esgoto_Indicadores indicador = service2.find(obj.getIndicador().getId());
        obj.setIndicador(indicador);
        obj = this.service.update(obj,id);
    	
    	return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/aprovarLancamento/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> aprovar(@PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {
    	Apprpa_Esgoto_Preenchimentos obj = service.find(id);
    	obj.setAprovado(1);
        obj = this.service.update(obj,id);
    	return ResponseEntity.noContent().build();
    }
    
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = { "/relatorio/{unid}/{de}/{ate}" },method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>> findByUnidadesDeAte(@PathVariable final Integer unid, @PathVariable final String de, @PathVariable final String ate) {
    	Apprpa_Esgoto_Unidades unidade = service3.find(unid);
    	final List<Apprpa_Esgoto_Preenchimentos> list = this.service.findUnidadeRef(unidade, de, ate);
        return (ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>>)ResponseEntity.ok().body(list);
    }

    
    @RequestMapping(value = { "/relatoriounidadeindicador/{unid}/{indicador}/{de}/{ate}" },method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>> findByUnidadesDeAteIndicUnidade(@PathVariable final Integer unid, @PathVariable final Integer indicador, @PathVariable final String de, @PathVariable final String ate) {
    	Apprpa_Esgoto_Indicadores ind = service2.find(indicador);
    	Apprpa_Esgoto_Unidades und = service3.find(unid);
    	
    	final List<Apprpa_Esgoto_Preenchimentos> list = this.service.findByUnidadesDeAteIndicUnidade(und, ind,de, ate);
        return (ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>>)ResponseEntity.ok().body(list);
    }
    
    

    @RequestMapping(value = { "/produtoquimico/{unid}" },method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>> findByUnidadesDeAte(@PathVariable final Integer unid) {
    	final List<Apprpa_Esgoto_Preenchimentos> list = this.service.findProdQuim(unid);
        return (ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>>)ResponseEntity.ok().body(list);
    }

	@Autowired
    private ApprpaEsgotoUnidadesService service3;
	
    @RequestMapping(value = { "/unidades/{unid}/{de}/{ate}/{usuario}" },method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>> findByUnidadesDeAteUser(@PathVariable final Integer unid, @PathVariable final String de, @PathVariable final String ate, @PathVariable final String usuario) {
    	Apprpa_Esgoto_Unidades unidade = service3.find(unid);
    	final List<Apprpa_Esgoto_Preenchimentos> list = this.service.findUnidadeRefUsuario(unidade, de, ate, usuario);
        return (ResponseEntity<List<Apprpa_Esgoto_Preenchimentos>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/naoaprovados" },method = { RequestMethod.GET })
    public ResponseEntity<List<EsgotoPreenchimentosDTO>> findByNaoAprovadosUsuario() {
    	final List<EsgotoPreenchimentosDTO> list = this.service.findByNaoAprovados();
        return (ResponseEntity<List<EsgotoPreenchimentosDTO>>)ResponseEntity.ok().body(list);
    }
    @RequestMapping(value = { "/naoaprovados/{usuario}" },method = { RequestMethod.GET })
    public ResponseEntity<List<EsgotoPreenchimentosDTO>> findByNaoAprovadosUsuario(@PathVariable final String usuario) {
    	final List<EsgotoPreenchimentosDTO> list = this.service.findByNaoAprovadosUser(usuario);
        return (ResponseEntity<List<EsgotoPreenchimentosDTO>>)ResponseEntity.ok().body(list);
    }
}
