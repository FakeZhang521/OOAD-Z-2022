import java.util.ArrayList;

class Store implements ProjectMessage{
     double register = 0;
     ArrayList<Item> goods;
     ArrayList<Item> mailBox;
     Store(){
         goods = new ArrayList<>();
         mailBox = new ArrayList<>();
         //Implement the initialization;
     }
     void cleanMailBox(){
          for (Item box : mailBox) {
               boolean flag = true;
               for (int y = 0; y < goods.size(); y++) {
                    Item current = goods.get(y);
                    if (current.SKU == box.SKU) {
                         current.amount++;
                         goods.set(y, current);
                         flag = false;
                    }
               }
               if (flag) {
                    goods.add(box);
               }
          }
          mailBox = new ArrayList<>();
     }

     void checkMoney(){
          if(register<75){
               scheduler.sendMessage("insufficient_money",null);
          }
          else{
               scheduler.sendMessage("sufficient_money",null);
          }
     }

     void add_1000(){
          register += 1000;
     }

     void sumInventory(ArrayList<Item> input){
           input = goods;
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
