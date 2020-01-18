package com.prolagos.sispcbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;
import com.prolagos.sispcbackend.dto.UsuarioDTO;
import com.prolagos.sispcbackend.dto.UsuarioNewDTO;
import com.prolagos.sispcbackend.repositories.UsuarioRepository;
import com.prolagos.sispcbackend.services.exceptions.DataIntegrityException;
import com.prolagos.sispcbackend.services.exceptions.ObjectNotFoundException;

@Service
public class UsuariosService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cad_SisPC_Usuarios find(Integer id) {
		Optional<Cad_SisPC_Usuarios> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cad_SisPC_Usuarios.class.getName()));
	}
	
	public Cad_SisPC_Usuarios findByEmail(String email) {
        final Cad_SisPC_Usuarios obj = this.repo.findByEmail(email);
        return obj;
    }
	
	@Transactional
	public Cad_SisPC_Usuarios insert(Cad_SisPC_Usuarios obj) {
		obj.setUsuarioId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	
	public Cad_SisPC_Usuarios update(Cad_SisPC_Usuarios obj) {
		Cad_SisPC_Usuarios newObj = find(obj.getUsuarioId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Cad_SisPC_Usuarios> findAll() {
		return repo.findAll();
	}
	
	public Page<Cad_SisPC_Usuarios> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cad_SisPC_Usuarios fromDTO(UsuarioDTO objDto) {
		return new Cad_SisPC_Usuarios(objDto.getUsuarioId(), objDto.getNome(), objDto.getEmail(), objDto.getLogin(),
				null, objDto.getAtivo(), null);
	}
	
	public Cad_SisPC_Usuarios fromDTO(UsuarioNewDTO objDto) {
		Cad_SisPC_Usuarios usu = new Cad_SisPC_Usuarios(null, objDto.getNome(), objDto.getEmail(), objDto.getLogin(),
				pe.encode(objDto.getSenha()), objDto.getAtivo(), null);
		
		return usu;
	}
	
	public Cad_SisPC_Usuarios updateSenha(Cad_SisPC_Usuarios obj) {
		Cad_SisPC_Usuarios newObj = find(obj.getUsuarioId());
		updateDatas(newObj, obj);
		return repo.save(newObj);
	}
	
	public Cad_SisPC_Usuarios updateFromDTO(UsuarioDTO objDto) {
		return new Cad_SisPC_Usuarios( objDto.getUsuarioId(), objDto.getNome(),objDto.getEmail(), objDto.getLogin(),
				pe.encode(objDto.getSenha()), objDto.getAtivo(),null);
	}

	private void updateDatas(Cad_SisPC_Usuarios newObj, Cad_SisPC_Usuarios obj) {
		newObj.setSenha(obj.getSenha());
	}
	
	private void updateData(Cad_SisPC_Usuarios newObj, Cad_SisPC_Usuarios obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
