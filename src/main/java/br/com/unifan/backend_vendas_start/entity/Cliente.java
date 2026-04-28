package br.com.unifan.backend_vendas_start.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PrePersist
    public void prePersist() {
        if (this.uuid == null)
            this.uuid = UUID.randomUUID();
    }
    private UUID uuid;
    private String nome;
    private String email;
    private String cep;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas = new ArrayList<>();
}
