public class OBJ_Player_Projectile extends Projectile{
    GamePanel gp;

    public OBJ_Player_Projectile(GamePanel gp) {
        super(gp);
        this.gp=gp;

        name = "pProj";
        speed = 15;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        //System.out.println("firing");
        getImage();

    }
    public void set(int worldX, int worldY, int mx, int my, String direction,boolean alive, Character user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.mx=mx;
        this.my=my;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

        double distX=(mx-(gp.player.worldX));
        double distY=(my-(gp.player.worldY));
        double dist = Math.sqrt((Math.pow(distX,2)+Math.pow(distY,2)));
        VelX = (distX/dist)*speed;
        VelY = (distY/dist)*speed;

        System.out.println("world Position: "+gp.player.worldX/gp.tileSize +", "+gp.player.worldY/gp.tileSize);
        System.out.println("slope: "+distX +", "+distY+", "+dist);

        System.out.println("Vel: "+VelX +", "+VelY);

    }
    public void getImage() {
        up1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize);
    }

}