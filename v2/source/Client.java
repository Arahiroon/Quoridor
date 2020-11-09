package quoridorII;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Client extends JFrame{
	static Socket socket;
	static PrintWriter out;//出力用のライター
	static InputStreamReader isr;
	static String messe;
	static BufferedReader br;
	Client() {
		//サーバに接続する
		socket = null;
		try {
			//"localhost"は，自分内部への接続．localhostを接続先のIP Address（"133.42.155.201"形式）に設定すると他のPCのサーバと通信できる
			//10000はポート番号．IP Addressで接続するPCを決めて，ポート番号でそのPC上動作するプログラムを特定する
			String hostname = JOptionPane.showInputDialog(null,"接続先のホスト名を入力してください\n"
					+ "（先攻はそのままOKボタンを押してください）","ホスト名の入力",JOptionPane.QUESTION_MESSAGE);
			if(hostname != null) {
				InetAddress address = InetAddress.getByName(hostname) ;
				socket = new Socket(address, 10000);
				out = new PrintWriter(socket.getOutputStream(), true);
				isr = new InputStreamReader(socket.getInputStream());
				br = new BufferedReader(isr);
				while(true) {
					messe = br.readLine();//データを一行分だけ読み込んでみる
					if (messe != null) {//読み込んだときにデータが読み込まれたかどうかをチェックする
						DrawCanvas.d.np = 1;
						CreateWindow.Draw("Client");
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
					}
				}
			}
		}
		catch (UnknownHostException e) {
		}
		catch (IOException e) {
			Server.serve();
		}
	}
	public static void main(String[] args) {
		Client net = new Client();
	}
}