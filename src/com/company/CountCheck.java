package com.company;

// output.big으로 input.big 문장에 실제로 있는지 확인

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountCheck {
    static String input = "src/com/company/input.small";
    static String output = "src/com/company/output.small";

    public static void main(String[] args) throws IOException {
        File path_input = new File(input);
        FileInputStream iStream = new FileInputStream(path_input.getAbsolutePath());
        BufferedReader iReader = new BufferedReader(new InputStreamReader(iStream));

        File path_output = new File(output);
        FileInputStream oStream = new FileInputStream(path_output.getAbsolutePath());
        BufferedReader oReader = new BufferedReader(new InputStreamReader(oStream));

        String inputLine;
        String outputLine;
        while((outputLine = oReader.readLine()) != null)
        {
            String word = outputLine.split(" ")[0];

            // " "의 개수 확인
            int spaceCount = 0;
            Pattern pattern = Pattern.compile(" ");
            Matcher matcher = pattern.matcher(outputLine);
            while (matcher.find())
            {
                spaceCount++;
            }

            // output 파일에서 단어와 문서 번호, 등장 횟수를 기록
            for(int i=1; i<=spaceCount/2; i+=2)
            {
                String docNum = outputLine.split(" ")[i];
                String count = outputLine.split(" ")[i+1];

                // input 파일에서 해당 문서 번호에 word의 등장 횟수가 맞는지 확인
                while((inputLine = iReader.readLine()) != null)
                {
                    String inputDocNum = inputLine.split(" ")[0];
                    String inputSentence = inputLine.replace(inputDocNum + " ", "");

                    if(docNum == inputDocNum)
                    {
                        int temp = 0;
                        for(String s : inputSentence.split(" "))
                        {
                            if(word.equals(s))
                            {
                                temp++;
                            }
                        }

                        if(count.equals(String.valueOf(temp)))
                        {
//                            System.out.println(word + " " + docNum + " " + count);
                        }
                        else
                        {
                            System.out.println(word + " " + docNum + " " + count + " / " + count + " " + temp);
                        }
                        break;
                    }
                }
            }

        }
    }
}
