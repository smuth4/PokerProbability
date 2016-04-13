package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import card.Card;

public class HandsComparator {
	
	private HandsComparator() {}
		
	public static ArrayList<ArrayList<Card>> compareHands(ArrayList<ArrayList<Card>> hands, 
												ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<ArrayList<Card>> bestHands = new ArrayList<ArrayList<Card>>();
		if (hands.isEmpty()) {
			return bestHands;
		}
		bestHands.add(hands.get(0));
		String result;
		String[] resultArray;
		
		for (int i = 1; i < hands.size(); i++) {
			result = checkStraightFlushes(bestHands.get(0), hands.get(i), community);
			resultArray = result.split("-");
			if (resultArray[0].equals("split")) {
				bestHands.add(hands.get(i));
			} else if (resultArray[0].equals("hand2")) {
				bestHands.clear();
				bestHands.add(hands.get(i));
			}
		}
		return bestHands;
	}
	
	public static String checkHighCards(ArrayList<Card> hand1,
										ArrayList<Card> hand2, 
										ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		handOneValue = convertToValue(handOneCopy);
		handTwoValue = convertToValue(handTwoCopy);
		handOneValue.sort(Collections.reverseOrder());
		handTwoValue.sort(Collections.reverseOrder());
		for (int i = 0; i < 5; i++) {
			if (handOneValue.get(i) > handTwoValue.get(i)) {
				return "hand1-HighCard";
			} else if (handTwoValue.get(i) > handOneValue.get(i)) {
				return "hand2-HighCard";
			}
		}
		return "split-HighCard";
	}
	
	public static String checkPairs(ArrayList<Card> hand1,
									ArrayList<Card> hand2, 
									ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneValue = convertToValue(handOneCopy);
		handTwoValue = convertToValue(handTwoCopy);
		
		if (hasPair(handOneValue) && hasPair(handTwoValue)) {
			return betterPair(handOneValue, handTwoValue);
		} else if(hasPair(handOneValue)) {
			return "hand1-Pair";
		} else if (hasPair(handTwoValue)) {
			return "hand2-Pair";
		} else {
			return checkHighCards(hand1, hand2, community);
		}
	}
	
	public static String checkTwoPairs(ArrayList<Card> hand1,
										ArrayList<Card> hand2, 
										ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneValue = convertToValue(handOneCopy);
		handTwoValue = convertToValue(handTwoCopy);
		
		if (hasTwoPair(handOneValue) && hasTwoPair(handTwoValue)) {
			return betterTwoPair(handOneValue, handTwoValue) ;
		} else if (hasTwoPair(handOneValue)) {
			return "hand1-TwoPair";
		} else if (hasTwoPair(handTwoValue)) {
			return "hand2-TwoPair";
		} else {
			return checkPairs(hand1, hand2, community);
		}
	}
	
	public static String checkThreeOfAKinds(ArrayList<Card> hand1,
											ArrayList<Card> hand2, 
											ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneValue = convertToValue(handOneCopy);
		handTwoValue = convertToValue(handTwoCopy);
		
		if (hasThreeOfAKind(handOneValue) && hasThreeOfAKind(handTwoValue)) {
			return betterThreeOfAKind(handOneValue, handTwoValue);
		} else if (hasThreeOfAKind(handOneValue)){
			return "hand1-ThreeOfAKind";
		} else if (hasThreeOfAKind(handTwoValue)){
			return "hand2-ThreeOfAKind";
		} else {
			return checkTwoPairs(hand1, hand2, community);
		}
	}
	
	public static String checkStraights(ArrayList<Card> hand1,
											ArrayList<Card> hand2, 
											ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneValue = convertStraightToValue(handOneCopy);
		handTwoValue = convertStraightToValue(handTwoCopy);
		
		if (hasStraight(handOneValue) && hasStraight(handTwoValue)) {
			return betterStraight(handOneValue, handTwoValue);
		} else if (hasStraight(handOneValue)) {
			return "hand1-Straight";
		} else if (hasStraight(handTwoValue)) {
			return "hand2-Straight";
		} else {
			return checkThreeOfAKinds(hand1, hand2, community);
		}
	}
	
