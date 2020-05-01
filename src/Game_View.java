import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game_View extends JFrame{

    // 이미지 버퍼
    Image buffImg;
    Graphics buffG;

    // model, controller
    Game_Model model;
    Game_Control controller;

    public Game_View(Game_Model model, Game_Control controller){

        this.model = model;
        this.controller = controller;

        // 프레임의 대한 설정.
        setTitle("JFrame 테스트"); // 프레임 제목 설정.
        setSize(854,480); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 프레임의 x버튼 누르면 프레임스레드 종료.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 프로세스 종료.
        addKeyListener(controller);

        // 컨트롤 스레드 실행. ( 데몬스레드를 사용하여 View가 멈추면 컨트롤의 스레드도 멈춤. )
        Thread control_thread = new Thread(this.controller);
        control_thread.setDaemon(true);
        control_thread.start();
    }

    @Override
    public void paint(Graphics g) {
        buffImg = createImage(getWidth(),getHeight()); // 버퍼링용 이미지 ( 도화지 )
        buffG = buffImg.getGraphics(); // 버퍼링용 이미지에 그래픽 객체를 얻어야 그릴 수 있다고 한다. ( 붓? )
        update(g);
    }

    @Override
    public void update(Graphics g) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buffG.drawImage(this.model.player.img,this.model.player.pos.x,this.model.player.pos.y, this); // 유저 비행기 그리기.
        drawMissile(g); // 플레이어의 미사일 그리기.
        drawEnemyFlight(g);
        g.drawImage(buffImg,0,0,this); // 화면g애 버퍼(buffG)에 그려진 이미지(buffImg)옮김. ( 도화지에 이미지를 출력 )
        repaint();
    }

    // 플레이어 미사일 출력.
    public void drawMissile(Graphics g){
        // 플레이어 미사일
        for(int i = 0; i < this.model.playerMissile.size(); i++){
            Missile missile = this.model.playerMissile.get(i);
            buffG.drawImage(missile.img,missile.pos.x,missile.pos.y,this);
        }
    }

    // 적 비행기 출력.
    public void drawEnemyFlight(Graphics g){
        for(int i = 0; i < this.model.enemyFlights.size(); i++){
            // 적 비행기 출력
            buffG.drawImage(this.model.enemyFlights.get(i).img, this.model.enemyFlights.get(i).pos.x, this.model.enemyFlights.get(i).pos.y,this);
        }
    }
}
