import java.util.Comparator;

/**
 * ExcitementComparator.java - A Comparator class which compares VideoGame objects by their excitement level.</h2>
 *
 * @author Wes Parker
 * @version Final Project
 */

public class ExcitementComparator implements Comparator<VideoGame>{

    /**
     * Compares VideoGame objects by their excitement level
     * @param o1 A game to be compared by its excitement level
     * @param o2 Another game to be compared by its excitement level
     * @return -1 if o1 is less exciting than o2, 1 if o1 is more exciting than o2, and 0 if they're equally exciting
     */
    public int compare(VideoGame o1, VideoGame o2) {
        Double o1ExcitementLvl = o1.getLvlOfExcitement() ;
        return o1ExcitementLvl.compareTo(o2.getLvlOfExcitement()) ;
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