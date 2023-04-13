package com.acelera.ti.stock.infrastructure.entrypoints.apirest;

import com.acelera.ti.stock.domain.model.model.parameters.FilterParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetPageStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import com.acelera.ti.stock.domain.usecase.SaveStockUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.FilterStockByParametersUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.UpdateStockSellPriceUseCase;
import com.acelera.ti.stock.infrastructure.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final UpdateStockSellPriceUseCase updateStockSellPrice;

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
    public ResponseEntity<List<Stock>> filterStockByParameters(
            @RequestBody FilterParameters filterParameters,@RequestParam int page, @RequestParam int size) {
        List<Stock> stockList = filterStockByParameters.action(filterParameters, page, size);
        HttpStatus status = stockList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(stockList);
    }

    @PatchMapping( "/{stockId}")
    public ResponseEntity<Stock> UpdateStockSellPrice(@PathVariable("stockId") Long stockId, @RequestParam("sellPrice") Double sellPrice) {
        Stock stock = updateStockSellPrice.action(stockId, sellPrice);
        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }
}