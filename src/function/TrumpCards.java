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
		this.deck = new ArrayList<>(52);
		// リストに1-52の連番を代入
		for(int i=1;i<=52;i++) {deck.add(i);}
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
	
	
	
	//山札（deck）を　シャッフルするメソッド
   public void shuffle() {

       //山札をシャッフル
      Collections.shuffle(deck);
    }
   
   //山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
  public String toDescription(int cardNumber) {
   	String rank=toRank(toNumber(cardNumber));
   	String suit=toSuit(cardNumber);
   	return suit+"の"+rank;
   	
  }


   //山札の数をカードの数に置き換えるメソッド
    public int toNumber(int cardNumber) {
   	int number=cardNumber%13;
   	if(number==0) {
   		number=13;
   	}
   	return number;
   }

   //カード番号をランク（AやJ,Q,K）に変換するメソッド
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

  //山札の数をスート（ハートやスペードなどのマーク）に置き換えるメソッド
   public String toSuit(int cardNumber) {
   	switch((cardNumber-1)/13) {
   	case 0:
   		return "クラブ";
   	case 1:
   		return "ダイヤ";
   	case 2:
   		return "ハート";
   	case 3:
   		return "スペード";
   	default:
   		return "例外です";
   		}
   	
   }
}
