import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main extends JavaGame {

    public void mouseDragged(MouseEvent e)
    {
        int nx = e.getX();
        int ny = e.getY();

        int dx = nx - mx;
        int dy = ny - my;

        mx = nx;
        my = ny;
    }

    public final void keyTyped(KeyEvent e) {	}

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
    }

    //------------------------------------------------------------------------//

    public void mouseReleased(MouseEvent e)
    {


    }

    public void mouseMoved(MouseEvent e) {

    }
    //------------------------------------------------------------------------//




}
