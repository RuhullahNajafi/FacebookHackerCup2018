/**
 * Facebook Hacker Cup 2018 | Template Class
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ChallengeName {

    public static void main(String[] args) {
        ChallengeName program = new ChallengeName();
        program.loadCases("res/pie_progress_sample_input.txt");
        System.out.println(program);
        // program.produceOutput("res/touristOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Case> cases;

    public ChallengeName() {
        this.cases = new ArrayList<>();
    }

    public void loadCases(String fileName) {
        //noinspection Duplicates
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // String line = br.readLine();
            int t = Integer.parseInt(br.readLine());
            for(int i = 0; i < t; i++) {
                Case nextCase = new Case(br.readLine());
                int numLines = nextCase.attributes.get(0);
                for(int j = 0; j < numLines; j++) {
                    nextCase.lines.add(Line.fromString(br.readLine()));
                }
                this.cases.add(nextCase);
            }
        } catch (IOException e) {
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
            String[] numbers = str.split(" ");
            for(String num : numbers) {
                line.numbers.add(Integer.parseInt(num));
            }
            return line;
        }
    }
}
