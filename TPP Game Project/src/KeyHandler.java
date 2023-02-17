import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Gets input from keyboard and sets booleans accordingly so game can react to it.
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    boolean checkDrawTime = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    /**
     * Changes booleans if key is pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();      //returns int of keyCode associated with key pressed.

        //TITLE STATE

        if(gp.gameState==gp.titleState) {

            titleState(code);
        }


        //PLAY STATE
        else if (gp.gameState == gp.playState) {
          playState(code);
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
           dialogueState(code);
        }

        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
          characterState(code);

        }


    }
    public void titleState(int code){

        if(code== KeyEvent.VK_W){
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if(code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){

            if(gp.ui.commandNum== 0){

                System.out.println("attack is canceled");
                gp.gameState=gp.playState;
                gp.player.attackCanceled = false;
                //gp.playMusic(0);
            }
            if(gp.ui.commandNum==1){
                //add later
            }
            if(gp.ui.commandNum==2){
                System.exit(0);
            }
        }
    }
    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
//        if (code == KeyEvent.VK_C) {
//            gp.gameState = gp.characterState;
//        }
        //DEBUG
        if(code == KeyEvent.VK_B){
            if(checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if(checkDrawTime == true) {
                checkDrawTime = false;
            }
        }

    }
    public void pauseState(int code) {
        if (gp.gameState == gp.playState) {
            gp.gameState = gp.pauseState;
        } else if (gp.gameState == gp.pauseState) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
    }

    /**
     * Changes boolean if button is no longer pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code= e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed=false;
        }
        if(code == KeyEvent.VK_S){
            downPressed=false;

        }
        if(code == KeyEvent.VK_A){
            leftPressed=false;

        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed=false;
        }

    }
}
