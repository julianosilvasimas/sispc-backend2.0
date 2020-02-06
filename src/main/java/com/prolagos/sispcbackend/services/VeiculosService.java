package com.prolagos.sispcbackend.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.AppWeb_ProcessAdm_DelibFraudes;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Transporte_Veiculos;
import com.prolagos.sispcbackend.domain.Cad_Transporte_Veiculos;
import com.prolagos.sispcbackend.repositories.GerenciasRepository;
import com.prolagos.sispcbackend.repositories.VeiculosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

import lombok.Getter;
import lombok.Setter;

@Service
public class VeiculosService {
	
	@Autowired
    private VeiculosRepository repo;
    
    public List<Cad_Transporte_Veiculos> findAll() {
        return (List<Cad_Transporte_Veiculos>)this.repo.findAll();
    }
    
    public Page<Cad_Transporte_Veiculos> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of((int)page, (int)linesPerPage, Sort.Direction.valueOf(direction), new String[] { orderBy });
        return (Page<Cad_Transporte_Veiculos>)this.repo.findAll((Pageable)pageRequest);
    }
    
    public Cad_Transporte_Veiculos find(Integer id) {
		Optional<Cad_Transporte_Veiculos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_Transporte_Veiculos.class.getName(), null));
	}
    

    

    
    public Cad_Transporte_Veiculos insert(final Cad_Transporte_Veiculos obj) {
    	Cad_Transporte_Veiculos veiculo = new Cad_Transporte_Veiculos(
    			obj.getVeiculoId(), 
    			obj.getDatCad(),
    			obj.getPlaca(),
    			obj.getChassi(),
    			obj.getModelo(),
    			obj.getCapacidadem3(),
    			obj.getResponsavel(),
    			obj.getTipoVeiculo(),
    			obj.getLocadora(),
    			obj.getGerencia(),
    			obj.getSupervisao(),
    			obj.isGPS(),
    			obj.isOficina(),
    			obj.isPool(),
    			obj.isDevolvido());
        return repo.save(veiculo);
    }
    
    public Cad_Transporte_Veiculos update(final Cad_Transporte_Veiculos obj) {
        this.find(obj.getVeiculoId());
        return (Cad_Transporte_Veiculos)this.repo.save(obj);
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
