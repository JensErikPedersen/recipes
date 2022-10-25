package dk.serik.recipes.enums;

public enum Rating {
	
	POOR("Ikke god"), AVERAGE("Middelmådig"), GOOD("God"), VERY_GOOD("Meget god"), FANTASTIC("Fantastisk");
	
	private String label;
	
	private Rating(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

}
