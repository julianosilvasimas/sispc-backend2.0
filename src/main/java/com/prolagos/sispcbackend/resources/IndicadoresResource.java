package com.prolagos.sispcbackend.resources;

import java.net.URI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;
import com.prolagos.sispcbackend.domain.procedures.ListaIndicadores;
import com.prolagos.sispcbackend.domain.procedures.ResumoIndicadores;
import com.prolagos.sispcbackend.repositories.ListaIndicadoresDAO;
import com.prolagos.sispcbackend.services.IndicadoresService;

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
		obj2.setValorretido(obj.getValorretido());
		obj2.setForecast(obj.getForecast());
		obj2.setForecast2(obj.getForecast2());
		obj2.setForecast3(obj.getForecast3());
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
	
	

    @Autowired
    ThreadPoolTaskExecutor threadPool;
	
    @Async
	@RequestMapping(value="/novoindicador/{indicador}/{ano}/{diaDeMudanca}/{unidade}/{dialimite}", method=RequestMethod.GET)
	public ResponseEntity<Void> insert(
			@PathVariable Integer indicador,
			@PathVariable Integer ano,
			@PathVariable Integer diaDeMudanca,
			@PathVariable Integer unidade,
			@PathVariable Integer dialimite) {
		Calendar Data = Calendar.getInstance();
		Calendar DataAte = Calendar.getInstance();
		Calendar DataRef = Calendar.getInstance();

		Cad_Ind_Indicadores objCadInd = new Cad_Ind_Indicadores();
		objCadInd.setIndicadorId(indicador);
		Cad_SisPC_Unidades objUnidade = new Cad_SisPC_Unidades();
		objUnidade.setUnidadeId(unidade);
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	
	    Integer dia = 01;
	    Integer mes = 00;
	    
	    //DATA FINAL
	    DataAte.set(Calendar.DAY_OF_MONTH, dia); 
	    DataAte.set(Calendar.MONTH, mes); 
	    DataAte.set(Calendar.YEAR, ano+1); 
	    Date date = DataAte.getTime();  
	    String DataFinal = dateFormat.format(date);  
	    
	    //DATAINDICADOR
	    Data.set(Calendar.DAY_OF_MONTH, dia); 
	    Data.set(Calendar.MONTH, mes); 
	    Data.set(Calendar.YEAR, ano); 
	    Date date2 = Data.getTime();  
	    String DataAtual = dateFormat.format(date2);  
	    
	    //DATAREFERENCIA
	    DataRef.set(Calendar.DAY_OF_MONTH, dia); 
	    DataRef.set(Calendar.MONTH, mes); 
	    DataRef.set(Calendar.YEAR, ano); 
	    Date date3 = DataRef.getTime();  
	    String DataReferencia = dateFormat.format(date3);  
	    
	    while(DataAtual.equals(DataFinal)!=true){
	    	
	        if(diaDeMudanca<=25 && dialimite==0 ){
	            if(String.valueOf(Data.get(Calendar.DAY_OF_MONTH)).equals(String.valueOf(diaDeMudanca))){
	                System.out.println(Data.get(Calendar.DAY_OF_MONTH)+"="+diaDeMudanca);
	                DataRef.add(Calendar.MONTH, 1);
	                date3 = DataRef.getTime();  
	                DataReferencia = dateFormat.format(date3);  
	            }
	        }else {
	            DataReferencia = DataAtual.substring(0,8)+"01";
	        }
		    
		  //==========================================================================================================
	        //INICIO AQUI
	        System.out.println("Data atual: "+DataAtual+" | "+DataReferencia);
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate localDateIndicador = LocalDate.parse(DataAtual, formatter);
	        
	        try {
		        AppWeb_Ind_ExeIndicadores obj = service.findByDataInd(indicador,localDateIndicador);
		        System.out.println(obj.getExeindicadorId()+" | "+obj.getOrcado()+" | "+obj.getRealizado());
	        }catch(Exception e) {
	        	System.out.println("ERRO NA EXECUCAO");
		        LocalDate localDateReferencia = LocalDate.parse(DataReferencia, formatter);
	        	AppWeb_Ind_ExeIndicadores obj = new AppWeb_Ind_ExeIndicadores(null,localDateReferencia,localDateIndicador,0,null,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0,0,null,null,null,"",objCadInd,objUnidade,0.0,0.0,0.0);
	        	service.insert(obj);
	        }
	        //FIM AQUI
	        //==========================================================================================================

	        Data.add(Calendar.DATE, 1);
		    if(dialimite>0) {
		    	if(Data.get(Calendar.DAY_OF_MONTH)>dialimite) {
		    		Data.set(Calendar.DAY_OF_MONTH, 1); 
			        Data.add(Calendar.MONTH, 1);
		    	}else {
			        Data.add(Calendar.DATE, 1);
		    	}
		    }
	        date2 = Data.getTime();  
	        DataAtual = dateFormat.format(date2);
	    }
	    
	    URI uri = null;
		return ResponseEntity.created(uri).build();
	}
}
