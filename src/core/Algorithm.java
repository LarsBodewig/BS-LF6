package core;

import java.util.Stack;

public abstract class Algorithm<T extends Numerical<T, ?>> {

	/*
	 * der Name des Algorithmus
	 */
	private String name;

	/*
	 * die zu sortierenden Elemente vom Typ T
	 */
	protected T[] elements;

	/*
	 * Verlauf der Zust�nde des Arrays von zu sortierenden Elementen
	 */
	private Stack<T[]> history;

	/*
	 * die Sortiertreihenfolge
	 */
	private boolean ascending;

	/*
	 * erzeugt ein neues Algorithmus-Objekt
	 */
	public Algorithm(String name, T[] elements, boolean ascending) {
		this.name = name;
		this.elements = elements;
		this.ascending = ascending;
		history = new Stack<T[]>();
	}

	/*
	 * gibt den Namen des Algorithmus zur�ck
	 */
	public String getName() {
		return name;
	}

	/*
	 * gibt die zu sortierenden Elemente zur�ck
	 */
	public T[] getElements() {
		return elements;
	}

	/*
	 * gibt zur�ck, ob ein Schritt r�ckw�rts gemacht werden kann
	 */
	public boolean canStepBackwards() {
		return history != null && !history.isEmpty();
	}

	/*
	 * geht einen Schritt in der Sortierung zur�ck
	 */
	public void stepBackwards() {
		if (canStepBackwards()) {
			elements = history.pop();
		}
	}

	/*
	 * gibt zur�ck, ob elements sortiert ist
	 */
	public boolean isSorted() {
		boolean isSorted = true;
		for (int i = 0; i < elements.length - 1; i++) {
			if (ascending && elements[i].compareTo(elements[i + 1]) > 0
					|| !ascending && elements[i].compareTo(elements[i + 1]) < 0) {
				isSorted = false;
			}
		}
		return isSorted;
	}

	/*
	 * f�hrt einen einzelnen Sortierschritt aus
	 */
	public void stepForward() {
		if (!isSorted()) {
			history.push(elements[0].clone(elements));
			step();
		}
	}

	/*
	 * gibt einen String mit allen zu sortierenden Elementen zur�ck
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(elements.length);
		for (T t : elements) {
			sb.append(t.toString());
		}
		return sb.toString();
	}

	/*
	 * f�hrt einen einzelnen Sortierschritt aus
	 */
	protected abstract void step();

	/*
	 * gibt die Beschreibung des Algorithmus zur�ck
	 */
	public abstract String getDescription();
}