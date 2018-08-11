/**
 * Facebook Hacker Cup 2018 | Qualification Round
 * Link of the contest: https://www.facebook.com/hackercup/problem/1632703893518337/
 * Participant: Ruhullah Najafi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tourist {

    public static void main(String[] args) {
        Tourist program = new Tourist();
        program.load("res/tourist.txt");
        program.produceOutput("res/touristOutput_RuhullahNajafi.txt");
    }

    private ArrayList<Campus> campuses;
    private ArrayList<Pair<Integer, Long>> kv_list;

    private Tourist() {
        this.campuses = new ArrayList<>();
        this.kv_list = new ArrayList<>();
    }

    private void load(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            int t = Integer.parseInt(line);
            for(int i = 0; i < t; i++) {
                line = br.readLine();
                String[] parameters = line.split(" ");
                int n = Integer.parseInt(parameters[0]);
                Integer k = Integer.parseInt(parameters[1]);
                Long v = Long.parseLong(parameters[2]);
                this.kv_list.add(new Pair<>(k, v));
                ArrayList<String> campusAttractions = new ArrayList<>();
                for(int j = 0; j < n; j++) {
                    line = br.readLine();
                    campusAttractions.add(line);
                }
                this.campuses.add(new Campus(campusAttractions));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void produceOutput(String fileName) {
        try(PrintWriter printWriter = new PrintWriter(fileName, "UTF-8")) {
            int t = this.campuses.size();
            for(int i = 0; i < t; i++) {
                String line = "Case #" + (i+1) + ":";
                Campus campus = this.campuses.get(i);
                Pair<Integer, Long> kv = this.kv_list.get(i);
                ArrayList<String> campusAttractions = campus.attractionsToVisit(kv.p1, kv.p2);
                for(String attraction : campusAttractions) {
                    line = line.concat(" " + attraction);
                }
                printWriter.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* Helper Classes */

    private class Pair<T, S> {
        T p1;
        S p2;
        Pair(T p1, S p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    private class Campus {
        private int n;
        private ArrayList<String> attractions;

        Campus(ArrayList<String> attractions) {
            this.attractions = attractions;
            this.n = attractions.size();
        }

        ArrayList<String> attractionsToVisit(int k, long v) throws Exception {
            if(k < 1 || k > this.n) {
                throw new Exception("k has to be in the interval [1," + n + "]. k = " + k);
            } else if(k == this.n) {
                return attractions;
            }
            ArrayList<String> result = new ArrayList<>();
            int nextIndexToVisited = (int) ((k * ((v-1) % this.n)) % this.n);
            if(this.n - nextIndexToVisited < k) {
                for(int i = 0; i < k - (this.n - nextIndexToVisited); i++) {
                    result.add(this.attractions.get(i));
                }
            }
            for(int i = nextIndexToVisited; i < Math.min(nextIndexToVisited + k, this.n); i++) {
                result.add(this.attractions.get(i));
            }
            return result;
        }
    }
}
