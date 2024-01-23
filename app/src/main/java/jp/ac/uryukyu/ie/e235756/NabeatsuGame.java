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

    private ScoreManager scoreManager;
    public NabeatsuGame() {
        this.scoreManager = new ScoreManager();
    }

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
    
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < TimeUnit.SECONDS.toMillis(timeLimitSeconds)) {
            if (System.currentTimeMillis() - startTime >= TimeUnit.SECONDS.toMillis(timeLimitSeconds)) {
                // 制限時間を過ぎた場合
                System.out.println("制限時間を超過しました。ゲームオーバー");
                gameActive = false;
                return;
            }
    
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String userAnswer = scanner.nextLine();
    
                if ((answer % 3 == 0 || String.valueOf(answer).contains("3"))) {
                    if (userAnswer.equalsIgnoreCase("aho")) {
                        System.out.println("正解！ 333ポイントを獲得しました。");
                        score += 333;
                        scoreManager.updateScore(score);
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
                            scoreManager.updateScore(score);
                        } else {
                            System.out.println("不正解。ゲームオーバー");
                            gameActive = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("数値を入力してください。");
                        System.out.println("ゲームオーバー");
                        gameActive = false;
                    }
                }
    
                System.out.println("スコア: " + score);
                System.out.println("-------------------------------");
                return;
            }
        }
    
        // 制限時間を過ぎた場合
        System.out.println("制限時間を超過しました。ゲームオーバー");
        gameActive = false;
    }

    private void endGame() {
        System.out.println("ゲーム終了！ スコア: " + score);
        System.out.println("ハイスコア: " + scoreManager.getHighScores());
        System.exit(0);
    }
}
