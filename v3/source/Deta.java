package quoridorAI;

import java.util.ArrayList;
import java.util.List;

class Deta {
	int wc, mo, np, nomn, key = -1, menu = 1;
	int[][] p = {{4, 8}, {4, 0}, {0, 0}};
	int[][] wd = new int[20][4];
	int[] n = new int [3];
	int[][][] nom = new int[4][40][2];
	static final int[] change = {1, 0};
	static final int[] trans = {1, 0, 3, 2, 4, 5, 6};
	Deta() {
		for(int i = 0 ; i < 4 ; i ++) {
			for(int j = 0 ; j < 40 ; j ++) {
				for(int k = 0 ; k < 2 ; k ++) {
					nom[i][j][k] = -1;
				}
			}
		}
	}
	Deta(boolean a) {
		for(int i = 0 ; i < 4 ; i ++) {
			for(int j = 0 ; j < 40 ; j ++) {
				for(int k = 0 ; k < 2 ; k ++) {
					nom[i][j][k] = -1;
					if(j % 2 == 0) {
						wd[j / 2][k] = -1;
					}
				}
			}
		}
	}
	static ArrayList<Integer> toDetalist(Deta deta) {
		ArrayList<Integer> d = new ArrayList<>();
		d.add(deta.wc);
		d.add(deta.mo);
		d.add(deta.np);
		d.add(deta.nomn);
		d.add(deta.key);
		d.add(deta.menu);
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 2 ; j++) {
				d.add(deta.p[i][j]);
			}
		}
		for(int i = 0 ; i < 20 ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				d.add(deta.wd[i][j]);
			}
		}
		for(int i = 0 ; i < 3 ; i++) {
			d.add(deta.n[i]);
		}
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 40 ; j++) {
				for(int k = 0 ; k < 2 ; k++) {
					d.add(deta.nom[i][j][k]);
				}
			}
		}
		return d;
	}
	static Deta toDeta(ArrayList<Integer> d) {
		Deta deta = new Deta();
		deta.wc = d.get(0);
		deta.mo = d.get(1);
		deta.np = d.get(2);
		deta.nomn = d.get(3);
		deta.key = d.get(4);
		deta.menu = d.get(5);
		List<Integer> info = d.subList(0, 6);
		info.clear();
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 2 ; j++) {
				deta.p[i][j] = d.get(0);
				d.remove(0);
			}
		}
		for(int i = 0 ; i < 20 ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				deta.wd[i][j] = d.get(0);
				d.remove(0);
			}
		}
		for(int i = 0 ; i < 3 ; i++) {
			deta.n[i] = d.get(0);
			d.remove(0);
		}
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 40 ; j++) {
				for(int k = 0 ; k < 2 ; k++) {
					deta.nom[i][j][k] = d.get(0);
					d.remove(0);
				}
			}
		}
		return deta;
	}
}
/*
np 現在のプレイヤー
mo　現在の行動
wc　現在の壁の方向
key　入力されたキー
nomn　移動制限のある場所の数
p プレイヤー、セレクターの座標
wd 壁の座標、色、方向
n 各プレイヤーの壁の数
nom 方向別の移動制限のある場所の座標
*/