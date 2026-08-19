import java.util.*;
public class WareHouseInventory {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> inventory = new ArrayList<>();

        ArrayList<Integer> row1 = new ArrayList<>();
//        row1.add(5);
//        row1.add(12);
//        row1.add(3);

        row1.add(10);
        row1.add(3);
        row1.add(7);

        ArrayList<Integer> row2 = new ArrayList<>();
//        row2.add(8);
//        row2.add(2);
//        row2.add(15);
        row2.add(1);
        row2.add(15);
        row2.add(2);

        ArrayList<Integer> row3 = new ArrayList<>();
//        row3.add(1);
//        row3.add(20);
//        row3.add(4);

        row3.add(4);
        row3.add(5);
        row3.add(0);

        inventory.add(row1);
        inventory.add(row2);
        inventory.add(row3);

        LinkedList<Integer> custom = new LinkedList<>();
        for(int i = 0; i < inventory.size(); i++){
            for(int j = 0; j < inventory.get(i).size(); j++){
                if(inventory.get(i).get(j) < 5){
                    custom.add(inventory.get(i).get(j));
                }
            }
        }

        for(int i = 0; i < custom.size(); i++){
            custom.set(i, custom.get(i)*2);
        }

        for(int i = 0; i < inventory.size(); i++){
            for(int j = 0; j < inventory.get(i).size(); j++){
                System.out.print(inventory.get(i).get(j) + " ");
            }
        }

        System.out.println(" ");

        for (int current : custom){
            System.out.println(current + " ");
        }

    }
}