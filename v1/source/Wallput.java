package ver2;

public class Wallput {
	static int[][] dicP = {{1, 1, 0, 0,}, {0, -1, -1, 0}, {8, 7, 7, 8}};
	static int[] plus = {-1, 1, 1, -1};
	static int[][][] nms1 = {{{1, 0},{1, 0}}, {{2, 2},{3, 3}}};  /*no move setter*/
	static int[][][] nms2 = {{{0, 0},{1, 1}}, {{0, 1},{0, 1}}};
	public static void run() {
		switch (Deta.key) {
		case 0:
		case 1:
		case 2:
		case 3:
			if(Deta.wd[Deta.n[2]][dicP[0][Deta.key]] > dicP[1][Deta.key]
					&& Deta.wd[Deta.n[2]][dicP[0][Deta.key]] < dicP[2][Deta.key]) {
				Deta.wd[Deta.n[2]][dicP[0][Deta.key]] += plus[Deta.key];
			}
			break;
		case 4:
			Deta.wc = Deta.change[Deta.wc];
			break;
		case 5:
			Deta.mo = 0;
			break;
		case 6:
			if(CrossWall.cross() == 0) {
				for(int i = 0 ; i < 2 ; i ++) {
					for(int j = 0 ; j < 2 ; j ++) {
						Deta.nom[nms1[Deta.wc][i][j]][Deta.nomn + nms2[Deta.wc][i][j]][0]
								= Deta.wd[Deta.n[2]][0] + i;
						Deta.nom[nms1[Deta.wc][i][j]][Deta.nomn + nms2[Deta.wc][i][j]][1]
								= Deta.wd[Deta.n[2]][1] + j;
					}
				}
				if(NotPut.simu(0, Deta.p[0][0], Deta.p[0][1]) == 0
						&& NotPut.simu(8, Deta.p[1][0], Deta.p[1][1]) == 0) {
					Deta.wd[Deta.n[2]][3] = Deta.wc;
					Deta.wc = Deta.mo = 0;
					Deta.n[Deta.np] += 1;
					Deta.n[2] += 1;
					Deta.np = Deta.change[Deta.np];
					Deta.nomn += 2;
				}
				else {
					for(int j = 0; j < 4 ; j ++) {
						for(int i = 0; i < 2 ; i ++) {
							Deta.nom[j][Deta.nomn][i] = -1;
							Deta.nom[j][Deta.nomn + 1][i] = -1;
						}
					}
				}
			}
		}
	}
}
