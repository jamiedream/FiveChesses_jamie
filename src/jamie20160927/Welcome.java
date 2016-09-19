package jamie20160927;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.text.Text;





public class Welcome extends JFrame{
	public Welcome() {
		super("FiveChesses");
		
		setContentPane(new JLabel(new ImageIcon("pic/bg.jpg")));
		
		JLabel label = new JLabel();
		label.setText("¤­¤l´Ñ");
		Font font = new Font("Times",Font.BOLD,36);
		label.setFont(font);
		setContentPane.add(label, BorderLayout.CENTER);
		
		
		setSize(680, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Welcome();

	}

}

