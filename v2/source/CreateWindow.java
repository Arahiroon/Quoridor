package quoridorII;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class CreateWindow{
	static DrawCanvas dc;
	static void Draw(String Player) {
		DrawWindow gw = new DrawWindow("QUORIDOR:" + Player);
		dc = new DrawCanvas();
		gw.add(dc);//描画領域の追加
		gw.setVisible(true);
	}
}
class DrawCanvas extends JPanel{
	static Deta d = new Deta();
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
		g.drawImage(wp, d.p[0][0] * 70, d.p[0][1] * 70, this);
		g.drawImage(bp, d.p[1][0] * 70, d.p[1][1] * 70, this);
		for(int j = 0 ; j < d.n[2] ; j ++) {
			wu[j] = DrawCanvas.class.getResource(d.wd[j][2] + "_" + d.wd[j][3] + ".png");
			wall[j] = Toolkit.getDefaultToolkit().getImage(wu[j]);
			g.drawImage(wall[j], d.wd[j][0] * 70 + ws[d.wd[j][3]][0],
					d.wd[j][1] * 70 + ws[d.wd[j][3]][1], this);
		}
		if(d.mo == 1) {
			wu[d.n[2]] = DrawCanvas.class.getResource(d.np + "_" + d.wc + ".png");
			wall[d.n[2]] = Toolkit.getDefaultToolkit().getImage(wu[d.n[2]]);
			g.drawImage(wall[d.n[2]], d.wd[d.n[2]][0] * 70 + ws[d.wc][0],
					d.wd[d.n[2]][1] * 70 + ws[d.wc][1], this);
		}
		else {
			g.drawImage(se, (d.p[d.np][0] + d.p[2][0]) * 70,
					(d.p[d.np][1] + d.p[2][1]) * 70, this);
		}
		if(d.menu == 1) {
			g.drawImage(menu, 0, 0, this);
		}
	}
}
class DrawWindow extends JFrame{
	DrawWindow(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(620, 620));
		this.pack();
		setLocationRelativeTo(null);
		setResizable(false);
		KeyProcess keyprocess = new KeyProcess();
		addKeyListener(keyprocess);
	}
	private class KeyProcess extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				DrawCanvas.d.menu = DrawCanvas.d.change[DrawCanvas.d.menu];
				DrawCanvas.d.key = -1;
				repaint();
			}
			else if(DrawCanvas.d.np == 0) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
					DrawCanvas.d.key = 0;
					break;
				case KeyEvent.VK_DOWN:
					DrawCanvas.d.key = 1;
					break;
				case KeyEvent.VK_RIGHT:
					DrawCanvas.d.key = 2;
					break;
				case KeyEvent.VK_LEFT:
					DrawCanvas.d.key = 3;
					break;
				case KeyEvent.VK_SPACE:
					DrawCanvas.d.key = 4;
					break;
				case KeyEvent.VK_BACK_SPACE:
					DrawCanvas.d.key = 5;
					if(DrawCanvas.d.wd[DrawCanvas.d.n[2]][0] == -1) {
						DrawCanvas.d.wd[DrawCanvas.d.n[2]][0] = DrawCanvas.d.wd[DrawCanvas.d.n[2]][1] = 0;
					}
					break;
				case KeyEvent.VK_ENTER:
					DrawCanvas.d.key = 6;
					break;
				}
				Systems.key(DrawCanvas.d);
				Client.out.println(DrawCanvas.d.trans[DrawCanvas.d.key]);
				Client.out.flush();
				repaint();
				if(DrawCanvas.d.p[0][1] == 0){
					JOptionPane.showMessageDialog(null, "君の勝ち！");
					setVisible(false);
					System.exit(0);
				}
			}
		}
	}
}