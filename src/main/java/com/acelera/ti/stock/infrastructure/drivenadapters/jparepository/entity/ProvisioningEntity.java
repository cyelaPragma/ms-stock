package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

//import com.acelera.ti.stock.domain.model.model.stock.ProvisioningProduct;
//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//@Entity
//@Table(name = "provisionings")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
public class ProvisioningEntity {
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "proveedor_id")
    private Long providerId;
    @Column(name = "fecha_provisionamiento")
    @UpdateTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate sourcingDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_stock")
    private Set<ProvisioningProductEntity> products;

    */
}