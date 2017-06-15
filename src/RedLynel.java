import java.io.*;

import javax.imageio.ImageIO;
//import json.org.*;

public class RedLynel extends Monster{

//	Global Variables

	//public JLabel theLabel = new JLabel("SAGFAD");

    public RedLynel(int theX, int theY, /*BufferedImage theScreenShot,*/ ZeldaPlayWindow theWindow, int theNum) {

    	super(theX, theY, theWindow, theNum);

    	for (int i = 0; i < 8; i++){
	    	try{
	    		this.monPic[i] = ImageIO.read(new File("Monster Sprites/Lynel/R" + i + ".gif")); //Saves each of Link's pictures for easy access
	    		//this.linkPic[i].setOpaque(false);
	    	}
	    	catch (IOException e){
	    	}
    	}
    	this.switchImage = true;
    	this.image = this.monPic[0];
    	this.straightWalking = 4;
    	this.speed = 8;
    	this.HP = 3;
    	this.strength = 2;
    	this.damaging = true;
    	this.shootRate = 1;
    	this.projectile = new Sword(this);
    	(new Thread(this.projectile)).start();

    }

    public void run(){

    	this.appear();
    	(new Thread(new MonsterChange(this))).start();

    	while (1>0){
    		if (!this.hurt || !this.knockBack){
	    		this.move();
	    		this.checkChange();
	    		this.linkHit();
	    		this.monHit();
	    		if (this.checkShoot()){
	    			this.shoot();
	    		}
	    		try{
					Thread.sleep(this.speed); //Delay his next image change
				}
				catch (InterruptedException e){
				}
    		}
    		else {
    			this.monockback();
    		}
    		hurtCount++;
    		if (hurtCount == 69){
				if (!this.knockBack){
					this.hurt = false;
					this.invincible = false;
					this.flash = false;
				}
				else {
					hurtCount = 0;
					this.knockBack = false;
				}
			}
			if (this.HP == 0){
				this.die(); //Homestuck reference FTW!
				break;
			}
    		try{
				Thread.sleep(1); //Delay his next image change
			}
			catch (InterruptedException e){
			}
    	}

    }

}