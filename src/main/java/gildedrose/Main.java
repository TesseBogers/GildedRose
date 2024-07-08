package main.java.gildedrose;

public class Main {
    public static void main(String[] args) {

        Item[] items = new Item[] {
                new Item("Normal Item", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Normal Item", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        System.out.println("Initial state:");
        for (Item item : items) {
            System.out.println(item);
        }

        int days = 5;
        for (int day = 0; day < days; day++) {
            System.out.println("Day " + (day + 1) + ":");
            app.updateQuality();
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }
}