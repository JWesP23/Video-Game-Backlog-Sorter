import java.util.*;

/**
 * <h2>BacklogSorter.java - Demonstrates a video game sorting system which helps a user organize and select a game from their backlog.</h2>
 *
 <p><b>Description:</b>
 *   Utilizes a priority queue to display games one by one in an order determined by a filter controlled by the user.
 *   This filter also determines what information from each game is displayed to the user.
 *   Games are placed into a stack if skipped, so the user can seamlessly move back and forth between games.
 *   Games can also be added into a "maybe" (ArrayList) list where they can be reviewed altogether separated from the rest of the collection.
 *   The "maybe" list displays all information available about each game and can filtered as well.
 *
 *
 * <p><b>Algorithm:</b></p>
 * <ol style="margin-left: 25px;">
 *   <li>Loop until the user enters quit: </li><ol type="i">
 *       <li>Display the game at the front of the queue</li>
 *       <li>Display command prompt and get user input</li><ol type="a">
 *           <li>If the user entered "next" store the current game in the stack and continue loop</li>
 *           <li>If the user entered "back" pop from the top of the stack and offer back into the queue then continue loop</li>
 *           <li>If the user entered "maybe" store the current game in the ArrayList and repeat step ii.</li>
 *           <li>If the user entered "change" determine their preferred sorting method then re-heap and continue loop</li>
 *           <li>If the user entered "quit" exit the loop</li>
 *           </ol>
 *       </ol>
 *   <li>Loop until the user enters quit: </li><ol type="i">
 *      <li>Sort the ArrayList by the current filter</li>
 *      <li>Display a table containing info about each game in the ArrayList</li>
 *      <li>Display command prompt and get user input</li><ol type="a">
 *          <li>If the user entered "change" determine their preferred sorting method and continue loop</li>
 *          <li>If the user entered "quit" exit the loop</li>
 *      </ol>
 * </ol>
 *
 *
 * @author Wes Parker
 * @version Final Project
 */

public class BacklogSorter {

    //Scanner for input
    static Scanner keyboard = new Scanner(System.in) ;

    //VideoGame objects for demonstration purposes
    static VideoGame portal2 = new VideoGame("Portal 2","Switch", 4182011, 11222023, 6.74, 6.0, 6.0) ;
    static VideoGame demonSouls = new VideoGame("Demon's Souls (remake)", "PS5", 11122020, 6152023, 70.00, 32.00, 6.5) ;
    static VideoGame riseTombRaider = new VideoGame("Rise of the Tomb Raider", "PS5", 11102015, 3032024, 17.49, 22.00, 5) ;
    static VideoGame shadowTombRaider = new VideoGame("Shadow of the Tomb Raider", "PS5", 9122018, 3032024, 17.49, 23.00, 7.0) ;
    static VideoGame dishonored = new VideoGame("Dishonored: Death of the Outsider", "PS5", 9152017, 1182024, 5.99, 11.50, 7.0) ;
    static VideoGame re7 = new VideoGame("Resident Evil 7 biohazard", "PS5", 1242017, 6192024, 7.99, 12.00, 7.5) ;
    static VideoGame re8 = new VideoGame("Resident Evil Village", "PS5", 5072021, 12042023, 19.99, 13.00, 9) ;
    static VideoGame bloodborne = new VideoGame("Bloodborne", "PS5", 3242015, 5312024, 17.49, 43.50, 9) ;
    static VideoGame halo = new VideoGame("Halo: The Master Chief Collection", "PC", 11112014, 11302024, 24.99, 63.00, 10) ;
    static VideoGame witcher = new VideoGame("The Witcher 3: Wild Hunt", "PS4", 19052015, 2202021, 7.99, 107.12, 4) ;
    static VideoGame fallout = new VideoGame("Fallout 4", "PS4", 11102015, 2272020, 14.99, 88.62, 5) ;
    static VideoGame wolfenstein = new VideoGame("Wolfenstein: The New Order", "PS4", 20052014, 7152021, 10.11, 15.72, 6) ;

    //An array of VideoGame objects used for rehearing when the comparator changes
    static VideoGame[] games = {portal2, demonSouls, riseTombRaider, shadowTombRaider, dishonored, re7, re8, bloodborne, halo, witcher, fallout, wolfenstein} ;

    //A priority queue that stores VideoGame objects in a backlog
    static GameCollection<VideoGame> backlog = new GameCollection<>() ;

