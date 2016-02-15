//Declaring the imports
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import Utilitaries.Database;
import javax.swing.JTable;
import java.sql.SQLException;
import javax.swing.border.LineBorder;

public class Pharmacist extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Connecting the database
	Database connectDb = new Database();

	public Pharmacist() {

		connectDb.connect();
		
		//Setting the details of the frame
		this.setTitle("Welcome to the CCT Clinic Software!");
		this.setSize(220, 270);
		this.getContentPane().setLayout(new BorderLayout());

		connectDb.executeSQL("Select name, prescription from patient");//DOing a select in the Db
		
		//Creating and populating the Jtable
		int j = 0;
		int size = 10;
		String[] columnNames = { "Patient", "Prescription" };
		Object[][] item = new Object[size][2];

		try {
			while (connectDb.rs.next()) {
				for (int i = 0; i < 2; i++) {
					item[j][i] = connectDb.rs.getString(i + 1);

				}

				j++;
			}
		} catch (SQLException e) {
			// handle any errors
			JOptionPane.showMessageDialog(null," Error");
		}

		//Details of the Table
		final JTable table = new JTable(item, columnNames);
		table.setRowSelectionAllowed(false);

		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(Color.BLUE));
		table.getTableHeader().setReorderingAllowed(false);

		this.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		this.getContentPane().add(table, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

}