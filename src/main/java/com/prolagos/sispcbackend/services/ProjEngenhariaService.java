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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Engenharia;
import com.prolagos.sispcbackend.domain.Cad_Projetos_Regulatorio;
import com.prolagos.sispcbackend.repositories.ProjEngenhariaRepository;
import com.prolagos.sispcbackend.repositories.ProjetosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjEngenhariaService {
	
	@Autowired
	private ProjEngenhariaRepository repo;
	
	@Autowired
	private ProjetosRepository repo2;
	
	public List<Cad_Projetos_Engenharia> findAll() {
		return repo.findAll();
	}
	
	public List<Cad_Projetos_Engenharia> findByProjeto(Integer projetoId) {
		return repo.engPorProjeto(projetoId);
	}

	public Page<Cad_Projetos_Engenharia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_Engenharia find(Integer id) {
		Optional<Cad_Projetos_Engenharia> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_Engenharia.class.getName(), null));
	}
	
	public Cad_Projetos_Engenharia insert(Cad_Projetos_Engenharia obj) {
		obj.setEngenhariaId(null);  //Utilizado em Entidade Com auto incremento
		if(obj.getPrevisto() != null ){
			obj.setPrevisto(obj.getPrevisto() .plusDays(1)) ;	
		}
		if(obj.getReplanejado() != null ){
			obj.setReplanejado(obj.getReplanejado().plusDays(1));	
		}
		if(obj.getRealizado() != null ){
			obj.setRealizado(obj.getRealizado() .plusDays(1)) ;	
		}
		if(obj.getContfisico() != null ){
			obj.setContfisico(obj.getContfisico().plusDays(1));	
		}
		if(obj.getContsistemico() != null ){
			obj.setContsistemico(obj.getContsistemico() .plusDays(1)) ;	
		}
		obj.setProjetoId(repo2.findById(obj.getProjetoId().getProjetoId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!", null)));
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_Engenharia update(Cad_Projetos_Engenharia obj) {
		find(obj.getEngenhariaId());
		if(obj.getPrevisto() != null ){
			obj.setPrevisto(obj.getPrevisto() .plusDays(1)) ;	
		}
		if(obj.getReplanejado() != null ){
			obj.setReplanejado(obj.getReplanejado().plusDays(1));	
		}
		if(obj.getRealizado() != null ){
			obj.setRealizado(obj.getRealizado() .plusDays(1)) ;	
		}
		if(obj.getContfisico() != null ){
			obj.setContfisico(obj.getContfisico().plusDays(1));	
		}
		if(obj.getContsistemico() != null ){
			obj.setContsistemico(obj.getContsistemico() .plusDays(1)) ;	
		}
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Preço que possui relacionamentos");
		}
	}

}
