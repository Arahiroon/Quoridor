package quoridorII;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Server {
	private static BufferedReader in;//バッファリングをによりテキスト読み込み用の配列
	//mainプログラム
	static void serve() {
		try {
			ServerSocket server = new ServerSocket(10000);//10000番ポートを利用する
			Dialog dia = new Dialog();
			dia.add(new Diame());
			dia.setVisible(true);
			Client.socket = server.accept();
			dia.setVisible(false);
			//必要な入出力ストリームを作成する
			Client.isr = new InputStreamReader(Client.socket.getInputStream());
			in = new BufferedReader(Client.isr);
			Client.out = new PrintWriter(Client.socket.getOutputStream(), true);
			Client.br = new BufferedReader(Client.isr);
			Client.out.println(0);
			Client.out.flush();
			CreateWindow.Draw("Server");
			while(true) {
				try {
					Client.messe = Client.br.readLine();
					if(Client.messe != null) {
						DrawCanvas.d.key = Integer.parseInt(Client.messe);
						if(DrawCanvas.d.key == 5 && DrawCanvas.d.wd[DrawCanvas.d.n[2]][0] == -1) {
							DrawCanvas.d.wd[DrawCanvas.d.n[2]][0] = DrawCanvas.d.wd[DrawCanvas.d.n[2]][1] = 7;
						}
						Systems.key(DrawCanvas.d);
						CreateWindow.dc.repaint();
						if(DrawCanvas.d.p[1][1] == 8) {
							CreateWindow.dc.setVisible(false);
							JOptionPane.showMessageDialog(null, "君の負け…");
							System.exit(0);
						}
					}
				} catch (Exception e１) {
					CreateWindow.dc.setVisible(false);
					System.exit(0);
				}
			}
		} catch (Exception e) {
		}
	}
}
class Dialog extends JFrame{
	Dialog(){
		super("ダイアログ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,100);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
class Diame extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("通信待機中…", 80, 35);
	}
}