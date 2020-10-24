package core;

public interface Numerical<T, E> extends Comparable<T> {

	/*
	 * verarbeitet den Eingabe-String aus dem Dialog und erzeugt mit dem Pattern ein
	 * Array von Inhaltsobjekten
	 */
	public T[] processInput(String input);

	/*
	 * gibt das Pattern für die Dialog-Eingabe zurück
	 */
	public String getPattern();

	/*
	 * gibt den numerischen Wert des Inhaltsobjekts zurück
	 */
	public int numericalValue();

	/*
	 * gibt das Inhaltsobjekt als String zurück
	 */
	public String toString();

	/*
	 * gibt eine Deep Copy eines Arrays von Inhaltsobjekten zurück
	 */
	public T[] clone(T[] array);
}