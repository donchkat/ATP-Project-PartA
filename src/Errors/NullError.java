package Errors;

public class NullError extends Exception implements IError {
    @Override
    public void Handle() {
        System.out.println("You did not enter details");
    }
}
