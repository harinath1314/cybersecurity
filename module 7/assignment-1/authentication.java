/**
 * module 7 cyber security assignment -2
 * secure version.
 * author harinathreddy
 * date :- 31-12-18
 */

import java.util.*;
import java.io.*;

/**
 * Class for authentication.
 *
 */
public class authentication {
    /**
     * main solution starts here.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        Console cs = System.console();
        Signup signup = new Signup();
        Boolean task = true;
        while (task) {
            //user selects sign in or sign up action.
            System.out.println("select\n\n"+ "1 - Sign in\n\n" + "2 - Signup\n\n"+"q - exit\n\n");
            String input = sc.nextLine();
            if (input.equals("2")) {
                signup.create();
            } else if (input.equals("1")) {
                System.out.println("PLease Enter USERNAME");
                String username = sc.nextLine();
                System.out.println("PLease Enter PASSWORD");
                String password = new String(cs.readPassword());
                String line = "";
                BufferedReader fileReader = null;
                try {
                    int count = 3;
                    Boolean test = false;
                    while (count > 0) {
                        //creates a buffereader to read the data file.
                        fileReader = new BufferedReader(new FileReader("database"));
                        while ((line = fileReader.readLine()) != null) {
                            String[] tokens = line.split(",");
                            if (username.equals(tokens[0]) && password.equals(tokens[1])) {
                                System.out.println("USER AUTHENTICATED: ACCESS GRANTED");
                                test = true;
                                count = 0;
                                break;
                            }
                        }
                        count--;
                        if (!test) {
                            System.out.println(" your password is incorrect" + "\n"
                                               + "\n" + " a  --> Do you want to re-enter password "
                                               + "\n" + "\n" + " b --> Did you forget password");
                            if (sc.nextLine().equals("a")) {
                                System.out.println("PLease Enter USERNAME");
                                username = sc.nextLine();
                                System.out.println("PLease Enter PASSWORD");

                                password = new String(cs.readPassword());
                            }
                        }
                    }
                    if (!test) {
                        System.out.println("USER AUTHENTICATION FAILED: ACCESS DENIED");
                    }
                    task = false;
                } catch (Exception l) {
                    System.out.println(l.getMessage());
                }
            }
        }
        System.out.println("user authetication task completed");
    }
}
/**
 * Class for signup.
 */
class Signup {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String header = "username,password,hint question,hint answer";
    FileWriter datafile = null;
    /**
     * Constructs the object.
     */
    Signup() {
    }
    /**
     * create the user data,.
     */
    void create() {
        Scanner sc = new Scanner(System.in);
        try {
            datafile = new FileWriter("database", true);
        } catch (IOException a) {
            System.out.println("harinathreddy");
        }
        try {
            System.out.println("enter a username");
            datafile.write(sc.nextLine()+COMMA_DELIMITER);
            System.out.println("set password");
            datafile.write(sc.nextLine()+COMMA_DELIMITER);
            System.out.println("enter a security question");
            datafile.write(sc.nextLine()+COMMA_DELIMITER);
            System.out.println("entr the answer for security question");
            datafile.write(sc.nextLine()+NEW_LINE_SEPARATOR);
            datafile.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }
}
