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

import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Transporte_Veiculos;
import com.prolagos.sispcbackend.repositories.AgendamentosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

import lombok.Getter;
import lombok.Setter;

@Service
public class AgendamentosService {
	
	@Autowired
    private AgendamentosRepository repo;
    
    public List<Appweb_Transporte_Agendamentos> findAll() {
        return (List<Appweb_Transporte_Agendamentos>)this.repo.findAll();
    }
    
    public Page<Appweb_Transporte_Agendamentos> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of((int)page, (int)linesPerPage, Sort.Direction.valueOf(direction), new String[] { orderBy });
        return (Page<Appweb_Transporte_Agendamentos>)this.repo.findAll((Pageable)pageRequest);
    }
    
    public Appweb_Transporte_Agendamentos find(Integer id) {
		Optional<Appweb_Transporte_Agendamentos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Appweb_Transporte_Agendamentos.class.getName(), null));
	}
    
    public List<Appweb_Transporte_Agendamentos> Disponiveis(String de, String ate) {
		List<Appweb_Transporte_Agendamentos> obj = repo.Disponiveis(de, ate);
		return (List<Appweb_Transporte_Agendamentos>)this.repo.Disponiveis(de, ate);
	}
  
    
    public List<Appweb_Transporte_Agendamentos> paraAprovar() {
		List<Appweb_Transporte_Agendamentos> obj = repo.paraAprovar();
		return (List<Appweb_Transporte_Agendamentos>)this.repo.paraAprovar();
	}

    public List<Appweb_Transporte_Agendamentos> Aprovados() {
		List<Appweb_Transporte_Agendamentos> obj = repo.Aprovados();
		return (List<Appweb_Transporte_Agendamentos>)this.repo.Aprovados();
	}
    
    public Appweb_Transporte_Agendamentos insert(final Appweb_Transporte_Agendamentos obj) {
    	Appweb_Transporte_Agendamentos veiculo = new Appweb_Transporte_Agendamentos(
			obj.getAgendamentoId(),
			obj.getDataAgendamento(),
			obj.getAgendadode(),
			obj.getAgendadoate(),
			obj.getSolicitante(),
			obj.getCondutor(),
			obj.getQtdPessoas(),
			obj.getTipoVeiculoSolicitado(),
			obj.getDestino(),
			obj.getPlaca(),
			obj.getTipoVeiculoDisponibilizado(),
			obj.getAprovador(),
			obj.getAprovacao(),
			obj.getJustificativa()
		);
        return repo.save(veiculo);
    }
    
    public Appweb_Transporte_Agendamentos update(final Appweb_Transporte_Agendamentos obj) {
        this.find(obj.getAgendamentoId());
        return (Appweb_Transporte_Agendamentos)this.repo.save(obj);
    }
    
    public void delete(final Integer id) {
        this.find(id);
        try {
            this.repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("N\u00e3o \u00e9 poss\u00edvel excluir um Objeto que possui relacionamentos");
        }
    }
	
}
