package quoridorII;

class Deta{
	int wc, mo, np, nomn, key = -1, menu = 1;
	int[][] p = {{4, 8}, {4, 0}, {0, 0}};
	int[][] wd = new int[20][4];
	int[] n = new int [3];
	int[][][] nom = new int[4][40][2];
	int[] change = {1, 0};
	int[] trans = {1, 0, 3, 2, 4, 5, 6};
	Deta() {
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