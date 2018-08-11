/**
 * Facebook Hacker Cup 2018 | Round 1
 * Link of the contest: https://www.facebook.com/hackercup/problem/1892930427431211/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parkour {
    public static void main(String[] args) {
        Parkour program = new Parkour();
        program.loadCases("res/platform_parkour_sample_input.txt");
        //program.loadCases("res/platform_parkour.txt");
        program.calculateSolutions();
        System.out.println(program);
        //program.saveSolutions("res/platform_parkour_sample_output.txt");
        //program.saveSolutions("res/platform_parkourOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Case> cases;
    private ArrayList<String> solutions;

    private Parkour() {
        this.cases = new ArrayList<>();
        this.solutions = new ArrayList<>();
    }

    private void loadCases(String fileName) {
        //noinspection Duplicates
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // String line = br.readLine();
            int t = Integer.parseInt(br.readLine());
            for(int i = 0; i < t; i++) {
                Case nextCase = new Case(br.readLine());
                nextCase.loadHeights(br.readLine());
                int numLines = nextCase.attributes.get(1);
                for(int j = 0; j < numLines; j++) {
                    //nextCase.lines.add(Line.fromString(br.readLine()));
                    nextCase.addLine(Line.fromString(br.readLine()));
                }
                this.cases.add(nextCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateSolutions() {

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
        String str = "\n\n" + getClass().getName() + " instance containing the following " + this.cases.size() + " cases:\n";
        for(int j = 0; j < this.cases.size(); j++) {
            str = str + "\nCase " + (j+1) + ":\n" + cases.get(j).toString();
        }
        return str;
    }


    /* ### Helper Classes ### */

    static class Case {
        ArrayList<Integer> attributes;
        ArrayList<Integer> heights;
        ArrayList<Line> lines;
        Interval[] validGaps;

        public Case(String attributesString) {
            this.lines = new ArrayList<>();
            this.attributes = new ArrayList<>();
            String[] attributeStrings = attributesString.split(" ");
            for (String attribute : attributeStrings) {
                this.attributes.add(Integer.parseInt(attribute));
            }
            this.heights = new ArrayList<>(this.attributes.get(0));
            this.validGaps = new Interval[this.attributes.get(0) - 1];
            for(int i = 0; i < this.attributes.get(0) - 1; i++) {
                this.validGaps[i] = new Interval();
            }
        }

        @Override
        public String toString() {
            String str = "Attributes -> " + this.attributes.get(0);
            for(int i = 1; i < this.attributes.size(); i++) {
                str = str + ", " + this.attributes.get(i);
            }
            str = str + "\n";

            str = str + "Heights -> " + this.heights.get(0);
            for(int i = 1; i < Math.min(this.attributes.get(0), 10); i++) {
                str = str + ", " + this.heights.get(i);
            }
            if(this.attributes.get(0) > 10) {
                str = str + ", ...";
            }
            str = str + "\n";

            str = str + "Valid Intervals -> " + this.validGaps[0];
            for(int i = 1; i < Math.min(this.attributes.get(0) - 1, 9); i++) {
                str = str + ", " + this.validGaps[i];
            }
            if(this.attributes.get(0) > 10) {
                str = str + ", ...";
            }
            str = str + "\n";

            for(Line line : this.lines) {
                str = str + line.toString();
            }
            return str;
        }



        void addLine(Line line) {
            this.lines.add(line);
            if(line.a < line.b) {
                for(int i = line.a; i < line.b; i++) {
                    if(this.validGaps[i - 1].low < -line.d)
                        this.validGaps[i - 1].low = -line.d;
                    if(this.validGaps[i - 1].high > line.u)
                        this.validGaps[i - 1].high = line.u;
                }
            } else {
                for(int i = line.b; i < line.a; i++) {
                    if(this.validGaps[i - 1].low < -line.u)
                        this.validGaps[i - 1].low = -line.u;
                    if(this.validGaps[i - 1].high > line.d)
                        this.validGaps[i - 1].high = line.d;
                }
            }
        }

        void loadHeights(String attributesString) {
            String[] attributeStrings = attributesString.split(" ");
            this.heights.add(Integer.parseInt(attributeStrings[0]));
            this.heights.add(Integer.parseInt(attributeStrings[1]));
            long w = Long.parseLong(attributeStrings[2]);
            long x = Long.parseLong(attributeStrings[3]);
            long y = Long.parseLong(attributeStrings[4]);
            long z = Long.parseLong(attributeStrings[5]);
            for(int i = 2; i < this.attributes.get(0); i++) {
                long temp = (((w % z) * (this.heights.get(i - 2) % z))
                        + ((x % z) * (this.heights.get(i - 1) % z))
                        + (y % z) ) % z;
                this.heights.add((int) temp);
            }
        }
    }

    static class Line {
        int a;
        int b;
        int u;
        int d;

        Line(int a, int b, int u, int d) {
            this.a = a;
            this.b = b;
            this.u = u;
            this.d = d;
        }

        @Override
        public String toString() {
            String str = "a, b, u, d = " + this.a + ", " + this.b + ", " + this.u + ", " + this.d + "\n";
            return str;
        }

        static Line fromString(String str) {
            String[] numbers = str.split(" ");
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);
            int u = Integer.parseInt(numbers[2]);
            int d = Integer.parseInt(numbers[3]);
            return new Line(a, b, u, d);
        }
    }

    static class Interval {
        int low;
        int high;
        Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
        Interval() {
            this(-1000000, 1000000);
        }

        @Override
        public String toString() {
            return "(" + this.low + "," + this.high + ")";
        }
    }
}
