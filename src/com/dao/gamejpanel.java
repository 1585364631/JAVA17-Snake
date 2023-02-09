package com.dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gamejpanel extends JPanel implements KeyListener, ActionListener {

    String snake_in;
    List<snake_info> list;
    boolean game_status,game,zuobi,bianjie,suduppd;

    int food_x,food_y;
    int jie;
    int score;
    int times = 80;
    Random food = new Random();

    Timer timer = new Timer(times,this);

    public void init(){
        score=0;
        jie=3;
        bianjie=false;
        zuobi=false;
        food_x = 25 + food.nextInt(54) * 25;
        food_y = 25 + food.nextInt(27) * 25;
        game = true;
        list = new ArrayList<>();
        suduppd = true;
        snake_in="r";
        list.add(new snake_info(50,0));
        list.add(new snake_info(25,0));
        list.add(new snake_info(0,0));
        for (int i=0;i<list.size();i++){
            if (list.get(i).x == food_x && list.get(i).y == food_y){
                init();
            }
        }
        timer.start();
    }

    public gamejpanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBounds(3,0,1400,750);
        this.setBackground(Color.BLACK);

        for (int i=1;i<list.size();i++){
            data.body.paintIcon(this,g,list.get(i).getx(),list.get(i).gety());
        }


        if (list.get(0).x == food_x && list.get(0).y == food_y){

            while (true){
                food_x = 25 + food.nextInt(54) * 25;
                food_y = 25 + food.nextInt(27) * 25;
                if (bianjie){
                    if (food_x == 0 || food_x ==1375 || food_y == 0 || food_y == 725 ){
                        continue;
                    }
                }
                boolean pd = false;
                for (int i=0;i<list.size();i++){
                    if (list.get(i).x == food_x && list.get(i).y == food_y){
                        pd = true;
                        break;
                    }
                }
                if (!pd){
                    break;
                }
            }
            list.add(new snake_info(list.get(list.size()-1).x,list.get(list.size()-1).y));
            jie++;
            score+=10;
        }

        data.food.paintIcon(this,g,food_x,food_y);

        if (bianjie){
            g.setColor(Color.yellow);
            g.drawRect(0,0,1400,2);
            g.drawRect(0,748,1400,2);
            g.drawRect(0,0,2,750);
            g.drawRect(1398,0,2,750);
        }

        if (snake_in.equals("r")){
            data.right.paintIcon(this,g,list.get(0).getx(),list.get(0).gety());
        }
        if (snake_in.equals("l")){
            data.left.paintIcon(this,g,list.get(0).getx(),list.get(0).gety());
        }
        if (snake_in.equals("u")){
            data.up.paintIcon(this,g,list.get(0).getx(),list.get(0).gety());
        }
        if (snake_in.equals("d")){
            data.down.paintIcon(this,g,list.get(0).getx(),list.get(0).gety());
        }



        if (!game_status){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",265*2,240);
            g.drawString("蛇长：" + String.valueOf(jie) + "  得分：" + String.valueOf(score),265*2,340);
            g.drawString("按Q开关边界模式",265*2,440);
            if (zuobi){
                g.drawString("作弊激活",265,440);
            }
        }

        if (!game){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格重新开始",265*2,240);
            g.drawString("蛇长：" + String.valueOf(jie) + "  得分：" + String.valueOf(score),265*2,340);
            g.drawString("按Q开关边界模式",265*2,440);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game_status && game){
            for (int i = list.size() - 1;i>0;i--) {
                list.get(i).setx(list.get(i - 1).getx());
                list.get(i).sety(list.get(i - 1).gety());
            }
            if (snake_in.equals("r")){
                list.get(0).x += 25;
                if (list.get(0).x > 1375){
                    if (bianjie && !zuobi){
                        game = !game;
                    }
                    else {
                        list.get(0).x = 0;
                    }
                }
            }
            if (snake_in.equals("l")){
                list.get(0).x -= 25;
                if (list.get(0).x < 0){
                    if (bianjie && !zuobi){
                        game = !game;
                    }
                    else {
                        list.get(0).x = 1375;
                    }
                }
            }
            if (snake_in.equals("u")){
                list.get(0).y -= 25;
                if (list.get(0).y < 0){
                    if (bianjie && !zuobi){
                        game = !game;
                    }
                    else {
                        list.get(0).y = 725;
                    }
                }
            }
            if (snake_in.equals("d")){
                list.get(0).y += 25;
                if (list.get(0).y > 725){
                    if (bianjie && !zuobi){
                        game = !game;
                    }
                    else {
                        list.get(0).y = 0;
                    }
                }
            }

            if (!zuobi){
                for (int i=1;i<list.size();i++){
                    if (list.get(0).x == list.get(i).x && list.get(0).y == list.get(i).y){
                        game = !game;
                    }
                }
            }

            repaint();
        }
        timer.start();
    }


    public void setsend(){
        if (suduppd){
            suduppd = false;
            timer.setDelay(times);
            suduppd = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_SPACE){
            if (!game){
                game = !game;
                init();
            }else {
                game_status = !game_status;
            }

            repaint();
        }

        if (keycode == KeyEvent.VK_P){
            zuobi = !zuobi;
        }

        if (keycode ==KeyEvent.VK_ADD){
            if (zuobi){
                times+=10;
                setsend();
            }
        }

        if (keycode ==KeyEvent.VK_F5){
            if (zuobi){
                times=80;
                setsend();
            }
        }

        if (keycode ==KeyEvent.VK_SUBTRACT){
            if (zuobi){
                if (0<times && times<10){
                    times-=1;
                }
                if (times>10){
                    times-=10;
                }
                setsend();
            }
        }

        if (keycode == KeyEvent.VK_Q){
            bianjie = !bianjie;
        }

        if (!zuobi){
            if (keycode == KeyEvent.VK_W||keycode == KeyEvent.VK_UP){
                if (!snake_in.equals("d")) {
                    snake_in = "u";
                }
            }
            if (keycode == KeyEvent.VK_A||keycode == KeyEvent.VK_LEFT){
                if (!snake_in.equals("r")){
                    snake_in = "l";
                }
            }
            if (keycode == KeyEvent.VK_S||keycode == KeyEvent.VK_DOWN){
                if (!snake_in.equals("u")) {
                    snake_in = "d";
                }
            }
            if (keycode == KeyEvent.VK_D||keycode == KeyEvent.VK_RIGHT){
                if (!snake_in.equals("l")){
                    snake_in = "r";
                }
            }
        }else {
            if (keycode == KeyEvent.VK_W||keycode == KeyEvent.VK_UP){
                snake_in = "u";
            }
            if (keycode == KeyEvent.VK_A||keycode == KeyEvent.VK_LEFT){
                snake_in = "l";
            }
            if (keycode == KeyEvent.VK_S||keycode == KeyEvent.VK_DOWN){
                snake_in = "d";
            }
            if (keycode == KeyEvent.VK_D||keycode == KeyEvent.VK_RIGHT){
                snake_in = "r";
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
