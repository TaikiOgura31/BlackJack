package function;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//ブラックジャックゲームの処理をするクラス
public class BlackJack {


	private TrumpCards trumpCards;
	private List<Integer> deck;
	private Player player;
	private Player dealer;
	private int playerScore;
	private int dealerScore;
	//掛け金
	private int betMoney;

	//勝ち負けフラグ(1は勝ち-1は負け、2はナチュラルブラックジャック(手札2枚で21)
	int winFrag=0;
	//コンストラクター
	public BlackJack() {
		super();
		this.trumpCards = new TrumpCards();
		this.player = new Player();
		this.dealer = new Player();

	}

	//最初の画面
	public void Title() {
		Scanner sc=new Scanner(System.in);

		while(true) {
		System.out.println("ブラックジャックゲームにようこそ。 スタート:s ルール説明：r ゲーム終了:q");
		String str=sc.next();
		if("s".equals(str)) {
		System.out.println("ブラックジャックゲームを開始します。");
		System.out.println("最初の所持金は500です。");
		this.bet();
	}	else if("r".equals(str)) {
		System.out.println("ルール：カジノディーラーとプレイヤーのうち、手札のカードの合計枚数が21に近いほうが勝利。");
		System.out.println("最初に2枚の手札が配られた後、あなたは手札を引くヒットとこのまま勝負に行くスタンドを選択できます。");
		System.out.println("ヒットは何度でも行えますが、手札の合計が22を超えた時、バーストとなり即あなたの負けです");
		System.out.println("あなたがスタンドを選ぶとディーラーが追加のカードを引きます。この時ディーラーは必ず手札の合計値が17以上になるようカードを追加します");
		System.out.println("あなたと同じく、ディーラーも手札の合計が22を超えるとバーストで即敗北となります。すなわちあなたの勝利です");
		System.out.println("ディーラーがカードを引き終えバーストしていなければ、勝負です。合計値が21に近いほうが勝利です");
		System.out.println("特殊ルール");
		System.out.println("ダブルダウン：最初の手札を見たタイミングで、掛け金を2倍にし1度だけヒットするダブルダウンを選択できます。");
		System.out.println("説明は以上です。タイトルに戻ります");
		this.Title();

	}
		else if("q".equals(str)){
			return;
		}else {
			System.out.println("あなたの入力は"+str+"です。sかrかqを入力してください。");
		}
		}
	}
	
	//チップを賭ける
	public void bet() {
		System.out.println("現在の所持金は"+player.getMoney()+"です。");
		Scanner sc=new Scanner(System.in);

		while(true){
		System.out.println("賭け金を設定してください(10pt～、10の倍数で選択してください)");


			String bet=sc.next();
			try{
				betMoney=Integer.parseInt(bet);
			}catch(NumberFormatException e) {
				System.out.println("ベットする値は数字で入力してください");
				bet=sc.next();
			}

			if(betMoney>0&&player.getMoney()>=betMoney&&betMoney%10==0) {
				player.setMoney(player.getMoney()-betMoney);
				gameStart();
			}else  if(player.getMoney()<betMoney){
				System.out.println("所持金が足りません。");
			}else {
				System.out.println("あなたの入力は"+betMoney+"です。10の倍数で入力してください");
			}

			}
		}


