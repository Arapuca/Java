
//Declaring the imports
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import Utilitaries.Database;

public class Recp extends JFrame {

	// Connecting the database
	Database connectDb = new Database();

	private static final long serialVersionUID = 1L;

	public Recp() {

		connectDb.connect();// Connecting the database

		// Setting the title of the frame
		this.setTitle("Welcome to the CCT Clinic Software!");
		this.getContentPane().setLayout(new BorderLayout());
		// Doing a select in the DB
		connectDb.executeSQL("Select date, name, address, phone from patient");
		String[] columnNames = { "Date of Appointment", "Name", "Address", "Phone Number" }; // Header
																								// of
																								// the
																								// table

		int j = 0;
		int size = 10;//

		Object[][] item = new Object[size][4]; // Creating the table

		try {
			// Inserting the info from the db into the Jtable
			while (connectDb.rs.next()) {

				for (int i = 0; i < 4; i++) {
					item[j][i] = connectDb.rs.getString(i + 1);
					// System.out.println(connectDb.rs.getString(1));
				}

				j++;
			}
		} catch (SQLException e) {
			// handle any errors
			JOptionPane.showMessageDialog(null, " Error");
		}

		// Creating the Jtable Details
		final JTable table = new JTable(item, columnNames);
		table.setRowSelectionAllowed(false);

		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(Color.BLUE));

		// Details of the panel
		JPanel btnPnl = new JPanel(new BorderLayout());
		JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton button = new JButton("Add Patient");
		button.addActionListener(new ActionListener() {

			// Adding an function to the button Add Patient
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		// Details of the panel
		bottombtnPnl.add(button);
		btnPnl.add(bottombtnPnl, BorderLayout.CENTER);
		JButton btnEdit = new JButton("Edit");
		bottombtnPnl.add(btnEdit);

		// Details of the table and button
		table.getTableHeader().setReorderingAllowed(false);

		this.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(table, BorderLayout.CENTER);
		this.getContentPane().add(btnPnl, BorderLayout.SOUTH);

		// Details of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

}