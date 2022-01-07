import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_NUMBER = Pattern.compile("(\\(?([\\d \\-)–+/(]+){6,}\\)?([ .\\-–/]?)([\\d]+))", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_FAX = Pattern.compile("^\\+?[0-9]+$", Pattern.CASE_INSENSITIVE);

    public static boolean checkPhone(String phone) { return VALID_PHONE_NUMBER.matcher(phone).find(); }
    public static boolean checkFax(String fax) { return VALID_FAX.matcher(fax).find();}
    public static boolean checkEmail(String email) { return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find(); }
}
