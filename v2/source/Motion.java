package quoridorII;

class Motion{
	static int motion[] = {0,8,8,0};
	static int judgement(int x, int y, int move, int w[][][]) {
		if(move < 2) {
			if(y == motion[move]) {
				return 1;
			}
		}
		else {
			if(x == motion[move]) {
				return 1;
			}
		}
		for(int i = 0; i < 40 ; i ++){
			if(x == w[move][i][0] && y == w[move][i][1]){
				return 1;
			}
		}
		return 0;
	}
}