package com.prolagos.sispcbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Preenchimentos;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_PreenchimentosLog;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoLogRepository;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoRepository;
import com.prolagos.sispcbackend.repositories.ApprpaEsgotoUnidadesRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;

@Service
public class ApprpaEsgotoService {

	@Autowired
	private ApprpaEsgotoRepository repo;

	
	public List<Apprpa_Esgoto_Preenchimentos> findAll() {
		return repo.findAll();
	}

	
	public Page<Apprpa_Esgoto_Preenchimentos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Apprpa_Esgoto_Preenchimentos find(Integer id) {
		Optional<Apprpa_Esgoto_Preenchimentos> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " +
		Apprpa_Esgoto_Preenchimentos.class.getName(), null));
	}
	
	public Apprpa_Esgoto_Preenchimentos insert(Apprpa_Esgoto_Preenchimentos obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo.save(obj);
		return obj;
	}

	public Apprpa_Esgoto_Preenchimentos update(Apprpa_Esgoto_Preenchimentos obj, Integer id) {
		obj.setId(id);
		obj = repo.save(obj);
		return obj;
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
	
	
	@Autowired
	private ApprpaEsgotoLogRepository repo2;
	public Apprpa_Esgoto_PreenchimentosLog insertLog(Apprpa_Esgoto_PreenchimentosLog obj) {
		obj.setId(null);  //Utilizado em Entidade Com auto incremento
		obj = repo2.save(obj);
		return obj;
	}

	public List<Apprpa_Esgoto_Preenchimentos> findUnidadeRef(String unidade, String de, String ate, Integer clas) {
		de = de + " 00:00:00";
		ate = ate + " 23:59:59";
		return repo.consultaPorData(unidade,de, ate,getClassifica(clas));
	}

	

	@Autowired
	private ApprpaEsgotoUnidadesRepository repo3;
	public List<Apprpa_Esgoto_Unidades> findUnidades() {
		return repo3.findAll();
	}
	
	public Object[] getClassifica(Integer classificacao) {
		ArrayList<String> classif = new ArrayList<>();
		classif.clear();
		
		
		//PRODUTOS QUÍMICOS
		if(classificacao==1) {
			classif.add("pq_cal");
			classif.add("pq_cal_mic");
			classif.add("pq_polimero_em");
			classif.add("pq_sulfato");
			classif.add("pq_pac");
			classif.add("pq_pastilha_cloro");
			classif.add("pq_anti_espumante");
			classif.add("pq_polimero_po");
			
		//PRODUTOS QUÍMICOS USO
		}else if(classificacao==2) {
			classif.add("pq_uso_cal");
			classif.add("pq_uso_cal_mic");
			classif.add("pq_uso_polimero_em");
			classif.add("pq_uso_sulfato");
			classif.add("pq_uso_pac");
			classif.add("pq_uso_pastilha_cloro");
			classif.add("pq_uso_anti_espumante");
			classif.add("pq_uso_polimero_po");

			
		//CONTROLE DIÁRIO
		}else if(classificacao==3) {
			classif.add("cd_ph_entrada");
			classif.add("cd_ph_saida");
			classif.add("cd_ph_minimo");
			classif.add("cd_ph_maximo");
			classif.add("cd_vazao");
			classif.add("cd_pluviometrico");
			classif.add("cd_solidos_sed_entrada");
			classif.add("cd_solidos_sed_saida");
			classif.add("cd_solidos_sed_leg");
			classif.add("cd_aeracao_inicio");
			classif.add("cd_aeracao_final");
			classif.add("cd_aeracao_1");
			classif.add("cd_aeracao_2");
			classif.add("cd_lodo_1");
			classif.add("cd_dec_saida_1");
			classif.add("cd_lodo_2");
			classif.add("cd_dec_saida_2");
			classif.add("cd_lodo_3");
			classif.add("cd_dec_saida_3");
			classif.add("cd_nitrogenio_total");
			classif.add("cd_nitrogenio_total_leg");
			classif.add("cd_od_tanque");
			classif.add("cd_od_tanque_2");
			classif.add("cd_od_tanque_3");
			classif.add("cd_od_tanque_4");
			classif.add("cd_od_tanque_5");
			classif.add("cd_od_tanque_6");
			classif.add("cd_od_tanque_7");
			classif.add("cd_od_tanque_8");
			classif.add("cd_od_tanque_desejavel");

			
		//QUALIDADE DO EFLUENTE
		}else if(classificacao==4) {
			classif.add("qe_dbo_entrada");
			classif.add("qe_dbo_saida");
			classif.add("qe_dbo_leg");
			classif.add("qe_eficiencia_remocao");
			classif.add("qe_fosforo_entrada");
			classif.add("qe_fosforo_saida");
			classif.add("qe_fosforo_leg");
			classif.add("qe_eficiencia_remocao_p");
			classif.add("qe_nitrogenio_entrada");
			classif.add("qe_nitrogenio_saida");
			classif.add("qe_nitrogenio_leg");
			classif.add("qe_eficiencia_remocao_n");

			
		//RESÍDUOS SÓLIDOS
		}else if(classificacao==5) {
			classif.add("rs_n_cacanbas_ret");
			classif.add("rs_lodo");
			classif.add("rs_lixo");
			classif.add("rs_areia");

			
		//CONSUMO GERAL
		}else if(classificacao==6) {
			classif.add("ex_hidrometro");
			classif.add("ex_nivel_diesel");

			
		//E
		}else if(classificacao==7) {
			classif.add("energia_consumo");
			classif.add("energia_consumo_ponta");
			classif.add("energia_meta");

		}
		Object[] classif2 = classif.toArray();
		return classif2;
	}
	
	
	

}
