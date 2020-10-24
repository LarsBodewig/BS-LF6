package ui;

import java.awt.event.ActionListener;

import core.Algorithm;

public class EventHandler {

	/*
	 * Referenz auf das zu manipulierende Fram
	 */
	private final Application app;

	/*
	 * Referenz auf den zu bedienenden Algorithmus
	 */
	private final Algorithm<?> algorithm;

	/*
	 * der Status der automatischen Ausführung
	 */
	private boolean running;

	/*
	 * erzeugt einen neuen EventHandler mit Referenzen auf das Frame und den
	 * Algorithmus
	 */
	public EventHandler(Application app, Algorithm<?> alg) {
		this.app = app;
		algorithm = alg;
		running = false;
	}

	/*
	 * startet die automatische Ausführung des Sortieralgorithmus
	 */
	public void play() {
		running = true;
		app.setButtonState(0, false);
		app.setButtonState(1, true);
		app.setButtonState(2, false);
		app.setButtonState(3, false);
		while (!algorithm.isSorted() && running) {
			algorithm.stepForward();
			app.updateBarChart();
			try {
				Thread.sleep(app.getSpeed());
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		running = false;
		app.setButtonState(1, false);
		if (algorithm.isSorted()) {
			app.setButtonState(0, false);
			app.setButtonState(3, false);
		} else {
			app.setButtonState(3, true);
		}
		if (algorithm.canStepBackwards()) {
			app.setButtonState(2, true);
		}
	}

	/*
	 * stoppt die automatische Ausführung
	 */
	public void pause() {
		running = false;
		if (!algorithm.isSorted()) {
			app.setButtonState(0, true);
			app.setButtonState(3, true);
		}
		if (algorithm.canStepBackwards()) {
			app.setButtonState(2, true);
		}
		app.setButtonState(1, false);
	}

	/*
	 * geht einen Schritt in der Sortierung zurück
	 */
	public void stepBack() {
		algorithm.stepBackwards();
		app.updateBarChart();
		if (!algorithm.canStepBackwards()) {
			app.setButtonState(2, false);
		} else {
			app.setButtonState(0, true);
			app.setButtonState(3, true);
		}
	}

	/*
	 * führt einen einzelnen Sortiertschritt aus
	 */
	public void stepForward() {
		algorithm.stepForward();
		app.updateBarChart();
		app.setButtonState(2, true);
		if (algorithm.isSorted()) {
			app.setButtonState(0, false);
			app.setButtonState(3, false);
		}
	}

	/*
	 * ruft die übergebene Methode asynchron in einem neuen Thread auf
	 */
	public static ActionListener createListener(Runnable r) {
		return (e) -> new Thread(r).start();
	}
}