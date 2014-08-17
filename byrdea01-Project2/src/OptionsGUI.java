import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */
public class OptionsGUI extends OrganizerGUI {
	/**
	 * This class is responsible for the window that allows the user
	 * to select what they would like to do in the bank. The selections
	 * include "open an account", "close an account", and "access an account."
	 * This class extends the OrganizerGUI class.
	 */
	private static final long serialVersionUID = 1L;
	Lobby bankLobby = new Lobby();
	private JPanel centerPanel;				//to reference a panel in the center of the border layout
	private JRadioButton openButton;		//to reference a radio button to open an account
	private JRadioButton closeButton;		//to reference a radio button to close an account
	private JRadioButton accessButton;		//to reference a radio button to access an account
	private ButtonGroup radioButtonGroup;  //to reference a radio button group
	
	/*
	 * Constructor
	 */
	public OptionsGUI() {
		final int WINDOW_WIDTH = 350;
		final int WINDOW_HEIGHT = 250;
	
	/*
	 * Set the window's size
	 */
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	/*
	 * Add a grid layout manager to the content pane.
	 */
	GridLayout gridLay = new GridLayout(3, 1);
	
	/*
	 * Build panel and add to the content frame.
	 */
	buildPanel();
	centerPanel.setLayout(gridLay);
	add(centerPanel, BorderLayout.CENTER);
	
	/*
	 * Display the window
	 */

	setVisible(true);
	
}
	private void buildPanel() {
		/*
		 * Create a message label to provide instructions to the user
		 */
		messageLabel = new JLabel("Enter information for the required process.");
		
		/*
		 * Create the radio buttons for the user to select their desired operation.
		 */
		openButton = new JRadioButton("Open a new account");
		closeButton = new JRadioButton("Close your account");
		accessButton = new JRadioButton("Access your account");
		
		/*
		 * Group the radio buttons
		 */
		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(openButton);
		radioButtonGroup.add(closeButton);
		radioButtonGroup.add(accessButton);
		
		/*
		 * Add action listeners to the radio buttons
		 */
		openButton.addActionListener(new RadioButtonListener());
		closeButton.addActionListener(new RadioButtonListener());
		accessButton.addActionListener(new RadioButtonListener());
		
		
		new JPanel();
		centerPanel = new JPanel();
		
		/*
		 * Add components to JPanel
		 */
		centerPanel.add(openButton);
		centerPanel.add(closeButton);
		centerPanel.add(accessButton);
		
		
	}
	

	private class RadioButtonListener implements ActionListener {
			/*
			 * Action listener that determines which option the user
			 * selected and then calls the appropriate method from the 
			 * Lobby.
			 */
		public void actionPerformed(ActionEvent e) {
			
			/*
			 * Determine which radio button was pushed.
			 */
			if (e.getSource() == openButton) {
				try {
					Lobby.openAccount();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == closeButton) {
				setVisible(false);
				Lobby.closeAccount();
				
			}
			else if (e.getSource() == accessButton){
				Lobby.accessAccount();
			}
			setVisible(false);
		}
	}
		
	}
	