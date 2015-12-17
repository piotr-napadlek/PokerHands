package com.capgemini.poker.sequences.sets;

import static com.capgemini.poker.sequences.PokerSequence.*;
import java.util.*;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.sequences.CardSequence;
import com.capgemini.poker.sequences.Sequence;

public class PokerSequenceSet implements CardGameSequenceSet {
	private Sequence primarySequence;
	private Sequence auxillarySequence;
	private SequenceDetector detector = new SequenceDetector();

	public PokerSequenceSet(Collection<Card> cards) {
		if (cards == null || new TreeSet<Card>(cards).size() < 5) {
			throw new IllegalArgumentException("Impossible card combination!");
		}
		detectSequences(cards);
	}

	public Sequence getPrimary() {
		return primarySequence;
	}

	public int getPrimaryStrength() {
		return primarySequence.sequenceStrength();
	}

	private void detectSequences(Collection<Card> cards) {
		primarySequence = detector.detectSequence(cards);
		List<Card> auxillaryCards = new ArrayList<>(cards); //not to mess with original
		auxillaryCards.removeAll(primarySequence.getCardsInSequence());
		auxillarySequence = new CardSequence(HIGH_CARD, new TreeSet<Card>(auxillaryCards));
	}

	@Override
	public int compareToSequenceSet(CardGameSequenceSet other) {
		int primaryCompare = primarySequence.compareToSequence(other.getPrimary());
		return primaryCompare == 0 ? this.getAuxillary().compareToSequence(other.getAuxillary())
				: primaryCompare;
	}

	@Override
	public Sequence getAuxillary() {
		return auxillarySequence;
	}
}