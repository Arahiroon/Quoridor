package ver2;

public class NotPut{
	public static int simu(int a, int x, int y) {
		int n = 0, x1 = x, y1 = y, b[] = new int[4],
				map[][] = new int[9][9], log[] = new int[81];
		map[x1][y1] = 1;
		while(true){
			if(y1 != a){
				for(int k = 0 ; k < 4 ; k ++){
					if((k == 0 && y1 > 0 && map[x1][y1 - 1] == 0) || (k == 1 && y1 < 8 && map[x1][y1 + 1] == 0)
					|| (k == 2 && x1 < 8 && map[x1 + 1][y1] == 0) || (k == 3 && x1 > 0 && map[x1 - 1][y1] == 0)){
						b[k] = 1;
						for(int i = 0; i < (Deta.n[2] + 1) * 2 ; i ++){
							if(x1 == Deta.nom[k][i][0]){
								if(y1 == Deta.nom[k][i][1]){
									b[k] = 0;
								}
							}
						}
					}
					else {
						b[k] = 0;
					}
				}
				if(b[0] == 1){
					map[x1][-- y1] = 1;
					log[n ++] = 1;
					continue;
				}
				if(b[2] == 1){
					map[++ x1][y1] = 1;
					log[n ++] = 3;
					continue;
				}
				if(b[1] == 1){
					map[x1][++ y1] = 1;
					log[n ++] = 2;
					continue;
				}
				if(b[3] == 1){
					map[-- x1][y1] = 1;
					log[n ++] = 4;
					continue;
				}
				if(n == 0){
					return 1;
				}
				switch(log[-- n]){
					case 1:{
						y1 ++;
						break;
					}
					case 2:{
						y1 --;
						break;
					}
					case 3:{
						x1 --;
						break;
					}
					case 4:{
						x1 ++;
						break;
					}
				}
				continue;
			}
			break;
		}
		return 0;
	}
}