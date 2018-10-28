public class Program {
    public static void main (String [] args){
      /*  CashRegister register = new CashRegister("files\\prices.txt", "files\\discounts.txt");
        register.printReceipt("files\\bar6.txt");*/

        CashRegister register = new CashRegister("prices.txt", "discounts.txt");
        register.printReceipt("bar1.txt");

    }


}
