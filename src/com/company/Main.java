package com.company;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File path_inputS = new File("src/com/company/data/input.small");
        File path_outputS = new File("src/com/company/data/output.small");
        File path_inputB = new File("src/com/company/data/input.big");

        FileInputStream isStream = new FileInputStream(path_inputS.getAbsolutePath());
        BufferedReader isBR = new BufferedReader(new InputStreamReader(isStream));

        FileInputStream osStream = new FileInputStream(path_outputS.getAbsolutePath());
        BufferedReader osBR = new BufferedReader(new InputStreamReader(osStream));

        FileInputStream ibSteram = new FileInputStream(path_inputB.getAbsolutePath());
        BufferedReader ibBR = new BufferedReader(new InputStreamReader(ibSteram));


        String strLine;

        while((strLine = isBR.readLine()) != null)
        {
            System.out.println(strLine);
        }



        isBR.close();


    }
}
