package test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import calculator.PokerProbabilityCalculator;
import card.Card;
import card.CardDeckDealer;
import card.CardDeckDealerException;
import card.CardDeckException;
import comparator.HandsComparatorException;

public class PokerProbabilityCalculatorTest {
	CardDeckDealer dealer = new CardDeckDealer();

  @Test
  public void handProbability_test() {
	  dealer.shuffle();
	  try {
		  ArrayList<Card> hand1 = dealer.generateHand();
		  ArrayList<Card> hand2 = dealer.generateHand();
		  ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		  hands.add(hand1);
		  hands.add(hand2);
		  int[] result = PokerProbabilityCalculator.handProbability(hands);
		  int sum = result[0] + result[1] + result[2];
		  System.out.println("Hand1: " + hand1.toString() + " " + result[1]/(double)sum);
		  System.out.println("Hand2 " + hand2.toString() + " " + result[2]/(double)sum);
		  System.out.println("Split " + result[0]/(double)sum);
		  Assert.assertTrue(sum == 205476480);
	  } catch (CardDeckDealerException | CardDeckException | HandsComparatorException e) {
		  System.out.println(e.getMessage());
		  return;
	  }
  }
}
