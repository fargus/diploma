package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.home.main.db.dao.RuleSrvice;
import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.fuzzyset.FuzzySetImpl;

public class FuzzySetDialog extends JDialog {

	/**
	 * 
	 */
	private FuzzySet fs;
	private RuleSrvice rs;
	private MainWindow main;
	private boolean isNew = true;
	
	private static final long serialVersionUID = -1702962787244205125L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox comboBox;
	private FuncPlot plot = new FuncPlot();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FuzzySetDialog dialog = new FuzzySetDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public FuzzySetDialog(MainWindow main, RuleSrvice rs) {
		this.main=main;
		this.rs=rs;
		initUI();
		setTitle("Create FuzzySet");
	}
	
	public FuzzySetDialog(MainWindow main, RuleSrvice rs,FuzzySet fs) {
		isNew = false;
		this.main=main;
		this.rs=rs;
		this.fs=fs;
		initUI();
		setTitle("Edit FuzzySet");
	}
	private void initUI(){
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 430, 254);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			Box verticalBox = Box.createVerticalBox();
			contentPanel.add(verticalBox);
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					JLabel lblName = new JLabel("Name");
					horizontalBox.add(lblName);
				}
				{
					textField = new JTextField();
					horizontalBox.add(textField);
					textField.setColumns(10);
				}
			}
			{
				comboBox = new JComboBox(new DefaultComboBoxModel(main.functions.values().toArray()));
				comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						plot.drawFunction((Func)comboBox.getSelectedItem());
					}
				});
				if ( isNew ){
					if (!main.functions.isEmpty()){
						plot.drawFunction((Func)main.functions.values().toArray()[0]);
					}
				}else{
					comboBox.setSelectedItem(fs.getFunc());
					textField.setText(fs.getName());
				}
				verticalBox.add(comboBox);
			}
		}
		{
			plot.setSize(200,120);
			contentPanel.add(plot);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton((isNew)?"Create":"Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(isNew){
							String name = textField.getText();
							if (name == null || name.isEmpty()){
								JOptionPane.showMessageDialog(main, "FuzzySet name null or empty!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							fs = new FuzzySetImpl(name, (Func)comboBox.getSelectedItem());
							rs.createFuzzySet(fs);
							main.terms.put(fs.getId(), fs);
							main.updateView();
							
						}else{
							
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
	
	}
}
