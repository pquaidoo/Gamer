import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, getPreviousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row =0;
        while(col<gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent() {

        //Check if player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventX);
        int distance = Math.max(xDistance, yDistance);
        if(distance >gp.tileSize){
            canTouchEvent=true;
        }
        if(canTouchEvent==true){
            if(hit(34,7, "left") == true) {
            //pass coordinates and direction
            damagePit(34,7,gp.dialogueState);
             }
            if(hit(23,7, "any") == true) {
                healingPool(gp.dialogueState);
            }
        }

        if(hit(31, 19, "up") == true) {
            teleport(gp.dialogueState);
        }

    }
    public boolean hit(int col, int row, String reqDirection) { // checks if the player hits the event rectangle

        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventX = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void teleport(int gameState) {

        gp.gameState = gameState;
        //super.speak(0);
        System.out.println("teleport");
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;
    }
    public void damagePit(int col, int row, int gameState) {

        gp.gameState = gameState;
        System.out.println("oof");
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent =false;
    }
    public void healingPool(int gameState) {
        //System.out.println("heal");
        if(gp.keyH.enterPressed  == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You Drunk the water.\nYour life has been recovered.";
            gp.player.life = gp.player.maxLife;
            canTouchEvent =false;

        }
    }
}
