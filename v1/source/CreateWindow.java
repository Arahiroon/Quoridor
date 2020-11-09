package ver2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateWindow{
	public static void Draw() {
		DrawWindow gw = new DrawWindow("QUORIDOR");
		DrawCanvas dc = new DrawCanvas();
		gw.add(dc);//描画領域の追加
		gw.setVisible(true);
	}
}
class DrawCanvas extends JPanel{
	URL u[] = {DrawCanvas.class.getResource("ボード.png"), DrawCanvas.class.getResource("白コマ.png"),
			DrawCanvas.class.getResource("黒コマ.png"), DrawCanvas.class.getResource("選択.png"),
			DrawCanvas.class.getResource("メニュー.png")};
	URL wu[] = new URL[20];
	Image bord = Toolkit.getDefaultToolkit().getImage(u[0]);
	Image wp = Toolkit.getDefaultToolkit().getImage(u[1]);
	Image bp = Toolkit.getDefaultToolkit().getImage(u[2]);
	Image se = Toolkit.getDefaultToolkit().getImage(u[3]);
	Image menu = Toolkit.getDefaultToolkit().getImage(u[4]);
	Image[] wall = new Image[20];
	int[][] ws = {{0, 35}, {35, 0}};
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bord, 0, 0, this);
		g.drawImage(wp, Deta.p[0][0] * 70, Deta.p[0][1] * 70, this);
		g.drawImage(bp, Deta.p[1][0] * 70, Deta.p[1][1] * 70, this);
		for(int j = 0 ; j < Deta.n[2] ; j ++) {
			wu[j] = DrawCanvas.class.getResource(Deta.wd[j][2] + "_" + Deta.wd[j][3] + ".png");
			wall[j] = Toolkit.getDefaultToolkit().getImage(wu[j]);
			g.drawImage(wall[j], Deta.wd[j][0] * 70 + ws[Deta.wd[j][3]][0],
					Deta.wd[j][1] * 70 + ws[Deta.wd[j][3]][1], this);
		}
		if(Deta.mo == 1) {
			wu[Deta.n[2]] = DrawCanvas.class.getResource(Deta.np + "_" + Deta.wc + ".png");
			wall[Deta.n[2]] = Toolkit.getDefaultToolkit().getImage(wu[Deta.n[2]]);
			g.drawImage(wall[Deta.n[2]], Deta.wd[Deta.n[2]][0] * 70 + ws[Deta.wc][0],
					Deta.wd[Deta.n[2]][1] * 70 + ws[Deta.wc][1], this);
		}
		else {
			g.drawImage(se, (Deta.p[Deta.np][0] + Deta.p[2][0]) * 70,
					(Deta.p[Deta.np][1] + Deta.p[2][1]) * 70, this);
		}
		if(Deta.menu == 1) {
			g.drawImage(menu, 0, 0, this);
		}
	}
}
class DrawWindow extends JFrame{
	public DrawWindow(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(width,height);
		this.getContentPane().setPreferredSize(new Dimension(620, 620));
		this.pack();
		setLocationRelativeTo(null);
		setResizable(false);
		KeyProcess keyprocess = new KeyProcess();
		addKeyListener(keyprocess);
	}
	private class KeyProcess extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				Deta.key = 0;
				break;
			case KeyEvent.VK_DOWN:
				Deta.key = 1;
				break;
			case KeyEvent.VK_RIGHT:
				Deta.key = 2;
				break;
			case KeyEvent.VK_LEFT:
				Deta.key = 3;
				break;
			case KeyEvent.VK_SPACE:
				Deta.key = 4;
				break;
			case KeyEvent.VK_BACK_SPACE:
				Deta.key = 5;
				break;
			case KeyEvent.VK_ENTER:
				Deta.key = 6;
				break;
			case KeyEvent.VK_ESCAPE:
				Deta.menu = Deta.change[Deta.menu];
				break;
			}
				Systems.key();
				repaint();
			if(Deta.p[0][1] == 0){
				System.out.println("白の勝利！");
				setVisible(false);
				System.exit(0);
			}
			if(Deta.p[1][1] == 8) {
				System.out.println("黒の勝利！");
				setVisible(false);
				System.exit(0);
			}
		}
	}
}