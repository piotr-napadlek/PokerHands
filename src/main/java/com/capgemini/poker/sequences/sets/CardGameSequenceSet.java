package com.capgemini.poker.sequences.sets;

import com.capgemini.poker.sequences.Sequence;

public interface CardGameSequenceSet {
	public Sequence getPrimary();
	public Sequence getAuxillary();
	public int compareToSequenceSet(CardGameSequenceSet other);
}