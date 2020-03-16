package com.prolagos.sispcbackend.threadpoolconfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;
import com.prolagos.sispcbackend.services.IndicadoresService;

public class InserirIndicadores implements Callable<String>{

	@Autowired
	private IndicadoresService service;
	
	
	private Integer indicador;
	private Integer ano;
	private Integer diaDeMudanca;
	private Integer unidade;
    String name;

	public InserirIndicadores(Integer indicador, Integer ano, Integer diaDeMudanca, Integer unidade) {
		this.indicador = indicador;
		this.ano = ano;
		this.diaDeMudanca = diaDeMudanca;
		this.unidade = unidade;
	}
	



	@Override
	public String call(){
	    agendamentos();
		return name;
	}
	 
	@Async
	public void agendamentos() { 
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
	        
	        if(diaDeMudanca>=40){
	            DataReferencia = DataAtual.substring(0,8)+"01";
	        }else{
	            if(String.valueOf(Data.get(Calendar.DAY_OF_MONTH)).equals(String.valueOf(diaDeMudanca))){
	                System.out.println(Data.get(Calendar.DAY_OF_MONTH)+"="+diaDeMudanca);
	                DataRef.add(Calendar.MONTH, 1);
	                date3 = DataRef.getTime();  
	                DataReferencia = dateFormat.format(date3);  
	            }
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
	        date2 = Data.getTime();  
	        DataAtual = dateFormat.format(date2);
	    }
     
		
	}
}
