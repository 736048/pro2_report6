package jp.ac.uryukyu.ie.e235756;
import java.util.Random;
import java.util.Scanner;

public class NabeatsuGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        int timeLimit = 60; // 制限時間（秒）

        while (timeLimit > 0) {
            // ランダムな2桁+1桁の足し算
            int num1 = random.nextInt(90) + 10; // 10から99
            int num2 = random.nextInt(9) + 1;   // 1から9
            int answer = num1 + num2;

            System.out.println("問題: " + num1 + " + " + num2);
            System.out.print("回答を入力してください: ");

            // ユーザーの回答を取得
            String userAnswer = scanner.next();

            // 「aho」が回答の場合
            if (userAnswer.equalsIgnoreCase("aho")) {
                // 3の倍数か3のつく数字だった場合
                if (answer % 3 == 0 || String.valueOf(answer).contains("3")) {
                    System.out.println("正解！ 333ポイントを獲得しました。");
                    score += 333;
                } else {
                    System.out.println("aho");
                }
            } else {
                try {
                    // ユーザーの回答を整数に変換
                    int userIntAnswer = Integer.parseInt(userAnswer);

                    // 正誤判定
                    if ((answer % 3 == 0 || String.valueOf(answer).contains("3")) && userIntAnswer != answer) {
                        System.out.println("ゲームオーバー");
                        break;
                    } else if (userIntAnswer == answer) {
                        if (answer % 3 == 0 || String.valueOf(answer).contains("3")) {
                            System.out.println("ゲームオーバー");
                            break;
                        } else {
                            System.out.println("正解！ 99ポイントを獲得しました。");
                            score += 99;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("数値を入力してください。");
                    // エラーが発生した場合、もう一度ループの最初に戻る
                    continue;
                }
            }

            // 制限時間を減少させる
            timeLimit--;

            // 次の問題へ
            if (timeLimit > 0) {
                System.out.println("次の問題へ進みます。");
            }
        }

        // 最終スコアの表示
        System.out.println("ゲーム終了！ スコア: " + score);
    }
}

