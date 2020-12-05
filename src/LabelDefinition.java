
public class LabelDefinition {

	String name;
	Long id;

	public LabelDefinition(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
}