	//ゲームスタート後必ず行うメソッド（初期手札の配布、ポイント表示まで
    //…山札をシャッフル、2枚ずつドロー、山札の状態を記録
    public void gameStart() {
    	System.out.println(trumpCards.getDeck());
    	//ジョーカー抜いてシャッフル
    	if(trumpCards.getDeck().size()==53) {
    		trumpCards.getDeck().remove(52);
    		}
    	trumpCards.shuffle();
    	System.out.println(trumpCards.getDeck());
	    //プレイヤー・ディーラーがカードを2枚引く
	    player.draw(trumpCards);
	    dealer.draw(trumpCards);
	    player.draw(trumpCards);
	    dealer.draw(trumpCards);


	    //プレイヤー・ディーラーの手札のポイントを表示
	    System.out.println("あなたの1枚目のカードは" +trumpCards.toDescription(player.getHandList().get(0) ));

	    System.out.println("ディーラーの1枚目のカードは" + trumpCards.toDescription(dealer.getHandList().get(0)));

	    System.out.println("あなたの2枚めのカードは" +trumpCards. toDescription(player.getHandList().get(1)));

	    System.out.println("ディーラーの2枚めのカードは秘密です。");



	    //プレイヤー・ディーラーのポイントを集計
	    playerScore=addScore(player.getHandList());
	    dealerScore=addScore(dealer.getHandList());

	    System.out.println("あなたの現在のポイントは" + playerScore+"です" );
	    if(winFrag==2) {
	    	System.out.println("ナチュラル・ブラックジャック！");
	    	this.gameEnd();
	    }
	    
	    this.playerDraw();
    }




  //プレイヤーがカードを引くフェーズ
	public void playerDraw() {
	



    while(true) {

    	System.out.println(player.getHandList());
    	//最初の一回だけダブルダウンを選択できるようにしたい
    	if(player.getHandCnt()==2) {
    	System.out.println("ヒット or スタンド　or ダブルダウン？　ヒット:h　or スタンド:s　ダブルダウン：d");
    	Scanner sc=new Scanner(System.in);
    	String str=sc.next();
    	
    		if("s".equals(str)) {
    			this.dealerDraw();
    		}
    		else if("h".equals(str)) {
    			hit();
    		}
    		else if("d".equals(str)){
    			doubleDown();
    		}
    		else{
    			System.out.println("あなたの入力は"+str+"です。hかsを入力してください。");

    			}
    		}
    	else {
    		System.out.println(player.getHandList());
    		System.out.println("ヒット or スタンド　ヒット:h　or スタンド:s");
        	Scanner sc=new Scanner(System.in);
        	String str=sc.next();

        		if("s".equals(str)) {
        			this.dealerDraw();
        		}
        		else if("h".equals(str)) {
        			hit();
        		}
        		else{
        			System.out.println("あなたの入力は"+str+"です。hかsを入力してください。");

        			}

    	}

    	}
    }

	
	public void hit() {
		player.draw(trumpCards);
		System.out.println("あなたの"+player.getHandCnt()+"枚目のカードは"+trumpCards.toDescription(player.getHandList().get(player.getHandCnt()-1))+"です");
		playerScore=addScore(player.getHandList());
		System.out.println("現在のポイントは"+playerScore+"です");
			if(isBusted(playerScore)) {
				System.out.println("残念、バーストしてしまいました。");
				winFrag=-1;
				this.gameEnd();
			}
			return;
	}
	

	//ダブルダウン(手札を見た後最初の一回のみ。掛け金を2倍にして1回だけヒットする）
	public void doubleDown() {
		System.out.println("ダブルダウン！掛け金を二倍にして1枚だけカードを引きます。");
		betMoney*=2;
		player.draw(trumpCards);
		System.out.println("あなたの"+player.getHandCnt()+"枚目のカードは"+trumpCards.toDescription(player.getHandList().get(player.getHandCnt()-1))+"です");
		playerScore=addScore(player.getHandList());
		System.out.println("現在のポイントは"+playerScore+"です");
			if(isBusted(playerScore)) {
				System.out.println("残念、バーストしてしまいました。");
				winFrag=-1;
				this.gameEnd();
				return;
				}
		dealerDraw();
		}




	//ディーラーが手札を17以上にするまでカードを引くフェーズ
    public void dealerDraw() {

    while(true) {
    	if(dealerScore>=17) {
    		judgeResult();
    	}else {
    		dealer.draw(trumpCards);

    		dealerScore=addScore(dealer.getHandList());
    		if(isBusted(dealerScore)) {
    			System.out.println("ディーラーがバーストしました。");
    			winFrag=1;
    			this.gameEnd();
    			System.out.println(trumpCards.getDeck());
    			}
    		}
    	}
    }

