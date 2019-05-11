//Matthew Hofmann
//Final project
//userTickets.java
//5/10/19
//this file is the menu for end user ticket management
import java.awt.GridLayout; 
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class userTickets extends JFrame {
	 public userTickets() throws SQLException {
		  
	        super("End User IT Ticket Management");
	        setSize(500, 200);
	        setLayout(new GridLayout(4, 1));
	        setLocationRelativeTo(null); 
	        
	        //SET UP CONTROLS
	        JLabel action  = new JLabel("Select an option", JLabel.CENTER);
	     
	        JButton btn = new JButton("Submit a Ticket");
	        JButton btn1 = new JButton("Update a Ticket");
	        JButton btn2 = new JButton("View a Ticket");
	        
	      //ADD OBJECTS TO FRAME
	        add(action);
	        add(btn);
	        add(btn1);
	        add(btn2);
	        //action listener for buttons that let the user select an action
	        btn.addActionListener(e-> {
	        	try {
					new Submit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn1.addActionListener(e-> {
	        	try {
					new Update();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn2.addActionListener(e-> {
	        	try {
					new View();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        setVisible(true); 
}}
