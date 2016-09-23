package jamie20160927;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javafx.scene.layout.BackgroundImage;

class BackgroundImg extends JPanel{
	Image img;
	public BackgroundImg(String img){
		this(new ImageIcon(img).getImage());
	}
	public BackgroundImg(Image img) {
		this.img = img;
		Dimension imgsize = new Dimension(img.getWidth(null),img.getHeight(null));
		setPreferredSize(imgsize);	
		setMaximumSize(imgsize);
		setMinimumSize(imgsize);
		setSize(imgsize);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	
}

public class Welcome extends JFrame{
	private BackgroundImg background;
	private JButton newgame, record;
	private JPanel JPane;


	public Welcome() {
		super("FiveChesses");
		
//		if (JPane == null) {
//	        JPane = new JPanel();
//	        JPane.setLayout(new FlowLayout());
//		}
//		JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED, 
//                Color.lightGray, Color.lightGray));
//        JPane.add(panel);

		background = new BackgroundImg(Toolkit.getDefaultToolkit().getImage("pic/bg.jpg"));		

	    newgame = new JButton("  New Game  ");
//	    record = new JButton("     Record     ");
	    newgame.setForeground(Color.white);
	    newgame.setBackground(new Color(64,2,2));
	    newgame.setFont(new Font("Times New Roman", Font.BOLD, 30));
	    newgame.setBorder(BorderFactory.createLineBorder(Color.white)); 
	    
//	    record.setForeground(Color.white);
//	    record.setBackground(new Color(64,2,2));
//	    record.setFont(new Font("Times New Roman", Font.BOLD, 30));
//	    record.setBorder(BorderFactory.createLineBorder(Color.white)); 
	    
	    FlowLayout fl = new FlowLayout();
	    fl.setVgap(300);
	    background.setLayout(fl);
	    background.add(newgame);	        
//	    background.add(record);
//	    background.add(panel);
		getContentPane().add(background);
		
		setSize(680, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		newgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						start();		
			}
		});
		
	}
	private void start(){
		ChessFrame cf = new ChessFrame();
		cf.isShowing();	
		
		dispose();
	}

	
	
	public static void main(String[] args) {
		new Welcome();
		

	}

}


