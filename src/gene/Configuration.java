package gene;

import interfaces.Fitenss;

public class Configuration {
	//最大适应度，若有个体大于最大适应度则输出
	private double max_fitenss;
	//最大种群容量
	private int max_poplation;
	//基因长度
	private int gene_length;
	//最大繁衍次数
	private int max_cross_times;
	//最大执行次数
	private int max_exec_time;
	//最大交叉互换基因数
	private int max_cross_gene_length;
	//变异权值
	private double vary_rate;
	//是否打印日志
	private boolean log;
	//fitenss 判断函数
	private Fitenss fitenss;
	
	public Configuration() {
		max_fitenss = 256;
		max_poplation = 500;
		gene_length = 256*8;
		max_cross_times = 4;
		max_exec_time = 5000;
		max_cross_gene_length = 4;
		vary_rate = 0.1;
		log = true;
		fitenss = null;
	}
	
	
	
	
	
	
	public double getMax_fitenss() {
		return max_fitenss;
	}
	public void setMax_fitenss(double max_fitenss) {
		this.max_fitenss = max_fitenss;
	}
	public int getMax_poplation() {
		return max_poplation;
	}
	public void setMax_poplation(int max_poplation) {
		this.max_poplation = max_poplation;
	}
	public int getMax_exec_time() {
		return max_exec_time;
	}
	public void setMax_exec_time(int max_exec_time) {
		this.max_exec_time = max_exec_time;
	}
	public boolean isLog() {
		return log;
	}
	public void setLog(boolean log) {
		this.log = log;
	}
	public Fitenss getFitenss() {
		return fitenss;
	}
	public void setFitenss(Fitenss fitenss) {
		this.fitenss = fitenss;
	}
	public int getGene_length() {
		return gene_length;
	}
	public void setGene_length(int gene_length) {
		this.gene_length = gene_length;
	}
	public int getMax_cross_times() {
		return max_cross_times;
	}
	public void setMax_cross_times(int max_cross_times) {
		this.max_cross_times = max_cross_times;
	}
	public int getMax_cross_gene_length() {
		return max_cross_gene_length;
	}
	public void setMax_cross_gene_length(int max_cross_gene_length) {
		this.max_cross_gene_length = max_cross_gene_length;
	}
	public double getVary_rate() {
		return vary_rate;
	}
	public void setVary_rate(double vary_rate) {
		this.vary_rate = vary_rate;
	}
	
	
	
	
	
}
	

