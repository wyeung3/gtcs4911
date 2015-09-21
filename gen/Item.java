/**
 * Created by tehub on 9/21/2015.
 */
public class Item {
    //
    int code; //USDA food code
    String name; //name of the item
    float quantity; //quantity IE mL, gallons, teaspoons etc
    //TODO: add list of nutrional attributes

    public Item(int code, String name, float quantity){
        this.code = code;
        this.name = name;
        this.quantity = quantity;

        //TODO: Add nutrional attributes to constructor
        // Add try catch for nulls?
    }

    public String getName(){
        return name;
    }

    public float getQuantity(){
        return quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCode(int code){
        this.code = code;
    }

    public void setQuantity(float quantity){
        this.quantity = quantity;
    }

    //TODO: add get/set methods for nutrional attributes
}
