import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A map implemented with a binary search tree.
 */
public class BSTMap<K extends Comparable<K>, V> {

    private Node<K, V> root;    // points to the root of the BST.

    /**
     * Create a new, empty BST.
     */
    public BSTMap() {
        root = null;
    }

    /**
     * Put (add a key-value pair) into this BST.  If the key already exists, the old
     * value will be overwritten with the new one.
     */
    public void put(K newKey, V newValue)
    {
        if (root == null) // if tree is empty
        {
            Node<K, V> newNode = new Node<K, V>(); // create new node
            newNode.key = newKey;
            newNode.value = newValue;
            root = newNode; // set root equal to the new node
        }
        else // if tree is not empty
            put(root, newKey, newValue); // call helper function
    }
    
    /**
     * Helper function for put.
     */
    private void put(Node<K, V> curr, K newKey, V newValue)
    {
        if (curr.key.equals(newKey)) // if new key exists (cannot add twice)
            curr.value = newValue;
        else if (newKey.compareTo(curr.key) < 0) // if newKey < currKey, look at the left branch
        {
            if (curr.left == null) // if left branch is empty, create and put new node
            {
                Node<K, V> newNode = new Node<K, V>();
                newNode.key = newKey;
                newNode.value = newValue;
                curr.left = newNode;
            }
            else // if left branch is not empty, recursively call the method on the left branch
                put(curr.left, newKey, newValue);
        }
        else // if newKey > currKey, look at right branch
        {
            if (curr.right == null) // if right branch is empty, create and put new node
            {
                Node<K, V> newNode = new Node<K, V>();
                newNode.key = newKey;
                newNode.value = newValue;
                curr.right = newNode;
            }
            else // if right branch is not empty, recursively call the method on the right branch
                put(curr.right, newKey, newValue);
        }
    }

    /**
     * Get a value from this BST, based on its key.  If the key doesn't already exist in the BST,
     * this method returns null.
     */
    public V get(K searchKey)
    {
        return get(root, searchKey); // call helper function
    }

    /**
     * Helper function for get.
     */
    private V get(Node<K, V> curr, K searchKey)
    {
        if (curr == null) // if searchKey does not exist in tree, return null
            return null;
        else if (searchKey.equals(curr.key)) // if searchKey equals the key of the current node
            return curr.value;
        else if (searchKey.compareTo(curr.key) < 0) // if searchKey < key of the current node
            return get(curr.left, searchKey); // recursive call on the left branch
        else // if searchKey > key of the current node
            return get(curr.right, searchKey); // recursive call on the right branch
    }

    /**
     * Get a value from this BST, based on its key.  If the key doesn't already exist in the BST,
     * this method returns false.
     */
    public boolean containsKey(K searchKey)
    {
        return containsKey(root, searchKey); // call helper function
    }

    /**
     * Helper function for containsKey.
     */
    private boolean containsKey(Node<K, V> curr, K searchKey)
    {
        if (curr == null) // if searchKey does not exist in tree, return false
            return false;
        else if (curr.key.equals(searchKey)) // if searchKey equals the key of the current node
            return true;
        else if (searchKey.compareTo(curr.key) < 0) // if searchKey < key of the current node
            return containsKey(curr.left, searchKey); // recursive call on the left branch
        else // if searchKey > key of the current node
            return containsKey(curr.right, searchKey); // recursive call on the right branch
    }

