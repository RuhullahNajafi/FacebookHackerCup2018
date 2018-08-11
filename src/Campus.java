/*import java.util.ArrayList;

public class Campus {

    private int n;
    private ArrayList<String> attractions;

    public Campus(ArrayList<String> attractions) {
        this.attractions = attractions;
        this.n = attractions.size();
    }

    public ArrayList<String> attractionsToVisit(int k, long v) throws Exception {
        if(k < 1 || k > this.n) {
            throw new Exception("k has to be between in the interval [1," + n + "]. k = " + k);
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

}*/
