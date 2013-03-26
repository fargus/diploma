package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.variable.Variable;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValuesDialog extends JDialog {

	private Map<Integer, JSpinner> spinnerMap = new HashMap<Integer, JSpinner>();
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleSrvice rs = new RuleServiceImpl();
			ValuesDialog dialog = new ValuesDialog(rs.getAllRules().getInputVars(), null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ValuesDialog(Set<Variable> inputVars, final Map<Integer, Double> inputVal, JFrame parent) {
		super(parent);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			for (Variable v : inputVars) {
				JPanel temp = new JPanel();
				temp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				temp.add(new JLabel("Variable:[ID=" + v.getId() + " Name=" + v.getName() + "]"));
				JSpinner s = new JSpinner(new SpinnerNumberModel((inputVal != null && inputVal.get(v.getId()) != null) ? inputVal.get(v.getId()) : v.getMin(), v
						.getMin(), v.getMax(), 1));
				spinnerMap.put(v.getId(), s);
				temp.add(s);
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
						inputVal.clear();
						for(Entry<Integer, JSpinner> entry : spinnerMap.entrySet()){
							inputVal.put(entry.getKey(), (Double)entry.getValue().getValue());
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
