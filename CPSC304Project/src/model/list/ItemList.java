package model.list;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private List<Item> items;

    public ItemList() {
        items = new ArrayList<>();
        items.add(new Item("Blade", "ItemDescription1"));
        items.add(new Item("Boot", "ItemDescription2"));
        items.add(new Item("Ring", "ItemDescription3"));
        items.add(new Item("Health Potion", "ItemDescription4"));
        items.add(new Item("Hammer", "ItemDescription5"));
    }

    public List<Item> getItems() {
        return items;
    }

    public void printItemData() {
        System.out.printf("%20s %20s\n", "ItemName", "ItemDescription");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%20s %20s\n", items.get(i).getItemName(), items.get(i).getItemDescription());
        }
    }

    public void add(Item[] itemArrayFromDatabase) {
        for (int i = 0; i < itemArrayFromDatabase.length; i++) {
            this.items.add(itemArrayFromDatabase[i]);
        }
    }
}
