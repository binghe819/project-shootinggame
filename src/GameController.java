import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter implements Runnable {

    // 키 눌림 처리
    boolean keyUp;
    boolean keyDown;
    boolean keyLeft;
    boolean keyRight;
    boolean keySpace;

    // 미사일의 발사 속도를 늦추기 위한 count.
    int cnt = 0;

    // Model에 접근하기 위한 참조변수.
    GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    // 키가 눌렸을 때
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP :
                this.keyUp = true;
                break;
            case KeyEvent.VK_DOWN :
                this.keyDown = true;
                break;
            case KeyEvent.VK_LEFT :
                this.keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT :
                this.keyRight = true;
                break;
            case KeyEvent.VK_SPACE:
                this.keySpace = true;
                break;
        }
    }

    // 키가 눌렀다 때졌을 때.
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP :
                this.keyUp = false;
                break;
            case KeyEvent.VK_DOWN :
                this.keyDown = false;
                break;
            case KeyEvent.VK_LEFT :
                this.keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT :
                this.keyRight = false;
                break;
            case KeyEvent.VK_SPACE:
                this.keySpace = false;
                break;
        }
    }

    // 키에 대한 처리.
    public void keyProcess() {
        cnt++;
        if(this.keyUp){
            if(this.model.player.y >= 26)
                this.model.player.y -= 5;
        }

        if(this.keyDown){
            if(this.model.player.y < 465)
                this.model.player.y += 5;
        }

        if(this.keyLeft){
            if(this.model.player.x > 0)
                this.model.player.x -= 5;
        }

        if(this.keyRight){
            if(this.model.player.x < 790)
                this.model.player.x += 5;
        }

        if(this.keySpace){
            if(cnt % 4 == 0)
                this.model.playerMissiles.add(new playerMissile(this.model.player.x+55, this.model.player.y,0));
        }
    }

    // 플레이어 미사일 처리
    public void missileProcess(){
        for(int i = 0 ; i < this.model.playerMissiles.size(); i++){
            // 움직임 구현.
            playerMissile missile = this.model.playerMissiles.get(i);
            missile.move();
            if (missile.x > 854) // 미사일이 화면에서 나가면 제거.
                this.model.playerMissiles.remove(i);

            // 충돌 처리.

        }
    }

    // 적 비행기 움직임 처리
    public void enemyProcess() {
        for(int i = 0; i < this.model.enemys.size(); i++){
            // 움직임 구현
            Enemy enemy = this.model.enemys.get(i);
            enemy.move();
            if(enemy.x < 10) // 적 비행기가 화면에서 나가면.
                enemy.x = 730;

            // 충돌 처리.

        }
    }

    @Override
    public void run() {
        try {
            while(true){
                keyProcess();
                missileProcess();
                enemyProcess();
                Thread.sleep(20);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
