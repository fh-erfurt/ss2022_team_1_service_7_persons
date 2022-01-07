import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean checkPhone(String phone) throws Exception { throw new Exception("Not Implemented Exception"); }

    public static boolean checkFax(String fax) throws Exception { throw new Exception("Not Implemented Exception"); }

    public static boolean checkEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
