package com.capgemini.poker.hands;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PokerHandTest {
	PokerHandTestData testData;
	PokerHand playerOne;
	PokerHand playerTwo;

	public PokerHandTest(PokerHandTestData testData) {
		this.testData = testData;
	}

	@Before
	public void initialize() {
		this.playerOne = new PokerHand(testData.getPlayerOneCards());
		this.playerTwo = new PokerHand(testData.getPlayerTwoCards());
	}

	@Parameters
	public static Collection<PokerHandTestData> getTestDataSet() {
		return PokerHandTestDataSet.getTestData();
	}

	@Test
	public void shouldDefineWhoWins() {
		// given
		boolean resultPlayerOneWins = playerOne.compareToHand(playerTwo) > 0;
		// when

		// then
		assertEquals(testData.getTestName(), testData.playerOneWins(), resultPlayerOneWins);
	}

}
