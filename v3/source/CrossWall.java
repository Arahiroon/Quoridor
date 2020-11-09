package quoridorAI;

class CrossWall{
	static int cross(Deta d) {
		for(int i = 0; i < d.n[2];i ++) {
			if(d.wd[d.n[2]][0] == d.wd[i][0]) {
				if(d.wd[d.n[2]][1] == d.wd[i][1])
					return 1;
				if(d.wc == d.wd[i][3] && d.wc == 1
				&& (d.wd[d.n[2]][1] == d.wd[i][1] + 1
				|| d.wd[d.n[2]][1] == d.wd[i][1] - 1))
					return 1;
			}
			else if(d.wd[d.n[2]][1] == d.wd[i][1]) {
				if(d.wc == d.wd[i][3] && d.wc == 0
				&& (d.wd[d.n[2]][0] == d.wd[i][0] + 1
				|| d.wd[d.n[2]][0] == d.wd[i][0] - 1))
					return 1;
			}
		}
		return 0;
	}
}