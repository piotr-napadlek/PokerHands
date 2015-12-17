package com.capgemini.poker.helpers;

import static com.capgemini.poker.cards.Rank.*;
import static com.capgemini.poker.cards.Suit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.cards.Rank;
import com.capgemini.poker.cards.Suit;
import com.capgemini.poker.helpers.SequenceHelper;

public class SequenceHelperTest {

	@Test
	public void shouldFindSequencePair() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.DIAMOND));
		cards.add(new Card(Rank.QUEEN, Suit.HEART));
		cards.add(new Card(Rank.TWO, Suit.SPADE));
		cards.add(new Card(Rank.TEN, Suit.CLUB));
		// when
		List<Card> expectedCards = new ArrayList<>();
		expectedCards.add(new Card(Rank.TEN, Suit.DIAMOND));
		expectedCards.add(new Card(Rank.TEN, Suit.CLUB));
		Collection<Card> foundCards = SequenceHelper.findRepeats(cards,
				Card::getRank, 2);
		// then
		assertEquals(expectedCards, foundCards);
	}

	@Test
	public void shouldFindSequenceThreeOfAKind() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.HEART));
		cards.add(new Card(Rank.TWO, Suit.SPADE));
		cards.add(new Card(Rank.TEN, Suit.CLUB));
		// when
		List<Card> expectedCards = new ArrayList<>();
		expectedCards.add(new Card(Rank.TEN, Suit.DIAMOND));
		expectedCards.add(new Card(Rank.TEN, Suit.HEART));
		expectedCards.add(new Card(Rank.TEN, Suit.CLUB));
		Collection<Card> foundCards = SequenceHelper.findRepeats(cards,
				Card::getRank, 3);
		// then
		assertEquals(expectedCards, foundCards);
	}

	@Test
	public void shouldFindNoSequence() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.HEART));
		cards.add(new Card(Rank.TWO, Suit.SPADE));
		cards.add(new Card(Rank.TEN, Suit.CLUB));
		// when
		List<Card> expectedCards = new ArrayList<>();
		Collection<Card> foundCards = SequenceHelper.findRepeats(cards,
				Card::getRank, 4);
		// then
		assertEquals(expectedCards, foundCards);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenNullParameterGiven() {
		// given
		SequenceHelper.findRepeats(null, Card::getRank, 0);
	}

	@Test
	public void shouldDetectFlush() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.EIGHT, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.DIAMOND));
		cards.add(new Card(Rank.JACK, Suit.DIAMOND));
		cards.add(new Card(Rank.ACE, Suit.DIAMOND));
		cards.add(new Card(Rank.TWO, Suit.DIAMOND));
		// when
		Collection<Card> foundCards = SequenceHelper.findRepeats(cards, Card::getSuit, 5);
		// then
		assertEquals(cards, foundCards);
	}
	
	@Test
	public void shouldFindStraight() {
		//given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.SIX, Suit.HEART));
		cards.add(new Card(Rank.THREE, Suit.DIAMOND));
		cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
		cards.add(new Card(Rank.SEVEN, Suit.CLUB));
		cards.add(new Card(Rank.FOUR, Suit.SPADE));
		//when
		boolean straight = SequenceHelper.isMonotonicByOne(cards, c -> c.getRank().asInt());
		//then
		assertTrue(straight);
	}
	
	@Test
	public void shouldFindStraightWithHighAce() {
		//given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.ACE, Suit.HEART));
		cards.add(new Card(Rank.QUEEN, Suit.SPADE));
		cards.add(new Card(Rank.KING, Suit.DIAMOND));
		cards.add(new Card(Rank.TEN, Suit.CLUB));
		cards.add(new Card(Rank.JACK, Suit.SPADE));
		//when
		boolean straight = SequenceHelper.isMonotonicByOne(cards,  c -> c.getRank().asInt());
		//then
		assertTrue(straight);
	}
	
	@Test
	public void shouldFindStraightWithLowAce() {
		//given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.ACE, Suit.HEART));
		cards.add(new Card(Rank.TWO, Suit.SPADE));
		cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
		cards.add(new Card(Rank.FOUR, Suit.CLUB));
		cards.add(new Card(Rank.THREE, Suit.SPADE));
		//when
		boolean straight = SequenceHelper.isMonotonicByOne(cards,  c -> c.getRank().asInt()) 
				|| SequenceHelper.isMonotonicByOne(
						CardHelper.reevaluateAceInSequence(cards), c-> c.getRank().asInt());
		//then
		assertTrue(straight);
	}
	@Test
	public void shouldNotFindStraight() {
		//given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Rank.EIGHT, Suit.HEART));
		cards.add(new Card(Rank.SIX, Suit.SPADE));
		cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
		cards.add(new Card(Rank.FOUR, Suit.CLUB));
		cards.add(new Card(Rank.THREE, Suit.SPADE));
		//when
		boolean straight = SequenceHelper.isMonotonicByOne(cards,  c -> c.getRank().asInt());
		
		//then
		assertFalse(straight);
	}
	
	@Test
	public void shouldFindStraightEvenWhenIncomplete() {
		//given
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(SIX, SPADE));
		cards.add(new Card(FIVE, DIAMOND));
		cards.add(new Card(FOUR, CLUB));
		cards.add(new Card(THREE, SPADE));
		//when
		boolean straight = SequenceHelper.isMonotonicByOne(cards,  c -> c.getRank().asInt());
		//then
		assertTrue(straight);
	}
	
	@Test
	public void shouldDetectPair() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(FIVE, CLUB));
		cards.add(new Card(SIX, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		boolean hasPair = SequenceHelper.hasRepeats(cards, Card::getRank, 2);
		// then
		assertTrue(hasPair);
	}
	
	@Test
	public void shouldDetectThree() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(FIVE, CLUB));
		cards.add(new Card(TWO, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		boolean hasThree = SequenceHelper.hasRepeats(cards, Card::getRank, 3);
		// then
		assertTrue(hasThree);
	}
	
	@Test
	public void shouldDetectTwoPairs() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(ACE, DIAMOND));
		cards.add(new Card(TEN, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		boolean hasTwoPairs = SequenceHelper.findRepeats(cards, Card::getRank, 2).size() == 4;
		// then
		assertTrue(hasTwoPairs);
	}
}
