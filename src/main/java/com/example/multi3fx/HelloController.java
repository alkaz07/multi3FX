package com.example.multi3fx;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class HelloController {

    Model mod = new Model(0);

    @FXML
    private TextField txtVal;

    @FXML
    TextArea log;

    BlockingQueue<String> mem = new LinkedBlockingQueue<>();


    public void initialize() {
       // txtVal.textProperty().bindBidirectional(mod.a, new DecimalFormat());
        mod.a.addListener((v, old, newval) -> {
            System.out.println(old + " стало " + newval);
            //log.appendText(old + " стало " + newval+"\n");
            mem.add(old + " стало " + newval+"\n");
        });

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                txtVal.setText(String.valueOf(mod.a.get()));
                ArrayList<String> buffer =new ArrayList<>();
                mem.drainTo(buffer);
                for (String s: buffer ) {
                    log.appendText(s);
                }
            }
        };
        at.start();
    }

    @FXML
    void start1() {
        Thread tr1 = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    mod.add(12);
                    sleep(50);
                }
            }catch (InterruptedException ie){}
        });
        tr1.setDaemon(true);
        tr1.start();

    }

    @FXML
    void start2() {
        Thread tr2 = new Thread(() -> {
            try {
                for (int i = 0; i < 700; i++) {
                    mod.add(-19);
                    sleep(50);
                }
            }catch (InterruptedException ie){}
        });
        tr2.setDaemon(true);
        tr2.start();

    }

    @FXML
    void start3() {
        System.out.println("нажали кн 3");
        Thread tr3 = new Thread(() -> {
            try {
                for (int i = 0; i < 500; i++) {
                    mod.add(100);
                    sleep(120);
                }
            }catch (InterruptedException ie){}
        });
        tr3.setDaemon(true);
        tr3.start();

    }

}
