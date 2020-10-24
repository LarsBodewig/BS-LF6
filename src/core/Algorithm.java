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
	 * Verlauf der Zustände des Arrays von zu sortierenden Elementen
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
	 * gibt den Namen des Algorithmus zurück
	 */
	public String getName() {
		return name;
	}

	/*
	 * gibt die zu sortierenden Elemente zurück
	 */
	public T[] getElements() {
		return elements;
	}

	/*
	 * gibt zurück, ob ein Schritt rückwärts gemacht werden kann
	 */
	public boolean canStepBackwards() {
		return history != null && !history.isEmpty();
	}

	/*
	 * geht einen Schritt in der Sortierung zurück
	 */
	public void stepBackwards() {
		if (canStepBackwards()) {
			elements = history.pop();
		}
	}

	/*
	 * gibt zurück, ob elements sortiert ist
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
	 * führt einen einzelnen Sortierschritt aus
	 */
	public void stepForward() {
		if (!isSorted()) {
			history.push(elements[0].clone(elements));
			step();
		}
	}

	/*
	 * gibt einen String mit allen zu sortierenden Elementen zurück
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
	 * führt einen einzelnen Sortierschritt aus
	 */
	protected abstract void step();

	/*
	 * gibt die Beschreibung des Algorithmus zurück
	 */
	public abstract String getDescription();
}