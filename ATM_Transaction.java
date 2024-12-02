import java.util.Scanner;
import java.util.HashMap;

public class ATM_Transaction {
    // Store user account data (username, PIN, and balance)
    static HashMap<String, Integer> usersPin = new HashMap<>();
    static HashMap<String, Integer> usersBalance = new HashMap<>();
    static String currentUser = "";
    static int currentPin = 0;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            // Display the main menu
            System.out.println("Automated Teller Machine");
            System.out.println("1. Register Account");
            System.out.println("2. Login");
            System.out.println("3. About Me");
            System.out.println("4. Exit");
            System.out.print("Your Choice is: ");
            
            // Fixing the input handling
            int choice = s.nextInt();
            s.nextLine(); // To consume the newline character left by nextInt()

            switch(choice) {
                case 1:
                    registerAccount();
                    break;
                case 2:
                    if (login()) {
                        accountMenu();
                    } else {
                        System.out.println("Invalid login credentials.");
                    }
                    break;
                case 3:
                    aboutMe();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method for user registration
    public static void registerAccount() {
        System.out.print("Enter your desired username: ");
        String username = s.nextLine(); // Reading the username as a full line
        if (usersPin.containsKey(username)) {
            System.out.println("Username already exists. Try another username.");
        } else {
            System.out.print("Create a 4-digit PIN: ");
            int pin = s.nextInt();
            s.nextLine(); // Consume the newline character
            if (pin < 1000 || pin > 9999) {
                System.out.println("Invalid PIN. Please use a 4-digit number.");
                return;
            }
            usersPin.put(username, pin);
            usersBalance.put(username, 0); // Initial balance is 0
            System.out.println("Account successfully created! You can now login.");
        }
    }

    // Method for user login
    public static boolean login() {
        System.out.print("Enter username: ");
        String username = s.nextLine(); // Reading the username as a full line
        if (!usersPin.containsKey(username)) {
            System.out.println("Username not found.");
            return false;
        }

        System.out.print("Enter PIN: ");
        int pin = s.nextInt();
        s.nextLine(); // Consume the newline character
        if (usersPin.get(username) == pin) {
            currentUser = username;
            currentPin = pin;
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Incorrect PIN.");
            return false;
        }
    }

    // Method for account operations after login
    public static void accountMenu() {
        while(true) {
            System.out.println("\nWelcome " + currentUser + "!");
            System.out.println("ATM Operations:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Transfer");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");
            System.out.print("Your Choice is: ");
            
            // Fixing the input handling
            int choice = s.nextInt();
            s.nextLine(); // Consume the newline character

            switch(choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    changePin();
                    break;
                case 6:
                    logout();
                    return; // Exit to main menu
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Withdraw method
    public static void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        int withdrawAmount = s.nextInt();
        s.nextLine(); // Consume the newline character
        int balance = usersBalance.get(currentUser);

        if (withdrawAmount <= balance) {
            usersBalance.put(currentUser, balance - withdrawAmount);
            System.out.println("Please collect your cash. Your new balance is: " + (balance - withdrawAmount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Deposit method
    public static void deposit() {
        System.out.print("Enter the amount to deposit: ");
        int depositAmount = s.nextInt();
        s.nextLine(); // Consume the newline character
        int balance = usersBalance.get(currentUser);

        usersBalance.put(currentUser, balance + depositAmount);
        System.out.println("Deposit successful. Your new balance is: " + (balance + depositAmount));
    }

    // Check balance method
    public static void checkBalance() {
        int balance = usersBalance.get(currentUser);
        System.out.println("Your current balance is: " + balance);
    }

    // Transfer method
    public static void transfer() {
        System.out.print("Enter the recipient's username: ");
        String recipient = s.nextLine(); // Reading the recipient username

        if (!usersBalance.containsKey(recipient)) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        int transferAmount = s.nextInt();
        s.nextLine(); // Consume the newline character
        int balance = usersBalance.get(currentUser);

        if (transferAmount <= balance) {
            usersBalance.put(currentUser, balance - transferAmount);
            int recipientBalance = usersBalance.get(recipient);
            usersBalance.put(recipient, recipientBalance + transferAmount);
            System.out.println("Transfer successful. Your new balance is: " + (balance - transferAmount));
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    // Change PIN method
    public static void changePin() {
        System.out.print("Enter your old PIN: ");
        int oldPin = s.nextInt();
        s.nextLine(); // Consume the newline character
        
        if (oldPin == currentPin) {
            System.out.print("Enter your new PIN: ");
            int newPin = s.nextInt();
            s.nextLine(); // Consume the newline character
            if (newPin < 1000 || newPin > 9999) {
                System.out.println("Invalid PIN. Please use a 4-digit number.");
                return;
            }
            usersPin.put(currentUser, newPin);
            currentPin = newPin;
            System.out.println("PIN successfully changed.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    //About Me "Mulokozi"
    public static void aboutMe() {
        System.out.println("\n--------About Me----------");
        System.out.println("Hello! I am MULOKOZI GEOFREY, for email mulokoziwillium@gmail.com .");
        System.out.println("I am passionate about technology and problem-solving.");
        System.out.print("I am constantly learning new skills and improving myself.");
        System.out.println("In my free time, I enjoy reading, coding, and exploring new ideas.");
        System.out.println("Thank you for using the ATM System!");
        System.out.println("-----------------------------");
    }

    // Logout method
    public static void logout() {
        System.out.println("You have successfully logged out.");
        currentUser = "";
        currentPin = 0;
    }
}
