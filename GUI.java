package pakge;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import pakge.Sorter.Ements;
import pakge.Sorter.Sorts;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Font;

public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;
	ArrayList<Resistor> resistors = new ArrayList<Resistor>();
	ArrayList<Result> results = new ArrayList<Result>();
	Sorter sorter = new Sorter();
	SpinnerNumberModel model = new SpinnerNumberModel(1.0, 0.0, Double.MAX_VALUE, .1);
	private JScrollBar scrollbar;
	private JPanel ResultsPanel;
	GUI me = this;
	
	public GUI() {
		
		Scanner scan = null;
		try {
			scan = new Scanner(new File("src/Resistors.dat"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		while (scan.hasNext()) {
			String next = scan.next().trim();
			//Don't add repeats
			if (!resistors.contains(next)) resistors.add(new Resistor(next));
		}
		scan.close();
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JPanel title = new JPanel();
		top.add(title, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		title.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVoltageDividerCalculator = new JLabel("Voltage Divider Calculator");
		lblVoltageDividerCalculator.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_10.add(lblVoltageDividerCalculator, BorderLayout.NORTH);
		
		JLabel lblByCameronOneil = new JLabel("            Cameron O'Neil");
		panel_10.add(lblByCameronOneil);
		lblByCameronOneil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel bottom = new JPanel();
		top.add(bottom);
		
		JPanel input = new JPanel();
		bottom.add(input);
		
		JLabel lblInputVoltage = new JLabel("Input Voltage:");
		input.add(lblInputVoltage);
		
		JSpinner inputSpinner = new JSpinner(new SpinnerNumberModel(5.0, 0.0, Double.MAX_VALUE, .1));
		inputSpinner.setPreferredSize(new Dimension(50, 20));
		input.add(inputSpinner);
		
		JPanel output = new JPanel();
		bottom.add(output);
		
		JLabel lblOutputVoltage = new JLabel("Output Voltage");
		output.add(lblOutputVoltage);
		
		JSpinner outputSpinner = new JSpinner(new SpinnerNumberModel(3.3, 0.0, Double.MAX_VALUE, .1));
		outputSpinner.setPreferredSize(new Dimension(50, 20));
		output.add(outputSpinner);
		
		JButton btnCalculate = new JButton("Calculate");
		bottom.add(btnCalculate);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		
		JPanel maxVolts = new JPanel();
		panel_4.add(maxVolts);
		

		JSpinner maxVoltsSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0, Double.MAX_VALUE, .1));
		maxVoltsSpinner.setPreferredSize(new Dimension(50, 20));
		maxVoltsSpinner.setEnabled(false);
		
		JToggleButton maxVoltsButton = new JToggleButton("Max Voltage");
		maxVoltsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maxVoltsSpinner.setEnabled(maxVoltsButton.isSelected());
			}
		});
		maxVolts.add(maxVoltsButton);
		maxVolts.add(maxVoltsSpinner);
		
		JPanel minVolts = new JPanel();
		panel_4.add(minVolts);
		
			JSpinner minVoltsSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0, Double.MAX_VALUE, .1));
			minVoltsSpinner.setPreferredSize(new Dimension(50, 20));
			minVoltsSpinner.setEnabled(false);
			
			JToggleButton minVoltsButton = new JToggleButton("Min Voltage");
			minVoltsButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					minVoltsSpinner.setEnabled(minVoltsButton.isSelected());
				}
			});
			minVolts.add(minVoltsButton);
			minVolts.add(minVoltsSpinner);
		
		JPanel maxHeat = new JPanel();
		panel_4.add(maxHeat);
		
		JSpinner maxHeatSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0, Double.MAX_VALUE, .1));
		maxHeatSpinner.setPreferredSize(new Dimension(50, 20));
		maxHeatSpinner.setEnabled(false);
		
		JToggleButton maxHeatButton = new JToggleButton("Max Heat");
		maxHeatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maxHeatSpinner.setEnabled(maxHeatButton.isSelected());
			}
		});
		maxHeat.add(maxHeatButton);
		maxHeat.add(maxHeatSpinner);
		
		JPanel minHeat = new JPanel();
		panel_4.add(minHeat);
		
		JSpinner minHeatSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.0, Double.MAX_VALUE, .1));
		minHeatSpinner.setPreferredSize(new Dimension(50, 20));
		minHeatSpinner.setEnabled(false);
		
		JToggleButton minHeatButton = new JToggleButton("Min Heat");
		minHeatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minHeatSpinner.setEnabled(minHeatButton.isSelected());
			}
		});
		minHeat.add(minHeatButton);
		minHeat.add(minHeatSpinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollbar = scrollPane.getVerticalScrollBar();
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		ResultsPanel = new JPanel();
		scrollPane.setViewportView(ResultsPanel);
		ResultsPanel.setLayout(new GridLayout(0,1));
		
		JPanel panel_9 = new JPanel();
		ResultsPanel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 5, 0, 0));
		
		JPanel columnHeaders = new JPanel();
		scrollPane.setColumnHeaderView(columnHeaders);
		columnHeaders.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnTop = new JButton("Resistor 1");
		btnTop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				sort(Sorts.r1);
			}
		});
		columnHeaders.add(btnTop);
		
		JButton btnBottom = new JButton("Resistor 2");
		btnBottom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				sort(Sorts.r2);
			}
		});
		columnHeaders.add(btnBottom);
		
		JButton btnResultingVoltage = new JButton("Resulting Voltage");
		btnResultingVoltage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				sort(Sorts.result);
			}
		});
		columnHeaders.add(btnResultingVoltage);
		
		JButton btnVoltageDifference = new JButton("Voltage Difference");
		btnVoltageDifference.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				sort(Sorts.closest);
			}
		});
		columnHeaders.add(btnVoltageDifference);
		
		JButton btnHeat = new JButton("Heat");
		btnHeat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				sort(Sorts.heat);
			}
		});
		columnHeaders.add(btnHeat);
		
		btnCalculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				results.clear();
				
				double in = (double) inputSpinner.getValue();
				double out = (double) outputSpinner.getValue();
				
				for (int i = 0; i < resistors.size(); i++) {
					for (int j = i; j < resistors.size(); j++) {
						Result temp = new Result(resistors.get(i), resistors.get(j), in, out);
						if (maxHeatSpinner.isEnabled()) temp.maxHeat = (double) maxHeatSpinner.getValue();
						if (minHeatSpinner.isEnabled()) temp.minHeat = (double) minHeatSpinner.getValue();
						if (minVoltsSpinner.isEnabled()) temp.minVoltage = (double) minVoltsSpinner.getValue();
						if (maxVoltsSpinner.isEnabled()) temp.maxVoltage = (double) maxVoltsSpinner.getValue();
						temp.calculate();
						if (temp.valid) results.add(temp);
					}
				}
				sort(Sorts.closest);
			}
		});
		
		this.setSize(741, 505);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void sort(Sorts sort) {
		if (sorter.currentSort == sort)
			sorter.sortBy(results, sort, (sorter.currentEment == Ements.decrementing ? Ements.incrementing : Ements.decrementing));
		else
			sorter.sortBy(results, sort, Ements.decrementing);
		
		printResults();
	}

	private void printResults() {
		
		ResultsPanel.removeAll(); 
		// refresh the panel.
//		ResultsPanel.updateUI();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//Output Results
				for (Result res : results) {
					JPanel temp = new JPanel();
					temp.setLayout(new GridLayout(0, 5, 0, 0));
					temp.add(new JLabel(res.resistor1.name));
					temp.add(new JLabel(res.resistor2.name));
					temp.add(new JLabel(String.format("%.2f volts", res.resultVoltage)));
					temp.add(new JLabel(String.format("%.2f volts", res.distanceFromTarget)));
					temp.add(new JLabel(String.format("%.2f watts", res.heat)));
					ResultsPanel.add(temp);
				}
				
				me.revalidate();
				me.repaint();
				scrollbar.setValue(scrollbar.getMaximum());
			}
		}).start();	
	}

	public static void say(Object s) {
		System.out.println(s);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
