package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.home.main.algorithm.AccumulationType;
import com.home.main.algorithm.AggregationType;
import com.home.main.algorithm.Algorithm;
import com.home.main.algorithm.ImplicationType;
import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.func.Func;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import javax.swing.Box;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1726502060471838452L;
	
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
	final JFrame frame = this;
	
	private RuleBase rb;
	private RuleSrvice rs = new RuleServiceImpl();
	private Algorithm alg = new Algorithm();
	private Map<Integer, Double> inputVal = new HashMap<Integer, Double>();
	private Map<Integer, Double> result = new HashMap<Integer, Double>();
	
	private List<Func> functions = new ArrayList<Func>();
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem exitItem;

	private JPanel mainPane;
	private JPanel contentPane;
	private JPanel east;
	private JPanel south;
	private JPanel north;
	private JPanel center;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JList list;
	private DefaultListModel listModel;
	private JButton btnStart;
	private JPanel panel;
	private JPanel panel_1;
	private JComboBox comboBox_3;
	private JPanel panel_3;
	private JButton btnSet;
	private JScrollPane ruleScroll;
	
	private JTabbedPane tabbedPane;
	
	private JPanel funcPanel;
	private DefaultListModel funcListModel;
	private JList funcList;
	private JScrollPane funcScroll;
	private JPanel funcBtnPanel;
	private JButton createFuncBtn;
	private JButton editFuncBtn;
	private JButton deleteFuncBtn;
	private Box verticalBox;
	private FuncPlot plot;
	private JPanel plotPanel;
	
	public MainWindow() {
		initUI();
	}
	
	private void initUI(){
		setTitle("Universal Fuzzy Logic Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 455);
		
		initMenu();
		
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPane.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainPane.add(tabbedPane, BorderLayout.CENTER);
		
		initControlPane();
		initRulePane();
		initFuncPane();
	}
	
	private void initMenu(){
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
	}
	
	private void initControlPane(){
		north = new JPanel();
		north.setBorder(BorderFactory.createLineBorder(Color.gray));
		FlowLayout flowLayout = (FlowLayout) north.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		//contentPane.add(north, BorderLayout.NORTH);
		mainPane.add(north, BorderLayout.NORTH);
		
		btnNewButton = new JButton("C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		btnNewButton.setToolTipText("Connect");
		btnNewButton.setPreferredSize(new Dimension(50, 50));
		north.add(btnNewButton);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValuesDialog dialog = new ValuesDialog(rb.getInputVars(), inputVal, frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);
			}
		});
		btnSet.setToolTipText("Set Input Values");
		btnSet.setPreferredSize(new Dimension(50, 50));
		north.add(btnSet);
	}
	
	private void initRulePane(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane.addTab("Rules", contentPane);

		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);

		center = new JPanel();
		//center.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		listModel = new DefaultListModel();
		list = new JList(listModel);
		ruleScroll = new JScrollPane(list);
		center.add(ruleScroll);

		south = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) south.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		south.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
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
					e1.printStackTrace();
				}
				result = alg.getLastResult();
				showResult();
			}
		});
		south.add(btnStart);

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
	
	private void initFuncPane(){
		funcPanel = new JPanel();
		funcPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		funcPanel.setLayout(new BorderLayout(0,0));
		tabbedPane.addTab("Functions", funcPanel);
		
		funcListModel = new DefaultListModel();
		funcList = new JList(funcListModel);
		funcList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					System.out.println(funcList.getSelectedValue());
					plot.clear();
					for(Object f : funcList.getSelectedValues()){
						plot.addFunction((Func)f);
					}
				}
			}
		});
		//funcList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		funcScroll = new JScrollPane(funcList);
		funcPanel.add(funcScroll, BorderLayout.CENTER);
		
		funcBtnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		funcBtnPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		funcPanel.add(funcBtnPanel, BorderLayout.SOUTH);
		createFuncBtn = new JButton("Create");
		createFuncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncDialog dialog = new FuncDialog(rs);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setVisible(true);
			}
		});
		editFuncBtn = new JButton("Edit");
		editFuncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (funcList.getSelectedValue() == null){
					JOptionPane.showMessageDialog(frame, "Choose func to edit!", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					FuncDialog dialog = new FuncDialog(rs,(Func) funcList.getSelectedValue());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
				}
			}
		});
		deleteFuncBtn = new JButton("Delete");
		funcBtnPanel.add(createFuncBtn);
		funcBtnPanel.add(editFuncBtn);
		funcBtnPanel.add(deleteFuncBtn);
		
		plotPanel = new JPanel();
		plot = new FuncPlot();
		plotPanel.add(plot);
		verticalBox = Box.createVerticalBox();
		verticalBox.setPreferredSize(new Dimension(200, 150));
		verticalBox.add(plotPanel);
		verticalBox.add(new JLabel("Function"));
		verticalBox.add(new JLabel("a:="));
		verticalBox.add(new JLabel("b:="));
		verticalBox.add(new JLabel("c:="));
		verticalBox.add(new JLabel("d:="));
		funcPanel.add(verticalBox, BorderLayout.EAST);
		
		
	}
	
	private void showResult(){
		ResultDialog dialog = new ResultDialog(rb.getOutputVars(), result, this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}
	
	private void connect(){
		functions = rs.getAllFunc();
		rb = rs.getRuleBase();
		updateView();
	}

	private void updateView(){
		listModel.clear();
		for (Rule r : rb.getRules()){
			listModel.addElement(r);
		}
		funcListModel.clear();
		for (Func f : functions){
			funcListModel.addElement(f);
		}
	}
}
