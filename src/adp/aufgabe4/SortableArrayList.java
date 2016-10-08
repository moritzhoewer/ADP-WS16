/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.10.2016 
 * Aufgabe: Aufgabenblatt 4
 */

package adp.aufgabe4;

import adp.aufgabe1.ArrayList;

/**
 * Extends the {@link ArrayList} from Aufgabe 1 to make it sortable (but only
 * works for {@code Integers})
 *
 * @author Moritz Höwer
 * @version 1.0 - 08.10.2016
 */
public class SortableArrayList extends ArrayList<Integer> {

	/**
	 * empties the List
	 */
	public void empty() {
		count = 0;
	}

	/**
	 * Sorts the list with the bubble sort algorithm
	 */
	public void doBubbleSort() {
		boolean wasSorted;

		do {
			// assume list is sorted
			wasSorted = true;
			for (int i = 1; i < count; i++) {
				Integer i1 = (Integer) data[i - 1];
				Integer i2 = (Integer) data[i];

				if (i1 > i2) {
					// switch elements if i1 is greater
					data[i] = i1;
					data[i - 1] = i2;
					
					// list wasn't sorted actually
					wasSorted = false;
				}
			}
		} while (!wasSorted);
	}

	/**
	 * Sorts the list with the insertion sort algorithm
	 */
	public void doInsertionSort() {
		//TODO: Implement
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("ArrayList(" + count + ")-[");

		// go through the array
		for (int i = 0; i < count; i++) {
			builder.append(data[i]);
			if ((i + 1) != count) {
				builder.append(", ");
			}
		}
		builder.append(']');
		return builder.toString();
	}
}
