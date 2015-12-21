package com.capgemini.poker.sequences.sets;

import static com.capgemini.poker.sequences.PokerSequence.FLUSH;
import static com.capgemini.poker.sequences.PokerSequence.FOUR_OF_A_KIND;
import static com.capgemini.poker.sequences.PokerSequence.PAIR;
import static com.capgemini.poker.sequences.PokerSequence.STRAIGHT;
import static com.capgemini.poker.sequences.PokerSequence.THREE_OF_A_KIND;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import com.capgemini.poker.helpers.CardHelper;
import com.capgemini.poker.helpers.SequenceHelper;
import com.capgemini.poker.cards.Card;
import com.capgemini.poker.sequences.CardSequence;
import com.capgemini.poker.sequences.PokerSequence;
import com.capgemini.poker.sequences.Sequence;
import com.capgemini.poker.sequences.specific.FullHouseSequence;
import com.capgemini.poker.sequences.specific.StraightSequence;

public class SequenceDetector {
	private SortedSet<Card> cardsProcessed = new TreeSet<Card>();
	
	public Sequence detectSequence(Collection<Card> cards) {
		PokerSequence detected = PokerSequence.fromMask(getSequenceCombinationMask(cards));
		Sequence resultingSequence;
		
		switch (detected) {
		case STRAIGHT_FLUSH:
		case STRAIGHT:
			resultingSequence = new StraightSequence(detected, 
					new TreeSet<Card>(cardsProcessed));
			break;
		case FULL_HOUSE:
			resultingSequence = new FullHouseSequence(detected, cardsProcessed);
			break;
		case FOUR_OF_A_KIND:
		case THREE_OF_A_KIND:
		case PAIR:
		case TWO_PAIRS:
			resultingSequence = new CardSequence(detected, new TreeSet<Card>(cardsProcessed));
			break;
		case FLUSH:
		case HIGH_CARD:
			resultingSequence = new CardSequence(detected, new TreeSet<Card>(cards));
			break;
		default:
			throw new IllegalArgumentException("Incorrect card combination!");
		}
		return resultingSequence;
	}

	private int getSequenceCombinationMask(Collection<Card> cards) {
		int combinationMask = SequenceHelper
				.hasRepeats(cards, Card::getSuit, 5) ? FLUSH.bitMask() : 0;
		boolean hasStraight = 
				SequenceHelper.isMonotonicByOne(CardHelper
						.reevaluateAceInSequence(cards), c -> c.getRank().asInt());
		if (hasStraight) {
			combinationMask += STRAIGHT.bitMask();
			cardsProcessed.addAll(cards);
		}
		combinationMask += cardsProcessed.addAll(SequenceHelper
				.findRepeats(cards, Card::getRank, 4)) ? FOUR_OF_A_KIND.bitMask() : 0;
		combinationMask += cardsProcessed.addAll(SequenceHelper
				.findRepeats(cards, Card::getRank, 3)) ? THREE_OF_A_KIND.bitMask() : 0;
		Collection<Card> pairs = SequenceHelper.findRepeats(cards, Card::getRank, 2);
		combinationMask += cardsProcessed
				.addAll(pairs) ? pairs.size() / 2 * PAIR.bitMask() : 0;
		
		return combinationMask;
	}
}