package bll.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientValidations {


    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    public boolean isValidPhoneNumber(String phoneNumber) {

        String phonePatterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern p = Pattern.compile(phonePatterns);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }


    public boolean isValidName(String name) {
        String namePattern = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
        Pattern p = Pattern.compile(namePattern);
        Matcher m = p.matcher(name);
        return m.matches();
    }




}