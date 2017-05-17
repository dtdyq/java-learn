package java8.functional;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * Created by dtdyq on 2017/4/1.
 */
public class TestAction {
    private JFrame main= new JFrame("functional test");
    private JTextArea jta=new JTextArea(24,40);
    private JButton jbn=new JButton("submit");
    void init(){
        main.setLocation(400 ,200);
        main.add(jta);
        main.add(jbn,"South");
        //�����ڲ���ʵ�֣�
//        jbn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                jta.append("submit...\n");
//            }
//        });
        //ʹ��lambda���ʽ��
        jbn.addActionListener(event->{
            jta.append("submit...\n"); 
        });

        //����ʽʵ��������Ӳ���
        BinaryOperator<Integer> op=(x,y)->x+y;

        //lambda��һ�ֱ����ʽ��
        ActionListener act=e -> System.out.println("button clicked..." +
                ""+op.apply((int)Math.random()*100,(int)Math.random()*100));
        jbn.addActionListener(act);
        main.pack();
        main.setVisible(true);

    }
    public  static void main(String[] args){
        new TestAction().init();
    }
}
