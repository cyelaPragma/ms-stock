package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockEntity {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "id_product")
    private long productId;

    @Column(name = "precio_venta")
    private double sellPrice;

    @Column(name = "cantidad")
    private int amount;
}




