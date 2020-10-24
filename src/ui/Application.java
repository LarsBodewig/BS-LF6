package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import core.Algorithm;

public class Application {

	/*
	 * das Frame
	 */
	private JFrame frame;

	/*
	 * der abgebildete Algorithmus
	 */
	private Algorithm<?> algorithm;

	/*
	 * die Visualisierung des Algorithmus in einem Säulendiagramm
	 */
	private EasyBarChart barChart;

	/*
	 * der Start-Button für die automatische Ausführung
	 */
	private JButton playButton;

	/*
	 * der Stop-Button für die automatische Ausführung
	 */
	private JButton pauseButton;

	/*
	 * der Button für einen Rückwärtsschritt
	 */
	private JButton stepBackButton;

	/*
	 * der Button für einen einzelnen Sortierschritt
	 */
	private JButton stepForwardButton;

	/*
	 * der Slider zur Anpassung der automatischen Sortiergeschwindigkeit
	 */
	private JSlider speedSlider;

	/*
	 * erzeugt das Frame mit dem Windows Look and Feel
	 */
	public Application(Algorithm<?> algorithm) {
		this.algorithm = algorithm;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * initialisiert das Frame, fügt ActionListener hinzu und erzeugt das
	 * Säulendiagramm
	 */
	private void initialize() {
		frame = new JFrame(algorithm.getName() + " by Lars Bodewig");
		frame.setBounds(100, 100, 762, 462);
		frame.setMinimumSize(new Dimension(762, 462));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				frame.setSize(new Dimension(frame.getWidth(), (int) frame.getPreferredSize().getHeight()));
				super.componentResized(e);
			}
		});

		EventHandler eventHandler = new EventHandler(this, algorithm);

		JPanel graphicPanel = new JPanel();

		frame.getContentPane().add(graphicPanel, BorderLayout.WEST);
		graphicPanel.setLayout(new BorderLayout(0, 0));

		JPanel controlBarPanel = new JPanel();
		barChart = EasyBarChart.buildBarChart(algorithm.getElements(), algorithm.getName());
		graphicPanel.add(barChart, BorderLayout.CENTER);
		graphicPanel.add(controlBarPanel, BorderLayout.SOUTH);
		controlBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		playButton = new JButton("Play");
		controlBarPanel.add(playButton);
		playButton.addActionListener(EventHandler.createListener(eventHandler::play));

		pauseButton = new JButton("Pause");
		controlBarPanel.add(pauseButton);
		pauseButton.addActionListener(EventHandler.createListener(eventHandler::pause));
		pauseButton.setEnabled(false);

		stepBackButton = new JButton("<< Step");
		controlBarPanel.add(stepBackButton);
		stepBackButton.addActionListener(EventHandler.createListener(eventHandler::stepBack));

		stepForwardButton = new JButton("Step >>");
		controlBarPanel.add(stepForwardButton);
		stepForwardButton.addActionListener(EventHandler.createListener(eventHandler::stepForward));

		speedSlider = new JSlider();
		controlBarPanel.add(speedSlider);

		JPanel detailPanel = new JPanel();
		frame.getContentPane().add(detailPanel, BorderLayout.CENTER);
		detailPanel.setLayout(new BorderLayout(0, 0));
		detailPanel.setPreferredSize(new Dimension(150, 394));

		JTextArea detailTextArea = new JTextArea();
		detailTextArea.setWrapStyleWord(true);
		detailTextArea.setLineWrap(true);
		detailTextArea.setText(algorithm.getDescription());
		detailPanel.add(new JScrollPane(detailTextArea), BorderLayout.CENTER);
	}

	/*
	 * aktualisiert das Säulendiagramm mit neuen Werten
	 */
	public void updateBarChart() {
		barChart.setBars(algorithm.getElements());
		System.out.println(algorithm.toString());
	}

	/*
	 * gibt die eingestellte Geschwindigkeit des Sliders zurück
	 */
	public long getSpeed() {
		return (speedSlider.getMaximum() - speedSlider.getValue()) * 10;
	}

	/*
	 * aktiviert oder deaktiviert einen Button
	 */
	public void setButtonState(int button, boolean enabled) {
		switch (button) {
		case 0:
			playButton.setEnabled(enabled);
			break;
		case 1:
			pauseButton.setEnabled(enabled);
			break;
		case 2:
			stepBackButton.setEnabled(enabled);
			break;
		case 3:
			stepForwardButton.setEnabled(enabled);
			break;
		}
	}
}