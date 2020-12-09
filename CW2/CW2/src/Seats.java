
public class Seats {
	String seatNum;
	String seatClass;
	Boolean isWindow;
	Boolean isAisle;
	Boolean isTable;
	Double seatPrice;
	String eMail;

public Seats(String seatNum, String seatClass, Boolean isWindow, Boolean isAisle, Boolean isTable, Double seatPrice, String eMail) {
		this.seatNum = seatNum;
		this.seatClass = seatClass;
		this.isWindow = isWindow;
		this.isAisle = isAisle;
		this.isTable = isTable;
		this.seatPrice = seatPrice;
		this.eMail = eMail;

	}
	@Override
	public String toString() {
		return seatNum + " " + seatClass + " " + isWindow + " " + isAisle + " " + isTable + " " + seatPrice + " " + eMail;
	}


}
