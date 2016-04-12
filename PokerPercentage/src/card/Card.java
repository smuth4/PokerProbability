package card;

public class Card {
	
	private String suit;
	private String rank;
	private boolean available;
	
	public Card(String rank, String suit) {
		this.suit = suit;
		this.rank = rank;
		this.available = true; 
		
	}
	
	public String toString(){
		return this.rank + "Of" + this.suit;
	}
	
	public boolean isAvailable() {
		return this.available;
		
	}
	
	public String getRank() {
		return this.rank;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}

	public void setAvailable() {
		this.available = true;
	}
	
	public void setUnavailable() {
		this.available = false;
	}
	
}
