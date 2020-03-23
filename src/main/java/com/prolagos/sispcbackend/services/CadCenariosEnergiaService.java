package com.prolagos.sispcbackend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Energia_Cenario;
import com.prolagos.sispcbackend.domain.Apprpa_Energia_cadCenarios;
import com.prolagos.sispcbackend.repositories.CadCenariosEnergiaRepository;
import com.prolagos.sispcbackend.repositories.CenariosEnergiaRepository;
import com.prolagos.sispcbackend.repositories.CenariosUnidadesRepository;
import com.prolagos.sispcbackend.repositories.IndicadoresRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class CadCenariosEnergiaService {

	@Autowired
	private CadCenariosEnergiaRepository repo;

	
	public List<Apprpa_Energia_cadCenarios> findAll() {
		return repo.findAll();
	}
	
	
	public Page<Apprpa_Energia_cadCenarios> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Energia_cadCenarios find(Integer id) {
		Optional<Apprpa_Energia_cadCenarios> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Energia_cadCenarios.class.getName(), null));
	}
	
	public Apprpa_Energia_cadCenarios insert(Apprpa_Energia_cadCenarios obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	

	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Objeto que possui relacionamentos");
		}
	}
	
	@Async
	public Apprpa_Energia_cadCenarios update(List<Apprpa_Energia_Cenario> listaNew, Apprpa_Energia_cadCenarios obj) {
		updatecenario(listaNew,obj);
		return obj;
	}
	
	//=================================================================================================================================================
	//INSERT CENARIO DIAS

	@Autowired
	private CenariosEnergiaRepository repo2;
	@Autowired
	private CenariosUnidadesRepository repo3;
	
	@Async
	public void updatecenario(List<Apprpa_Energia_Cenario> objnew, Apprpa_Energia_cadCenarios idCadCenario) {
		for(int i=0;i<objnew.size();i++) {
			objnew.get(i).setCadCenario(idCadCenario);
			Apprpa_Energia_Cenario cenario = repo2.save(objnew.get(i));
			
			for(int j=0;j<objnew.get(i).getUnidades().size();j++) {
				objnew.get(i).getUnidades().get(j).setCenarioLinha(cenario);
			}
			repo3.saveAll(objnew.get(i).getUnidades());
		}
	}


	//=================================================================================================================================================
	//ATUALIZARINDICADORES
	@Autowired
	private IndicadoresRepository repo4;
	
	@Async
	public void updateIndicador(Apprpa_Energia_cadCenarios obj, Integer idKw, Integer idRS, Integer Kwm3, Integer RSm3) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<AppWeb_Ind_ExeIndicadores> Lista = new ArrayList<>();
		
		for(int i=0;i<obj.getCenariosLinhas().size();i++) {
			LocalDate date = LocalDate.parse(obj.getCenariosLinhas().get(i).getDataIndicador(), formatter);
			
			AppWeb_Ind_ExeIndicadores obj1= repo4.findByDataInd(idKw, date.plusDays(1));
			obj1.setRealizado(obj.getCenariosLinhas().get(i).getRealGestalPorcentagemkWh());
			repo4.save(obj1);

			AppWeb_Ind_ExeIndicadores obj2= repo4.findByDataInd(Kwm3, date.plusDays(1));
			obj2.setRealizado(obj.getCenariosLinhas().get(i).getRealizadokWhM3());
			repo4.save(obj2);
			
			AppWeb_Ind_ExeIndicadores obj3= repo4.findByDataInd(idRS, date.plusDays(1));
			obj3.setRealizado(obj.getCenariosLinhas().get(i).getRealGestalPorcentagemRS());
			repo4.save(obj3);
			
			AppWeb_Ind_ExeIndicadores obj4= repo4.findByDataInd(RSm3, date.plusDays(1));
			obj4.setRealizado(obj.getCenariosLinhas().get(i).getRealizadoRSM3());
			repo4.save(obj4);
		}
		
		
	}

	
	
}
