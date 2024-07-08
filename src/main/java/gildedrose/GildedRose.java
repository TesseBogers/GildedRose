package main.java.gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    // Alle items hebben een Quality (kwaliteit) waarde die aangeeft hoe waardevol het item is
    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item)) {
                updateItemQuality(item);
                // Alle artikelen items hebben een SellIn waarde die aangeeft hoeveel dagen we nog hebben om de items te verkopen
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    handleExpiredItem(item);
                }
            }
        }
    }

    private void updateItemQuality(Item item) {
        if (isAgedBrie(item)) {
            updateAgedBrie(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        } else {
            updateRegularItem(item);
        }
    }

    /**
     * Oude Brie "Aged Brie" neemt eigenlijk toe in Quality naarmate het ouder wordt
     * De Quality van een item is nooit meer dan 50
     */
    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    /**
     * "Backstage passes", zoals aged brie, neemt toe in Quality naarmate de SellIn waarde nadert
     * Quality neemt met 2 toe wanneer er 10 dagen of minder zijn en met 3 wanneer er 5 dagen of minder zijn, maar
     * Quality daalt naar 0 na het concert
     * De Quality van een item is nooit meer dan 50
     */
    private void updateBackstagePass(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11 && item.quality < 50) {
                item.quality = item.quality + 1;
            }

            if (item.sellIn < 6 && item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    /**
     * Zodra de uiterste verkoopdatum is verstreken, degradeert Quality twee keer zo snel
     * De Quality van een item is nooit negatief
     */
    private void updateRegularItem(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    /**
     * Zodra de uiterste verkoopdatum is verstreken, degradeert Quality twee keer zo snel
     * Oude Brie "Aged Brie" neemt eigenlijk toe in Quality naarmate het ouder wordt
     * Quality daalt naar 0 na het concert
     */
    private void handleExpiredItem(Item item) {
        if (isAgedBrie(item)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        } else if (isBackstagePass(item)) {
            item.quality = 0;
        } else {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }
}
