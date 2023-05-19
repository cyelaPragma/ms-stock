package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.badrequest.BadParamsException;
import com.acelera.ti.stock.domain.model.exceptions.badrequest.BadStockSellPriceException;
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
        if(sellPrice <= 0){
            throw new BadStockSellPriceException();
        }
        var stock = getStockUseCase.action(stockId);
        stock.setSellPrice(sellPrice);
        return saveStockUseCase.action(stock);
    }
}