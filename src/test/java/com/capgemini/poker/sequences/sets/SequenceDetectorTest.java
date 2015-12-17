package com.capgemini.poker.sequences.sets;

import static org.junit.Assert.*;

import java.util.*;

import static com.capgemini.poker.cards.Rank.*;
import static com.capgemini.poker.cards.Suit.*;
import static com.capgemini.poker.sequences.PokerSequence.*;

import org.junit.Test;

import com.capgemini.poker.cards.*;
import com.capgemini.poker.sequences.Sequence;
import com.capgemini.poker.sequences.sets.SequenceDetector;

public class SequenceDetectorTest {
	private SequenceDetector detector = new SequenceDetector();
	
	@Test
	public void shouldDetectHighCard() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(FIVE, CLUB));
		cards.add(new Card(FOUR, CLUB));
		cards.add(new Card(JACK, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(HIGH_CARD, detected.getSequence());
	}
	
	@Test
	public void shouldDetectPair() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(FIVE, CLUB));
		cards.add(new Card(THREE, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(PAIR, detected.getSequence());
	}
	
	@Test
	public void shouldDetectThree() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(TWO, DIAMOND));
		cards.add(new Card(FIVE, CLUB));
		cards.add(new Card(THREE, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(THREE_OF_A_KIND, detected.getSequence());
	}
	
	@Test
	public void shouldDetectFour() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(TWO, DIAMOND));
		cards.add(new Card(TWO, HEART));
		cards.add(new Card(THREE, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(FOUR_OF_A_KIND, detected.getSequence());
	}
	
	@Test
	public void shouldDetectFullHouse() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(ACE, DIAMOND));
		cards.add(new Card(ACE, HEART));
		cards.add(new Card(TWO, CLUB));
		cards.add(new Card(TWO, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(FULL_HOUSE, detected.getSequence());
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
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(TWO_PAIRS, detected.getSequence());
	}
	
	@Test
	public void shouldDetectStraightWithLowAce() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(TWO, DIAMOND));
		cards.add(new Card(THREE, HEART));
		cards.add(new Card(FOUR, CLUB));
		cards.add(new Card(FIVE, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(STRAIGHT, detected.getSequence());
	}
	
	@Test
	public void shouldDetectAnotherStraight() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(SIX, CLUB));
		cards.add(new Card(TWO, DIAMOND));
		cards.add(new Card(THREE, HEART));
		cards.add(new Card(FOUR, CLUB));
		cards.add(new Card(FIVE, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(STRAIGHT, detected.getSequence());
	}
	
	@Test
	public void shouldDetectStraightWithHighAce() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(TEN, CLUB));
		cards.add(new Card(JACK, DIAMOND));
		cards.add(new Card(ACE, HEART));
		cards.add(new Card(KING, CLUB));
		cards.add(new Card(QUEEN, SPADE));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(STRAIGHT, detected.getSequence());
	}
	
	@Test
	public void shouldDetectFlush() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(SIX, CLUB));
		cards.add(new Card(JACK, CLUB));
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(KING, CLUB));
		cards.add(new Card(QUEEN, CLUB));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(FLUSH, detected.getSequence());
	}
	
	@Test
	public void StraightFlush() {
		// given
		SortedSet<Card> cards = new TreeSet<Card>();
		cards.add(new Card(TEN, CLUB));
		cards.add(new Card(JACK, CLUB));
		cards.add(new Card(ACE, CLUB));
		cards.add(new Card(KING, CLUB));
		cards.add(new Card(QUEEN, CLUB));
		// when
		Sequence detected = detector.detectSequence(cards);
		// then
		assertEquals(STRAIGHT_FLUSH, detected.getSequence());
	}
}