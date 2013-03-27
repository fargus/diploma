package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Variable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ResultDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleSrvice rs = new RuleServiceImpl();
			RuleBase rb = rs.getAllRules();
			Map<Integer, Double> result = new HashMap<Integer, Double>();
			Random rnd = new Random();
			for(Variable v : rb.getOutputVars()){
				result.put(v.getId(), rnd.nextDouble()*100);
			}
			ResultDialog dialog = new ResultDialog(rb.getOutputVars(), result, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResultDialog(Set<Variable> outputVars, final Map<Integer, Double> result, JFrame parent) {
		super(parent);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			for (Variable v : outputVars) {
				JPanel temp = new JPanel();
				temp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				temp.add(new JLabel("Variable:[ID=" + v.getId() + " Name=" + v.getName() + "]"));
				temp.add(new JLabel("Result: ["+result.get(v.getId())+"]"));
				contentPanel.add(temp);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
