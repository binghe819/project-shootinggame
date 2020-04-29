import java.awt.*;

class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

// 게임의 나타나는 모든 객체.
class Game_Unit {
    int HP; // HP
    Point pos;
    // 비행기 혹은 총알의 길이, 높이 ( Crash 처리용 )
    int width;
    int height;
    // 비행기 혹은 총알의 이미지. ( View에 올리기 용 )
    Image img;
}

class Player extends Game_Unit{
    Player() {
        this.HP = 100;
        this.pos = new Point(100,100);
        this.width = 60;
        this.height = 13;
        img = ETC.tk.getImage("res/img/F4K.png");
    }
}

