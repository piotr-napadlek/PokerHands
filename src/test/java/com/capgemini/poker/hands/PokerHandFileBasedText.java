package com.capgemini.poker.hands;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assume;
import org.junit.Test;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.helpers.CardHelper;

public class PokerHandFileBasedText {

	@Test
	public void shoudFindTestFile() {
		 assertNotNull("Test file missing!", getClass().getResource("poker.txt"));
	}
	
	@Test
	public void playerOneShouldWin376TimesFromFileInput() throws Exception {
		// given
		Assume.assumeNotNull(getClass().getResource("poker.txt"));
	
		Path testFilePath = Paths.get(getClass().getResource("poker.txt").toURI());
		Collection<Collection<Card>> cardCombinations = new ArrayList<>();
		Files.lines(testFilePath)
			.forEach(line -> cardCombinations.add(CardHelper.parseCardsFromString(line)));
		// when
		PokerHand playerOne;
		PokerHand playerTwo;
		List<Card> tenCards;
		int playerOneWins = 0;
		
		for(Collection<Card> cards : cardCombinations) {
			tenCards = new ArrayList<>(cards);
			playerOne = new PokerHand(tenCards.subList(0, 5));
			playerTwo = new PokerHand(tenCards.subList(5, 10));
			playerOneWins += playerOne.compareToHand(playerTwo) > 0 ? 1 : 0;
		}
		// then
		int expectedPlayerOneWins = 376;
		assertEquals(expectedPlayerOneWins, playerOneWins);
	}
}