    //A stack object for forward and back functionality during traversal
    static Stack<VideoGame> skippedGames = new Stack<>() ;

    //An ArrayList to store games which the user wants to view separated from the rest of the collection
    static ArrayList<VideoGame> maybeGames = new ArrayList<>(games.length/3) ;

    public static void main(String[] args) {

        System.out.println("\tA video game backlog is a collection of games which one has purchased but has yet to play. " +
                "\nThe purpose of this project is to help the user sort through their backlog in order to determine what they should play next.") ;


        //populate the backlog
        for (VideoGame game : games){
            backlog.offer(game) ;
        }

        String input = "" ;                 //Input to be read from the user and interpreted by the program
        boolean quit = false ;              //Determines when to stop traversing the backlog
        boolean nextGame = true ;           //Determines whether to display the next game or simply repeat command instructions
        boolean validInput = false ;        //Represents whether the user's last input was a valid command that is interpretable by the programs
        boolean reprompt = false ;          //Determines whether to repeat command instructions

        VideoGame currentGame = null ;      //The VideoGame object last popped off the backlog
        String currentFilter = "title" ;    //Determines format of output String based on the comparator currently being used


        while (!quit){

            //Print the next game in the queue
            if (nextGame) {
                currentGame = backlog.poll();
                System.out.print("\n" + currentGame.getName()) ;

                //Output determined by filter
                switch (currentFilter) {

                    case "price":
                        System.out.println(": $" + currentGame.getPrice()) ;
                        break;

                    case "length":
                        int hours = (int) currentGame.getLength() ;
                        int minutes = (int) Math.ceil(60 * ((currentGame.getLength() * 100) % 100 / 100)) ;

                        if (minutes == 0) {
                            System.out.printf(": %d hours\n", hours) ;
                        } else {
                            System.out.printf(": %d hours and %d minutes\n", hours, minutes) ;
                        }
                        break;

                    case "release date":
                        int releaseDate = currentGame.getReleaseDate() ;
                        int year = (releaseDate % 10000) ;
                        int month = (releaseDate / 1000000 % 100) ;
                        int day = (releaseDate / 10000 % 100) ;
                        System.out.printf(": released on - %d/%d/%d\n", month, day, year) ;
                        break ;

                    case "date purchased":
                        int datePurchased = currentGame.getDatePurchased() ;
                        int y = (datePurchased % 10000) ;
                        int m = (datePurchased / 1000000 % 100) ;
                        int d = (datePurchased / 10000 % 100) ;
                        System.out.printf(": purchased on - %d/%d/%d\n", m, d, y) ;
                        break ;

                    case "excitement":
                        System.out.println(": Excitement Level - " + currentGame.getLvlOfExcitement()) ;
                        break ;
                }
            }

            //Print the command instructions
            if (nextGame || !validInput || reprompt){
                System.out.print("\nEnter \"next\" to see the next game in your backlog that you should play" +
                        "\n\"back\" to see the last game" +
                        "\n\"maybe\" to add the list to a 'maybe' list" +
                        "\n\"change\" to switch filter" +
                        "\nor \"quit\" if you wish to review your 'maybe' list: ") ;
            }

            //Values will be set to true depending on input
            validInput = false ;
            nextGame = false ;
            reprompt = false ;

            input = keyboard.nextLine().toLowerCase().trim() ;

            //Display the next game
            if (input.equals("next")){
                validInput = true ;
                if (!backlog.isEmpty()) {
                    //push the current game to the skipped games stack before moving to the next
                    skippedGames.push(currentGame) ;
                    nextGame = true ;
                    clearScreen() ;
                } else {
                    //If the backlog is empty there is nothing left to display
                    reprompt = true ;
                    System.out.println("\nThere are no games left to look at.") ;
                }
            }

            //Go back to the last game shown
            if (input.equals("back")){

                validInput = true ;

                if(!skippedGames.isEmpty()) {
                    //pop the top game from the skippedGames list and reinsert it into the queue where it will be naturally sorted to the top
                    nextGame = true ;
                    backlog.offer(currentGame) ;
                    backlog.offer(skippedGames.pop()) ;
                    clearScreen() ;
                } else {
                    //If the skippedGames list is empty there is nothing to display
                    reprompt = true ;
                    System.out.println("\nCannot go back. This is the first game by the current filter.") ;
                }
            }

            //Add the current game to the maybeGames ArrayList
            if (input.equals("maybe")){
                validInput = true ;

                //Check if the current game is already in the maybeGames list
                Iterator<VideoGame> iter = maybeGames.iterator() ;
                boolean containsCurrentGame = false ;
                while (iter.hasNext()){
                    if (iter.next().equals(currentGame)){
                        containsCurrentGame = true ;
                    }
                }

                if (!containsCurrentGame){
                    //If the list does not contain the game, append the game to the list
                    skippedGames.push(currentGame) ;
                    maybeGames.add(currentGame) ;
                    System.out.println("\n" + currentGame.getName() + " successfully added to 'maybe' list") ;
                    reprompt = true ;
                    if (!backlog.isEmpty()) {
                        nextGame = true ;
                        clearScreen() ;
                    }
                } else {
                    //Inform the user that the game is already in the maybe list and do not append it to the list
                    System.out.println("\nYour 'maybe' list already contains " + currentGame.getName()) ;
                    reprompt = true ;
                }
            }

            //Change the current filter
            if (input.equals("change")){
                validInput = true ;
                nextGame = true ;
                currentFilter = changeComparator() ;
            }

            //Quit traversing the queue
            if (input.equals("quit")){
                validInput = true ;
                quit = true ;
            }

            //Re-prompt is a valid input is not entered
            if (!validInput){
                System.out.println("\nPlease enter a valid input.") ;
            }
        }

        //Display the maybe games
        if (!maybeGames.isEmpty()) {

            QuickSort quickSort = new QuickSort() ;

            quit = false ;
            while (!quit){

                //Sort the backlog based on the current filter
                switch (currentFilter) {

                    case "title":
                        if (backlog.getComparator() instanceof ReverseComparator){
                            quickSort = new QuickSort(new ReverseComparator(backlog.getComparator())) ;
                        } else {
                            quickSort = new QuickSort() ;
                        }
                        break ;

                    case "price":
                        if (backlog.getComparator() instanceof ReverseComparator){
                            quickSort = new QuickSort(new ReverseComparator(new PriceComparator())) ;
                        } else {
                            quickSort = new QuickSort(new PriceComparator()) ;
                        }
                        break;

                    case "length":
                        if (backlog.getComparator() instanceof ReverseComparator){
                            quickSort = new QuickSort(new ReverseComparator(new LengthComparator())) ;
                        } else {
                            quickSort = new QuickSort(new LengthComparator()) ;
                        }
                        break;

                    case "release date":
                        if (backlog.getComparator() instanceof ReverseComparator){
                            quickSort = new QuickSort(new ReverseComparator(new RelaseDateComparator())) ;
                        } else {
                            quickSort = new QuickSort(new RelaseDateComparator()) ;
                        }
                        break;

                    case "date purchased":
                        if (backlog.getComparator() instanceof ReverseComparator){
                            quickSort = new QuickSort(new ReverseComparator(new DatePurchasedComparator())) ;
                        } else {
                            quickSort = new QuickSort(new DatePurchasedComparator()) ;
                        }
                        break;

                    case "excitement":
                        if (backlog.getComparator() instanceof ReverseComparator){
                            quickSort = new QuickSort(new ReverseComparator(new ExcitementComparator())) ;
                        } else {
                            quickSort = new QuickSort(new ExcitementComparator()) ;
                        }
                        break;
                }

                if (validInput) {
                    clearScreen();
                }

                    quickSort.sort(maybeGames) ;

                //Display all info about each maybe game
                    System.out.println("Here's your 'maybe' list:") ;
                    int gameCount = 1 ;
                    System.out.println("                             Name                           Release Date  Purchase Date   Price   Console            Length            Excitement Level") ;
                    System.out.println("    ------------------------------------------------------  ------------  -------------  -------  -------  --------------------------  ----------------") ;

                    for (VideoGame game : maybeGames) {
                        if (game != null) {
                            String releaseDate = (game.getReleaseDate() / 1000000 % 100) + "/" + (game.getReleaseDate() / 10000 % 100) + "/" + (game.getReleaseDate() % 10000) ;
                            String datePurchased = (game.getDatePurchased() / 1000000 % 100) + "/" + (game.getDatePurchased() / 10000 % 100) + "/" + (game.getDatePurchased() % 10000) ;

                            String lengthString ;
                            int hours = (int) game.getLength() ;
                            int minutes = (int) Math.ceil(60 * ((game.getLength() * 100) % 100 / 100)) ;

                            if (minutes != 0) {
                                lengthString = hours + " hours and " + minutes + " minutes" ;
                            } else {
                                lengthString = hours + " hours" ;
                            }

                            System.out.printf("%d.  %-54s  %-12s  %-13s  $%-,6.2f  %-7s  %-26s  %-16.2f\n", gameCount++, game.getName(), releaseDate, datePurchased, game.getPrice(), game.getConsole(), lengthString, game.getLvlOfExcitement()) ;
                        }
                    }

                    //Prompt for input
                System.out.print("\nEnter \"change\" to filter your 'maybe' list" +
                            "\nor \"quit\" to exit the program: ") ;

                validInput = false ;
                input = keyboard.nextLine().toLowerCase().trim() ;

                //Change the filter by which maybe games are ordered
                if (input.equals("change")){
                    validInput = true ;
                    currentFilter = changeComparator() ;
                }

                //End the program
                if (input.equals("quit")){
                    validInput = true ;
                    quit = true ;
                }

                //Re-prompt for valid input
                if (!validInput){
                    System.out.println("\nPlease enter a valid input.\n") ;
                }
            }

        }

    }

