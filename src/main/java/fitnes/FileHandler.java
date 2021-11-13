package fitnes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class FileHandler {

    File file = new File("C://fitness/members.csv.txt");


    public LinkedList<Member> readFile() {
        LinkedList<Member> membersList = new LinkedList<>();

/*        try (FileReader fileReader = new FileReader(file)) {
            char[] buffer = new char[256];
            int c;
            while ((c = fileReader.read(buffer)) > 0) {

                if(c < 256){
                    buffer = Arrays.copyOf(buffer,c);
                }
                System.out.println(buffer);
            }

            String a = new String(buffer);

            String[] array;
            String parse = ",";
            array = a.split(parse);

            for(int i = 0; i < array.length; i++) {
                char memberType;
                int memberID;
                String name;
                double fees;
                int club;
                int membershipPoints;

                if(array[i].equals("S")){

                }else if(array[i].equals())
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                char memberType = 'S';
                int memberID = 0;
                String name = null;
                double fees = 0;
                int club = 0;
                int membershipPoints = 0;

                String parse = ",";
                String[] array = line.split(parse);
                for (int i = 0; i < array.length; i++) {
                    memberType = array[0].charAt(0);
                    memberID = Integer.parseInt(array[1]);
                    name = array[2];
                    fees = Double.valueOf(array[3]);
                    if (memberType == 'S') {
                        club = Integer.parseInt(array[4]);
                        // membersList.add(new SingleClubMember(memberType, memberID, name, fees, club));
                    } else if (memberType == 'M') {
                        membershipPoints = Integer.parseInt(array[4]);
                        //membersList.add(new MultiClubMember(memberType, memberID, name, fees, membershipPoints));
                    }

                }
                if (memberType == 'S') {
                    membersList.add(new SingleClubMember(memberType, memberID, name, fees, club));
                } else if (memberType == 'M') {
                    membersList.add(new MultiClubMember(memberType, memberID, name, fees, membershipPoints));
                }
                // System.out.println(line);

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        // System.out.println(membersList);
        return membersList;
    }

    public void appendFile(String mem) {
        try (FileWriter writer = new FileWriter(file, true)) {
            String newLine = System.getProperty("line.separator");
            writer.write(mem + newLine);
            //writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void overwriteFile(LinkedList<Member> m) {
        File temp = new File("C://fitness/members.temp.txt");

        try (FileWriter writer = new FileWriter(temp, true)) {
            String newLine = System.getProperty("line.separator");
            for (Member member : m) {
                writer.write(member + newLine);
                // writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.delete()) {
            System.out.println("Файл успешно удален");
        } else {
            System.out.println("Файл не удален");
        }
        if (temp.renameTo(file)) {
            System.out.println("Файл переименован успешно");
        } else {
            System.out.println("Файл не был переименован");
        }

    }

}

