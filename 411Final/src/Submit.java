//Matthew Hofmann
//Final project
//Submit.java
//5/10/19
//this file submits a new ticket to the database
import java.awt.GridLayout; 
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class Submit extends JFrame {
	 public Submit() throws SQLException {
		  
	        super("Submit Ticket IT Ticket Management");
	        Dao d = new Dao();
	        setSize(500, 200);
	        setLayout(new GridLayout(4, 2));
	        setLocationRelativeTo(null); 
	        
	        //SET UP CONTROLS
	        JLabel name = new JLabel("Enter a name", JLabel.LEFT);
	        JLabel desc = new JLabel("Enter ticket description", JLabel.LEFT);
	        name.setBorder(new EmptyBorder(0,10,0,0));
	        desc.setBorder(new EmptyBorder(0,10,0,0));
	        JLabel space = new JLabel("", JLabel.CENTER);
	        JButton btn = new JButton("Submit");
	        JTextField tname  = new JTextField(20);
	        JTextField tdesc  = new JTextField(50);
	        
	      //ADD OBJECTS TO FRAME
	        add(name);
	        add(tname);
	        add(desc);
	        add(tdesc);
	        add(space);
	        add(btn);
	        //listener for button that inserts a new ticket into the database
	        btn.addActionListener(e-> {
	        	String sname = new String(tname.getText());
	        	String sdesc = new String(tdesc.getText());
	        	try {
					d.insertTicket(sname, sdesc);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        	JOptionPane.showMessageDialog(null, "Ticket inserted into database");
	        });
	     
	        setVisible(true); 
}}
