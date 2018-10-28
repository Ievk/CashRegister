public class Grocery {

    private String barcode;
    private int count;
    private Product product;


    public Grocery(String barcode){
        this.barcode= barcode;
        count=1;
    }
    public String getBarcode() {
        return barcode;
    }

    public int getCount() {
        return count;
    }

    public static Grocery GenerateNew(String bar) {
        return new Grocery(bar);

    }

    public void updateCount(){
        this.count++;
    }
    public void setProduct(Product p){
        this.product=p;
    }
    public String getName(){
        if (this.product != null){
            return this.product.getName();
        }else{
            return "";
        }
    }
    public String getCategory(){
        if (this.product != null){
            return this.product.getCategory();
        }else{
            return "";
        }
    }
    public double GetTotal() {
        if (this.product != null){
            String value = this.product.getKr() +"." +this.product.getOre() ;
            double dValue = Double.parseDouble(value);
            double d = this.count * dValue;
            return this.count * dValue ; //Math.round(d * 100.0) / 100.0;
        }else{
            return 0;
        }
    }
    public double GetTotalDiscount() {
        if (this.product != null && product.getLimit() != 0 && this.count >= product.getLimit()){
            String value = this.product.getDiscount_kr() +"." +String.format("%02d", this.product.getDiscount_ore()) ;
            double dValue = Double.parseDouble(value);
            double test = (this.count *  dValue);
            double test2 = GetTotal();
            double test3 = test2 - test;
            double d = GetTotal() - (this.count *  dValue);
            String ds = getDiscountDescription();
            return  d ; //Math.round(d * 100.0) / 100.0;
        }else{
            return 0;
        }
    }

    public String GetTotalString() {
        if (this.product != null){
            int totalKr = this.count  * this.product.getKr();
            int tmpTotalOre =  this.count * this.product.getOre();
            int totalOre = tmpTotalOre/ 100;
            int remainder = tmpTotalOre % 100 ;
            String remainderValue = String.format("%02d", remainder);
            return (totalKr + totalOre) +"," + remainderValue;
        }else{
            return "0";
        }
    }
    public String GetTotalDiscountString() {
        if (this.product != null){
            int totalKr = this.count  * (this.product.getKr()- this.product.getDiscount_kr());
            int tmpTotalOre =  this.count * (this.product.getOre() -this.product.getDiscount_ore());
            int totalOre = tmpTotalOre/ 100;
            int remainder = tmpTotalOre % 100 ;
            String remainderValue = String.format("%02d", remainder);
            return (totalKr + totalOre) +"," + remainderValue;
        }else{
            return "0";
        }
    }

    public String getSimpleDescription(){

        String productInfo = "";
        productInfo = String.format("%-"+ getName().length()+"s", getName(), productInfo) +  String.format("%"+(37- getName().length())+"s",this.GetTotalString() , productInfo) ;
        return productInfo;
    }
    public String getAdvanceDescription(){
/*        "SKYR YOGHURT\n" +
                "  2 x 22,75                     45,50\n" +*/
        String productInfo = "";
        String priceInfo = "";
        productInfo = String.format("%-37s", getName(), productInfo) + System.lineSeparator() ;
        String info = "  "+this.count + " X "+ product.getKr() + "," + product.getOre();
        priceInfo = String.format("%-"+ info.length()+"s", info, priceInfo) +
                     String.format("%"+(37- info.length())+"s",this.GetTotalString()  , priceInfo) ;
        return productInfo + priceInfo;
    }
    public String getDiscountDescription(){
        String newline = System.lineSeparator();
        String discount = "";
        discount = String.format("%-"+ getName().length()+"s", "Rabat", discount) +  String.format("%"+(38- getName().length())+"s",this.GetTotalDiscountString() + "-", discount) ;
        return newline + discount;
    }
    @Override
    public String toString()
    {
        String output = "";
     if(this.count == 1){
         output = getSimpleDescription();
     }
      else
     {
         output =getAdvanceDescription();
     }
     if(product.getLimit() != 0 && this.count >= product.getLimit()){
         output += getDiscountDescription();
     }
     return  output;
    }
}
