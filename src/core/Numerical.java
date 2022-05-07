package core;

public interface Numerical<T, E> extends Comparable<T> {

	/*
	 * verarbeitet den Eingabe-String aus dem Dialog und erzeugt mit dem Pattern ein
	 * Array von Inhaltsobjekten
	 */
	public T[] processInput(String input);

	/*
	 * gibt das Pattern f�r die Dialog-Eingabe zur�ck
	 */
	public String getPattern();

	/*
	 * gibt den numerischen Wert des Inhaltsobjekts zur�ck
	 */
	public int numericalValue();

	/*
	 * gibt das Inhaltsobjekt als String zur�ck
	 */
	public String toString();

	/*
	 * gibt eine Deep Copy eines Arrays von Inhaltsobjekten zur�ck
	 */
	public T[] clone(T[] array);
}