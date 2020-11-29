package com.teacher.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * 日期选择对话框
 * Date date = DateDialog.open();
 * Date date = DateDialog.open(new Date());
 * ClassName : com.teach.ui.DateDialog
 * Author : J.L.Zhou
 * Date : 2019年5月23日 下午1:33:43
 * Version : V1.0
 * Copyright 2019 jlzhou.top Inc. All rights reserved. 
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目
 * </pre>
 */
public class DateDialog extends JDialog {

	private static final long serialVersionUID = -3589773241985388507L;

	public static void main(String[] args) {
		Date date = DateDialog.open();
		System.out.println(format.format(date));
//		date = DateDialog.open(date);
//		System.out.println(format.format(date));
//		date = DateDialog.open(date);
//		System.out.println(format.format(date));
		System.exit(0);
	}

	private static DateDialog dialog = new DateDialog();

	/**
	 * <pre>
	 * 获取日期选择对话框对象
	 * </pre>
	 * 
	 * getDialog
	 * 
	 * @return
	 */
	public static DateDialog getDialog() {
		return dialog;
	}

	/**
	 * <pre>
	 * 打开对话框
	 * </pre>
	 * 
	 * open
	 * 
	 * @return 返回选择的日期
	 */
	public static Date open() {
		return open(new Date());
	}

	/**
	 * <pre>
	 * 打开对话框，并设置默认选择日期
	 * </pre>
	 * 
	 * open
	 * 
	 * @param date
	 * @return
	 */
	public static Date open(Date date) {
		dialog.selectDate = date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		dialog.setYearMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		return dialog.selectDate;
	}

