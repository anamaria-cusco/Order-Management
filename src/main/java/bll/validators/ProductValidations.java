package bll.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductValidations {

    public boolean isValidPrice(float price) {
        return price > 0.0f;
    }

    public boolean isValidName(String name){
        String pPattern = "^[a-zA-Z0-9-\\s]*$";
        Pattern p = java.util.regex.Pattern.compile(pPattern);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public boolean isValidStockQuantity(int stockQuantity) {
        return stockQuantity > 0;
    }
}
