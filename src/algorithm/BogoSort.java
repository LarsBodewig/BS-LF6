package algorithm;

import core.Algorithm;
import core.Numerical;

public class BogoSort<T extends Numerical<T, ?>> extends Algorithm<T> {

	/*
	 * der Name des Algorithmus
	 */
	private final static String name = "BogoSort";

	/*
	 * die angezeigte Beschreibung zum Sortieralgorithmus
	 */
	private final static String description = "Bogosort, Monkeysort oder Stupidsort bezeichnet ein nicht-stabiles Sortierverfahren, "
			+ "bei dem die Elemente so lange zufällig gemischt werden, bis sie sortiert sind. "
			+ "\nWegen der langen Laufzeit ist Bogosort der Prototyp eines schlechten Algorithmus. "
			+ "Bogosort wird insbesondere in der Informatik-Ausbildung in den Bereichen Datenstrukturen und Algorithmen verwendet, "
			+ "um an einem Extrembeispiel die Eigenschaften von Sortierverfahren im Allgemeinen zu verdeutlichen.";

	/*
	 * Erzeugt ein neues Algorithmus-Objekt mit dem Array elements
	 */
	public BogoSort(T[] elements, boolean ascending) {
		super(name, elements, ascending);
	}

	/*
	 * gibt die Beschreibung des Algorithmus zurück
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * führt einen einzelnen Sortierschritt aus
	 */
	@Override
	protected void step() {
		int posAlt = (int) (Math.random() * elements.length);
		int posNeu = (int) (Math.random() * elements.length);
		T temp = elements[posAlt];
		elements[posAlt] = elements[posNeu];
		elements[posNeu] = temp;
	}
}