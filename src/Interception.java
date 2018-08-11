/**
 * Facebook Hacker Cup 2018 | Qualification Round
 * Link of the contest: https://www.facebook.com/hackercup/problem/175329729852444/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Interception {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("res/interception.txt"));
            PrintWriter printWriter = new PrintWriter("res/interceptionOutput_RuhullahNajafi.txt", "UTF-8"))
        {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for(int i = 0; i < t; i++) {
                line = bufferedReader.readLine();
                int polyDeg = Integer.parseInt(line);
                for(int j = 0; j < polyDeg + 1; j++) {
                    bufferedReader.readLine();
                }
                int numInterceptions = polyDeg % 2;
                printWriter.println("Case #" + (i + 1) + ": " + numInterceptions);
                if(numInterceptions != 0) {
                    printWriter.println("0.0");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
