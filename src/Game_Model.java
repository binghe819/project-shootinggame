import java.util.*;

// Model
public class Game_Model {
    Player player;
    ArrayList<Missile> playerMissile; // 플레이어의 마시알.

    Game_Model(){
        this.player = new Player();
        this.playerMissile = new ArrayList<Missile>();
    }
}
