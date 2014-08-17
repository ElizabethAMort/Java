import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */
public class OrganizerGUI extends JFrame implements ActionListener {
	/*
	 * This class is a superclass for the GUI windows. It extends JFrame and then
	 * controls aspects of the other windows that are common to all the GUI windows.
	 */
	private static final long serialVersionUID = 1L;
	protected static JLabel messageLabel;
	private JButton cancelButton;
	private JPanel southPanel;
	private JPanel northPanel;
	

	public OrganizerGUI() {
		final int WINDOW_WIDTH = 350;
		final int WINDOW_HEIGHT = 250;
		
	/*
	 * Set the title of the window	
	 */
	setTitle("Welcome to the Programmer's Bank!");
	
	/*
	 * Set the window's size
	 */
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	/*
	 * Specify what happens when the close button is clicked
	 */
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	/*
	 * Add a border layout manager to the content pane.
	 */
	setLayout(new BorderLayout());
	
	/*
	 * Build panel and add to the content frame.
	 */
	buildPanel();
	add(northPanel, BorderLayout.NORTH);
	add(southPanel, BorderLayout.SOUTH);
	
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
		 * Create the "Cancel Process" button and add an action listener
		 * to handle when this button is pressed.
		 */
		cancelButton = new JButton("Cancel Process");
		cancelButton.addActionListener(new CancelButtonListener());
		
		northPanel = new JPanel();
		southPanel = new JPanel();
		
		/*
		 * Add components to JPanel
		 */
		northPanel.add(messageLabel);
		southPanel.add(cancelButton);
		
	}
	private class CancelButtonListener implements ActionListener {
		/*
		 * ActionListener that determines if the Cancel button has been
		 * pressed and then closes the window that is open. It then
		 * allows the next customer to be called.
		 */

		public void actionPerformed(ActionEvent e) {
			e.getActionCommand();
			setVisible(false);
			Lobby.nextCustomer();
				
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
