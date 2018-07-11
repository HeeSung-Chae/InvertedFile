package com.company;


import java.io.*;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.TreeMap;

public class InvertedFile
{
    static String input = "input.big";
    static String output = "output.big";

    public static void main(String[ ] args) throws IOException {
        int count = 0;
        for (String arg : args)
        {
            if(count == 0)
                input = arg;
            else
                output = arg;
            count++;
        }

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
            frequencyData.put(word, new TreeMap<>(){
                {
                    put(docNum, 1);
                }
            });
            return frequencyData.get(word);
        }
    }

    public static void sortAndPrint(TreeMap<String, TreeMap<Integer, Integer>> frequencyData) throws IOException
    {
        String path = InvertedFile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8").replace("InvertedFile.jar", "");

        File path_output = new File(decodedPath + output);
        FileOutputStream oStream = new FileOutputStream(path_output);

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
        String path = InvertedFile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8").replace("InvertedFile.jar", "");

        File path_input = new File(decodedPath + input);
        FileInputStream iStream = new FileInputStream(path_input);
        BufferedReader iReader = new BufferedReader(new InputStreamReader(iStream));


        String strLine;
        while((strLine = iReader.readLine()) != null)
        {
            strLine = strLine.toLowerCase();

            int docNum = Integer.parseInt(strLine.split(" ")[0]);
            String sentence = strLine.replace(docNum + " ", "");

            String regex = "\\W";
            String reSent = sentence.replaceAll(regex, " ");

            

            for(String s : reSent.split(" "))
            {
                TreeMap<Integer, Integer> temp = getCount(s, docNum, frequencyData);
                frequencyData.put(s, temp);
            }
        }

        frequencyData.remove("");
    }
}