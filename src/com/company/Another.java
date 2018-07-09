package com.company;

import java.util.*;  // Provides TreeMap, Iterator, Scanner
import java.io.*;    // Provides FileReader, FileNotFoundException

public class Another
{
    public static void main(String[ ] args) throws IOException
    {
        TreeMap<String, TreeMap<Integer, Integer>> frequencyData = new TreeMap<>( );

        readWordFile(frequencyData);
        // 요기에 정렬 함수 있으면 될듯
        printAllCounts(frequencyData);
    }


    public static TreeMap<Integer, Integer> getCount(String word, int docNum, TreeMap<String, TreeMap<Integer, Integer>> frequencyData)
    {
        if(frequencyData.containsKey(word))
        {
            if(frequencyData.get(word).containsKey(docNum))
            {
                frequencyData.get(word).put(docNum, frequencyData.get(word).get(docNum) + 1);
                return frequencyData.get(word);
            }
            else
            {
                frequencyData.get(word).put(docNum, 1);
                return frequencyData.get(word);
            }
        }
        else
        {
            frequencyData.put(word, new TreeMap<Integer, Integer>(){
                {
                    put(docNum, 1);
                }
            });
            return frequencyData.get(word);
        }
    }

    public static void printAllCounts(TreeMap<String, TreeMap<Integer, Integer>> frequencyData)
    {
        frequencyData.remove("");

        for(String word : frequencyData.keySet( ))
        {
            System.out.print(word);
            Iterator iterator = frequencyData.get(word).keySet().iterator();
            Object object;
            while(iterator.hasNext())
            {
                object = iterator.next();
                System.out.print(" " + object + " " + frequencyData.get(word).get(object));
            }
            System.out.println();
        }
        System.out.println(frequencyData.size());
    }


    public static void readWordFile(TreeMap<String, TreeMap<Integer, Integer>> frequencyData) throws IOException
    {
        File path_input = new File(input);
        FileInputStream iStream = new FileInputStream(path_input.getAbsolutePath());
        BufferedReader iReader = new BufferedReader(new InputStreamReader(iStream));

        Integer count;

        // Input Sentence
        String strLine;
        while((strLine = iReader.readLine()) != null)
        {
            strLine = strLine.toLowerCase();

            // Document Number, Sentence
            int docNum = Integer.parseInt(strLine.split(" ")[0]);
            String sentence = strLine.replace(docNum + " ", "");

            // 문자 처리
            String regex = "\\W";
            String reSent = sentence.replaceAll(regex, " ");

            // Get the current count of this word, add one, and then store the new count
            for(String s : reSent.split(" "))
            {
                TreeMap<Integer, Integer> temp = getCount(s, docNum, frequencyData);
                frequencyData.put(s, temp);
            }
        }
    }

    // Array of documents
    static String input = "src/com/company/data/input.small";
}