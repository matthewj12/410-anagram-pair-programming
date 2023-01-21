import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class AnagramGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textPath;
	private JTextArea textAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnagramGUI frame = new AnagramGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String formatAnagramsForPrinting(ArrayList<ArrayList<String>> allAnagrams) {
		String toReturn = "";

		for (ArrayList<String> anagrams : allAnagrams) {
			toReturn += anagrams.get(0);

			for (int i = 1; i < anagrams.size(); i++) {
				toReturn += ", " + anagrams.get(i);
			}

			toReturn += "\n";
		}

		return toReturn;
	}


	/**
	 * Create the frame.
	 */
	public AnagramGUI() {
		setTitle("Anagrams");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPath = new JLabel("Enter the path to the text file to analyze:");
		GridBagConstraints gbc_lblPath = new GridBagConstraints();
		gbc_lblPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblPath.gridx = 0;
		gbc_lblPath.gridy = 1;
		panel.add(lblPath, gbc_lblPath);
		
		textPath = new JTextField();
		GridBagConstraints gbc_textPath = new GridBagConstraints();
		gbc_textPath.insets = new Insets(0, 0, 5, 0);
		gbc_textPath.gridwidth = 7;
		gbc_textPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPath.gridx = 1;
		gbc_textPath.gridy = 1;
		panel.add(textPath, gbc_textPath);
		textPath.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnter.gridx = 4;
		gbc_btnEnter.gridy = 2;
		panel.add(btnEnter, gbc_btnEnter);

		
		textAnswer = new JTextArea();
		textAnswer.setEditable(false);
		GridBagConstraints gbc_textAnswer = new GridBagConstraints();
		gbc_textAnswer.gridwidth = 8;
		gbc_textAnswer.insets = new Insets(0, 0, 0, 5);
		gbc_textAnswer.fill = GridBagConstraints.BOTH;
		gbc_textAnswer.gridx = 0;
		gbc_textAnswer.gridy = 3;
		panel.add(textAnswer, gbc_textAnswer);

		// allows the enter button to add text to the textAnswer field
		btnEnter.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				String path = textPath.getText(); //gets the text from the path field
				ArrayList<ArrayList<String>> anagrams = new ArrayList<ArrayList<String>>();

				try {
					anagrams = AnagramFinder.findAnagrams(path);
				}
				catch (IOException p) {
					p.printStackTrace();
				}

				String output = formatAnagramsForPrinting(anagrams);
				textAnswer.setText(output);
			}  
		}); 
	}
	
}
