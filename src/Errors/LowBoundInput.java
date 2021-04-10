package Errors;

public class LowBoundInput extends Exception implements IError {

    @Override
    public void Handle() {
        System.out.println("You entered too low input");
    }
}
