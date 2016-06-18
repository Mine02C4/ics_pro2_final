public class Ticket {
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
