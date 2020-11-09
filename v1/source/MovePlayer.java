package ver2;

public class MovePlayer{
	static int XY[]= {1, 1, 0, 0}, FB[]= {-1, 1, 1, -1}, jump, last,
			priority[][] = {{0, 2, 3}, {1, 3, 2}, {2, 0, 1}, {3, 1, 0}},
			motion[] = {0,8,8,0};
	public static void run() {
		int a = Deta.change[Deta.np];
		if(Deta.key == 6 && (Deta.p[2][0] != 0 || Deta.p[2][1] != 0)) {
			Deta.p[Deta.np][0] += Deta.p[2][0];
			Deta.p[Deta.np][1] += Deta.p[2][1];
			Deta.np = Deta.change[Deta.np];
			last = -1;
		}
		Deta.p[2][0] = Deta.p[2][1] = 0;
		move:switch(Deta.key) {
		case 0:
		case 1:
		case 2:
		case 3:
			if(last != Deta.key) {
				jump = -1;
			}
			last = Deta.key;
			if(Motion.judgement(Deta.key, Deta.np) == 1) {
				break;
			}
			Deta.p[2][XY[Deta.key]] = FB[Deta.key];
			if(Deta.p[Deta.np][0] + Deta.p[2][0] == Deta.p[a][0]
					&& Deta.p[Deta.np][1] + Deta.p[2][1] == Deta.p[a][1]){
				if(Motion.judgement(priority[Deta.key][0], a) == 0) {
					Deta.p[2][XY[priority[Deta.key][0]]]
							+= FB[priority[Deta.key][0]];
					break;
				}
				for(int i = 1 ; i < 3 ; i ++) {
					if(Motion.judgement(priority[Deta.key][i], a) == 0) {
						if(i != jump) {
							Deta.p[2][XY[priority[Deta.key][i]]]
									+= FB[priority[Deta.key][i]];
							jump = i;
							break move;
						}
					}
				}
				Deta.p[2][XY[Deta.key]] = 0;
				break;
			}
			break;
		case 5:
			if(Deta.n[Deta.np] < 10) {
				Deta.mo = 1;
			}
			Deta.wd[Deta.n[2]][2] = Deta.np;
			break;
		}
	}
}