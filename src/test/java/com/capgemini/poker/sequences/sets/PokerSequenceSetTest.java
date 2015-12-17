package com.capgemini.poker.sequences.sets;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.sequences.PokerSequence;
import com.capgemini.poker.sequences.sets.CardGameSequenceSet;
import com.capgemini.poker.sequences.sets.PokerSequenceSet;

@RunWith(Parameterized.class)
public class PokerSequenceSetTest {
	private final SequenceTestData testData;
	private CardGameSequenceSet sequenceSet;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	public PokerSequenceSetTest(SequenceTestData testData) {
		this.testData = testData;
	}
	
	@Before
	public void initialize() {
		testData.getExpectedException().ifPresent(ex -> expectedException.expect(ex));
		sequenceSet = new PokerSequenceSet(testData.getCards());
	}

	@Parameters
	public static Collection<SequenceTestData> testData() {
		return PokerSequenceSetTestDataSet.getTestData();
	}

	@Test
	public void shouldProperlyRecognizePrimarySequence() {
		// given
		PokerSequence detectedPrimarySequence = sequenceSet.getPrimary().getSequence();
		// when

		// then
		assertEquals(testData.getTestName(), testData.getPrimary(), detectedPrimarySequence);
	}

	@Test
	public void shouldProperlyDetermineIfAuxillarySequenceIsPresent() {
		// given
		boolean auxillaryCardsArePresent = !sequenceSet.getAuxillary().getCardsInSequence()
				.isEmpty();
		// when

		// then
		assertEquals(testData.getTestName(), testData.getHighestAuxillaryCard().isPresent(),
				auxillaryCardsArePresent);
	}

	@Test
	public void shouldGetHighestAuxillaryCardIfItsPresent() {
		// given
		Assume.assumeTrue(testData.getHighestAuxillaryCard().isPresent());
		// when
		Card highestRemaining = sequenceSet.getAuxillary().getCardsInSequence().last();
		// then
		assertEquals(testData.getTestName(), testData.getHighestAuxillaryCard().get(),
				highestRemaining);
	}
}