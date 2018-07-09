package com.company;

import java.util.*;  // Provides TreeMap, Iterator, Scanner
import java.io.*;    // Provides FileReader, FileNotFoundException

public class Another
{
    public static void main(String[ ] args) throws IOException
    {
//        TreeMap<String, String> frequencyData = new TreeMap<>( );
//        readWordFile(frequencyData);
//        printAllCounts(frequencyData);

        TreeMap<String, TreeMap<Integer, Integer>> frequencyData = new TreeMap<>( );

        readWordFile(frequencyData);
        printAllCounts(frequencyData);
    }


    public static TreeMap<Integer, Integer> getCount(String word, int docNum, TreeMap<String, TreeMap<Integer, Integer>> frequencyData)
    {
//        frequencyData.get(word)
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


    public static void readWordFile3(TreeMap<String, String> frequencyData) throws IOException
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
                frequencyData.put(s, getCount3(s, docNum, frequencyData));
            }
        }
    }

    public static String getCount3(String word, int docNum, TreeMap<String, String> frequencyData)
    {
        if(frequencyData.containsKey(word))
        {
            int count = 1;
            Boolean change = false;
            String temp = "";
            for(String s : frequencyData.get(word).split(" "))
            {
                if(count%2 != 0)
                {
                    if(s.equals(docNum))
                    {
                        change = true;
                        temp += docNum;
                    }
                }
                count++;

                if(change == true && count%2 == 0)
                {
                    s = String.valueOf(Integer.parseInt(s) + 1);
                    temp += s;
                    change = false;
                }
            }
            return temp;
        }
        else
        {
            frequencyData.put(word, docNum + " 1");
            return frequencyData.get(word);
        }
    }

    public static void printAllCounts3(TreeMap<String, String> frequencyData)
    {
        frequencyData.remove("");

        for(String word : frequencyData.keySet( ))
        {
            System.out.println(word + " / " + frequencyData.get(word));
        }
        System.out.println(frequencyData.size());
    }

    // Array of documents
    static String input = "src/com/company/data/input.small";
}