package app;

import io.contek.invoker.binancefutures.api.ApiFactory;
import io.contek.invoker.binancefutures.api.rest.market.GetExchangeInfo;
import io.contek.invoker.binancefutures.api.rest.market.MarketRestApi;

import java.util.List;
import java.util.Set;

public class RefactorBot {
    public static void main(String[] args) {
        String basicStable = "USDT";
        String secondStable = "BUSD";

        MarketRestApi api = ApiFactory.getMainNet().rest().market();
        List<GetExchangeInfo.MarketDetails> symbols = api.getExchangeInfo().submit().symbols;


        Set<String> tradingPairs = CoinsAnalyser.convertMDetailsListToSet(symbols);
        Set<String> coins = CoinsAnalyser.checkIfCoinsHasPair(tradingPairs, basicStable, secondStable);


        Logger.loggCoinsWithDelta(coins, api, 0.02, basicStable, secondStable);


    }
}
