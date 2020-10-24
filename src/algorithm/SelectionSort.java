package algorithm;

import core.Algorithm;
import core.Numerical;

public class SelectionSort<T extends Numerical<T, ?>> extends Algorithm<T> {

	/*
	 * der Name des Algorithmus
	 */
	private final static String name = "SelectionSort";

	/*
	 * die angezeigte Beschreibung zum Sortieralgorithmus
	 */
	private final static String description = "Selectionsort ist ein naiver Sortieralgorithmus, "
			+ "der in-place arbeitet und in seiner Grundform instabil ist, wobei er sich auch stabil implementieren lässt. \n"
			+ "Die Komplexität von Selectionsort ist, in der Landau-Notation ausgedrückt, O(n²).";

	/*
	 * die aktuelle Position im Array
	 */
	private int pos;

	/*
	 * Erzeugt ein neues Algorithmus-Objekt mit dem Array elements
	 */
	public SelectionSort(T[] elements, boolean ascending) {
		super(name, elements, ascending);
	}

	/*
	 * führt einen einzelnen Sortierschritt aus
	 */
	@Override
	protected void step() {
		int minPos = pos;
		for (int j = pos + 1; j < elements.length; j++) {
			if (elements[j].compareTo(elements[minPos]) < 1) {
				minPos = j;
			}
		}
		T temp = elements[minPos];
		elements[minPos] = elements[pos];
		elements[pos] = temp;
		pos++;
	}

	/*
	 * erweitert die stepBackwards()-Methode von Algorithm um die Position
	 * anzupassen
	 */
	@Override
	public void stepBackwards() {
		super.stepBackwards();
		pos--;
	}

	/*
	 * gibt die Beschreibung des Algorithmus zurück
	 */
	@Override
	public String getDescription() {
		return description;
	}
}