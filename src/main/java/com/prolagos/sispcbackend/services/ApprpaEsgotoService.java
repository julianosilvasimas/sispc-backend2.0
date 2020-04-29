package com.prolagos.sispcbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_PreenchimentosLog;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.dto.EsgotoPreenchimentosDTO;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoIndicadoresRepository;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoLogRepository;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ApprpaEsgotoService {

	@Autowired
	private ApprpaEsgotoRepository repo;

	
	public List<Apprpa_Esgoto_Preenchimentos> findAll() {
		return repo.findAll();
	}

	
	public Page<Apprpa_Esgoto_Preenchimentos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Esgoto_Preenchimentos find(Integer id) {
		Optional<Apprpa_Esgoto_Preenchimentos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Esgoto_Preenchimentos.class.getName(), null));
	}
	
	public Apprpa_Esgoto_Preenchimentos insert(Apprpa_Esgoto_Preenchimentos obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}

	public Apprpa_Esgoto_Preenchimentos updatePorDataEIndicador(Apprpa_Esgoto_Preenchimentos obj) {
		
		repo.updatePorDataEIndicador(obj.getId(),obj.getValor(),obj.getUsuario());
		
		return obj;
	}
	public Apprpa_Esgoto_Preenchimentos update(Apprpa_Esgoto_Preenchimentos obj, Integer id) {
		obj.setId(id);
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
	
	
	@Autowired
	private ApprpaEsgotoLogRepository repo2;
	public Apprpa_Esgoto_PreenchimentosLog insertLog(Apprpa_Esgoto_PreenchimentosLog obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo2.save(obj);
		return obj;
	}
	





	public List<Apprpa_Esgoto_Preenchimentos> findUnidadeRef(Apprpa_Esgoto_Unidades unidade, String de, String ate) {
		de = de + " 00:00:00";
		ate = ate + " 23:59:59";
		
		return repo.consultaPorData(unidade,de, ate);
	}


	public List<Apprpa_Esgoto_Preenchimentos> findByUnidadesDeAteIndicUnidade(Apprpa_Esgoto_Unidades unidade,Apprpa_Esgoto_Indicadores indicador, String de, String ate) {
		de = de + " 00:00:00";
		ate = ate + " 23:59:59";
		
		return repo.findByUnidadesDeAteIndicUnidade(unidade,indicador,de, ate);
	}
	
	
	public List<Apprpa_Esgoto_Preenchimentos> findUnidadeRefUsuario(Apprpa_Esgoto_Unidades unidade, String de, String ate, String usuario) {
		de = de + " 00:00:00";
		ate = ate + " 23:59:59";
		
		return repo.consultaPorDataUsuario(unidade,de, ate,usuario);
	}
	
	public List<EsgotoPreenchimentosDTO> findByNaoAprovadosUser(String usuario) {
		List<Apprpa_Esgoto_Preenchimentos> lista = repo.findByNaoAprovadosUser(usuario);
		List<EsgotoPreenchimentosDTO> listDto = lista.stream().map(obj -> new EsgotoPreenchimentosDTO(obj)).collect(Collectors.toList());  
		return listDto;
	}
	

	public List<EsgotoPreenchimentosDTO> findByNaoAprovados() {
		List<Apprpa_Esgoto_Preenchimentos> lista =  repo.findByNaoAprovados();
		List<EsgotoPreenchimentosDTO> listDto = lista.stream().map(obj -> new EsgotoPreenchimentosDTO(obj)).collect(Collectors.toList());  
		return listDto;
	}
	
	
	  
	

	@Autowired
	private ApprpaEsgotoIndicadoresRepository repo3;

	public List<Apprpa_Esgoto_Preenchimentos> findProdQuim(Integer unidade) {
		List<Apprpa_Esgoto_Preenchimentos> list = new ArrayList<>();
		List<Apprpa_Esgoto_Indicadores> listind = repo3.findProdQuim(unidade,4);
		for(int i =0 ;i<listind.size();i++) {
			repo.findProdQuim(new PageRequest(0, 1),unidade,listind.get(i)).stream().map(obj -> list.add(obj)).collect(Collectors.toList());
			
		}
		return list;
	}
}
