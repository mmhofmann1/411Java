//Matthew Hofmann
//Final project
//Update.java
//5/10/19
//this file updates a ticket in the database
import java.awt.GridLayout; 
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class Update extends JFrame {
	 public Update() throws SQLException {
		  
	        super("Update Ticket IT Ticket Management");
	        Dao d = new Dao();
	        setSize(500, 250);
	        setLayout(new GridLayout(5, 2));
	        setLocationRelativeTo(null); 
	        
	        //SET UP CONTROLS
	        JLabel id = new JLabel("Enter id of ticket you wish to update", JLabel.LEFT);
	        id.setBorder(new EmptyBorder(0,10,0,0));
	        JLabel name = new JLabel("Enter new ticket name", JLabel.LEFT);
	        name.setBorder(new EmptyBorder(0,10,0,0));
	        JLabel desc = new JLabel("Enter new ticket description", JLabel.LEFT);
	        desc.setBorder(new EmptyBorder(0,10,0,0));
	        JLabel space = new JLabel("", JLabel.CENTER);
	        JButton btn = new JButton("Submit");
	        JTextField tid  = new JTextField(4);
	        JTextField tname = new JTextField(20);
	        JTextField tdesc  = new JTextField(50);
	        
	      //ADD OBJECTS TO FRAME
	        add(id);
	        add(tid);
	        add(name);
	        add(tname);
	        add(desc);
	        add(tdesc);
	        add(space);
	        add(btn);
	        //listener for button that updates a ticket in the database
	        btn.addActionListener(e-> {
	        	String v = "";
	        	String stid = new String(tid.getText());
	        	String sname = new String(tname.getText());
	        	String sdesc = new String(tdesc.getText());
	        	try {
					d.updateTick(stid, sname, sdesc);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        	v = Dao.baos.toString(); 
	        	if(v.trim().equals("Ticket id does not exist")) {
		        	JOptionPane.showMessageDialog(null, "Ticket id does not exist");
	        	}
	        	if(v.trim().equals("Ticket updated")) {
	        		JOptionPane.showMessageDialog(null, "Ticket Updated");
	        	}
	        });
	        
	        setVisible(true);  
}}
