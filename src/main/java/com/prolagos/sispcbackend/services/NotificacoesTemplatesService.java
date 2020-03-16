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

import com.prolagos.sispcbackend.domain.Appweb_Notificacoes_Templates;
import com.prolagos.sispcbackend.repositories.NotificacoesTemplatesRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class NotificacoesTemplatesService {
	
	@Autowired
    private NotificacoesTemplatesRepository repo;
    
    public List<Appweb_Notificacoes_Templates> findAll() {
        return (List<Appweb_Notificacoes_Templates>)this.repo.findAll();
    }
    
    public Page<Appweb_Notificacoes_Templates> findPage(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        final PageRequest pageRequest = PageRequest.of((int)page, (int)linesPerPage, Sort.Direction.valueOf(direction), new String[] { orderBy });
        return (Page<Appweb_Notificacoes_Templates>)this.repo.findAll((Pageable)pageRequest);
    }
    
    public Appweb_Notificacoes_Templates find(Integer id) {
		Optional<Appweb_Notificacoes_Templates> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Appweb_Notificacoes_Templates.class.getName(), null));
	}
    

    
    public Appweb_Notificacoes_Templates insert(final Appweb_Notificacoes_Templates obj){
    	Appweb_Notificacoes_Templates veiculo = new Appweb_Notificacoes_Templates(
			obj.getId(), 
			obj.getNomeDoTemplate(), 
			obj.getAssunto(), 
			obj.getTexto()
		 );
        return repo.save(veiculo);
    }
    
    public Appweb_Notificacoes_Templates update(final Appweb_Notificacoes_Templates obj){
        this.find(obj.getId());
    	return (Appweb_Notificacoes_Templates)this.repo.save(obj);
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
