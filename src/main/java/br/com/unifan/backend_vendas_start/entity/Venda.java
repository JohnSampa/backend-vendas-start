package br.com.unifan.backend_vendas_start.entity;

import br.com.unifan.backend_vendas_start.entity.enums.VendaStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

import static br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.PENDENTE;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private VendaStatus status = PENDENTE;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> items = new ArrayList<>();

    public void addItem(ItemVenda itemVenda) {
        itemVenda.setVenda(this);
        this.items.add(itemVenda);
    }

    public Double getValorTotal() {
        double total = 0.0;
        for (ItemVenda item : items) {
            total += item.getValorTotal();
        }
        return total;
    }
}
