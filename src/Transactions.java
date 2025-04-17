import javax.swing.*;
import java.util.ArrayList;
import  java.util.regex.*;

public class Transactions {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public void initialiseValues(){
        Book book1 = new Book(1, "Of Skeletons and Dragons", "Belle", "Fantasy");
        Book book2 = new Book(2, "The Chronicles of Adam", "Adam Santi", "Autobiography");
        Book book3 = new Book(3, "A Lost Wallet", "Samdu", "Romance");

        //look up CommandLineRunner

        User user1 = new User("Janah", 1, false);
        User user2 = new User("Rico", 2, false);
        User user3 = new User("Sesi", 3, false);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    //methods needed: .addBook(/), .removeBook(/), .returnBook(/), .borrowBook(/), .addUser(/), searchForBook(/), byISBN(/), byGenre(/), byName(/), byAuthor(/), displayBooks(/), displayUsers(/)

    public void addUser() {
        try {
            int libraryID = Integer.parseInt(JOptionPane.showInputDialog("Please input your library ID: "));
            String name = JOptionPane.showInputDialog("Input your name: ");
            boolean owesBooks = false;

            User newUser = new User(name, libraryID, owesBooks);
            users.add(newUser);

            JOptionPane.showMessageDialog(null, newUser.getName() + " has successfully been added!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }



    public void addBook(){
        try {
            int ISBN = Integer.parseInt(JOptionPane.showInputDialog("Please input the book's ISBN: "));
            String name = JOptionPane.showInputDialog("Input the book's name: ");
            String author = JOptionPane.showInputDialog("Input the book's author: ");
            String genre = JOptionPane.showInputDialog("Input the book's genre: ");

            Book newBook = new Book(ISBN, name, author, genre);
            books.add(newBook);
            JOptionPane.showMessageDialog(null, String.format("%s by %s has been added successfully!", newBook.getName(), newBook.getAuthor()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter correct values.");
        }
    }

    public void removeBook(){
        int checkID = Integer.parseInt(JOptionPane.showInputDialog("Please input the book's ISBN: "));
        boolean check = false;
        String delname = "";
        String delauthor = "";

        for (Book b : books){
            if (checkID == b.getISBN()){
                delname = b.getName();
                delauthor = b.getAuthor();
                books.remove(b);
                check = true;
                break;
            }
        }

        if(check){
            JOptionPane.showMessageDialog(null, String.format("%s by %s has successfully been deleted.", delname, delauthor));
        } else {
            JOptionPane.showMessageDialog(null, "This book does not exist!");
        }
    }

    public void returnBook(){
        int checkUserID = (Integer.parseInt(JOptionPane.showInputDialog("Please input your library ID: ")));
        boolean check = false;

        for (User u : users){
            if (checkUserID == u.getLibraryID()){
                check = true;
                int ISBN = Integer.parseInt(JOptionPane.showInputDialog("Please input the book's ISBN: "));
                String name = JOptionPane.showInputDialog("Input the book's name: ");
                String author = JOptionPane.showInputDialog("Input the book's author: ");
                String genre = JOptionPane.showInputDialog("Input the book's genre: ");

                Book newBook = new Book(ISBN, name, author, genre);
                books.add(newBook);
                JOptionPane.showMessageDialog(null, String.format("You have successfully returned %s by %s!", name, author));
            } else {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }
        }
    }

    public void borrowBook(){
        int checkUserID = (Integer.parseInt(JOptionPane.showInputDialog("Please input your library ID: ")));
        boolean checkUser = false;
        int checkID = 0;

        for (User u : users){
            if (checkUserID == u.getLibraryID()){
                checkUser = true;
                checkID = Integer.parseInt(JOptionPane.showInputDialog("Please input the book's ISBN: "));
                u.setOwesBooks(true);
            } else {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }
        }

        if (checkUser) {
            for (Book b : books){
                if (checkID == b.getISBN()){
                    JOptionPane.showMessageDialog(null, "You have successfully borrowed " + b.getName() + " by " + b.getAuthor() + "!");
                    books.remove(b);
                    break;
                }
            }
        }
    }
    /*
        public void searchForBook(){
            boolean running = true;

            while (running) {
                String[] options = {"ISBN","Name", "Author", "Genre", "Exit"};
                int choice = JOptionPane.showOptionDialog(null, "How do you want to search?", "Book Search",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        byISBN();
                        break;
                    case 1:
                        byName();
                        break;
                    case 2:
                        byAuthor();
                        break;
                    case 3:
                        byGenre();
                        break;
                    case 4:
                        running = false;
                }
            }
        }

        public void byISBN(){
            int checkISBN = Integer.parseInt(JOptionPane.showInputDialog("Please input the book's ISBN: "));
            StringBuilder sb = new StringBuilder("Results:\n");

            for (Book b : books){
                Pattern pattern = Pattern.compile(Integer.toString(checkISBN));
                Matcher matcher = pattern.matcher(Integer.toString(b.getISBN()));
                boolean matchfound = matcher.find();

                if (matchfound){
                    sb.append(b.toString()).append("\n");
                } else {
                    sb.append("No results.");
                }
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

        public void byGenre(){
            String checkGenre = JOptionPane.showInputDialog("Please input a genre: ");
            StringBuilder sb = new StringBuilder("Results:\n");

            for (Book b : books){
                Pattern pattern = Pattern.compile(checkGenre, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(b.getGenre());
                boolean matchfound = matcher.find();

                if (matchfound){
                    sb.append(b.toString()).append("\n");
                } else {
                    sb.append("No results.");
                }
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

        public void byAuthor(){
            String checkAuthor = JOptionPane.showInputDialog("Please input an author: ");
            StringBuilder sb = new StringBuilder("Results:\n");

            for (Book b : books){
                Pattern pattern = Pattern.compile(checkAuthor, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(b.getAuthor());
                boolean matchfound = matcher.find();

                if (matchfound){
                    sb.append(b.toString()).append("\n");
                } else {
                    sb.append("No results.");
                }
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }

        public void byName(){
            String checkName = JOptionPane.showInputDialog("Please input a name: ");
            StringBuilder sb = new StringBuilder("Results:\n");

            for (Book b : books){
                Pattern pattern = Pattern.compile(checkName, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(b.getName());
                boolean matchfound = matcher.find();

                if (matchfound){
                    sb.append(b.toString()).append("\n");
                } else {
                    sb.append("No results.");
                }
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        }
    */
    public void displayBooks(){

        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books in the system.");
            return;
        }

        StringBuilder sb = new StringBuilder("Book List:\n");
        for (Book b : books) {
            sb.append(b.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }


    public void displayUsers(){

        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No users in the system.");
            return;
        }

        StringBuilder sb = new StringBuilder("User List:\n");
        for (User u : users) {
            sb.append(u.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
