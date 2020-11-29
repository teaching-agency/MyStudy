package com.teacher.ui;

import com.teacher.dao.RoleInfoDAO;
import com.teacher.dao.UserInfoDAO;
import com.teacher.entity.RoleInfo;
import com.teacher.entity.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserEditDialog extends JDialog {

	private JTextField userNameField;
	private JTextField brithdateField;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	private Date brithdate;
	private JTextField userRealNameField;

	private UserInfoDAO dao = new UserInfoDAO();
	private RoleInfoDAO dao1 = new RoleInfoDAO();

	private UserInfo userInfo;
	private List<RoleInfo> roleList;

	/**
	 * Create the dialog.
	 */
	public UserEditDialog(String userName) {
		try {
			userInfo = dao.selectById(userName);
			brithdate = userInfo.getUserBirthdate();

			setModal(true);// 设置模态框
			setBounds(100, 100, 450, 430);
			setTitle("修改用户");
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			setLocationRelativeTo(null);
			getContentPane().setLayout(null);

			brithdateField = new JTextField();
			brithdateField.setFont(new Font("新宋体", Font.PLAIN, 14));
			
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
			brithdateField.setBounds(150, 124, 150, 25);
			getContentPane().add(brithdateField);

			JLabel label = new JLabel("用户名：");
			label.setBounds(15, 15, 135, 25);
			getContentPane().add(label);
			label.setPreferredSize(new Dimension(200, 0));
			label.setFont(new Font("新宋体", Font.PLAIN, 14));
			label.setHorizontalAlignment(SwingConstants.RIGHT);

			userNameField = new JTextField();
			userNameField.setEditable(false);
			userNameField.setBounds(150, 15, 200, 25);
			getContentPane().add(userNameField);
			userNameField.setFont(new Font("新宋体", Font.PLAIN, 14));
			userNameField.setColumns(10);
			userNameField.setText(userName);

			JLabel label_3 = new JLabel("真实姓名：");
			label_3.setPreferredSize(new Dimension(200, 0));
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			label_3.setFont(new Font("新宋体", Font.PLAIN, 14));
			label_3.setBounds(15, 54, 135, 25);
			getContentPane().add(label_3);

			userRealNameField = new JTextField();
			userRealNameField.setFont(new Font("新宋体", Font.PLAIN, 14));
			userRealNameField.setColumns(10);
			userRealNameField.setBounds(150, 54, 200, 25);
			getContentPane().add(userRealNameField);
			userRealNameField.setText(userInfo.getUserRealName());

			JLabel label_4 = new JLabel("性别：");
			label_4.setPreferredSize(new Dimension(200, 0));
			label_4.setHorizontalAlignment(SwingConstants.RIGHT);
			label_4.setFont(new Font("新宋体", Font.PLAIN, 14));
			label_4.setBounds(15, 89, 135, 25);
			getContentPane().add(label_4);

			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.setBounds(150, 89, 200, 25);
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
			
			if(userInfo.getUserSex()==true){
				manButton.setSelected(true);
			}else{
				wemanButton.setSelected(true);
			}

			JLabel label_5 = new JLabel("出生日期：");
			label_5.setPreferredSize(new Dimension(200, 0));
			label_5.setHorizontalAlignment(SwingConstants.RIGHT);
			label_5.setFont(new Font("新宋体", Font.PLAIN, 14));
			label_5.setBounds(15, 124, 135, 25);
			getContentPane().add(label_5);

			JLabel label_6 = new JLabel("角色：");
			label_6.setPreferredSize(new Dimension(200, 0));
			label_6.setHorizontalAlignment(SwingConstants.RIGHT);
			label_6.setFont(new Font("新宋体", Font.PLAIN, 14));
			label_6.setBounds(15, 166, 135, 25);
			getContentPane().add(label_6);

			
			roleList = dao1.select(0, 100);
			String[] as = new String[roleList.size()];
			int selectedIndex = 0;
			for (int i = 0; i < as.length; i++) {
				as[i] = roleList.get(i).getRoleName();
				if(userInfo.getRoleId().equals(roleList.get(i).getRoleId())){
					selectedIndex = i;
				}
			}
		
			JComboBox roleListBox = new JComboBox(as);
			roleListBox.setFont(new Font("新宋体", Font.PLAIN, 14));
			roleListBox.setBounds(150, 168, 127, 21);
			getContentPane().add(roleListBox);
			roleListBox.setSelectedIndex(selectedIndex);
			
			
			

			JButton button = new JButton("修改");
			button.setFont(new Font("新宋体", Font.PLAIN, 14));
			button.setBounds(150, 349, 93, 28);
			getContentPane().add(button);
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					userInfo.setUserRealName(userRealNameField.getText());
					userInfo.setUserSex(manButton.isSelected());
					userInfo.setUserBirthdate(brithdate);
					userInfo.setRoleId(roleList.get(roleListBox.getSelectedIndex()).getRoleId());
					try{
						dao.updateById(userInfo);
						JOptionPane.showMessageDialog(null, "修改成功");
						UserEditDialog.this.dispose();
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "修改失败","错误提示",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
