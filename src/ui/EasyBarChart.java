package ui;

import java.awt.Color;
import java.util.ArrayList;

import core.Numerical;

public class EasyBarChart extends BarChart {

	/*
	 * automatisch generierte ID des Elements
	 */
	private static final long serialVersionUID = -2167411580992440991L;

	/*
	 * erzeugt ein neues Säulendiagramm aus der Liste von Säulen und Achsen
	 */
	protected EasyBarChart(ArrayList<Bar> bars, Axis yAxis, String title) {
		super(bars, yAxis);
		this.title = title;
	}

	/*
	 * erzeugt ein Säulendiagramm aus dem Array von Inhaltsobjekten
	 */
	public static <T> EasyBarChart buildBarChart(Numerical<?, T>[] elements, String title) {
		int max = 0;
		ArrayList<Bar> values = new ArrayList<>();

		for (Numerical<?, T> t : elements) {
			if (t.numericalValue() > max) {
				max = t.numericalValue();
			}
			values.add(new Bar(t.numericalValue(), Color.RED, t.toString()));
		}
		Axis yAxis = new Axis(max, 0, max / 5, max / 10, max / 20, "Numeric value");

		return new EasyBarChart(values, yAxis, title);
	}

	/*
	 * aktualisiert die abgebildeten Säulen anhand des Arrays von Inhaltsobjekten
	 */
	public <T> void setBars(Numerical<?, T>[] elements) {
		int max = 0;
		ArrayList<Bar> values = new ArrayList<>();

		for (Numerical<?, T> t : elements) {
			if (t.numericalValue() > max) {
				max = t.numericalValue();
			}
			values.add(new Bar(t.numericalValue(), Color.RED, t.toString()));
		}
		this.bars = values;
		this.yAxis = new Axis(max, 0, max / 5, max / 10, max / 20, "Numerical value");
		this.repaint();
	}
}

