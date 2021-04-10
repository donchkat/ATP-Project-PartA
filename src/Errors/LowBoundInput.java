package Errors;

public class LowBoundInput extends Exception implements IError {

    @Override
    public void Handle() {
        System.out.println("you entered too lower input");
    }
}
