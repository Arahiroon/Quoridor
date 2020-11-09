package quoridorAI;

class Wallput {
	static int[][] dicP = {{1, 1, 0, 0,}, {0, -1, -1, 0}, {8, 7, 7, 8}};
	static int[] plus = {-1, 1, 1, -1};
	static int[][][] nms1 = {{{1, 0},{1, 0}}, {{2, 2},{3, 3}}};  /*no move setter*/
	static int[][][] nms2 = {{{0, 0},{1, 1}}, {{0, 1},{0, 1}}};
	static void run(Deta d) {
		switch (d.key) {
		case 0:
		case 1:
		case 2:
		case 3:
			if(d.wd[d.n[2]][dicP[0][d.key]] > dicP[1][d.key]
					&& d.wd[d.n[2]][dicP[0][d.key]] < dicP[2][d.key]) {
				d.wd[d.n[2]][dicP[0][d.key]] += plus[d.key];
			}
			break;
		case 4:
			d.wc = d.change[d.wc];
			break;
		case 5:
			d.mo = 0;
			break;
		case 6:
			if(CrossWall.cross(d) == 0) {
				for(int i = 0 ; i < 2 ; i ++) {
					for(int j = 0 ; j < 2 ; j ++) {
						d.nom[nms1[d.wc][i][j]][d.nomn + nms2[d.wc][i][j]][0]
								= d.wd[d.n[2]][0] + i;
						d.nom[nms1[d.wc][i][j]][d.nomn + nms2[d.wc][i][j]][1]
								= d.wd[d.n[2]][1] + j;
					}
				}
				if(NotPut.simu(0, d.p[0][0], d.p[0][1], d) == 0
						&& NotPut.simu(8, d.p[1][0], d.p[1][1], d) == 0) {
					d.wd[d.n[2]][3] = d.wc;
					d.wc = d.mo = 0;
					d.n[d.np] += 1;
					d.n[2] += 1;
					d.np = d.change[d.np];
					d.nomn += 2;
				}
				else {
					for(int j = 0; j < 4 ; j ++) {
						for(int i = 0; i < 2 ; i ++) {
							d.nom[j][d.nomn][i] = -1;
							d.nom[j][d.nomn + 1][i] = -1;
						}
					}
				}
			}
		}
	}
}