/**
 * Facebook Hacker Cup 2018 | Round 2
 * Link of the contest: https://www.facebook.com/hackercup/problem/638251746380051/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CandyShop {

    public static void main(String[] args) {
        CandyShop program = new CandyShop();
        program.loadCases("res/jacks_candy_shop_sample_input.txt");
        System.out.println(program);
        program.calculateSolutions();
        program.saveSolutions("res/jacks_candy_shop_sample_output.txt");
        //program.saveSolutions("res/jacks_candy_shopOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Case> cases;
    private ArrayList<Integer> solutions;

    private CandyShop() {
        this.cases = new ArrayList<>();
        this.solutions = new ArrayList<>();
    }


    private void calculateSolutions() {
        int dummy = 1;
        for(Case nextCase : this.cases) {
            System.out.print("Case #" + (dummy) + ": ");
            int n = nextCase.attributes.get(0);
            int m = nextCase.attributes.get(1);
            ArrayList<ArrayList<Integer>> reachables = new ArrayList<>(n);
            for(int i = 0; i < m; i++) {
                reachables.add(new ArrayList<>());
            }
            for(int i = 0; i < n; i++) {
                int current = i;
                int parent = nextCase.p_i.get(current);
                reachables.get(current).add(i);
                while(parent >= 0) {
                    current = parent;
                    parent = nextCase.p_i.get(current);
                    reachables.get(current).add(i);
                }
            }
            HashMap<Integer, Integer> possiblities = new HashMap<>();
            for(int i = 0; i < m; i++) {
                possiblities.put(i, reachables.get(nextCase.c_i.get(i)).size());
            }
            Map.Entry<Integer, Integer> minEntry = null;
            /*for (Map.Entry<Foo, Bar> entry : map.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }*/
            int solution = 0;
            this.solutions.add(solution);
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
                int numLines = nextCase.attributes.get(0) - 1;
                for(int j = 0; j < numLines; j++) {
                    //nextCase.lines.add(Line.fromString(br.readLine()));
                    nextCase.p_i.add(Integer.parseInt(br.readLine()));
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
            str = str + "C_i:\n";
            for(Integer c : cases.get(j).c_i) {
                str = str + "" + c + "\n";
            }
        }
        return str;
    }



    /* ### Helper Classes ### */

    static class Case {
        ArrayList<Integer> attributes;
        ArrayList<Integer> c_i;
        ArrayList<Integer> p_i;
        ArrayList<Line> lines;

        public Case(String attributesString) {
            this.lines = new ArrayList<>();
            this.attributes = new ArrayList<>();
            String[] attributeStrings = attributesString.split(" ");
            for (String attribute : attributeStrings) {
                this.attributes.add(Integer.parseInt(attribute));
            }
            int n = this.attributes.get(0);
            int m = this.attributes.get(1);
            int a = this.attributes.get(2);
            int b = this.attributes.get(3);
            this.c_i = new ArrayList<>(m);
            for(int i = 0; i < m; i++) {
                this.c_i.add((a * i + b) % n);
            }
            this.p_i = new ArrayList<>(n);
            this.p_i.add(-1);
        }

        @Override
        public String toString() {
            String str = "Attributes -> " + this.attributes.get(0);
            for(int i = 1; i < this.attributes.size(); i++) {
                str = str + ", " + this.attributes.get(i);
            }
            /*str = str + "\n";
            for(Line line : this.lines) {
                str = str + line.toString();
            }*/
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
