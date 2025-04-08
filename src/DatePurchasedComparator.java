import java.util.Comparator;

public class DatePurchasedComparator implements Comparator<VideoGame>{

    /**
     * Compares Strings in reverse lexicographic order
     *
     * @param o1 A VideoGame to be compared by its purchase date
     * @param o2 Another VideoGame to be compared by its purchase date
     * @return -1 if o1 was purchased before o2, 1 if o1 was purchased after o2, and 0 if they were purchased on the same day
     */
    public int compare(VideoGame o1, VideoGame o2) {
        Integer o1purchaseDate = o1.getDatePurchased() ;
        Integer o2purchaseDate = o2.getDatePurchased() ;

        int o1Year = (o1purchaseDate % 10000) ;
        int o2Year = (o2purchaseDate % 10000) ;

        if (o1Year != o2Year){
            Integer o1YearAsInteger = o1Year ;
            return o1YearAsInteger.compareTo(o2Year) ;
        }

        int o1Month = (o1purchaseDate / 1000000 % 100) ;
        int o2Month = (o2purchaseDate / 1000000 % 100) ;

        if (o1Month != o2Month){
            Integer o1MonthAsInteger = o1Month ;
            return o1MonthAsInteger.compareTo(o2Month) ;
        }

        Integer o1Day = (o1purchaseDate / 10000 % 100) ;
        Integer o2Day = (o2purchaseDate / 10000 % 100) ;

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
