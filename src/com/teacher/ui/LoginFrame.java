package com.teacher.ui;

import com.teacher.utils.MD5Util;
import com.teacher.dao.UserInfoDAO;
import com.teacher.entity.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	
	public static UserInfo user=null;
	
	private static UserInfoDAO dao = new UserInfoDAO();

	private JPanel contentPane;
	private JTextField userNameField;
	private JPasswordField passwordField;



	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/") + "src/imgs/a.png"));
		setForeground(Color.RED);
		setTitle("登录窗口");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 348, 271);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel label = new JLabel("邮箱：");
		label.setFont(new Font("新宋体", Font.PLAIN, 14));
//		label.setIcon(new ImageIcon(LoginFrame2.class.getResource("/imgs/a.png")));
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(37, 69, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(37, 121, 54, 15);
		contentPane.add(label_1);
		
		userNameField = new JTextField();
		userNameField.setFont(new Font("新宋体", Font.PLAIN, 14));
//		textField.setBackground(new Color(0, 0, 0, 0));
		userNameField.setBounds(101, 66, 183, 25);
		userNameField.setText("admin");
		contentPane.add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("新宋体", Font.PLAIN, 14));
		passwordField.setEchoChar('*');
		passwordField.setBounds(101, 118, 183, 25);
		passwordField.setText("123");
		contentPane.add(passwordField);
		
		JButton button = new JButton("登录");
		button.setFont(new Font("新宋体", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userNameField.getText();
				String userPwd = new String(passwordField.getPassword());
				
				UserInfo user = null;
				try {
					 user = dao.selectByUserName(userName);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "账户不存在","登录错误提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(user.getUserPwd().equals(MD5Util.encode(userPwd))){
					LoginFrame.user = user;
					new MainFrame().setVisible(true);
					LoginFrame.this.dispose();
					
				}else{
					JOptionPane.showMessageDialog(null, "密码错误","登录错误提示",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		button.setBounds(101, 167, 69, 27);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.setFont(new Font("新宋体", Font.PLAIN, 14));
		btnNewButton.addActionListener(e->{
			RegisterDialog dialog = new RegisterDialog();
			dialog.setVisible(true);
		});
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(191, 167, 77, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/imgs/snowflake.gif")));
		lblNewLabel.setBounds(0, 0, 342, 242);
		contentPane.add(lblNewLabel);
		
		
	}
}
