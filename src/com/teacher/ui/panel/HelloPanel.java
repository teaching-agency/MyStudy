package com.teacher.ui.panel;

import javax.swing.*;

public class HelloPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HelloPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("欢迎进入本系统！");
		label.setBounds(10, 10, 280, 54);
		add(label);

	}
}
