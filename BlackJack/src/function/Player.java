package function;

import java.util.ArrayList;
import java.util.List;

public class Player {
	//扱うカード
		private TrumpCards trumpCards;
	 //プレイヤー・ディーラーの手札リスト
	private List<Integer> handList;
	 //プレイヤーの手札枚数を記録する変数handCntを定義
	private int handCnt
	;
	 //現在の合計ポイント
	private int score;

	//現在の所持金
	private int money;

	

	public Player() {
		this.handList = new ArrayList<Integer>();
		this.handCnt = 0;
		this.score = 0;
		this.money=500;
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

	//デッキから1枚引く
	public void draw(TrumpCards trumpCards){
		handList.add(trumpCards.card());
		this.handCnt++;

	}


	//所持金確認
	public int getMoney() {
		return money;
	}

	//所持金変動（賭けの勝敗ごとにつかう）
	public void setMoney(int money) {
		this.money=money;
	}

}
