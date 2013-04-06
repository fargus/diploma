package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.home.main.db.dao.RuleSrvice;
import com.home.main.func.Func;
import com.home.main.func.FuncType;
import com.home.main.func.FuncUtil;

public class FuncDialog extends JDialog implements ChangeListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5670105779752850821L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FuncDialog dialog = new FuncDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private RuleSrvice rs;
	private Func func = FuncUtil.createFunc(1., 2., FuncType.LINEUP);
	private boolean isNew = true;
	
	private JSpinner aSpin;
	private JSpinner bSpin;
	private JSpinner cSpin;
	private JSpinner dSpin;
	private JComboBox typeBox;
	private FuncPlot plot;
	private JLabel a;
	private JLabel b;
	private JLabel c;
	private JLabel d;

	/**
	 * Create the dialog.
	 */
	public FuncDialog(RuleSrvice rs, Func func){
		this.func = func;
		this.isNew = false;
		this.rs = rs;
		initUI();
		setTitle("Edit Function");
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public FuncDialog(RuleSrvice rs) {
		this.rs=rs;
		initUI();
		setTitle("Create Function");
	}
	
	private void initUI(){
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel = new JLabel("Settings");
				panel.add(lblNewLabel, BorderLayout.NORTH);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new SpringLayout());
				
				a = new JLabel("a=");
				panel_1.add(a);
				aSpin = new JSpinner(new SpinnerNumberModel(1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, .01));
				aSpin.setValue(func.getA());
				aSpin.addChangeListener(this);
				panel_1.add(aSpin);
				
				b = new JLabel("b=");
				panel_1.add(b);
				bSpin = new JSpinner(new SpinnerNumberModel(3, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, .01));
				bSpin.setValue(func.getB());
				bSpin.addChangeListener(this);
				panel_1.add(bSpin);
				
				c = new JLabel("c=");
				panel_1.add(c);
				cSpin = new JSpinner(new SpinnerNumberModel(5, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, .01));
				if (func.getC() != null) {
					cSpin.setValue(func.getC());
				} else {
					c.setVisible(false);
					cSpin.setVisible(false);
				}
				cSpin.addChangeListener(this);
				panel_1.add(cSpin);
				
				d = new JLabel("d=");
				panel_1.add(d);
				dSpin = new JSpinner(new SpinnerNumberModel(4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, .01));
				if (func.getD() != null) {
					
					dSpin.setValue(func.getD());
				} else {
					d.setVisible(false);
					dSpin.setVisible(false);
				}
				dSpin.addChangeListener(this);
				panel_1.add(dSpin);

				SpringUtilities.makeCompactGrid(panel_1, 4, 2, 5, 5, 5, 5);
			}
			{
				typeBox = new JComboBox();
				typeBox.setModel(new DefaultComboBoxModel(FuncType.values()));
				typeBox.setSelectedItem(func.getType());
				typeBox.addItemListener(this);
				panel.add(typeBox, BorderLayout.SOUTH);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			plot = new FuncPlot();
			plot.setSize(300, 220);
			panel.add(plot);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton((isNew)?"Save":"Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (isNew){
							rs.createFunc(func);
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		updateFuncPlot();
	}

	private void updateFuncPlot() {
		func = FuncUtil.createFunc((Double) aSpin.getValue(), (Double) bSpin.getValue(), (cSpin.isVisible()) ? (Double) cSpin.getValue() : null,
				(dSpin.isVisible()) ? (Double) dSpin.getValue() : null, (FuncType) typeBox.getSelectedItem());
		plot.drawFunction(func);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateFuncPlot();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int n = ((FuncType)typeBox.getSelectedItem()).getNumArgs();
		if (n == 2 ){
			c.setVisible(false);
			cSpin.setVisible(false);
			d.setVisible(false);
			dSpin.setVisible(false);
		}
		if(n == 3){
			c.setVisible(true);
			cSpin.setVisible(true);
			d.setVisible(false);
			dSpin.setVisible(false);
		}
		if (n == 4){
			c.setVisible(true);
			cSpin.setVisible(true);
			d.setVisible(true);
			dSpin.setVisible(true);
		}
		updateFuncPlot();
	}
}
