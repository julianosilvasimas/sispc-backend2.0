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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Licoesaprendidas;
import com.prolagos.sispcbackend.repositories.ProjLicoesAprendidasRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjLiceosaprendidasService {

	@Autowired
	private ProjLicoesAprendidasRepository repo;
	
	public List<Cad_Projetos_Licoesaprendidas> findAll() {
		return repo.findAll();
	}

	public Page<Cad_Projetos_Licoesaprendidas> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_Projetos_Licoesaprendidas find(Integer id) {
		Optional<Cad_Projetos_Licoesaprendidas> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_Projetos_Licoesaprendidas.class.getName(), null));
	}
	
	public Cad_Projetos_Licoesaprendidas insert(Cad_Projetos_Licoesaprendidas obj) {
		//obj.setComissionamentoId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}
	
	public Cad_Projetos_Licoesaprendidas update(Cad_Projetos_Licoesaprendidas obj) {
		find(obj.getLicoesId());
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
