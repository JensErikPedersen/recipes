package dk.serik.recipes.enums;

public enum Rating {
	
	VERY_POOR("Meget dårlig"), POOR("Dårlig"), AVERAGE("Middelmådig"), GOOD("God"), VERY_GOOD("Meget god");
	
	private String label;
	
	private Rating(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

}
