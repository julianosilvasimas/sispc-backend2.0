package com.prolagos.sispcbackend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import com.prolagos.sispcbackend.repositories.IndicadoresRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class IndicadoresService {
	
	@Autowired
	private IndicadoresRepository repo;
	
	//private ListaIndicadoresDAO dao;

	public List<AppWeb_Ind_ExeIndicadores> findAll() {
		return repo.findAll();
	}
	
	
	public Page<AppWeb_Ind_ExeIndicadores> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public AppWeb_Ind_ExeIndicadores find(Integer id) {
		Optional<AppWeb_Ind_ExeIndicadores> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		AppWeb_Ind_ExeIndicadores.class.getName(), null));
	}
	
	public AppWeb_Ind_ExeIndicadores findDiario(final List<Cad_Ind_Indicadores> indicador, final LocalDate data) {
        final Optional<AppWeb_Ind_ExeIndicadores> obj = this.repo.findIndDiario(indicador, data.plusDays(1));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + indicador + ", Tipo: " +
        		AppWeb_Ind_ExeIndicadores.class.getName(), null));
    }

	public AppWeb_Ind_ExeIndicadores findByDataInd(final Integer indicadorId, final LocalDate data) {
        final AppWeb_Ind_ExeIndicadores obj = this.repo.findByDataInd(indicadorId, data.plusDays(1));
        return obj;
    }
	

	public List<AppWeb_Ind_ExeIndicadores>  findDiarioPorMes(final List<Cad_Ind_Indicadores> indicador, final LocalDate data) {
		return this.repo.findIndDiariosPorMes(indicador, data.plusDays(1));
        
    }

	public List<AppWeb_Ind_ExeIndicadores>  findDiarioPorRange(final List<Cad_Ind_Indicadores> indicador, final LocalDate data1, final LocalDate data2) {
		return this.repo.findIndDiariosPorRange(indicador, data1.plusDays(1), data2.plusDays(1));
        
    }
	
	public AppWeb_Ind_ExeIndicadores insert(AppWeb_Ind_ExeIndicadores obj) {
		obj.setExeindicadorId(null);  //Utilizado em Entidade Com auto incremento
		obj.setDatareferencia(obj.getDatareferencia().plusDays(1));
		obj.setDataindicador(obj.getDataindicador().plusDays(1));
		obj = repo.save(obj);
		return obj;
	}
	
	public AppWeb_Ind_ExeIndicadores update(AppWeb_Ind_ExeIndicadores obj) {
		find(obj.getExeindicadorId());
		obj.setDatareferencia(obj.getDatareferencia().plusDays(1));
		obj.setDataindicador(obj.getDataindicador().plusDays(1));
		return repo.save(obj);
	}
	public AppWeb_Ind_ExeIndicadores update2(AppWeb_Ind_ExeIndicadores obj) {
		obj.setDatareferencia(obj.getDatareferencia().plusDays(1));
		obj.setDataindicador(obj.getDataindicador().plusDays(1));
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
