package com.acelera.ti.stock.mock.user;

import com.acelera.ti.stock.domain.model.model.user.Person;

public class PersonMocks {
    public static Person getPerson(Long id) {
        return Person.builder()
                .id(id)
                .name("nombre " + id)
                .surname("apellido " + id)
                .mail("correo" + id + "@correo.com")
                .phone("3111231234")
                .address("direccion " + id)
                .dniTypeId("cedula")
                .dniNumber("123" + id)
                .personTypeId("natural")
                .build();
    }
}
