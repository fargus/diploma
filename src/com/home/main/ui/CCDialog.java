package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.variable.Variable;

public class CCDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5562775911901167897L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CCDialog dialog = new CCDialog(null, new RuleServiceImpl());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private MainWindow main;
	private RuleSrvice rs;
	private Collection<Variable> variables;
	private Variable selectedVar;
	private boolean isNew = true;
	private Integer id;
	
	private DefaultListModel leftModel = new DefaultListModel();
	private DefaultListModel rightModel = new DefaultListModel();
	private JList leftList = new JList(leftModel);
	private JList rightList = new JList(rightModel);
	private JSpinner spinner;
	private JCheckBox chckbxConclusion;
	
	/**
	 * Create the dialog.
	 */
	public CCDialog(MainWindow main, RuleSrvice rs) {
		this.main = main;
		this.rs=rs;
		this.variables = rs.getAllVariable().values();
		initUI();
		setTitle("Create Condition/Conclusion");
	}
	
	public CCDialog(MainWindow main, RuleSrvice rs, Condition cond) {
		this.main = main;
		this.rs=rs;
		this.variables = rs.getAllVariable().values();
		this.isNew = false;
		initUI();
		setTitle("Edit Condition/Conclusion");
		this.id = cond.getId();
		chckbxConclusion.setEnabled(false);
		spinner.setEnabled(false);
		leftList.setSelectedValue(cond.getVar(), true);
		rightList.setSelectedValue(cond.getTerm(), true);
	}
	
	public CCDialog(MainWindow main, RuleSrvice rs, Conclusion conc) {
		this.main = main;
		this.rs=rs;
		this.variables = rs.getAllVariable().values();
		this.isNew = false;
		initUI();
		setTitle("Edit Condition/Conclusion");
		this.id = conc.getId();
		chckbxConclusion.setEnabled(false);
		chckbxConclusion.setSelected(true);
		spinner.setEnabled(true);
		spinner.setValue(conc.getWeight());
		leftList.setSelectedValue(conc.getVar(), true);
		rightList.setSelectedValue(conc.getTerm(), true);
	}

	private void initUI(){
		setResizable(false);
		setBounds(100, 100, 597, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			for(Variable v : variables){
				leftModel.addElement(v);
			}
			leftList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (e.getValueIsAdjusting() == false) {
						System.out.println("Left");
						System.out.println(leftList.getSelectedValue());
						selectedVar = (Variable)leftList.getSelectedValue();
						rightModel.clear();
						if (selectedVar != null){
							for(FuzzySet f : selectedVar.getTerms()){
								rightModel.addElement(f);
							}
						}
					}
				}
			});
			JScrollPane leftScroll = new JScrollPane(leftList);
			JScrollPane rightScroll = new JScrollPane(rightList);
			Dimension minD = new Dimension(280, 170);
			leftScroll.setPreferredSize(minD);
			rightScroll.setPreferredSize(minD);
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightScroll);
			contentPanel.add(splitPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			chckbxConclusion = new JCheckBox("Conclusion");
			chckbxConclusion.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					spinner.setEnabled(!spinner.isEnabled());
				}
			});
			chckbxConclusion.setHorizontalTextPosition(SwingConstants.LEFT);
			buttonPane.add(chckbxConclusion);
			
			spinner = new JSpinner(new SpinnerNumberModel(1, 0, 1, 0.01));
			JComponent editor = spinner.getEditor();
			JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setColumns(3);
			spinner.setEnabled(false);
			buttonPane.add(spinner);
			{
				JButton okButton = new JButton((isNew)?"Create":"Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selectedVar == null){
							JOptionPane.showMessageDialog(main, "Select variable!", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (rightList.getSelectedValue() == null){
							JOptionPane.showMessageDialog(main, "Select term!", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (isNew){
							if (chckbxConclusion.isSelected()){
								Conclusion c = new Conclusion((FuzzySet) rightList.getSelectedValue(), selectedVar);
								c.setWeight((Double)spinner.getValue());
								rs.createConclusion(c);
								main.conclusions.put(c.getId(), c);
							}else{
								Condition c = new Condition((FuzzySet) rightList.getSelectedValue(), selectedVar);
								rs.createCondition(c);
								main.conditions.put(c.getId(), c);
							}
						}else{
							if (chckbxConclusion.isSelected()){
								Conclusion c = new Conclusion(id, (FuzzySet) rightList.getSelectedValue(), selectedVar);
								c.setWeight((Double)spinner.getValue());
								rs.updateConclusion(c);
								main.conclusions.put(c.getId(), c);
							}else{
								Condition c = new Condition(id, (FuzzySet) rightList.getSelectedValue(), selectedVar);
								rs.updateCondition(c);
								main.conditions.put(c.getId(), c);
							}
						}
						main.updateView();
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
				buttonPane.add(cancelButton);
			}
		}
	}
}
