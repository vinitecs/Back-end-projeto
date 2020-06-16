package br.com.vini.projetointegrador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.dominio.Estado;
import br.com.vini.projetointegrador.repository.EstadoRepository;
@Service
public class EstadoService {

	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll(){
		return repo.findAllByOrderByNome();
	}
}
