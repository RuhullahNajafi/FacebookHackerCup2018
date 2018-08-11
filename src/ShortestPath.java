/**
 * Facebook Hacker Cup 2018 | Round 2
 * Link of the contest: https://www.facebook.com/hackercup/problem/988017871357549/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ShortestPath {

    public static void main(String[] args) {
        ShortestPath program = new ShortestPath();
        program.loadCases("res/ethan_finds_the_shortest_path.txt");
        System.out.println(program);
        program.calculateSolutions();
        //program.saveSolutions("res/ethan_finds_the_shortest_path_sample_output.txt");
        program.saveSolutions("res/ethan_finds_the_shortest_pathOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Case> cases;
    private ArrayList<String> solutions;

    private ShortestPath() {
        this.cases = new ArrayList<>();
        this.solutions = new ArrayList<>();
    }


    private void calculateSolutions() {
        int dummy = 1;
        for(Case nextCase : this.cases) {
            System.out.print("Case #" + (dummy) + ": ");
            int n = nextCase.attributes.get(0);
            int k = nextCase.attributes.get(1);
            int solution = 0;
            if(n >= 3 && k >= 3) {
                solution = ((k - 1) * k) / 2 - k;
                if (k > n) {
                    solution -= (k - n) * (k - n + 1) / 2;
                }
            }
            String solutionString = "Case #" + (dummy) + ": " + solution + "\n";
            if(n >= 3 && k >= 3) {
                solutionString = solutionString + "" + Math.min(k, n) + "\n";
            } else {
                solutionString = solutionString + "1\n";
            }
            solutionString = solutionString + "1 " + n + " " + k + "\n";
            if(n >= 3 && k >= 3) {

                for(int i = 1; i < Math.min(k, n) - 1; i++) {
                    solutionString = solutionString + i + " " + (i + 1) + " " + (k - i) + "\n";
                }
                solutionString = solutionString + (Math.min(k, n) - 1) + " " + (n) + " " + (k - Math.min(k, n) + 1) + "\n";
            }
            this.solutions.add(solutionString);
            System.out.println(" -> " + solution);
            dummy++;
        }
    }

    private void loadCases(String fileName) {
        //noinspection Duplicates
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int t = Integer.parseInt(br.readLine());
            for(int i = 0; i < t; i++) {
                Case nextCase = new Case(br.readLine());
                /*int numLines = 3;
                for(int j = 0; j < numLines; j++) {
                    nextCase.lines.add(Line.fromString(br.readLine()));
                }*/
                this.cases.add(nextCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSolutions(String fileName) {
        //noinspection Duplicates
        try(PrintWriter pw = new PrintWriter(fileName, "UTF-8")) {
            int t = this.cases.size();
            for(int i = 0; i < t; i++) {
                String line = /*"Case #" + (i+1) + ": " +*/ this.solutions.get(i);
                pw.print(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String str = getClass().getName() + " instance containing the following " + this.cases.size() + " cases:\n";
        for(int j = 0; j < this.cases.size(); j++) {
            str = str + "Case " + (j+1) + ": " + cases.get(j).toString();
        }
        return str;
    }



    /* ### Helper Classes ### */

    static class Case {
        ArrayList<Integer> attributes;
        ArrayList<Line> lines;

        public Case(String attributesString) {
            this.lines = new ArrayList<>();
            this.attributes = new ArrayList<>();
            String[] attributeStrings = attributesString.split(" ");
            for (String attribute : attributeStrings) {
                this.attributes.add(Integer.parseInt(attribute));
            }
        }

        @Override
        public String toString() {
            String str = "Attributes -> " + this.attributes.get(0);
            for(int i = 1; i < this.attributes.size(); i++) {
                str = str + ", " + this.attributes.get(i);
            }
            str = str + "\n";
            for(Line line : this.lines) {
                str = str + line.toString();
            }
            str = str + "\n";
            return str;
        }
    }

    static class Line {
        ArrayList<Integer> numbers;

        Line() {
            numbers = new ArrayList<>();
        }

        @Override
        public String toString() {
            String str = "" + this.numbers.get(0);
            for(int i = 1; i < this.numbers.size(); i++) {
                str = str + ", " + this.numbers.get(i);
            }
            str = str + "\n";
            return str;
        }

        static Line fromString(String str) {
            Line line = new Line();
            String[] numbers = str.split(" ");
            for(String num : numbers) {
                line.numbers.add(Integer.parseInt(num));
            }
            return line;
        }
    }
}
