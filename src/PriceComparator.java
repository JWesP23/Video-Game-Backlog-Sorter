import java.util.Comparator;

/**
 * PriceComparator.java - A Comparator class which compares VideoGame objects by their price.</h2>
 *
 * @author Wes Parker
 * @version Final Project
 */

public class PriceComparator implements Comparator<VideoGame>{

    /**
     * Compares VideoGame objects by Price
     * @param o1 A game to be compared by its price
     * @param o2 Another game to be compared by its price
     * @return -1 if o1 costs less than o2, 1 if o1 costs more than o2, and 0 if they cost the same amount
     */
    public int compare(VideoGame o1, VideoGame o2) {
        Double o1Price = o1.getPrice() ;
        return o1Price.compareTo(o2.getPrice()) ;
    }

    /**
     * Always returns false
     * @param obj an object
     * @return false
     */
    public boolean equals(String obj) {
        //Not sure what this method would actually be used for
        return false ;
    }
}
