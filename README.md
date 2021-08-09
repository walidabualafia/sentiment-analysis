# Sentiment Analysis

This program reads a text file, and uses binary search trees to logarithmically assess a score for each word, based on a text file with user reviews. 

In order to run the program, run SentimentAnalysis.java. Then, feed the program one of the sample text files (reviews-big.txt, reviews-small.txt). The program will automatically take into account stopwords.txt, and omit all stop words from the sample text file. Once the sample text file is read, the user is then free to keep entering words, and testing their frequency. The program will assess the frequency, and give each word a "sentiment score", which is essentially a mean of the review scores this word has appeared in.

Clarifications and General Rules:
 - Each text file contains different reviews. The first character is a score (0-4) that the user assigns the movie they are reviewing.
 - After the score, each file contains a text review for the movie it is reviewing.
 - After feeding a review text file to the program, the program will average the scores for each word that the user enters until the user stops the program.
