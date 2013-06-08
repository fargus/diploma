package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.home.main.algorithm.AccumulationType;
import com.home.main.algorithm.AggregationType;
import com.home.main.algorithm.Algorithm;
import com.home.main.algorithm.Algorithm2;
import com.home.main.algorithm.ImplicationType;
import com.home.main.db.dao.RuleServiceImpl;
import com.home.main.db.dao.RuleSrvice;
import com.home.main.func.Func;
import com.home.main.fuzzyset.FuzzySet;
import com.home.main.rule.Conclusion;
import com.home.main.rule.Condition;
import com.home.main.rule.Rule;
import com.home.main.rule.RuleBase;
import com.home.main.variable.Variable;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

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
	final MainWindow frame = this;

	public RuleBase rb;
	private RuleSrvice rs = new RuleServiceImpl();
	private Algorithm2 alg = new Algorithm2();
	private Algorithm alg2 = new Algorithm();
	private Map<Integer, Double> inputVal = new HashMap<Integer, Double>();
	private Map<Integer, Double> result = new HashMap<Integer, Double>();

	public Map<Integer, Func> functions = new HashMap<Integer, Func>();
	public Map<Integer, FuzzySet> terms = new HashMap<Integer, FuzzySet>();
	public Map<Integer, Variable> variables = new HashMap<Integer, Variable>();
	public Map<Integer, Condition> conditions = new HashMap<Integer, Condition>();
	public Map<Integer, Conclusion> conclusions = new HashMap<Integer, Conclusion>();

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

	private JPanel fuzzySetTab;
	private DefaultListModel fuzzySetListModel;
	private JList fuzzySetList;
	private JScrollPane fuzzySetScroll;
	private JPanel fuzzySetBtnPanel;
	private JButton createfuzzySetBtn;
	private JButton editfuzzySetBtn;
	private JButton deletefuzzySetBtn;

	private JPanel variableTab;
	private DefaultListModel variableListModel;
	private JList variableList;
	private JScrollPane variableScroll;
	private JPanel variableBtnPanel;
	private JButton createVariableBtn;
	private JButton editVariableBtn;
	private JButton deleteVariableBtn;
	private Box varVerticalBox;
	private FuncPlot varPlot;
	private JPanel varPlotPanel;

	private JPanel ccTab;
	private DefaultListModel ccListModel;
	private JList ccList;
	private JScrollPane ccScroll;
	private JPanel ccBtnPanel;
	private JButton createCCBtn;
	private JButton editCCBtn;
	private JButton deleteCCBtn;
	private Box horizontalBox;
	private JButton btnCreate;
	private JButton btnEdit;
	private JButton btnDelete;
	private JMenu mnTask;
	private JMenuItem mntmEdgeDetection;
	
	private boolean isConnected = false;

	public MainWindow() {
		initUI();
	}

	private void initUI() {
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
		initCCPane();
		initVariablePane();
		initFuzzySetPane();
		initFuncPane();
	}

	private void initMenu() {
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
		
		mnTask = new JMenu("Task");
		menuBar.add(mnTask);
		
		mntmEdgeDetection = new JMenuItem("Edge Detection");
		mntmEdgeDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alg.setRules(rb.getRules());
				alg.setAccumType((AccumulationType) comboBox_2.getSelectedItem());
				alg.setActType((ImplicationType) comboBox_3.getSelectedItem());
				alg.setAggrType((AggregationType) comboBox_1.getSelectedItem());
				ImageWindow frame = new ImageWindow(alg);
				frame.setVisible(true);
			}
		});
		mnTask.add(mntmEdgeDetection);
		
		mnTask.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				mntmEdgeDetection.setEnabled(isConnected);
			}
		});
	}

	private void initControlPane() {
		north = new JPanel();
		north.setBorder(BorderFactory.createLineBorder(Color.gray));
		FlowLayout flowLayout = (FlowLayout) north.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		// contentPane.add(north, BorderLayout.NORTH);
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

	private void initRulePane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane.addTab("Rules", contentPane);

		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);

		center = new JPanel();
		// center.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
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
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuleDialog dialog = new RuleDialog(frame, rs);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		south.add(btnCreate);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rule r = (Rule)list.getSelectedValue();
				if (r == null){
					JOptionPane.showMessageDialog(frame, "Choose rule to edit!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				RuleDialog dialog = new RuleDialog(frame, rs, (Rule)list.getSelectedValue());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		south.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		south.add(btnDelete);

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
		
		horizontalBox = Box.createHorizontalBox();
		east.add(horizontalBox);
		
				btnStart = new JButton("Start");
				horizontalBox.add(btnStart);
				btnStart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						alg2.setRuleBase(rb);
						alg2.setAccumType((AccumulationType) comboBox_2.getSelectedItem());
						alg2.setActType((ImplicationType) comboBox_3.getSelectedItem());
						alg2.setAggrType((AggregationType) comboBox_1.getSelectedItem());
						try {
							alg2.run(inputVal);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						result = alg2.getLastResult();
						showResult();
					}
				});
	}

	private void initFuncPane() {
		funcPanel = new JPanel();
		funcPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		funcPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Functions", funcPanel);

		funcListModel = new DefaultListModel();
		funcList = new JList(funcListModel);
		funcList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					System.out.println(funcList.getSelectedValue());
					plot.clear();
					for (Object f : funcList.getSelectedValues()) {
						plot.addFunction((Func) f);
					}
				}
			}
		});
		// funcList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		funcScroll = new JScrollPane(funcList);
		funcPanel.add(funcScroll, BorderLayout.CENTER);

		funcBtnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		funcBtnPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		funcPanel.add(funcBtnPanel, BorderLayout.SOUTH);
		createFuncBtn = new JButton("Create");
		createFuncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncDialog dialog = new FuncDialog(frame, rs);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);
			}
		});
		editFuncBtn = new JButton("Edit");
		editFuncBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (funcList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Choose func to edit!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					FuncDialog dialog = new FuncDialog(frame, rs, (Func) funcList.getSelectedValue());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(ModalityType.APPLICATION_MODAL);
					dialog.setLocationRelativeTo(frame);
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
		// verticalBox.add(new JLabel("Function"));
		//
		// horizontalBox = Box.createHorizontalBox();
		// verticalBox.add(horizontalBox);
		// JLabel label = new JLabel("a:=");
		// horizontalBox.add(label);
		//
		// lblNewLabel_1 = new JLabel("New label");
		// horizontalBox.add(lblNewLabel_1);
		//
		// horizontalBox_1 = Box.createHorizontalBox();
		// verticalBox.add(horizontalBox_1);
		// JLabel label_1 = new JLabel("b:=");
		// horizontalBox_1.add(label_1);
		//
		// lblNewLabel_2 = new JLabel("New label");
		// horizontalBox_1.add(lblNewLabel_2);
		//
		// horizontalBox_2 = Box.createHorizontalBox();
		// verticalBox.add(horizontalBox_2);
		// JLabel label_2 = new JLabel("c:=");
		// horizontalBox_2.add(label_2);
		//
		// lblNewLabel_3 = new JLabel("New label");
		// horizontalBox_2.add(lblNewLabel_3);
		//
		// horizontalBox_3 = Box.createHorizontalBox();
		// verticalBox.add(horizontalBox_3);
		// JLabel label_3 = new JLabel("d:=");
		// horizontalBox_3.add(label_3);
		funcPanel.add(verticalBox, BorderLayout.EAST);

	}

	private void initFuzzySetPane() {
		fuzzySetTab = new JPanel();
		fuzzySetTab.setBorder(new EmptyBorder(5, 5, 5, 5));
		fuzzySetTab.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("FuzzySets", fuzzySetTab);

		fuzzySetListModel = new DefaultListModel();
		fuzzySetList = new JList(fuzzySetListModel);
		fuzzySetList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

			}
		});
		fuzzySetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fuzzySetScroll = new JScrollPane(fuzzySetList);
		fuzzySetTab.add(fuzzySetScroll, BorderLayout.CENTER);

		fuzzySetBtnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		fuzzySetBtnPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		fuzzySetTab.add(fuzzySetBtnPanel, BorderLayout.SOUTH);
		createfuzzySetBtn = new JButton("Create");
		createfuzzySetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuzzySetDialog dialog = new FuzzySetDialog(frame, rs);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);
			}
		});
		editfuzzySetBtn = new JButton("Edit");
		editfuzzySetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fuzzySetList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Choose fuzzyset to edit!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					FuzzySetDialog dialog = new FuzzySetDialog(frame, rs, (FuzzySet) fuzzySetList.getSelectedValue());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(frame);
					dialog.setVisible(true);
				}
			}
		});
		deletefuzzySetBtn = new JButton("Delete");
		fuzzySetBtnPanel.add(createfuzzySetBtn);
		fuzzySetBtnPanel.add(editfuzzySetBtn);
		fuzzySetBtnPanel.add(deletefuzzySetBtn);

	}

	private void initVariablePane() {
		variableTab = new JPanel();
		variableTab.setBorder(new EmptyBorder(5, 5, 5, 5));
		variableTab.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Variables", variableTab);

		variableListModel = new DefaultListModel();
		variableList = new JList(variableListModel);
		variableList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					varPlot.clear();
					Variable v = ((Variable) variableList.getSelectedValue());
					if (v !=null){
						for (FuzzySet f : v.getTerms()) {
							varPlot.addFunction(f.getFunc());
						}
					}
				}
			}
		});
		variableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		variableScroll = new JScrollPane(variableList);
		variableTab.add(variableScroll, BorderLayout.CENTER);

		variableBtnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		variableBtnPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		variableTab.add(variableBtnPanel, BorderLayout.SOUTH);
		createVariableBtn = new JButton("Create");
		createVariableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VariableDialog dialog = new VariableDialog(frame, new RuleServiceImpl());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);
			}
		});
		editVariableBtn = new JButton("Edit");
		editVariableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (variableList.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Choose variable to edit!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					VariableDialog dialog = new VariableDialog(frame, new RuleServiceImpl(), (Variable) variableList.getSelectedValue());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(frame);
					dialog.setVisible(true);
				}
			}
		});
		deleteVariableBtn = new JButton("Delete");
		variableBtnPanel.add(createVariableBtn);
		variableBtnPanel.add(editVariableBtn);
		variableBtnPanel.add(deleteVariableBtn);

		varPlotPanel = new JPanel();
		varPlot = new FuncPlot();
		varPlotPanel.add(varPlot);
		varVerticalBox = Box.createVerticalBox();
		varVerticalBox.setPreferredSize(new Dimension(200, 150));
		varVerticalBox.add(varPlotPanel);

		variableTab.add(varVerticalBox, BorderLayout.EAST);

	}

	private void initCCPane(){
		ccTab = new JPanel();
		ccTab.setBorder(new EmptyBorder(5, 5, 5, 5));
		ccTab.setLayout(new BorderLayout(0,0));
		tabbedPane.addTab("Conditions/Conclusions", ccTab);
		
		ccListModel = new DefaultListModel();
		ccList = new JList(ccListModel);
		ccList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		ccList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ccScroll = new JScrollPane(ccList);
		ccTab.add(ccScroll, BorderLayout.CENTER);
		
		ccBtnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		ccBtnPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		ccTab.add(ccBtnPanel, BorderLayout.SOUTH);
		createCCBtn = new JButton("Create");
		createCCBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CCDialog dialog = new CCDialog(frame, new RuleServiceImpl());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		editCCBtn = new JButton("Edit");
		editCCBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = ccList.getSelectedValue();
				if (ccList.getSelectedValue() == null){
					JOptionPane.showMessageDialog(frame, "Choose something to edit!", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					CCDialog dialog;
					if (obj instanceof Condition) {
						dialog = new CCDialog(frame, new RuleServiceImpl(), (Condition) obj);
					} else {
						dialog = new CCDialog(frame, new RuleServiceImpl(), (Conclusion) obj);
					}
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		deleteCCBtn = new JButton("Delete");
		ccBtnPanel.add(createCCBtn);
		ccBtnPanel.add(editCCBtn);
		ccBtnPanel.add(deleteCCBtn);
		
	}

	private void showResult() {
		ResultDialog dialog = new ResultDialog(rb.getOutputVars(), result, this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	private void connect() {
		try{
			functions = rs.getAllFunc();
			terms = rs.getAllFuzzySet();
			variables = rs.getAllVariable();
			conditions = rs.getAllCondition();
			conclusions = rs.getAllConclusion();
			rb = rs.getRuleBase();
			isConnected = true;
			updateView();
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Unable to connect!", "Connection error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateView() {
		listModel.clear();
		for (Rule r : rb.getRules()) {
			listModel.addElement(r);
		}
		funcListModel.clear();
		for (Func f : functions.values()) {
			funcListModel.addElement(f);
		}
		fuzzySetListModel.clear();
		for (FuzzySet f : terms.values()) {
			fuzzySetListModel.addElement(f);
		}
		variableListModel.clear();
		for (Variable f : variables.values()) {
			variableListModel.addElement(f);
		}

		ccListModel.clear();
		for (Condition c : conditions.values()) {
			ccListModel.addElement(c);
		}
		for (Conclusion c : conclusions.values()) {
			ccListModel.addElement(c);
		}
	}
}
