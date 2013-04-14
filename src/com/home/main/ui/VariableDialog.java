package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.variable.Variable;

public class VariableDialog extends JDialog implements ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5201032157934201411L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VariableDialog dialog = new VariableDialog(null, new RuleServiceImpl());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private MainWindow main;
	private RuleSrvice rs;
	
	private String nameVal = "";
	private double minVal = 0;
	private double maxVal = 100;
	private Set<FuzzySet> termsVal = new HashSet<FuzzySet>();
	private Variable var;
	private Integer id;
	
	private Collection<FuzzySet> terms;
	private boolean isNew = true;

	private JTextField name;
	private JSpinner min;
	private JSpinner max;
	
	private JList leftList;
	private JList rightList;
	private DefaultListModel leftModel = new DefaultListModel();
	private DefaultListModel rightModel = new DefaultListModel();
	
	private FuncPlot plot = new FuncPlot();
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public VariableDialog(MainWindow main, RuleSrvice rs) {
		this.main = main;
		this.rs=rs;
		this.terms = rs.getAllFuzzySet().values();
		initUI();
		setTitle("Create Variable");
	}
	
	public VariableDialog(MainWindow main, RuleSrvice rs, Variable var) {
		this.main = main;
		this.rs=rs;
		this.terms = rs.getAllFuzzySet().values();
		this.nameVal = var.getName();
		this.minVal = var.getMin();
		this.maxVal = var.getMax();
		this.termsVal.addAll(var.getTerms());
		isNew = false;
		this.id = var.getId();
		initUI();
		setTitle("Edit Variable");
	}
	
	private void initUI(){
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 556, 530);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			
			panel.add(new JLabel("Name"));
			name = new JTextField();
			name.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void removeUpdate(DocumentEvent arg0) {
					update();
				}
				@Override
				public void insertUpdate(DocumentEvent arg0) {
					update();
				}
				@Override
				public void changedUpdate(DocumentEvent arg0) {
					update();
				}
				public void update(){
					nameVal = name.getText();
				}
			});
			name.setColumns(10);
			name.setText(nameVal);
			panel.add(name);
			min = new JSpinner(new SpinnerNumberModel(minVal, Double.NEGATIVE_INFINITY ,Double.POSITIVE_INFINITY, 1));
			min.addChangeListener(this);
			JComponent editor = min.getEditor();
			JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setColumns(4);
			panel.add(min);
			panel.add(new JLabel("Min"));
			max = new JSpinner(new SpinnerNumberModel(maxVal, Double.NEGATIVE_INFINITY ,Double.POSITIVE_INFINITY, 1));
			max.addChangeListener(this);
			editor = max.getEditor();
			tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setColumns(4);
			panel.add(max);
			panel.add(new JLabel("Max"));
			
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				
				for (FuzzySet f : terms) {
					if (!isNew) {
						if (termsVal.contains(f))
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
							if (selected.length != 0){
								for(Object obj : selected){
									leftModel.removeElement(obj);
									rightModel.addElement(obj);
									termsVal.add((FuzzySet)obj);
								}
								updatePlot();
							}
						}
					});
					verticalBox.add(btnNewButton);
					JButton btnNewButton_1 = new JButton("<");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object[] selected = rightList.getSelectedValues();
							if (selected.length != 0){
								for(Object obj : selected){
									rightModel.removeElement(obj);
									leftModel.addElement(obj);
									termsVal.remove(obj);
								}
								updatePlot();
							}
						}
					});
					verticalBox.add(btnNewButton_1);
				}
			}
			{
				if (!isNew) {
					for(FuzzySet f : termsVal){
						rightModel.addElement(f);
					}
				}
				
				rightList = new JList(rightModel);
				JScrollPane jsp = new JScrollPane(rightList);
				jsp.setPreferredSize(new Dimension(220, 180));
				panel.add(new JScrollPane(jsp));
				
				plot.setSize(500, 220);
				panel.add(plot);
				plot.drawVariable(new Variable(nameVal, termsVal, minVal, maxVal));
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton((isNew)?"Create":"Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (name.getText() == null || name.getText().isEmpty()){
							JOptionPane.showMessageDialog(main, "Name is null or empty!", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (isNew){
							var = new Variable(nameVal, termsVal, minVal, maxVal);
							rs.createVariable(var);
						}else{
							var = new Variable(id, nameVal, termsVal, minVal, maxVal);
							rs.updateVariable(var);
						}
						main.variables.put(var.getId(), var);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		minVal = (Double)min.getValue();
		maxVal = (Double)max.getValue();
		updatePlot();
	}
	
	private void updatePlot(){
		plot.drawVariable(new Variable(nameVal, termsVal, minVal, maxVal));
	}

}
