package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda,Long> {
}
