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
	static PrintWriter out;//�o�͗p�̃��C�^�[
	static InputStreamReader isr;
	static String messe;
	static BufferedReader br;
	Client() {
		//�T�[�o�ɐڑ�����
		socket = null;
		try {
			//"localhost"�́C���������ւ̐ڑ��Dlocalhost��ڑ����IP Address�i"133.42.155.201"�`���j�ɐݒ肷��Ƒ���PC�̃T�[�o�ƒʐM�ł���
			//10000�̓|�[�g�ԍ��DIP Address�Őڑ�����PC�����߂āC�|�[�g�ԍ��ł���PC�㓮�삷��v���O��������肷��
			String hostname = JOptionPane.showInputDialog(null,"�ڑ���̃z�X�g������͂��Ă�������\n"
					+ "�i��U�͂��̂܂�OK�{�^���������Ă��������j","�z�X�g���̓���",JOptionPane.QUESTION_MESSAGE);
			if(hostname != null) {
				InetAddress address = InetAddress.getByName(hostname) ;
				socket = new Socket(address, 10000);
				out = new PrintWriter(socket.getOutputStream(), true);
				isr = new InputStreamReader(socket.getInputStream());
				br = new BufferedReader(isr);
				while(true) {
					messe = br.readLine();//�f�[�^����s�������ǂݍ���ł݂�
					if (messe != null) {//�ǂݍ��񂾂Ƃ��Ƀf�[�^���ǂݍ��܂ꂽ���ǂ������`�F�b�N����
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
										JOptionPane.showMessageDialog(null, "�N�̕����c");
										System.exit(0);
									}
								}
							} catch (Exception e�P) {
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