	public static String checkFlushes(ArrayList<Card> hand1,
										ArrayList<Card> hand2, 
										ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Card> handOneFlush = new ArrayList<Card>();
		ArrayList<Card> handTwoFlush = new ArrayList<Card>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneFlush = filterFlush(handOneCopy);
		handTwoFlush = filterFlush(handTwoCopy);
		
		if (hasFlush(handOneFlush) && hasFlush(handTwoFlush)) {
			return higherFlush(handOneFlush, handTwoFlush);
		} else if (hasFlush(handOneFlush)) {
			return "hand1-Flush";
		} else if (hasFlush(handTwoFlush)) {
			return "hand2-Flush";
		} else {
			return checkStraights(hand1, hand2, community);
		}
	}
	
	public static String checkFullHouses( ArrayList<Card> hand1,
										ArrayList<Card> hand2, 
										ArrayList<Card> community) throws HandsComparatorException { 
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneValue = convertToValue(handOneCopy);
		handTwoValue = convertToValue(handTwoCopy);
		
		if (hasFullHouse(handOneValue) && hasFullHouse(handTwoValue)) {
			return betterFullHouse(handOneValue, handTwoValue);
		} else if (hasFullHouse(handOneValue)){
			return "hand1-FullHouse";
		} else if (hasFullHouse(handTwoValue)){
			return "hand2-FullHouse";
		} else {
			return checkFlushes(hand1, hand2, community);
		}
	}
	
	public static String checkFourOfAKinds( ArrayList<Card> hand1,
											ArrayList<Card> hand2, 
											ArrayList<Card> community) throws HandsComparatorException { 
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneValue = convertToValue(handOneCopy);
		handTwoValue = convertToValue(handTwoCopy);
		
		if (hasFourOfAKind(handOneValue) && hasFourOfAKind(handTwoValue)) {
			return betterFourOfAKind(handOneValue, handTwoValue);
		} else if (hasFourOfAKind(handOneValue)){
			return "hand1-FourOfAKind";
		} else if (hasFourOfAKind(handTwoValue)){
			return "hand2-FourOfAKind";
		} else {
			return checkFullHouses(hand1, hand2, community);
		}
	}
	
	public static String checkStraightFlushes(	ArrayList<Card> hand1,
												ArrayList<Card> hand2, 
												ArrayList<Card> community) throws HandsComparatorException {
		ArrayList<Card> handOneCopy = new ArrayList<Card>();
		ArrayList<Card> handTwoCopy = new ArrayList<Card>();
		ArrayList<Card> handOneFlush = new ArrayList<Card>();
		ArrayList<Card> handTwoFlush = new ArrayList<Card>();
		
		handOneCopy.addAll(hand1);
		handTwoCopy.addAll(hand2);
		handOneCopy.addAll(community);
		handTwoCopy.addAll(community);
		
		handOneFlush = filterFlush(handOneCopy);
		handTwoFlush = filterFlush(handTwoCopy);
		
		if (hasStraightFlush(handOneFlush) && hasStraightFlush(handTwoFlush) ) {
			return betterStraightFlush(handOneFlush, handTwoFlush);
		} else if (hasStraightFlush(handOneFlush)){
			return "hand1-StraightFlush";
		} else if (hasStraightFlush(handTwoFlush)){
			return "hand2-StraightFlush";
		} else {
			return checkFourOfAKinds(hand1, hand2, community);
		}
	}
	
	private static String betterStraightFlush(ArrayList<Card> hand1,
												ArrayList<Card> hand2) throws HandsComparatorException {
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		String result;
		
		handOneValue = convertToValue(hand1);
		handTwoValue = convertToValue(hand2);
		
		result = betterStraight(handOneValue, handTwoValue);
		
		if (result.equals("hand1-Straight")) {
			return "hand1-StraightFlush";
		} else if (result.equals("hand2-Straight")) {
			return "hand2-StraightFlush";
		} else {
			return "split-StraightFlush";
		}
	}

