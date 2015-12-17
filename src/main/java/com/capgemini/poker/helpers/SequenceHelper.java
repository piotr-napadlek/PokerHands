package com.capgemini.poker.helpers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class SequenceHelper {
	/**
	 * Finds and returns a collection of T where T property values
	 *  occured specified amount of times in the collection
	 * @param collection - Input collection
	 * @param comparisonBasis - property on which the comparison occurs
	 * @param repeatsAmount - int value of searched occurance amount
	 * @return new collection of T items meeting the demands
	 * @throws IllegalArgumentException arguments cannot be null
	 * @author PNAPADLE
	 */
	public static <T> List<T> findRepeats(Collection<T> collection, Function<T, ?> comparisonBasis, 
			int repeatsAmount) throws IllegalArgumentException {
		if (collection == null || comparisonBasis == null) {
			throw new IllegalArgumentException("Collection and compared property cannot be null!");
		}
		return collection.stream()
			.collect(Collectors.groupingBy(comparisonBasis))
			.values().stream()
			.filter(repeats -> repeats.size() == repeatsAmount)
			.flatMap(List::stream)
			.collect(Collectors.toList());
	}
	/**
	 * Analogical to findRepeats method, but only states if there are
	 * specified repeats insted of returning them.
	 * @param collection - input collection
	 * @param comparisonBasis - property on which the comparison occurs
	 * @param repeatsAmount - int value of searched occurance amount
	 * @return statement if the collection meets the demands
	 * @throws IllegalArgumentException arguments cannot be null
	 * @author PNAPADLE
	 * @see findRepeats
	 */
	public static <T> boolean hasRepeats(Collection<T> collection, Function<T, ?> comparisonBasis, 
			int repeatsAmount) throws IllegalArgumentException {
		if (collection == null || comparisonBasis == null) {
			throw new IllegalArgumentException("Collection and compared property cannot be null!");
		}
		return collection.stream()
				.collect(Collectors.groupingBy(comparisonBasis))
				.values().stream()
				.anyMatch(repeats -> repeats.size() == repeatsAmount);
	}
	/**
	 * Determines if specified items' Integer properties can form a strictly 
	 * monotonically increasing sequence where each consecutive value is increased 
	 * by one. Eg. 1, 2, 3 or 19, 20, 21, 22, 23 or 4, 5 are s.m.i.s. by one.
	 * @param collection - input collection to be checked
	 * @param propertyToBeMonotonic - checked property. Should be a function of 
	 * T that returns instance of Integer
	 * @return defines if such sequence exists in collection
	 * @throws IllegalArgumentException arguments cannot be null
	 * @author PNAPADLE
	 */
	public static <T> boolean isMonotonicByOne(Collection<T> collection, 
			Function<T, ? extends Integer> propertyToBeMonotonic) throws IllegalArgumentException {
		if (collection == null || propertyToBeMonotonic == null) {
			throw new IllegalArgumentException("Cards cannot be null!");
		}
		if (collection.isEmpty()) {
			return false;
		}
		SortedSet<Integer> valuesDistinctSorted = 
				reduceToSortedSetByProperty(collection, propertyToBeMonotonic);
		return valuesDistinctSorted.size() == collection.size() 
				&& (valuesDistinctSorted.last() - valuesDistinctSorted.first()) 
					== (collection.size() - 1);
	}
	/**
	 * Reduces collection items' pointed Integer properties to SortedSet
	 * @param collection to be reduced
	 * @param property to by mapped in resulting SortedSet
	 * @return sorted distinct collection of input collection item properties.
	 */
	public static <T> SortedSet<Integer> reduceToSortedSetByProperty(Collection<T> collection,
			Function<T, ? extends Integer> property) {
		if (collection == null) {
			return new TreeSet<Integer>();
		}
		SortedSet<Integer> valuesDistinctSorted = new TreeSet<Integer>(collection.stream()
				.mapToInt(i -> property.apply(i))
				.distinct()
				.boxed()
				.collect(Collectors.toSet()));
		return valuesDistinctSorted;
	}
}