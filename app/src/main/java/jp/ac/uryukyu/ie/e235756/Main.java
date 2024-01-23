// Main.java

package jp.ac.uryukyu.ie.e235756;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("ゲームを始めるにはエンターキーを押してください。");

        System.out.print("ルールを確認するには 'y' を入力してください。");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("y")) {
            displayRules();
            waitForEnterKey(); // エンターキーが押されるまで待機
        }

        System.out.println("ゲームを開始します。");
        System.out.println("------------------------------");
        NabeatsuGame game = new NabeatsuGame();
        game.startGame();
    }

    private static void waitForEnterKey() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // エンターキーが押されるまで待機
    }

    private static void displayRules() {
        System.out.println("------------------------------");
        System.out.println("ルール説明:");
        System.out.println("ランダムな2桁+1桁の足し算が出るので、正しい回答をしよう。");
        System.out.println("ただし、3の倍数か3のつく場合には「aho」と回答しよう。");
        System.out.println("正解したら99のポイントが加算されるよ。");
        System.out.println("正解が「aho」だった場合には333のポイントが加算されるよ。");
        System.out.println("誤答した場合はゲームオーバーになるよ。");
        System.out.println("ゲームオーバー時にスコアが表示されるよ。");
        System.out.println("ゲームを始めるにはエンターキーを押してください。");
        System.out.println("------------------------------");
    }
}
