import java.util.ArrayList;



class Store implements ProjectMessage{
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

     }

     private int getSKU(Item item){
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

    private String SKUitemclass(String SKU){
        String item = switch (Integer.parseInt(SKU)) {
            case 0 -> "PaperScore";
            case 1 -> "CD";
            case 2 -> "Vinyl";
            case 3 -> "CDPlayer";
            case 4 -> "RecordPlayer";
            case 5 -> "MP3";
            case 6 -> "Guitar";
            case 7 -> "Bass";
            case 8 -> "Mandolin";
            case 9 -> "Flute";
            case 10 -> "Harmonica";
            case 11 -> "Hat";
            case 12 -> "Shirt";
            case 13 -> "Bandana";
            case 14 -> "PracticeAmp";
            case 15 -> "Cable";
            case 16 -> "StringAcc";
            default -> "undefined";
        };
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
               case "getItemType" -> SKUitemclass(input);
          }
     }
    public void receiveMessage(String event,String information) {
        if ("getItemType".equals(event)) {
            SKUitemclass(information);
        }
    }

}
