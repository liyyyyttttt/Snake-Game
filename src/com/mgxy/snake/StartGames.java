package com.mgxy.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

public class StartGames {

    static int dcnum =100;//越大越慢
    static boolean flag = true;

    public static void main(String[] args) {
        //1.绘制一个静态窗口
        JFrame frame = new JFrame("贪吃蛇小游戏----By 一组");
        //窗口设置大小
        frame.setBounds(10,10,500,720);
        //窗口不可变
        frame.setResizable(false);
        //设置关闭事件
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置按钮布局 和 标签布局
        frame.setLayout (null);
        JLabel label1 = new JLabel("请选择贪吃蛇游戏难度");
        label1.setFont(new Font("微软雅黑",Font.BOLD,30));
        JLabel label2 = new JLabel("长度达到550即可获得胜利！");
        label2.setFont(new Font("微软雅黑",Font.BOLD,20));
        label2.setForeground(Color.ORANGE);
        label1.setBounds(75,200,500,50);
        label2.setBounds(50,100,500,50);
        JButton bt1 = new JButton("简单模式");
        JButton bt2 = new JButton("正常模式");
        JButton bt3 = new JButton("困难模式");
        bt1.setBounds (150, 300, 150, 50);
        bt2.setBounds (150,370, 150, 50);
        bt3.setBounds (150,440, 150, 50);
        frame.add(label1);
        frame.add(label2);
        frame.add(bt1);
        frame.add(bt2);
        frame.add(bt3);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("简单模式");
                dcnum=130;
                flag=!flag;
                frame.setVisible(flag);
                SnakeFrame snakeFrame = new SnakeFrame();
            }
        });
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("正常模式");
                dcnum=90;
                flag=!flag;
                frame.setVisible(flag);
                SnakeFrame snakeFrame = new SnakeFrame();
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("困难模式");
                dcnum=50;
                flag=!flag;
                frame.setVisible(flag);
                SnakeFrame snakeFrame = new SnakeFrame();
            }
        });
        //窗口可显示
        frame.setVisible(flag);


    }
}
