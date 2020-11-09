package quoridorAI;

import java.util.ArrayList;
import java.util.List;

class KeySet{
	static ArrayList<Integer> Keycreate(int situ){
		ArrayList<Integer> key = new ArrayList<>();
		if(situ < 4) {
			key.add(situ);
		}
		else {
			key.add(5);
			if(situ >= 68) {
				key.add(4);
				situ -= 64;
			}
			situ -= 4;
			while(situ >= 8) {
				key.add(2);
				situ -= 8;
			}
			while(situ >= 1) {
				key.add(1);
				situ -= 1;
			}
		}
		key.add(6);
		return key;
	}
	static void Keydelete(ArrayList<Integer> key) {
		if(key.size() != 0) {
			key.remove(key.lastIndexOf(6));
			List<Integer> k = key.subList(key.lastIndexOf(6) + 1, key.size());
			k.clear();
		}
	}
	static ArrayList<Integer> Keycut(ArrayList<Integer> key) {
		if(key.size() != 0) {
			key.remove(key.lastIndexOf(6));
			List<Integer> k1 = key.subList(key.lastIndexOf(6) + 1, key.size());
			k1.add(6);
			ArrayList<Integer> k2 = new ArrayList<>();
			for(int i = 0 ; i < k1.size() ; i ++) {
				k2.add(k1.get(i));
			}
			k1.clear();
			return k2;
		}
		return null;
	}
}