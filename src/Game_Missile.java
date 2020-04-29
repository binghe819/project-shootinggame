
public class Game_Missile {
    Point pos;
    int angle; // 총알 날아가는 각도.
    int speed; // 총알 속도.

    public Game_Missile(Point pos, int angle, int speed){
        this.angle = angle;
        this.speed = speed;
        this.pos = pos;
    }

    public void move() {
        this.pos.x += Math.cos(Math.toRadians(this.angle))*this.speed;
        this.pos.y += Math.sin(Math.toRadians(this.angle))*this.speed;
    }
}

class player_missile extends Game_Missile{

    public player_missile(Point pos, int angle, int speed) {
        super(pos, angle, speed);
    }
}
