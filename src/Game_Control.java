import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game_Control extends KeyAdapter implements Runnable{

    Game_Model model;

    // 키 눌림 처리
    boolean keyUp;
    boolean keyDown;
    boolean keyLeft;
    boolean keyRight;
    boolean keySpace;

    // 미사일의 발사 속도를 늦추기 위한 count.
    int cnt = 0;

    public Game_Control(Game_Model model){
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
    public void keyProcess(){
        cnt++;
        if(this.keyUp){
            if(this.model.player.pos.y >= 26)
                this.model.player.pos.y -= 5;
        }

        if(this.keyDown){
            if(this.model.player.pos.y < 465)
            this.model.player.pos.y += 5;
        }

        if(this.keyLeft){
            if(this.model.player.pos.x > 0)
                this.model.player.pos.x -= 5;
        }

        if(this.keyRight){
            if(this.model.player.pos.x < 790)
                this.model.player.pos.x += 5;
        }

        if(this.keySpace){
            if(cnt % 4 == 0)
                this.model.playerMissile.add(new Missile(new Point(this.model.player.pos.x+55, this.model.player.pos.y),0,12));
        }
    }

    // 미사일 움직임 로직.
    public void missileProcess(){
        // 플레이어 총알 움직임.
        for(int i = 0; i < this.model.playerMissile.size(); i++){
            Missile missile = this.model.playerMissile.get(i);
            missile.move();
            if(missile.pos.x > 854)
                this.model.playerMissile.remove(i);
        }


    }

    @Override
    public void run() {
        try {
            while(true){
                keyProcess();
                missileProcess();
                Thread.sleep(20);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
