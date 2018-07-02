package br.com.dbc.projeto.repository;

import br.com.dbc.projeto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
