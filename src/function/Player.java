package function;

import java.util.ArrayList;
import java.util.List;

public class Player {
	 //プレイヤー・ディーラーの手札リストを生成
	private List<Integer> handList;
	 //プレイヤーの手札枚数を記録する変数handCntを定義
	private int handCnt;
	 //現在の合計ポイントを計算するメソッド
	private int score;
	
	public Player() {
		this.handList = new ArrayList<Integer>();
		this.handCnt = 0;
		this.score = 0;
	}

	public List<Integer> getHandList() {
		return handList;
	}

	public void setHandList(List<Integer> handList) {
		this.handList = handList;
	}

	public int getHandCnt() {
		return handCnt;
	}

	public void setHandCnt(int handCnt) {
		this.handCnt = handCnt;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [handList=" + handList + ", handCnt=" + handCnt + ", score=" + score + "]";
	}
	
	
	
}
