import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


public class MTHClass extends JPanel implements ActionListener  {
	public static boolean RIGHT_TO_LEFT = false;
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected static JTextField textField;
		protected JTextArea textArea;
		protected JScrollPane scrollPane;
		private static String newline = "\n";
		protected JButton button;
		protected JLabel topLabel;
		protected JLabel centerLabel; 
		ArrayList<String> studentID = new ArrayList<String>();
		
	public MTHClass(Container pane){
		
		if(!(pane.getLayout() instanceof BorderLayout)){
			pane.add(new JLabel("Container doesn't use border layout!"));
		}
		
		if(RIGHT_TO_LEFT){
			pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		}
		
		textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(60, 60));
		textField.addActionListener(this);
		textField.setBorder(BorderFactory.createTitledBorder("Enter Student ID Number:"));
		pane.add(textField, BorderLayout.PAGE_START);
		
         textArea = new JTextArea(15, 25);
         textArea.setEditable(false);
         textArea.setBorder(BorderFactory.createTitledBorder("Scanned Student IDs:"));
         JScrollPane scrollPane = new JScrollPane(textArea);
         pane.add(scrollPane, BorderLayout.LINE_END);
		
         button = new JButton("Draw Random ID");
       button.setPreferredSize(new Dimension(150, 30));
       
       button.setVerticalTextPosition(SwingConstants.CENTER);
       button.setMnemonic(KeyEvent.VK_D);
       button.setActionCommand("randomize");
       button.setContentAreaFilled(false);
       button.addActionListener(this);
       button.setToolTipText("Press this button to randomly draw a student ID");
       button.setBorder(BorderFactory.createRaisedBevelBorder());
       pane.add(button, BorderLayout.PAGE_END);
       
       
       ImageIcon image = new ImageIcon("./Images/mastodonText.png");
       JLabel graphic = new JLabel(image);
     
       pane.add(graphic, BorderLayout.LINE_START);
         
    }
	

    public void actionPerformed(ActionEvent evt) {
    	ImageIcon icon = new ImageIcon("./Images/mastodon.png");
        String text = textField.getText();
        textArea.append(text + newline);
        textField.selectAll();
        studentID.add(text);
        textField.setText("");

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
        
        
        if("randomize".equals(evt.getActionCommand())){
        	Random randNum = new Random();
			int randStudentID = randNum.nextInt(studentID.size());
			
			JOptionPane.showMessageDialog(null, "Congrats " + studentID.get(randStudentID) + "!", "Mastodon Tech Hub", 1, icon);
			System.exit(0);
			
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Mastodon Tech Hub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        //Add contents to the window.
        frame.add(new MTHClass(frame));
        frame.setPreferredSize(new Dimension(500, 500));
        
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }
 
   
    
	public static void main(String[] args){
		final ImageIcon icon = new ImageIcon("/Images/mastodon.png");
		int delay = 1000 * 60 * 30; //30 minutes
		Timer timer;
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  JOptionPane.showMessageDialog(null, "Time's Up!", "Mastodon Tech Hub", 1, icon);
		    	  textField.setEditable(false);
		      }
		  };
		  timer = new Timer(delay, taskPerformer);
		  timer.setRepeats(false);
		  timer.start();
		  
		 
		 
		
	javax.swing.SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			 			
			//Nimbus Look and Feel
			try {
			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			        if ("Nimbus".equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
			} catch (UnsupportedLookAndFeelException e) {
			    // handle exception
			} catch (ClassNotFoundException e) {
			    // handle exception
			} catch (InstantiationException e) {
			    // handle exception
			} catch (IllegalAccessException e) {
			    // handle exception
			}
			createAndShowGUI();
		}	
	});
	
	
	}

	
}

