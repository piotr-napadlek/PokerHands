package com.capgemini.poker.cards;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.cards.Rank;
import com.capgemini.poker.cards.Suit;

public class CardTest {

	@Test
	public void shouldReturnGreaterThanZero() {
		// given
		Card two = new Card(Rank.TWO, Suit.CLUB);
		Card three = new Card(Rank.THREE, Suit.DIAMOND);
		// when
		int compareResult = three.compareTo(two);
		// then
		assertTrue(compareResult > 0);
	}

	@Test
	public void shouldReturnLessThanZero() {
		// given
		Card ace = new Card(Rank.ACE, Suit.CLUB);
		Card jack = new Card(Rank.JACK, Suit.HEART);
		// when
		int compareResult = jack.compareTo(ace);
		// then
		assertTrue(compareResult < 0);
	}

	@Test
	public void shouldReturnThatSameValueCardsAreNotEqual() {
		// given
		Card firstQueen = new Card(Rank.QUEEN, Suit.DIAMOND);
		Card secondQueen = new Card(Rank.QUEEN, Suit.HEART);
		// when
		int compareResult = secondQueen.compareTo(firstQueen);
		// then
		assertFalse(compareResult == 0);
	}

	@Test
	public void shouldCheckThatAreEqual() {
		// given
		Card firstQueen = new Card(Rank.QUEEN, Suit.SPADE);
		Card secondQueen = new Card(Rank.QUEEN, Suit.SPADE);
		// when
		int isEqual = secondQueen.compareTo(firstQueen);
		// then
		assertEquals(0, isEqual);
	}

	@Test
	public void shouldCheckThatSameValueCardsAreNotEqual() {
		// given
		Card firstQueen = new Card(Rank.QUEEN, Suit.SPADE);
		Card secondQueen = new Card(Rank.QUEEN, Suit.DIAMOND);
		// when
		boolean isEqual = secondQueen.equals(firstQueen);
		// then
		assertFalse(isEqual);
	}

	@Test
	public void shouldCheckThatAreNotEqual() {
		// given
		Card queen = new Card(Rank.QUEEN, Suit.HEART);
		Card king = new Card(Rank.KING, Suit.HEART);
		// when
		boolean isEqual = king.equals(queen);
		// then
		assertFalse(isEqual);
	}
	
	@Test
	public void shouldCorrectlyAddUpToTreeMap(){
		// given
		Set<Card> cardsSet = new TreeSet<Card>();
		cardsSet.add(new Card(Rank.ACE, Suit.DIAMOND));
		cardsSet.add(new Card(Rank.ACE, Suit.CLUB));
		// when
		int setSize = cardsSet.size();
		// then
		int expectedSetSize = 2;
		assertEquals(expectedSetSize, setSize);
	}
	
	@Test
	public void shouldCorrectlyAddUpToTreeMapWhenSame(){
		// given
		Set<Card> cardsSet = new TreeSet<Card>();
		cardsSet.add(new Card(Rank.ACE, Suit.DIAMOND));
		cardsSet.add(new Card(Rank.ACE, Suit.DIAMOND));
		// when
		int setSize = cardsSet.size();
		// then
		int expectedSetSize = 1;
		assertEquals(expectedSetSize, setSize);
	}

}
