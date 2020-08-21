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

import com.prolagos.sispcbackend.domain.Cad_Projetos_ComprovacaoArquivos;
import com.prolagos.sispcbackend.repositories.ProjComprovacaoArquivosRepository;
import com.prolagos.sispcbackend.repositories.ProjComprovacaoRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjComprovacaoArquivosService {
	
	@Autowired
	private ProjComprovacaoRepository repo2;
	
	@Autowired
	private ProjComprovacaoArquivosRepository repo;
	
	public List<Cad_Projetos_ComprovacaoArquivos> findAll() {
		return repo.findAll();
	}
	
	public List<Cad_Projetos_ComprovacaoArquivos> findByComprovacao(Integer comprovacaoId) {
		return repo.arquivoPorProjeto(comprovacaoId);
	}

	public Page<Cad_Projetos_ComprovacaoArquivos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_ComprovacaoArquivos find(Integer id) {
		Optional<Cad_Projetos_ComprovacaoArquivos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_ComprovacaoArquivos.class.getName(), null));
	}
	
	public Cad_Projetos_ComprovacaoArquivos insert(Cad_Projetos_ComprovacaoArquivos obj) {
		obj.setComprovacaoarquivoId(null);  //Utilizado em Entidade Com auto incremento
		obj.setComprovacaoId(repo2.findById(obj.getComprovacaoId().getComprovacaoId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!", null)));
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_ComprovacaoArquivos update(Cad_Projetos_ComprovacaoArquivos obj) {
		find(obj.getComprovacaoarquivoId());
		if(obj.getEnvio() != null ){
			obj.setEnvio(obj.getEnvio() .plusDays(1)) ;	
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
