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

import com.prolagos.sispcbackend.domain.Cad_Ind_Graficos;
import com.prolagos.sispcbackend.domain.Cad_Ind_Graficos;
import com.prolagos.sispcbackend.repositories.CadindicadoresGraficosRepository;
import com.prolagos.sispcbackend.repositories.CadindicadoresRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class CadindicadoresGraficosService {

	@Autowired
	private CadindicadoresGraficosRepository repo;

	public List<Cad_Ind_Graficos> findAll() {
		return repo.findAll();
	}
	


	public Page<Cad_Ind_Graficos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Ind_Graficos find(Integer id) {
		Optional<Cad_Ind_Graficos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Ind_Graficos.class.getName(), null));
	}
	
	public Cad_Ind_Graficos insert(Cad_Ind_Graficos obj) {
		obj.setCampoDoGraficoId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Ind_Graficos update(Cad_Ind_Graficos obj) {
		find(obj.getCampoDoGraficoId());
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
	
}
