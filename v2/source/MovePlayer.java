package quoridorII;

class MovePlayer{
	static int XY[]= {1, 1, 0, 0}, FB[]= {-1, 1, 1, -1}, jump, last,
			priority[][] = {{0, 2, 3}, {1, 3, 2}, {2, 0, 1}, {3, 1, 0}},
			motion[] = {0,8,8,0};
	static void run(Deta d) {
		int a = d.change[d.np];
		if(d.key == 6 && (d.p[2][0] != 0 || d.p[2][1] != 0)) {
			d.p[d.np][0] += d.p[2][0];
			d.p[d.np][1] += d.p[2][1];
			d.np = d.change[d.np];
			last = -1;
		}
		d.p[2][0] = d.p[2][1] = 0;
		move:switch(d.key) {
		case 0:
		case 1:
		case 2:
		case 3:
			if(last != d.key) {
				jump = -1;
			}
			last = d.key;
			if(Motion.judgement(d.p[d.np][0], d.p[d.np][1], d.key, d.nom) == 1) {
				break;
			}
			d.p[2][XY[d.key]] = FB[d.key];
			if(d.p[d.np][0] + d.p[2][0] == d.p[a][0]
					&& d.p[d.np][1] + d.p[2][1] == d.p[a][1]){
				if(Motion.judgement(d.p[a][0], d.p[a][1], d.key, d.nom) == 0) {
					d.p[2][XY[priority[d.key][0]]]
							+= FB[priority[d.key][0]];
					break;
				}
				for(int i = 1 ; i < 3 ; i ++) {
					if(Motion.judgement(d.p[a][0], d.p[a][1], priority[d.key][i], d.nom) == 0) {
						if(i != jump) {
							d.p[2][XY[priority[d.key][i]]]
									+= FB[priority[d.key][i]];
							jump = i;
							break move;
						}
					}
				}
				d.p[2][XY[d.key]] = 0;
				break;
			}
			break;
		case 5:
			if(d.n[d.np] < 10) {
				d.mo = 1;
			}
			d.wd[d.n[2]][2] = d.np;
			break;
		}
	}
}