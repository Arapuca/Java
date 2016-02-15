
//Declaring the imports
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Utilitaries.Database;

public class Login extends JFrame implements ActionListener {

	JTextField userName = null;
	JTextField password = null;

	Database connectDb = new Database();
	private static final long serialVersionUID = 1L;

	public Login() {

		connectDb.connect(); // Connecting the database

		// Setting the details of the frame
		this.setTitle("Welcome to the CCT Clinic Software!");
		this.setSize(425, 147);
		this.setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Creating the Panel and setting the configuration
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(3, 2);
		panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(400, 100));
		panel.setSize(200, 170);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(layout);

		//Inserting the items in the panel
		Label user = new Label("user");
		panel.add(user);

		userName = new JTextField(20);
		panel.add(userName);

		Label pass = new Label("password");
		panel.add(pass);

		password = new JPasswordField(20);
		panel.add(password);

		JButton login = new JButton("Login!");
		login.addActionListener(this); // Giving a function for the button
		panel.add(login);// Add the button in the panel

		getContentPane().add(panel);
		this.pack();
		this.setVisible(true);

	}
	
	//Main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		try {
			//Doing the select in the DB
			connectDb.executeSQL("select * from guilogin where username = '" + userName.getText() + "' and password = '"
					+ password.getText() + "';");

			// loop over results

			while (connectDb.rs.next()) {

				String type = connectDb.rs.getString("type");
				
				//Sending the user for the correct class
				if (type.equals("doctor")) {
					Doctors doc = new Doctors();
				} else if (type.equals("pharm")) {
					Pharmacist p = new Pharmacist();
				} else if (type.equals("recp")) {
					Recp r = new Recp();
				}

			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

}
