package com.company;

import java.util.*;  // Provides TreeMap, Iterator, Scanner
import java.io.*;    // Provides FileReader, FileNotFoundException

public class Another
{
    public static void main(String[ ] args) throws IOException
    {
        TreeMap<String, TreeMap<Integer, Integer>> frequencyData = new TreeMap<>( );

        readFile(frequencyData);
        sortAndPrint(frequencyData);
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

    public static void sortAndPrint(TreeMap<String, TreeMap<Integer, Integer>> frequencyData) throws IOException
    {
        File path_output = new File(output);
        FileOutputStream oStream = new FileOutputStream(path_output.getAbsolutePath());

        for(String word : frequencyData.keySet())
        {
            Iterator iterator = frequencyData.get(word).keySet().iterator();
            Object docNum;
            int count;
            int max = 0;
            while(iterator.hasNext())
            {
                docNum = iterator.next();
                count = frequencyData.get(word).get(docNum);
                if(count > max)
                {
                    max = count;
                }
            }

            oStream.write(word.getBytes());
            for(int i=max; i > 0; i--)
            {
                iterator = frequencyData.get(word).keySet().iterator();
                while(iterator.hasNext())
                {
                    docNum = iterator.next();
                    count = frequencyData.get(word).get(docNum);
                    if(count == i)
                    {
                        oStream.write((" " + docNum + " " + count).getBytes());
                    }
                }
            }
            oStream.write("\n".getBytes());
        }
        oStream.close();
    }


    public static void readFile(TreeMap<String, TreeMap<Integer, Integer>> frequencyData) throws IOException
    {
        File path_input = new File(input);
        FileInputStream iStream = new FileInputStream(path_input.getAbsolutePath());
        BufferedReader iReader = new BufferedReader(new InputStreamReader(iStream));

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

            // 문자 카운트
            for(String s : reSent.split(" "))
            {
                TreeMap<Integer, Integer> temp = getCount(s, docNum, frequencyData);
                frequencyData.put(s, temp);
            }
        }

        frequencyData.remove("");
    }

    // Array of documents
    static String input = "src/com/company/input.small";
    static String output = "src/com/company/output.small";
}