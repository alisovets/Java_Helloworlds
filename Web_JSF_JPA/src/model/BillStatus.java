package model;

public enum BillStatus {
	PRELIMINARY(1), FINAL(2);

	private int value;

	private BillStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
