package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.ErrorHandling;
import com.acelera.ti.stock.domain.model.exceptions.StockEmptyException;
import com.acelera.ti.stock.domain.model.exceptions.StockNotFoundException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import com.acelera.ti.stock.domain.usecase.SaveStockUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
@RequiredArgsConstructor
public class UpdateStockSellPriceUseCase {
    Log log = null;
    private final SaveStockUseCase saveStockUseCase ;
    private final GetStockUseCase getStockUseCase;

    public Stock action(Long stockId, Double sellPrice) {
        try {
            var stock = getStockUseCase.action(stockId) ;
            stock.setSellPrice(sellPrice);
            return saveStockUseCase.action(stock);
        } catch(StockNotFoundException e){
            log.error("Stock not encontrado para el id: " + stockId, e);
            throw new ErrorHandling("Error actualizando el precio de venta", e);
        } catch (StockEmptyException e) {
            log.error("Stock está vació para el id: " + stockId, e);
            throw new ErrorHandling("Error actualizando el precio de venta", e);
        } catch (Exception e) {
            log.error("Error updating stock sell price for id: " + stockId, e);
            throw new ErrorHandling("Error actualizando el precio de venta", e);
        }
    }
}