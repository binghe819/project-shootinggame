import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game_Control extends KeyAdapter implements Runnable{

    Game_Model game_model;

    // 키 눌림 처리
    boolean keyUp;
    boolean keyDown;
    boolean keyLeft;
    boolean keyRight;
    boolean keySpace;

    public Game_Control(Game_Model game_model){
        this.game_model = game_model;
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
        if(this.keyUp){
            if(this.game_model.player.pos.y >= 26)
                this.game_model.player.pos.y -= 5;
        }
        if(this.keyDown){
            if(this.game_model.player.pos.y < 465)
            this.game_model.player.pos.y += 5;
        }

        if(this.keyLeft){
            if(this.game_model.player.pos.x > 0)
                this.game_model.player.pos.x -= 5;
        }

        if(this.keyRight){
            if(this.game_model.player.pos.x < 790)
                this.game_model.player.pos.x += 5;
        }

        if(this.keySpace){ // 미사일 발사.

        }
    }

    @Override
    public void run() {
        try {
            while(true){
                keyProcess();
                Thread.sleep(20);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
