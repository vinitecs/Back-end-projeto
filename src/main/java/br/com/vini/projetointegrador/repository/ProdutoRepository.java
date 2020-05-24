package br.com.vini.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vini.projetointegrador.dominio.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
