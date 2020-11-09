package ver2;

public class CrossWall{
	public static int cross() {
		for(int i = 0; i < Deta.n[2];i ++) {
			if(Deta.wd[Deta.n[2]][0] == Deta.wd[i][0]) {
				if(Deta.wd[Deta.n[2]][1] == Deta.wd[i][1])
					return 1;
				if(Deta.wc == Deta.wd[i][3] && Deta.wc == 1
				&& (Deta.wd[Deta.n[2]][1] == Deta.wd[i][1] + 1
				|| Deta.wd[Deta.n[2]][1] == Deta.wd[i][1] - 1))
					return 1;
			}
			else if(Deta.wd[Deta.n[2]][1] == Deta.wd[i][1]) {
				if(Deta.wc == Deta.wd[i][3] && Deta.wc == 0
				&& (Deta.wd[Deta.n[2]][0] == Deta.wd[i][0] + 1
				|| Deta.wd[Deta.n[2]][0] == Deta.wd[i][0] - 1))
					return 1;
			}
		}
		return 0;
	}
}