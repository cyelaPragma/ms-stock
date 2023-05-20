package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "carrito", uniqueConstraints = @UniqueConstraint(name = "uk_id_user", columnNames = "user_id"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long idUser;

    @Column(name = "ultima_actualizacion")
    @UpdateTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_carrito")
    private Set<ShoppingCartProductEntity> products;
}