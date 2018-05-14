package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine2 implements MouseListener, GameReporter{

	Player2 p2;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v2;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;

	public boolean gameOver, started;
	
	public GameEngine2(Player2 p2, SpaceShip v2 ) {
		this.p2 = p2;
		this.v2 = v2;		
		
		p2.sprites.add(v2);
		
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
		p2.sprites.add(e);
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
				p2.sprites.remove(e);
				score += 100;
			}
		}
		
		p2.updateGameUI(this);

		Rectangle2D.Double vr = v2.getRectangle();
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
	
	
	void controlVehicle2(MouseEvent m) {

		int xMouse = m.getX();
		int st = 600;

		if(xMouse - st > 0){
			v2.move(1);
		}
			
		else if(xMouse - st < 0){
			v2.move(-1);
		}

		if(score % 1000 == 0){
			difficulty += 0.1;
		}

    }
	


	public long getScore(){
		return score;
	}
	
	@Override
	public void mouseClicked(MouseEvent m)	
	{
		controlVehicle2(m);	
	}	
	@Override
	public void mousePressed(MouseEvent m)	{	}
	@Override
	public void mouseReleased(MouseEvent m)	{	}
	@Override
	public void mouseEntered(MouseEvent m)	{	}
	@Override
	public void mouseExited(MouseEvent m)	{	}
}
