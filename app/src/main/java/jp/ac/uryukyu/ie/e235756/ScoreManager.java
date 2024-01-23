package jp.ac.uryukyu.ie.e235756;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
    private List<Integer> highScores;
    private static final int MAX_HIGH_SCORES = 5;
    private boolean scoreUpdated; // スコアが更新されたかどうかのフラグ

    public ScoreManager() {
        this.highScores = loadHighScoresFromFile();
        this.scoreUpdated = false;
    }

    public List<Integer> getHighScores() {
        return highScores;
    }

    public void updateScore(int points) {
        if (!scoreUpdated) { // 一回のゲームでスコア更新は一回だけに制限
            this.highScores.add(points);
            Collections.sort(highScores, Collections.reverseOrder());
            trimToMaxHighScores();
            saveHighScoresToFile();
            scoreUpdated = true; // フラグを更新
        }
    }

    private void trimToMaxHighScores() {
        if (highScores.size() > MAX_HIGH_SCORES) {
            highScores.subList(MAX_HIGH_SCORES, highScores.size()).clear();
        }
    }

    private List<Integer> loadHighScoresFromFile() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int score = Integer.parseInt(line.trim().split(" ")[2]);
                scores.add(score);
            }
        } catch (IOException | NumberFormatException e) {
            // ファイルが存在しない、または読み込みエラーが発生した場合は無視して新しく作成
        }
        return scores;
    }

    private void saveHighScoresToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("highscores.txt"))) {
            for (int i = 0; i < highScores.size(); i++) {
                writer.println((i + 1) + ". Score: " + highScores.get(i));
            }
        } catch (IOException e) {
            System.out.println("ハイスコアの保存中にエラーが発生しました。");
            e.printStackTrace();
        }
    }
}
