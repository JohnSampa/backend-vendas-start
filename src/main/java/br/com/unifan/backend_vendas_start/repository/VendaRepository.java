package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda,Long> {

    Optional<Venda> findByUuid(UUID uuid);

}
