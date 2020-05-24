package br.com.vini.projetointegrador.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.dominio.Pedido;
import br.com.vini.projetointegrador.repository.PedidoRepository;
import br.com.vini.projetointegrador.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository repo;

	

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
