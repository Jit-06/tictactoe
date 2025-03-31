package botplayingstrategy;

public class BotPlayingStrategyFactory {

    public BotPlayingStrategy getBotPlayingStrategy(){
        return new RandomBotPlayingStrategy();
    }
}
