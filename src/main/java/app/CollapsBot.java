package app;

import io.contek.invoker.binancefutures.api.ApiFactory;
import io.contek.invoker.binancefutures.api.rest.market.GetDepth;
import io.contek.invoker.binancefutures.api.rest.market.GetExchangeInfo;
import io.contek.invoker.binancefutures.api.rest.market.GetTickerBookTicker;
import io.contek.invoker.binancefutures.api.rest.market.MarketRestApi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollapsBot {
    public static void main(String[] args) {
        MarketRestApi api = ApiFactory.getMainNet().rest().market();
        GetExchangeInfo.Response pairs = api.getExchangeInfo().submit();
        Set<String> pairsSet = new HashSet<>();
        Set<String> coinsWithPair = new HashSet<>();

        for (GetExchangeInfo.MarketDetails pair : pairs.symbols) {
            pairsSet.add(pair.symbol);
        }

        for (String pair : pairsSet){
            if (pair.contains("USDT") ){
                String coin = pair.replace("USDT", "");
                if (pairsSet.contains(coin + "BUSD")){
                    coinsWithPair.add(coin);
                }
            }
        }

        for (String coin : coinsWithPair){
            GetDepth.Response responseUsdt = api.getDepth().setSymbol(coin + "USDT").setLimit(10).submit();
            GetDepth.Response responseBusd = api.getDepth().setSymbol(coin + "BUSD").setLimit(10).submit();

            System.out.println(coin);
            System.out.println("USDT Best bid: " + responseUsdt.bids.get(0).get(0) + ", best ask: " + responseUsdt.asks.get(0).get(0));
            System.out.println("BUSD Best bid: " + responseBusd.bids.get(0).get(0) + ", best ask: " + responseBusd.asks.get(0).get(0));
            System.out.println("=====");
        }
    }
}
