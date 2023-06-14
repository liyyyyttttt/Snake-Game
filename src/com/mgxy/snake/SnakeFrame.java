package com.mgxy.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SnakeFrame {

    SnakeFrame(){
        //1.����һ����̬����
        JFrame snframe = new JFrame("̰����С��Ϸ----Byһ�� ");
        //�������ô�С
        snframe.setBounds(10,10,900,720);
        //���ڲ��ɱ�
        snframe.setResizable(false);
        //���ùر��¼�
        snframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����ѡ���Ѷ�
        Button bt1 = new Button("Choose");
        bt1.setSize(50,50);
        bt1.setLocation(850,30);
        bt1.setBackground(Color.cyan);
        snframe.add(bt1);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ѡ���Ѷ�");
                //����flagȡ��
                StartGames.flag=!StartGames.flag;
                try {
                    Method method = StartGames.class.getMethod("main",String[].class);
                    method.invoke(null,(Object) new String[] {});
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                //����̰���߻���
                snframe.setVisible(!StartGames.flag);

            }
        });
        //2.��� JPanel  ���Լ��뵽JFrame
        snframe.add(new GamePanel());
        snframe.setVisible(!StartGames.flag);
    }

}