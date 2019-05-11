//Matthew Hofmann
//Final project
//Delete.java
//5/10/19
//this file creates the window to delete a ticket
import java.awt.GridLayout; 
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class Delete extends JFrame {
	 public Delete() throws SQLException {
		  
	        super("Delete Ticket IT Ticket Management");
	        Dao d = new Dao();
	        setSize(500, 200);
	        setLayout(new GridLayout(3, 2));
	        setLocationRelativeTo(null);
	        
	        //SET UP CONTROLS
	        JLabel id = new JLabel("Enter id of ticket you wish to delete", JLabel.LEFT);
	        id.setBorder(new EmptyBorder(0,10,0,0));
	        JLabel space = new JLabel("", JLabel.CENTER);
	        JButton btn = new JButton("Submit");
	        JTextField tid  = new JTextField(4);
	        
	      //ADD OBJECTS TO FRAME
	        add(id);
	        add(tid);
	        add(space);
	        add(btn);
	        //listener for button that deletes ticket and displays a message
	        btn.addActionListener(e-> {
	        	String v = "";
	        	String stid = new String(tid.getText());
	        	try {
					d.deleteTicket(stid);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        	v = Dao.baos.toString(); 
	        	if(v.trim().equals("Ticket id does not exist")) {
		        	JOptionPane.showMessageDialog(null, "Ticket id does not exist");
	        	}
	        	if(v.trim().equals("Ticket deleted")) {
	        		JOptionPane.showMessageDialog(null, "Ticket deleted");
	        	}
	        });
	     
	        setVisible(true);  
}}
