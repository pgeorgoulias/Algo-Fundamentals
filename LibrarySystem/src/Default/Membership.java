package Default;
import java.util.*;

//Membership class to store membership data
class Membership {
    private String membershipId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Membership(String membershipId, String firstName, String lastName, String emailAddress) {
        this.membershipId = membershipId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Membership ID: " + membershipId + ", Name: " + firstName + " " + lastName + ", Email: " + emailAddress;
    }
}

