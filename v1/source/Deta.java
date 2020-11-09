package ver2;

class Deta{
	static int wc, mo, np, nomn, key = -1, menu = 1;
	static int[][] p = {{4, 8}, {4, 0}, {0, 0}};
	static int[][] wd = new int[20][4];
	static int[] n = new int [3];
	static int[][][] nom = new int[4][40][2];
	static int[] change = {1, 0};
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