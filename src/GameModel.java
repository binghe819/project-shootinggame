import java.util.ArrayList;

public class GameModel {

    // Model에 저장할 객체
    Player player;

    ArrayList<Enemy> enemys;
    ArrayList<playerMissile> playerMissiles;
    ArrayList<enemyMissile> enemyMissiles;

    GameModel() {
        this.player = new Player(100,300);

        this.enemys = new ArrayList<Enemy>();
        this.playerMissiles = new ArrayList<playerMissile>();
        this.enemyMissiles = new ArrayList<enemyMissile>();

        // 적 생성 ( 기본 )
        enemys.add(new Enemy(730, 50, 10, 5));
        enemys.add(new Enemy(730, 150, 10, 5));
        enemys.add(new Enemy(730, 250, 10 , 5));
        enemys.add(new Enemy(730, 350, 10 , 5));
        enemys.add(new Enemy(730, 450, 10 , 5));
    }

}
