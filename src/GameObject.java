import java.awt.*;

public abstract class GameObject {
    int x; // x좌표
    int y; // y좌표
    int width; // 충돌구현을 위한 객체의 크기.
    int height; // 충돌구현을 위한 객체의 크기.
    Image img; // 출력을 위한 객체의 이미지.
}

interface Movable {
    void move();
}

class Unit extends GameObject{
    int HP;

    Unit(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Player extends Unit {
    int score = 0;

    Player(int x, int y) {
        super(x,y);
        this.HP = 10;
        this.width = 60;
        this.height = 13;
        this.img = ETC.tk.getImage("res/img/PlayerFlight.png");
    }
}

class Enemy extends Unit implements Movable{
    int moveSpeed;
    public Enemy(int x, int y, int HP, int moveSpeed) {
        super(x, y);
        this.HP = HP;
        this.width = 60;
        this.height = 13;
        this.img = ETC.tk.getImage("res/img/enemyFlight.png");
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void move() {
        this.x -= this.moveSpeed;
        if(this.x < 1)
            this.x = 730;
    }
}

abstract class Missile extends GameObject implements Movable{
    int moveSpeed;
    int angle;

    public Missile(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 13;
        this.moveSpeed = 12;
        this.angle = angle;
    }

    @Override
    public void move() {
        this.x += Math.cos(Math.toRadians(this.angle))*this.moveSpeed;
        this.y += Math.sin(Math.toRadians(this.angle))*this.moveSpeed;
    }
}

class playerMissile extends Missile {
    public playerMissile(int x, int y, int angle) {
        super(x, y, angle);
        this.img = ETC.tk.getImage("res/img/PlayerMissile.png");
    }
}

class enemyMissile extends Missile {
    public enemyMissile(int x, int y, int angle) {
        super(x, y, angle);
        this.img = ETC.tk.getImage("res/img/enemyMissile.png");
    }
}
