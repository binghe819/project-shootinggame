import java.awt.*;
import javax.swing.*;

// 게임 화면 출력을 담당.
public class Game extends JFrame{

    // 유저 비행기 이미지
    Image userFlight = ETC.tk.getImage("res/img/F4K.png");

    Game(){
        // 프레임의 대한 설정.
        setTitle("JFrame 테스트"); // 프레임 제목 설정.
        setSize(854,480); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
    }

    @Override
    public void paint(Graphics g) {
        repaint(); // 프레임 화면 전체를 지웁니다.
        g.drawImage(userFlight, 100,100,this);
    }
}
