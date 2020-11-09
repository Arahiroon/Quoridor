package quoridorAI;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

class Enemy extends Thread{
	static ArrayList<Integer> keylog = new ArrayList<>();
	static final int computedepth = 4, sign[] = {1, -1}, goal[] = {0, 8};
	static int ab[] = new int[computedepth + 1];
	static int is[] = new int[computedepth + 1];
	static int percent = -1;
	static Deta d;
	public static ArrayList<Integer> brain(Deta deta) {
		d = Deta.toDeta(Deta.toDetalist(deta));
		for(int j = d.n[2] ; j < 20 ; j ++) {
			for(int k = 0 ; k < 2 ; k ++) {
				d.wd[j][k] = 0;
			}
		}
		keylog.clear();
		Enemy brain = new Enemy();
		brain.start();
		new Bar();
		return keylog;
	}
	@Override
	public void run() {
		Compute(0, d, true, true, false);
		Enemy.percent = 100;
	}
	static int Compute(int depth, Deta deta, boolean first1, boolean first2, boolean move) {
		if(move) {
			deta.np = Deta.change[deta.np];
			int result = Checker.GoalDistance(goal[deta.np], 0, 0, 0, 0, deta.p[deta.np][0], deta.p[deta.np][1], deta.nom);
			System.out.println(result);
			return result * -sign[deta.np] - 100;
		}
		if(depth == computedepth) {
			int result;
			result = Checker.GoalDistance(8, 0, 0, 0, 0, deta.p[1][0], deta.p[1][1], deta.nom)
					- Checker.GoalDistance(0, 0, 0, 0, 0, deta.p[0][0], deta.p[0][1], deta.nom);
			if(first2 == false && (result - ab[depth]) * sign[depth % 2] <= 0) {
				return 200 + result;
			}
			return result;
		}
		int result = 0;
		boolean start = true;
		for(int i = 0 ; i < 132 ; i ++, is[depth] ++) {
			if(depth == 0) {
				percent = Math.round(i * 100 / 132);
			}
			if(deta.n[deta.np] == 10) {
				if(i < 4) {
					System.out.println(deta.np + "," + i);
					move = true;
				}
				else {
					continue;
				}
			}
			if(depth != 0 && i > 3 && i < is[depth - 1]) {
				continue;
			}
			ArrayList<Integer> keys = new ArrayList<>();
			Deta d = Deta.toDeta(Deta.toDetalist(deta));
			keys = KeySet.Keycreate(i);
			if(Negotiate(d, keys, keys.size()) == false) {
				continue;
			}
			int midresult = Compute(depth + 1, d, start, first1, move);
			if(midresult > 100 && depth != 0) {
				return midresult - 200;
			}
			else if(start == true) {
				result = midresult;
				if(depth == 0) {
					System.out.println("選択" + i);
					keylog.clear();
					keylog.addAll(keys);
				}
				start = false;
			}
			else if(depth % 2 == 0) {
				if(midresult > result) {
					result = midresult;
					if(depth == 0) {
						System.out.println("選択" + i);
						keylog.clear();
						keylog.addAll(keys);
					}
				}
			}
			else if(midresult < result) {
				result = midresult;
				if(depth == 0) {
					keylog.clear();
					keylog.addAll(keys);
				}
			}
		}
		if(first2 == false && result - ab[depth]< 0) {
			return 200 + result;
		}
		if(depth != 0 && depth != computedepth - 1) {
			ab[depth + 1] = result;
			ab[depth + 2] = 0;
		}
		is[depth] = 0;
		return result;
	}
	static boolean Negotiate(Deta d, ArrayList<Integer> key, int size) {
		for(int i = 0 ; i < size ; i ++) {
			int p = d.np;
			d.key = key.get(i);
			Systems.key(d);
			if(d.key == 6 && d.np == p) {
				d.wc = d.mo = 0;
				if(d.n[2] < 20) {
					d.wd[d.n[2]][0] = d.wd[d.n[2]][1] = 0;
				}
				return false;
			}
		}
		return true;
	}
}
class Bar extends JFrame{
	Bar(){
		getContentPane().setLayout(new FlowLayout());
        //プログレスバーを作る
        JProgressBar pb = new JProgressBar(1, 100);
        //プログレスバーをフレームに貼る
        getContentPane().add(pb);
        pb.setValue(0);
        pb.setStringPainted(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("now loading……");
        setSize(200, 100);
        setVisible(true);
        int i = 0;
        while(true){
        	try {
        		Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        	if(Enemy.percent > i) {
        		i = Enemy.percent;
        		pb.setValue(i);
        	}
        	if(i == 100) {
        		break;
        	}
        }
        setVisible(false);
	}
}