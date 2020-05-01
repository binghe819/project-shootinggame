import java.util.*;

// Model
public class Game_Model {
    Player player;

    ArrayList<Missile> playerMissile; // 플레이어의 미사일.
    ArrayList<Enemy> enemyFlights; // 적 비행기.
    ArrayList<enemyMissile> enemyMissiles; // 적 미사일.

    Game_Model(){
        this.player = new Player();
        this.playerMissile = new ArrayList<Missile>();
        this.enemyMissiles = new ArrayList<enemyMissile>();

        // 적 생성
        this.enemyFlights = new ArrayList<Enemy>();
        enemyFlights.add(new Enemy(730, 50));
        enemyFlights.add(new Enemy(730, 150));
        enemyFlights.add(new Enemy(730, 250));
        enemyFlights.add(new Enemy(730, 350));
        enemyFlights.add(new Enemy(730, 450));
    }
}
