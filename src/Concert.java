import java.util.Date;

public class Concert {
	private int id;
	private String name;
	private int genreId;
	private Date startTime;
	private int siteId;
	private int price;

	public Concert(int id, String name, int genreId, Date startTime, int siteId, int price) {
		this.setId(id);
		this.setName(name);
		this.setGenreId(genreId);
		this.setStartTime(startTime);
		this.setSiteId(siteId);
		this.setPrice(price);
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

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
