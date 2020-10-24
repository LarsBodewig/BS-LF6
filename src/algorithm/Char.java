package algorithm;

import core.Numerical;

public class Char implements Numerical<Char, Character> {

	/*
	 * das Inhaltsobjekt vom Typ Character
	 */
	private final Character content;

	/*
	 * das im Dialog angezeigte und in processInput(String) verarbeitete Pattern
	 */
	public static final String pattern = "x,y,z";

	/*
	 * Erzeugt einen neuen Char aus einem primitiven Character
	 */
	public Char(char content) {
		this.content = new Character(content);
	}

	/*
	 * gibt den numerischen Wert zurück
	 */
	@Override
	public int numericalValue() {
		return (int) content;
	}

	/*
	 * gibt zurück, ob ein anderer Char größer, kleiner oder gleich diesem ist
	 */
	@Override
	public int compareTo(Char arg0) {
		return content.compareTo(arg0.content);
	}

	/*
	 * verarbeitet den Eingabe-String aus dem Dialog und erzeugt mit dem Pattern ein
	 * Char-Array
	 */
	@Override
	public Char[] processInput(String input) {
		String[] frag = input.split(",");
		Char[] cs = new Char[frag.length];
		for (int i = 0; i < frag.length; i++) {
			cs[i] = new Char(frag[i].trim().charAt(0));
		}
		return cs;
	}

	/*
	 * gibt das Pattern für die Dialog-Eingabe zurück
	 */
	@Override
	public String getPattern() {
		return pattern;
	}

	/*
	 * gibt das Inhaltsobjekt als String zurück
	 */
	@Override
	public String toString() {
		return content.toString();
	}

	/*
	 * gibt eine Deep Copy eines Char-Arrays zurück
	 */
	@Override
	public Char[] clone(Char[] array) {
		Char[] clone = new Char[array.length];
		for (int i = 0; i < array.length; i++) {
			clone[i] = new Char(array[i].content);
		}
		return clone;
	}
}