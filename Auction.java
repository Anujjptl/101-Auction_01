import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> listOfLots;
    // The number that will be given to the next lot entered into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        listOfLots = new ArrayList<>();
        nextLotNumber = 1;
        
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        listOfLots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot aLot : listOfLots) {
            System.out.println(aLot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null if a lot with this 
     * number does not exist.
     * @param lotNumber The number of the lot to return.
     * @return The lot with the given number, or null.
     */
    public Lot getLot(int lotNumber)
    {
        for (Lot lot : listOfLots) { // goes through every Lot in the list
        if(lot.getNumber() == lotNumber) { //reads the lotNumber field inside the lot.
            return lot; // found it, return immediately 
                }
            }
                System.out.println("Internal error: Lot number " +
                                   lotNumber +
                                   " was returned instead of " +
                                   lotNumber);
                return null;   // no lot with that number found
    }
    
    
    public void close(){
        for (Lot lot : listOfLots) { //“for each Lot object inside the listOfLots
                                    //ArrayList, call it lot for this loop step.”
            Bid highest = lot.getHighestBid();
            
            if (highest != null) {  // “does highest refer to a real object, 
                                    //or is it empty (null)?”
                //sold 
                System.out.println(
                "Lot " + lot.getNumber() +
                " \"" + lot.getDescription() + "\"" +
                " sold to " + highest.getBidder().getName() +
                " for " + highest.getValue()
                    );
            } else {
                // no bid 
                System.out.println(
                "Lot " + lot.getNumber() +
                " \"" + lot.getDescription() + "\"" +
                " has no bids.");
            }
        }
    }
    
    public ArrayList<Lot> getUnsold() {
    ArrayList<Lot> unsoldLots = new ArrayList<>();
    
    for (Lot lot : listOfLots){
        if (lot.getHighestBid() == null) {
            unsoldLots.add(lot);
        }
    }
    return unsoldLots;
    }
    
    public Lot removeLot(int number)
    {
    Lot lot = getLot(number);    // use your new search-based getLot
    if (lot != null) {
        listOfLots.remove(lot);  // remove that Lot object from the list
    }
    return lot;                  // will be null if not found
    }

}


