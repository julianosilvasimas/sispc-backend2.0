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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Regulatorio;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Projetos;
import com.prolagos.sispcbackend.repositories.ProjRegulatorioRepository;
import com.prolagos.sispcbackend.repositories.ProjetosRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ProjetosService {
	
	@Autowired
	private ProjetosRepository repo;
	
	@Autowired
	private ProjRegulatorioRepository regrepo;

	public List<Cad_SisPC_Projetos> findAll() {
		return repo.findAll();
	}

	public Page<Cad_SisPC_Projetos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_SisPC_Projetos find(Integer id) {
		Optional<Cad_SisPC_Projetos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Cad_SisPC_Projetos.class.getName(), null));
	}
	
	
	public Cad_SisPC_Projetos findRegulatorios(Integer id) {
		final List<Cad_Projetos_Regulatorio> regula = regrepo.findAll();
		Cad_SisPC_Projetos obj = repo.findRegulatorios(regula, id);
		System.out.println(obj);
		return obj;
	}
	//obj.getPagamento().setPedido(obj);
	public Cad_SisPC_Projetos insert(Cad_SisPC_Projetos obj) {
		obj.setProjetoId(null);  //Utilizado em Entidade Com auto incremento
		obj.getFinanceiro().setProjeto(obj);
		obj.getContratacao().setProjeto(obj);
		obj.getObra().setProjeto(obj);
		obj.getComissionamento().setProjeto(obj);
		obj.getComprovacao().setProjeto(obj);
		obj.getLicoes().setProjeto(obj);
		obj.getSesuite().setProjeto(obj);
		
		obj.getSesuite().getBeneficios().setSesuite(obj.getSesuite());
		obj.getSesuite().getDirecionamento().setSesuite(obj.getSesuite());
		obj.getSesuite().getLicenca().setSesuite(obj.getSesuite());
		obj.getSesuite().getRiscoscontratual().setSesuite(obj.getSesuite());
		obj.getSesuite().getRiscosoperacionais().setSesuite(obj.getSesuite());
		
		obj = repo.save(obj);
		
		return obj;
	}
	
	public Cad_SisPC_Projetos update(Cad_SisPC_Projetos obj) {
		find(obj.getProjetoId());
		/*obj.setInicioprevisto(obj.getInicioprevisto().plusDays(1));
		obj.setInicioreplanejado(obj.getInicioreplanejado().plusDays(1));
		obj.setIniciorealizado(obj.getIniciorealizado().plusDays(1));
		obj.setTerminoprevisto(obj.getTerminoprevisto().plusDays(1));
		obj.setTerminoreplanejado(obj.getTerminoreplanejado().plusDays(1));
		obj.setTerminorealizado(obj.getTerminorealizado().plusDays(1));*/
		
		if(obj.getInicioprevisto() != null ){
			obj.setInicioprevisto(obj.getInicioprevisto().plusDays(1)) ;	
		}
		if(obj.getTerminoprevisto() != null ){
			obj.setTerminoprevisto(obj.getTerminoprevisto().plusDays(1));	
		}
		if(obj.getInicioreplanejado() != null ){
			obj.setInicioreplanejado(obj.getInicioreplanejado().plusDays(1)) ;	
		}
		if(obj.getTerminoreplanejado() != null ){
			obj.setTerminoreplanejado(obj.getTerminoreplanejado().plusDays(1));	
		}
		if(obj.getIniciorealizado() != null ){
			obj.setIniciorealizado(obj.getIniciorealizado().plusDays(1)) ;	
		}
		if(obj.getTerminorealizado() != null ){
			obj.setTerminorealizado(obj.getTerminorealizado().plusDays(1));	
		}
		
		
		obj.getFinanceiro().setProjeto(obj);
		obj.getContratacao().setProjeto(obj);
		obj.getObra().setProjeto(obj);
		obj.getComissionamento().setProjeto(obj);
		obj.getComprovacao().setProjeto(obj);
		obj.getLicoes().setProjeto(obj);
		obj.getSesuite().setProjeto(obj);
		
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
