import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Facebook Hacker Cup 2018 | Round 1
 * Link of the contest: https://www.facebook.com/hackercup/problem/232395994158286/
 * Participant: Ruhullah Najafi
 */

import java.util.*;

public class TreeTraverse {

    public static void main(String[] args) {
        TreeTraverse program = new TreeTraverse();
        program.loadCases("res/ethan_traverses_a_tree_sample_input.txt");
        //program.loadCases("res/ethan_traverses_a_tree.txt");
        program.calculateSolutions();
        System.out.println(program);
        program.saveSolutions("res/ethan_traverses_a_tree_sample_output.txt");
        //program.saveSolutions("res/ethan_traverses_a_treeOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Case> cases;
    private ArrayList<String> solutions;

    private TreeTraverse() {
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
                int numLines = nextCase.attributes.get(0);
                for(int j = 0; j < numLines; j++) {
                    nextCase.lines.add(Line.fromString(br.readLine()));
                }
                nextCase.calculatePreOrder(1);
                nextCase.calculatePostOrder(1);
                this.cases.add(nextCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateSolutions() {
        for(Case nextCase : this.cases) {
            ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
            int currentGroupNumber = 0;

            int n = nextCase.attributes.get(0);
            int k = nextCase.attributes.get(1);

            int[] solution = new int[n];
            Queue<Integer> group = new LinkedList<>();
            //Set<Integer> group = new HashSet<>();
            for(int node = 1; node <= n; node++) {
                if(solution[node - 1] == 0) {
                    if(currentGroupNumber < k) {
                        currentGroupNumber++;
                    }
                    group.add(node);
                    while(!group.isEmpty()) {
                        int nextNode = group.poll();
                        solution[nextNode - 1] = currentGroupNumber;

                        int indexInPreOrder = nextCase.preOrder.indexOf(nextNode);
                        int indexInPostOrder = nextCase.postOrder.indexOf(nextNode);
                        int preOrderCandidate = nextCase.preOrder.get(indexInPostOrder);
                        int postOrderCandidate = nextCase.postOrder.get(indexInPreOrder);
                        if(solution[preOrderCandidate - 1] == 0 && !group.contains(preOrderCandidate)) {
                            group.add(preOrderCandidate);
                        }
                        if(solution[postOrderCandidate - 1] == 0 && !group.contains(postOrderCandidate)) {
                            group.add(postOrderCandidate);
                        }
                    }
                }
            }
            String outPut;
            if(currentGroupNumber < k) {
                outPut = "Impossible";
            } else {
                outPut = "" + solution[0];
                for(int i = 1; i < n; i++) {
                    outPut = outPut + " " + solution[i];
                }
            }
            this.solutions.add(outPut);
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
            str = str + "Solution: " + solutions.get(j) + "\n\n";
        }
        return str;
    }

    /* ### Helper Classes ### */

    static class Case {
        ArrayList<Integer> attributes;
        ArrayList<Line> lines;
        ArrayList<Integer> preOrder;
        ArrayList<Integer> postOrder;

        public Case(String attributesString) {
            this.lines = new ArrayList<>();
            this.attributes = new ArrayList<>();
            String[] attributeStrings = attributesString.split(" ");
            for (String attribute : attributeStrings) {
                this.attributes.add(Integer.parseInt(attribute));
            }
            preOrder = new ArrayList<>();
            postOrder = new ArrayList<>();
        }

        void calculatePreOrder(int node) {
            if(node == 0) {
                return;
            }
            this.preOrder.add(node);
            this.calculatePreOrder(this.lines.get(node - 1).numbers.get(0));
            this.calculatePreOrder(this.lines.get(node - 1).numbers.get(1));
        }

        void calculatePostOrder(int node) {
            if(node == 0) {
                return;
            }
            this.calculatePostOrder(this.lines.get(node - 1).numbers.get(0));
            this.calculatePostOrder(this.lines.get(node - 1).numbers.get(1));
            this.postOrder.add(node);
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
            str = str + "PreOrder:";
            for(Integer i : this.preOrder) {
                str = str + " " + i.toString();
            }
            str = str + "\nPostOrder:";
            for(Integer i : this.postOrder) {
                str = str + " " + i.toString();
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
