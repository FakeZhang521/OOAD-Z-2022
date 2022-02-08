import java.util.ArrayList;

class Store implements ProjectMessage{
     private double register = 0;
     private final ArrayList<Item> goods;
     final ArrayList<ArrayList<Integer>> mailBox;
     Store(){
          // TODO: 2/7/2022 Please implement the initialization part
          //For the use of SKU attribute, please check how I coded the
          //scheduleShipping method and shipToStore method in scheduler.java
          //And cleanMailBox() method in this class.

          //The following is my test code.
         goods = new ArrayList<>();
         mailBox = new ArrayList<>();

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
