package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "provisionings-product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProvisioningProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "id_abastecimiento")
    private Long provisioningId;

    @ManyToOne
    @JoinColumn(name="id_stock", nullable = false)
    private Stock stock;

    @Column(name = "cantidad")
    private int amount;

    @Column(name = "precio_entrada")
    private Double providerPrice;
}