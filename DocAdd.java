
//Declaring the imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Utilitaries.Database;

public class DocAdd extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// Connecting the database
	Database connectDb = new Database();

	JTextField patName;
	JTextField precr;
	JTextField drugDetails;

	public DocAdd() {

		connectDb.connect(); // Connecting the database

		// Setting the details of the frame
		this.setTitle("Add Patient");
		this.setSize(425, 147);
		this.setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Setting the details of the frame
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(4, 2);
		panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(400, 100));
		panel.setSize(200, 170);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(layout);

		// Adding items to the panel
		Label name = new Label("Name");
		panel.add(name);

		patName = new JTextField(20);
		panel.add(patName);

		Label prescription = new Label("Prescription");
		panel.add(prescription);

		precr = new JTextField(20);
		panel.add(precr);

		Label drug = new Label("Drug");
		panel.add(drug);

		drugDetails = new JTextField(20);
		panel.add(drugDetails);

		JButton save = new JButton("Save");
		save.addActionListener(this);
		panel.add(save);

		getContentPane().add(panel);
		this.pack();
		this.setVisible(true);

	}
	
	// Giving a function to the save button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		connectDb.connect();
		
		//Saving in the Db
				String sql = ("insert into patient(name,prescription,drug) values (?, ?, ?)");
				try {
					PreparedStatement pst = connectDb.conn.prepareStatement(sql);
					pst.setString(1,patName.getText());
					pst.setString(2,precr.getText());
					pst.setString(3,drugDetails.getText());
					pst.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
				connectDb.executeSQL(sql);
				JOptionPane.showMessageDialog(null, "Saved!");

			}
}
