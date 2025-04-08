import java.util.ArrayList;
import java.util.Comparator;

/**
 * <h2>Quicksort.java - Implements the quicksort algorithm.</h2>
 *
 * <p><b>Algorithm:</b></p>
 * <ol style="margin-left: 25px;">
 *   <li>find the middle element index, then choose the index of the middle element between the first, middle, and last elements to be the pivot</li>
 *   <li>swap the elements at the first index and the pivot index (the first element is now the pivot). </li>
 *   <li>set "up" to 0 and "down" to the size of the array - 1</li>
 *   <li>keep adding 1 to "up" until the element at "up" greater than or equal to the pivot</li>
 *   <li>keep subtracting 1 from "down" until the element at "down" is less than the pivot</li>
 * </ol>
 *
 * <p><b>Instance variable:</b></p>
 * <ul style="margin-left: 25px;">
 *  <li>comparator (Comparator) - A comparator to sort by.</li>
 * </ul>
 *
 * @author Wes Parker
 * @version Final Project
 */
public class QuickSort {

    //A comparator to sort by
    Comparator<VideoGame> comparator = new LexicographicComparator() ;

    /**
     * No-arg constructor sets comparator to a Lexicographic Comparator
     */
    public QuickSort(){}

    /**
     * Full constructor allows the user to set the comparator
     * @param comparator the comparator to be used for this object
     */
    public QuickSort(Comparator<VideoGame> comparator){
        this.comparator = comparator ;
    }

    /**
     * Sorts the ArrayList passed using the quicksort algorithm
     * @param table a table containing VideoGame objects to be sorted
     */
    public void sort(ArrayList<VideoGame> table){
        quicksort(table, 0, table.size() - 1) ;
    }

    /**
     * Sorts a portion of the table passed in the sort() method.
     * This method fulfills the recursion requirement.
     * @param table a table containing VideoGame objects to be sorted
     * @param first the first index of the subsection of the table to be sorted
     * @param last the last index of the subsection of the table to be sorted
     */
    public void quicksort(ArrayList<VideoGame> table, int first, int last){
        if (first >= last){
            return ;
        }

        // Find the pivot (use the first element in the array), then pivot the table
        // so that all elements "less than" the pivot are on the left, and all
        // elements "greater than" the pivot point are on its right in the array
        int pivotIndex = sortAroundPivot(table, first, last) ;

        //Recursively sort everything to the left of the pivot
        quicksort(table, first, pivotIndex - 1) ;
        //Recursively sort everything to the right of the pivot
        quicksort(table, pivotIndex + 1, last) ;
    }

    /**
     * Moves all elements less than a pivot point to the left of that pivot and all items greater than the pivot to the right of it
     * @param table a table containing VideoGame objects to be lightly sorted
     * @param first the first index of the subsection of the table to be sorted
     * @param last the last index of the subsection of the table to be sorted
     * @return the index of the pivot in its correct position
     */
    public int sortAroundPivot(ArrayList<VideoGame> table, int first, int last){

        //get the index of the middle element
        int middle = first + ((last - first) / 2) ;
        int pivotIndex = 0 ;

        //Determine the median between the first, middle, and last elements
        VideoGame front = table.get(first) ;
        VideoGame mid = table.get(middle) ;
        VideoGame end = table.get(last) ;
        VideoGame pivot ;

        if (comparator.compare(front, mid) < 1 && comparator.compare(mid, end) < 1){
            pivot = mid ;
            pivotIndex = middle ;
        } else if (comparator.compare(mid, front) < 1 && comparator.compare(front, end) < 1) {
            pivot = front ;
            pivotIndex = first ;
        } else {
            pivot = end ;
            pivotIndex = last ;
        }

        //Swap the pivot to the front
        swap(table, first, pivotIndex) ;
        int up = first ;    //Items greater than the pivot value
        int down = last ;   //Items less than the pivot value

        do{
            //Move up the list toward the pivot until an element greater than the pivot is found or the end of the list is reached
            while (up < last && comparator.compare(table.get(up), pivot) < 1){
                up++ ;
            }

            //Move down the list toward the pivot until an element less than the pivot is found or the beginning of the list is reached
            while (down > first && comparator.compare(table.get(down), pivot) > -1){
                down-- ;
            }

            //If up and down have not passed each other, swap the elements at their indices
            if (up < down){
                swap(table, up, down) ;
            }

            //End loop when up and down pass each other
        } while (down > up) ;

        //swap the pivot into place by putting it into the position that down ended in
        swap(table, down, first) ;

        return down ;
    }

    /**
     * Swap the items in table[i] and table[j].
     * @param table The array that contains the items
     * @param i The index of one item
     * @param j The index of the other item
     */
    protected void swap(ArrayList<VideoGame> table, int i, int j) {
        VideoGame temp = table.get(i) ;
        table.set(i, table.get(j)) ;
        table.set(j, temp) ;
    }

    /**
     * LexicographicComparator.java - A Comparator class which compares VideoGame objects by their titles.</h2>
     *
     * <p>This class fulfills the inner class requirement</p>
     *
     * @author Wes Parker
     * @version Final Project
     */
    static class LexicographicComparator implements Comparator<VideoGame> {

        /**
         * Compares Strings in reverse lexicographic order
         * @param o1 A game to be compared by its title
         * @param o2 Another game to be compared by its title
         * @return -1 if o1 should come before o2, 1 if o1 should go after o2, and 0 if they're equal
         */
        public int compare(VideoGame o1, VideoGame o2) {
            return o1.getName().compareTo(o2.getName()) ;
        }

        /**
         * Always returns false
         * @param obj and object
         * @return false
         */
        public boolean equals(String obj) {
            //Not sure what this method would actually be used for
            return false;
        }
    }
}
