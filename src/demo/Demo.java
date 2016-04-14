package demo;

import java.util.ArrayList;

import calculator.PokerProbabilityCalculator;
import card.Card;
import card.CardDeckDealer;
import card.CardDeckDealerException;
import card.CardDeckException;
import comparator.HandsComparatorException;

public class Demo {

	public static void main(String[] args) {
		CardDeckDealer dealer = new CardDeckDealer();
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		try {
			ArrayList<Card> hand1 = dealer.generateHand();
			ArrayList<Card> hand2 = dealer.generateHand();
			hands.add(hand1);
			hands.add(hand2);
			
			int[] result = PokerProbabilityCalculator.handProbability(hands);
			int sum = result[0] + result[1] + result[2];
			System.out.println("****PRE FLOP****");
			System.out.println("Hand1: " + hand1.toString() + " Win probability: " + result[1]/(double)sum);
			System.out.println("Hand2: " + hand2.toString() + " Win probability: " + result[2]/(double)sum);
			System.out.println("Split Probability: " + result[0]/(double)sum);
			
			ArrayList<Card> community = new ArrayList<Card>();
			
			ArrayList<Card> flop = dealer.generateFlop();
			community.addAll(flop);
			result = PokerProbabilityCalculator.handProbability(hands, community);
			sum = result[0] + result[1] + result[2];
			System.out.println();
			System.out.println("****FLOP****");
			System.out.println("Community: " + community.toString());
			System.out.println("Hand1: " + hand1.toString() + " Win probability: " + result[1]/(double)sum);
			System.out.println("Hand2: " + hand2.toString() + " Win probability: " + result[2]/(double)sum);
			System.out.println("Split Probability: " + result[0]/(double)sum);
			
			Card turn = dealer.generateTurn();
			community.add(turn);
			result = PokerProbabilityCalculator.handProbability(hands, community);
			sum = result[0] + result[1] + result[2];
			System.out.println();
			System.out.println("****TURN****");
			System.out.println("Community: " + community.toString());
			System.out.println("Hand1: " + hand1.toString() + " Win probability: " + result[1]/(double)sum);
			System.out.println("Hand2: " + hand2.toString() + " Win probability: " + result[2]/(double)sum);
			System.out.println("Split Probability: " + result[0]/(double)sum);
			
			Card river = dealer.generateRiver();
			community.add(river);
			result = PokerProbabilityCalculator.handProbability(hands, community);
			sum = result[0] + result[1] + result[2];
			System.out.println();
			System.out.println("****RIVER****");
			System.out.println("Community: " + community.toString());
			System.out.println("Hand1: " + hand1.toString() + " Win probability: " + result[1]/(double)sum);
			System.out.println("Hand2: " + hand2.toString() + " Win probability: " + result[2]/(double)sum);
			System.out.println("Split Probability: " + result[0]/(double)sum);
			  
		} catch (CardDeckDealerException | CardDeckException | HandsComparatorException e) {
			  System.out.println(e.getMessage());
			  return;
		}
	}
}
