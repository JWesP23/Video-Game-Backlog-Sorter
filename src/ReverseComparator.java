import java.util.Comparator;

/**
 * <h2>ReverseComparator.java - A Comparator class which reverses the output of any comparator passed to it.</h2>
 *
 * <p><b>Static variable:</b></p>
 * <ul style="margin-left: 25px;">
 *   <li>COMPARATOR (Comparator) - A comparator by which to compare with. The output of this comparator will be reversed and returned. </li>
 * </ul>
 *
 *
 * @author Wes Parker
 * @version Final Project
 */

public class ReverseComparator implements Comparator<VideoGame> {

    /**
     * A comparator by which to compare with; the output of this comparator will be reversed and returned
     */
    private final Comparator<VideoGame> COMPARATOR ;

    /**
     * Constructor sets underlying comparator object
     * @param comparator A comparator object who's output will be reversed
     */
    public ReverseComparator(Comparator<VideoGame> comparator){
        COMPARATOR = comparator ;
    }

    /**
     * Compares two objects by the qualities determined by the underlying comparator
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return -1 if the underlying comparator returns 1 or 1 if the underlying comparator returns -1. 0 will remain 0
     */
    public int compare(VideoGame o1, VideoGame o2) {
        return (-1) * COMPARATOR.compare(o1, o2) ;
    }
}
