package com.company;

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        Thread thread = new Thread();

        File path_inputS = new File("src/com/company/data/input.small");
        File path_outputS = new File("src/com/company/data/output.small");
        File path_inputB = new File("src/com/company/data/input.big");

        FileInputStream isStream = new FileInputStream(path_inputS.getAbsolutePath());
        BufferedReader isBR = new BufferedReader(new InputStreamReader(isStream));

        FileInputStream osStream = new FileInputStream(path_outputS.getAbsolutePath());
        BufferedReader osBR = new BufferedReader(new InputStreamReader(osStream));

        FileInputStream ibSteram = new FileInputStream(path_inputB.getAbsolutePath());
        BufferedReader ibBR = new BufferedReader(new InputStreamReader(ibSteram));


        HashMap<String, List<Integer>> hashMap = new HashMap<>();
        ArrayList<String> sentList = new ArrayList<>();

        //문서내 색인어 추출
        String strLine;
        while((strLine = isBR.readLine()) != null)
        {
            strLine = strLine.toLowerCase();

            String docNum = strLine.split(" ")[0];
            String sent = strLine.replace(docNum + " ", "");

            String regex = "\\W";
            String reSent = sent.replaceAll(regex, " ");
            sentList.add(reSent);

            List<Integer> initList = new ArrayList<>();
            initList.add(-1);
            for(String s : reSent.split(" ")){
                if(hashMap.containsKey(s) != true){
                    hashMap.put(s, initList);
                }
            }
        }
        hashMap.remove("");
//        System.out.println(hashMap);
//        System.out.println(hashMap.size());


        String strLine2;
        while((strLine2 = ibBR.readLine()) != null)
        {
            strLine2 = strLine2.toLowerCase();

            int docNum = Integer.parseInt(strLine2.split(" ")[0]);
            String sent = strLine2.replace(String.valueOf(docNum) + " ", "");

            String regex = "\\W";
            String reSent = sent.replaceAll(regex, " ");

            for(String word : reSent.split(" ")){
                if(hashMap.containsKey(word)){
                    List<Integer> tempList = hashMap.get(word);
                    if(tempList.size() == 1){
                        tempList.add(docNum);
                        tempList.add(1);
                    }
                    else {
                        for (int i = 1; i < tempList.size(); i += 2) {
                            if (tempList.get(i) == docNum) {
                                tempList.set(i+1, tempList.get(i+1)+1);
                            }
                            else {
                                tempList.add(docNum);
                                tempList.add(1);
                                thread.run();
                            }
                        }
                    }
                    hashMap.put(word, tempList);
                }
                else{

                }
            }
            System.out.println(reSent);
        }
        System.out.println(hashMap);







        //hashMap 결과 파일과
        //입력 결과 파일과 비교 및 결과파일 output
        //키값 오름차순 정렬
        TreeMap<String, List<Integer>> treeMap = new TreeMap<>(hashMap);
        Iterator<String> keyIterator = treeMap.keySet().iterator();
//        while(keyIterator.hasNext()){
//            String key = keyIterator.next();
//            System.out.println(key);
//        }

        isBR.close();
    }
}
