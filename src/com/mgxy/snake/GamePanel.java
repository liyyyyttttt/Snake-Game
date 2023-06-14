package com.mgxy.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {


    int lenth;  //蛇的长度
    int[] snakeX = new int[600];    //蛇的坐标X
    int[] snakeY = new int[500];     //蛇的坐标Y
    String fx;  //蛇的方向 ： R:右  L:左  U:上  D:下
    boolean isStart = false;    //游戏是否开始
    Timer timer = new Timer(StartGames.dcnum,this);  //可以通过delay调整游戏难度
    //定义一个食物
    int foodxx;
    int foodyy;
    int foodx;
    int foody;
    Random random = new Random();
    boolean isFail = false; //游戏是否结束
    boolean isSuccess = false; //游戏是否胜利
    int score; //游戏分数！

    //构造器
    public GamePanel(){
        //调用初始化
        init();
        //设置键盘监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();

    }

    /**
     * 初始化游戏
     */
    public void init(){
        lenth=3;
        snakeX[0] = 100;snakeY[0] = 100;    //头部坐标
        snakeX[1] = 75;snakeY[1] = 100;    //第一个身体坐标
        snakeX[2] = 50;snakeY[2] = 100;    //第二个身体坐标
        fx = "R";
        //初始化食物数据,食物不能和蛇重合
        foodxx = 25 + 25* random.nextInt(34);
        foodyy = 75 + 25* random.nextInt(24);
        int[] food = getFood(foodxx,foodyy);
        foodx=food[0];
        foody=food[1];
        score=0;
    }

    public int[] getFood(int foodxx,int foodyy){
        for (int i = 1; i < lenth; i++) {
            if (foodxx==snakeX[i]&&foodyy==snakeY[i]){
                foodxx = 25 + 25* random.nextInt(34);
                foodyy = 75 + 25* random.nextInt(24);
                getFood(foodxx,foodyy);
            }
        }
        return new int[]{foodxx,foodyy};
    }

    //画板：  界面,蛇
    //Graphics: 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //清屏

        /**
         * 页面设置
         */
        this.setBackground(Color.WHITE);    //设置背景颜色
        //绘制广告栏
        Data.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,600);

        /**
         * 贪吃蛇绘制
         */
        if(fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < lenth; i++) {
            //蛇的身体长度由length来控制
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        /**
         * 食物绘制
         */
        Data.food.paintIcon(this,g,foodx,foody);
        /**
         * 积分绘制
         */
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度:"+lenth,750,35);
        g.drawString("分数:"+score,750,50);
        //游戏提示:是否开始
        if(isStart==false){
            g.setColor(Color.WHITE);//画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//字体
            g.drawString("按下空格键开始游戏！",300,300);
        }
        //失败提醒
        if (isFail==true){
            g.setColor(Color.RED);//画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//字体
            g.drawString("游戏失败！按下空格重新开始！",200,300);
        }
        //胜利提醒
        if (isSuccess==true){
            g.setColor(Color.ORANGE);//画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//字体
            g.drawString("游戏胜利！按下空格重新开始！",200,300);
        }
    }

    /**
     * 监听键盘
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){
                isFail=false;
                init();
            }else if (isSuccess){
                isSuccess=false;
                init();
            } else{
                isStart = !isStart;
            }
            repaint();//刷新界面
        }

        //键盘控制走向
        if(keyCode == KeyEvent.VK_LEFT){
            if(!fx.equals("R")){
                fx="L";
            }
        }else if(keyCode == KeyEvent.VK_RIGHT){
            if(!fx.equals("L")){
                fx="R";
            }
        }else if(keyCode == KeyEvent.VK_UP){
            if (!fx.equals("D")){
                fx="U";
            }
        }else if(keyCode == KeyEvent.VK_DOWN){
            if (!fx.equals("U")){
                fx="D";
            }

        }
    }

    /**
     * 定时器
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && isFail==false && isSuccess==false){
            for (int i = lenth-1; i >0 ; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //通过方向控制头部
            if(fx.equals("R")){
                snakeX[0] = snakeX[0]+25;
                if(snakeX[0] > 850){isFail = true;}
            }else if (fx.equals("L")){
                snakeX[0] = snakeX[0]-25;
                if(snakeX[0] < 25){isFail = true;}
            }else if (fx.equals("U")){
                snakeY[0] = snakeY[0]-25;
                if(snakeY[0] < 75){isFail = true;}
            }else if (fx.equals("D")){
                snakeY[0] = snakeY[0]+25;
                if(snakeY[0] > 650){isFail = true;}
            }

            //吃食物长大
            if(snakeX[0]==foodx&&snakeY[0]==foody){
                //长度+1
                lenth++;
                snakeX[lenth-1] = foodx-1;
                snakeY[lenth-1] = foody-1;
                score+=10;
                //重新生成食物
                foodxx = 25 + 25* random.nextInt(34);
                foodyy = 75 + 25* random.nextInt(24);
                int[] food = getFood(foodxx,foodyy);
                foodx=food[0];
                foody=food[1];
            }
            
            //失败判断
            for (int i = 1; i < lenth; i++) {
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    isFail = true;

                }
            }
            //成功判断
            if(lenth==550){
                isSuccess = true;

            }
            repaint();//刷新界面
        }
        timer.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


}
