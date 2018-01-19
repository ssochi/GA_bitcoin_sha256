package gene;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static boolean randomBoolean(){
		if(Math.random()>0.5)
			return true;
		return false;
		
	}

	public static List<Boolean> createRandomGene(int len) {
		List<Boolean> gene = new ArrayList<>();
		for(int i=0;i<len;i++){
			gene.add(randomBoolean());
		}
		return gene;
	}

	public static List<Boolean> cloneList(List<Boolean> g1) {
		List<Boolean> res = new ArrayList<>();
		for(boolean b : g1){
			res.add(b);
		}
		return res;
	}
	public static String print(List<Boolean> list){
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");

		for(Boolean b : list){
			sb.append(b?"1":"0");
			sb.append(",");
		}
		
		sb.append("[");
		return sb.toString();
		
	}

	public static String binList2HexStr(List<Boolean> gene) {
		StringBuilder sb = new StringBuilder();
		for(Boolean b : gene){
			sb.append(b?"1":"0");
		}
		BigInteger integer = new BigInteger(sb.toString(),2);
		
		return integer.toString(16);
	}
}
