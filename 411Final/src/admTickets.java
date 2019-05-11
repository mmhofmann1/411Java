//Matthew Hofmann
//Final project
//admTickets.java
//5/10/19
//this file is the menu for admin ticket management
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class admTickets extends JFrame {
	 public admTickets() throws SQLException {
		  
	        super("Admin IT Ticket Management");
	        Dao d = new Dao();
	        setSize(500, 400);
	        setLayout(new GridLayout(4, 3));
	        setLocationRelativeTo(null); //centers window
	        
	        //SET UP CONTROLS
	        JLabel action  = new JLabel("Select an option", JLabel.CENTER);
	        JLabel space1  = new JLabel("", JLabel.CENTER);
	        JLabel space2  = new JLabel("", JLabel.CENTER);
	     
	        JButton btn = new JButton("Submit a Ticket");
	        JButton btn1 = new JButton("Update a Ticket");
	        JButton btn2 = new JButton("View a Ticket");
	        JButton btn8 = new JButton("View all Tickets");
	        JButton btn3 = new JButton("Close a Ticket");
	        JButton btn4 = new JButton("Delete a Ticket");
	        JButton btn5 = new JButton("Create a User");
	        JButton btn6 = new JButton("Update a User");
	        JButton btn7 = new JButton("Delete a User");
	        
	      //ADD OBJECTS TO FRAME
	        add(space1);
	        add(action);
	        add(space2);
	        add(btn);
	        add(btn1);
	        add(btn2);
	        add(btn3);
	        add(btn8);
	        add(btn4);
	        add(btn5);
	        add(btn6);
	        add(btn7);
	        
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
	        btn3.addActionListener(e-> {
	        	try {
					new Close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn4.addActionListener(e-> {
	        	try {
					new Delete();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn5.addActionListener(e-> {
	        	try {
					new crUser();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn6.addActionListener(e-> {
	        	try {
					new upUser();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn7.addActionListener(e-> {
	        	try {
					new deUser();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	        btn8.addActionListener(e-> {
					try {
						d.retrieveAllTickets();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	        });
	        
	        setVisible(true);  
}}
