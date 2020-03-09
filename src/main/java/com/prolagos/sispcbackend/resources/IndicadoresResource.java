package com.prolagos.sispcbackend.resources;

import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import com.prolagos.sispcbackend.domain.procedures.ListaIndicadores;
import com.prolagos.sispcbackend.domain.procedures.ResumoIndicadores;
import com.prolagos.sispcbackend.repositories.ListaIndicadoresDAO;
import com.prolagos.sispcbackend.services.IndicadoresService;
import com.prolagos.sispcbackend.threadpoolconfig.EnvioDeEmails;

@RestController
@RequestMapping(value="/indicadores")
public class IndicadoresResource {

	@Autowired
	private IndicadoresService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AppWeb_Ind_ExeIndicadores> find(@PathVariable Integer id) {
		AppWeb_Ind_ExeIndicadores obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = { "/{indicador}/{data}" }, method = { RequestMethod.GET })
    public ResponseEntity<AppWeb_Ind_ExeIndicadores> findDiario(@PathVariable final List<Cad_Ind_Indicadores> indicador, @PathVariable final String data) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate date = LocalDate.parse(data, formatter);
        final AppWeb_Ind_ExeIndicadores obj = this.service.findDiario(indicador, date);
        return (ResponseEntity<AppWeb_Ind_ExeIndicadores>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = { "/pormes/{indicador}/{data}" }, method = { RequestMethod.GET })
    public ResponseEntity<List<AppWeb_Ind_ExeIndicadores>> findIndDiariosPorMes(@PathVariable final List<Cad_Ind_Indicadores> indicador, @PathVariable final String data) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate date = LocalDate.parse(data, formatter);
        final List<AppWeb_Ind_ExeIndicadores> list = this.service.findDiarioPorMes(indicador, date);
		return ResponseEntity.ok().body(list);
	}
    
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody AppWeb_Ind_ExeIndicadores obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getExeindicadorId()).toUri();
		return ResponseEntity.created(uri).build();
	}


	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody AppWeb_Ind_ExeIndicadores obj, @PathVariable Integer id) {
		obj.setExeindicadorId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	

    
	@RequestMapping(value="/updateCsv/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCsv(@RequestBody AppWeb_Ind_ExeIndicadores obj, @PathVariable Integer id) throws ClassNotFoundException, SQLException {
		AppWeb_Ind_ExeIndicadores obj2 = service.findByDataInd(id, obj.getDataindicador());
		obj2.setOrcado(obj.getOrcado());
		obj2.setMeta(obj.getMeta());
		obj2.setMinimo(obj.getMinimo());
		obj2.setMaximo(obj.getMaximo());
		obj2.setForecast(obj.getForecast());
		obj2.setPrevisao(obj.getPrevisao());
		obj = service.update(obj2);
        
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AppWeb_Ind_ExeIndicadores>> findAll() {
		List<AppWeb_Ind_ExeIndicadores> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Autowired
	private ListaIndicadoresDAO dao;
	
	
	//Aqui será o endpoint para o gráfico
	@RequestMapping(value="/grafico/{ref}/{ind}", method=RequestMethod.GET)
	public ResponseEntity<List<ListaIndicadores>> findAllBygrafico(@PathVariable String ref, @PathVariable Integer ind){
		return ResponseEntity.ok().body(dao.findAllBygrafico(ref, ind));
	}


	//Aqui será o endpoint para o gráfico
	@RequestMapping(value="/graficoResumo/{ref}/{ind}", method=RequestMethod.GET)
	public ResponseEntity<List<ResumoIndicadores>> findAllBygraficoResumo(@PathVariable String ref, @PathVariable Integer ind){
		return ResponseEntity.ok().body(dao.findAllBygraficoResumo(ref, ind));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> inserte (@RequestBody AppWeb_Ind_ExeIndicadores obj) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //final LocalDate datInd = LocalDate.parse(dataindicador, formatter);
        //final LocalDate datRef = LocalDate.parse(datareferencia , formatter);
		dao.insert(obj.getAcao(),obj.getAnalise(),obj.getAtendente(),obj.getAtendimento(),obj.getCiclo(), obj.getColaborador(),obj.getComentario(), obj.getDataindicador() ,obj.getDatareferencia(),
				obj.getDentroprazo(),obj.getDentroprazoreg(),obj.getForaprazo(),obj.getForaprazoreg(),obj.getForecast(),obj.getMaximo(),obj.getMeta(),obj.getMinimo(),obj.getOrcado() ,obj.getPecld(),3,
				obj.getPrevisao(),obj.getRealizado(), obj.getIndicadorId(),obj.getUndcodigo(),obj.getRealizadokg());
		
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<AppWeb_Ind_ExeIndicadores>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="exeindicadorId") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<AppWeb_Ind_ExeIndicadores> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
