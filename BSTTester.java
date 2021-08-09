import java.util.ArrayList;
import java.util.List;

public class BSTTester {
    public static void main(String[] args) {
//        testGeneral();
//        testGeneral2();
//        testTraversals1();
//        testTraversals2();
//        testAll();
//        testInorderExam();
    }

    // One put and one get/containsKey per map (tree won't be deeper than the root). Print size of BSTs.
    private static void testGeneral() {
        System.out.println("\n____Testing (put/get/containsKey/size) 1____");
        // Simulate a bank account map.
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        // check if account 123 exists in BST accounts
        System.out.println("Contains: " + accounts.containsKey(123));
        System.out.println("Get: " + accounts.get(123)); // get the balance for account 123
        System.out.println("Size BST accounts: " + accounts.size()); // prints the size of BST accounts

        // Simulate an address book.
        BSTMap<String, String> emails = new BSTMap<String, String>();
        emails.put("Elvis Presley", "elvis@graceland.com");
        // check if Elvis's email address exists in BST emails
        System.out.println("Contains Elvis: " + emails.containsKey("Elvis Presley"));
        System.out.println("Get: " + emails.get("Elvis Presley")); // get Elvis's email address
        // check if Waldo's email address exists in BST emails
        System.out.println("Contains John: " + emails.containsKey("John Doe"));
        System.out.println("Get: " + emails.get("John Doe")); // get John Doe's email (does not exist)
        System.out.println("Size BST emails: " + emails.size()); // prints the size of BST emails
    }

    // Multiple puts and gets, testing making deeper trees. Prints size of BSTs.
    private static void testGeneral2() {
        System.out.println("\n____Testing (put/get/containsKey/size) 2____");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        // check if account 123 exists in BST accounts
        System.out.println("Contains 123: " + accounts.containsKey(123));
        System.out.println("Get: " + accounts.get(123));
        System.out.println("Contains 456: " + accounts.containsKey(456));
        System.out.println("Get: " + accounts.get(456));
        System.out.println("Contains 78: " + accounts.containsKey(78));
        System.out.println("Get: " + accounts.get(78));
        System.out.println("Size BST accounts: " + accounts.size()); // prints the size of BST accounts

        BSTMap<String, String> emails = new BSTMap<String, String>();
        emails.put("Elvis Presley", "elvis@graceland.com");
        emails.put("Albus Dumbledore", "dumbledore@hogwarts.edu");
        emails.put("Dorothy Gale", "dorothy@oz.org");
        emails.put("Grace Hopper", "hopper@navy.mil");  // look her up!
        // check if Elvis's email address exists in BST emails
        System.out.println("\nContains Elvis: " + emails.containsKey("Elvis Presley"));
        System.out.println("Get: " + emails.get("Elvis Presley"));
        System.out.println("Contains Dumbledore: " + emails.containsKey("Albus Dumbledore"));
        System.out.println("Get: " + emails.get("Albus Dumbledore"));
        System.out.println("Contains Dorothy: " + emails.containsKey("Dorothy Gale"));
        System.out.println("Get: " + emails.get("Dorothy Gale"));
        System.out.println("Contains Hopper: " + emails.containsKey("Grace Hopper"));
        System.out.println("Get: " + emails.get("Grace Hopper"));
        System.out.println("Size BST emails: " + emails.size()); // prints the size of BST emails
    }

    // Traversals on a balanced tree
    private static void testTraversals1() {
        System.out.println("\n____Testing Traversals 1____");
        BSTMap<Integer, Integer> map = createBst(List.of(4, 2, 6, 1, 3, 5, 7));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("Smaller than 4: " + map.countSmalller(9));
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    // Traversal on a linear tree
    private static void testTraversals2() {
        System.out.println("\n____Testing Traversals 2____");
        BSTMap<Integer, Integer> map = createBst(List.of(1, 2, 3, 4, 5, 6, 7));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    private static void testInorderExam() {
        System.out.println("Exam");
        BSTMap<Integer, Integer> map = createBst(List.of(52, 35, 83, 17, 89, 44, 68, 80, 81, 75));
        System.out.println("Inorder Exam: " + map.inorderKeys());
    }

    // General test that tests all the methods
    private static void testAll() {
        System.out.println("\n____Testing Put____");
        // Simulate a report card map.
        BSTMap<Integer, Integer> classStudents = new BSTMap<Integer, Integer>();
        // Put the classes and the number of students per class in the map
        classStudents.put(9, 95);
        classStudents.put(10, 89);
        classStudents.put(11, 77);
        classStudents.put(12, 90);
        // Print out the initial map
        System.out.println("\nList: [ Grade 9: " + classStudents.get(9) + ", Grade 10: " + classStudents.get(10) + ", Grade 11: " + classStudents.get(11) + ", Grade 12: " + classStudents.get(12) + " ]");
        System.out.println("Size: " + classStudents.size());
        System.out.println("Height: " + classStudents.height());
        // Check if the map contains certain keys
        System.out.println("\nContains grade 9: " + classStudents.containsKey(9));
        System.out.println("Contains grade 12: " + classStudents.containsKey(12));
        // Remove key 12
        classStudents.remove(12);
        System.out.println("\n*** Grade 12 removed. ***");
        System.out.println("\nList: [ Grade 9: " + classStudents.get(9) + ", Grade 10: " + classStudents.get(10) + ", Grade 11: " + classStudents.get(11) + ", Grade 12: " + classStudents.get(12) + " ]");
        System.out.println("Size: " + classStudents.size());
        System.out.println("Height: " + classStudents.height());
        // Check contains again
        System.out.println("\nContains grade 9: " + classStudents.containsKey(9));
        System.out.println("Contains grade 12: " + classStudents.containsKey(12));
        // Put key 12 back
        classStudents.put(12, 90);
        System.out.println("\n*** Grade 12 put back. ***");
        System.out.println("\nList: [ Grade 9: " + classStudents.get(9) + ", Grade 10: " + classStudents.get(10) + ", Grade 11: " + classStudents.get(11) + ", Grade 12: " + classStudents.get(12) + " ]");
        System.out.println("Size: " + classStudents.size());
        System.out.println("Height: " + classStudents.height());
        // Print traversals of classes
        System.out.println("\nPreorder: " + classStudents.preorderKeys());
        System.out.println("Inorder: " + classStudents.inorderKeys());
        System.out.println("Postorder: " + classStudents.postorderKeys());
    }

    /**
     * Helper method to create a BST map from the numbers in a list.  It just uses zero
     * for every value, since this is mostly used to test the traversal methods.
     */
    private static BSTMap<Integer, Integer> createBst(List<Integer> list) {
        BSTMap<Integer, Integer> map = new BSTMap<>();
        for (int x = 0; x < list.size(); x++) {
            map.put(list.get(x), 0);
        }
        return map;
    }
}