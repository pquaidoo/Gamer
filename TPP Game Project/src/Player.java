import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Character{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;
        setDefaultValues();
        getplayerImage();
        direction="down";

    }

    /**
     *  Gets player sprites from res directory.
     */
    public void getplayerImage(){
        try{
            up1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_up_1.png"));
            up2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_up_2.png"));
            down1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_down_1.png"));
            down2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_down_2.png"));
            right1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_right_1.png"));
            right2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_right_2.png"));
            left1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_left_1.png"));
            left2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_left_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  Sets Default values for player.
     */
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
    }
    /**
     *  Updates the player data (60FPS).
     */
    public void update(){

        //Checks all player input to see if moving. (so player animation doesn't move when player is still).

        if(keyH.upPressed==true||keyH.downPressed==true||
                keyH.leftPressed==true||keyH.rightPressed==true) {

            //Subtracts speed from x & y coordinates and sets direction for sprite.

            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;

            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;

            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            }

            //Alternates sprite number so draw method can switch sprites for animation.

            spriteCounter++;    //every frame increases counter by 1
            if (spriteCounter > 10) {    //changes sprite every 10 frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    /**
     * Updates screen with player images (60FPS).
     */
    public void draw(Graphics2D graphics2){
//        graphics2.setColor(Color.white);
//        graphics2.fillRect(x,y, /*width*/ gp.tileSize, /*height*/ gp.tileSize);   //Player Character

        //When direction switches: calls appropriate sprite & alternates between them for animation.

        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum==1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum==1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum==1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum==1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;

            }

        //Changes image, puts it where it goes, changes how big it is.
        graphics2.drawImage(image, x, y, gp.tileSize,gp.tileSize,null);
    }
}