package com.prolagos.sispcbackend.services;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.mail.EmailException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Appweb_Juridico_pagamentos;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;
import com.prolagos.sispcbackend.repositories.AgendamentosRepository;
import com.prolagos.sispcbackend.repositories.JuridicoPagamentosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;
import com.prolagos.sispcbackend.services.util.AgendamentoEmails;

@Service
public class JuridicoPagamentoService {
	
	@Autowired
    private JuridicoPagamentosRepository repo;
    
    public List<Appweb_Juridico_pagamentos> findAll() {
        return (List<Appweb_Juridico_pagamentos>)this.repo.findAll();
    }

    public List<Appweb_Juridico_pagamentos> SemAprovar() {
        return (List<Appweb_Juridico_pagamentos>)this.repo.enviarParaAprovacao();
    }

    public List<Appweb_Juridico_pagamentos> EmAprovacao() {
        return (List<Appweb_Juridico_pagamentos>)this.repo.emAprovacao();
    }
    

    public List<Appweb_Juridico_pagamentos> Aprovacao(Integer nivel, List<String> centroDeCusto) {
    	List<Appweb_Juridico_pagamentos> lista= null;
		if(nivel == 1) {
    		lista = this.repo.emAprovacaoNivel1();
    	}else if(nivel == 2){
    		lista = this.repo.emAprovacaoNivel2(centroDeCusto);
    	}else if(nivel == 3){
    		lista = this.repo.emAprovacaoNivel3();
    	}
        return lista;
    }
    
    public Page<Appweb_Juridico_pagamentos> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of((int)page, (int)linesPerPage, Sort.Direction.valueOf(direction), new String[] { orderBy });
        return (Page<Appweb_Juridico_pagamentos>)this.repo.findAll((Pageable)pageRequest);
    }
    
    public Appweb_Juridico_pagamentos find(Integer id) {
		Optional<Appweb_Juridico_pagamentos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Appweb_Juridico_pagamentos.class.getName(), null));
	}

    
    public Appweb_Juridico_pagamentos insert(final Appweb_Juridico_pagamentos obj) throws EmailException, UnsupportedEncodingException {
    	
    	Appweb_Juridico_pagamentos veiculo = new Appweb_Juridico_pagamentos(
    			obj.getIdPagamento(),
    			obj.getDataCadastro(),
    			obj.getUsuarioInsert(),
    			obj.getEmpresa(),
    			obj.getAutor(),
    			obj.getProcesso(),
    			obj.getNatureza(),
    			obj.getDataProgramada(),
    			obj.getValor(),
    			obj.getEscritorio(),
    			obj.getContaContabil(),
    			obj.getCentroDeCusto(),
    			obj.getFornecedor(),
    			obj.getSentenca(),
    			obj.getMotivoPagamento(),
    			obj.isFalhaConcess(),
    			obj.getEnviadoParaAprovacao(),
    			obj.getAprovador1(),
    			obj.getAprovacao1(),
    			obj.getAprovador2(),
    			obj.getAprovacao2(),
    			obj.getAprovador3(),
    			obj.getAprovacao3()
		);
        return repo.save(veiculo);
    }
    
    public Appweb_Juridico_pagamentos update(final Appweb_Juridico_pagamentos obj) throws UnsupportedEncodingException, EmailException {
        this.find(obj.getIdPagamento());
    	return (Appweb_Juridico_pagamentos)this.repo.save(obj);
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
