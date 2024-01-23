package jp.ac.uryukyu.ie.e235756;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * スコアを管理し、ハイスコアを保存するクラス。
 */
public class ScoreManager {
    private List<Integer> highScores;
    private static final int MAX_HIGH_SCORES = 5;
    private boolean scoreUpdated; // スコアが更新されたかどうかのフラグ

    /**
     * {@code ScoreManager} クラスの新しいインスタンスを作成。
     * ハイスコアはファイルから読み込まれる。
     */
    public ScoreManager() {
        this.highScores = loadHighScoresFromFile();
        this.scoreUpdated = false;
    }

    /**
     * ハイスコアのリストを取得。
     *
     * @return ハイスコアのリスト
     */
    public List<Integer> getHighScores() {
        return highScores;
    }

    /**
     * スコアを更新。一回のゲームでのスコア更新は一回だけに制限される。
     *
     * @param points 更新するスコア
     */
    public void updateScore(int points) {
        if (!scoreUpdated) {
            this.highScores.add(points);
            Collections.sort(highScores, Collections.reverseOrder());
            trimToMaxHighScores();
            saveHighScoresToFile();
            scoreUpdated = true;
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
