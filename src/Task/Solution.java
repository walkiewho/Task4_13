package Task;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args){

    }


    public static ArrayList<Segment> solve(ArrayList<Segment> segments) {
        ArrayList<Segment> result = new ArrayList<>();
        ArrayList<Bracket> brackets = Bracket.segmentListToSortedBracketList(segments);

        int currentNests = 0;
        int maxNests = 0;

        Integer prev = null;

        for (Bracket bracket : brackets) {
            if (prev != null) {
                if (currentNests > maxNests) {
                    maxNests = currentNests;
                    result.clear();
                    result.add(new Segment(prev, bracket.val));
                }
                else if (currentNests == maxNests) {
                    result.add(new Segment(prev, bracket.val));
                }
            }

            if (bracket.opener) {
                currentNests++;
            } else {
                currentNests--;
            }

            prev = bracket.val;
        }

        return result;
    }
}

