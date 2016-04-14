package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import card.Card;
import card.CardDeckDealer;
import card.CardDeckDealerException;
import card.CardDeckException;
import comparator.HandsComparator;
import comparator.HandsComparatorException;

public class HandsComparatorTest {
	
	@Test
	public void highCardWins_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String highCard;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Six", "Hearts");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Queen", "Diamonds" );
		Card card4 = new Card("Ten", "Clubs");
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3, card4);
			community = dealer.generateCommunity();
			highCard = HandsComparator.checkHighCards(hand1,hand2,community);
			if (highCard.equals("hand1-HighCard")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void highCardsSplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String highCard;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Six", "Hearts");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Six", "Spades");
		Card card4 = new Card("King", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity();
			highCard = HandsComparator.checkHighCards(hand1,hand2,community);
			if (highCard.equals("split-HighCard")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void lowCardsSplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String highCard;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Three", "Diamonds");
		Card card3 = new Card("Two", "Spades");
		Card card4 = new Card("Three", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity();
			highCard = HandsComparator.checkHighCards(hand1,hand2,community);
			if (highCard.equals("split-HighCard")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
		
	@Test
	public void pairVsHighCard_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Two", "Spades");
		Card card4 = new Card("Three", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Nine", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkPairs(hand1,hand2,community);
			if (pair.equals("hand2-Pair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}

	@Test
	public void pairVsPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Two", "Spades");
		Card card4 = new Card("Three", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Ace", "Spades");
		Card card9 = new Card("Jack", "Hearts");
				
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkPairs(hand1,hand2,community);
			if (pair.equals("hand1-Pair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void equalPairsHighKicker_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("King", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Ace", "Spades");
		Card card9 = new Card("Jack", "Hearts");
				
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkPairs(hand1,hand2,community);
			if (pair.equals("hand2-Pair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void equalPairsKickerSplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Ace", "Spades");
		Card card9 = new Card("Jack", "Hearts");
				
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkPairs(hand1,hand2,community);
			if (pair.equals("split-Pair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void equalPairsCommunitySplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Three", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Four", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Ace", "Spades");
		Card card9 = new Card("Jack", "Hearts");
				
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkPairs(hand1,hand2,community);
			if (pair.equals("split-Pair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void PairWithNoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Three", "Spades");
		Card card9 = new Card("Jack", "Hearts");
				
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkPairs(hand1,hand2,community);
			if (pair.equals("split-HighCard")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
		
	@Test 
	public void  twoPairVsHighCard_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String twoPair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Four", "Spades");
		Card card4 = new Card("Three", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Clubs");
		Card card8 = new Card("King", "Diamonds");
		Card card9 = new Card("Ace", "Hearts");
		
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			twoPair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (twoPair.equals("hand1-TwoPair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void twoPairVsPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String twoPair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Four", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Clubs");
		Card card8 = new Card("King", "Diamonds");
		Card card9 = new Card("Ace", "Hearts");
		
		
		try {
			hand2 = dealer.generateHand(card1,card2);
			hand1 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			twoPair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (twoPair.equals("hand2-TwoPair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void twoPairVstwoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String twoPair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Four", "Spades");
		Card card4 = new Card("Three", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Four", "Hearts");
		Card card7 = new Card("Three", "Spades");
		Card card8 = new Card("King", "Diamonds");
		Card card9 = new Card("Ace", "Hearts");
		
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			twoPair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (twoPair.equals("hand1-TwoPair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void TwoPairVsTwoPairSecondaryPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String twoPair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("King", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Clubs");
		Card card8 = new Card("King", "Diamonds");
		Card card9 = new Card("Ace", "Hearts");
		
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			twoPair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (twoPair.equals("hand2-TwoPair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}

	@Test 
	public void twoPairVsTwoPairHighKicker_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String twoPair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Four", "Hearts");
		Card card7 = new Card("Three", "Spades");
		Card card8 = new Card("Four", "Diamonds");
		Card card9 = new Card("Ace", "Hearts");
		
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			twoPair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (twoPair.equals("hand1-TwoPair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void twoPairVsTwoPairBoardKicker_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String twoPair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Clubs");
		Card card8 = new Card("King", "Diamonds");
		Card card9 = new Card("Ace", "Hearts");
		
		
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			twoPair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (twoPair.equals("split-TwoPair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
	
	@Test 
	public void TwoPairWithNoTwoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String pair;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Three", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Four", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("Seven", "Hearts");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Ace", "Spades");
		Card card9 = new Card("Jack", "Hearts");
				
		try {
			hand1 = dealer.generateHand(card1,card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			pair = HandsComparator.checkTwoPairs(hand1,hand2,community);
			if (pair.equals("split-Pair")) {
				result = true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(result);
	}
		
	@Test 
	public void  threeOfAKindVsHighCard_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Queen", "Spades");
		Card card3 = new Card("Three", "Hearts");
		Card card4 = new Card("Two", "Diamonds");
		
		Card card5 = new Card("Jack", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Ace", "Clubs");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("hand1-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	
	
	@Test 
	public void threeOfAKindVsPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Three", "Hearts");
		Card card2 = new Card("Two", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("Jack", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Two", "Clubs");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("hand2-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test 
	public void threeOfAKindVsTwoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("Queen", "Spades");
		Card card3 = new Card("Three", "Hearts");
		Card card4 = new Card("Two", "Diamonds");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Two", "Clubs");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("hand1-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test 
	public void threeOfAKindVsThreeOfAKind_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Jack", "Hearts");
		Card card2 = new Card("Jack", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("Jack", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Two", "Clubs");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("hand2-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test 
	public void threeOfAKindVsThreeOfAKindKicker_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("Queen", "Hearts");
		Card card3 = new Card("Jack", "Clubs");
		Card card4 = new Card("Queen", "Diamonds");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Queen", "Spades");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("hand1-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test 
	public void threeOfAKindVsThreeOfAKindSecondKicker_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Jack", "Hearts");
		Card card2 = new Card("Queen", "Diamonds");
		Card card3 = new Card("King", "Hearts");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("Ace", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Queen", "Spades");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("hand2-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}

	@Test 
	public void threeOfAKindVsThreeOfAKindBoardKicker_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Diamonds");
		Card card2 = new Card("Eight", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Nine", "Spades");
		
		Card card5 = new Card("Jack", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Ace", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("split-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test 
	public void threeOfAKindWithNoThreeOfAKind_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Diamonds");
		Card card2 = new Card("Eight", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Eight", "Spades");
		
		Card card5 = new Card("Jack", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Eight", "Hearts");
		Card card8 = new Card("Ace", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkThreeOfAKinds(hand1, hand2, community);
			if (threes.equals("split-TwoPair")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
		
	
	@Test 
	public void lowStraight_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Four", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("hand1-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}

	@Test 
	public void highStraight_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Ace", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Jack", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Queen", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("hand2-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}

	@Test 
	public void straightVsPair_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Ace", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Jack", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Queen", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("hand2-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test 
	public void straightVsTwoPair_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Ten", "Spades");
		Card card4 = new Card("Five", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Four", "Clubs");
		Card card7 = new Card("Ten", "Diamonds");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("hand1-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}

	@Test 
	public void straightVsThreeOfAKind_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Ace", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Jack", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("King", "Spades");
		Card card9 = new Card("Queen", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("hand2-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test 
	public void straightVsStraight_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Three", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Ace", "Clubs");
		Card card6 = new Card("Four", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("hand2-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
		

	}
	
	@Test 
	public void straightVsStraightSplit_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Ace", "Clubs");
		Card card6 = new Card("Four", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkStraights(hand1, hand2, community);
			if (straight.equals("split-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	@Test 
	public void straightWithNoStraight_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String threes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Diamonds");
		Card card2 = new Card("Eight", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Nine", "Spades");
		
		Card card5 = new Card("Jack", "Clubs");
		Card card6 = new Card("Queen", "Clubs");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Ace", "Clubs");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			threes = HandsComparator.checkStraights(hand1, hand2, community);
			if (threes.equals("split-ThreeOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	
	
	
	@Test
	public void flushVsHighCard_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Four", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("hand1-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void flushVsPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Clubs");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Hearts");
		Card card4 = new Card("Five", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("hand2-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test 
	public void flushVsTwoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Jack", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("hand1-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test 
	public void flushVsThreeOfAKind_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Clubs");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Hearts");
		Card card4 = new Card("Five", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Ace", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("hand2-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void flushVsStraight_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Eight", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Jack", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("hand1-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test
	public void flushVsFlush_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Hearts");
		Card card4 = new Card("Five", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Ace", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("hand1-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void flushVsFlushSplit_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Three", "Hearts");
		Card card3 = new Card("Four", "Hearts");
		Card card4 = new Card("Five", "Hearts");
		
		Card card5 = new Card("Ace", "Hearts");
		Card card6 = new Card("Ten", "Hearts");
		Card card7 = new Card("Nine", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("King", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFlushes(hand1, hand2, community);
			if (flushes.equals("split-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	
	@Test
	public void flushWithNoFlush_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straight;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Hearts");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Ace", "Clubs");
		Card card6 = new Card("Four", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Five", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straight = HandsComparator.checkFlushes(hand1, hand2, community);
			if (straight.equals("split-Straight")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	
	@Test
	public void fullHouseVsHighCard_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Spades");
		Card card2 = new Card("King", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Nine", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Three", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand2-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	
	
	
	@Test
	public void fullHouseVsPair_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Spades");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Nine", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Three", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand1-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
				 
	}
	
	@Test 
	public void fullHouseVsTwoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ten", "Spades");
		Card card2 = new Card("Three", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Nine", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Three", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand2-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}

	@Test 
	public void fullHouseVsThreeOfAKind_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Spades");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Three", "Spades");
		Card card4 = new Card("Nine", "Hearts");
		
		Card card5 = new Card("Three", "Hearts");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Three", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand1-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
			
		Assert.assertTrue(result);
		
		
	}
	
	@Test
	public void fullHouseVsStraight_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ten", "Spades");
		Card card2 = new Card("Eight", "Hearts");
		Card card3 = new Card("Nine", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Nine", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Queen", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand2-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test
	public void fullHouseVsFlush_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Spades");
		Card card2 = new Card("Ace", "Hearts");
		Card card3 = new Card("Three", "Spades");
		Card card4 = new Card("Nine", "Hearts");
		
		Card card5 = new Card("Three", "Diamonds");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Three", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand1-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
			
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void fullHouseVsFullHouse_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Eight", "Spades");
		Card card2 = new Card("Eight", "Hearts");
		Card card3 = new Card("Jack", "Spades");
		Card card4 = new Card("Jack", "Hearts");
		
		Card card5 = new Card("Nine", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Ten", "Hearts");
		Card card8 = new Card("Jack", "Clubs");
		Card card9 = new Card("Eight", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand2-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
		
	}
	

	@Test 
	public void fullHouseVsFullHouseKicker_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Spades");
		Card card2 = new Card("Jack", "Hearts");
		Card card3 = new Card("Ace", "Hearts");
		Card card4 = new Card("Ten", "Hearts");
		
		Card card5 = new Card("Ace", "Diamonds");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ten", "Spades");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("Jack", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("hand1-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
			
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void fullHouseVsFullHouseSplit_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Jack", "Diamonds");
		Card card2 = new Card("Jack", "Hearts");
		Card card3 = new Card("Jack", "Spades");
		Card card4 = new Card("Jack", "Clubs");
		
		Card card5 = new Card("Nine", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Nine", "Diamonds");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Eight", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (fullHouse.equals("split-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}

	@Test
	public void fullHouseWithNoFullHouse_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String flushes;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Two", "Hearts");
		Card card2 = new Card("Three", "Hearts");
		Card card3 = new Card("Four", "Hearts");
		Card card4 = new Card("Five", "Hearts");
		
		Card card5 = new Card("Ace", "Hearts");
		Card card6 = new Card("Ten", "Hearts");
		Card card7 = new Card("Nine", "Hearts");
		Card card8 = new Card("Seven", "Hearts");
		Card card9 = new Card("King", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			flushes = HandsComparator.checkFullHouses(hand1, hand2, community);
			if (flushes.equals("split-Flush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void fourOfAkindVsHighCard_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("King", "Spades");
		Card card3 = new Card("Jack", "Spades");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("Four", "Clubs");
		Card card6 = new Card("Five", "Spades");
		Card card7 = new Card("King", "Diamonds");
		Card card8 = new Card("King", "Hearts");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand1-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test
	public void fourOfAKindVsPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("Three", "Spades");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Five", "Spades");
		Card card7 = new Card("Queen", "Diamonds");
		Card card8 = new Card("Nine", "Hearts");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand2-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test 
	public void fourOfAKindVsTwoPair_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("King", "Spades");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("King", "Hearts");
		Card card6 = new Card("Five", "Spades");
		Card card7 = new Card("King", "Diamonds");
		Card card8 = new Card("Nine", "Hearts");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand1-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test 
	public void fourOfAKindVsThreeOfAKind_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("Seven", "Spades");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Five", "Spades");
		Card card7 = new Card("Queen", "Diamonds");
		Card card8 = new Card("Queen", "Hearts");
		Card card9 = new Card("Four", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand2-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void fourOfAKindVsStraight_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("King", "Spades");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("King", "Hearts");
		Card card6 = new Card("Jack", "Spades");
		Card card7 = new Card("King", "Diamonds");
		Card card8 = new Card("Nine", "Hearts");
		Card card9 = new Card("Ten", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand1-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}

	@Test
	public void fourOfAKindVsFlush_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("Seven", "Clubs");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Five", "Clubs");
		Card card7 = new Card("Queen", "Diamonds");
		Card card8 = new Card("Queen", "Hearts");
		Card card9 = new Card("Four", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand2-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void fourOfAKindVsFullHouse_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Clubs");
		Card card2 = new Card("King", "Spades");
		Card card3 = new Card("Ace", "Spades");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("King", "Hearts");
		Card card6 = new Card("Jack", "Spades");
		Card card7 = new Card("King", "Diamonds");
		Card card8 = new Card("Nine", "Hearts");
		Card card9 = new Card("Ace", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand1-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void fourOfAKindVsfourOfAKind_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Clubs");
		Card card2 = new Card("Ace", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("Queen", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Five", "Clubs");
		Card card7 = new Card("Queen", "Diamonds");
		Card card8 = new Card("Ace", "Hearts");
		Card card9 = new Card("Ace", "Spades");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand1-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test 
	public void fourOfAKindVsfourOfAKindKicker_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Clubs");
		Card card2 = new Card("Queen", "Spades");
		Card card3 = new Card("Queen", "Hearts");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("King", "Clubs");
		Card card6 = new Card("King", "Spades");
		Card card7 = new Card("King", "Diamonds");
		Card card8 = new Card("King", "Hearts");
		Card card9 = new Card("Queen", "Diamonds");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("hand2-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void fourOfAKindVsfourOfASplit_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Nine", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ace", "Diamonds");
		Card card8 = new Card("Ace", "Hearts");
		Card card9 = new Card("Ace", "Spades");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3, card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("split-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void fourOfAKindWithNofourOfAKind_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Jack", "Diamonds");
		Card card2 = new Card("Jack", "Hearts");
		Card card3 = new Card("Jack", "Spades");
		Card card4 = new Card("Jack", "Clubs");
		
		Card card5 = new Card("Nine", "Hearts");
		Card card6 = new Card("Nine", "Clubs");
		Card card7 = new Card("Nine", "Diamonds");
		Card card8 = new Card("Seven", "Clubs");
		Card card9 = new Card("Eight", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3, card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkFourOfAKinds(hand1, hand2, community);
			if (fullHouse.equals("split-FullHouse")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void straightFlushVsHighCard_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Eight", "Spades");
		Card card2 = new Card("Two", "Clubs");
		Card card3 = new Card("Ace", "Diamonds");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3, card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand2-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void straightFlushVsPair_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Hearts");
		Card card2 = new Card("Two", "Clubs");
		Card card3 = new Card("Two", "Diamonds");
		Card card4 = new Card("Two", "Spades");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand1-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
				 
		Assert.assertTrue(result);
	}
	
	@Test 
	public void straightFlushVsTwoPair_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("King", "Spades");
		Card card2 = new Card("Three", "Spades");
		Card card3 = new Card("Ace", "Diamonds");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3, card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand2-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test 
	public void straightFlushVsThreeOfAKind_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Hearts");
		Card card2 = new Card("Three", "Clubs");
		Card card3 = new Card("Two", "Diamonds");
		Card card4 = new Card("Two", "Spades");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand1-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void straightFlushVsStraight_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Spades");
		Card card2 = new Card("Three", "Spades");
		Card card3 = new Card("Ace", "Diamonds");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand2-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void straightFlushVsFlush_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Hearts");
		Card card2 = new Card("Three", "Clubs");
		Card card3 = new Card("Two", "Hearts");
		Card card4 = new Card("Two", "Spades");
		
		Card card5 = new Card("Two", "Clubs");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand1-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void straightFlushVsFullHouse_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Queen", "Clubs");
		Card card2 = new Card("Three", "Spades");
		Card card3 = new Card("King", "Hearts");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Queen", "Spades");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand2-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void straightFlushVsFourOfAKind_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Hearts");
		Card card2 = new Card("Ten", "Hearts");
		Card card3 = new Card("Two", "Hearts");
		Card card4 = new Card("Two", "Spades");
		
		Card card5 = new Card("Two", "Diamonds");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Two", "Clubs");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand1-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void straightFlushVsStraightFlush_test() {
		
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Eight", "Hearts");
		Card card2 = new Card("Nine", "Hearts");
		Card card3 = new Card("King", "Hearts");
		Card card4 = new Card("Ace", "Hearts");
		
		Card card5 = new Card("Three", "Clubs");
		Card card6 = new Card("Queen", "Spades");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ten", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("hand2-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void straightFlushVsStraightFlushSplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String straightFlush;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Eight", "Hearts");
		Card card2 = new Card("Nine", "Diamonds");
		Card card3 = new Card("King", "Spades");
		Card card4 = new Card("Ace", "Clubs");
		
		Card card5 = new Card("Ten", "Hearts");
		Card card6 = new Card("King", "Hearts");
		Card card7 = new Card("Queen", "Hearts");
		Card card8 = new Card("Jack", "Hearts");
		Card card9 = new Card("Ace", "Hearts");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			straightFlush = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (straightFlush.equals("split-StraightFlush")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}

	@Test
	public void straightFlushWithNostraightFlush_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		String fullHouse;
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Nine", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ace", "Diamonds");
		Card card8 = new Card("Ace", "Hearts");
		Card card9 = new Card("Ace", "Spades");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			fullHouse = HandsComparator.checkStraightFlushes(hand1, hand2, community);
			if (fullHouse.equals("split-FourOfAKind")) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void compareHandsEmptyList_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> community = new ArrayList<Card>();
		
		Card card1 = new Card("Nine", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ace", "Diamonds");
		Card card8 = new Card("Ace", "Hearts");
		Card card9 = new Card("Ace", "Spades");
		
		try {
			dealer.generateHand(card1, card2);
			dealer.generateHand(card3, card4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			winners = HandsComparator.compareHands(hands, community);
			if (winners.size() == 0) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void compareHands_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		
		ArrayList<Card>hand1 = new ArrayList<Card>();
		ArrayList<Card>hand2 = new ArrayList<Card>();
		ArrayList<Card>hand3 = new ArrayList<Card>();
		ArrayList<Card>hand4 = new ArrayList<Card>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Nine", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		
		Card card3 = new Card("Queen", "Spades");
		Card card4 = new Card("King", "Hearts");
		
		Card card10 = new Card("King", "Spades");
		Card card11 =  new Card("Ten", "Hearts");
		
		Card card12 = new Card("Jack", "Diamonds");
		Card card13 = new Card("Jack", "Clubs");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Two", "Clubs");
		Card card7 = new Card("Seven", "Diamonds");
		Card card8 = new Card("Ace", "Hearts");
		Card card9 = new Card("Ten", "Spades");
		
		try {
			hand1 = dealer.generateHand(card1, card2);
			hand2 = dealer.generateHand(card3,card4);
			hand3 = dealer.generateHand(card10, card11);
			hand4 = dealer.generateHand(card12, card13);
			hands.add(hand1);
			hands.add(hand2);
			hands.add(hand3);
			hands.add(hand4);
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			winners = HandsComparator.compareHands(hands , community);
			if (winners.get(0).get(0).toString().equals("QueenOfSpades") && 
				winners.get(0).get(1).toString().equals("KingOfHearts") &&
				winners.size() == 1) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void compareHandsSplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
	
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Ace", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		
		Card card3 = new Card("Ace", "Diamonds");
		Card card4 = new Card("King", "Hearts");
		
		Card card10 = new Card("Ace", "Spades");
		Card card11 =  new Card("Ten", "Diamonds");
		
		Card card12 = new Card("Ace", "Hearts");
		Card card13 = new Card("Jack", "Clubs");
		
		Card card5 = new Card("Queen", "Clubs");
		Card card6 = new Card("Two", "Clubs");
		Card card7 = new Card("Queen", "Diamonds");
		Card card8 = new Card("Queen", "Hearts");
		Card card9 = new Card("Queen", "Spades");
		
		try {
			hands.add(dealer.generateHand(card1, card2));
			hands.add(dealer.generateHand(card3, card4));
			hands.add(dealer.generateHand(card10, card11));
			hands.add(dealer.generateHand(card12, card13));
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			winners = HandsComparator.compareHands(hands, community);
			
			if (winners.get(0).get(0).toString().equals("AceOfClubs") && 
				winners.get(0).get(1).toString().equals("KingOfDiamonds") &&
				winners.get(1).get(0).toString().equals("AceOfDiamonds") && 
				winners.get(1).get(1).toString().equals("KingOfHearts") &&
				winners.get(2).get(0).toString().equals("AceOfSpades") && 
				winners.get(2).get(1).toString().equals("TenOfDiamonds") &&
				winners.get(3).get(0).toString().equals("AceOfHearts") && 
				winners.get(3).get(1).toString().equals("JackOfClubs") &&
				winners.size() == 4) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
	
	@Test
	public void compareHandsAllSplit_test() {
		boolean result = false;
		CardDeckDealer dealer = new CardDeckDealer();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> community = new ArrayList<Card>();
		Card card1 = new Card("Nine", "Clubs");
		Card card2 = new Card("King", "Diamonds");
		
		Card card3 = new Card("Ten", "Clubs");
		Card card4 = new Card("King", "Hearts");
		
		Card card5 = new Card("King", "Clubs");
		Card card6 = new Card("Ace", "Clubs");
		Card card7 = new Card("Ace", "Diamonds");
		Card card8 = new Card("Ace", "Hearts");
		Card card9 = new Card("Ace", "Spades");
		
		try {
			hands.add(dealer.generateHand(card1, card2));
			hands.add(dealer.generateHand(card3, card4));
			community = dealer.generateCommunity(card5, card6, card7, card8, card9);
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			hands.add(dealer.generateHand());
			winners = HandsComparator.compareHands(hands, community);
			if (winners.size() == 23) {
				result =  true;
			}
		} catch (CardDeckDealerException | HandsComparatorException | CardDeckException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(result);
	}
}
