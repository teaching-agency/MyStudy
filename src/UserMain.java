import jframe.JDBCUtils;
import jframe.JTextUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class UserMain {


    public static void main(String[] args) {
        //insertStudent();
        //updateStudent();
        deletStudent();
    }

    /**
     *@Author 86151
     *@Date 2020/11/1 15:16
     *Description 新增用户
     *
     * * @return : void
     */
    public static void insertStudent(){
        JFrame frame = new JFrame("新增学生");
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel depart = new JPanel();
        frame.add(depart);
        String[] texts = {"学生编号","学生姓名","学生密码","班级编号"};
        JTextUtils.createText(Arrays.asList(texts), depart);

        frame.setVisible(true);
    }

    public static void updateStudent(){
        JFrame frame = new JFrame("修改学生");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);
        JPanel depart = new JPanel();
        frame.add(depart);
        String[] texts = {"学生编号","学生姓名","学生密码","班级编号"};
        JTextUtils.createText(Arrays.asList(texts), depart);

        frame.setVisible(true);
    }

    public static void deletStudent(){
        JFrame frame = new JFrame("删除学生");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);
        JPanel depart = new JPanel();
        frame.add(depart);
        String[] texts = {"学生编号"};

        JTextUtils.createText(Arrays.asList(texts), depart);

        // 增加按钮
        JButton btn3=new JButton("确定");
        JButton btn4=new JButton("取消");
        depart.add(btn3);
        depart.add(btn4);
        Dimension preferredSize=new Dimension(60, 30);    //设置尺寸
        btn3.setPreferredSize(preferredSize);    //设置按钮大小
        btn3.setVerticalAlignment(SwingConstants.BOTTOM);    //设置按钮垂直对齐方式
        btn4.setPreferredSize(preferredSize);    //设置按钮大小
        btn4.setVerticalAlignment(SwingConstants.BOTTOM);    //设置按钮垂直对齐方式

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    // 操作完了，关闭进程
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // 删除
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.setVisible(true);
    }

    public static void deleteStudent(String id){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "DELETE FROM teaching_system.student (id) values(?)";
            Object[] params = {id};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }
}
