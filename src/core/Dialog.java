package core;

import javax.swing.JOptionPane;

public class Dialog {

	/*
	 * zeigt einen Dialog mit dem Pattern des Sortieralgorithmus an und gibt einen
	 * Array von vom Inhaltstyp verarbeiteten Objekten zurück
	 */
	public static <T extends Numerical<T, ?>> T[] getElements(T numerical) {
		String elements = JOptionPane.showInputDialog("Zu sortierende Elemente: " + numerical.getPattern());
		return numerical.processInput(elements);
	}
}