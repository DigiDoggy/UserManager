import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Chat request:
 * Please provide an example of a program written in Java where the main idea is to work
 * with data of users of any website who have previously registered. Such data as the user's
 * name, birthdate, city, and email will be stored in an Array List (Provide 5 examples of users).
 * The program's menu should be displayed in the terminal where:
 * 1) Show all users - a list of all names will be displayed (when displaying names, make a beautiful
 * separation of names), where before the name is placed " - sequential number (starting from 1)
 * - ". After the entire list, the menu is displayed, you can specify the name or the sequential
 * number in order to show all the user's data + existing notes or exit to the main menu.
 * 2) Create a user. When creating a user, check in the email field if it is an email or not.
 * The name should not start with numbers or spaces.
 * 3) Update user, you can update all data as well as leave a note or edit it.
 * 4) Delete user, data and all notes will be deleted.
 * 5) Exit.
 *
 */

public class UserManager {
    static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Adding some example users
        users.add(new User("John Smith", "johnsmith@email.com", "New York", "01/01/1970"));
        users.add(new User("Jane Doe", "janedoe@email.com", "Los Angeles", "03/03/1980"));
        users.add(new User("Bob Johnson", "bobjohnson@email.com", "Chicago", "05/05/1990"));
        users.add(new User("Amy Lee", "amylee@email.com", "Houston", "07/07/2000"));
        users.add(new User("Mike Brown", "mikebrown@email.com", "Phoenix", "09/09/2010"));

        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("Menu:");
            System.out.println("1) Show all users");
            System.out.println("2) Create a user");
            System.out.println("3) Update user");
            System.out.println("4) Delete user");
            System.out.println("5) Exit");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    showAllUsers();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void showAllUsers() {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + 1 + ") " + users.get(i).name);
        }
        System.out.println("Enter the number of the user you wish to view or enter 0 to return to the main menu:");
        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();
        if (userChoice == 0) {
            return;
        } else if (userChoice > 0 && userChoice <= users.size()) {
            User user = users.get(userChoice - 1);
            System.out.println("Name: " + user.name);
            System.out.println("Email: " + user.email);
            System.out.println("City: " + user.city);
            System.out.println("Birthdate: " + user.birthdate);
            System.out.println("Notes: " + user.notes);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void createUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the user: ");
        String name = input.nextLine();
        if (Character.isDigit(name.charAt(0)) || name.charAt(0) == ' ') {
            System.out.println("Name cannot start with numbers or spaces.");
            return;
        }
        System.out.println("Enter the email of the user: ");
        String email = input.nextLine();
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            System.out.println("Invalid email format.");
            return;
        }
        System.out.println("Enter the city of the user: ");
        String city = input.nextLine();
        System.out.println("Enter the birthdate of the user (MM/DD/YYYY): ");
        String birthdate = input.nextLine();
        User user = new User(name, email, city, birthdate);
        users.add(user);
        System.out.println("User created successfully.");
    }

    public static void updateUser() {
        System.out.println("Enter the number of the user you wish to update or enter 0 to return to the main menu:");
        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();
        if (userChoice == 0) {
            return;
        } else if (userChoice > 0 && userChoice <= users.size()) {
            User user = users.get(userChoice - 1);
            input.nextLine();
            System.out.println("Enter the new name of the user or press enter to keep the current name: ");
            String name = input.nextLine();
            if (!name.isEmpty()) {
                if (Character.isDigit(name.charAt(0)) || name.charAt(0) == ' ') {
                    System.out.println("Name cannot start with numbers or spaces.");
                    return;
                }
                user.name = name;
            }
            System.out.println("Enter the new email of the user or press enter to keep the current email: ");
            String email = input.nextLine();
            if (!email.isEmpty()) {
                Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
                Matcher matcher = pattern.matcher(email);
                if (!matcher.matches()) {
                    System.out.println("Invalid email format.");
                    return;
                }
                user.email = email;
            }
            System.out.println("Enter the new city of the user or press enter to keep the current city: ");
            String city = input.nextLine();
            if (!city.isEmpty()) {
                user.city = city;
            }
            System.out.println("Enter the new birthdate of the user or press enter to keep the current birthdate: ");
            String birthdate = input.nextLine();
            if (!birthdate.isEmpty()) {
                user.birthdate = birthdate;
            }
            System.out.println("Enter new notes for the user or press enter to keep the current notes: ");
            String notes = input.nextLine();
            if (!notes.isEmpty()) {
                user.notes = notes;
            }
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void deleteUser() {
        System.out.println("Enter the number of the user you wish to delete or enter 0 to return to the main menu:");
        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();
        if (userChoice == 0) {
            return;
        } else if (userChoice > 0 && userChoice <= users.size()) {
            users.remove(userChoice - 1);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}