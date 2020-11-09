package ver2;

public class Motion{
	static int motion[] = {0,8,8,0};
	public static int judgement(int move, int position) {
		if(Deta.p[position][MovePlayer.XY[move]] == motion[move]) {
			return 1;
		}
		for(int i = 0; i < Deta.nomn ; i ++){
			if(Deta.p[position][0] == Deta.nom[move][i][0]
					&& Deta.p[position][1] == Deta.nom[move][i][1]){
				return 1;
			}
		}
		return 0;
	}
}