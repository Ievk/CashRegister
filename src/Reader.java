// using nio.File
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

public class Reader
    {

        private List<String>readFileInList(String fileName)
        {
            List<String> lines;
            try
            {

                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource(fileName).getFile());
                lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            }

            catch (IOException e)
            {

              return new ArrayList<>();
            }
            catch (Exception e){
                return new ArrayList<>();
            }
            return lines;
        }
        public Map<String, Product> readProducts(String filename){

            Map<String,Product> productMap = new HashMap<>();
            List<String> productFile = readFileInList(filename);
            for (String s: productFile) {
                Product p = Product.GenerateNew(s);
                String key = p.getBarcode();
                productMap.put(key,p);
            }
            return  productMap;
        }

        public void readDiscounts(String filename, Map<String, Product> productMap){
            List<String> discountFile = readFileInList(filename);
            for (String s: discountFile) {
                String[] valueArray = s.split(",");
                String bar = valueArray[0];
                Product p = productMap.get(bar);
                if(p != null)
                  p.updateDiscount(s);

            }

        }
        public Map<String, Grocery> readGroceries(String filename){
            Map<String,Grocery> groceryMap = new HashMap<>();
            List<String> groceryFile = readFileInList(filename);
            for (String s: groceryFile) {
                String bar = s;
                Grocery g= groceryMap.get(bar);
                if (g==null){
                    g = Grocery.GenerateNew(s);
                    groceryMap.put(bar,g);
                }else{
                    g.updateCount();
                }

            }
            return  groceryMap;
        }
    }



