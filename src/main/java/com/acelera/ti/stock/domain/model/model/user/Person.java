package com.acelera.ti.stock.domain.model.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Person {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String address;
    private String dniTypeId;
    private String dniNumber;
    private String personTypeId;
}
