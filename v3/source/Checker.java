package quoridorAI;

class Checker{
	static int map[][] = new int[81][3];
	static int log[][] = new int[9][9];
	static int a[][] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	static int GoalDistance(int Goal, int n, int m, int posi, int mo, int x, int y, int w[][][]) {
		if(m == 0) {
			map[0][0] = x;
			map[0][1] = y;
			log[x][y] = 1;
		}
		if(y == Goal) {
			for(int i = 0 ; i < 9 ; i ++) {
				for(int j = 0 ; j < 9 ; j ++) {
					map[9 * i + j][0] = 0;
					map[9 * i + j][1] = 0;
					map[9 * i + j][2] = 0;
					log[i][j] = 0;
				}
			}
			return n;
		}
		if(Motion.judgement(x, y, mo, w) == 0) {
			if(log[x + a[mo][0]][y + a[mo][1]] == 0) {
				map[++ m][0] = x + a[mo][0];
				map[m][1] = y + a[mo][1];
				map[m][2] = n + 1;
				log[x + a[mo][0]][y + a[mo][1]] = 1;
			}
		}
		mo ++;
		if(mo == 4) {
			if(map[posi][2] != map[posi + 1][2]) {
				n ++;
			}
			posi ++;
			mo = 0;
		}
		return GoalDistance(Goal, n, m, posi, mo, map[posi][0], map[posi][1], w);
	}
}
/*
n ˆÚ“®‹——£
m@ˆ——\–ñ”Ô†
posi@ˆ—’†‚Ì”Ô†
*/