	private static boolean hasStraightFlush(ArrayList<Card> handFlush) throws HandsComparatorException {
		if (hasFlush(handFlush)) {
			ArrayList<Integer> handFlushValue = new ArrayList<Integer>();
			handFlushValue = convertToValue(handFlush);
			if (hasStraight(handFlushValue)) {
				return true;
			}
		}
		return false;
	}

	private static String betterFourOfAKind(ArrayList<Integer> handOneValue,
											ArrayList<Integer> handTwoValue) {
		if (highestFourOfAKind(handOneValue) == highestFourOfAKind(handTwoValue)) {
			return _betterFourOfAKindKicker(handOneValue, handTwoValue);
		} else if (highestFourOfAKind(handOneValue) > highestFourOfAKind(handTwoValue)) {
			return "hand1-FourOfAKind";
		} else {
			return "hand2-FourOfAKind";
		}
	}

	private static String _betterFourOfAKindKicker(ArrayList<Integer> handOneValue, ArrayList<Integer> handTwoValue) {
		ArrayList<Integer> handOneCopy = new ArrayList<Integer>() ;
		ArrayList<Integer> handTwoCopy = new ArrayList<Integer>();
		handOneCopy.addAll(handOneValue);
		handTwoCopy.addAll(handTwoValue);
		
		removeHighestFourOfAKind(handOneCopy);
		removeHighestFourOfAKind(handTwoCopy);
		
		if (handOneCopy.get(0) > handTwoCopy.get(0)) {
				return "hand1-FourOfAKind";
		} else if (handTwoCopy.get(0) > handOneCopy.get(0)) {
				return "hand2-FourOfAKind";
		}
		return "split-FourOfAKind";
	}
	
	private static ArrayList<Integer> removeHighestFourOfAKind( ArrayList<Integer> handCopy) {
		handCopy.sort(Collections.reverseOrder());
		for (int i = 0; i <= handCopy.size() - 3; i++) {
			if (handCopy.get(i) == handCopy.get(i+1) 
					&& handCopy.get(i) == handCopy.get(i+2) 
					&& handCopy.get(i) == handCopy.get(i+3)) {
				handCopy.remove(i);
				handCopy.remove(i);
				handCopy.remove(i);
				handCopy.remove(i);
				break;
			}
		}
		return handCopy;
	}

	private static Integer highestFourOfAKind(ArrayList<Integer> hand) {
		hand.sort(Collections.reverseOrder());
		for (int i = 0; i <= hand.size() - 4 ; i++) {
			if ( hand.get(i) == hand.get(i+1) 
					&& hand.get(i) == hand.get(i+2) 
					&& hand.get(i) == hand.get(i+3)) {
				return hand.get(i);
			}
		}
		//throw exception
		return 0;
	}

	private static boolean hasFourOfAKind(ArrayList<Integer> hand) {
		hand.sort(Collections.reverseOrder());
		for (int i = 0; i <= hand.size() - 4 ; i++) {
			if ( hand.get(i) == hand.get(i+1) && hand.get(i) == hand.get(i+2) && hand.get(i) == hand.get(i+3)) {
				return true;
			}
		}
		return false;
	}

	private static String betterFullHouse(ArrayList<Integer> handOneValue,
											ArrayList<Integer> handTwoValue) {
		String threesResult = new String();
		String pairResult = new String();
		threesResult = betterThreeOfAKind(handOneValue, handTwoValue);
		if (threesResult.equals("split-ThreeOfAKind")) {
			pairResult = betterPair(handOneValue,handTwoValue);
			if (pairResult.equals("split-Pair")) {
				return "split-FullHouse";
			} else if (pairResult.equals("hand1-Pair")) {
				return "hand1-FullHouse";
			} else {
				return "hand2-FullHouse";
			}
		} else if (threesResult.equals("hand1-ThreeOfAKind")) {
			return "hand1-FullHouse";
		} else {
			return "hand2-FullHouse";
		}
	}

