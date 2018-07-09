package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Run {
    public static void main(String[] args) throws IOException{
        Thread thread = new Thread();

        File path_inputS = new File("src/com/company/data/input.small");
        File path_outputS = new File("src/com/company/data/output.small");

        FileInputStream isStream = new FileInputStream(path_inputS.getAbsolutePath());
        BufferedReader isBR = new BufferedReader(new InputStreamReader(isStream));

        FileInputStream osStream = new FileInputStream(path_outputS.getAbsolutePath());
        BufferedReader osBR = new BufferedReader(new InputStreamReader(osStream));

        TreeMap<String, List<Integer>> treeMap = new TreeMap<>();
        ArrayList<String> sentList = new ArrayList<>();

        String strLine;
        while((strLine = isBR.readLine()) != null){
            strLine = strLine.toLowerCase();

            String docNum = strLine.split(" ")[0];
            String sent = strLine.replace(docNum + " ", "");

            String regex = "\\W";
            String reSent = sent.replaceAll(regex, " ");
            sentList.add(reSent);

            List<Integer> initList = new ArrayList<>();
//            initList.add(-1);
            for(String s : reSent.split(" ")){
                if(treeMap.containsKey(s) != true)
                    treeMap.put(s, initList);
            }
        }
        treeMap.remove("");
        System.out.println(treeMap);
    }
}
