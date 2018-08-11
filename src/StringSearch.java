/**
 * Facebook Hacker Cup 2018 | Qualification Round
 * Link of the contest: https://www.facebook.com/hackercup/problem/1153996538071503/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class StringSearch {

    private static String createFailureCase(String substring) {
        String failureCase = "Impossible";
        if(substring.length() >= 3 && substring.indexOf(substring.substring(0, 1), 1) > 0) {
            String begSeq = substring.substring(0, 1);
            int len = 2;
            String longerBegSeq = substring.substring(0, len++);
            while(substring.indexOf(longerBegSeq, 1) > 0) {
                begSeq = longerBegSeq;
                longerBegSeq = substring.substring(0, len++);
            }
            int indexOfRepetition = substring.indexOf(begSeq, 1);
            if(indexOfRepetition < substring.length() - begSeq.length()) {
                failureCase = substring.substring(0, indexOfRepetition).concat(substring);
            }
        }
        return failureCase;
    }

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("res/ethan_searches_for_a_string.txt"));
            PrintWriter printWriter = new PrintWriter("res/ethan_searches_for_a_stringOutput_RuhullahNajafi.txt", "UTF-8"))
        {
            int t = Integer.parseInt(bufferedReader.readLine());
            for(int i = 0; i < t; i++) {
                String substring = bufferedReader.readLine();
                String failureCase = StringSearch.createFailureCase(substring);
                printWriter.println("Case #" + (i + 1) + ": " + failureCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
