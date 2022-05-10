package exception;

public class InvalidUserException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidUserException() {
		super("유효하지 않은 접근입니다.");
	}
	
}
