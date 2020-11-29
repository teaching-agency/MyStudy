package jframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhenghaiyang
 * Date: 2020/11/1
 * Description:
 */
public class JTextUtils{

    public static JPanel createText(List<String> texts,JPanel depart){
        depart.setBorder(new EmptyBorder(20, 0, 10, 0));
        for(String text : texts){
            JLabel departNum = new JLabel(text+":");
            departNum.setBounds(10,40,90,40);
            depart.add(departNum);

            JTextField textField1 = new JTextField(20);
            departNum.setBounds(10,40,90,40);
            textField1.setPreferredSize(new Dimension(10, 40));  // 设置宽高
            depart.add(textField1);
        }

     return depart;
    }
}
