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
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoIndicadoresRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ApprpaEsgotoIndicadoresService {

	@Autowired
	private ApprpaEsgotoIndicadoresRepository repo;

	
	public List<Apprpa_Esgoto_Indicadores> findAll() {
		return repo.findAll();
	}

	
	public Page<Apprpa_Esgoto_Indicadores> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Esgoto_Indicadores find(Integer id) {
		Optional<Apprpa_Esgoto_Indicadores> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Esgoto_Indicadores.class.getName(), null));
	}
	
	public List<Apprpa_Esgoto_Indicadores> findByUnidade(Integer unidade) {
		return repo.findByUnidade(unidade);
	}


	@Autowired
    private ApprpaEsgotoClassificacaoService service2;
	
	public List<Apprpa_Esgoto_Indicadores> findByClass(Integer id) {
		Apprpa_Esgoto_Classificacoes classificacao = service2.find(id);
		return repo.findByClass(classificacao);
	}
	
	public Apprpa_Esgoto_Indicadores insert(Apprpa_Esgoto_Indicadores obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}

	public Apprpa_Esgoto_Indicadores update(Apprpa_Esgoto_Indicadores obj, Integer id) {
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
