package model;

public enum OrderStatus {
	NEW(1), OPENED(2), IN_USAGE(3), RETURNED(4), CLOSED(5);

	private int value;

	private OrderStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
};
