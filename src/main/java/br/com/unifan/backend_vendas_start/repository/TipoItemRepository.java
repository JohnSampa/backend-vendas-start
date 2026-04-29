package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TipoItemRepository extends JpaRepository<TipoItem,Long> {

    Optional<TipoItem> findByUuid(UUID id);
}
