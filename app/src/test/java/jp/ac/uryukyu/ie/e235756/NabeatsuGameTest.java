package jp.ac.uryukyu.ie.e235756;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NabeatsuGameTest {

    @Test
    public void testPlayRoundCorrectAnswer() {
        NabeatsuGame game = new NabeatsuGame();
        game.playRound(); // テスト対象のメソッドを呼び出す
        assertEquals(99, game.getScore()); // 期待されるスコアに変更する
    }

    @Test
    public void testPlayRoundIncorrectAnswer() {
        NabeatsuGame game = new NabeatsuGame();
        game.playRound(); 
        assertEquals(0, game.getScore()); 
    }

    @Test
    public void testPlayRoundAhoAnswer() {
        NabeatsuGame game = new NabeatsuGame();
        game.playRound(); 
        assertEquals(333, game.getScore()); 
    }
}
