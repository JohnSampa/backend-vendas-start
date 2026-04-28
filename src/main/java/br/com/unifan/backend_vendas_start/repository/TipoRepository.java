package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo,Long> {
}
