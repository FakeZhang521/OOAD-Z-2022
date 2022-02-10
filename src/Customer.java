import java.util.ArrayList;
import java.util.Random;

public class Customer{
    String type;
    Object item;
    private static final Random random = new Random(System.currentTimeMillis());

    Customer(){
        int SKU = random.nextInt(0,16);
    }
}
