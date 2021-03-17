package cn.edu.exception;

public class DeleteException extends RuntimeException {

  public DeleteException() {
  }

  public DeleteException(String message) {
    super(message);
  }

  public DeleteException(String message, Throwable cause) {
    super(message, cause);
  }
}
