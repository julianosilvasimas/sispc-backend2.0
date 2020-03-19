package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_Gestal;
import com.prolagos.sispcbackend.domain.Apprpa_Energia_GestalLog;
import com.prolagos.sispcbackend.domain.Cad_Energia_Gestal;
import com.prolagos.sispcbackend.repositories.AppRpaGestalLogRepository;
import com.prolagos.sispcbackend.repositories.AppRpaGestalRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class AppRpaGestalService {

	@Autowired
	private AppRpaGestalRepository repo;

	
	public List<Apprpa_Energia_Gestal> findAll() {
		return repo.findAll();
	}
	

	public List<Apprpa_Energia_Gestal> paraData(String Data1,String Data2, Integer Classif) {
		return repo.paraData(Data1,Data2, Classif);
	}
	
	public List<Apprpa_Energia_Gestal> paraAprovar() {
		return repo.paraAprovar();
	}

	public List<Apprpa_Energia_Gestal> paraAprovar2(Cad_Energia_Gestal id) {
		return repo.paraAprovar2(id);
	}
	
	public Page<Apprpa_Energia_Gestal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Energia_Gestal find(Integer id) {
		Optional<Apprpa_Energia_Gestal> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Energia_Gestal.class.getName(), null));
	}
	
	public Apprpa_Energia_Gestal insert(Apprpa_Energia_Gestal obj) {
		obj.setIdEnergia(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Apprpa_Energia_Gestal pelaData(Cad_Energia_Gestal id, String Data1) {
		Data1 = Data1+" 00:00:00";
		String Data2 = Data1+" 23:59:59";
		return repo.pelaData(id, Data1,Data2);
	}
	
	public Apprpa_Energia_Gestal update(Apprpa_Energia_Gestal obj) {
		find(obj.getIdEnergia());
		return repo.save(obj);
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
	private AppRpaGestalLogRepository repo2;
	public Apprpa_Energia_GestalLog insertLog(Apprpa_Energia_GestalLog obj) {
		obj.setIdLog(null);  //Utilizado em Entidade Com auto incremento
		obj = repo2.save(obj);
		return obj;
	}
	
	
	
}
