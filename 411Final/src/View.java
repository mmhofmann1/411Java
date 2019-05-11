//Matthew Hofmann
//Final project
//View.java
//5/10/19
//this file allows the user to view a row of data
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
public class View extends JFrame {
	static Statement stmt = null;
	static Statement stmt1 = null;
	static DbConnect conn = null;
	
	 public View() throws SQLException {
		  
	        super("View Ticket IT Ticket Management");
	        conn = new DbConnect();
	        Dao d = new Dao();
	        setSize(500, 200);
	        setLayout(new GridLayout(3, 2));
	        setLocationRelativeTo(null); 
	        
	        //SET UP CONTROLS
	        JLabel id = new JLabel("Enter id of ticket you wish to view", JLabel.LEFT);
	        id.setBorder(new EmptyBorder(0,10,0,0));
	        JLabel space = new JLabel("", JLabel.CENTER);
	        JButton btn = new JButton("Submit");
	        JTextField tid  = new JTextField(4);
	        
	      //ADD OBJECTS TO FRAME
	        add(id);
	        add(tid);
	        add(space);
	        add(btn);
	        //listener for button that shows data
	        btn.addActionListener(e-> {
	        	String stid = new String(tid.getText());
	        	try {
					ResultSet rs = d.retrieveTicketRecords(stid);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columns = rsmd.getColumnCount();
					rs.beforeFirst();
					String[] array = new String[0];
					ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
					while (rs.next()) {
					    for (int i = 1; i <= columns; i++) {
					        String data = rs.getString(i);
					        String col = rsmd.getColumnName(i);
					        String combo = col + ": " + data;
					        list.add(combo);
					    }

				}
					stmt1 = conn .connect().createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT * from m_hofm_tickets");
					String[] idarray = new String[0];
					ArrayList<String> idlist = new ArrayList<String>(Arrays.asList(idarray));
					rs1.beforeFirst();
					while (rs1.next()) {
						String id1 = rs1.getString("tid");
						String add = id1;
						idlist.add(add);
					}
					idarray = idlist.toArray(idarray);
					rs1.first();
					if (!rs1.isFirst()) {
						return;
					}
					int i;
					equal: {
						System.out.println(stid);
					for(i = 0; i < idarray.length; i++) 
						if(stid.trim().equals(idarray[i])) {
							break equal;
						}
			        JOptionPane.showMessageDialog(null, "Ticket id does not exist");
					return;
					}
					array = list.toArray(array);
					System.out.println(Arrays.toString(array));
			        JOptionPane.showMessageDialog(null, Arrays.toString(array));
			        
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        });
	     
	        setVisible(true);  //SHOW THE FRAME
}}
