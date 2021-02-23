package com.miw.gildedrose.enums;

public enum StatusCode {
	SC_OK(200, "Success"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401, "Unauthorized"), NOT_FOUND(404,
			"Not Found");
	int code;
	String message;

	StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
