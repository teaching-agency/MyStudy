import jframe.Course;
import jframe.JDBCUtils;
import jframe.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseMain {

    public static void main(String[] args) {
        //insertCourse();
        //updateCourse();
        //deletCourse();
        selectStudent();
    }
    //新增课程
    public static void insertCourse(){
        JFrame frame = new JFrame("新增课程");
        frame.setSize(350, 500);
        frame.setBounds(10,40,90,40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel depart = new JPanel();

        depart.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel departNum = new JLabel("课程编号:");
        departNum.setBounds(10,40,90,40);
        depart.add(departNum);

        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField);

        JLabel departNum1 = new JLabel("课程姓名:");
        departNum1.setBounds(10,40,90,40);
        depart.add(departNum1);

        JTextField textField1 = new JTextField(20);
        textField1.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField1);

        JLabel departNum2 = new JLabel("课程学分:");
        departNum2.setBounds(10,40,90,40);
        depart.add(departNum2);

        JTextField textField2 = new JTextField(20);
        textField2.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField2);


        JLabel departNum3 = new JLabel("理论课时:");
        departNum3.setBounds(10,40,90,40);
        depart.add(departNum3);

        JTextField textField3 = new JTextField(20);
        textField3.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField3);


        JLabel departNum4 = new JLabel("实验课时:");
        departNum4.setBounds(10,40,90,40);
        depart.add(departNum4);

        JTextField textField4 = new JTextField(20);
        textField4.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField4);

        JLabel departNum5 = new JLabel("点名成绩:");
        departNum5.setBounds(10,40,90,40);
        depart.add(departNum5);

        JTextField textField5 = new JTextField(20);
        textField5.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField5);

        JLabel departNum6 = new JLabel("作业成绩:");
        departNum6.setBounds(10,40,90,40);
        depart.add(departNum6);

        JTextField textField6 = new JTextField(20);
        textField6.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField6);

        JLabel departNum7 = new JLabel("实验成绩:");
        departNum7.setBounds(10,40,90,40);
        depart.add(departNum7);

        JTextField textField7 = new JTextField(20);
        textField7.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField7);

        JLabel departNum8 = new JLabel("测试成绩:");
        departNum8.setBounds(10,40,90,40);
        depart.add(departNum8);

        JTextField textField8 = new JTextField(20);
        textField8.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField8);

        JLabel departNum9 = new JLabel("报告成绩:");
        departNum9.setBounds(10,40,90,40);
        depart.add(departNum9);

        JTextField textField9 = new JTextField(20);
        textField9.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField9);

        JLabel departNum10 = new JLabel("项目成绩:");
        departNum10.setBounds(10,40,90,40);
        depart.add(departNum10);

        JTextField textField10 = new JTextField(20);
        textField10.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField10);



        /*JLabel departNum4 = new JLabel("性别:");
        departNum4.setBounds(10,40,90,40);
        depart.add(departNum4);
        JRadioButton dx1=new JRadioButton("男");
        JRadioButton dx2=new JRadioButton("女");*/


       /* ButtonGroup dxz=new ButtonGroup();
        dxz.add(dx1);   dxz.add(dx2);
        depart.add(dx1);depart.add(dx2);
*/
        frame.add(depart);

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

        // 新增
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id               = textField.getText();
                    String name             = textField1.getText();
                    Double credit           = Double.valueOf(textField2.getText());
                    int timeTheory       = Integer.parseInt(textField3.getText());
                    int timeExperiment   = Integer.parseInt(textField4.getText());
                    Double scoreCall        = Double.valueOf(textField5.getText());
                    Double scoreJob         = Double.valueOf(textField6.getText());
                    Double scoreExp         = Double.valueOf(textField7.getText());
                    Double scoreExm         = Double.valueOf(textField8.getText());
                    Double scoreWord        = Double.valueOf(textField9.getText());
                    Double scorePro         = Double.valueOf(textField10.getText());


                   saveCourseSQL(id,name,credit,timeTheory,timeExperiment,scoreCall,scoreJob,scoreExp,scoreExm,scoreWord,scorePro);
                    // 操作完了，关闭进程
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // 取消
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
    public static void saveCourseSQL( String id, String name,Double credit ,int timeTheory  , int timeExperiment,Double scoreCall   ,  Double scoreJob ,     Double scoreExp ,     Double scoreExm ,     Double scoreWord,     Double scorePro      ){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql ="INSERT INTO `teaching_system`.`course` (`id`, `name`, `credit`, `timeTheory`, `timeExperiment`, `scoreCall`, `scoreJob`, `scoreExp`, `scoreExm`, `scoreWord`, `scorePro`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Object[] params = {id, name, credit, timeTheory, timeExperiment, scoreCall, scoreJob, scoreExp, scoreExm, scoreWord, scorePro};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }
    //修改课程
    public static void updateCourse(){
        JFrame frame = new JFrame("修改课程");
        frame.setSize(350, 500);
        frame.setBounds(10,40,90,40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel depart = new JPanel();

        depart.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel departNum = new JLabel("课程编号:");
        departNum.setBounds(10,40,90,40);
        depart.add(departNum);

        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField);

        JLabel departNum1 = new JLabel("课程姓名:");
        departNum1.setBounds(10,40,90,40);
        depart.add(departNum1);

        JTextField textField1 = new JTextField(20);
        textField1.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField1);

        JLabel departNum2 = new JLabel("课程学分:");
        departNum2.setBounds(10,40,90,40);
        depart.add(departNum2);

        JTextField textField2 = new JTextField(20);
        textField2.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField2);


        JLabel departNum3 = new JLabel("理论课时:");
        departNum3.setBounds(10,40,90,40);
        depart.add(departNum3);

        JTextField textField3 = new JTextField(20);
        textField3.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField3);


        JLabel departNum4 = new JLabel("实验课时:");
        departNum4.setBounds(10,40,90,40);
        depart.add(departNum4);

        JTextField textField4 = new JTextField(20);
        textField4.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField4);

        JLabel departNum5 = new JLabel("点名成绩:");
        departNum5.setBounds(10,40,90,40);
        depart.add(departNum5);

        JTextField textField5 = new JTextField(20);
        textField5.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField5);

        JLabel departNum6 = new JLabel("作业成绩:");
        departNum6.setBounds(10,40,90,40);
        depart.add(departNum6);

        JTextField textField6 = new JTextField(20);
        textField6.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField6);

        JLabel departNum7 = new JLabel("实验成绩:");
        departNum7.setBounds(10,40,90,40);
        depart.add(departNum7);

        JTextField textField7 = new JTextField(20);
        textField7.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField7);

        JLabel departNum8 = new JLabel("测试成绩:");
        departNum8.setBounds(10,40,90,40);
        depart.add(departNum8);

        JTextField textField8 = new JTextField(20);
        textField8.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField8);

        JLabel departNum9 = new JLabel("报告成绩:");
        departNum9.setBounds(10,40,90,40);
        depart.add(departNum9);

        JTextField textField9 = new JTextField(20);
        textField9.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField9);

        JLabel departNum10 = new JLabel("项目成绩:");
        departNum10.setBounds(10,40,90,40);
        depart.add(departNum10);

        JTextField textField10 = new JTextField(20);
        textField10.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField10);



        /*JLabel departNum4 = new JLabel("性别:");
        departNum4.setBounds(10,40,90,40);
        depart.add(departNum4);
        JRadioButton dx1=new JRadioButton("男");
        JRadioButton dx2=new JRadioButton("女");*/


       /* ButtonGroup dxz=new ButtonGroup();
        dxz.add(dx1);   dxz.add(dx2);
        depart.add(dx1);depart.add(dx2);
*/
        frame.add(depart);

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

        // 新增
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id               = textField.getText();
                    String name             = textField1.getText();
                    Double credit           = Double.valueOf(textField2.getText());
                    int timeTheory       = Integer.parseInt(textField3.getText());
                    int timeExperiment   = Integer.parseInt(textField4.getText());
                    Double scoreCall        = Double.valueOf(textField5.getText());
                    Double scoreJob         = Double.valueOf(textField6.getText());
                    Double scoreExp         = Double.valueOf(textField7.getText());
                    Double scoreExm         = Double.valueOf(textField8.getText());
                    Double scoreWord        = Double.valueOf(textField9.getText());
                    Double scorePro         = Double.valueOf(textField10.getText());


                    updateCourseSQL(id,name,credit,timeTheory,timeExperiment,scoreCall,scoreJob,scoreExp,scoreExm,scoreWord,scorePro);
                    // 操作完了，关闭进程
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // 取消
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
    public static void updateCourseSQL( String id, String name,Double credit ,int timeTheory  , int timeExperiment,Double scoreCall   ,  Double scoreJob ,     Double scoreExp ,     Double scoreExm ,     Double scoreWord,     Double scorePro      ){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql ="UPDATE `teaching_system`.`course` SET `name`=?, `credit`=?, `timeTheory`=?, `timeExperiment`=?, `scoreCall`=?, `scoreJob`=?, `scoreExp`=?, `scoreExm`=?, `scoreWord`=?, `scorePro`=? WHERE `id`=?";
            Object[] params = { name, credit, timeTheory, timeExperiment, scoreCall, scoreJob, scoreExp, scoreExm, scoreWord, scorePro,id};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }
    //删除课程
    public static void deletCourse(){
        JFrame frame = new JFrame("删除课程");
        frame.setSize(350, 500);
        frame.setBounds(10,40,90,40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel depart = new JPanel();

        depart.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel departNum = new JLabel("课程编号:");
        departNum.setBounds(10,40,90,40);
        depart.add(departNum);

        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(10, 40));  // 设置宽高
        depart.add(textField);

        frame.add(depart);
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
                    String id = textField.getText();
                    deletCourseSQL(id);
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
    public static void deletCourseSQL(String id){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "DELETE FROM teaching_system.course WHERE id=?";
            Object[] params = {id};
            queryRunner.update(connection, sql,params);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
    }
    //查询课程
    public static void selectStudent(){
        JFrame f = new JFrame();
        List<Course> course = selectCourseSQL(null);
        Object[][] tableDate=new Object[course.size()][11];
        for(int i=0;i<course.size();i++){
            Course mentDto = course.get(i);
            tableDate[i][0]=mentDto.getId();
            for(int j=1;j<11;j++){
                if (j== 1) {
                    tableDate[i][j]=mentDto.getName();
                }
                if (j == 2) {
                    tableDate[i][j]=mentDto.getCredit();
                }
                if (j == 3) {
                    tableDate[i][j]=mentDto.getTimeTheory();
                }
                if (j == 4) {
                    tableDate[i][j]=mentDto.getTimeExperiment();
                }
                if (j == 5) {
                    tableDate[i][j]=mentDto.getScoreCall();
                }
                if (j == 6) {
                    tableDate[i][j]=mentDto.getScoreJob();
                }
                if (j == 7) {
                    tableDate[i][j]=mentDto.getScoreExp();
                }
                if (j == 8) {
                    tableDate[i][j]=mentDto.getScoreExm();
                }
                if (j == 9) {
                    tableDate[i][j]=mentDto.getScoreWord();
                }
                if (j == 10) {
                    tableDate[i][j]=mentDto.getScorePro();
                }
            }
        }
        // 创建表格中的横标题
        String[] Names = { "课程编号", "全部课程名", "学分", "理论课时", "实验课时","点名成绩","作业成绩","实验成绩","测试成绩","报告成绩","项目成绩" };
        // 以Names和playerInfo为参数，创建一个表格
        JTable table = new JTable(tableDate, Names);
        // 设置此表视图的首选大小
        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
        // 将表格加入到滚动条组件中
        JScrollPane scrollPane = new JScrollPane(table);
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        // 再将滚动条组件添加到中间容器中
        f.setTitle("表格测试窗口");
        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static  List<Course> selectCourseSQL(String name){
        Connection connection = JDBCUtils.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try{
            String sql = "SELECT * FROM teaching_system.course";
            List<Course> departMentDtoList = (List<Course>)queryRunner.query(connection, sql, new BeanListHandler(Course.class));
            return departMentDtoList;
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }finally{
            JDBCUtils.release(null, null, connection);
        }
        return null;
    }



}
