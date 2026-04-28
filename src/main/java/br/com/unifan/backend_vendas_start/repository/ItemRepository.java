package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
