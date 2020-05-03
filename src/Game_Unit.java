import java.awt.*;

//class Point{
//    int x;
//    int y;
//    Point(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}
//
//interface movable{
//    void move();
//}
//
//// 게임의 나타나는 모든 객체.
//abstract class Game_Unit {
//    int HP; // HP
//    Point pos;
//    // 비행기 혹은 총알의 길이, 높이 ( Crash 처리용 )
//    int width;
//    int height;
//    // 비행기 혹은 총알의 이미지. ( View에 올리기 용 )
//    Image img;
//}
//
//class Player extends Game_Unit{
//    Player() {
//        this.HP = 100;
//        this.pos = new Point(100,240);
//        this.width = 60;
//        this.height = 13;
//        img = ETC.tk.getImage("res/img/PlayerFlight.png");
//    }
//}
//
//class Enemy extends  Game_Unit{
//
//    Enemy(int x, int y){
//        this.HP = 100;
//        this.pos = new Point(x, y);
//        this.width = 60;
//        this.height = 13;
//        img = ETC.tk.getImage("res/img/enemyFlight.png");
//    }
//
//    public void move(){
//        this.pos.x -= 5;
//        if(this.pos.x < 1)
//            this.pos.x = 730;
//    }
//}
//
//class Missile {
//    Point pos;
//    int angle; // 총알 날아가는 각도.
//    int speed; // 총알 속도.
//    Image img;
//
//    public Missile(Point pos, int angle, int speed){
//        this.angle = angle;
//        this.speed = speed;
//        this.pos = pos;
//        this.img = ETC.tk.getImage("res/img/PlayerMissile.png");
//    }
//
//    public void move() {
//        this.pos.x += Math.cos(Math.toRadians(this.angle))*this.speed;
//        this.pos.y += Math.sin(Math.toRadians(this.angle))*this.speed;
//    }
//}
//
//class enemyMissile extends Missile{
//
//    public enemyMissile(Point pos, int angle, int speed) {
//        super(pos, angle, speed);
//        this.img = ETC.tk.getImage("res/img/enemyMissile.png");
//    }
//
//    @Override
//    public void move() {
//        this.pos.x -= Math.cos(Math.toRadians(this.angle))*this.speed;
//        this.pos.y -= Math.sin(Math.toRadians(this.angle))*this.speed;
//    }
//}

