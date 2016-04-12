package card;

import java.util.ArrayList;

public class CardDeck  {
	private ArrayList<Card> deck = new ArrayList<Card>(); 
	private String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", 
								"Nine", "Ten", "Jack", "Queen", "King"};
	private String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
		
	public CardDeck() {
		for (int i = 0; i < ranks.length ; i++) {
			for (int j = 0; j< suits.length; j++) {
				Card card = new Card(this.ranks[i], this.suits[j]);
				this.deck.add(card);
			}
		}
	}
	
	public ArrayList<Card> getDeck() {
		return this.deck;
	}
	
	public void setUnavailable(Card card) throws CardDeckException {
		for (int i = 0; i< deck.size(); i++) {
			if (deck.get(i).toString().equals(card.toString())) {
				deck.get(i).setUnavailable();
				return;
			}
		}
		throw new CardDeckException("This card does not exist: " + card.toString());
	}
	
	public Card get(Card card) throws CardDeckException {
		for (int i = 0; i< deck.size(); i++) {
			if (deck.get(i).toString().equals(card.toString())) {
				return deck.get(i);	
			}
		}
		throw new CardDeckException("This card does not exist: " + card.toString());
	}
	
	public ArrayList<Card> getAllAvailable() {
		ArrayList<Card> result = new ArrayList<Card>();
		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i).isAvailable()){
				result.add(deck.get(i));
			}
		}
		return result;
	}
	
	public boolean contains(Card card) {
		for (int i = 0; i< deck.size(); i++) {
			if (deck.get(i).toString().equals(card.toString())) {
				return true;
			}
		}
		return false;
	}	
}
