import java.util.ArrayList;
import java.util.List;

public class Product {
    //Price info <barcode>,<category>,<name>,<kr>,<øre>
    // Discount <barcode>,<limit>,<kr>,<øre>

    public String barcode;
    public String category;
    public String name;
    public int kr;
    public int ore;
    public int limit;
    public int discount_kr;
    public int discount_ore;

    public Product(String barcode, String category, String name, int kr, int ore) {
        this.barcode = barcode;
        this.category = category;
        this.name = name;
        this.kr = kr;
        this.ore = ore;
    }

    public static Product GenerateNew(String values) {
        String[] valueArray = values.split(",");
        String bar = valueArray[0];
        String cat = valueArray[1];
        String nam = valueArray[2];
        String kroner = valueArray[3];
        String or = valueArray[4];
        return new Product(bar, cat, nam, Integer.parseInt(kroner), Integer.parseInt(or));

    }

    public void updateDiscount(String discount){
        String[] valueArray = discount.split(",");
        String dLimit = valueArray[1];
        String dKroner = valueArray[2];
        String dOr = valueArray[3];
        this.limit = Integer.parseInt(dLimit);
        this.discount_kr = Integer.parseInt(dKroner);
        this.discount_ore = Integer.parseInt(dOr);


    }

    public String getBarcode() {
        return barcode;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getKr() {
        return kr;
    }

    public int getOre() {
        return ore;
    }


    public int getLimit() {
        return limit;
    }

    public int getDiscount_kr() {
        return discount_kr;
    }

    public int getDiscount_ore() {
        return discount_ore;
    }
}
