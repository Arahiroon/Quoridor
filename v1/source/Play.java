package ver2;

public class Play{
	public static void main(String[] args) {
		for(int i = 0 ; i < 4 ; i ++) {
			for(int j = 0 ; j < 40 ; j ++) {
				for(int k = 0 ; k < 2 ; k ++) {
					Deta.nom[i][j][k] = -1;
				}
			}
		}
		CreateWindow.Draw();
	}
}