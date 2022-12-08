package app;

import io.contek.invoker.binancefutures.api.rest.market.GetExchangeInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CoinsAnalyser {
    static Set<String> convertMDetailsListToSet(List<GetExchangeInfo.MarketDetails> tradingPairsInfo) {
        return tradingPairsInfo.stream()
            .map(marketDetails -> marketDetails.symbol)
            .collect(Collectors.toSet());
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
