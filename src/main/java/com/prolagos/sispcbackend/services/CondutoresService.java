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
import com.prolagos.sispcbackend.domain.Cad_Transporte_CondutoresTicketLog;
import com.prolagos.sispcbackend.repositories.CondutoresRepository;
import com.prolagos.sispcbackend.repositories.GerenciasRepository;
import com.prolagos.sispcbackend.repositories.VeiculosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

import lombok.Getter;
import lombok.Setter;

@Service
public class CondutoresService {
	
	@Autowired
    private CondutoresRepository repo;
    
    public List<Cad_Transporte_CondutoresTicketLog> findAll() {
        return (List<Cad_Transporte_CondutoresTicketLog>)this.repo.findAll();
    }
    
    public Page<Cad_Transporte_CondutoresTicketLog> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of((int)page, (int)linesPerPage, Sort.Direction.valueOf(direction), new String[] { orderBy });
        return (Page<Cad_Transporte_CondutoresTicketLog>)this.repo.findAll((Pageable)pageRequest);
    }
    
    public Cad_Transporte_CondutoresTicketLog find(Integer id) {
		Optional<Cad_Transporte_CondutoresTicketLog> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_Transporte_CondutoresTicketLog.class.getName(), null));
	}
    

    

    
    public Cad_Transporte_CondutoresTicketLog insert(final Cad_Transporte_CondutoresTicketLog obj) {
    	Cad_Transporte_CondutoresTicketLog veiculo = new Cad_Transporte_CondutoresTicketLog(
    			obj.getMatricula(), 
    			obj.getNome(),
    			obj.getGestor(),
    			obj.getVencimentoHabilitacao(),
    			obj.getNascimento(),
    			obj.getRG(),
    			obj.getCPF(),
    			obj.getSituacao(),
    			obj.getDataCadastro()
    			);
        return repo.save(veiculo);
    }
    
    public Cad_Transporte_CondutoresTicketLog update(final Cad_Transporte_CondutoresTicketLog obj) {
        this.find(obj.getMatricula());
        return (Cad_Transporte_CondutoresTicketLog)this.repo.save(obj);
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
