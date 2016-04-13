package card;
import java.util.ArrayList;
import java.util.Random;

public class CardDeckDealer{
	private int numberOfActiveHands = 0;
	private int numberOfCardsDealt = 0;
	private boolean flopDealt = false;
	private boolean turnDealt = false;
	private boolean riverDealt = false;
	private CardDeck cardDeckObject;
	
	public CardDeckDealer() {
		shuffle();
	}

	public int getNumberOfHandsDealt() {
		return this.numberOfActiveHands;
	}
	
	public int getNumberOfCardsDealt() {
		return this.numberOfCardsDealt;
	}
	
	public boolean isFlopDealt() {
		return this.flopDealt;
	}
	
	public boolean isTurnDealt() {
		return this.turnDealt;
	}
	
	public boolean isRiverDealt() {
		return this.riverDealt;
	}
	
	/**
	* Shuffles the card deck, giving a fresh deck.
	*/
	public void shuffle() {	
		cardDeckObject = new CardDeck();
		numberOfCardsDealt = 0;
		numberOfActiveHands = 0;
		riverDealt = false;
		turnDealt = false;
		flopDealt = false;	
	}
	
	public Card generateCard() throws CardDeckDealerException {
		if (numberOfCardsDealt == 52) {
			throw new CardDeckDealerException("All cards have been dealt.");
		}
		
		ArrayList<Card> availableDeck = cardDeckObject.getAllAvailable();
		Random random = new Random();
		Card card = availableDeck.get(random.nextInt(availableDeck.size()));
		
		try {
			cardDeckObject.setUnavailable(card);
		} catch (CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		numberOfCardsDealt++;
		return card;
	}
	
	public Card generateCard(Card card) throws CardDeckDealerException, CardDeckException {
		
		if (numberOfCardsDealt == 52) {
			throw new CardDeckDealerException("All cards have been dealt.");
		}
		
		if (!cardDeckObject.get(card).isAvailable()) {
			throw new CardDeckDealerException("This card has already been dealt: " + card.toString());
		}
		if (cardDeckObject.contains(card) == false) {
			throw new CardDeckDealerException("This card does not exist: " + card.toString());
		}
		
		cardDeckObject.setUnavailable(card);
		numberOfCardsDealt++;
		return card;
	}
	
	/**
	* Gives a 2 card hard. Max of 23 hands.
	* @throws MaximumHandsException 
	* @throws CardDeckDealerException 
	*/
	public ArrayList<Card> generateHand() throws CardDeckDealerException {
		if (numberOfActiveHands > 23) {
			throw new CardDeckDealerException("Maximum number of hands dealt.");
		}
		
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(generateCard());
		hand.add(generateCard());
		numberOfActiveHands++;
		return hand;
	}
	
	/**
	 * Generate a hand with the card specified and one random card.
	 * @param card
	 * @return A hand with 2 cards.
	 * @throws CardDeckDealerException 
	 * @throws CardDeckException 
	 */
	public ArrayList<Card> generateHand(Card card) throws CardDeckDealerException, CardDeckException{
		if (numberOfActiveHands > 23) {
			throw new CardDeckDealerException("Maximum number of hands dealt.");
		}
	
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(generateCard(card));
		hand.add(generateCard());
		
		numberOfActiveHands++;
		return hand;
	}
	
	/**
	 * Generate a hand with the cards specified.
	 * @param card1
	 * @param card2
	 * @return A hand with 2 cards.
	 * @throws CardDeckDealerException 
	 * @throws CardDeckException 
	 */
	public ArrayList<Card> generateHand(Card card1, Card card2) throws CardDeckDealerException, CardDeckException {
		if (numberOfActiveHands > 23) {
			throw new CardDeckDealerException("Maximum number of hands dealt.");
		}
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(generateCard(card1));
		hand.add(generateCard(card2));
		numberOfActiveHands++;		
		return hand;
	}
		
	/**
	* Gives a flop with 3 random cards.
	* @throws CardDeckDealerException 
	*/
	public ArrayList<Card> generateFlop() throws CardDeckDealerException {
		if (numberOfActiveHands < 2) {
			throw new CardDeckDealerException("Not enough active hands.");
		}
		
		if (flopDealt) {
			throw new CardDeckDealerException("Flop already dealt");
		}
		
		ArrayList<Card> flop = new ArrayList<Card>();
		flop.add(generateCard());
		flop.add(generateCard());
		flop.add(generateCard());
		flopDealt = true;
		return flop;
	}
	
	/**
	 * Gives a flop that contains the card specified and two random cards.
	 * @throws CardDeckDealerException 
	 * @throws CardDeckException 
	 * 
	 */
	public ArrayList<Card> generateFlop(Card card) throws  CardDeckDealerException, CardDeckException {
		if (numberOfActiveHands < 2) {
			throw new CardDeckDealerException("Not enough active hands.");
		}
		if (flopDealt ) {
			throw new CardDeckDealerException("Flop already dealt");
		}
		ArrayList<Card> flop = new ArrayList<Card>();
		flop.add(generateCard(card));
		flop.add(generateCard());
		flop.add(generateCard());
		
		flopDealt = true;
		return flop;
	}
	
	/**
	 * Gives a flop that contains the cards specified and one random card.
	 * @throws CardDeckDealerException 
	 * @throws CardDeckException 
	 */
    public ArrayList<Card> generateFlop(Card card1, Card card2) throws CardDeckDealerException, CardDeckException{
    	if (numberOfActiveHands < 2) {
    		throw new CardDeckDealerException("Not enough active hands.");
		}
		if (flopDealt) {
			throw new CardDeckDealerException("Flop already dealt");
		}
		ArrayList<Card> flop = new ArrayList<Card>();
		flop.add(generateCard(card1));
		flop.add(generateCard(card2));
		flop.add(generateCard());
		flopDealt = true;
		return flop;
	}

    /**
     * Gives a flop that contains the cards specified.
     * @throws CardDeckDealerException 
     * @throws CardDeckException 
     */
    public ArrayList<Card> generateFlop(Card card1, Card card2, Card card3) throws CardDeckDealerException, CardDeckException{
    	if (numberOfActiveHands < 2) {
    		throw new CardDeckDealerException("Not enough active hands.");
		}
		if (flopDealt) {
			throw new CardDeckDealerException("Flop already dealt");
		}
		ArrayList<Card> flop = new ArrayList<Card>();
		flop.add(generateCard(card1));
		flop.add(generateCard(card2));
		flop.add(generateCard(card3));
		
		flopDealt = true;
		return flop;
		
	}
	
    /**
     * Gives the turn with a random card.
     * @throws CardDeckDealerException 
     */
	public Card generateTurn() throws CardDeckDealerException {

		if (!flopDealt | riverDealt) {
			throw new CardDeckDealerException("Turn called incorrectly");
		}

		if (turnDealt) {
			throw new CardDeckDealerException("Turn already dealt");
		}
		
		Card turn = generateCard();
		turnDealt = true;
		return turn;
	}
	
	/**
	 * Gives the turn with the card specified.
	 * @throws CardDeckDealerException 
	 * @throws CardDeckException 
	 */
	public Card generateTurn(Card card) throws CardDeckDealerException, CardDeckException {
		if (!flopDealt | riverDealt) {
			throw new CardDeckDealerException("Turn called incorrectly");
		}
		
		if (turnDealt) {
			throw new CardDeckDealerException("Turn already dealt");
		}

		Card turn = generateCard(card);
		turnDealt = true;
		return turn;
	}
	
	/**
	 * Gives the river with a random card.
	 * @throws CardDeckDealerException 
	 */
	public Card generateRiver() throws CardDeckDealerException {
		if (riverDealt) {
			throw new CardDeckDealerException("River already dealt");
		}

		if (!turnDealt) {
			throw new CardDeckDealerException("River called incorrectly");
		}
		
		Card river = generateCard();
		riverDealt = true;
		return river;
	}	
	
	/**
	 * Gives the river with the card specified.
	 * @throws CardDeckDealerException 
	 * @throws CardDeckException 
	 */
	public Card generateRiver(Card card) throws CardDeckDealerException, CardDeckException{
		if (riverDealt) {
			throw new CardDeckDealerException("River already dealt");
		}

		if (!turnDealt) {
			throw new CardDeckDealerException("River called incorrectly");
		}
		
		Card river = generateCard(card);
		riverDealt = true;
		return river;	
	}
	
	public ArrayList<Card> generateCommunity() throws CardDeckDealerException{
		ArrayList<Card> community = new ArrayList<Card>();
		community.addAll(generateFlop());
		community.add(generateTurn());
		community.add(generateRiver());
		return community;
	}
	
	public ArrayList<Card> generateCommunity(Card card1, Card card2, Card card3, Card card4, Card card5) throws CardDeckDealerException, CardDeckException{
		ArrayList<Card> community = new ArrayList<Card>();
		community.addAll(generateFlop(card1, card2, card3));
		community.add(generateTurn(card4));
		community.add(generateRiver(card5));
		return community;
	}
}
