package com.example.javafxdemo.tetris;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends Application {

    // 변수
    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static int XMAX = SIZE * 12;
    public static int YMAX = SIZE * 24;

    public static int [][] MESH = new int [XMAX/SIZE][YMAX/SIZE]; // 맵 크기

    private static  Pane group = new Pane();
    private static Form object;
    private static Scene scene = new Scene(group, XMAX + 150, YMAX);
    public static int score = 0;
    public static int top = 0;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();
    private static int linsNo = 0;
    private Form nextObj1;

    public static void main(String[] args) { launch(args);}


    @Override
    public void start(Stage stage) throws Exception {
        // todo: generate method stub
        for (int[] mesh : MESH) {
            Arrays.fill(mesh, 0);
        }

        //Creating score and level text
        Line line = new Line(XMAX, 0, XMAX, XMAX);
        Text scoreText = new Text("Score: ");
        scoreText.setStyle("-fx-font: 20 arials;");
        scoreText.setY(50);
        scoreText.setX(XMAX + 5);

        Text level = new Text("Lines: ");
        level.setStyle("-fx-font: 20 arials");
        level.setY(100);
        level.setX(XMAX + 5);
        level.setFill(Color.GREEN);
        group.getChildren().addAll(scoreText, line, level);

        //Creating First block and stage
        Form a = nextObj1;
        group.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPress(a);
        object = a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        //TIMER => new java thread
        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0 || object.d.getY() == 0 )
                            top++;
                        else
                            top = 0;


                        if(top == 2) {
                            // Game Over => 블럭이 지정해둔 높이의 2칸을 넘어서면 게임오버
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 70 arial");
                            over.setY(250);
                            over.setX(10);
                            group.getChildren().add(over);
                            game = false;
                        }

                        //Exit
                        if(top == 15) {
                            System.exit(0);
                        }

                        if(game) { // 게임이 돌아가는 동안, 점수 계산
                            MoveDown(object);
                            scoreText.setText("Score: " + Integer.toString(score));
                            level.setText("Lines: " + Integer.toString(linsNo));
                        }


                    }
                });
            }

        }

        // 떨어지는 속도
        fall.schedule(task, 300);
    }

    // create the control System
    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        Controller.MoveRight(form);
                        break;
                    case LEFT:
                        Controller.MoveLeft(form);
                        score++;
                        break;
                    case UP:
                        MoveTurn(form);
                        break;
                    case DOWN:
                        MoveDown(form);
                        break;
                }
            }
        });
    }


    //move the stones into different shapes (forms)
    private void MoveTurn(Form form) {
        // moving every single rect of each stone
        int eachStone = form.form;
        Rectangle a =  form.a;
        Rectangle b =  form.b;
        Rectangle c =  form.c;
        Rectangle d =  form.d;

        switch (form.getName()) {
            case "j":
                  if(eachStone == check(a, 1, -1) && check(c, -1, -1) && check(d, -2, -2)) {

                  }
        }

    }


    // 정사각형인지 확인하기
    private boolean check(Rectangle rect, int x, int y) {
        boolean yb = false;
        boolean xb = false;

        if(x >= 0) {
            xb = rect.getX() + x*MOVE <= XMAX - SIZE;
        }

        if(x < 0) {
            xb = rect.getX() + x*MOVE >= 0;
        }

        if(y >= 0) {
            yb = rect.getY() + y*MOVE > 0;
        }

        if(y < 0) {
            yb = rect.
        }

    }
}
