import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class test_controll extends KeyAdapter {



    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: // 위 버튼.
                System.out.println("Up");
                break;
            case KeyEvent.VK_DOWN: // 아래 버튼.
                System.out.println("Down");
                break;
            case KeyEvent.VK_LEFT: // 왼쪽 버튼.
                System.out.println("Left");
                break;
            case KeyEvent.VK_RIGHT: // 오른쪽 버튼.
                System.out.println("Right");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
    }
}
