package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.variable.Operator;

public class RuleDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 168857557567214625L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleDialog dialog = new RuleDialog(null, new RuleServiceImpl());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MainWindow main;
	private RuleSrvice rs;
	private boolean isNew = true;
	private Collection<Condition> conds;
	private Collection<Conclusion> concs;
	private List<Condition> ruleCond = new ArrayList<Condition>();
	private List<Conclusion> ruleConc = new ArrayList<Conclusion>();
	private Integer id;

	private JList leftList;
	private JList rightList;
	private DefaultListModel leftModel = new DefaultListModel();
	private DefaultListModel rightModel = new DefaultListModel();
	private JTextPane textPane;
	private JComboBox comboBox; 
	private JComboBox comboBox_1;
	
	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	public RuleDialog(MainWindow main, RuleSrvice rs) {
		this.main = main;
		this.rs = rs;
		this.conds = rs.getAllCondition().values();
		this.concs = rs.getAllConclusion().values();
		initUI();
		setTitle("Create Rule");
	}

	public RuleDialog(MainWindow main, RuleSrvice rs, Rule r) {
		this.main = main;
		this.rs = rs;
		this.conds = rs.getAllCondition().values();
		this.concs = rs.getAllConclusion().values();
		this.id = r.getId();
		this.ruleCond = r.getConditions();
		this.ruleConc = r.getConclusions();
		this.isNew = false;
		initUI();
		setTitle("Edit Rule");
	}

	private void initUI() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 556, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{

				for (Condition f : conds) {
					if (!isNew) {
						if (ruleCond.contains(f))
							continue;
					}
					leftModel.addElement(f);
				}
				for (Conclusion f : concs) {
					if (!isNew) {
						if (ruleConc.contains(f))
							continue;
					}
					leftModel.addElement(f);
				}

				leftList = new JList(leftModel);
				JScrollPane jsp = new JScrollPane(leftList);
				jsp.setPreferredSize(new Dimension(220, 180));
				panel.add(jsp);
			}
			{
				Box verticalBox = Box.createVerticalBox();
				panel.add(verticalBox);
				{
					JButton btnNewButton = new JButton(">");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object[] selected = leftList.getSelectedValues();
							if (selected.length != 0) {
								for (Object obj : selected) {
									leftModel.removeElement(obj);
									rightModel.addElement(obj);
									if (obj instanceof Condition) {
										ruleCond.add((Condition) obj);
									} else {
										ruleConc.add((Conclusion) obj);
									}
									updateRuleText();
								}
							}
						}
					});
					verticalBox.add(btnNewButton);
					JButton btnNewButton_1 = new JButton("<");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object[] selected = rightList.getSelectedValues();
							if (selected.length != 0) {
								for (Object obj : selected) {
									rightModel.removeElement(obj);
									leftModel.addElement(obj);
									if (obj instanceof Condition) {
										ruleCond.remove((Condition) obj);
									} else {
										ruleConc.remove((Conclusion) obj);
									}
									updateRuleText();
								}
							}
						}
					});
					verticalBox.add(btnNewButton_1);
				}
			}
			{
				if (!isNew) {
					for (Condition f : ruleCond) {
						rightModel.addElement(f);
					}
					for (Conclusion f : ruleConc) {
						rightModel.addElement(f);
					}
				}

				rightList = new JList(rightModel);
				JScrollPane jsp = new JScrollPane(rightList);
				jsp.setPreferredSize(new Dimension(220, 180));
				panel.add(new JScrollPane(jsp));

			}
			{
				textPane = new JTextPane();
				textPane.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(textPane);
				scrollPane.setPreferredSize(new Dimension(450, 60));
				scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				panel.add(scrollPane);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblConditions = new JLabel("Conditions");
				panel.add(lblConditions);
			}
			{
				comboBox = new JComboBox();
				comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						updateRuleText();
					}
				});
				comboBox.setModel(new DefaultComboBoxModel(Operator.values()));
				panel.add(comboBox);
			}
			{
				JLabel label = new JLabel("     ");
				panel.add(label);
			}
			{
				JLabel lblConclusions = new JLabel("Conclusions");
				panel.add(lblConclusions);
			}
			{
				comboBox_1 = new JComboBox();
				comboBox_1.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						updateRuleText();
					}
				});
				comboBox_1.setModel(new DefaultComboBoxModel(Operator.values()));
				panel.add(comboBox_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton((isNew) ? "Create" : "Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (ruleCond.isEmpty() || ruleConc.isEmpty()) {
							JOptionPane.showMessageDialog(main, "Select condition and conclusion!", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (isNew) {
							Rule r = new Rule(id, ruleCond, ruleConc);
							rs.createRule(r);
							main.rb.addRule(r);
							main.updateView();
						} else {

						}
						dispose();
					}
				});
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
		updateRuleText();

	}

	private void updateRuleText() {
		Rule r = new Rule(id, ruleCond, ruleConc);
		r.setCondO((Operator)comboBox.getSelectedItem());
		r.setConcO((Operator)comboBox_1.getSelectedItem());
		textPane.setText(r.toString());
	}

}
