package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Builder
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "ID_PRODUCT")
    private long productId;

    @Column(name = "SALE_PRICE")
    private double sellPrice;

    @Column(name = "AMOUNT_STOCK")
    private int amount;

    // validación de datos para el atributo "amount"
    public void setAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.amount = amount;
    }
}
