package pack1;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTable table;
	EmployeeDb empDb = new EmployeeDb();
	DefaultTableModel model;

	public void fillTheTable() {
		model.setRowCount(0);
		try {
			
			ResultSet rs = empDb.getEmployees();
			int colNum = rs.getMetaData().getColumnCount();
			
			while(rs.next()) {
				
				Object[] row = new Object[colNum];
				for (int i = 0; i < colNum; i++) {
					row[i] = rs.getObject(i+1);
				}
				model.addRow(row);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm frame = new EmployeeForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public EmployeeForm() throws SQLException {
		setTitle("Employee Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 25, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(78, 22, 32, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 53, 46, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(78, 50, 86, 20);
		contentPane.add(txtName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 82, 71, 14);
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(78, 79, 86, 20);
		contentPane.add(txtSurname);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 116, 71, 14);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(78, 112, 109, 23);
		contentPane.add(rdbtnMale);
		rdbtnMale.setActionCommand("Male");
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(78, 137, 109, 23);
		contentPane.add(rdbtnFemale);
		rdbtnFemale.setActionCommand("Female");

		ButtonGroup gender = new ButtonGroup();
		gender.add(rdbtnFemale);
		gender.add(rdbtnMale);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 171, 71, 14);
		contentPane.add(lblCity);
		
		JComboBox cbCity = new JComboBox();
		cbCity.setBounds(80, 167, 84, 22);
		contentPane.add(cbCity);
		cbCity.addItem("Istanbul");
		cbCity.addItem("Ankara");
		cbCity.addItem("Antalya");
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Employee emp = new Employee();
				emp.setEmpId( Integer.parseInt( txtId.getText() ) );
				emp.setName(txtName.getText());
				emp.setSurname(txtSurname.getText());
				emp.setGender(gender.getSelection().getActionCommand());
				emp.setCity(cbCity.getSelectedItem().toString());
				
				
				empDb.saveEmployee(emp);
				JOptionPane.showMessageDialog(contentPane, "Employee Saved!");
				txtId.setText("");
				txtName.setText("");
				txtSurname.setText("");
				cbCity.setSelectedIndex(0);
				gender.clearSelection();
				fillTheTable();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSave.setBounds(78, 200, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblNewLabel_1 = new JLabel("Employee List");
		lblNewLabel_1.setBounds(207, 25, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 53, 242, 126);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel();
		ResultSet temp_rs = empDb.getEmployees();
		int colNum = temp_rs.getMetaData().getColumnCount();
		
		Object array [] = new Object[colNum];
		for (int i = 0; i < colNum; i++) {
			//model.addColumn(temp_rs.getMetaData().getColumnName(i+1));
			array[i]=temp_rs.getMetaData().getColumnName(i+1);
		}
		
		model.setColumnIdentifiers(array);
		
		
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Get List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fillTheTable();
			}
		});
		btnNewButton.setBounds(207, 187, 132, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow != -1) {
					int empId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
					try {
						empDb.deleteEmployee(empId);
					}catch(SQLException ex)
					{
						ex.printStackTrace();
					}
				}
						

			}

			
		});
		btnDeleteSelected.setBounds(207, 212, 132, 23);
		contentPane.add(btnDeleteSelected);
	}
}
