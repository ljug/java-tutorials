package lambdainterfacefonction;

/**
 *
 * @author pascalfares
 */
public class LambdaInterfacefonction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Set variables with lambda expressions
        OperationBinaire multiply = (x, y) -> x * y;
        OperationBinaire divide = (x, y) -> x / y;

        // Call methods of lambda expressions
        int product = multiply.op(50, 10);
        int quotient = divide.op(50, 10);

        System.out.println("product = " + product + " quotient = " + quotient);
        
        // Pass lambda expressions to a method as arguments
        uneMethode (multiply, divide);
        
        // Pass lambda expressions to a method as arguments
        uneAutreMethode((x, y) -> x * y, (x, y) -> x / y);
        
    }
    
    public static void uneMethode(OperationBinaire m, OperationBinaire d){
        int product = m.op(60, 10);
        int quotient = d.op(60, 10);
        System.out.println("product = " + product + " quotient = " + quotient);
    }
    
    public static void uneAutreMethode(OperationBinaire m, OperationBinaire d){
        int product = m.op(70, 10);
        int quotient = d.op(70, 10);
        System.out.println("product = " + product + " quotient = " + quotient);
    }
    
    
}
