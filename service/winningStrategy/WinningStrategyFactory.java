package service.winningStrategy;

import constants.WinningStrategies;

public class WinningStrategyFactory {

    public WinningStrategy getWinningStrategy(WinningStrategies winningStrategies, int dimensions){
        //TODO: add a switchcase basis of the strategy chosen and return an object
        return new OrderOneWinningStrategy(dimensions);
    }
}
