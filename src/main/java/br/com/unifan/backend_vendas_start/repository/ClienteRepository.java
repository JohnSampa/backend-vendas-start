package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
