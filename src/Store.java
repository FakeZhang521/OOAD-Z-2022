import java.util.ArrayList;

interface ItemAccess{
    static String SKUitemclass(int SKU);
}

class Store implements ProjectMessage, ItemAccess{
     private double register = 0;
     private final ArrayList<Item> goods;
     final ArrayList<ArrayList<Integer>> mailBox;
     private int[] inventorylist;
     Store(){
          // TODO: 2/7/2022 Please implement the initialization part
          //For the use of SKU attribute, please check how I coded the
          //scheduleShipping method and shipToStore method in Scheduler.java
          //And cleanMailBox() method in s class.

          //The following is my test code.
         goods = new ArrayList<>();
         mailBox = new ArrayList<>();
         inventorylist = new int[17];
         for(int i=0; i<17; i++){
             inventorylist[i]=0;
         }

         Customer test = new Customer();

         Item newItem = new Hat();
         newItem.SKU = goods.size();
         newItem.amount = 3;
         goods.add(newItem);

         Item newItem2 = new Shirt();
         newItem2.SKU = goods.size();
         newItem2.amount = 3;
         goods.add(newItem2);

         Item newItem3 = new CDPlayer();
         newItem.SKU = goods.size();
         newItem3.amount = 3;
         goods.add(newItem3);

     }

     public int getSKU(Item item){
         int SKU = -1;
         switch (item.type){
             case "PaperScore": SKU = 0; break;
             case "CD": SKU = 1; break;
             case "Vinyl": SKU = 2; break;
             case "CDPlayer": SKU = 3; break;
             case "RecordPlayer": SKU = 4; break;
             case "MP3": SKU = 5; break;
             case "Guitar": SKU = 6; break;
             case "Bass": SKU = 7; break;
             case "Mandolin": SKU = 8; break;
             case "Flute": SKU = 9; break;
             case "Harmonica": SKU = 10; break;
             case "Hat": SKU = 11; break;
             case "Shirt": SKU = 12; break;
             case "Bandana": SKU = 13; break;
             case "PracticeAmp": SKU = 14; break;
             case "Cable": SKU = 15; break;
             case "StringAcc": SKU = 16; break;
             default: SKU = -1; break;
         }
         return SKU;
     }

    public static String SKUitemclass(int SKU){
        String item;
        switch (SKU){
            case 0: item = "PaperScore"; break;
            case 1: item = "CD"; break;
            case 2: item = "Vinyl"; break;
            case 3: item = "CDPlayer"; break;
            case 4: item = "RecordPlayer"; break;
            case 5: item = "MP3"; break;
            case 6: item = "Guitar"; break;
            case 7: item = "Bass"; break;
            case 8: item = "Mandolin"; break;
            case 9: item = "Flute"; break;
            case 10: item = "Harmonica"; break;
            case 11: item = "Hat"; break;
            case 12: item = "Shirt"; break;
            case 13: item = "Bandana"; break;
            case 14: item = "PracticeAmp"; break;
            case 15: item = "Cable"; break;
            case 16: item = "StringAcc"; break;
            default: item = "undefined"; break;
        }
        return item;
    }

     //Add all shipped things to the goods list.
     private void cleanMailBox(){
          mailBox.forEach(entry->
               goods.forEach(item -> {
                    if(item.SKU == entry.get(0)) {
                         item.amount += 3;
                         item.purchasePrice = entry.get(2);
                    }
               })
          );
          mailBox.clear();
     }

     private void checkMoney(){
          if(register<75){
               scheduler.sendMessage("insufficient_money",null);
          }
          else{
               scheduler.sendMessage("sufficient_money",null);
          }
     }

     void add_1000(){
          register += 1000;
          System.out.println("Cash register now has: "+register);
     }

    //return the user the goods list.
     void sumInventory(ArrayList<Item> input){
          input.addAll(goods);
     }

     @Override
     public void receiveMessage(String event,ArrayList<Item> input) {
          switch (event) {
               case "checkMail" -> cleanMailBox();
               case "checkRegister" -> checkMoney();
               case "add_1000" -> add_1000();
               case "checkInventory"-> sumInventory(input);
          }
     }

}
