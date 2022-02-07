import java.util.ArrayList;
import java.util.Random;

interface ProjectMessage{
      void receiveMessage(String event,ArrayList<Item> items);
}
class scheduler{
      private static final Random random = new Random(System.currentTimeMillis());
      private static int bankMoney = 0;
      private static int day = 0;
      private static final ArrayList<Store> store = new ArrayList<>();
      private static final ArrayList<Staff> staff = new ArrayList<>();
      private static final ArrayList<Item> waitingQueue = new ArrayList<>();

      static void startEmunation() throws IllegalAccessException {
             Store store = new Store();
             Staff clerk1 = new Staff("Shaggy");
             Staff clerk2 = new Staff("Velma");
             scheduler.store.add(store);
             scheduler.staff.add(clerk1);
             scheduler.staff.add(clerk2);
             do{
                 System.out.println("DAY: "+day);
                 System.out.println("--------------------------------------------");
                 shipToStore();
                 workAssignment();
                 sendMessage("work",null);
                 oneDayPassed();
                 System.out.println("--------------------------------------------");
             }while (day != 30);
      }

      static void shipToStore(){
      }

      static void sendMessage(String event,ArrayList<Item> items){
            for (ProjectMessage tem : store) {
                  tem.receiveMessage(event,items);
            }
            for (ProjectMessage tem : staff) {
                  tem.receiveMessage(event,items);
            }
      }


      static void workAssignment() throws IllegalAccessException {
          if(staff.size() == 0)throw new IllegalAccessException("Staff list cannot be zero");
          while(true){
              int whoToWork = random.nextInt(0,staff.size());
              Staff tem = staff.get(whoToWork);
              if(tem.dayWorked<3){
                  tem.workToday = true;
                  staff.set(whoToWork,tem);
                  staff.forEach(member->{
                      if(!member.equals(tem))member.workToday = false;
                  });
                  break;
              }
          }
          staff.forEach(member->{if(member.dayWorked >= 3)member.dayWorked = 0;});
      }

      static void scheduleShipping(Item item){
           item.dayArraived = day + random.nextInt(1,4);
           item.purchasePrice = random.nextInt(1,51);
           waitingQueue.add(item);
      }

      static void oneDayPassed(){
          if(day == 30)return;
          day ++;
      }
      static int getday(){
          return day;
      }
      static void withdrawMoney(){
          bankMoney += 1000;
      }
}
