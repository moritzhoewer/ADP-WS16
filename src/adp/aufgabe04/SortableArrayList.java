/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.10.2016 
 * Aufgabe: Aufgabenblatt 4
 */

package adp.aufgabe04;

import adp.aufgabe01.ArrayList;

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
	//  VERBESSERUNGSVORSCHLAG
	public void doBubbleSort() {
		boolean wasSorted;

		do {
			// assume list is sorted
			wasSorted = true;
			for (int i = 1; i < count; i++) {
				
				if ((Integer)data[i-1] > (Integer)data[i]) {
					// switch elements if i1 is greater
					int temp = (Integer)data[i-1];
					data[i-1] = data[i];
					data[i] = temp;
					// list wasn't sorted actually
					wasSorted = false;
				}
			}
		} while (!wasSorted);
	}

	
	public void doBubbleSortfaster() {
		boolean wasSorted;
		int limit = count;
		do {
			// assume list is sorted
			wasSorted = true;
			for (int i = 1; i < limit; i++) {
				
				if ((Integer)data[i-1] > (Integer)data[i]) {
					// switch elements if i1 is greater
					int temp = (int)data[i-1];
					data[i-1] = data[i];
					data[i] = temp;
					// list wasn't sorted actually
					wasSorted = false;
					
				}
				
			}
			limit--;
		} while (!wasSorted);
	}


	public void doBubbleSortEvenFaster() {
		boolean wasSorted;
		int limit=count, lastSwap=count;
		do {
			// assume list is sorted
			wasSorted = true;
			for (int i = 1; i < limit; i++) {
				
				if ((Integer)data[i-1] > (Integer)data[i]) {
					// switch elements if i1 is greater
					int temp = (int)data[i-1];
					data[i-1] = data[i];
					data[i] = temp;
					// list wasn't sorted actually
					wasSorted = false;
					lastSwap = i;
				}
			}
			limit = lastSwap;
		} while (!wasSorted);
	}
	/**
	 * Sorts the list with the insertion sort algorithm
	 */
	public void doInsertionSort() {
		for (int i=1;i<count;i++){
			if((Integer)data[i]<(Integer)data[i-1]){
				int temp = (Integer)data[i];
				int k=i-1;
				while(k>=0 && temp<(Integer)data[k]){
					data[k+1] = data[k];
					k--;
				}
				data[k+1]=temp;
			}
			
		}
	}
	/**
	 * Swaps Entries at Index1 and Index2 in ArrayList
	 * @param Index1
	 * @param Index2
	 */
    public void swap(int Index1, int Index2){
	    	Object tmp;
			tmp = data[Index1];
			data[Index1] = data[Index2];
			data[Index2] = tmp;
	    }
	/**
	 * 
	 * @param leftElement
	 * @param Pivot
	 */
	public void doQuickSort(int leftElement, int Pivot){
		
		if(leftElement < Pivot){
			int i=leftElement;
			int j=Pivot-1;
			while(true){
				while((int)data[i] < (int)data[Pivot]){
					i++;
				}
				while((int)data[j]>=(int)data[Pivot] && (j>leftElement)){
					j--;
				}
				if(i>=j){
					break;
				}
				swap(i,j);
			}
			swap(i, Pivot);
			doQuickSort(leftElement, i-1);
			doQuickSort(i+1, Pivot);
		}
		
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
