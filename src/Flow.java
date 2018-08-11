/**
 * Facebook Hacker Cup 2018 | Round 1
 * Link of the contest: https://www.facebook.com/hackercup/problem/180494849326631/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Flow {

    public static void main(String[] args) {
        Flow program = new Flow();
        program.loadCases("res/let_it_flow_sample_input.txt");
        System.out.println(program);
        program.calculateSolutions();
        program.saveSolutions("res/let_it_flow_sample_output.txt");
        //program.saveSolutions("res/let_it_flowOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Case> cases;
    private ArrayList<Integer> solutions;

    private Flow() {
        this.cases = new ArrayList<>();
        this.solutions = new ArrayList<>();
    }

    private void calculateSolutions() {
        int dummy = 1;
        for(Case nextCase : this.cases) {
            System.out.print("Case #" + (dummy++) + ": ");
            int solution = 0;
            int numColumns = nextCase.attributes.get(0);
            if(((numColumns % 2) == 0) && nextCase.topColFree(0) && nextCase.bottomColFree(numColumns - 1)
                    && nextCase.middleCompletelyFree()) {
                solution = 1;
                for(int col = 1; col < numColumns - 2; col += 2) {
                    if(nextCase.topPathFree(col) && nextCase.bottomPathFree(col)) {
                        solution *= 2;
                        solution = solution % 1000000007;
                    }
                    if(!nextCase.topPathFree(col) && !nextCase.bottomPathFree(col)) {
                        solution = 0;
                        System.out.print("Failed at column " + col);
                        break;
                    }
                }
            } else {
                System.out.print("notEvenTried ;)");
            }
            this.solutions.add(solution);
            System.out.println(" -> " + solution);
        }
    }

    private void loadCases(String fileName) {
        //noinspection Duplicates
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int t = Integer.parseInt(br.readLine());
            for(int i = 0; i < t; i++) {
                Case nextCase = new Case(br.readLine());
                int numLines = 3;
                for(int j = 0; j < numLines; j++) {
                    nextCase.lines.add(Line.fromString(br.readLine()));
                }
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
                String line = "Case #" + (i+1) + ": " + this.solutions.get(i);
                pw.println(line);
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
            return str;
        }

        public boolean middleCompletelyFree() {
            return (!lines.get(1).numbers.contains(1));
        }

        public boolean topColFree(int col) {
            return lines.get(0).numbers.get(col) == 0;
        }

        public boolean bottomColFree(int col) {
            return lines.get(2).numbers.get(col) == 0;
        }

        public boolean topPathFree(int col) {
            return (this.topColFree(col) && this.topColFree(col + 1));
        }

        public boolean bottomPathFree(int col) {
            return (this.bottomColFree(col) && this.bottomColFree(col + 1));
        }

        public void loadLinesFromStringArray(String[] stringArray) {
            for (String line : stringArray) {
                this.lines.add(Line.fromString(line));
            }
        }
    }

    static class Line {
        ArrayList<Integer> numbers;

        public Line() {
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

        public static Line fromString(String str) {
            Line line = new Line();
            for(char character : str.toCharArray()) {
                int value = character == '.' ? 0 : 1;
                line.numbers.add(value);
            }
            return line;
        }
    }

}
