package br.com.unifan.backend_vendas_start.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @PrePersist
    public void prePersist() {
        if(this.uuid == null)
            this.uuid = UUID.randomUUID();
    }
    private UUID uuid;

    private LocalDate data;
    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "vendas_items",
            joinColumns = @JoinColumn(name = "id_venda"),
            inverseJoinColumns = @JoinColumn(name = "id_item"))
    private List<Item> items = new ArrayList<>();

    public Double getValorTotal() {
        double total = 0.0;
        for (Item item : items) {
            total = item.getValor().doubleValue();
        }
        return total;
    }
}
