package com.ywmobile.refer;

public class Result {
	private boolean valid;
	private String errMsg;
	
	private Result(boolean valid, String errMsg) {
		this.valid = valid;
		this.errMsg = errMsg;
	}
	
	public boolean isValid() {
		return valid;
	}

	public String getErrMsg() {
		return errMsg;
	}

	private Result(String errMsg) {
		this.valid = false;
		this.errMsg = errMsg;
	}
	
	public static Result success() {
		return new Result(true, null);
	}
	
	public static Result fail(String errMsg) {
		return new Result(false, errMsg);
	}
}
