package Errors;

/**
 * we throw it if we are out of bound of matrix
 */
public class OutOfBoundMatrixInput extends Exception implements IError {
    @Override
    public void Handle() {
        System.out.println("index is out of bound of matrix");
    }
}
