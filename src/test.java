import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class test extends JFrame {

    // 유저 비행기 이미지
    Image userFlight = ETC.tk.getImage("res/img/PlayerFlight.png");

    int xpos = 100;
    int ypos = 100;

    public test(){

        // 프레임의 대한 설정.
        setTitle("JFrame 테스트"); // 프레임 제목 설정.
        setSize(854,480); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 프레임의 x버튼 누르면 종료;

//        this.addKeyListener(new test_controll());
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("paint called");
//        repaint();
        g.clearRect(0, 0, 854, 480);
        g.drawImage(userFlight, xpos, ypos, this); // 유저 비행기를 그립니다.
    }


}
