public class AccessChecker {

    // LibraryMember with the required access modifiers
    static class LibraryMember {

        private String membershipPin;
        String branchCode;              // default
        protected double finesOwed;
        public String displayName;
    }


    // Checks access according to Java visibility rules
    static String classifyAccess(String fieldModifier, String accessorContext) {

        switch (fieldModifier) {

            case "private":
                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "protected":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }


    // Groups the result according to modifier
    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private",
            "default",
            "protected",
            "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            for (int j = 0; j < modifiers.length; j++) {

                if (modifier.equals(modifiers[j])) {

                    if (result.equals("ALLOWED")) {
                        allowed[j]++;
                    } else {
                        denied[j]++;
                    }

                    break;
                }
            }
        }

        StringBuilder summary = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {

            if (i > 0) {
                summary.append(" | ");
            }

            summary.append(modifiers[i])
                   .append(": ")
                   .append(allowed[i])
                   .append(" allowed / ")
                   .append(denied[i])
                   .append(" denied");
        }

        return summary.toString();
    }


    public static void main(String[] args) {

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("protected", "DIFFERENT_PACKAGE")
        );


        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };


        System.out.println(
            summarizeByModifier(attempts)
        );
    }
}