    //山札の通し番号を得点計算用のポイントに変換するメソッド.J/Q/Kは10
   public  int toScore(int num) {
    	if(num==11||num==12||num==13) {
    		num=10;
    	}
    	return num;

    }


    //現在の合計ポイントを計算するメソッド
    public int addScore(List<Integer> list) {
    	//エースの計算のためのフラグを立てる
    	int sum=0;
    	int aceFrag=0;
    	
    	for(int i=0;i<list.size();i++) {
    		if(trumpCards.toNumber(list.get(i))==1) {
    			aceFrag++;
    		}
    		sum=sum+toScore(trumpCards.toNumber(list.get(i)));	
    	}	
    	//1枚の目のエースを持っていて,エースを11にしてもバーストしないときは10として数える
    	if(aceFrag==1&&sum<12) {
    		sum+=10;
    		//そうしたとき、手札が2枚で21を作っていたらナチュラルブラックジャック
    		if(player.getHandCnt()==2&&sum==21) {
    			winFrag=2;
    		}
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


    //ポイントを比較して勝ち負けを決めるフェイズ
    public void judgeResult() {

    System.out.println("あなたのポイントは" + playerScore);
    System.out.println("ディーラーのポイントは"+ dealerScore);
    if(playerScore == dealerScore) {
        System.out.println("引き分けです。");
        winFrag=0;
    } else if(playerScore > dealerScore) {
        System.out.println("勝ちました！");
        winFrag=1;
    } else {
        System.out.println("負けました・・・");
        winFrag=-1;
    };
    this.gameEnd();
   }


    //掛け金清算
    public void clearUp() {
    	
    	
    	
    	if(winFrag==-1) {
    		System.out.println("残念。掛け金は没収です");
    	}else if(winFrag==0) {
    		System.out.println("掛け金が戻ってきます");
    		player.setMoney(player.getMoney()+betMoney);
    	}else if(winFrag==1){
    		System.out.println("おめでとう！掛け金が2倍になりました");
    		player.setMoney(player.getMoney()+betMoney*2);
    	}else if(winFrag==2) {
    		System.out.println("特別な勝利です！掛け金が3倍になります。");
    		player.setMoney(player.getMoney()+betMoney*3);
    	}
    }
    
    //1ゲーム終わった時のメソッド(ブラックジャックを初期化、もう一度orタイトルに戻る)
    public void gameEnd() {
    	System.out.println("掛け金を清算します。");
    	clearUp();
    	playerScore=0;
    	dealerScore=0;
    	betMoney=0;
    	player.setHandList(new ArrayList<Integer>());
    	dealer.setHandList(new ArrayList<Integer>());
    	player.setHandCnt(0);
    	dealer.setHandCnt(0);
    	if(trumpCards.getDeck().size()<=10) {
    		System.out.println("カードデッキの枚数が不足しています。新しいデッキを取得します…");
    		trumpCards=new TrumpCards();}
    	System.out.println(trumpCards.getDeck());
    	System.out.println("現在の所持金は"+player.getMoney()+"です。");
    	if(player.getMoney()==0) {
    		System.out.println("ゲームオーバー！タイトルに戻ります。");
    		player.setMoney(500);
    		Title();
    	}
    	Scanner sc=new Scanner(System.in);
    	  while(true) {
    	System.out.println("もう一度プレイしますか？ yes:y or no:n");
		String str=sc.next();
		if("y".equals(str)) {
			this.bet();
			return;
		}else if("n".equals(str)) {
			System.out.println("タイトルに戻ります");
			this.Title();
			return;
		}else {
			System.out.println("あなたの入力は"+str+"です。yかnを入力してください。");
			}
    	  }
    }

}
