import java.util.*;
public class CashRegister {

    private Map<String,Product> productMap;
    private Reader reader;

    public CashRegister(String priceFilename, String discountsFilename)
    {
        this.reader= new Reader();
        this.productMap= this.reader.readProducts(priceFilename);
        this.reader.readDiscounts(discountsFilename,this.productMap);

    }

    public void printReceipt(String barcodeFilename){

        Map<String,Grocery> groceryMap= reader.readGroceries(barcodeFilename);
        Map<String, List<Grocery>> categoryMap = new HashMap<>();
        Set<String> keys = groceryMap.keySet();
        for (String barcode: keys)
        {
            Grocery g = groceryMap.get(barcode);
            Product p = productMap.get(barcode);
            g.setProduct(p);
            //Insert intocategory map
            String category = g.getCategory();
            List<Grocery> groceriesInCat = categoryMap.get(category);
            if(groceriesInCat != null){
                groceriesInCat.add(g);
            }
            else{
                groceriesInCat = new ArrayList<>();
                groceriesInCat.add(g);
                categoryMap.put(category, groceriesInCat);
            }

        }
        List<String> categories = new ArrayList<>();
        Set<String> categorykeys = categoryMap.keySet();
        for (String cat:categorykeys) {
            categories.add(cat);
        }
        java.util.Collections.sort(categories);
        for (String category:categories) {
            System.out.println();
            System.out.println(center_string_with_java("* " + category + " *",38));
            List<Grocery> groceries = categoryMap.get(category);
            for (Grocery g:groceries) {
                System.out.println(g);
            }
        }
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();
        printSum(groceryMap);

    }
    private void printSum(Map<String,Grocery> groceryMap){

        Collection<Grocery> groceries = groceryMap.values();
        double SUBTOT = 0;
        double RABAT = 0;
        for (Grocery g:groceries) {
            SUBTOT += g.GetTotal();
            RABAT += g.GetTotalDiscount();
        }
        SUBTOT = Math.round(SUBTOT * 100.0) / 100.0;
        RABAT = Math.round(RABAT * 100.0) / 100.0;
        double TOTAL =Math.round((SUBTOT - RABAT) * 100.0) / 100.0;
        long STAMPS = Math.round(TOTAL/50);
        double MOMS = Math.round((TOTAL * 0.20) * 100.0) / 100.0;

        String subtotal = "";
        subtotal =  String.format("%-19s", "SUBTOT", subtotal) +  String.format("%18s",convertToString(SUBTOT) , subtotal) ;
        System.out.println(subtotal);
        System.out.println();

        if(RABAT > 0){
            String rabat = "";
            rabat =  String.format("%-19s", "RABAT", rabat) +  String.format("%18s",convertToString(RABAT) , rabat) ;
            System.out.println(rabat);
            System.out.println();
        }

        String total = "";
        total =  String.format("%-19s", "TOTAL", total) +  String.format("%18s",convertToString(TOTAL) , total) ;
        System.out.println(total);
        System.out.println();

        String stamps = "";
        stamps =  String.format("%-37s", "KØBET HAR UDLØST "+STAMPS+" MÆRKER", stamps) ;
        System.out.println(stamps);
        System.out.println();


        String moms = "";
        moms =  String.format("%-19s", "MOMS UDGØR", moms) +  String.format("%18s",convertToString(MOMS) , moms) ;
        System.out.println(moms);
        System.out.print(System.lineSeparator());


    }
    private String convertToString(double d){
        String ds = Double.toString(d);
        ds = ds.replace(".", ",");
        String[] split = ds.split(",");
        String remainder =  ""+Integer.parseInt(split[1]);
        if(remainder.length() == 1)
            remainder+="0";


       // String remainderValue = String.format("%02d", remainder);
        return  split[0] +","+ remainder;
    }
//https://www.leveluplunch.com/java/examples/center-justify-string/
    private String center_string_with_java (String value, int length) {

        int width = length;
        String s = value;

        int padSize = width - s.length();
        int padStart = s.length() + padSize / 2;

        s = String.format("%"+ (padStart-1) + "s", s);
        s = String.format("%-" + width  + "s", s);

        return s;
    }

}


