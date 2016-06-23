import java.io.Serializable;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	private int memberId;
	private int concertId;

	public Ticket(int memberId, int concertId) {
		this.setMemberId(memberId);
		this.setConcertId(concertId);
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getConcertId() {
		return concertId;
	}

	public void setConcertId(int concertId) {
		this.concertId = concertId;
	}
}
