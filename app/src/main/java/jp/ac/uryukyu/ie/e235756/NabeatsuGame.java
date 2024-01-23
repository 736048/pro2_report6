package jp.ac.uryukyu.ie.e235756;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NabeatsuGame {
    private int score;
    private int timeLimitSeconds = 13;
    private boolean gameActive = true;

    public static void main(String[] args) {
        NabeatsuGame game = new NabeatsuGame();
        game.startGame();
    }

    public void startGame() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            if (gameActive) {
                if (timeLimitSeconds > 0) {
                    playRound();
                    timeLimitSeconds = 13; // 制限時間をリセット
                } else {
                    System.out.println("制限時間を超過しました。ゲームオーバー");
                    endGame();
                }
            } else {
                endGame();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void playRound() {
        Random random = new Random();
        int num1 = random.nextInt(90) + 10; // 10から99
        int num2 = random.nextInt(10);      // 0から9
        int answer = num1 + num2;
    
        System.out.println("問題: " + num1 + " + " + num2);
    
        System.out.print("回答を入力してください: ");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.next();
    
        if ((answer % 3 == 0 || String.valueOf(answer).contains("3"))) {
            if (userAnswer.equalsIgnoreCase("aho")) {
                System.out.println("正解！ 333ポイントを獲得しました。");
                score += 333;
            } else {
                System.out.println("不正解。ゲームオーバー");
                gameActive = false;
            }
        } else {
            try {
                int userIntAnswer = Integer.parseInt(userAnswer);
    
                if (userIntAnswer == answer) {
                    System.out.println("正解！ " + 99 + "ポイントを獲得しました。");
                    score += 99;
                } else if (userAnswer.equalsIgnoreCase("aho")) {
                    System.out.println("ゲームオーバー");
                    gameActive = false;
                } else {
                    System.out.println("不正解。ゲームオーバー");
                    gameActive = false;
                }
            } catch (NumberFormatException e) {
                // 数値以外の入力はここで処理
                System.out.println("不正解。ゲームオーバー");
                gameActive = false;
            }
        }
    
        System.out.println("スコア: " + score);
        System.out.println("-------------------------------");
    }

    private void endGame() {
        System.out.println("ゲーム終了！ スコア: " + score);
        System.exit(0);
    }
}
