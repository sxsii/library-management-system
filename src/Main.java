import  javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Transactions transactions = new Transactions();
        transactions.initialiseValues();
        boolean running = true;

        while (running) {
            String[] options = {"Add User","Add Book", "Borrow Book", "Return Book", "Remove Book", "Display Users", "Display Books"/*,"Browse Books"*/, "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome! Choose an option:", "Library Management System",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    transactions.addUser();
                    break;
                case 1:
                    transactions.addBook();
                    break;
                case 2:
                    transactions.borrowBook();
                    break;
                case 3:
                    transactions.returnBook();
                    break;
                case 4:
                    transactions.removeBook();
                    break;
                case 5:
                    transactions.displayUsers();
                    break;
                case 6:
                    transactions.displayBooks();
                    break;
                case 7:/*
                    transactions.searchForBook();
                    break;
                case 8:*/
                    JOptionPane.showMessageDialog(null, "Exiting program.");
                    running = false;
                    System.exit(0);
                    break;
            }
        }
    }
}
