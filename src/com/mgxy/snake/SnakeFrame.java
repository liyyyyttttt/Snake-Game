package com.mgxy.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SnakeFrame {

    SnakeFrame(){
        //1.绘制一个静态窗口
        JFrame snframe = new JFrame("贪吃蛇小游戏----By一组 ");
        //窗口设置大小
        snframe.setBounds(10,10,900,720);
        //窗口不可变
        snframe.setResizable(false);
        //设置关闭事件
        snframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置选择难度
        Button bt1 = new Button("Choose");
        bt1.setSize(50,50);
        bt1.setLocation(850,30);
        bt1.setBackground(Color.cyan);
        snframe.add(bt1);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("选择难度");
                //设置flag取反
                StartGames.flag=!StartGames.flag;
                try {
                    Method method = StartGames.class.getMethod("main",String[].class);
                    method.invoke(null,(Object) new String[] {});
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                //隐藏贪吃蛇画面
                snframe.setVisible(!StartGames.flag);

            }
        });
        //2.面板 JPanel  可以加入到JFrame
        snframe.add(new GamePanel());
        snframe.setVisible(!StartGames.flag);
    }

}