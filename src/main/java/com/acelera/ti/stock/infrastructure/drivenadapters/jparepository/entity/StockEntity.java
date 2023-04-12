package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StockEntity {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "id_product")
    private long productId;

    @Column(name = "cantidad")
    private double sellPrice;

    @Column(name = "precio_venta")
    private int amount;
}


