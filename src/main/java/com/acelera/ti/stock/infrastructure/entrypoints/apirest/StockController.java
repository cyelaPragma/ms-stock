package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetPageStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import com.acelera.ti.stock.domain.usecase.SaveStockUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.FilterStockByParametersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    private final SaveStockUseCase saveStockUseCase;
    private final GetAllStockUseCase getAllStockUseCase;
    private final GetStockUseCase getStockUseCase;
    private final GetPageStockUseCase getPageStockUseCase;
    private final FilterStockByParametersUseCase filterStockByParameters;

    @PostMapping
    public ResponseEntity<Stock> saveStock(@RequestBody Stock stock) {
        Stock savedStock = saveStockUseCase.action(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStock);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks(@RequestParam int page, @RequestParam int size) {
        List<Stock> stockLists = getPageStockUseCase.action(getAllStockUseCase.action(), page, size);
        HttpStatus status = stockLists.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(stockLists, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable("id") Long id) {
        Stock stock = getStockUseCase.action(id);
        HttpStatus status = stock.getId() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(stock, status);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Stock>> filterStockByParameters(@RequestBody FilterParameters filterParameters) {
        List<Stock> stockList = filterStockByParameters.action(filterParameters);
        HttpStatus status = stockList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(stockList);
    }
}
