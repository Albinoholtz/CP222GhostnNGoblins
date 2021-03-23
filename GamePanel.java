import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author William & Quinn
 *
 */
public class GamePanel extends JFrame{
	
	private int fps = 60;
	private int frameWidth = 736;
	private int frameHeight = 758;
	private int movedRight = 0;
	private int movedLeft = 0;

	private int myTimerDelay;
	private Timer myTimer;
	private Image backgroundImg;
	private Image foregroundImg;
	private InputListener listener;
	private boolean isRunning = true;
	private BufferedImage backBuffer;
	private int scrolling;
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	ArrayList<Structure> groundArr = new ArrayList<Structure>();

	public GamePanel() {
		
		//creating Knight
		Knight knight = new Knight(250, 575);
		spriteList.add(knight);
	
		//adding initial ground
		int xCor = -92;
		for(int i = 0; i < 20; i++) {
			Structure ground = new Structure(xCor, 580);
			groundArr.add(ground);
			xCor += 46;
			
		}
	}

	public void run() {
		//calls initialization to set up the frame
		initialize();
		
		//runs while the game is going
		while(isRunning) {
			
			if(scrolling > 750) {
				scrolling = -200;
			}
			long time = System.currentTimeMillis();

			update(time);
			draw();

			time = (1000/fps) - (System.currentTimeMillis() - time);
			if(time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e){}
			}
		}
		//"closes" the window when the game stops running
		setVisible(false);
	}

	public void initialize(){
		//setting up all the stuffs
		setTitle("Ghosts N' Goblins");
		setSize(frameWidth, frameHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		
		ImageIcon background = new ImageIcon("background.png");
		backgroundImg = background.getImage();
		
		backBuffer = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_RGB);
		listener = new InputListener(this);
		
		
	}
	
	public void update(long time) {
		
		
		if(listener.isKeyDown(KeyEvent.VK_RIGHT)) {
			movedRight -= 2;
			scrolling -= 1;
			for(int i = 0; i < groundArr.size(); i ++) {
				groundArr.get(i).updateX(-2);
			}
			if(movedRight%46 == 0 ) {
				int lastX = groundArr.get(groundArr.size()-1).getX();
				Structure ground = new Structure(lastX + 46, 580);
				groundArr.add(ground);
			}
		
		}
		
		if(listener.isKeyDown(KeyEvent.VK_LEFT)) {
			movedLeft += 2;
			scrolling += 1;
			for(int i = 0; i < groundArr.size(); i ++) {
				groundArr.get(i).updateX(2);
			}
			if(movedLeft%46 == 0 ) {
				int firstX = groundArr.get(0).getX();
				Structure ground = new Structure(firstX - 46, 580);
				groundArr.add(0, ground);
			}
			
		}
		if(listener.isKeyDown(KeyEvent.VK_UP)) {
			System.out.println("UP");
		}
		if(listener.isKeyDown(KeyEvent.VK_DOWN)) {
			System.out.println("DOWN");
		}
		
		//check for collisions here 
		
		//update all sprites here
		
		//removing ground if neccessary
		
		for (int j = 0; j < groundArr.size(); j++) {
			groundArr.get(j).update();
			if(groundArr.get(j).getRemove() == true) {
				groundArr.remove(j);
			}
			
		}
		
	}
	
	public void draw() {
		Graphics2D g = (Graphics2D) getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		
		bbg.setColor(Color.BLACK);
		bbg.fillRect(0, 0, frameWidth, frameHeight);
		g.drawImage(backgroundImg, scrolling, 150, 400 , 300, null);
		//g.drawImage(backBuffer, 0, 0, this);
	

		//draw initial ground this should be in the initiator
		for (int j = 0; j < groundArr.size(); j++) {
			groundArr.get(j).draw(g);
		}
		
		for(int i = 0; i < spriteList.size(); i ++) {
			spriteList.get(i).draw(g);
		}


	}


}
