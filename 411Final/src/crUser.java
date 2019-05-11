//Matthew Hofmann
//Final project
//crUser.java
//5/10/19
//this file creates users 
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class crUser extends JFrame{ 
    public crUser() throws SQLException {
      
    	super("Create User IT Ticket Management");
        Dao d = new Dao();
        setSize(500, 250);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(null); 
    
        //SET UP CONTROLS
        JLabel lblUsername  = new JLabel("Enter a Username", JLabel.LEFT);
        JLabel lblPassword   = new JLabel("Enter a Password", JLabel.LEFT);
        JLabel lblAdmin   = new JLabel("Admin Privileges (1 or 0)", JLabel.LEFT);
        JLabel lblStatus  = new JLabel(" ", JLabel.CENTER);
        JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);
        lblUsername.setBorder(new EmptyBorder(0,10,0,0));
        lblPassword.setBorder(new EmptyBorder(0,10,0,0));
        lblAdmin.setBorder(new EmptyBorder(0,10,0,0));
        
        JTextField txtUname  = new JTextField(20);
        JPasswordField txtPassword  = new JPasswordField();
        JTextField txtAdmin  = new JTextField(1);
        
     
        JButton btn = new JButton("Login");

        //ADD OBJECTS TO FRAME
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
        	String sname = new String(txtUname.getText());
        	String sdesc = new String(txtPassword.getPassword());
        	String sadmin = new String(txtAdmin.getText());
        	try {
				d.insertUser(sname, sdesc, sadmin);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	v = Dao.baos.toString(); 
        	if(v.trim().equals("Username already exists")) {
	        	JOptionPane.showMessageDialog(null, "Username already exists");
        	} else {
        		JOptionPane.showMessageDialog(null, "User inserted into database");
        	}
        });
        setVisible(true);  //SHOW THE FRAME
        };
}
