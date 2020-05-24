package br.com.vini.projetointegrador.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.dominio.Categoria;
import br.com.vini.projetointegrador.repository.CategoriaRepository;
import br.com.vini.projetointegrador.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository repo;

	

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
