//Matthew Hofmann
//Final project
//upUser.java
//5/10/19
//this file updates a user in the database
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class upUser extends JFrame{ 
    public upUser() throws SQLException {
  
        super("Update User IT Ticket Management");
        Dao d = new Dao();
        setSize(500, 250);
        setLayout(new GridLayout(6, 2));
        setLocationRelativeTo(null); 
        
        //SET UP CONTROLS
        JLabel lblUsername  = new JLabel("Enter a new Username", JLabel.LEFT);
        JLabel lblPassword   = new JLabel("Enter a new Password", JLabel.LEFT);
        JLabel lblAdmin   = new JLabel("Admin Privileges (1 or 0)", JLabel.LEFT);
        JLabel lblid = new JLabel("Enter id of user you wish to update", JLabel.LEFT);
        JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);
        lblUsername.setBorder(new EmptyBorder(0,10,0,0));
        lblPassword.setBorder(new EmptyBorder(0,10,0,0));
        lblAdmin.setBorder(new EmptyBorder(0,10,0,0));
        lblid.setBorder(new EmptyBorder(0,10,0,0));
        
        JTextField txtUname  = new JTextField(20);
        JPasswordField txtPassword  = new JPasswordField();
        JTextField txtAdmin  = new JTextField(1);
        JTextField txtid = new JTextField(5);
        
     
        JButton btn = new JButton("Update");

        //ADD OBJECTS TO FRAME
        add(lblid);
        add(txtid);
        add(lblUsername);
        add(txtUname);
        add(lblPassword);  
        add(txtPassword);
        add(lblAdmin);
        add(txtAdmin);
        add(lblSpacer); 
        add(btn); 
        //listener for submit button for login credentials
        btn.addActionListener(e-> {
        	String v = "";
        	String stid = new String(txtid.getText());
        	String sname = new String(txtUname.getText());
        	String sdesc = new String(txtPassword.getPassword());
        	String sadmin = new String(txtAdmin.getText());
        	try {
				d.updateUser(stid, sname, sdesc, sadmin);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	v = Dao.baos.toString(); 
        	 if(v.trim().equals("User id does not exist")) {
 	        	JOptionPane.showMessageDialog(null, "User id does not exist");
         	} else if(v.trim().equals("Username already exists")) {
	        	JOptionPane.showMessageDialog(null, "Username already exists");
        	}	else if (v.trim().equals("Invalid key input")){
	        	JOptionPane.showMessageDialog(null, "Invalid key input");

        	} else {
        		JOptionPane.showMessageDialog(null, "User updated");
        	}
        });
        setVisible(true);  //SHOW THE FRAME
        };
}
