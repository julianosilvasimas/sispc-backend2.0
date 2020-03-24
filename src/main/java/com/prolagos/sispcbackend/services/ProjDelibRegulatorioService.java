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

import com.prolagos.sispcbackend.domain.Cad_Projetos_DeliberacoesRegulatorios;
import com.prolagos.sispcbackend.repositories.ProjDelibRegulatorioRepository;
import com.prolagos.sispcbackend.repositories.ProjRegulatorioRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjDelibRegulatorioService {
	
	@Autowired
	private ProjRegulatorioRepository repo2;
	
	@Autowired
	private ProjDelibRegulatorioRepository repo;
	
	public List<Cad_Projetos_DeliberacoesRegulatorios> findAll() {
		return repo.findAll();
	}
	
	public List<Cad_Projetos_DeliberacoesRegulatorios> findByRevisao(Integer revisaoId) {
		return repo.delibPorRev(revisaoId);
	}

	public Page<Cad_Projetos_DeliberacoesRegulatorios> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_DeliberacoesRegulatorios find(Integer id) {
		Optional<Cad_Projetos_DeliberacoesRegulatorios> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_DeliberacoesRegulatorios.class.getName(), null));
	}
	
	public Cad_Projetos_DeliberacoesRegulatorios insert(Cad_Projetos_DeliberacoesRegulatorios obj) {
		obj.setDeliberacaoId(null);  //Utilizado em Entidade Com auto incremento
		obj.setRegulatorio(repo2.findById(obj.getRegulatorio().getRegulatorioId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!", null)));
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_DeliberacoesRegulatorios update(Cad_Projetos_DeliberacoesRegulatorios obj) {
		find(obj.getDeliberacaoId());
		if(obj.getEnvio() != null ){
			obj.setEnvio(obj.getEnvio() .plusDays(1)) ;	
		}
		if(obj.getRetorno() != null ){
			obj.setRetorno(obj.getRetorno().plusDays(1));	
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
