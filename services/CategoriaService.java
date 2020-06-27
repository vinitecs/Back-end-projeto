package br.com.vini.projetointegrador.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.DTO.CategoriaDTO;
import br.com.vini.projetointegrador.dominio.Categoria;
import br.com.vini.projetointegrador.repository.CategoriaRepository;
import br.com.vini.projetointegrador.security.UserSS;
import br.com.vini.projetointegrador.services.exception.DataIntegrityException;
import br.com.vini.projetointegrador.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository repo;

	UserSS user = UserService.authenticated();
	
	

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj =  find(obj.getId());
		updadeData(newObj, obj);
		return repo.save(newObj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir cxaategoria que possui produto cadastrado");
		}
	
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	public Page<Categoria> findPage(Integer page, Integer linesPerPage,String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
			
			return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		
		return new Categoria(objDto.getId(), objDto.getNome());
		
	}
	private void updadeData(Categoria newObj,Categoria  obj) {
		newObj.setNome(obj.getNome());
	
	}
}
