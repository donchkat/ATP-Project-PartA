package Errors;

/**
 * we throw it if we get too lower nums
 */
public class LowBoundInput extends Exception implements IError {

    @Override
    public void Handle() {
        System.out.println("You entered too low input");
    }
}
