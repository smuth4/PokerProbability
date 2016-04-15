package calculator;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.CardDeck;
import card.CardDeckException;
import comparator.HandsComparator;
import comparator.HandsComparatorException;

public class PokerProbabilityCalculator {
	
	private PokerProbabilityCalculator() {}
	
	public static int[] handProbability(ArrayList<ArrayList<Card>> hands) throws CardDeckException, HandsComparatorException {
		CardDeck deck = new CardDeck();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> community = new ArrayList<Card>();
		
		for (ArrayList<Card> hand : hands) {
			for (Card card : hand) {
				deck.setUnavailable(card);
			}
		}
		
		ArrayList<Card> hand1 = hands.get(0);
		
		//result[split, hand1, hand2, ...]
		int[] result =  new int[hands.size() + 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		
		ArrayList<Card> deck1 = deck.getAllAvailable();
		deck.setUnavailable(deck1.get(0));
		ArrayList<Card> deck2 = deck.getAllAvailable();
		deck.setUnavailable(deck2.get(0));
		ArrayList<Card> deck3 = deck.getAllAvailable();
		deck.setUnavailable(deck3.get(0));
		ArrayList<Card> deck4 = deck.getAllAvailable();
		deck.setUnavailable(deck4.get(0));
		ArrayList<Card> deck5 = deck.getAllAvailable();
		
		int i = 0;
		int j = 0;
		Random rand = new Random();
		while(j++ < 99999) {
			i = rand.nextInt(j);
			community.clear();
			community.add(deck1.get(i%(deck1.size()-1)));
			community.add(deck2.get(i%(deck2.size()-1)));
			community.add(deck3.get(i%(deck3.size()-1)));
			community.add(deck4.get(i%(deck4.size()-1)));
			community.add(deck5.get(i%(deck5.size()-1)));
			winners = HandsComparator.compareHands(hands, community);
			
			if (winners.size() == 1) {
				if (winners.get(0).get(0).toString().equals(hand1.get(0).toString())
					|| winners.get(0).get(0).toString().equals(hand1.get(1).toString())
					|| winners.get(0).get(1).toString().equals(hand1.get(0).toString())
					|| winners.get(0).get(1).toString().equals(hand1.get(1).toString())) {
					result[1]++;
				} else {
					result[2]++;
				}
			} else {
				result[0]++;
			}
		}
		return result;
	}
	
	public static int[] handProbability(ArrayList<ArrayList<Card>> hands, ArrayList<Card> inCommunity) throws CardDeckException, HandsComparatorException {
		CardDeck deck = new CardDeck();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> community = new ArrayList<Card>();
		
		for (ArrayList<Card> hand : hands) {
			for (Card card : hand) {
				deck.setUnavailable(card);
			}
		}
		
		ArrayList<Card> hand1 = hands.get(0);
		
		//result[split, hand1, hand2, ...]
		int[] result =  new int[hands.size() + 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		
		ArrayList<Card> deck1 = deck.getAllAvailable();
		deck.setUnavailable(deck1.get(0));
		ArrayList<Card> deck2 = deck.getAllAvailable();
		deck.setUnavailable(deck2.get(0));
		ArrayList<Card> deck3 = deck.getAllAvailable();
		deck.setUnavailable(deck3.get(0));
		ArrayList<Card> deck4 = deck.getAllAvailable();
		deck.setUnavailable(deck4.get(0));
		ArrayList<Card> deck5 = deck.getAllAvailable();
		
		int i = 0;
		int j = 0;
		Random rand = new Random();
		while(j++ < 2000) {
			i = rand.nextInt(j);
			community.clear();
			for (Card card : inCommunity) {
				community.add(card);
			}
			if (community.size() == 3) {
				community.add(deck4.get(i%(deck4.size()-1)));
			} 
			if (community.size() == 4) {
				community.add(deck5.get(i%(deck5.size()-1)));	
			}
			
			winners = HandsComparator.compareHands(hands, community);
			
			if (winners.size() == 1) {
				if (winners.get(0).get(0).toString().equals(hand1.get(0).toString())
					|| winners.get(0).get(0).toString().equals(hand1.get(1).toString())) {
					result[1]++;
				} else {
					result[2]++;
				}
			} else {
				result[0]++;
			}
		}
		return result;
	}
}
