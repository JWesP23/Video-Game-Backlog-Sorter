import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * <h2>GameCollection.java - Represents a collection of games.</h2>
 *
 * <p><b>Instance variables:</b></p>
 * <ul style="margin-left: 25px;">
 *   <li>comparator (Comparator) - A comparator to be used to sort the collection.</li>
 *   <li>list (ArrayList) - A priority queue derived from a heap in the form of an ArrayList which represents the collection.</li>
 * </ul>
 *
 * @author Wes Parker
 * @version Final Project
 */

public class GameCollection<E> {

    //A comparator to be used to sort the collection
    Comparator<E> comparator = (Comparator<E>) new LexicographicComparator() ;
    //A priority queue in the form of an ArrayList which represents the collection
    ArrayList<E> list = null;

    /**
     * No-arg constructor sets comparator to a Lexicographic comparator
     */
    public GameCollection() {
        list = new ArrayList<E>();
    }

    /**
     * Returns the collection's comparator
     * @return the Comparator object used by the collection
     */
    public Comparator<E> getComparator(){
        return comparator ;
    }

    /**
     * No-arg method sets the comparator to a Lexicographic comparator
     */
    public void setComparator() {
        comparator = (Comparator<E>) new LexicographicComparator();
    }

    /**
     * Changes the comparator of this GameCollection object
     * @param comparator the new comparator for this object
     */
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator ;
    }

    /**
     * Calls the compare method of the comparator
     * @param a an object to be compared
     * @param b another object to be compared
     * @return the return value from passing the arguments a and b to the comparator
     */
    public int compare(E a, E b) {
        return comparator.compare(a, b);
    }

    /**
     * Swaps two values in the ArrayList given their index values
     * @param itemAIndex the index of the first item to swap
     * @param itemBIndex the index of the second item to swap
     */
    public void swap(int itemAIndex, int itemBIndex) {

        //Ensures that both arguments passed are valid indices
        if (itemAIndex < 0 || itemAIndex > list.size() || itemBIndex < 0 || itemBIndex > list.size()) {
            throw new NoSuchElementException();
        }

        E temp = list.get(itemAIndex);
        list.set(itemAIndex, list.get(itemBIndex));
        list.set(itemBIndex, temp);
    }

    /**
     * Adds a game into its correct spot in the collection
     * @param item a game to be added to the collection
     * @return true if the item was successfully added or false if it wasn't
     */
    public boolean offer(E item) {

        //Item is added to end of list
        if (!list.add(item)){
            return false ;
        }

        int child = list.size() - 1;  //Because Priority queues have a heap structure
        int parent = (child - 1) / 2; //The parent is always (child - 1) / 2

        //Move the child into the correct spot in the queue
        while (compare(list.get(child), list.get(parent)) < 0) {
            swap(child, parent) ;
            child = parent ;
            parent = (child - 1) / 2;
        }
        return true;
    }

    /**
     * Removes the front item in the queue
     * @return the item removed from the queue
     */
    public E poll() {

        //If there's only one item in the queue then poll that item and skip other commands
        if(list.size() == 1){
            return list.remove(0) ;
        }

        //Remove the top item if it exists
        E returnItem;
        if (!list.isEmpty()) {
            returnItem = list.get(0);
        } else {
            return null;
        }

        //Bring the last item to the front
        list.set(0, list.remove(list.size() - 1));

        int parent = 0;
        int rightChild = (parent + 1) * 2;
        int leftChild = rightChild - 1;

        //Find next in queue by sorting back down the tree with the item in front
        while (true) {
            if (leftChild >= list.size()) {
                break ;
            }

            // Determine whether the left child or the right child holds the smaller data element
            int smallerChild = leftChild ;
            if (rightChild < list.size() && compare(list.get(leftChild), list.get(rightChild)) > 0) {
                smallerChild = rightChild ;
            }

            // If the data element in the smaller child is greater than the data
            // element in its parent, then we're done. Otherwise, swap those two
            // elements in the ArrayList and restart the loop, moving one level
            // down in the heap
            if (compare(list.get(parent), list.get(smallerChild)) < 0) {
                break;
            }
            swap(parent, smallerChild);
            parent = smallerChild;

            rightChild = (parent + 1) * 2;
            leftChild = rightChild - 1;

        }


    return returnItem ;
    }

    /**
     * Checks if there are games in the collection
     * @return true if the collection is empty, otherwise false
     */
    public boolean isEmpty(){
        return list.isEmpty() ;
    }

    /**
     * Tests for equality between two GameCollections
     * @param obj an object to test for equality with the calling object
     * @return true if the argument is equal to the calling object, otherwise false
     */
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GameCollection<E> collection = (GameCollection<E>) obj;
        return list.equals(collection.list);
    }

    /**
     * Formats the games in this collection as a list separated by commas
     * @return The games in this collection as a String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Game Collection contains: ") ;

        for (E game : list) {
            sb.append(game.toString()).append(", ") ;
        }
        sb.deleteCharAt(sb.lastIndexOf(", ")) ;

        return sb.toString() ;
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
