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

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Classificacoes;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoClassificacoesRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ApprpaEsgotoClassificacaoService {

	@Autowired
	private ApprpaEsgotoClassificacoesRepository repo;

	
	public List<Apprpa_Esgoto_Classificacoes> findAll() {
		return repo.findAll();
	}

	
	public Page<Apprpa_Esgoto_Classificacoes> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Esgoto_Classificacoes find(Integer id) {
		Optional<Apprpa_Esgoto_Classificacoes> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Esgoto_Classificacoes.class.getName(), null));
	}
	
	public Apprpa_Esgoto_Classificacoes insert(Apprpa_Esgoto_Classificacoes obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}

	public Apprpa_Esgoto_Classificacoes update(Apprpa_Esgoto_Classificacoes obj, Integer id) {
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

}
