import java.io.Serializable;
import java.util.Date;

public class Concert implements HasId, Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String genre;
	private Date startTime;
	private String site;
	private int price;
	private int capacity;

	public Concert(int id, String name, String genre, Date startTime, String site, int price, int capacity) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.startTime = startTime;
		this.site = site;
		this.price = price;
		this.capacity = capacity;
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

	public String getGenre() {
		return genre;
	}

	public void setGenreId(String genre) {
		this.genre = genre;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getSite() {
		return site;
	}

	public void setSiteId(String site) {
		this.site = site;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
