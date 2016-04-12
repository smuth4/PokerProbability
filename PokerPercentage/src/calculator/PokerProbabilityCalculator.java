package calculator;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.CardDeck;
import card.CardDeckDealer;
import card.CardDeckException;
import comparator.HandsComparator;
import comparator.HandsComparatorException;

public class PokerProbabilityCalculator {
	
	private static int THRESHOLD = 205476480;
	private PokerProbabilityCalculator() {}
	
	public static int[] handProbability(ArrayList<Card> hand1, ArrayList<Card> hand2) throws CardDeckException, HandsComparatorException {
		CardDeck deck = new CardDeck();
		ArrayList<ArrayList<Card>> winners = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> community = new ArrayList<Card>();
		community.add(hand1.get(0));
		community.add(hand1.get(0));
		community.add(hand1.get(0));
		community.add(hand1.get(0));
		community.add(hand1.get(0));
		
		hands.add(hand1);
		hands.add(hand2);
		deck.setUnavailable(hand1.get(0));
		deck.setUnavailable(hand1.get(1));
		deck.setUnavailable(hand2.get(0));
		deck.setUnavailable(hand2.get(1));
		
		int[] result =  new int[3];
		
		int split = 0;
		int handOneWins = 0;
		int handTwoWins = 0;
		
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
		while(j++ < 10000000) {
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
					|| winners.get(0).get(0).toString().equals(hand1.get(1).toString())) {
					handOneWins++;
				} else {
					handTwoWins++;
				}
			} else {
				split++;
			}
		}
		/*
		for (Card card1 : deck1) {
			community.set(0, card1);
			System.out.println(++i);
			for (Card card2 : deck2) {
				community.set(1, card2);
				
				for (Card card3 : deck3) {
					community.set(2, card3);
					
					for (Card card4 : deck4) {
						community.set(3, card4);
						
						for (Card card5 : deck5) {
							community.set(4, card5);
							winners = HandsComparator.compareHands(hands, community);
							
							if (winners.size() == 1) {
								if (winners.get(0).get(0).toString().equals(hand1.get(0).toString())
									|| winners.get(0).get(0).toString().equals(hand1.get(1).toString())) {
									handOneWins++;
								} else {
									handTwoWins++;
								}
							} else {
								split++;
							}
						}
					}
				}
			}
		} */
		result[0] = handOneWins;
		result[1] = handTwoWins;
		result[2] = split;
		return result;
	}
}
