package iitdurollsix.exception;

import java.time.LocalDateTime;

public class RollSixCustomException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime exceptionTime;
	
	public RollSixCustomException(String msg, LocalDateTime exceptionTime) {
		super(msg);
		this.setExceptionTime(exceptionTime);
		
	}

	public LocalDateTime getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(LocalDateTime exceptionTime) {
		this.exceptionTime = exceptionTime;
	}
}
