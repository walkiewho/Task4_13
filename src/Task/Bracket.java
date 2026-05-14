package Task;

import java.util.ArrayList;

public class Bracket implements Comparable<Bracket> {
    public Integer val;
    public boolean opener;

    public Bracket(Integer val, boolean opener){
        this.val = val;
        this.opener = opener;
    }


    @Override
    public int compareTo(Bracket other) {
        if (this.val.equals(other.val)){
            return Boolean.compare(this.opener, other.opener);
        }
        return Integer.compare(this.val, other.val);
    }

    @Override
    public String toString() {
        return "(" + val + ", " + (opener ? "opener" : "closer") + ")";
    }

    public static ArrayList<Bracket> segmentListToSortedBracketList(ArrayList<Segment> segments){
        ArrayList<Bracket> result = new ArrayList<>();

        for (Segment segment : segments){
            result.add(new Bracket(segment.start, true));
            result.add(new Bracket(segment.end, false));
        }

        result.sort(Bracket::compareTo);
        return result;
    }
}
