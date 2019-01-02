/**
 * module 7 cyber security assignment -2
 * not secure version.
 * author harinathreddy
 * date :- 31-12-18
 */

import java.util.*;
import java.io.*;
class user {
    String user_name, password, question, secrateans;
    boolean lock = false;
    public user(String u_name, String p, String q, String sa) {
        user_name = u_name;
        password = p;
        question = q;
        secrateans  = sa;
    }
}
class Authentic {
    user[] users = new user[10];
    int cou_namet = 0;
    HashMap<String, Integer> list = new HashMap<String, Integer>();
    Console console = System.console();
    public void signup() {
        console.printf("Register with your details :\n");
        String user_name = console.readLine("Enter User Name:\n");
        char[] arr = console.readPassword("Enter Password:\n");
        String password = new String(arr);
        String squestion = console.readLine("Enter your secrate question?\n");
        String sanswer = console.readLine("Enter secrate answer:\n");
        user temp = new user(user_name, password, squestion, sanswer);
        if (cou_namet / 2 == users.length) {
            resize();
        }
        users[cou_namet] = temp;
        list.put(user_name, cou_namet);
        cou_namet++;
        console.printf("Thank you for registerin\n\n");
    }
    public void resize() {
        users = Arrays.copyOf(users, 2 * users.length);
    }
    public void login() {
        Scanner sc = new Scanner(System.in);
        String u_name = "", p;
        int i;
        for (i = 3; i > 0; i--) {
            console.printf("enter username:\n");
            u_name = console.readLine();
            console.printf("enter password:\n");
            char[] arr = console.readPassword();
            p = new String(arr);
            if (checker(u_name, p)) {
                return;
            } else {
                int pos = list.get(u_name);
                if (users[pos].lock) {
                    console.printf("Your Accou_namet Got Blocked\n");
                    return;
                }
                console.printf("1)Do you want to re-enter password (Y/N)?\n");
                console.printf("2)Did you forget password (Y/N)?\n");
                String opt = sc.nextLine();
                if (opt.equals("2")) {
                    reset(u_name);
                    break;
                }
            }
        }
        if (i == 0) {
            int pos = list.get(u_name);
            users[pos].lock = true;
            console.printf("your account got blocked\n");
        }
    }
    public void reset(String user_name) {
        Scanner sc = new Scanner(System.in);
        int pos = list.get(user_name);
        System.out.println(users[pos].question);
        String ans = sc.nextLine();
        if (ans.equals(users[pos].secrateans)) {
            System.out.println("enter new password");
            users[pos].password = sc.nextLine();
        }
    }
    public boolean checker(String u_name, String p) {
        int pos = list.get(u_name);
        if (users[pos].password.equals(p) && !users[pos].lock) {
            System.out.println("USER AUTHENTICATED: ACCESS GRANTED");
            return true;
        } else {
            return false;
        }
    }
}
class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome  please select an option \n\n");
        System.out.println("1) -- signin" + "          " + "2) -- signup" + "          " + "q) -- EXIT\n");
        Authentic authentic = new Authentic();
        while (true) {
            String token = sc.nextLine();
            if (token.equals("q")) {
                System.out.println("***********Thank you*********");
                break;
            } else if (Integer.parseInt(token) == 1) {
                authentic.login();
            } else if (Integer.parseInt(token) == 2) {
                authentic.signup();
            } else {
                System.out.println("choose 1     or      2  or      q");
            }
        }
    }
}