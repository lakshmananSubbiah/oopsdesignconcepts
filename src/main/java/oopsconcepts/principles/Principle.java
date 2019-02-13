package oopsconcepts.principles;

public class Principle {

	private Long id;
	
	private String principleName;
	
	private String definition;

	public Long getId() {
		return id;
	}

	public String getPrincipleName() {
		return principleName;
	}

	public String getDefinition() {
		return definition;
	}

	Principle(Long id, String principleName, String definition) {
		super();
		this.id = id;
		this.principleName = principleName;
		this.definition = definition;
	}
	
	private Principle() {
		
	}
	public static class Builder{
		private Principle principle = new Principle();
		
		public Builder(String principleName, String definition){
			principle.definition = definition;
			principle.principleName = principleName;
		}
		
		public Principle build() {
			return this.principle;
		}
	}

	@Override
	public String toString() {
		return "Principle [id=" + id + ", principleName=" + principleName + ", definition=" + definition + "]";
	}
	
}
