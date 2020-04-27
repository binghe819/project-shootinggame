import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game_View extends JFrame implements Runnable{

    // 이미지 파일 불러오는 툴킷.
    Toolkit imageTool = Toolkit.getDefaultToolkit();
    Image flight = imageTool.getImage("res/img/F4K.png");

    // 플레이어 비행기의 위치값.
    int xpos = 100;
    int ypos = 100;

    public Game_View(){
        // 프레임의 대한 설정.
        setTitle("JFrame 테스트"); // 프레임 제목 설정.
        setSize(854,480); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setVisible(true); // 프레임 보이기;
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 프레임의 x버튼 누르면 프레임스레드 종료.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 프로세스 종료.

        // 키 어댑터 ( 키 처리용 )
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        ypos-=5;
                        break;
                    case KeyEvent.VK_DOWN:
                        ypos+=5;
                        break;
                    case KeyEvent.VK_LEFT:
                        xpos-=5;
                        break;
                    case KeyEvent.VK_RIGHT:
                        xpos+=5;
                        break;
                }
            }
        });
    }

    // 움직임에 따른 그림 그리기.
    @Override
    public void paint(Graphics g) {
        System.out.println("paint called");
        g.clearRect(0,0,854, 480);
        g.drawImage(flight, xpos,ypos,this);
    }

    // repaint 스레드.
    @Override
    public void run() {
        try{
            while(true) {
                repaint();
                Thread.sleep(15);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
