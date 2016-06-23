import java.io.Serializable;

public class Member implements HasId, Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

	public Member(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
