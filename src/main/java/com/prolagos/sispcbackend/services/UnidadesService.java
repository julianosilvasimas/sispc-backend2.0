package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;
import com.prolagos.sispcbackend.repositories.SupervisoesRepository;
import com.prolagos.sispcbackend.repositories.UnidadesRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class UnidadesService {
	
	@Autowired
    private UnidadesRepository repo;
    
    public List<Cad_SisPC_Unidades> findAll() {
        return (List<Cad_SisPC_Unidades>)this.repo.findAll();
    }
    
  
    public Cad_SisPC_Unidades find(Integer id) {
		Optional<Cad_SisPC_Unidades> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_SisPC_Unidades.class.getName(), null));
	}
    
    public Cad_SisPC_Unidades insert(final Cad_SisPC_Unidades obj) {
        obj.setUnidadeId((Integer)null);
        return obj;
    }
    
    public Cad_SisPC_Unidades update(final Cad_SisPC_Unidades obj) {
        this.find(obj.getUnidadeId());
        return (Cad_SisPC_Unidades)this.repo.save(obj);
    }
    
    public void delete(final Integer id) {
        this.find(id);
        try {
            this.repo.deleteById((id));
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("N\u00e3o \u00e9 poss\u00edvel excluir um Objeto que possui relacionamentos");
        }
    }
	
}
