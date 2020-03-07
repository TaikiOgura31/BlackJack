package function;

import java.util.List;
import java.util.Scanner;

//ブラックジャックゲームの処理をするクラス
public class BlackJack {
	
	
	private TrumpCards trumpCards;
	private Player player;
	private Player dealer;
	private int deckCnt=trumpCards.getDeckCnt();
	private int handCnt=player.getHandCnt();
	private int playerScore=player.getScore();
	private int dealerScore=dealer.getScore();
	
	//コンストラクター
	public BlackJack() {
		super();
		this.trumpCards = new TrumpCards();
		this.player = new Player();
		this.dealer = new Player();
		this.deckCnt=0;
		this.handCnt=0;
		this.playerScore=0;
	}
	//getter,setter
	public TrumpCards getTrumpCards() {
		return trumpCards;
	}

	public void setTrumpCards(TrumpCards trumpCards) {
		this.trumpCards = trumpCards;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getDealer() {
		return dealer;
	}

	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}


	
	//ゲームスタート後必ず行うメソッド（初期手札の配布、ポイント表示まで
    //…山札をシャッフル、2枚ずつドロー、山札の状態を記録
    public void gameStart() {
    	
  
    	
	    //山札をシャッフル
		trumpCards.shuffle();	    
		
	    //プレイヤー・ディーラーがカードを2枚引く
	    player.getHandList().add(trumpCards.getDeck().get(0));
	    dealer.getHandList().add(trumpCards.getDeck().get(1));
	    player.getHandList().add(trumpCards.getDeck().get(2));
	    dealer.getHandList().add(trumpCards.getDeck().get(3));
	    
	    //山札の進行状況を記録する変数deckCountを定義
	    trumpCards.setDeckCnt(4);
	    
	    //プレイヤーの手札枚数を記録する変数playerHandsを定義
	    player.setHandCnt(2);
	    
	    //プレイヤー・ディーラーの手札のポイントを表示
	    System.out.println("あなたの1枚目のカードは" +trumpCards.toDescription(player.getHandList().get(0) ));

	    System.out.println("ディーラーの1枚目のカードは" + trumpCards.toDescription(dealer.getHandList().get(0)));

	    System.out.println("あなたの2枚めのカードは" +trumpCards. toDescription(player.getHandList().get(1)));

	    System.out.println("ディーラーの2枚めのカードは秘密です。");

	    //プレイヤー・ディーラーのポイントを集計
	    int playerScore=addScore(player.getHandList());
	    int dealerScore=addScore(dealer.getHandList());
	    
	    System.out.println("あなたの現在のポイントは" + playerScore+"です" );
    }

	
	public void playerDraw() {
    //プレイヤーがカードを引くフェーズ
    while(true) {
    	System.out.println("カードを引きますか？Yes:y　or No:n");
    	Scanner sc=new Scanner(System.in);
    	String str=sc.next();
    	
    	if("n".equals(str)) {
    		break;
    	}else if("y".equals(str)) {
    	player.getHandList().add(deckCnt);
		
    	deckCnt++;
    	handCnt++;
		
		System.out.println("あなたの"+player.getHandCnt()+"枚目のカードは"+trumpCards.toDescription(player.getHandList().get(handCnt-1)));
		playerScore=addScore(player.getHandList());
		System.out.println("現在の合計は"+player.getScore()+"です");
		
		if(isBusted(playerScore)) {
			System.out.println("残念、バーストしてしまいました");
			return;
    	}else{
    		System.out.println("あなたの入力は"+str+"です。yかnを入力してください。");
    		
    		}
    	};
    }
	}
	
	
    public void dealerDraw() {
    //ディーラーが手札を17以上にするまでカードを引くフェーズ
    while(true) {
    	if(dealerScore>=17) {
    		break;
    	}else {
    		dealer.getHandList().add(deckCnt);
    		deckCnt++;
    		dealerScore=addScore(dealer.getHandList());
    		if(isBusted(dealerScore)) {
    			System.out.println("ディーラーがバーストしました。あなたの勝ちです。");
    			return;
    			}
    		}
    	}
    }
    
    //山札の通し番号を得点計算用のポイントに変換するメソッド.J/Q/Kは10とする
   public  int toScore(int num) {
    	if(num==11||num==12||num==13) {
    		num=10;
    	}
    	return num;
       
    }

    
    //現在の合計ポイントを計算するメソッド
    public int addScore(List<Integer> list) {
    	int sum=0;
    	for(int i=0;i<list.size();i++) {
    		sum=sum+toScore(trumpCards.toNumber(list.get(i)));
    	}
    	return sum;
   
    }
    
    
  //手札がバーストしているか判定するメソッド
    public boolean isBusted(int score) {
    	if(score<=21) {
    		return false;
    	}else {
    		return true;
    	}
   
   }


    
    public void getResult() {
    //ポイントを比較して勝ち負けを決める
    System.out.println("あなたのポイントは" + playerScore);
    System.out.println("ディーラーのポイントは"+ dealerScore);

    if(playerScore == dealerScore) {
        System.out.println("引き分けです。");
    } else if(playerScore > dealerScore) {
        System.out.println("勝ちました！");
    } else {
        System.out.println("負けました・・・");
    }
   }


}
