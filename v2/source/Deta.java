package quoridorII;

class Deta{
	int wc, mo, np, nomn, key = -1, menu = 1;
	int[][] p = {{4, 8}, {4, 0}, {0, 0}};
	int[][] wd = new int[20][4];
	int[] n = new int [3];
	int[][][] nom = new int[4][40][2];
	int[] change = {1, 0};
	int[] trans = {1, 0, 3, 2, 4, 5, 6};
	Deta() {
		for(int i = 0 ; i < 4 ; i ++) {
			for(int j = 0 ; j < 40 ; j ++) {
				for(int k = 0 ; k < 2 ; k ++) {
					nom[i][j][k] = -1;
					if(j % 2 == 0) {
						wd[j / 2][k] = -1;
					}
				}
			}
		}
	}
}
/*
np ���݂̃v���C���[
mo�@���݂̍s��
wc�@���݂̕ǂ̕���
key�@���͂��ꂽ�L�[
nomn�@�ړ������̂���ꏊ�̐�
p �v���C���[�A�Z���N�^�[�̍��W
wd �ǂ̍��W�A�F�A����
n �e�v���C���[�̕ǂ̐�
nom �����ʂ̈ړ������̂���ꏊ�̍��W
*/