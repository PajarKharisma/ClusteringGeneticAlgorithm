package gui;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static DrawPanel drawPanel;
    private static ControlPanel controlPanel;

    public Main(){
        super("ZAP");
        this.setLayout(new BorderLayout());

        this.add(drawPanel, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.WEST);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                drawPanel = new DrawPanel();
                controlPanel = new ControlPanel();

                drawPanel.setOtherPanel(controlPanel);
                controlPanel.setOtherPanel(drawPanel);

                new Main();
            }
        });
    }
}
