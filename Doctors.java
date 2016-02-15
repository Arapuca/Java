
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

public class Doctors extends JFrame {

	Database connectDb = new Database(); // Connecting the DB
	private static final long serialVersionUID = 1L;

	public Doctors() {

		// Setting the title of the frame
		this.setTitle("Welcome to the CCT Clinic Software!");

		this.getContentPane().setLayout(new BorderLayout());

		connectDb.connect(); // Connecting the database

		connectDb.executeSQL("Select date, name, prescription, drug from patient"); // Doing
																					// a
																					// Select
																					// in
																					// the
																					// DB

		String[] columnNames = { "Date of Appointment", "Name", "Prescription", "Drug" }; // Header
																							// of
																							// the
																							// table

		int j = 0;
		int size = 10;//

		Object[][] table = new Object[size][4]; // Creating the table

		// populating the table
		try {
			while (connectDb.rs.next()) {

				for (int i = 0; i < 4; i++) {
					table[j][i] = connectDb.rs.getString(i + 1);
					// System.out.println(connectDb.rs.getString(1));
				}

				j++;
			}
		} catch (SQLException e) {
			// handle any errors
			JOptionPane.showMessageDialog(null, " Error");
		}

		// Details of the table
		final JTable table1 = new JTable(table, columnNames);
		table1.setRowSelectionAllowed(false);
		table1.setCellSelectionEnabled(true);
		table1.setColumnSelectionAllowed(true);
		table1.setBorder(new LineBorder(Color.BLUE));

		// Details of the panel
		JPanel btnPnl = new JPanel(new BorderLayout());
		JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton button = new JButton("Edit"); // Creating a button

		// Giving a function to the button
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				DocEdit doc = new DocEdit();

			}
		});

		bottombtnPnl.add(button);
		btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

		JButton btnOk = new JButton("Add");
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DocAdd doc = new DocAdd();
			}
		});
		bottombtnPnl.add(btnOk);

		table1.getTableHeader().setReorderingAllowed(false);

		// Details of the table and buttons
		this.getContentPane().add(table1.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(table1, BorderLayout.WEST);
		this.getContentPane().add(btnPnl, BorderLayout.SOUTH);

		// Details of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

}