package com.dao;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);

        JFrame jFrame = new JFrame("贪吃蛇");
        jFrame.setBounds((screenWidth-1420)/2,(screenHeight-800)/2,1420,790);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        jFrame.add(new gamejpanel());

        jFrame.setVisible(true);
    }
}
