package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine1 implements KeyListener, GameReporter{
	Player1 p1;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v1;
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine1(Player1 p1, SpaceShip v1 ) {
		this.p1 = p1;
		this.v1 = v1;	
		
		p1.sprites.add(v1);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		p1.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				p1.sprites.remove(e);
				score += 100;
				
			}
		}
		
		p1.updateGameUI(this);

		Rectangle2D.Double vr = v1.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();

		
	}
	
	void controlVehicle1(KeyEvent k) {

		double tsp = 100;


		switch (k.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v1.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v1.move(1);
			break;
		/*case KeyEvent.VK_D:
			difficulty += 0.1;
			break;*/
		}

		if(score % 1000 == 0){
			difficulty += 0.01;
		}

	}
	


	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		controlVehicle1(k);
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent k) {
		//do nothing		
	}

}
