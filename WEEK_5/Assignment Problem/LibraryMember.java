public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // Public no-argument constructor
    public LibraryMember() {
    }
    // Write-once membershipId
    public String getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }
    // Name property
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Premium membership property
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only securityAnswer
    public void setSecurityAnswer(String answer) {

        if (answer != null) {
            securityAnswer = oneWayTransform(answer);
        }
    }

    // One-way deterministic transformation
    private String oneWayTransform(String value) {

        int hash = 7;

        for (int i = 0; i < value.length(); i++) {
            hash = hash * 31 + value.charAt(i);
        }

        return Integer.toHexString(hash);
    }

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        // Set membership details
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());

        // Second attempt should be ignored
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());

        // Premium member
        System.out.println(m.isPremiumMember());

        // Security answer is write-only
        m.setSecurityAnswer("BlueMountain");

        System.out.println("Security answer stored securely.");
    }
}