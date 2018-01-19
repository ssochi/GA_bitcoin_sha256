package gene;

import java.math.BigInteger;
import java.util.*;

import interfaces.Fitenss;

public class Gene {
	
	Configuration cfg;
	
	List<List<Boolean>> pools = new ArrayList<>();
	
	List<Double> fitenssList = new ArrayList<>();
	
	Gene(Configuration cfg){
		this.cfg = cfg;
		
		init_pools();	
	}

	private void init_pools() {
		int len = cfg.getGene_length();
		
		pools.add(Utils.createRandomGene(len));
		pools.add(Utils.createRandomGene(len));
		pools.add(Utils.createRandomGene(len));
		pools.add(Utils.createRandomGene(len));
		pools.add(Utils.createRandomGene(len));
		
	}
	
	public void start(){
		
		for(int t=0;t<cfg.getMax_exec_time();t++){
			
			if(cfg.isLog()) Logger.log("运算次数 : "+t);
			
			fit();
			select();
			cross();
			vary();
			
		}
		
	}
	
	//适应度计算
	private void fit(){
		
		double max = -1;
		List<Boolean> maxFitenssGene = null;
		fitenssList.clear();
		for(List<Boolean> gene : pools){
			
			double fitenss = cfg.getFitenss().fitenss(gene);
			fitenssList.add(fitenss);
			
			if(max<fitenss){
				max = fitenss;
				maxFitenssGene = gene;
			}
			
		}
		
		if(cfg.isLog()){
			Logger.log("最大适应值 ： "+ max);
			Logger.log(Utils.print(maxFitenssGene));
		} 
		
	}
	
	//选择 淘汰
	private void select(){
		int expire = pools.size() - cfg.getMax_poplation();
		
		if(expire>0){
			List<FitenssEntry> lives = new ArrayList<>();
			for(int i=0;i<fitenssList.size();i++){
				lives.add(new FitenssEntry(i, fitenssList.get(i)*Math.random()));
			}
			
			sort(lives);
			
			List<List<Boolean>> temp = new ArrayList<>();
			
			for(int t = expire;t<pools.size();t++){
				FitenssEntry e = lives.get(t);
				temp.add(pools.get(e.index));
			}
			
			//更新pools
			pools = temp;
		}
	}
	
	private void sort(List<FitenssEntry> lives) {
		Collections.sort(lives, new Comparator<FitenssEntry>() {

			@Override
			public int compare(FitenssEntry f1, FitenssEntry f2) {
				double det = f1.fitenss-f2.fitenss;
				if(det>0) return 1;
				else if(det<0) return -1;
				return 0;
			}
		});
	}

	//交叉互换
	private void cross(){
		List<List<Boolean>> temp = new ArrayList<>();
		int len = pools.size();
		
		for(int i=0;i<len;i++){
			int times = (int) (Math.random()*cfg.getMax_cross_times());
			List<Boolean> g = pools.get(i);
			for(int t=0;t<times;t++){
				int tar = (int) (Math.random()*len);
				List<Boolean> newGene = crossNew(g,pools.get(tar));
				temp.add(newGene);
			}
			temp.add(g);
		}
		
		pools = temp;
		
		
	}
	
	private List<Boolean> crossNew(List<Boolean> g1, List<Boolean> g2) {
		int len = g1.size();
		int crossTimes = (int) (Math.random()*cfg.getMax_cross_times());
		
		List<Boolean> temp = Utils.cloneList(g1);
		
		for(int i=0;i<crossTimes;i++){
			int randomPos = (int) (Math.random()*len);
			int crosslen = ((int) ((len-randomPos)*Math.random()));
			for(int t = 0;t < crosslen;t++){
				g1.set(t, g2.get(t));
			}
		}
		
		
		return temp;
	}

	

	//变异
	private void vary(){
		int len = pools.size();
		for(List<Boolean> g : pools){
			if(Math.random()*cfg.getVary_rate()>0.5){
				int pos = (int) (Math.random()*len);
				g.set(pos, !g.get(pos));
			}
		}
	}

	
	static final String target = SHA256.SHA256Encrypt("123");
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		
		Fitenss fitenss = newFitenss();
		
		cfg.setFitenss(fitenss);
//		cfg.setVary_rate(0.5);
//		cfg.setMax_cross_gene_length(8);
//		cfg.setGene_length(512);
		
		
		Gene gene = new Gene(cfg);
		
		gene.start();
	}

	private static Fitenss newFitenss() {
		return new Fitenss() {
			
			@Override
			public double fitenss(List<Boolean> gene) {
				
				String hex = Utils.binList2HexStr(gene);
				
				hex = target + hex;
				
				hex = SHA256.SHA256Encrypt(hex);
				int res = 0;
				
				for(int i=0;i<hex.length();i++){
					if(hex.charAt(i)!='0'){
						res = i;
						break;
					}
				}
				
				return res;
			}
		};
	}
	
	
	
	
	
	
	
}
class FitenssEntry{
	int index;
	double fitenss;
	public FitenssEntry(int index, double fitenss) {
		super();
		this.index = index;
		this.fitenss = fitenss;
	}
	
}
