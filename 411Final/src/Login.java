//Matthew Hofmann
//Final project
//Login.java
//5/10/19
//This file creates the initial login screen and determines if the user can login with admin or end user credentials
import java.awt.GridLayout; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame{
	static Statement stmt = null;
	static Statement stmt2 = null;
	static DbConnect conn = null;
    public Login() throws SQLException {
  
        super("Login IT Ticket Management");
        Dao d = new Dao();
        conn = new DbConnect();
        setSize(500, 200);
        setLayout(new GridLayout(4, 3));
        setLocationRelativeTo(null); 
        
        //SET UP CONTROLS
        JLabel lblUsername  = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword   = new JLabel("Password", JLabel.LEFT);
        JLabel lblStatus  = new JLabel(" ", JLabel.CENTER);
        JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);
        lblUsername.setBorder(new EmptyBorder(0,10,0,0));
        lblPassword.setBorder(new EmptyBorder(0,10,0,0));
        
        JTextField txtUname  = new JTextField(10);
        JPasswordField txtPassword  = new JPasswordField();
 
     
        JButton btn = new JButton("Login");

        //ADD OBJECTS TO FRAME
        add(lblUsername);
        add(txtUname);
        add(lblPassword);  
        add(txtPassword);
        add(lblSpacer); 
        add(btn);
        add(lblStatus);  
        //listener for submit button for login credentials
        btn.addActionListener(e-> {
        	String passtext = new String(txtPassword.getPassword());
			try {
			stmt = conn.connect().createStatement();
			stmt2 = conn.connect().createStatement();
			ResultSet ars = stmt.executeQuery("SELECT * from m_hofm_users WHERE admin = 1");
			String[] auarray = new String[0];
			String[] aparray = new String[0];
			ArrayList<String> aulist = new ArrayList<String>(Arrays.asList(auarray));
			ArrayList<String> aplist = new ArrayList<String>(Arrays.asList(aparray));
			ars.beforeFirst();
			while (ars.next()) {
				String user = ars.getString("username");
				String pass1 = ars.getString("password");
				String adduser = user;
				String addpass = pass1;
				aulist.add(adduser);
				aplist.add(addpass);
			}
			auarray = aulist.toArray(auarray);
			aparray = aplist.toArray(aparray);
			ResultSet ers = stmt2.executeQuery("SELECT * from m_hofm_users WHERE admin = 0");
			String[] euarray = new String[0];
			String[] eparray = new String[0];
			ArrayList<String> eulist = new ArrayList<String>(Arrays.asList(euarray));
			ArrayList<String> eplist = new ArrayList<String>(Arrays.asList(eparray));
			ers.beforeFirst();
			while (ers.next()) {
				String user = ers.getString("username");
				String pass1 = ers.getString("password");
				String adduser = user;
				String addpass = pass1;
				eulist.add(adduser);
				eplist.add(addpass);
			}
			ars.last();
			ers.last();
			euarray = eulist.toArray(euarray);
			eparray = eplist.toArray(eparray);
			ars.beforeFirst();
			ers.beforeFirst();
			Invalid: {
			while(ars.next() && ers.next()) {
				if(txtUname.getText().equals(ars.getString("username")) && passtext.equals(ars.getString("password"))) {
					dispose();
					new admTickets();
					break Invalid;
				} else if(txtUname.getText().equals(ers.getString("username")) && passtext.equals(ers.getString("password"))) {
					dispose();
					new userTickets();
					break Invalid;
			} 
	        	JOptionPane.showMessageDialog(null, "Invalid login");
			}
			}
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			});
        setVisible(true);  //SHOW THE FRAME
        };
}
