import java.util.Objects;

/**
 * <h2>VideoGame.java - Represents a Video Game.</h2>
 *
 * <p><b>Instance variables:</b></p>
 * <ul style="margin-left: 25px;">
 *   <li>name (String) - The title of the game.</li>
 *   <li>console (String) - The console that the user owns the game on.</li>
 *   <li>releaseDate (int) - The date when the game came out.</li>
 *   <li>datePurchased (int) - The date when the user purchased the game.</li>
 *   <li>price (double) - The price that the user paid for the game.</li>
 *   <li>length (double) - The average time (in hours) it takes to finish the game.</li>
 *   <li>lvlOfExcitement (double) - The level of excitement (on a scale of 10) the user has to play the game.</li>
 * </ul>
 *
 *
 * @author Wes Parker
 * @version Final Project
 */

public class VideoGame {

    private String name = "Unknown";        //The title of the game
    private String console = "Unknown";     //The console that the user owns the game on
    private int releaseDate = 0;            //The date when the game came out
    private int datePurchased = 0;          //The date when the user purchased the game
    private double price = 0.0;             //The price that the user paid for the game
    private double length = 0.0;            //The average time (in hours) it takes to finish the game
    private double lvlOfExcitement = 0.0;   //The level of excitement (on a scale of 10) the user has to play the game

    /**
     * Full constructor for all parameters
     * @param name The title of the game
     * @param console The console that the user owns the game on
     * @param releaseDate The date when the game came out
     * @param datePurchased The date when the user purchased the game
     * @param price The price that the user paid for the game
     * @param length The average time (in hours) it takes to finish the game
     * @param lvlOfExcitement The level of excitement (on a scale of 10) the user has to play the game
     */
    public VideoGame(String name, String console, int releaseDate, int datePurchased, double price, double length, double lvlOfExcitement) {
        this.name = name;
        this.console = console;
        this.releaseDate = releaseDate;
        this.datePurchased = datePurchased;
        this.price = price;
        this.length = length;
        this.lvlOfExcitement = lvlOfExcitement;
    }

    /**
     * Returns the game's name
     * @return a String containing the name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this VideoGame object
     * @param name the new name for this object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the console that the game is on
     * @return a String containing the console that the user owns the game on
     */
    public String getConsole() {
        return console;
    }

    /**
     * Changes the console of this VideoGame object
     * @param console the new console for this object
     */
    public void setConsole(String console) {
        this.console = console;
    }

    /**
     * Returns the date when the game came out
     * @return an int representing the game's release date
     */
    public int getReleaseDate() {
        return releaseDate;
    }

    /**
     * Changes the releaseDate of this VideoGame object
     * @param releaseDate the new releaseDate for this object
     */
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Returns the date when the user purchased the game
     * @return an int representing the purchase date
     */
    public int getDatePurchased() {
        return datePurchased;
    }

    /**
     * Changes the datePurchased of this VideoGame object
     * @param datePurchased the new datePurchased for this object
     */
    public void setDatePurchased(int datePurchased) {
        this.datePurchased = datePurchased;
    }

    /**
     * Returns the price the user paid for this game
     * @return a double representing the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Changes the price of this VideoGame object
     * @param price the new price for this object
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the average length to beat this game
     * @return a double representing the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Changes the length of this VideoGame object
     * @param length the new length for this object
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Returns the level of excitement (on a scale of 10) the user has to play the game
     * @return a double representing the user's level of excitement
     */
    public double getLvlOfExcitement() {
        return lvlOfExcitement;
    }

    /**
     * Changes the level of excitement for this VideoGame object
     * @param lvlOfExcitement the new level of excitement for this object
     */
    public void setLvlOfExcitement(double lvlOfExcitement) {
        this.lvlOfExcitement = lvlOfExcitement;
    }

    /**
     * Compares the names of two Video game objects as a test for equality
     * @param obj a VideoGame object or a descendant of a VideoGame object to compare to the calling object
     * @return true if the objects are equal; false if they are not equal
     */
    public boolean equals(Object obj) {
        if (obj instanceof VideoGame) {
            return Objects.equals(((VideoGame) obj).name, name);
        }
        return false;
    }

    /**
     * Combines all the instance variables into a String
     * @return a String representing all the information about this VideoGame
     */
    public String toString() {
        String releaseDate = (this.releaseDate / 1000000 % 100) + "/" + (this.releaseDate / 10000 % 100) + "/" + (this.releaseDate % 10000) ;
        String datePurchased = (this.datePurchased / 1000000 % 100) + "/" + (this.datePurchased / 10000 % 100) + "/" + (this.datePurchased % 10000) ;
        int hours = (int) length ;
        int minutes = (int) Math.ceil(60 * ((length * 100) % 100 / 100)) ;  //multiply only the decimal values in length with 60 to get the number of minutes

        String returnString = "\"" + name + "\"" + " released on: " + releaseDate + " purchased on: " +
                datePurchased + " for: $" + price + " on: " + console + " length: " ;

        if (minutes != 0) {
            returnString += +hours + " hours and " + minutes + " minutes" + " excitement-level: " + lvlOfExcitement ;
        } else {
            returnString += +hours + " hours" + " excitement-level: " + lvlOfExcitement;
        }


        return returnString;

    }

}
