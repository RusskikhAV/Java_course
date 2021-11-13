package fitnes;

import java.io.File;
import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();
        int choice;

        choice = mm.getChoice();

        if (choice == 1) {
            mm.addMember(members);
        } else if (choice == 2) {
            mm.removeMember(members);
        } else if (choice == 3) {
            mm.printMemberInfo(members);
        }
    }
}
