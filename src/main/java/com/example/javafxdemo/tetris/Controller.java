package com.example.javafxdemo.tetris;

import javafx.scene.shape.Rectangle;

public class Controller {

    //getting numbers and MESH from Tetris class

    public static final int MOVE = Tetris.MOVE;

    public static final int SIZE = Tetris.SIZE;

    public static int XMAX = Tetris.XMAX;
    public static int YMAX = Tetris.YMAX;
    public static int [][] MESH = Tetris.MESH;

    //moving the blocks
    public static void MoveRight(Form form) { // 이동인데 매트릭스 블럭당 움직이는 곳을 좌표처럼 옮긴다
        if(form.a.getX() + MOVE <= XMAX - SIZE && form.b.getX() + MOVE <= XMAX - SIZE
            && form.c.getX() + MOVE <= XMAX - SIZE && form.d.getX() + MOVE <= XMAX - SIZE) {

            int moveA = MESH[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
            int moveB = MESH[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
            int moveC = MESH[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
            int moveD = MESH[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];

            if(moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
                // 최종 확인 후, 그곳으로 옮긴다
            }

        }
    }

    // 다른 방향키도 만들어 두자
    public static void MoveLeft(Form form) { // 이동인데 매트릭스 블럭당 움직이는 곳을 좌표처럼 옮긴다
        if(form.a.getX() - MOVE >= 0 && form.b.getX() - MOVE >= 0
                && form.c.getX() - MOVE >= 0 && form.d.getX() - MOVE >= 0) {

            int moveA = MESH[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
            int moveB = MESH[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
            int moveC = MESH[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
            int moveD = MESH[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];

            if(moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
                form.a.setX(form.a.getX() - MOVE);
                form.b.setX(form.b.getX() - MOVE);
                form.c.setX(form.c.getX() - MOVE);
                form.d.setX(form.d.getX() - MOVE);
                // 최종 확인 후, 그곳으로 옮긴다
            }

        }
    }


    //create the stone
    public static Form makeRect() {

        //random color for the stones
        int block = (int) (Math.random() * 100);
        String name;


        // SIZE - 1, 이거는 블럭을 이루는 각 사각형이 -1만큼 떨어져 있어 구분된 것처럼 보인다
        Rectangle a = new Rectangle(SIZE-1, SIZE-1),
                b = new Rectangle(SIZE-1, SIZE-1),
                c = new Rectangle(SIZE-1, SIZE-1),
                d = new Rectangle(SIZE-1, SIZE-1);

        // 다른 모양의 돌만들기
        if(block < 15) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name  = "j";
        } else if (block < 30) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name  = "l";
        } else if (block < 45) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            b.setX(XMAX / 2 - SIZE);
            c.setY(SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2);
            d.setX(SIZE);
            name  = "o";
        } else if (block < 60) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            b.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name  = "s";
        } else if (block < 75) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            b.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            name  = "t";
        } else if (block < 90) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            b.setX(XMAX / 2 + SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name  = "z";
        } else {
            a.setX(XMAX / 2 - SIZE - SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            d.setX(XMAX / 2 + SIZE);
            name  = "i";
        }

        return new Form(a, b, c, d, name);


    }
}
