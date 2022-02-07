import java.util.ArrayList;

class Staff implements ProjectMessage{
      String name;
      int dayWorked;
      boolean workToday = false;
      Staff(String name){
            this.name = name;
            dayWorked = 0;
      }

      void ArriveAtStore(){
            dayWorked ++;
            System.out.println(name+" arrives at the store on Day "+scheduler.getday());
            scheduler.sendMessage("checkMail",null);
            CheckRegister();
      }
      void CheckRegister(){
             scheduler.sendMessage("checkRegister",null);
            System.out.println(name+" checks cash register");
      }
      void GoToBank(){
            scheduler.withdrawMoney();
            scheduler.sendMessage("add_1000",null);
            System.out.println(name + " goes to take some money from the bank");
      }
      void DoInventory(){
            ArrayList<Item> buffer = new ArrayList<>();
            scheduler.sendMessage("checkInventory",buffer);
            int totalValue = 0;
            for (Item item : buffer) {
                  if (item.purchasePrice == 0) {
                        System.out.println(item.name + " is out of stock");
                        PlaceAnOrder(item);
                  }else {
                        totalValue += item.purchasePrice;
                  }
            }
            System.out.println(name+" has announced that the total value of inventory is: "+totalValue);

      }

      void PlaceAnOrder(Item item){
            scheduler.scheduleShipping(item);
      }

      void OpenTheStore(){

      }

      @Override
      public void receiveMessage(String event, ArrayList<Item> input) {
            if(!workToday)return;
            switch (event) {
                  case "work" -> ArriveAtStore();
                  case "insufficient_money" -> GoToBank();
                  case "sufficient_money" -> DoInventory();
            }
      }
}
