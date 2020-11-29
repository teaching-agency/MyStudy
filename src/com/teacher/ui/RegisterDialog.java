package com.teacher.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterDialog extends JDialog {
	private JTextField userNameField;
	private JTextField brithdateField;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	private Date brithdate;
	private JPasswordField userPwdField;
	private JPasswordField userPwdConfirmField;
	private JTextField userRealNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterDialog dialog = new RegisterDialog();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public RegisterDialog() {
		setModal(true);//设置模态框
		setBounds(100, 100, 450, 430);
		setTitle("用户注册");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		brithdateField = new JTextField();
		brithdateField.setFont(new Font("新宋体", Font.PLAIN, 14));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -18);
		brithdate = c.getTime();
		brithdateField.setText(sdf.format(brithdate));
		brithdateField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				brithdate = DateDialog.open(brithdate);
				brithdateField.setText(sdf.format(brithdate));
			}
		});
		brithdateField.setEditable(false);
		brithdateField.setColumns(10);
		brithdateField.setBounds(150, 192, 150, 25);
		getContentPane().add(brithdateField);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(15, 15, 135, 25);
		getContentPane().add(label);
		label.setPreferredSize(new Dimension(200,0));
		label.setFont(new Font("新宋体", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		userNameField = new JTextField();
		userNameField.setBounds(150, 15, 200, 25);
		getContentPane().add(userNameField);
		userNameField.setFont(new Font("新宋体", Font.PLAIN, 14));
		userNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setPreferredSize(new Dimension(200, 0));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_1.setBounds(15, 50, 135, 25);
		getContentPane().add(label_1);
		
		userPwdField = new JPasswordField();
		userPwdField.setFont(new Font("新宋体", Font.PLAIN, 14));
		userPwdField.setColumns(10);
		userPwdField.setBounds(150, 50, 200, 25);
		getContentPane().add(userPwdField);
		
		JLabel label_2 = new JLabel("密码确认：");
		label_2.setPreferredSize(new Dimension(200, 0));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_2.setBounds(15, 87, 135, 25);
		getContentPane().add(label_2);
		
		userPwdConfirmField = new JPasswordField();
		userPwdConfirmField.setFont(new Font("新宋体", Font.PLAIN, 14));
		userPwdConfirmField.setColumns(10);
		userPwdConfirmField.setBounds(150, 87, 200, 25);
		getContentPane().add(userPwdConfirmField);
		
		JLabel label_3 = new JLabel("真实姓名：");
		label_3.setPreferredSize(new Dimension(200, 0));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_3.setBounds(15, 122, 135, 25);
		getContentPane().add(label_3);
		
		userRealNameField = new JTextField();
		userRealNameField.setFont(new Font("新宋体", Font.PLAIN, 14));
		userRealNameField.setColumns(10);
		userRealNameField.setBounds(150, 122, 200, 25);
		getContentPane().add(userRealNameField);
		
		JLabel label_4 = new JLabel("性别：");
		label_4.setPreferredSize(new Dimension(200, 0));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_4.setBounds(15, 157, 135, 25);
		getContentPane().add(label_4);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(150, 157, 200, 25);
		getContentPane().add(panel);
		
		JRadioButton manButton = new JRadioButton("男");
		manButton.setSelected(true);
		manButton.setFont(new Font("新宋体", Font.PLAIN, 14));
		panel.add(manButton);
		
		JRadioButton wemanButton = new JRadioButton("女");
		wemanButton.setFont(new Font("新宋体", Font.PLAIN, 14));
		panel.add(wemanButton);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(manButton);
		bg.add(wemanButton);
		
		JLabel label_5 = new JLabel("出生日期：");
		label_5.setPreferredSize(new Dimension(200, 0));
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_5.setBounds(15, 192, 135, 25);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("籍贯：");
		label_6.setPreferredSize(new Dimension(200, 0));
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_6.setBounds(15, 227, 135, 25);
		getContentPane().add(label_6);
		
		JComboBox addressBox = new JComboBox();
		addressBox.setModel(new DefaultComboBoxModel(new String[] {"请选择", "湖南", "湖北", "广州", "广西", "河南", "河北"}));
		addressBox.setFont(new Font("新宋体", Font.PLAIN, 14));
		addressBox.setBounds(150, 227, 127, 21);
		getContentPane().add(addressBox);
		
		JLabel label_7 = new JLabel("备注：");
		label_7.setPreferredSize(new Dimension(200, 0));
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_7.setBounds(15, 262, 135, 25);
		getContentPane().add(label_7);
		
		JTextArea memoArea = new JTextArea();
		memoArea.setBounds(150, 263, 200, 76);
		getContentPane().add(memoArea);
		
		JButton button = new JButton("注册");
		button.setFont(new Font("新宋体", Font.PLAIN, 14));
		button.setBounds(150, 349, 93, 28);
		getContentPane().add(button);
		
		

	}
}