    //Simulates clearing screen because I can't find a simple enough way to actually do it
    public static void clearScreen(){
        for(int i = 0 ; i <50 ; i++){
            System.out.println() ;
        }
    }

    /**
     * Changes the comparators for the backlog and the maybeGames list
     * @return a String representing the new comparator for these objects
     */
    public static String changeComparator(){

        boolean validInput = false ;    //Represents whether the user's last input was a valid command that is interpretable by the programs
        String newFilter = "title" ;    //Represents the filter by which the collection will be sorted
        String input = "" ;             //Input to be read from the user and interpreted by the program

        //Command prompt
        while (!validInput) {
            System.out.print("\nWhat would you like to filter by?"
                    +       "\nEnter \"title\"" +
                            "\n\"price\"" +
                            "\n\"length\"" +
                            "\n\"release date\"" +
                            "\n\"date purchased\"" +
                            "\nor \"excitement\": ") ;

            input = keyboard.nextLine().trim().toLowerCase() ;
            System.out.println() ;

            //Set comparator to appropriate filter
            if(input.equals("title")){
                validInput = true ;
                backlog.setComparator() ;
                newFilter = "title" ;
            }

            if(input.equals("price")){
                validInput = true ;
                backlog.setComparator(new PriceComparator()) ;
                newFilter = "price" ;
            }

            if(input.equals("length")){
                validInput = true ;
                backlog.setComparator(new LengthComparator()) ;
                newFilter = "length" ;
            }

            if(input.equals("release date")){
                validInput = true ;
                backlog.setComparator(new RelaseDateComparator()) ;
                newFilter = "release date" ;
            }

            if(input.equals("date purchased")){
                validInput = true ;
                backlog.setComparator(new DatePurchasedComparator()) ;
                newFilter = "date purchased" ;
            }

            if(input.equals("excitement")){
                validInput = true ;
                backlog.setComparator(new ExcitementComparator()) ;
                newFilter = "excitement" ;
            }

            //Re-prompt on invalid inputs
            if (!validInput){
                System.out.println("\nPlease enter a valid input.") ;
            }
        }

        validInput = false ;

        //Determine sorting low to high or high to low
        while (!validInput){
            System.out.print("How would you like to filter?"
                    + "\nEnter \"high to low\"" +
                    "\nor \"low to high\": ") ;

            input = keyboard.nextLine().trim().toLowerCase() ;
            System.out.println() ;

            if(input.equals("low to high")){
                validInput = true ;
            }

            //Use reverse comparator of the current comparator
            if(input.equals("high to low")){
                validInput = true ;
                backlog.setComparator(new ReverseComparator(backlog.getComparator())) ;
            }

            //Re-prompt on invalid inputs
            if (!validInput){
                System.out.println("\nPlease enter a valid input.") ;
            }
        }

        //Clear backlog
        while (!backlog.isEmpty()){
            backlog.poll() ;
        }

        //Repopulate backlog to resort it with the new comparator
        for (VideoGame game : games){
            backlog.offer(game) ;
        }

        return newFilter ;
    }
}