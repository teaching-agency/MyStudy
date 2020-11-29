package com.teacher.ui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PagePanel extends JPanel implements ActionListener {

	private JPanel panel_1;

	private long total;
	private int pageMax;
	private int rowMax = 5;
	private int current = 1;
	private JLabel pageInfo;
	private String info = " 总记录数：%s, 每页记录数：%s, 页数：%s, 当前页码：%s ";

	private PageChangeListener listener;
	private PageCountListener countListener;

	public PagePanel() {
		panel_1 = this;
		JButton btnFirst = new JButton("首页");
		panel_1.add(btnFirst);
		btnFirst.setActionCommand("first");
		btnFirst.addActionListener(this);

		JButton btnPrevious = new JButton("上一页");
		panel_1.add(btnPrevious);
		btnPrevious.setActionCommand("previous");
		btnPrevious.addActionListener(this);

		pageInfo = new JLabel(String.format(info, 0, 0, 0, 0));
		pageInfo.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pageInfo.setBorder(BorderFactory.createEmptyBorder(3,5,0,5));
		panel_1.add(pageInfo);

		JButton btnNext = new JButton("下一页");
		btnNext.setActionCommand("next");
		btnNext.addActionListener(this);
		panel_1.add(btnNext);

		JButton btnLast = new JButton("末页");
		btnLast.setActionCommand("last");
		btnLast.addActionListener(this);
		panel_1.add(btnLast);
	}



	public void setRowMax(int rowMax) {
		this.rowMax = rowMax;
	}

	public void setPage(int p) {
		total = countListener.doCount();
		if (total == 0) {
			pageMax = 0;
			current = 0;
		} else {
			pageMax = (int) (total - 1) / rowMax + 1;
			if (p < 1) {
				current = 1;
			} else if (p > pageMax) {
				current = pageMax;
			} else {
				current = p;
			}
			listener.doPageChange((current - 1) * rowMax, rowMax);
		}
		pageInfo.setText(String.format(info, total, rowMax, pageMax, current));
		repaint();
	}
	
	public void reload(){
		setPage(current);
	}

	public void setListener(PageChangeListener listener) {
		this.listener = listener;
	}

	public void setCountListener(PageCountListener countListener) {
		this.countListener = countListener;
	}
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "first":
			setPage(1);
			break;
		case "previous":
			setPage(current - 1);
			break;
		case "next":
			setPage(current + 1);
			break;
		case "last":
			setPage(pageMax);
			break;

		default:
			break;
		}
	}

	public interface PageChangeListener {
		void doPageChange(int index, int len);
		
		
	}
	
	public interface PageCountListener{
		long doCount();
	}
}
