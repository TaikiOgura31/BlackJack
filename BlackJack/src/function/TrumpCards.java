package function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrumpCards {
	
	private List<Integer> deck;
	private int deckCnt;
	
    //1～52のカードを設定
	public TrumpCards() {
		super();
		this.deck = new ArrayList<>(53);
		// リストに1-53の連番を代入
		for(int i=1;i<=53;i++) {deck.add(i);}
		this.deckCnt = 0;
	}


	public List<Integer> getDeck() {
		return deck;
	}


	public void setDeck(List<Integer> deck) {
		this.deck = deck;
	}


	public int getDeckCnt() {
		return deckCnt;
	}


	public void setDeckCnt(int deckCnt) {
		this.deckCnt = deckCnt;
	}

	@Override
	public String toString() {
		return "deck [deck=" + deck + ", deckCnt=" + deckCnt + "]";
	}
	
	
	
	//山札（deck）を　シャッフルするメソッド  OK!
   public void shuffle() {

       //山札をシャッフル
      Collections.shuffle(deck);
    }
   
   //山札から1枚手に入れるメソッド
   public int card() {
	   int card=deck.get(0);
	   deck.remove(0);
	   return card;
   }
   
   
   
   
   //山札の数を（スート）の（ランク）の文字列に置き換えるメソッド OK!
  public String toDescription(int cardNumber) {
   	String rank=toRank(toNumber(cardNumber));
   	String suit=toSuit(cardNumber);
   	if(cardNumber==53) {
   		return "ジョーカー";
   	}
   	return suit+"の"+rank;
   	
  }


   //山札の数をカードの数に置き換えるメソッド OK!
    public int toNumber(int cardNumber) {
   	int number=cardNumber%13;
    if(cardNumber==53) {
    	number=-1;
    	return number;
    }
    if(number==0) {
    	number=13;
    }
   	return number;
   }

   //カード番号をランク（AやJ,Q,K）に変換するメソッド OK!
    public String toRank(int number) {
   	
   	switch(number) {
   	case 1:
   		return"A";
   	case 11:
   		return"J";
   	case 12:
   		return"Q";
   	case 13:
   		return"K";
   	default:
   		String str=String.valueOf(number);
   		return str;
   	}
  
   }

  //山札の数をスート（ハートやスペードなどのマーク）に置き換えるメソッド OK!
   public String toSuit(int cardNumber) {
   	switch(cardNumber/13) {
   	case 0:
   		return "クラブ";
   	case 1:
   		return "ダイヤ";
   	case 2:
   		return "ハート";
   	case 3:
   		return "スペード";
   	default:
   		return "ジョーカー";
   		}
   	
   }
}
