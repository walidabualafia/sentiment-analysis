import java.io.InputStream;
import java.util.*;

public class SentimentAnalysis {
    public static void main(String[] args)
    {
        final boolean PRINT_TREES = false;  // whether or not to print extra info about the maps.

        BSTMap<String, Integer> wordFreqs = new BSTMap<String, Integer>();
        BSTMap<String, Integer> wordTotalScores = new BSTMap<String, Integer>();
        Set<String> stopwords = new TreeSet<String>();

        System.out.print("Enter filename: ");
        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();

        processFile(filename, wordFreqs, wordTotalScores);

        System.out.println("Number of words is: " + wordFreqs.size());
        System.out.println("Height of the tree is: " + wordFreqs.height());

        if (PRINT_TREES)
        {
            System.out.println("Preorder:  " + wordFreqs.preorderKeys());
            System.out.println("Inorder:   " + wordFreqs.inorderKeys());
            System.out.println("Postorder: " + wordFreqs.postorderKeys());
            printFreqsAndScores(wordFreqs, wordTotalScores);
        }

        removeStopWords(wordFreqs, wordTotalScores, stopwords);

        System.out.println("After removing stopwords:");
        System.out.println("Number of words is: " + wordFreqs.size());
        System.out.println("Height of the tree is: " + wordFreqs.height());

        if (PRINT_TREES)
        {
            System.out.println("Preorder:  " + wordFreqs.preorderKeys());
            System.out.println("Inorder:   " + wordFreqs.inorderKeys());
            System.out.println("Postorder: " + wordFreqs.postorderKeys());
            printFreqsAndScores(wordFreqs, wordTotalScores);
        }

        while (true)
        {
            System.out.print("\nEnter a new review to analyze: ");
            String line = scan.nextLine();
            if (line.equals("quit")) break;

            String[] words = line.split(" ");
            // create new double accumulators to facilitate division for the sentiment score
            double totalSize = 0;
            double totalScores = 0;
            // for every word in array words
            for (String word : words) {
                // if word has appeared before and is not a stopword
                if (wordFreqs.containsKey(word) && wordTotalScores.containsKey(word)) {
                    System.out.println("The average sentiment of " + word + " is " + (double) wordTotalScores.get(word) / (double) wordFreqs.get(word));
                    totalScores += (double) wordTotalScores.get(word) / (double) wordFreqs.get(word);
                    totalSize += 1;
                } else // if word is a stopword or has not appeared before
                {
                    if (stopwords.contains(word))
                        System.out.println("Skipping " + word + " (stopword)");
                    else
                        System.out.println("Skipping " + word + " (never seen before)");
                }
            }
            System.out.println("Sentiment score for this review is " + totalScores/totalSize);
        }
    }

    /**
     * Read the file specified to add proper items to the word frequencies and word scores maps.
     */
    private static void processFile(String filename,
                                    BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores)
    {
        InputStream is = SentimentAnalysis.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Bad filename: " + filename);
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");
            for (int i = 1; i < words.length; i++)
            {
                int score = Integer.parseInt(words[0]); // casts the score into an integer
                // checks if word had appeared before
                if (wordFreqs.containsKey(words[i]) && wordTotalScores.containsKey(words[i]))
                {
                    wordFreqs.put(words[i], wordFreqs.get(words[i]) + 1);
                    wordTotalScores.put(words[i], wordTotalScores.get(words[i]) + score);
                } else // if the word had not appeared in any reviews yet
                {
                    wordFreqs.put(words[i], 1); // put the new word in wordFreqs
                    wordTotalScores.put(words[i], score); // put the new word in wordTotalScores
                }
            }
        }
        scan.close();
    }

    /**
     * Print a table of the words found in the movie reviews, along with their frequencies and total scores.
     * Call wordFreqs.inorderKeys() to get a list of the words, and then loop over that list.
     */
    private static void printFreqsAndScores(BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores)
    {
        List<String> wordList = wordFreqs.inorderKeys(); // create a new inorder list of wordFreqs
        // for every word in our new inorder list
        for (String i : wordList) {
            // print word, its frequency, and its total score
            System.out.println("Word: " + i + ", frequency: " + wordFreqs.get(i) + ", total score: " + wordTotalScores.get(i));
        }
    }

    /**
     * Read the stopwords.txt file and add each word to the stopwords set.  Also remove each word from the
     * word frequencies and word scores maps.
     */
    private static void removeStopWords(BSTMap<String, Integer> wordFreqs,
                                        BSTMap<String, Integer> wordTotalScores, Set<String> stopwords)
    {
        InputStream is = SentimentAnalysis.class.getResourceAsStream("stopwords.txt");
        if (is == null) {
            System.err.println("Bad filename: " + "stopwords.txt");
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            stopwords.add(word); // add each word to stopwords
            wordFreqs.remove(word); // remove word from wordFreqs
            wordTotalScores.remove(word); // remove word from wordTotalScores
        }
        scan.close();
    }
}
