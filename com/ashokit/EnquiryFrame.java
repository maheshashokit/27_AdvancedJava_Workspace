package com.ashokit;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EnquiryFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
    private String enquiryName;
    private String enquiryEmailId;
    private String enquiryContactNo;
    private String enquiryCourse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnquiryFrame frame = new EnquiryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EnquiryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Welcome To AshokIT Enquiry Form");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titleLabel.setBounds(285, 46, 415, 28);
		contentPane.add(titleLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setForeground(Color.RED);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(372, 130, 73, 28);
		contentPane.add(nameLabel);
		
		textField = new JTextField();
		textField.setBounds(467, 132, 169, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel emailId = new JLabel("EmailID");
		emailId.setForeground(Color.RED);
		emailId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailId.setBounds(361, 190, 73, 28);
		contentPane.add(emailId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(467, 192, 169, 33);
		contentPane.add(textField_1);
		
		JLabel contactNo = new JLabel("ContactNo");
		contactNo.setForeground(Color.RED);
		contactNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contactNo.setBounds(345, 256, 100, 28);
		contentPane.add(contactNo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(467, 258, 169, 33);
		contentPane.add(textField_2);
		
		JLabel course = new JLabel("Courses");
		course.setForeground(Color.RED);
		course.setFont(new Font("Tahoma", Font.PLAIN, 20));
		course.setBounds(361, 322, 100, 28);
		contentPane.add(course);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Changed Dropdown.....");
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Java FullStack", "Pythin FullStack", "Dotnet FullStack", "UI Full Stack"}));
		comboBox.setBounds(467, 324, 169, 33);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Send Enquiry");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getting content of name text field
				enquiryName = textField.getText();
				//getting content of name text field
				enquiryEmailId = textField_1.getText();
				//getting content of name text field
				enquiryContactNo = textField_2.getText();
				//getting selected value from courses dropdown
				enquiryCourse = (String)comboBox.getSelectedItem();
				System.out.println(enquiryName+"===" +enquiryEmailId+"===="+ enquiryContactNo+"==="+ enquiryCourse);
				//we can define one method to save the frame information
				boolean insertStatus = saveEnquiryInformation(enquiryName,enquiryEmailId,enquiryContactNo,enquiryCourse);
				
				if(insertStatus) {
					JOptionPane.showMessageDialog(contentPane,"Your Course Enquiry Sent To AshokIT Admin Team....","Alert",JOptionPane.INFORMATION_MESSAGE);     
				}else {
					JOptionPane.showMessageDialog(contentPane,"Error Occurred During Insertion.....","Alert",JOptionPane.WARNING_MESSAGE); 
				}
			}

		});
		btnNewButton.setBounds(397, 419, 113, 39);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Swiping the data from Frame
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				comboBox.setSelectedIndex(0);
			}
			
		});
		
		btnCancel.setBounds(537, 419, 113, 39);
		contentPane.add(btnCancel);
	}
	
	private boolean saveEnquiryInformation(String enquiryName, String enquiryEmailId, String enquiryContactNo,
			String enquiryCourse) {
		
		boolean insertStatus = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			
			PreparedStatement pstmt = con.prepareStatement("insert into ashokit_enquires values(ashokit_enquires_seq.nextval,?,?,?,?,?,?)");
			){
			
			pstmt.setString(1, enquiryName);
			pstmt.setString(2, enquiryEmailId);
			pstmt.setString(3, enquiryContactNo);
			pstmt.setString(4, enquiryCourse);
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(6, enquiryName);
			
			int rowCount = pstmt.executeUpdate();
			
			insertStatus = rowCount > 0;
			
			return insertStatus;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return insertStatus;
	}
}
