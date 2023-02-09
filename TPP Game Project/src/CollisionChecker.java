public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character chara) {
        int charLeftWorldX = chara.worldX + chara.solidArea.x;
        int charRightWorldX = chara.worldX + chara.solidArea.x + chara.solidArea.width;
        int charTopWorldY = chara.worldY + chara.solidArea.y;
        int charBottomWorldY = chara.worldY + chara.solidArea.y + chara.solidArea.height;

        int charLeftCol = charLeftWorldX / gp.tileSize;
        int charRightCol = charRightWorldX / gp.tileSize;
        int charTopRow = charTopWorldY / gp.tileSize;
        int charBottomRow = charBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (chara.direction) {
            case "up":
                charTopRow = (charTopWorldY - chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charLeftCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[charRightCol][charTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "down":
                charBottomRow = (charBottomWorldY + chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charLeftCol][charBottomRow];
                tileNum2 = gp.tileM.mapTileNum[charRightCol][charBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "left":
                charLeftCol = (charLeftWorldX - chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charLeftCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[charLeftCol][charTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;

            case "right":
                charRightCol = (charRightWorldX + chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charRightCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[charRightCol][charTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;

        }
    }

    public int checkObject(Character chara, boolean player){

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i]!= null){
                //get character's solid area position
                    chara.solidArea.x = chara.worldX+ chara.solidArea.x;
                    chara.solidArea.y = chara.worldY+ chara.solidArea.y;

                //get the object's solid area position

                gp.obj[i].solidArea.x = gp.obj[i].worldX +gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY +gp.obj[i].solidArea.y;

                switch(chara.direction){
                    case"up":
                        chara.solidArea.y -=chara.speed;
                        if(chara.solidArea.intersects(gp.obj[i].solidArea)){//checks if rectangle is intersecting anothter
                            if (gp.obj[i].collision ==true){
                                chara.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case"down":
                        chara.solidArea.y +=chara.speed;
                        if(chara.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision ==true){
                                chara.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;

                    case "left":
                        chara.solidArea.x -=chara.speed;
                        if(chara.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision ==true){
                                chara.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        chara.solidArea.x +=chara.speed;
                        if(chara.solidArea.intersects(gp.obj[i].solidArea)){
                            if (gp.obj[i].collision ==true){
                                chara.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;

                }
                chara.solidArea.x = chara.solidAreaDefaultX;
                chara.solidArea.y = chara.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }

        return index;
    }
}