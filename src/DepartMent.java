import jframe.DepartMentDto;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import jframe.JDBCUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhenghaiyang
 * Date: 2020/11/1
 * Description:
 */
public class DepartMent {
    private JPanel depart;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;

    public static void main(String[] args){
        JFrame frame = new JFrame("学院管理");
        frame.setSize(600,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel depart = new JPanel();
        frame.setContentPane(depart);
        depart.setBorder(new EmptyBorder(40, 0, 10, 0));

        JLabel departNum = new JLabel("学院编号:");
        setFontToJLabel(departNum);
        depart.add(departNum);
        final JTextField textField = new JTextField(30);
        textField.setFont(new Font("楷体", Font.BOLD,22));
        setFontToJTextField(textField);
        depart.add(textField,BorderLayout.CENTER);

        JLabel departName = new JLabel("学院名称:");
        setFontToJLabel(departName);
        depart.add(departName);
        final JTextField textFieldName = new JTextField(30);
        textFieldName.setFont(new Font("楷体", Font.BOLD,22));
        setFontToJTextField(textFieldName);
        depart.add(textFieldName,BorderLayout.CENTER);


        JButton jButtonSave = new JButton("新增");
        setFontTodepartName(jButtonSave);
        jButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String departName = textFieldName.getText();
                if (departName== null || departName.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "学院名称不能为空！", "", JOptionPane.ERROR_MESSAGE);
                }else{
                    saveDepartMent(departName);
                    JOptionPane.showMessageDialog(null, "添加成功！", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        JButton jButtonUpdate = new JButton("编辑");
        setFontTodepartName(jButtonUpdate);
        jButtonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String departNum = textField.getText();
                String departName = textFieldName.getText();
                if ((departNum== null || departNum.trim().length() == 0) && (departName== null || departName.trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, "学院编号和学院名称不能为空！", "", JOptionPane.ERROR_MESSAGE);
                }else{
                    updateDepartMent(departNum,departName);
                    JOptionPane.showMessageDialog(null, "编辑成功！", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton jButtonDel = new JButton("删除");
        setFontTodepartName(jButtonDel);
        jButtonDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String departNum = textField.getText();
                delDepartMent(departNum);
                if (departNum== null || departNum.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "学院编号不能为空！", "", JOptionPane.ERROR_MESSAGE);
                }else{
                    saveDepartMent(departNum);
                    JOptionPane.showMessageDialog(null, "删除成功！", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        jButtonSave.setBorder(new EmptyBorder(40, 0, 10, 0));
        depart.add(jButtonSave);
        depart.add(jButtonDel);
        depart.add(jButtonUpdate);


        List<DepartMentDto> departMentDtos = queryDepartMent();
        Object[][] tableDate=new Object[departMentDtos.size()][2];
        for(int i=0;i<departMentDtos.size();i++){
            DepartMentDto mentDto = departMentDtos.get(i);
            tableDate[i][0]=mentDto.getId();
            for(int j=1;j<2;j++){
                tableDate[i][j]=mentDto.getName();
            }
        }
        String[] name={"学院编号","学院名称"};
        JTable table=new JTable(tableDate,name);
        table.setFont(new Font("楷体", Font.BOLD,22));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setFont(new Font("楷体", Font.BOLD,22));
        depart.add(scrollPane);

        frame.setVisible(true);
    }

    public static void setDimensionToJButton(JButton jButton){
        Dimension preferredSize=new Dimension(60, 30);    //设置尺寸
        jButton.setPreferredSize(preferredSize);    //设置按钮大小
        jButton.setVerticalAlignment(SwingConstants.BOTTOM);    //设置按钮垂直对齐方式
    }
    public static void setFontToJLabel(JLabel jLabel){
        jLabel.setFont(new Font("楷体", Font.BOLD,22));
        jLabel.setBounds(10,40,90,40);
        jLabel.setHorizontalAlignment(2);
    }

    public static void setFontToJTextField(JTextField jTextField){
        jTextField.setPreferredSize(new Dimension(10, 40));
    }

    public static void setFontTodepartName(JButton jButton){
        jButton.setFont(new Font("楷体", Font.BOLD,22));
    }

    public static void saveDepartMent(String departName){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "INSERT INTO teaching_system.department (name) values(?)";
            Object[] params = {departName};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }
    public static void delDepartMent(String departNum){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "DELETE FROM teaching_system.department WHERE id=?";
            Object[] params = {departNum};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }

    public static void updateDepartMent(String departName,String departNum){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "UPDATE teaching_system.department SET name = ? WHERE id = ?";
            Object[] params = {departName};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }

    public static List<DepartMentDto> queryDepartMent(){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "select * FROM teaching_system.department";
            List<DepartMentDto> departMentDtoList = (List<DepartMentDto>)queryRunner.query(connection, sql, new BeanListHandler(DepartMentDto.class));
            return departMentDtoList;
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
        return null;
    }
}
