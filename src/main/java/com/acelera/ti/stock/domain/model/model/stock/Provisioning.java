package com.acelera.ti.stock.domain.model.model.stock;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Provisioning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ID_PROVIDER")
    private Long providerId;
    @Column(name = "SOURCING-DATE")
    private LocalDate sourcingDate;
    private List<ProvisioningProduct> products;
}
