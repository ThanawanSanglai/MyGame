package f2.spw;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Str {

	public static void main(String[] args){
		
		JFrame frame = new JFrame("Space War");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 650);
		JButton b1 = new JButton("START");
		b1.addActionListener(new Go());
		frame.add(b1);
		frame.setVisible(true);

    }
   
}

class Go implements ActionListener{
	public void actionPerformed(ActionEvent e){
			
		JFrame frame = new JFrame("Space War");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 650);
		frame.setVisible(true);

		frame.setLayout(new GridLayout(1,2,0,0));

		
		SpaceShip v1 = new SpaceShip(180, 550, 20, 20);
		SpaceShip v2 = new SpaceShip(180, 550, 20, 20);

		Player1 p1 = new Player1();
		Player2 p2 = new Player2();
		GameEngine1 engine1 = new GameEngine1(p1, v1);
		GameEngine2 engine2 = new GameEngine2(p2, v2);

		frame.addKeyListener(engine1);
		frame.addMouseListener(engine2);
		frame.add(p1);
		frame.add(p2);
		frame.setVisible(true);
	
		engine1.start();
		engine2.start();
		

	}

}

/*public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 650);
		frame.setLayout(new GridLayout(1,2,0,0));


		
		SpaceShip v1 = new SpaceShip(180, 550, 20, 20);
		SpaceShip v2 = new SpaceShip(180, 550, 20, 20);

		Player1 p1 = new Player1();
		Player2 p2 = new Player2();
		GameEngine1 engine1 = new GameEngine1(p1, v1);
		GameEngine2 engine2 = new GameEngine2(p2, v2);

		frame.addKeyListener(engine1);
		frame.addMouseListener(engine2);
		frame.add(p1);
		frame.add(p2);
		frame.setVisible(true);

		//engine1.start();
		//engine2.start();

		str = new Str();

		
	}*/

	/*public class Str {
		public static Str str;
		public Str (){
			Graphics g = new Graphic();
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial",1,100));
			g.drawString("START!!", 350,300);

			engine1.start();
			engine2.start();


		}
	}*/
//}
