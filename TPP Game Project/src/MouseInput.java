import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
        GamePanel gp;
        boolean mousePress = false;

        @Override
        public void mouseClicked(MouseEvent e) {
                System.out.println("firing");
//                int mx = e.getX() + gp.player.screenX;
//                int my = e.getY() + gp.player.screenY;
//
//                OBJ_Player_Projectile pProj = new OBJ_Player_Projectile(gp, gp.player.screenX, gp.player.screenY, mx, my);
        }


        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
}
