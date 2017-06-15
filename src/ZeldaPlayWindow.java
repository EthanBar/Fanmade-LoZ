/**
 * @(#)GraphicalStuff.java
 *
 *
 * @author nitrodragon
 * @version 1.00 2012/3/19
 */
	import java.awt.*;
	import javax.swing.*;
	import java.io.*;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import java.util.*;
    	import java.util.concurrent.atomic.AtomicReference;


public class ZeldaPlayWindow extends JFrame {

	public final int NORTH = 0;
	public final int EAST = 1;
	public final int SOUTH = 2;
	public final int WEST = 3; //Numbers defining the compass directions, never used
	public int x, y; // Link's x and y positions, I wanna say?
	public BufferedImage screenShot; //Nothing important
	public Monster[] theMonster = new Monster[10]; // Array of Monsters
	private String[] monString = new String[10]; // Monster IDs, who it is, etc.
	public Random r = new Random(); // Idk
	private BufferedReader br; // File-handling for tilesets
    boolean intro = false; // For the intro, I'm setting it to false. Don't want no intro crap

    Link thePlayer; //The instance of Link
    map theMap; //The instance of the map in the background (not a minimap)
    
	public ZeldaPlayWindow(String title) throws Exception {
		super(title);
		this.setSize(544,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null); // Get it in the center
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Sets window commands

		BorderLayout border = new BorderLayout();
		thePlayer = new Link (300, 200, this); // Creates an instance of Player as Link
		this.add(thePlayer); // Actually attaches Link to the screen
		theMap = new map (this);
		//FlowLayout ItemPanel = new FlowLayout();  //Organizes the layout, sadly it doesn't exist
		this.setLayout(border);
		this.add(theMap, BorderLayout.CENTER);

		monLoad();

		this.setVisible(true); //Makes final adjustments

		try{
			Thread.sleep(3000); //Delay his next image change
		}
		catch (InterruptedException e){
		    e.printStackTrace();
		}

		this.intro = false;

	}

    void monLoad() throws IOException{
		this.theMap.loading = true;
		br = new BufferedReader(new FileReader("res/" + this.theMap.x + " " + this.theMap.y + ".txt")); //Reads from the file specified for the map

		for (int i = 0; i < 362; i++){
			if (i > 351){
				monString[i-352] = br.readLine();
			}
			else {
				br.readLine();
			}
		}

		for (int i = 0; i < 10; i++){
			if (theMonster[i] == null){
				do {
					x = r.nextInt(14) + 1;
					y = r.nextInt(9) + 1;
				} while (this.theMap.walkable[(y*16) + x] != 1);

				theMonster[i] = chooseMon(x, y, monString[i], i);
				(new Thread(theMonster[i])).start();
			}
		}
		this.theMap.loading = false;
    }

    private Monster chooseMon(int monX, int monY, String theMonString, int theNum) throws IOException{

		switch (theMonString) {
			case ("redoctorok"):
				return new RedOctorok(monX, monY, this, theNum);
			case ("blueoctorok"):
				return new BlueOctorok(monX, monY, this, theNum);
			case ("reddarknut"):
				return new RedDarknut(monX, monY, this, theNum);
			case ("bluedarknut"):
				return new BlueDarknut(monX, monY, this, theNum);
			case ("redgoriya"):
				return new RedGoriya(monX, monY, this, theNum);
			case ("bluegoriya"):
				return new BlueGoriya(monX, monY, this, theNum);
			case ("redmoblin"):
				return new RedMoblin(monX, monY, this, theNum);
			case ("bluemoblin"):
				return new BlueMoblin(monX, monY, this, theNum);
			case ("redlynel"):
				return new RedLynel(monX, monY, this, theNum);
			case ("bluelynel"):
				return new BlueLynel(monX, monY, this, theNum);
			default:
				return null;
		}

    }

    public static void main(String[] args) throws Exception {
	    @SuppressWarnings("unused")
        AtomicReference<ZeldaPlayWindow> theZeldaPlayWindow = new AtomicReference<>(new ZeldaPlayWindow("The Legend of Zelda"));
    }

}
