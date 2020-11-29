package com.teacher.ui;

import com.teacher.ui.panel.HelloPanel;
import com.teacher.ui.panel.RoleListPanel;
import com.teacher.ui.panel.UserListPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel bodyPane;
	private CardLayout cardLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/imgs/32/0-trans.png")));
		setTitle("我们自己的系统名称");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(400, 300));
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btn1 = new TopBtn();
		btn1.setText("首页");
		btn1.setIcon(new ImageIcon(this.getClass().getResource("/imgs/32/60-trans.png")));
		btn1.setActionCommand("do1");
		top.add(btn1);
		
		JButton btn2 = new TopBtn();
		btn2.setIcon(new ImageIcon(this.getClass().getResource("/imgs/32/130-trans.png")));
		btn2.setText("角色管理");
		btn2.setActionCommand("do2");
		top.add(btn2);
		
		JButton btn3 = new TopBtn();
		btn3.setIcon(new ImageIcon(this.getClass().getResource("/imgs/32/122-trans.png")));
		btn3.setText("用户管理");
		btn3.setActionCommand("do3");
		top.add(btn3);
		
		
		bodyPane = new JPanel();
		bodyPane.setBorder(BorderFactory.createEtchedBorder());
//		bodyPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
//		bodyPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//		bodyPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//		bodyPane.setBorder(BorderFactory.createLineBorder(Color.black));
//		bodyPane.setBorder(BorderFactory.createTitledBorder("正文内容"));
		
		contentPane.add(bodyPane, BorderLayout.CENTER);
		
		cardLayout = new CardLayout();
		bodyPane.setLayout(cardLayout);
		

		bodyPane.add("do1", new HelloPanel());
		bodyPane.add("do2", new RoleListPanel());
		bodyPane.add("do3", new UserListPanel());
		cardLayout.show(bodyPane, "do3");
		
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cardLayout.show(bodyPane, e.getActionCommand());
	}
	
	private class TopBtn extends JButton {
		
		public TopBtn(){
			setPreferredSize(new Dimension(80, 80));
			setMargin(new Insets(0, 0, 0, 0));
			setHorizontalTextPosition(SwingConstants.CENTER);
			setVerticalTextPosition(SwingConstants.BOTTOM);
			setFocusPainted(false);
			setFont(new Font("微软雅黑", Font.PLAIN, 13));
			addActionListener(MainFrame.this);
		}
	}
}
