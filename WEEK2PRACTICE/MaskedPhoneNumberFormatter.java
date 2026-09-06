package WEEK2PRACTICE;

public class MaskedPhoneNumberFormatter {

    static String maskPhoneNumber(String phone) {

        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFour = phone.substring(6);

        StringBuilder result = new StringBuilder("XXXXXX");

        result.insert(6, "-" + lastFour);

        return result.toString();
    }

    public static void main(String[] args) {

        String phone = "9876543210";

        System.out.println(maskPhoneNumber(phone));
    }
}
