package app;

import io.contek.invoker.binancefutures.api.rest.market.GetDepth;
import io.contek.invoker.binancefutures.api.rest.market.MarketRestApi;

import java.util.Set;

public class Logger {
    static public void loggCoinsWithDelta(Set<String> coins,
                                          MarketRestApi api,
                                          double delta,
                                          String basicStable,
                                          String secondStable){
        System.out.println("Total coins with usdt and busd pairs: " + coins.size());

        for (String coin : coins){
            try{
                GetDepth.Response responseUsdt = api.getDepth().setSymbol(coin + basicStable).setLimit(10).submit();
                GetDepth.Response responseBusd = api.getDepth().setSymbol(coin + secondStable).setLimit(10).submit();

                double delta1 = (responseUsdt.bids.get(0).get(0) / responseBusd.asks.get(0).get(0)) * 100 - 100;
                double delta2 = (responseBusd.bids.get(0).get(0) / responseUsdt.asks.get(0).get(0)) * 100 - 100;
                double maxDelta = Math.max(delta1, delta2);
                if (maxDelta > delta) {

                    System.out.println(coin);
                    System.out.println(maxDelta);
                    System.out.println("=====");
                }

            }catch (Exception e){
                System.out.println("Error with: " + coin);
            }
        }
    }

}
