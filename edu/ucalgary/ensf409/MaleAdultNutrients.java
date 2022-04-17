/**
@author Sahil Bhatt
@author Harshal Patel
@author Siwon Kim
@author Abhiraam Manchiraju    

<a href="mailto:sahil.bhatt@ucalgary.ca"> sahil.bhatt@ucalgary.ca</a>
<a href="mailto:harshal.patel@ucalgary.ca"> harshal.patel@ucalgary.ca</a>
<a href="mailto:siwon.kim@ucalgary.ca"> siwon.kim@ucalgary.ca</a>
<a href="mailto:abhiraam.manchiraju@ucalgary.ca"> abhiraam.manchiraju@ucalgary.ca</a>

@version 1.8
@since 1.0
*/

package edu.ucalgary.ensf409;

/**
 * This class extends the abstract Nutrients class and retreives the nutrional requirment info for an average
 * male adult from the database. The data is then also manipulated to accomdate the number of male adults in a
 * certain hamper.
*/
public class MaleAdultNutrients extends Nutrients{
    private final static int CLIENTID = 1;
    private int numMales;
    
    /**
     * This constructor takes an input of the amount of male adults in an hamper, and then sets the total nutrional requirements
     * for the adult males in that hamper.
     * @param amount The number of adult males in a hamper.
    */
    public MaleAdultNutrients(int amount){
        this.numMales = amount;
    }


    /**
     * This method returns the number of adult males in a hamper.
     * @param none
    */
    public int getNumMales(){
        return this.numMales;
    }


    /**
     * This method sets the number of adult males in a hamper, which is then used to find the nutrional requirements for all the adult males
     * in a hamper.
     * @param none
     * @param amount The amount of adult males in a hamper.
    */
    public void setNumMales(int amount){
        this.numMales = amount;
    }


    /**
     * This method returns the client ID corresponding to the client type of an adult male.
     * 
    */
    public static int getClientId(){
        return MaleAdultNutrients.CLIENTID;
    }


    /**
     * This method retreives the nutrional info for an adult male through the database, and sets the nutrional requirements for a hamper based
     * on the number of adult males in the hamper. The methods used are extensions of the Nutrients class.
     * @param none
    */
    protected void findInfoFromDataBase(){
        Database db = this.establishDB();
        int[] values = db.getClientNeeds(MaleAdultNutrients.CLIENTID);

        //Performing the calculations for the needed nutrients in one week
        this.setWholeGrain(((double)values[0] / 100) * values[4]* numMales * 7);
        this.setFruitsVeggies(((double)values[1] / 100) * values[4]* numMales * 7);
        this.setProtein(((double)values[2] / 100) * values[4]* numMales * 7);
        this.setOther(((double)values[3] / 100) * values[4]* numMales * 7);
        this.setCalories(values[4]);
        db.close();
    }
}//End of Class Declaration