	private static boolean hasFullHouse(ArrayList<Integer> hand) {
		ArrayList<Integer> handCopy = new ArrayList<Integer>();
		handCopy.addAll(hand);
		
		if (hasThreeOfAKind(handCopy)) {
			removeHighestThreeOfAKind(handCopy);
			if(hasPair(handCopy)) {
				return true;
			}
		}
		return false;
	}

	private static String higherFlush(ArrayList<Card> hand1,
										ArrayList<Card> hand2) throws HandsComparatorException {
		ArrayList<Integer> handOneValue = new ArrayList<Integer>();
		ArrayList<Integer> handTwoValue = new ArrayList<Integer>();
		handOneValue = convertToValue(hand1);
		handTwoValue = convertToValue(hand2);
		handOneValue.sort(Collections.reverseOrder());
		handTwoValue.sort(Collections.reverseOrder());
		for (int i = 0; i < 5; i++) {
			if (handOneValue.get(i) > handTwoValue.get(i)) {
				return "hand1-Flush";
			} else if (handTwoValue.get(i) > handOneValue.get(i)) {
				return "hand2-Flush";
			} 
		}
		
		return "split-Flush";	
		
	}

	private static boolean hasFlush(ArrayList<Card> hand) {
		//this doesn't fit in!
		if (hand.size() < 5) {
			return false;
		}
		return true;
	}

	private static ArrayList<Card> filterFlush(ArrayList<Card> cards) throws HandsComparatorException {
		ArrayList<Card> spades = new ArrayList<Card>();
		ArrayList<Card> clubs = new ArrayList<Card>();
		ArrayList<Card> diamonds = new ArrayList<Card>();
		ArrayList<Card> hearts = new ArrayList<Card>();
		for (Card card : cards) {
			String suit = card.getSuit();
			switch (suit) {
				
			case "Clubs":
				clubs.add(card);
				break;
			case "Spades":
				spades.add(card);
				break;
			case "Hearts":
				hearts.add(card);
				break;
			case "Diamonds":
				diamonds.add(card);
				break;

			default:
				throw new HandsComparatorException("Ooops, can't convert that suffix to a value: " + suit);			
			}
		}
		
		if (clubs.size() >= 5) {
			return clubs;
		} else if (spades.size() >= 5) {
			return spades;
		} else if (hearts.size() >= 5) {
			return hearts;
		} 		
		return diamonds;
	}

	private static String betterStraight(ArrayList<Integer> handOneValue,
											ArrayList<Integer> handTwoValue) {
		if (startOfStraight(handOneValue) > startOfStraight(handTwoValue)) {
			return "hand1-Straight";
		} else if (startOfStraight(handTwoValue) > startOfStraight(handOneValue) ) {
			return "hand2-Straight";
		} else {
			return "split-Straight";
		}
	}

	private static int startOfStraight(ArrayList<Integer> hand) {
		hand.sort(Collections.reverseOrder());
		int counter = 0;
		for (int i = 0; i <= hand.size() - 2 ; i++) {
			if (hand.get(i) == hand.get(i+1) + 1) {
				counter++;
				if (counter == 4) {
					return hand.get(i-3);
				}
			} else {
				counter = 0;
			}
		}
		//throw exception
		return 999;
	}

