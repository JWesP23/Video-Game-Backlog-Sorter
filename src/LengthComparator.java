import java.util.Comparator;

/**
 * LengthComparator.java - A Comparator class which compares VideoGame objects by their length.</h2>
 *
 * @author Wes Parker
 * @version Final Project
 */

public class LengthComparator implements Comparator<VideoGame>{

    /**
     * Compares VideoGame objects by length
     * @param o1 A game to be compared by its length
     * @param o2 Another game to be compared by its length
     * @return -1 if o1 is shorter than o2, 1 if o1 is longer than o2, and 0 if they're equal
     */
    public int compare(VideoGame o1, VideoGame o2) {
        Double o1length = o1.getLength() ;
        return o1length.compareTo(o2.getLength()) ;
    }

    /**
     * Always returns false
     * @param obj and object
     * @return false
     */
    public boolean equals(String obj) {
        //Not sure what this method would actually be used for
        return false ;
    }
}