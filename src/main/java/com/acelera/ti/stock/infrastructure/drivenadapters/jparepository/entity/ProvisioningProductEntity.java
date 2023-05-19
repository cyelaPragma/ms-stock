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
    @Column(name = "ID")
    private long id;
    @Column(name = "ID_ABASTECIMIENTO")
    private Long provisioningId;

    @ManyToOne
    @JoinColumn(name="ID_STOCK", nullable = false)
    private Stock stock;

    @Column(name = "CANTIDAD")
    private int amount;

    @Column(name = "PRECIO_ENTRADA")
    private Double providerPrice;
}