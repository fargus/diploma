package com.home.main.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.home.main.algorithm.Algorithm2;
import com.home.main.image.ColorEdgeDetection;
import com.home.main.image.MonochromeEdgeDetection;

public class ImageWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageWindow frame = new ImageWindow(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private final JFrame frame = this;
	private ImagePanel leftImagePane;
	private ImagePanel rightImagePane;
	private BufferedImage originImage;
	private BufferedImage resultImage;
	private MonochromeEdgeDetection monoEdgeDet;
	private ColorEdgeDetection colorEdgeDet;
	private JProgressBar progressBar;
	private JLabel status;
	private JCheckBoxMenuItem chckbxmntmNoizeRemoving;
	private JLabel resultMsg;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public ImageWindow(Algorithm2 fuzzyLogic) {
		monoEdgeDet = new MonochromeEdgeDetection(fuzzyLogic);
		colorEdgeDet = new ColorEdgeDetection(fuzzyLogic);
		initUI();
		monoEdgeDet.setProgressBar(progressBar);
		monoEdgeDet.setImagePanel(rightImagePane);
		monoEdgeDet.setLable(status);

		colorEdgeDet.setProgressBar(progressBar);
		colorEdgeDet.setImagePanel(rightImagePane);
		colorEdgeDet.setLable(status);
		colorEdgeDet.setMsgLable(resultMsg);
		colorEdgeDet.setButton(btnNewButton);
	}
	
	private void initUI(){
		setTitle("Edge Detection");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenImage = new JMenuItem("Open Image");
		mntmOpenImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int rv = fc.showOpenDialog(frame);
				if (rv == JFileChooser.APPROVE_OPTION){
					try {
						originImage = ImageIO.read(fc.getSelectedFile());
						leftImagePane.setImage(originImage);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame, "Error while open file", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnFile.add(mntmOpenImage);
		
		JMenuItem mntmSaveImage = new JMenuItem("Save Image");
		mntmSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int rv = fc.showSaveDialog(frame);
				if (rv == JFileChooser.APPROVE_OPTION){
					try {
						resultImage = rightImagePane.getImage();
						rightImagePane.setImage(resultImage);
						ImageIO.write(resultImage, "png", fc.getSelectedFile());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame, "Error while open file", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnFile.add(mntmSaveImage);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnAlgorithm = new JMenu("Algorithm");
		menuBar.add(mnAlgorithm);
		
		JRadioButtonMenuItem rdbtnmntmMonochrome = new JRadioButtonMenuItem("Monochrome");
		rdbtnmntmMonochrome.setEnabled(false);
		buttonGroup.add(rdbtnmntmMonochrome);
		mnAlgorithm.add(rdbtnmntmMonochrome);
		
		final JRadioButtonMenuItem rdbtnmntmColor = new JRadioButtonMenuItem("Color");
		rdbtnmntmColor.setSelected(true);
		buttonGroup.add(rdbtnmntmColor);
		mnAlgorithm.add(rdbtnmntmColor);
		
		JMenu mnFiltering = new JMenu("Filtering");
		menuBar.add(mnFiltering);
		
		chckbxmntmNoizeRemoving = new JCheckBoxMenuItem("Noize removing");
		chckbxmntmNoizeRemoving.setSelected(true);
		mnFiltering.add(chckbxmntmNoizeRemoving);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		final JCheckBoxMenuItem chckbxmntmStatistics = new JCheckBoxMenuItem("Statistics");
		chckbxmntmStatistics.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				resultMsg.setVisible(chckbxmntmStatistics.isSelected());
			}
		});
		mnView.add(chckbxmntmStatistics);
		
		final JCheckBoxMenuItem chckbxmntmLiveView = new JCheckBoxMenuItem("Live View");
		chckbxmntmLiveView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				colorEdgeDet.isLiveView(chckbxmntmLiveView.isSelected());
			}
		});
		chckbxmntmLiveView.setSelected(true);
		mnView.add(chckbxmntmLiveView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmShowHelp = new JMenuItem("Help Contents");
		mnHelp.add(mntmShowHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnmntmColor.isSelected()){
					colorEdgeDet.setImage(originImage);
					colorEdgeDet.isRemoveNoize(chckbxmntmNoizeRemoving.isSelected());
					Thread t  = new Thread(colorEdgeDet);
					t.start();
				}else{
					monoEdgeDet.setImage(originImage);
					monoEdgeDet.isRemoveNoize(chckbxmntmNoizeRemoving.isSelected());
					Thread t  = new Thread(monoEdgeDet);
					t.start();
				}
			}
		});
		
		resultMsg = new JLabel("");
		resultMsg.setVisible(false);
		panel.add(resultMsg);
		
		status = new JLabel("");
		panel.add(status);
		
		progressBar = new JProgressBar();
		panel.add(progressBar);
		panel.add(btnNewButton);
		
		leftImagePane = new ImagePanel();
		rightImagePane = new ImagePanel();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftImagePane, rightImagePane);
		splitPane.setResizeWeight(0.5);
		contentPane.add(splitPane, BorderLayout.CENTER);
	}

}
