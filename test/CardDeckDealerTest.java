package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import card.Card;
import card.CardDeckDealer;
import card.CardDeckDealerException;
import card.CardDeckException;

public class CardDeckDealerTest {
	CardDeckDealer dealer = new CardDeckDealer();

	@Test
	public void generateCardReturnsUniqueCards_test(){
		boolean result = true;
		dealer.shuffle();
		Set<Card> tempSet = new HashSet<Card>();
		for (int i = 0; i < 52; i++){
			try {
				Card card = dealer.generateCard();
				result = tempSet.add(card);
			} catch (CardDeckDealerException e) {
				System.out.println(e.getMessage());
			}
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateCardThrowsExceptionAfterFiftyTwoCards_test() {
		boolean result = false;
		dealer.shuffle();
		try {
			for (int i = 0; i <= 53; i++){
				dealer.generateCard();
			}
		} catch (CardDeckDealerException e) {
			if (e.getMessage().equals("All cards have been dealt.")) {
			result =  true;
			}
		}
		Assert.assertTrue(result);
	}
	
	@Test 
	public void generateDuplicateCard_test() {
		boolean result = false;
		dealer.shuffle();
		Card card = new Card("Seven", "Clubs");
		try {
			dealer.generateCard(card);
			dealer.generateCard(card);
		} catch (CardDeckDealerException | CardDeckException e) {
			if(e.getMessage().equals("This card has already been dealt: " + card.toString())) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateAllCards_test() {
		boolean result = false;
		Set<Card> hands = new HashSet<Card>();
		dealer.shuffle();
		try {
			for (int i=0 ; i< 52; i++) {
				hands.add(dealer.generateCard());
			}
			
			if (hands.size() == 52) {
				result = true;
			}
		} catch (CardDeckDealerException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateAllSpecificCards_test() {
		boolean result = true;
		dealer.shuffle();
		Card card = new Card("Seven", "Clubs");
		
		try {
			dealer.generateCard(card);
			for (int i=0 ; i< 51; i++) {
				dealer.generateCard();
			}
		} catch (CardDeckDealerException | CardDeckException e) {
			result = false;
		}
		Assert.assertTrue(result);
	}
	
	@Test 
	public void generateHandWithTwoSpecificCards_test() {
		boolean result = false;
		dealer.shuffle();
		Card card = new Card("Seven", "Clubs");
		try {
			dealer.generateHand(card, card);
		} catch (CardDeckDealerException | CardDeckException e) {
			if(e.getMessage().equals("This card has already been dealt: " + card.toString())) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateFakeCard_test() {
		boolean result = false;
		dealer.shuffle();
		Card card = new Card("Sven", "Clubs");
		try {
			dealer.generateCard(card);
		} catch (CardDeckDealerException | CardDeckException e) {
			if(e.getMessage().equals("This card does not exist: " + card.toString())) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateMaxHands_test() {
		boolean result = false;
		dealer.shuffle();
		try {
			for (int i = 0; i <= 24; i++){
				dealer.generateHand();
			}
		} catch (CardDeckDealerException e) {
			if(e.getMessage().equals("Maximum number of hands dealt.")) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}
		
	@Test
	public void generateFlopCanOnlyBeCalledAfterTwoHandsAreGenerated_test(){
		boolean result = false;
		dealer.shuffle();
		try {
			dealer.generateHand();
			dealer.generateFlop();
			
		} catch (CardDeckDealerException e) {
			if (e.getMessage().equals("Not enough active hands.")) {
				result = true;
			}
		}
	  	Assert.assertTrue(result);
	}
	
	@Test
	public void generateFlopCanOnlyBeCalledOnce_test() {
		boolean result = false;
		dealer.shuffle();
		try {
			dealer.generateHand();
			dealer.generateHand();
			dealer.generateFlop();
			dealer.generateFlop();
		} catch (CardDeckDealerException e) {
			if (e.getMessage().equals("Flop already dealt")) {
				result = true;
			}
			
		}
		Assert.assertTrue(result);
	}

	@Test
	public void generateTurnCanOnlyBeCalledAfterTheFlop_test() {
		boolean result = false;
		dealer.shuffle();
		try {
			dealer.generateHand();
			dealer.generateHand();
			dealer.generateTurn();
		} catch (CardDeckDealerException e) {
			if (e.getMessage().equals("Turn called incorrectly")) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}

	@Test
	public void generateTurnCanOnlyBeCalledOnce_test(){
		boolean result = false;
		dealer.shuffle();
		try {
			dealer.generateHand();
			dealer.generateHand();
			dealer.generateFlop();
			dealer.generateTurn();
			dealer.generateTurn();
			
		} catch (CardDeckDealerException e) {
			if(e.getMessage().equals("Turn already dealt")) {
				result = true;
			}
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateRiverCanOnlyBeCalledAfterTheTurn_test() {
		boolean result = false;
		dealer.shuffle();
		try {
			dealer.generateHand();
			dealer.generateHand();
			dealer.generateRiver();
		} catch (CardDeckDealerException e) {
			if (e.getMessage().equals("River called incorrectly")) {
			result = true;
			}
			
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void generateRiverCanOnlyBeCalledOnce_test() {
		boolean result = false;
		dealer.shuffle();
		try {
			dealer.generateHand();
			dealer.generateHand();
			dealer.generateFlop();
			dealer.generateTurn();
			dealer.generateRiver();
			dealer.generateRiver();
			
		} catch (CardDeckDealerException e) {
			if (e.getMessage().equals("River already dealt")) {
				result = true;
			}
		}		
		Assert.assertTrue(result);
	}
}

