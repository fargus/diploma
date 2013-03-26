package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ListModel;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import org.hibernate.ejb.criteria.predicate.ExistsPredicate;
import org.hibernate.envers.tools.graph.Vertex;

import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Variable;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import com.home.main.algorithm.AccumulationType;
import javax.swing.border.TitledBorder;
import com.home.main.algorithm.AggregationType;
import com.home.main.algorithm.ActivationType;
import com.home.main.algorithm.Algorithm;
import com.home.main.algorithm.ImplicationType;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private RuleBase rb;
	private RuleSrvice rs = new RuleServiceImpl();
	private Algorithm alg = new Algorithm();
	private Map<Integer, Double> inputVal = new HashMap<Integer, Double>();

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem exitItem;

	private JPanel east;
	private JPanel south;
	private JPanel north;
	private JPanel center;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JList list;
	private DefaultListModel<String> listModel;
	private JButton btnStart;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JComboBox comboBox_3;
	private JPanel panel_3;
	private JButton btnSet;

	public MainWindow() {
		setTitle("Universal Fuzzy Logic Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 418);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		mnFile.add(exitItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);

		center = new JPanel();
		center.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5, true));
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		listModel = new DefaultListModel<String>();
		list = new JList(listModel);
		center.add(list);

		south = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) south.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		south.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		contentPane.add(south, BorderLayout.SOUTH);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alg.setRuleBase(rb);
				alg.setAccumType((AccumulationType)comboBox_2.getSelectedItem());
				alg.setActType((ImplicationType)comboBox_3.getSelectedItem());
				alg.setAggrType((AggregationType)comboBox_1.getSelectedItem());
				try {
					alg.run(inputVal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		south.add(btnStart);

		north = new JPanel();
		north.setBorder(BorderFactory.createLineBorder(Color.gray));
		FlowLayout flowLayout = (FlowLayout) north.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(north, BorderLayout.NORTH);

		btnNewButton = new JButton("C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				//rb = rs.getAllRules();
				rb = RuleBase.getTestBase();
				for (Rule r : rb.getRules()){
					listModel.addElement(r.toString());
				}
			}
		});
		btnNewButton.setToolTipText("Connect");
		btnNewButton.setPreferredSize(new Dimension(50, 50));
		north.add(btnNewButton);
		
		final JFrame t = this;
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValuesDialog dialog = new ValuesDialog(rb.getInputVars(), inputVal, t);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setVisible(true);
			}
		});
		btnSet.setToolTipText("Set Input Values");
		btnSet.setPreferredSize(new Dimension(50, 50));
		north.add(btnSet);

		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));

		lblNewLabel = new JLabel("Settings");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setPreferredSize(new Dimension(100, 20));
		east.add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Aggregation Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		east.add(panel_1);

		comboBox_1 = new JComboBox();
		panel_1.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(AggregationType.values()));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Activation Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		east.add(panel_3);
		
		comboBox_3 = new JComboBox();
		panel_3.add(comboBox_3);
		comboBox_3.setModel(new DefaultComboBoxModel(ImplicationType.values()));

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Accumulation Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		east.add(panel);

		comboBox_2 = new JComboBox();
		panel.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(AccumulationType.values()));
	}

}
