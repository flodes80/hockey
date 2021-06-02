package chart;

public class TirChartRecord {
	
	private final int amount;
	private final String zoneName;
	
	public TirChartRecord(int amount, String zoneName) {
		this.amount = amount;
		this.zoneName = zoneName;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	
	
}
