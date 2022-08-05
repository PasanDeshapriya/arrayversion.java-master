import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

import static java.lang.System.exit;

public class ArrayVersionFinal {
    public static String[][] pump = new String[3][6];
    public static Scanner scanner = new Scanner(System.in);

    public static int stock = 6600;
    public static boolean loop = true;

    public static void main(String[] args){
        LOOP();
    }

    public  static void LOOP() {
        menu();

        System.out.println("\n pls choose an option ");
        String choice = scanner.next();
        switch (valid(choice)) {
            case 0 -> VFQ();
            case 1 -> VEQ();
            case 2 -> ACQ();
            case 3 -> RCQ();
            case 4 -> PCQ();
            case 5 -> VCS();
            case 6 -> SPD();
            case 7 -> LPD();
            case 8 -> STK();
            case 9 -> AFS();
            case 10 -> EXT();
            case 100 -> System.out.println("\n you have selected a wrong option !");
        }
        if(loop){
            LOOP();
        }else{
            System.out.println("\n Thank you for using this program ");
        }
        exit(0);
        //noinspection ResultOfMethodCallIgnored
        scanner.hasNext();


    }

    public  static void menu() {
        System.out.println("---------------MENU--------------\n" +
                "   100 or VFQ: View all Fuel Queues.\n" +
                "   101 or VEQ: View all Empty Queues.\n" +
                "   102 or ACQ: Add customer to a Queue.\n" +
                "   103 or RCQ: Remove a customer from a Queue. (From a specific location)\n" +
                "   104 or PCQ: Remove a served customer.\n" +
                "   105 or VCS: View Customers Sorted in alphabetical order\n" +
                "   106 or SPD: Store Program Data into file.\n" +
                "   107 or LPD: Load Program Data from file\n" +
                "   108 or STK: View Remaining Fuel Stock.\n" +
                "   109 or AFS: Add Fuel Stock.\n" +
                "   999 or EXT: Exit the Program.\n" +
                "-------------------------------------------");

    }


