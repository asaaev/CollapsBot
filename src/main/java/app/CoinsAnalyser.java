package app;

import io.contek.invoker.binancefutures.api.rest.market.GetExchangeInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoinsAnalyser {
    static Set<String> convertMDetailsListToSet(List<GetExchangeInfo.MarketDetails> tradingPairsInfo) {
        Set<String> pairsSet = new HashSet<>();
        for (GetExchangeInfo.MarketDetails pair : tradingPairsInfo) {
//            symbol contains name of trading pair from tradingPairsInfo
            pairsSet.add(pair.symbol);
        }
        return pairsSet;
    }

    static Set<String> checkIfCoinsHasPair(Set<String> tradingPairs, String basicStable, String secondStable){
        Set<String>coinsWithPair = new HashSet<>();
        for (String pair : tradingPairs){
            if (pair.contains(basicStable) ){
                String coin = pair.replace(basicStable, "");
                if (tradingPairs.contains(coin + secondStable)){
                    coinsWithPair.add(coin);
                }
            }
        }
        return coinsWithPair;
    }
}
