import java.util.Comparator;

public class RelaseDateComparator implements Comparator<VideoGame>{

    /**
     * Compares Strings in reverse lexicographic order
     *
     * @param o1 A VideoGame to be compared by its release date
     * @param o2 Another VideoGame to be compared by its release date
     * @return -1 if o1 came out before o2, 1 if o1 came out after o2, and 0 if they came out on the same day
     */
    public int compare(VideoGame o1, VideoGame o2) {
        Integer o1releaseDate = o1.getReleaseDate() ;
        Integer o2releaseDate = o2.getReleaseDate() ;

        int o1Year = (o1releaseDate % 10000) ;
        int o2Year = (o2releaseDate % 10000) ;

        if (o1Year != o2Year){
            Integer o1YearAsInteger = o1Year ;
            return o1YearAsInteger.compareTo(o2Year) ;
        }

        int o1Month = (o1releaseDate / 1000000 % 100) ;
        int o2Month = (o2releaseDate / 1000000 % 100) ;

        if (o1Month != o2Month){
            Integer o1MonthAsInteger = o1Month ;
            return o1MonthAsInteger.compareTo(o2Month) ;
        }

        Integer o1Day = (o1releaseDate / 10000 % 100) ;
        Integer o2Day = (o2releaseDate / 10000 % 100) ;

        return o1Day.compareTo(o2Day) ;
    }

    /**
     * Always returns false
     *
     * @param obj and object
     * @return false
     */
    public boolean equals(String obj) {
        //Not sure what this method would actually be used for
        return false ;
    }
}
