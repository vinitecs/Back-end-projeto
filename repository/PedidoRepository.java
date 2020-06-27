package br.com.vini.projetointegrador.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.dominio.Pedido;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Transactional(readOnly=true)
	Page <Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
}