    public static Integer valid(String choice) {
        String[][] choice1 = {{"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "999"}, {"VFQ", "VEQ", "ACQ", "RCQ", "PCQ", "VCS", "SPD", "LPD", "STK", "AFS", "EXT"}};
        int num = 100;
        for (int i = 0; i < 11; i++) {
            if ((Objects.equals(choice1[0][i], choice)) || (Objects.equals(choice1[1][i], choice))) {
                //value of i is saved in num, so we can use it later
                System.out.println("you choose " + choice1[0][i] + ", " + choice1[1][i]);
                num = i;
                break;
            }
        }
        return num;

    }
    public static void VFQ() {

        int out = 0;
        int in = 0;
        System.out.println("View all Fuel Queues");
        for (out = 0; out < 3; out++) {
            for (in = 0; in < 6; in++) {
                if (pump[out][in] == null) {
                    System.out.println("pump " + out + ",row " + in + " : empty");

                } else  {
                    System.out.println("pump " + out + ",row " + in + " : "+pump[out][in]);
                }
            }
        }
    }

    public static void VEQ() {
        int out = 0;
        int in = 0;
        System.out.println("View all Empty Queues.");
        for (out = 0; out < 3; out++) {
            for (in = 0; in < 6; in++) {
                if (pump[out][in] == null) {
                    System.out.println("pump " + out + ",row " + in + " is empty");

                }
            }
        }
    }

    public static void ACQ() {
        int in = 0;
        System.out.println("Add customer to a Queue");
        System.out.println("pls enter customer name");
        String name = scanner.next();
        System.out.println("pls enter which pump (0,1,2)");
        int out = scanner.nextInt();
        if(pump[out][5] ==null) {
            for (in = 0; in < 6; in++) {
                if (Objects.equals(pump[out][in], null)) {
                    System.out.println("a customer "+name+" is added to pump " + out+ ",row " + in+ ".");
                    pump[out][in] =name;
                    break;
                }
            }
        }else{
            System.out.println(" pump " + out + " is full.");
        }
    }

    public static void RCQ() {
        System.out.println("Remove a customer from a Queue. (From a specific location)");
        int out = 0;
        int in = 0;
        System.out.println("pls enter from which row you want to remove(0,1,2)");
        out = scanner.nextInt();
        System.out.println("pls enter from which column you want to remove(0,1,2,3,4,5)");
        in = scanner.nextInt();
        if (Objects.equals(pump[out][in], null)) {
            System.out.println("there is no customer at the given location");
        } else {
            pump[out][in] = null;
            System.out.println("pump " + out + ",row " + in + " customer has been removed.");
            for (int inLoop = in + 1; inLoop < 6; inLoop++) {
                if (pump[out][inLoop] != null) {
                    String temp = pump[out][inLoop - 1];
                    pump[out][inLoop - 1] = pump[out][inLoop];
                    pump[out][inLoop] = temp;
                } else{
                    break;

                }

            }
        }

    }

    public static void PCQ() {
        System.out.println("Remove a served customer.");
        int out = 0;
        if(stock==0){
            System.out.println("NO stocks remaining");
        }else {
            System.out.println("pls enter from which row you want to remove(0,1,2)");
            out = scanner.nextInt();
            for (int in = 0; in < 6; in++) {
                if (!Objects.equals(pump[out][in], null)) {
                    System.out.println("pump " + out + ",row " + in + " severed customer has been removed.");
                    pump[out][in] = null;
                    stock -= 10;
                    for (int inLoop = in+ 1; inLoop < 6; inLoop++) {

                        String temp = pump[out][inLoop - 1];
                        pump[out][inLoop - 1] = pump[out][inLoop];
                        pump[out][inLoop] = temp;
                    }
                } else {
                    System.out.println("pump " + out + " has no customers.");
                }
                break;
            }
            if(stock<=500){
                System.out.println("Only "+stock+" liters stocks remaining");
            }
        }
    }

    public static void VCS() {
        int out = 0;
        int in = 0;
        int count=0;
        int count2=0;
        String[][] Alphabet = new String[3][6];

        for (int outLoop = 0; outLoop < 3; outLoop++) {
            for (int inLoop = 0; inLoop < 6; inLoop++) {
                Alphabet[outLoop][inLoop]=pump[outLoop][inLoop];
            }
        }
        //0
        boolean fo=true;
        for (int inLoop = 0; inLoop < 6; inLoop++) {
            if (Alphabet[0][inLoop] == null) {
                count2++;
            }
        }
        if (count2 != 6) {
            while(true){
                for (out= 0;out <3; out++) {
                    for (in = 0; in < 5; in++) {
                        if (Alphabet[out][in + 1] != null) {
                            assert Alphabet[out][in] != null;
                            if (Alphabet[out][in].compareTo(Alphabet[out][in + 1]) > 0) {

                                String temp = Alphabet[out][in];
                                Alphabet[out][in] = Alphabet[out][in + 1];
                                Alphabet[out][in + 1] = temp;
                            }
                        }
                        count++;
                    }
                }
                if(count==6){
                    System.out.println(count);
                    break;
                }
            }
        }




        for (out = 0; out < 3; out++) {
            for (in = 0; in < 6; in++) {
                if (Alphabet[out][in] == null) {
                    System.out.println("pump " + out + ",row " + in + " : empty");

                } else  {
                    System.out.println("pump " + out + ",row " + in + " : "+Alphabet[out][in]);
                }
            }
        }



    }


    public static void SPD() {
        System.out.println("Store Program Data into file.");
        int out=0;
        int in=0;
        System.out.println("please enter the file name ");
        String fileName=scanner.nextLine();

        try {
            File txt = new File(fileName+".txt");
            boolean x = (txt.createNewFile());
            if (!x) {
                System.out.println("You have already created txt file using this name!");
            } else {
                PrintWriter writeFile = new PrintWriter(txt);
                for (out = 0; out < 3; out++) {
                    for (in = 0; in < 6; in++) {
                        if (pump[out][in] == null) {
                            writeFile.println("pump " + out + ",row " + in + " is empty");
                        }else{
                            writeFile.println("pump " + out + ",row " + in + " : "+pump[out][in]);
                        }
                    }
                }
                writeFile.close();
                System.out.println("\ndata saved into file");
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
        }

    }

    public static void LPD() {
        System.out.println("Load Program Data from file.");
        System.out.println("please enter the file name ");
        String fileName=scanner.nextLine();
        try{
            FileReader load = new FileReader(fileName+".txt");
            int line = load.read();
            while(line != -1){
                System.out.print((char)line);
                line=load.read();
            }
            load.close();
        }catch(Exception e){
            System.out.println("couldn't find a file named "+fileName);
        }

    }

    public static void STK() {
        System.out.println("View Remaining Fuel Stock.");
        System.out.println(stock);
    }

    public static void AFS() {
        System.out.println("Add Fuel Stock.");
        System.out.println("Enter the amount of stock to add.");
        int addStock= scanner.nextInt();
        stock+=addStock;

    }

    public static void EXT() {
        System.out.println("Exit the Program.");
        loop = false;
    }










}