    /**
     * Given a key, remove the corresponding key-value pair from this BST.  Returns true
     * for a successful deletion, or false if the key wasn't found in the tree.
     */
    public boolean remove(K removeKey)
    {
        Node<K, V> curr = root; // last pointer will be to the node we want to remove
        Node<K, V> parent = null; // last pointer will be to the parent of the node we want to remove
        while (curr != null && !curr.key.equals(removeKey))
        {
            // traverse the BST, look for the node that contains removeKey
            // stop when we find it, or when we point to a null
            parent = curr;
            if (removeKey.compareTo(curr.key) < 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        // removeKey is successfully found
        if (curr == null)
            return false; // removeKey does not exist in the BST

        // curr points to the node containing removeKey
        // divide solution into 2 cases

        // case 1: node has 2-children
        if (curr.left != null && curr.right != null)
        {
            // find next "inorder" value to succeed curr
            Node<K, V> ioSuccessor = curr.right;
            Node<K, V> ioSuccessorParent = curr;
            while (ioSuccessor.left != null)
            {
                ioSuccessorParent = ioSuccessor;
                ioSuccessor = ioSuccessor.left;
            }
            // copy inorder successor's key into curr
            curr.key = ioSuccessor.key;
            // reassign values of curr and parent to remove pointers from the removeKey
            curr = ioSuccessor;
            parent = ioSuccessorParent;
        }

        // case 2: node has 1-child or 0-children
        Node<K, V> subtree; // 0-child: null.   1-child: subtree of curr
        if(curr.left == null && curr.right == null) // 0-child: null.
            subtree = null;
        else if (curr.left != null) // 1-left-child: left child subtree
            subtree = curr.left;
        else // 1-right-child: right child subtree
            subtree = curr.right;

        // attach subtree to the correct child pointer of the parent node.
        if (parent == null) // if the parent does not exist, delete root and subtree becomes new root
            root = subtree;
        else if (parent.left == curr) // delete parent's left child
            parent.left = subtree;
        else // delete parent's right child
            parent.right = subtree;

        return true; // this is only reachable if the removal of removeKey is successful
    }

    /**
     * Return the number of key-value pairs in this BST.
     */
    public int size()
    {
        return size(root); // call helper function
    }

    /**
     * Helper function for size.
     */
    private int size(Node<K, V> curr)
    {
        if (curr == null) // if tree is empty
            return 0;
        else // return size of left subtree + size of right subtree + 1 (to account for curr)
            return (size(curr.left) + size(curr.right) + 1);
    }

    /**
     * Return the height of the longest subtree from the node in BST.
     */
    public int height()
    {
        return height(root); // call helper function
    }

    /**
     * Helper function for height.
     */
    private int height(Node<K, V> curr)
    {
        if (curr == null) // if tree is empty
            return -1;
        else
        {
            int heightLeft = height(curr.left); // variable holding recursive call to find height of left subtree
            int heightRight = height(curr.right); // variable holding recursive call to find height or right subtree

            // if the tree only has one element
            if (curr.left == null && curr.right == null)
                return 0;
            // if the height of the left subtree is greater than the height of the right subtree
            else if (heightLeft > heightRight)
                return (heightLeft + 1); // return height of the left subtree + 1 (curr)
            // if the height of the right subtree is greater than the height of the left subtree
            else
                return (heightRight + 1); // return height of the right subtree + 1 (curr)
        }
    }

    /**
     * Return a List of the keys in this BST, ordered by a preorder traversal.
     */
    public List<K> preorderKeys()
    {
        ArrayList<K> list = new ArrayList<K>(); // arraylist to hold keys
        return preorderKeys(root, list); // call helper function
    }

    /**
     * Helper function for preorderKeys.
     */
    private List<K> preorderKeys(Node<K, V> curr, List<K> list)
    {
        if (curr == null) // if tree is empty
            return list; // return empty list
        else
        {
            list.add(curr.key); // add root key
            preorderKeys(curr.left, list); // traverse left subtree
            preorderKeys(curr.right, list); // traverse right subtree
        }
        return list;
    }

    /**
     * Return a List of the keys in this BST, ordered by an inorder traversal.
     */
    public List<K> inorderKeys()
    {
        ArrayList<K> list = new ArrayList<K>(); // arraylist to hold keys
        return inorderKeys(root, list); // call helper function
    }

    /**
     * Helper function for inorderKeys.
     */
    private List<K> inorderKeys(Node<K, V> curr, List<K> list)
    {
        if (curr == null) // if tree is empty
            return list; // return empty list
        else
        {
            inorderKeys(curr.left, list); // traverse left subtree
            list.add(curr.key); // add root key
            inorderKeys(curr.left, list); // traverse right subtree
        }
        return list;
    }

    /**
     * Return a List of the keys in this BST, ordered by a postorder traversal.
     */
    public List<K> postorderKeys()
    {
        ArrayList<K> list = new ArrayList<K>(); // arraylist to hold keys
        return postorderKeys(root, list); // call helper function
    }

    /**
     * Helper function for postorderKeys.
     */
    private List<K> postorderKeys(Node<K, V> curr, List<K> list)
    {
        if (curr == null) // if tree is empty
            return list; // return empty list
        else
        {
            postorderKeys(curr.left, list); // traverse left subtree
            postorderKeys(curr.right, list); // traverse right subtree
            list.add(curr.key); // add root key
        }
        return list;
    }

    public int countSmalller(K num)
    {
        return countSmaller(root, num);
    }

    private int countSmaller(Node<K, V> node, K num)
    {
        int count = 0;

        if (node == null)
            return 0;
        else if (node.key.compareTo(num) < 0)
        {
            count++;
            count += countSmaller(node.left, num);
            count += countSmaller(node.right, num);
        } else
        {
            count += countSmaller(node.left, num);
            count += countSmaller(node.right, num);
        }
        return count;
    }

    /**
     * Private nested Node class that is useful for iterating through the tree. The member
     * variables are public so that they could be accessed out of the class when necessary.
     */
    private static class Node<K extends Comparable<K>, V> {
        public K key = null;
        public V value = null;
        public Node<K, V> left = null;     // you may initialize member variables of a class when they are defined;
        public Node<K, V> right = null;    // this behaves as if they were initialized in a constructor.
    }
}
