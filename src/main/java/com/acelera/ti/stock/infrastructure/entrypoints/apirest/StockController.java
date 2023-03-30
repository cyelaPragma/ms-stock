package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import com.acelera.ti.stock.domain.usecase.SaveStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    private final SaveStockUseCase saveStockUseCase;
    private final GetAllStockUseCase getAllStockUseCase;
    private final GetStockUseCase getStockUseCase;

    @PostMapping
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock) {
        Stock savedStock = saveStockUseCase.action(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStock);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = getAllStockUseCase.action();
        HttpStatus status = stockList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(stockList, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable("id")  Long id) {
        Stock stock = getStockUseCase.action(id);
        HttpStatus status = stock.getId() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(stock, status);
    }
}
