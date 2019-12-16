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

import com.prolagos.sispcbackend.domain.Cad_SisPC_Gerencias;
import com.prolagos.sispcbackend.repositories.GerenciasRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class GerenciasService {
	
	@Autowired
    private GerenciasRepository repo;
    
    public List<Cad_SisPC_Gerencias> findAll() {
        return (List<Cad_SisPC_Gerencias>)this.repo.findAll();
    }
    
    public Page<Cad_SisPC_Gerencias> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of((int)page, (int)linesPerPage, Sort.Direction.valueOf(direction), new String[] { orderBy });
        return (Page<Cad_SisPC_Gerencias>)this.repo.findAll((Pageable)pageRequest);
    }
    
    public Cad_SisPC_Gerencias find(Integer id) {
		Optional<Cad_SisPC_Gerencias> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_SisPC_Gerencias.class.getName(), null));
	}
    
    public Cad_SisPC_Gerencias insert(final Cad_SisPC_Gerencias obj) {
        obj.setGerenciaId((Integer)null);
        return obj;
    }
    
    public Cad_SisPC_Gerencias update(final Cad_SisPC_Gerencias obj) {
        this.find(obj.getGerenciaId());
        return (Cad_SisPC_Gerencias)this.repo.save(obj);
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
