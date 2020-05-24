package br.com.vini.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vini.projetointegrador.dominio.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
