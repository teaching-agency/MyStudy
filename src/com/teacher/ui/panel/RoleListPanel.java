package com.teacher.ui.panel;

import com.teacher.dao.RoleInfoDAO;
import com.teacher.entity.RoleInfo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleListPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;

	private RoleInfoDAO dao = new RoleInfoDAO();
	
	private MyTableModel model;
	
	private JTable table;
	
	private PagePanel pagePanel;
	
/**
	 * Create the panel.
	 */
	public RoleListPanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("关键字：");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("搜索");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("添加");
		panel.add(btnNewButton_1);
		
		
		pagePanel = new PagePanel();

		
		pagePanel.setListener(new PagePanel.PageChangeListener() {
			
			@Override
			public void doPageChange(int index, int len) {
				try {
					model.setList(dao.select(index, len));
					model.fireTableDataChanged();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		pagePanel.setCountListener(()->{
			try {
				return dao.count();
			} catch (Exception e) {
				return 0;
			}
		});
		
		add(pagePanel, BorderLayout.SOUTH);
		model = new MyTableModel();
		
		
		
        // 创建一个表格，指定 表头 和 所有行数据
        table = new JTable(model);
    
        
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));

        // 设置表格内容颜色
     

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
//        JButton b = new JButton("asdfasdf");
//        b.setPreferredSize(new Dimension(2000, 2000));
        JScrollPane scrollPane = new JScrollPane(table);
        
//        scrollPane.setViewportView(b);
        
        add(scrollPane, BorderLayout.CENTER);

        table.getColumnModel().getColumn(0).setMaxWidth(70);
        table.getColumnModel().getColumn(2).setMaxWidth(120);

        
        MyTableCellRenderer renderer = new MyTableCellRenderer();
        // 遍历表格的每一列，分别给每一列设置单元格渲染器
        for (int i = 0; i < table.getColumnCount(); i++) {
            // 根据 列名 获取 表格列
            TableColumn tableColumn = table.getColumnModel().getColumn(i);
            // 设置 表格列 的 单元格渲染器
            tableColumn.setCellRenderer(renderer);
            tableColumn.sizeWidthToFit();
            
            
        }
        
        
        pagePanel.setPage(1);
	}
	
	
	

	
	@SuppressWarnings("serial")
	private class MyTableModel extends AbstractTableModel{
		
		private String[] columes = {"角色编号","角色名称","操作"};
		
		private List<RoleInfo> list = new ArrayList<>(0);
		
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
				return list.get(rowIndex).getRoleId();
			case 1:
				return list.get(rowIndex).getRoleName();
			default:
				return null;
			}
		}


		public void setList(List<RoleInfo> list) {
			if(list!=null){
				this.list = list;
			}
		}

	}

	@SuppressWarnings("serial")
	private class MyTableCellRenderer extends DefaultTableCellRenderer{
		
		private class Btn extends JLabel{

			public Btn(String text) {
				super(text);
				setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
				setFont(new Font(null,Font.PLAIN,12));
				setForeground(Color.blue);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseEntered(MouseEvent e) {
						System.out.println(e);
						Btn.this.setForeground(Color.red);
						Btn.this.repaint();
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						Btn.this.setForeground(Color.blue);
						Btn.this.repaint();
					}
				});
			}
			
			
			
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			if(column==2){
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
				p.setPreferredSize(new Dimension(100, 0));
				p.add(new Btn("修改"));
				p.add(new Btn("删除"));
				if(isSelected){
					p.setBackground(table.getSelectionBackground());
				}else if (row % 2 == 0) {
	                p.setBackground(Color.WHITE);
	            } else {
	                p.setBackground(Color.LIGHT_GRAY);
	            }
				return p;
				
			}
			
			if(column!=1){
				setHorizontalAlignment(SwingConstants.CENTER);
			}else{
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			
			if (row % 2 == 0) {
                setBackground(Color.WHITE);
            } else {
                setBackground(Color.LIGHT_GRAY);
            }
			
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}


}
