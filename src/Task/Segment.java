package Task;

import java.util.Objects;

public class Segment {
    public Integer start;
    public Integer end;

    public Segment(Integer start, Integer end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }

    @Override
    public String toString() {
        return "(" + start + ", " + end + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;

        Segment segment = (Segment) o;

        return Objects.equals(start, segment.start)
                && Objects.equals(end, segment.end);
    }
}
