package com.acelera.ti.stock.infrastructure.entrypoints.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/tests")
public class TestController {
    @GetMapping("/v1")
    public ResponseEntity<?> getTest(){
        return new ResponseEntity<>("Test endpoint", HttpStatus.OK);
    }
}
