package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity;

import com.acelera.ti.stock.domain.model.model.stock.ProvisioningProduct;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "provisionings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProvisioningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "ID_PROVEEDOR")
    private Long providerId;
    @Column(name = "FECHA")
    private LocalDate sourcingDate;
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProvisioningProduct> products;
}