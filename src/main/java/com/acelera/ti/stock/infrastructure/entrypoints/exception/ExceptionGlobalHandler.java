package com.acelera.ti.stock.infrastructure.entrypoints.exception;

import com.acelera.ti.stock.domain.model.exceptions.NotExistShoppingCartProductPageException;
import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.badrequest.BadParamsException;
import com.acelera.ti.stock.domain.model.exceptions.badrequest.BadStockSellPriceException;
import com.acelera.ti.stock.domain.model.exceptions.conflict.ApiConflictException;
import com.acelera.ti.stock.domain.model.exceptions.notfound.ApiNotFoundException;
import com.acelera.ti.stock.infrastructure.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> runtimeException(RuntimeException e) {
        ResponseDTO response = new ResponseDTO(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> exception(Exception e) {
        ResponseDTO response = new ResponseDTO(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<ResponseDTO> notFoundException(ApiNotFoundException e){
        ResponseDTO response = new ResponseDTO(null, HttpStatus.NOT_FOUND.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiConflictException.class)
    public ResponseEntity<ResponseDTO> conflictException(ApiConflictException e){
        ResponseDTO response = new ResponseDTO(null, HttpStatus.CONFLICT.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BadParamsException.class)
    public ResponseEntity<ResponseDTO> badParamException(BadParamsException e){
        ResponseDTO response = new ResponseDTO(null, HttpStatus.BAD_REQUEST.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadStockSellPriceException.class)
    public ResponseEntity<ResponseDTO> badStockSellPriceException(BadStockSellPriceException e){
        ResponseDTO response = new ResponseDTO(null, HttpStatus.BAD_REQUEST.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoppingCartNotFoundException.class)
    public ResponseEntity<ResponseDTO> shoppingCartNotFoundException(ShoppingCartNotFoundException e) {
        ResponseDTO response = new ResponseDTO(null, HttpStatus.NOT_FOUND.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotExistShoppingCartProductPageException.class)
    public ResponseEntity<ResponseDTO> notExistShoppingCartProductPageException(NotExistShoppingCartProductPageException e) {
        ResponseDTO response = new ResponseDTO(null, HttpStatus.NOT_FOUND.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> productNotFoundException(ProductNotFoundException e) {
        ResponseDTO response = new ResponseDTO(null, HttpStatus.NOT_FOUND.value(), "error", e.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
