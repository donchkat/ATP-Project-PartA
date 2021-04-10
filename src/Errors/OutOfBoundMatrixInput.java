package Errors;

public class OutOfBoundMatrixInput extends Exception implements IError {
    @Override
    public void Handle() {
        System.out.println("index out of bound of matrix");
    }
}
