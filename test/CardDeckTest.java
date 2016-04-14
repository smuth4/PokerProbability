package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import card.Card;
import card.CardDeck;

public class CardDeckTest {

	@Test
	public void getDeck_Test() {
		CardDeck CardDeck = new CardDeck();
		ArrayList<Card> deck = CardDeck.getDeck();
		Assert.assertTrue(deck.size() == 52);
	}
	
	@Test
	public void getAllAvailable_Test() {
		CardDeck CardDeck = new CardDeck();
		ArrayList<Card> deck = CardDeck.getAllAvailable();
		Assert.assertTrue(deck.size() == 52);
	}

	@Test
	public void getAllAvailableUpdated_Test() {
		CardDeck CardDeck = new CardDeck();
		ArrayList<Card> deck = CardDeck.getAllAvailable();
		deck.get(0).setUnavailable();
		deck = CardDeck.getAllAvailable();
		Assert.assertTrue(deck.size() == 51);
	}
}
