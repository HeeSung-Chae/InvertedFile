package com.company;

import java.util.*;  // Provides TreeMap, Iterator, Scanner
import java.io.*;    // Provides FileReader, FileNotFoundException

public class Another
{
    public static void main(String[ ] args) throws IOException
    {
        TreeMap<String, Integer> frequencyData = new TreeMap<String, Integer>( );

        readWordFile(frequencyData);
        printAllCounts(frequencyData);
    }


    public static int getCount(String word, TreeMap<String, Integer> frequencyData)
    {
        if (frequencyData.containsKey(word))
        {  // The word has occurred before, so get its count from the map
            return frequencyData.get(word); // Auto-unboxed
        }
        else
        {  // No occurrences of this word
            return 0;
        }
    }


    public static void printAllCounts(TreeMap<String, Integer> frequencyData)
    {
        frequencyData.remove("");

        for(String word : frequencyData.keySet( ))
        {
            System.out.println(word + " " + frequencyData.get(word));
        }
        System.out.println(frequencyData.size());
    }


    public static void readWordFile(TreeMap<String, Integer> frequencyData) throws IOException
    {
        File path_input = new File(input);
        FileInputStream iStream = new FileInputStream(path_input.getAbsolutePath());
        BufferedReader iReader = new BufferedReader(new InputStreamReader(iStream));

        Integer count;

        String strLine;     // Input Sentence
        while((strLine = iReader.readLine()) != null)
        {
            strLine = strLine.toLowerCase();

            // Document Number, Sentence
            String docNum = strLine.split(" ")[0];
            String sentence = strLine.replace(docNum + " ", "");

            // 문자 처리
            String regex = "\\W";
            String reSent = sentence.replaceAll(regex, " ");

            // Get the current count of this word, add one, and then store the new count
            for(String s : reSent.split(" "))
            {
                count = getCount(s, frequencyData) + 1;
                frequencyData.put(s, count);
            }
        }
    }


    // Array of documents
    static String input = "src/com/company/data/input.small";
}