import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game_View extends JFrame{

    // 이미지 버퍼
    Image buffImg;
    Graphics buffG;

    // Game_Model 받아오기
    Game_Model game_model = new Game_Model();
    Game_Control game_control = new Game_Control(game_model);

    public Game_View(){
        // 프레임의 대한 설정.
        setTitle("JFrame 테스트"); // 프레임 제목 설정.
        setSize(854,480); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 프레임의 x버튼 누르면 프레임스레드 종료.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 프로세스 종료.
        addKeyListener(game_control);

        // 컨트롤 스레드 실행. ( 데몬스레드를 사용하여 View가 멈추면 컨트롤의 스레드도 멈춤. )
        Thread control_thread = new Thread(this.game_control);
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
        buffG.clearRect(0, 0, 854, 480); // 백지화
        buffG.drawImage(this.game_model.player.img,this.game_model.player.pos.x,this.game_model.player.pos.y, this); // 유저 비행기 그리기.
        g.drawImage(buffImg,0,0,this); // 화면g애 버퍼(buffG)에 그려진 이미지(buffImg)옮김. ( 도화지에 이미지를 출력 )
        repaint();
    }
}
