package com.teacher.ui.panel;

import com.teacher.dao.RoleInfoDAO;
import com.teacher.dao.UserInfoDAO;
import com.teacher.dto.UserInfoDTO;
import com.teacher.dto.UserSearch;
import com.teacher.entity.RoleInfo;
import com.teacher.ui.RegisterDialog;
import com.teacher.ui.UserEditDialog;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class UserListPanel extends JPanel implements ActionListener {

	private JTable table;
	private MyTableModel model;
	private List<UserInfoDTO> list = new ArrayList<>();

	private UserInfoDAO dao = new UserInfoDAO();
	private RoleInfoDAO dao1 = new RoleInfoDAO();

	private JTextField keyField;
	private JComboBox<String> roleListBox;
	private List<RoleInfo> roleList;

	private PagePanel p;

	private UserSearch search = new UserSearch();

	public UserListPanel() {

		try {
			setLayout(new BorderLayout(0, 0));
			setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
			JPanel panel = new JPanel();
			add(panel, BorderLayout.NORTH);

			roleList = dao1.select(0, 100);
			String[] as = new String[roleList.size() + 1];
			as[0] = "所有会员";
			for (int i = 1; i < as.length; i++) {
				as[i] = roleList.get(i - 1).getRoleName();
			}

			roleListBox = new JComboBox<>(as);
			panel.add(roleListBox);

			JLabel label = new JLabel("关键字：");
			panel.add(label);

			keyField = new JTextField(10);
			panel.add(keyField);

			JButton btnNewButton = new JButton("搜索");
			btnNewButton.addActionListener(this);
			btnNewButton.setActionCommand("search");
			panel.add(btnNewButton);

			JButton btnNewButton_1 = new JButton("添加");
			btnNewButton_1.addActionListener(this);
			btnNewButton_1.setActionCommand("add");
			panel.add(btnNewButton_1);
			
			JButton editBtn = new JButton("修改");
			editBtn.addActionListener(this);
			editBtn.setActionCommand("edit");
			panel.add(editBtn);
			
			JButton deleteBtn = new JButton("删除");
			deleteBtn.addActionListener(this);
			deleteBtn.setActionCommand("delete");
			panel.add(deleteBtn);

			model = new MyTableModel();

			// 创建一个表格，指定 表头 和 所有行数据
			table = new JTable(model);

			table.setRowHeight(28);
			table.getTableHeader().setPreferredSize(new Dimension(0, 30));
			table.getColumnModel().getColumn(0).setMaxWidth(60);
			table.getColumnModel().getColumn(1).setMaxWidth(80);
			table.getColumnModel().getColumn(2).setMaxWidth(60);
			table.getColumnModel().getColumn(3).setMaxWidth(80);
			table.getColumnModel().getColumn(4).setMaxWidth(40);
			table.getColumnModel().getColumn(5).setMinWidth(100);
			table.getColumnModel().getColumn(6).setMinWidth(100);

			MyTableCellRenderer renderer = new MyTableCellRenderer();
			// 遍历表格的每一列，分别给每一列设置单元格渲染器
			for (int i = 0; i < table.getColumnCount(); i++) {
				// 根据 列名 获取 表格列
				TableColumn tableColumn = table.getColumnModel().getColumn(i);
				// 设置 表格列 的 单元格渲染器
				tableColumn.setCellRenderer(renderer);
				tableColumn.sizeWidthToFit();

			}

			// 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
			table.setPreferredScrollableViewportSize(new Dimension(400, 300));

			JScrollPane scrollPane = new JScrollPane(table);

			add(scrollPane, BorderLayout.CENTER);

			p = new PagePanel();
			add(p, BorderLayout.SOUTH);

			p.setListener((index, len) -> {
				try {
					search.setIndex(index);
					search.setLen(len);
					list = dao.select1(search);
					model.fireTableDataChanged();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
			p.setCountListener(()->{
				long ret = 0;
				try {
					 ret = dao.count1(search);
				} catch (Exception e) {
				}
				if(ret==0)
				{
					list.clear();
					model.fireTableDataChanged();
				}
				return ret;
			});
			p.setRowMax(10);

			p.setPage(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

		case "search":// 搜索
			if (roleListBox.getSelectedIndex() == 0) {
				search.setRoleId(null);
			} else {
				int roleId = roleList.get(roleListBox.getSelectedIndex() - 1).getRoleId();
				search.setRoleId(roleId);
			}
			search.setKey(keyField.getText());
			p.setPage(1);
			break;
		case "add":
			new RegisterDialog().setVisible(true);
			p.setPage(1);
			break;
		case "edit":
			
			int index = table.getSelectionModel().getMinSelectionIndex();
			if(index==-1){
				JOptionPane.showMessageDialog(null, "请选择要修改的数据");
				break;
			}
			new UserEditDialog(list.get(index).getUserName()).setVisible(true);;
			
			p.reload();
			break;
		case "delete":
			int[] indexs=table.getSelectedRows();
			if(indexs.length==0){
				JOptionPane.showMessageDialog(null, "请选择要删除的数据");
			}else{
				int btnType=JOptionPane.showConfirmDialog(null, "您确定要删除选择的["+indexs.length+"]条数据吗?");
				if(btnType==JOptionPane.OK_OPTION){
					for(int i : indexs){
						try{
							dao.deleteById(list.get(i).getUserName());
						}catch(Exception ex){
							JOptionPane.showMessageDialog(null, "请选择要删除的数据","错误提示",JOptionPane.ERROR_MESSAGE);
						}
					}
					p.reload();
				}
			}
			break;
		default:
			break;
		}

	}

	private class MyTableModel extends AbstractTableModel {

		private String[] columes = { "用户名", "角色名称", "昵称", "出生日期", "性别", "电话", "QQ"};

		@Override
		public String getColumnName(int column) {
			return columes[column];
		}

		@Override
		public int getRowCount() {
			return list.size();
		}

		@Override
		public int getColumnCount() {
			return columes.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return list.get(rowIndex).getUserName();
			case 1:
				return list.get(rowIndex).getRoleName();
			case 2:
				return list.get(rowIndex).getUserRealName();
			case 3:
				if (list.get(rowIndex).getUserBirthdate() == null) {
					return "";
				} else {
					return new SimpleDateFormat("yyyy-MM-dd").format(list.get(rowIndex).getUserBirthdate());
				}
			case 4:
				if (list.get(rowIndex).getUserSex() == null) {
					return "";
				} else {
					return list.get(rowIndex).getUserSex() ? "男" : "女";
				}
			case 5:
				return list.get(rowIndex).getUserTel();
			case 6:
				return list.get(rowIndex).getUserQq();
			default:
				return null;
			}
		}

	}



	private class MyTableCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {



			setHorizontalAlignment(SwingConstants.CENTER);

			if (row % 2 == 0) {
				setBackground(Color.WHITE);
			} else {
				setBackground(new Color(230, 230, 230));
			}

			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

}