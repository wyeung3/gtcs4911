package com.projectbasil.projectbasil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tehub on 10/14/2015.
 */
public class TestMain {



    public static void main(String[] args) {
        Item item1 = new Item(null, "Item1", null, null);
        Item item2 = new Item(null, "Item2", null, null);
        Item item3 = new Item(null, "Item3", null, null);

        List<Item> list = new ArrayList<Item>();
        list.add(item1); list.add(item2); list.add(item3);
        Inventory inv = new Inventory(list);

        List<Recipe> recList = RecipeManager.getRecipe(inv);

        System.out.println("test");
        for (Recipe rec : recList) {
            System.out.println(rec.getName());
        }

    }
}
