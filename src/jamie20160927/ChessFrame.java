package jamie20160927;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.corba.se.impl.orbutil.graph.GraphImpl;

import sun.java2d.loops.FillRect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessFrame extends JFrame{
	private JButton newGame, exit;
	private Chesses chesses;
	
	public ChessFrame() {
		super("FiveChesses");
		
		setLayout(new BorderLayout());
		
		newGame = new JButton("NewGame");
		exit = new JButton("Exit");
		JPanel top = new JPanel(new BorderLayout());
		JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topLeft.add(newGame);topRight.add(exit);
		top.add(topLeft, BorderLayout.WEST);
		top.add(topRight, BorderLayout.EAST);
		add(top, BorderLayout.NORTH);
		top.setBackground(new Color(184,137,24));
		topLeft.setBackground(new Color(184,137,24));
		topRight.setBackground(new Color(184,137,24));
		
		chesses = new Chesses();
		chesses.setBackground(new Color(184,137,24));
		add(chesses, BorderLayout.CENTER);
		
		setSize(680, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		newGame.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restart();			
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	
	}
	protected void restart() {
		chesses.dorestart();
		
	}
	
	public static void main(String[] args) {
		new ChessFrame();
		
	}
}




