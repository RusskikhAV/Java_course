package fitnes;

import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

    FileHandler fh = new FileHandler();

    public int getIntImput() {
        int userIn = 0;

        try {
            Scanner scanner = new Scanner(System.in);
            userIn = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Выберите один из вариантов: ");
            getIntImput();
        }
        return userIn;
    }

    public void printClubOptions() {
        System.out.println("1) Club Mercury\n" +
                "2) Club Neptune\n" +
                "3) Club Jupiter\n" +
                "4) Multi Clubs");
    }

    public int getChoice() {
        int choice;
        System.out.println("WELCOME TO OZONE FITNESS CENTER\n" +
                "================================\n" +
                "1) Add Member\n" +
                "2) Remove Member\n" +
                "3) Display Member Information\n" +
                "Please select an option (or Enter -1 to quit):");
        choice = getIntImput();
        return choice;
    }

    public String addMember(LinkedList<Member> m) {
        String name = null;
        int club;
        String mem;
        double fees = 0;
        int memberID = 1;
        Member mbr;
        char memberType;
        Calculator<Integer> calculator;
        int membershipPoints = 0;

        calculator = (clubID) -> {
            if (clubID == 1) {
                return 900.0;
            } else if (clubID == 2) {
                return 950.0;
            } else if (clubID == 3) {
                return 1000.0;
            } else
                return 900.0 + 950.0 + 1000.0;

        };

        printClubOptions();
        club = getIntImput();

        if (m.isEmpty()) {
            memberID = 1;
        } else if (!m.isEmpty()) {
            memberID = m.getLast().getMemberID() + 1;

        }


        if (club == 1) {
            fees = calculator.calculateFees(club);
            memberType = 'S';

        } else if (club == 2) {
            fees = calculator.calculateFees(club);
            memberType = 'S';
        } else if (club == 3) {
            fees = calculator.calculateFees(club);
            memberType = 'S';
        } else {
            club = 4;
            fees = calculator.calculateFees(club);
            memberType = 'M';
            membershipPoints = 100;
        }

        System.out.println("Enter your name: ");
        try (Scanner scanner = new Scanner(System.in)) {
            name = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (memberType == 'S') {
            m.add(new SingleClubMember(memberType, memberID, name, fees, club));
            mem = String.format("%c,%d,%s,%.0f,%x", memberType, memberID, name, fees, club);
            fh.appendFile(mem);
        } else {
            m.add(new MultiClubMember(memberType, memberID, name, fees, membershipPoints));
            mem = String.format("%c,%d,%s,%.0f,%d", memberType, memberID, name, fees, membershipPoints);
            fh.appendFile(mem);
        }

        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int memberNumber = 0;

        System.out.println("Введите ID участника, которого следует удалить: ");
        try (Scanner scanner = new Scanner(System.in)) {
            memberNumber = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Вы ввели немер ошибочно");
        }

        for (Member member : m) {
            String[] memberInfo = member.toString().split(",");
            try {
                if (memberNumber == member.getMemberID()) {
                    m.remove(member);
                    System.out.println("Учасник с номером " + memberNumber + " был успешно удалён");
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Участника под таким номером не существует");
                System.out.println("Уточните номер участника");
                printMemberInfo(m);
            }

        }




        fh.overwriteFile(m);

    }

    public void printMemberInfo(LinkedList<Member> m) {
        int count = 0;

        for (Member member : m) {
            if (member.getMemberType() == 'S' || member.getMemberType() == 'M') {
                String[] memberInfo = member.toString().split(",");
                System.out.print(count + ": ");
                System.out.print("Тип участника: " + memberInfo[0]
                        + " ID участника: " + memberInfo[1]
                        + " Имя участника: " + memberInfo[2]
                        + " Ежемесечная плата: " + memberInfo[3]);
                if (member.getMemberType() == 'S') {
                    System.out.print(" Учасник клуба: " + memberInfo[4] + "\n");
                } else {
                    System.out.print(" Бонусные баллы: " + memberInfo[4] + "\n");
                }
                count++;
            }


        }
    }
}