	private static ArrayList<Integer> convertStraightToValue(ArrayList<Card> cards) throws HandsComparatorException {
		ArrayList<String> ranks = new ArrayList<String>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Card card : cards) {
			String rank = card.getRank();
			if (ranks.contains(rank)) {
				continue;
			}
			ranks.add(rank);	
		
			switch (rank) {
			case "Two":
				result.add(2);
				break;
			case "Three":
				result.add(3);
				break;
			case "Four":
				result.add(4);
				break;
			case "Five":
				result.add(5);
				break;
			case "Six":
				result.add(6);
				break;
			case "Seven":
				result.add(7);
				break;
			case "Eight":
				result.add(8);
				break;
			case "Nine":
				result.add(9);
				break;
			case "Ten":
				result.add(10);
				break;
			case "Jack":
				result.add(11);
				break;
			case "Queen":
				result.add(12);
				break;
			case "King":
				result.add(13);
				break;
			case "Ace":
				result.add(14);
				result.add(1);
				break;
			default:
				throw new HandsComparatorException("Cannot be converted to value: " + rank);		
			}
		}
		return result;
	}

	private static boolean hasStraight(ArrayList<Integer> hand) {
		hand.sort(Collections.reverseOrder());
		int counter = 0;
		for (int i = 0; i <= hand.size() - 2 ; i++) {
			if (hand.get(i) == hand.get(i+1) + 1) {
				counter++;
				if (counter == 4) {
					return true;
				}
			} else {
				counter = 0;
			}
		}
		return false;
	}

	private static String betterThreeOfAKind(ArrayList<Integer> handOneValue,
												ArrayList<Integer> handTwoValue) {
		if (highestThreeOfAKind(handOneValue) == highestThreeOfAKind(handTwoValue)) {
			return betterThreeOfAKindKicker(handOneValue, handTwoValue);
		} else if (highestThreeOfAKind(handOneValue) > highestThreeOfAKind(handTwoValue)) {
			return "hand1-ThreeOfAKind";
		} else {
			return "hand2-ThreeOfAKind";
		}
	}
	

	private static String betterThreeOfAKindKicker(ArrayList<Integer> handOneValue, 
													ArrayList<Integer> handTwoValue) {
		ArrayList<Integer> handOneCopy = new ArrayList<Integer>() ;
		ArrayList<Integer> handTwoCopy = new ArrayList<Integer>();
		handOneCopy.addAll(handOneValue);
		handTwoCopy.addAll(handTwoValue);
		
		removeHighestThreeOfAKind(handOneCopy);
		removeHighestThreeOfAKind(handTwoCopy);
		
		for (int i = 0; i <=2 ; i++) {
			if (handOneCopy.get(i) > handTwoCopy.get(i)) {
				return "hand1-ThreeOfAKind";
			} else if (handTwoCopy.get(i) > handOneCopy.get(i)) {
				return "hand2-ThreeOfAKind";
			}
		}
		return "split-ThreeOfAKind";
	}
	
	private static ArrayList<Integer> removeHighestThreeOfAKind( ArrayList<Integer> handCopy) {
		handCopy.sort(Collections.reverseOrder());
		for (int i = 0; i <= handCopy.size() - 3; i++) {
			if (handCopy.get(i) == handCopy.get(i+1) && handCopy.get(i) == handCopy.get(i+2)) {
				handCopy.remove(i);
				handCopy.remove(i);
				handCopy.remove(i);
				break;
			}
		}
		return handCopy;
	}

	private static int highestThreeOfAKind(ArrayList<Integer> hand) {
		hand.sort(Collections.reverseOrder());
		for (int i = 0; i <= hand.size() - 3 ; i++) {
			if ( hand.get(i) == hand.get(i+1) && hand.get(i) == hand.get(i+2)) {
				return hand.get(i);
			}
		}
		// throw exception
		return 0;
	}

	private static boolean hasThreeOfAKind(ArrayList<Integer> hand) {
		hand.sort(Collections.reverseOrder());
		for (int i = 0; i <= hand.size() - 3 ; i++) {
			if ( hand.get(i) == hand.get(i+1) && hand.get(i) == hand.get(i+2)) {
				return true;
			}
		}
		return false;
	}

	private static String betterTwoPair(ArrayList<Integer> handOneValue,
										ArrayList<Integer> handTwoValue) {
		if (highestPair(handOneValue) > highestPair(handTwoValue) ) {
			return "hand1-TwoPair";
		} else if (highestPair(handTwoValue) > highestPair(handOneValue)){
			return "hand2-TwoPair";
		} else {
			return highestSecondPair(handOneValue, handTwoValue);
		}

	}

	private static String highestSecondPair(ArrayList<Integer> handOneValue,
											ArrayList<Integer> handTwoValue) {
		ArrayList<Integer> handOneCopy = new ArrayList<Integer>();
		ArrayList<Integer> handTwoCopy = new ArrayList<Integer>();
		String result = new String();
		handOneCopy.addAll(handOneValue);
		handTwoCopy.addAll(handTwoValue);
		
		handOneCopy = removeHighestPair(handOneCopy);
		handTwoCopy = removeHighestPair(handTwoCopy);
		result = betterPair(handOneCopy, handTwoCopy);
		if (result.equals("hand1-Pair")) {
			return "hand1-TwoPair";
		} else if (result.equals("hand2-Pair")) {
			return "hand2-TwoPair";
		} else if (result.equals("split-Pair")) {
			return "split-TwoPair";
		} else {
			// throw exception
			return "That's no good!";
		}
	}

	private static boolean hasTwoPair(ArrayList<Integer> hand) {
		Set<Integer> mySet =  new HashSet<Integer>(hand);
		if (hand.size() == mySet.size() + 2) {
			return true;
		}
		return false;
	}

	private static String betterPair(ArrayList<Integer> handOneValue,
										ArrayList<Integer> handTwoValue) {
		int handOnePair = highestPair(handOneValue);
		int handTwoPair = highestPair(handTwoValue);
		if (handOnePair == handTwoPair) {
			return betterPairKicker(handOneValue, handTwoValue);
		} else if (handOnePair > handTwoPair) {
			return "hand1-Pair";
		} else {
			return "hand2-Pair";
		}
	}

	private static String betterPairKicker(ArrayList<Integer> handOneValue,
										ArrayList<Integer> handTwoValue) {
		ArrayList<Integer> handOneCopy = new ArrayList<Integer>();
		ArrayList<Integer> handTwoCopy = new ArrayList<Integer>();
		handOneCopy.addAll(handOneValue);
		handTwoCopy.addAll(handTwoValue);
		
		handOneCopy = removeHighestPair(handOneCopy);
		handTwoCopy = removeHighestPair(handTwoCopy);
		
		for (int i = 0; i < handOneCopy.size() - 2; i++) {
			if (handOneCopy.get(i) > handTwoCopy.get(i)) {
				return "hand1-Pair";
			} else if (handTwoCopy.get(i) > handOneCopy.get(i)) {
				return "hand2-Pair";
			}
		}
		return "split-Pair";
	}

	private static ArrayList<Integer> removeHighestPair( ArrayList<Integer> handCopy) {
		for (int i = 0; i <= handCopy.size() - 2; i++) {
			if (handCopy.get(i) == handCopy.get(i+1)) {
				handCopy.remove(i);
				handCopy.remove(i);
				break;
			}
		}
		return handCopy;
	}

	private static int highestPair(ArrayList<Integer> handValue) {
		handValue.sort(Collections.reverseOrder());
		for (int i = 0; i <= handValue.size() - 2; i++) {
			if (handValue.get(i) == handValue.get(i+1)) {
				return handValue.get(i);
			}
		}
		//throw exception?
		return 999;
	}

	private static boolean hasPair(ArrayList<Integer> hand) {
		Set<Integer> mySet =  new HashSet<Integer>(hand);
		if (hand.size() == mySet.size() + 1) {
			return true;
		}
		return false;
	}

	private static ArrayList<Integer> convertToValue(ArrayList<Card> cards) throws HandsComparatorException {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Card card : cards) {
			String rank = card.getRank();
			switch (rank) {
			case "Two":
				result.add(2);
				break;
			case "Three":
				result.add(3);
				break;
			case "Four":
				result.add(4);
				break;
			case "Five":
				result.add(5);
				break;
			case "Six":
				result.add(6);
				break;
			case "Seven":
				result.add(7);
				break;
			case "Eight":
				result.add(8);
				break;
			case "Nine":
				result.add(9);
				break;
			case "Ten":
				result.add(10);
				break;
			case "Jack":
				result.add(11);
				break;
			case "Queen":
				result.add(12);
				break;
			case "King":
				result.add(13);
				break;
			case "Ace":
				result.add(14);
				break;
			default:
				throw new HandsComparatorException("Cannot be converted to value: " + rank);
			}
		}
		return result;
	}
}