	private JLabel topMsg;
	private int y, m = 1;
	private Date selectDate;
	private JPanel body;

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	private DateDialog() {
		setSize(400, 400);// 设置大小 pack返回后无效
		setModal(true);// 设置模态对话框
		setTitle("日期选择对话框");// 设置标题
		setResizable(false);// 不允许修改大小
		JPanel contentPane = new JPanel(new BorderLayout(5, 5));
		setContentPane(contentPane);
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));// 设置外部空白边框
		contentPane.add(initTop(), BorderLayout.NORTH);// 北部加入

		contentPane.add(initBody(), BorderLayout.CENTER);// 加入中部

		pack();// 根据内容重置大小
		setLocationRelativeTo(null);// 居中
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);// 点击关闭按钮，销毁
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);// 点击关闭按钮，隐藏
	}

	/**
	 * <pre>
	 * 窗口顶部
	 * </pre>
	 * 
	 * initTop
	 * 
	 * @return
	 */
	private JPanel initTop() {
		JPanel p = new JPanel();
//		p.setPreferredSize(new Dimension(0, 50));//用于布局时的大小设置
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));// 设置居中的流式布局
		JLabel preYear = initTopBtn("«", 10);
		preYear.setToolTipText("上一年");
		p.add(preYear);
		p.add(initTopBtn("‹", 0));

		JLabel l = new JLabel("2019年02月");
		l.setBorder(new EmptyBorder(5, 20, 0, 20));
		l.setFont(new Font("宋体", Font.PLAIN, 14));
		l.setForeground(Color.BLACK);
		p.add(l);
		topMsg = l;

		p.add(initTopBtn("›", 10));
		p.add(initTopBtn("»", 0));

		return p;
	}

	/**
	 * <pre>
	 * 窗口顶部按钮
	 * </pre>
	 * 
	 * initTopBtn
	 * 
	 * @param text
	 * @param right
	 * @return
	 */
	private JLabel initTopBtn(String text, int right) {
		JLabel l = new JLabel(text);
		l.setBorder(new EmptyBorder(0, 0, 0, right));
		l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//		l.setPreferredSize(new Dimension(24, 24));
		l.setForeground(Color.BLACK);
		
		l.addMouseListener(new MouseAdapter() {

			/**
			 * 鼠标进入时，改为红色
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				l.setForeground(Color.RED);
			}

			/**
			 * 输入移开时，改为黑色
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				l.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				DateDialog.this.clickTopBtn(text);
			}
		});
		return l;
	}

	/**
	 * <pre>
	 * 顶部单击事件
	 * «‹›»
	 * </pre>
	 * 
	 * clickTopBtn
	 * 
	 */
	private void clickTopBtn(String text) {
		Calendar c = Calendar.getInstance();
		c.set(y, m, 1);
		switch (text) {
		case "«":
			setYearMonth(y - 1, m);
			break;
		case "»":
			setYearMonth(y + 1, m);
			break;
		case "‹":
			c.add(Calendar.MONTH, -1);
			dialog.setYearMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
			break;
		case "›":
			c.add(Calendar.MONTH, 1);
			dialog.setYearMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
			break;
		default:
			break;
		}
	}

	/**
	 * <pre>
	 * 设置年月
	 * </pre>
	 * 
	 * setYearMonth
	 * 
	 * @param y
	 * @param m
	 */
	private void setYearMonth(int y, int m) {
		this.y = y;
		this.m = m;
		this.topMsg.setText(String.format("%d年%02d月", y, m + 1));
		reset();
		pack();
	}

	/**
	 * <pre>
	 * 初始化日期显示部分
	 * </pre>
	 * 
	 * initBody
	 * 
	 * @return
	 */
	@SuppressWarnings("serial")
	private JPanel initBody() {
		JPanel p = new JPanel();

		p.setLayout(new BorderLayout(0, 0));
		p.setBorder(new LineBorder(Color.WHITE, 10));

		JPanel top = new JPanel();
		p.add(top, BorderLayout.NORTH);

		top.setLayout(new GridLayout(1, 7, 0, 0));

		String[] ws = { "日", "一", "二", "三", "四", "五", "六" };
		for (String w : ws) {
			JLabel l = new JLabel(w) {

				@Override
				protected void paintComponent(Graphics g) {
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, getWidth(), getHeight());
					g.setColor(Color.BLACK);
					g.drawLine(0, 1, getWidth(), 1);
					g.drawLine(0, 0, getWidth(), 0);
					g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
					super.paintComponent(g);
				}

			};
			l.setHorizontalAlignment(SwingConstants.CENTER);
			l.setVerticalAlignment(SwingConstants.CENTER);
			l.setFont(new Font("宋体", Font.BOLD, 14));
			l.setPreferredSize(new Dimension(30, 35));
			l.setForeground(Color.BLACK);
			l.setBackground(Color.WHITE);
			if (w.equals("日")) {
				l.setForeground(Color.RED);
				;
			} else if (w.equals("六")) {
				l.setForeground(new Color(0, 150, 0));
			}
			top.add(l);
		}

		body = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(255,255,255));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		body.setLayout(new GridLayout(0, 7, 0, 0));
		body.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		p.add(body, BorderLayout.CENTER);

		return p;
	}

	private void reset() {
		body.removeAll();
		Calendar c = Calendar.getInstance();
		c.set(y, m, 1);
		for (int i = 1; i < c.get(Calendar.DAY_OF_WEEK); i++) {
			JLabel l = new JLabel("");
			l.setPreferredSize(new Dimension(30, 30));
			body.add(l);
		}
		while (c.get(Calendar.MONTH) == m) {
			JDateLable l = new JDateLable(c);

			if (c.get(Calendar.DAY_OF_WEEK) == 1) {
				l.setForeground(Color.RED);
			} else if (c.get(Calendar.DAY_OF_WEEK) == 7) {
				l.setForeground(new Color(0, 150, 0));
			}

			if (format.format(c.getTime()).equals(format.format(selectDate))) {
				l.setSelected(true);
			}

			l.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectDate = l.getDate();
					DateDialog.this.setVisible(false);
				}
			});

			c.add(Calendar.DATE, 1);
			body.add(l);
		}
	}

	private class JDateLable extends JLabel {

		private static final long serialVersionUID = 1L;

		private boolean selected;

		private boolean mouseEnter;

		private Date date;

		public JDateLable(Calendar c) {
			super("" + c.get(Calendar.DATE));
			date = c.getTime();
			setHorizontalAlignment(SwingConstants.CENTER);
			setVerticalAlignment(SwingConstants.CENTER);
			setFont(new Font("宋体", Font.PLAIN, 13));
			setPreferredSize(new Dimension(30, 30));
			setForeground(Color.BLACK);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					mouseEnter = true;
					repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					mouseEnter = false;
					repaint();
				}
			});
		}

		public Date getDate() {
			return date;
		}

		@Override
		protected void paintComponent(Graphics g) {
			
			if (mouseEnter) {
				g.setColor(new Color(0, 0, 0, 20));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			if (isSelected()) {
				g.setColor(new Color(0, 0, 0, 150));
				g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			}
			super.paintComponent(g);
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

	}

}
