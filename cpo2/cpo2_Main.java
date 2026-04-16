import java.util.Scanner;

public class cpo2_Main {

    public static void main(String[] args) {

        PhoneBook book = new PhoneBook();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== PHONE BOOK =====");
            System.out.println("1 - Add contact");
            System.out.println("2 - Search contact");
            System.out.println("3 - Delete contact");
            System.out.println("4 - Show all");
            System.out.println("5 - Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter number: ");
                String number = sc.nextLine();

                book.add(name, number);

                System.out.println("✔ Contact added");

            } else if (choice == 2) {

                System.out.print("Enter name: ");
                String name = sc.nextLine();

                Node result = book.search(name);

                if (result != null) {
                    System.out.println("✔ Found: " + result.name + " -> " + result.number);
                } else {
                    System.out.println("✖ Not found");
                }

            } else if (choice == 3) {

                System.out.print("Enter name to delete: ");
                String name = sc.nextLine();

                if (book.delete(name)) {
                    System.out.println("✔ Deleted");
                } else {
                    System.out.println("✖ Not found");
                }

            } else if (choice == 4) {

                book.showAll();

            } else if (choice == 5) {

                System.out.println("Bye!");
                break;
            }
        }

        sc.close();
    }
}

/* ===== NODE (элемент списка) ===== */
class Node {

    String name;
    String number;
    Node next;

    Node(String name, String number) {
        this.name = name;
        this.number = number;
        this.next = null;
    }
}

/* ===== PHONE BOOK (связный список) ===== */
class PhoneBook {

    private Node head;

    // добавление
    public void add(String name, String number) {

        Node newNode = new Node(name, number);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // поиск
    public Node search(String name) {

        Node temp = head;

        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    // удаление
    public boolean delete(String name) {

        if (head == null) return false;

        if (head.name.equalsIgnoreCase(name)) {
            head = head.next;
            return true;
        }

        Node temp = head;

        while (temp.next != null) {

            if (temp.next.name.equalsIgnoreCase(name)) {
                temp.next = temp.next.next;
                return true;
            }

            temp = temp.next;
        }

        return false;
    }

    // вывод всех
    public void showAll() {

        if (head == null) {
            System.out.println("Phone book is empty");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.println(temp.name + " -> " + temp.number);
            temp = temp.next;
        }
    }
}