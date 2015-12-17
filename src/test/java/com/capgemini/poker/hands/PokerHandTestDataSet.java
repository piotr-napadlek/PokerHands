package com.capgemini.poker.hands;

import java.util.ArrayList;
import java.util.Collection;

public class PokerHandTestDataSet {

	public static Collection<PokerHandTestData> getTestData() {
		Collection<PokerHandTestData> testSet = new ArrayList<>();
		
		testSet.add(PokerHandTestData.FluentBuilder()
				.withPlayerOneCards("2S 3D 4C 8D 8S")
				.withPlayerTwoCards("JC AC QC TC 9D")
				.andPlayerOneWins(true)
				.withTestName("Pair of eights over high ace")
				.build());
		testSet.add(PokerHandTestData.FluentBuilder()
				.withPlayerOneCards("2S 3D 4C 8D 8S")
				.withPlayerTwoCards("JC AC QC TC JD")
				.andPlayerOneWins(false)
				.withTestName("Pair of jacks over pair of eights")
				.build());
		testSet.add(PokerHandTestData.FluentBuilder()
				.withPlayerOneCards("2S 3D 8C 8D 8S")
				.withPlayerTwoCards("JC AC QC TC JD")
				.andPlayerOneWins(true)
				.withTestName("Three eights over pair of jacks")
				.build());
		testSet.add(PokerHandTestData.FluentBuilder()
				.withPlayerOneCards("2S 3D 3C 3S 8S")
				.withPlayerTwoCards("JC QC QH TC JD")
				.andPlayerOneWins(true)
				.withTestName("Three threes over two pairs of jacks and queens")
				.build());
		testSet.add(PokerHandTestData.FluentBuilder()
				.withPlayerOneCards("TS JS QS KS AS")
				.withPlayerTwoCards("AD 2D 3D 4D 5D")
				.andPlayerOneWins(true)
				.withTestName("Royal flush over straight flush to five")
				.build());
		
		return testSet;
	}
}
