package quoridorII;

class Systems {
	static void key(Deta d) {
		if (d.mo == 1 && d.n[d.np] < 10)
			Wallput.run(d);
		else {
			MovePlayer.run(d);
		}
	}
}
