package com.prolagos.sispcbackend.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prolagos.sispcbackend.domain.AppWeb_ProcessAdm_DelibFraudes;
import com.prolagos.sispcbackend.dto.InputDeliberacaoDTO;
import com.prolagos.sispcbackend.repositories.GestaoDeliberacaoRepository;
import com.prolagos.sispcbackend.services.exceptions.ObjectNotFoundException;

@Service
public class GestaoDeliberacaoService {


	@Autowired
	private GestaoDeliberacaoRepository repo;
	private Integer proxDelib;

	public AppWeb_ProcessAdm_DelibFraudes fromDTO(InputDeliberacaoDTO objDTO) {
		DateFormat ano = new SimpleDateFormat("yyyy");
		DateFormat mes = new SimpleDateFormat("MM");
		Date date = new Date();
		String anoAtual = ano.format(date);
		String mat = String.format("%06d", objDTO.getNum_ligacao());
		
		//Aqui verifico se a Tabela está vazia ou se o ano atual é maior que o ano do último registro Para voltar a contagem de delib para 1
		if(repo.ultDelib().size() == 0 || Integer.parseInt(anoAtual) > Integer.parseInt(repo.ultDelib().get(0).getProcesso().toString().substring(0, 4))) {
			proxDelib = 1;
		}else{
			proxDelib = repo.ultDelib().get(0).getDeliberacao() + 1;
		}

        String delib = String.format("%06d", proxDelib);

		objDTO.setProcesso(ano.format(date) + mes.format(date) + mat + delib);

		AppWeb_ProcessAdm_DelibFraudes deliberacao = new AppWeb_ProcessAdm_DelibFraudes(objDTO.getProcesso(), proxDelib, objDTO.getIdIrregularidade(), date,
				objDTO.getDataAviso1(), objDTO.getDataAviso2(), objDTO.getDataAviso3(), objDTO.getMesRetroativo(), objDTO.getTitular(), 
				objDTO.getUsuarioPresente(), objDTO.getContrato(), objDTO.getNum_ligacao(), objDTO.getCarta(), objDTO.getCartaProcedente(), objDTO.getRo(),
				objDTO.getNum_termo(), objDTO.getColaborador(), objDTO.getProtocolo(), objDTO.getCartacedoc());

		return repo.save(deliberacao);
	}

	public List<AppWeb_ProcessAdm_DelibFraudes> findAll() {
		return repo.findAll();
	}
	
	public AppWeb_ProcessAdm_DelibFraudes find(String processo) {
		Optional<AppWeb_ProcessAdm_DelibFraudes> obj = repo.findById(processo);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + processo + ", Tipo: " + AppWeb_ProcessAdm_DelibFraudes.class.getName(), null));
	}
	
	
	public List<AppWeb_ProcessAdm_DelibFraudes> findIrreg(Integer idIrreg) {
		List<AppWeb_ProcessAdm_DelibFraudes> obj = repo.findIrreg(idIrreg);
		return obj;
	}
	
}
