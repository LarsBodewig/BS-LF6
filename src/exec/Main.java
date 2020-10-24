package exec;

import algorithm.BogoSort;
import algorithm.Char;
import algorithm.SelectionSort;
import core.Algorithm;
import core.Dialog;
import ui.Application;

public class Main {

	/*
	 * ein inhaltsleerer Char zum Aufruf von Methoden
	 */
	private static final Char initChar = new Char((char) -1);

	/*
	 * erzeugt einen neuen Algorithmus und startet die Applikation
	 */
	public static void main(String[] args) {
		Algorithm<Char> a = new SelectionSort<Char>(Dialog.getElements(initChar), true);
		// Algorithm<Char> a = new BogoSort<Char>(Dialog.getElements(initChar), true);
		new Application(a);
	}
}