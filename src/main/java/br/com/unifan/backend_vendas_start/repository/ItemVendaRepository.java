package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
