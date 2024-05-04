import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        System.out.printf(" This is format String %-.10s \n", 10);
        // GUI
        // String name = JOptionPane.showInputDialog("Enter your name: ");
        // JOptionPane.showMessageDialog(null, "Hello " + name);

        // int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age: "));
        // JOptionPane.showMessageDialog(null, "Hello " + age);

        // double height = Double.parseDouble(JOptionPane.showInputDialog("Enter your
        // height: "));
        // JOptionPane.showMessageDialog(null, "Hello " + height);

        // MATH
        // int a = 9, b = 101;

        // int z = Math.max(a, b);
        // System.out.println(z);

        // int z1 = Math.min(a, b);
        // System.out.println(z1);

        // double z2 = Math.abs((double) b);
        // System.out.println(z2);

        // int z3 = (int) Math.sqrt((double) b);
        // System.out.println(z3);

        // double z4 = Math.round(b); // làm tròn .5
        // System.out.println(z4);

        // double z5 = Math.ceil(-10.9);// làm tròn số > 0
        // System.out.println(z5);

        // double z6 = Math.floor(10.9);// làm tròn số < 0
        // System.out.println(z6);

        // double z7 = Math.pow(2, 3);
        // System.out.println(z7);

        // RANDOM
        // Random random = new Random();
        // for (int i = 0; i < 5; i++) {
        // int x = random.nextInt(10);
        // double y = random.nextDouble();
        // boolean z = random.nextBoolean();
        // System.out.printf("%d, %f, %b, %d\n", x, y, z, i);
        // }

        // ARRAY 1D
        // String cars[] = { "Mercedes", "BMW", "Audi" };

        // for (String car : cars) {
        // System.out.println(car);
        // }

        // ARRAY 2D

        // String cars[][] = {
        // { "Mercedes", "BMW", "Audi" },
        // { "Toyota", "Honda", "Nissan" },
        // { "Ford", "Chevrolet", "GMC" },
        // { "Mazda", "Hyundai", "Kia" }
        // };
        // for (int i = 0; i < cars.length; i++) {
        // for (int j = 0; j < cars[i].length; j++) {
        // System.out.printf("%s,%s,%d,%s,%d,%s", cars[i][j], "[", i, "],[", j, "]");
        // System.out.println();
        // }
        // }

        // STRING

        // String name = "NGUYEN THANH TRONG";

        // boolean result = name.equalsIgnoreCase("Nguyen Thanh Trong"); // Tìm chuỗi có
        // giống (không tính hoa/thường)
        // int result = name.length();
        // char result = name.charAt(0); // Trả về Chữ của vị trí truyền vào
        // int result = name.indexOf("Trong"); // Trả về Vị trí của chữ truyền vào
        // String result = name.toUpperCase();// Chuyển chuỗi thành chữ In Hoa
        // String result = name.toLowerCase();// Chuyển chuỗi này chữ In Thường
        // String result = name.trim();// Xóa khoảng trắng đầu và cuối trong chuỗi
        // String result = name.replace(" ", "-");// Thay thế chuối này bằng chuỗi khác
        // System.out.println(result);

        // ARRAY LIST 1D
        // ArrayList<String> food = new ArrayList<String>();

        // food.add("Pizza");
        // food.add("Burger");
        // food.add("Pasta");
        // food.add("Risotto");

        // food.remove(1);

        // System.out.println(food.get(1) + "\n");

        // food.set(1, "Salad");

        // food.clear();

        // System.out.println(food.size() + "\n");

        // for (String f : food) {
        // System.out.println(f);
        // }
        // System.out.println();
        // for (int i = 0; i < food.size(); i++) {
        // System.out.println(food.get(i));
        // }
        // System.out.println();

        // ARRAY LIST 2D
        // ArrayList<String> bakeryList = new ArrayList<String>();
        // bakeryList.add("Pasta");
        // bakeryList.add("Garlic bread");
        // bakeryList.add("donut");

        // ArrayList<String> produceList = new ArrayList<String>();
        // produceList.add("Tomatoes");
        // produceList.add("zucchini");
        // produceList.add("peppers");

        // ArrayList<String> drinkList = new ArrayList<String>();
        // drinkList.add("Water");
        // drinkList.add("Juice");
        // drinkList.add("Soda");

        // ArrayList<ArrayList<String>> List = new ArrayList<>();
        // List.add(bakeryList);
        // List.add(produceList);
        // List.add(drinkList);
        // System.out.println(List.get(0));
        // System.out.println(List.get(0).get(0));

        // for (int i = 0; i < List.size(); i++) {
        // for (int j = 0; j < List.get(i).size(); j++) {
        // System.out.println(List.get(i).get(j));
        // }
        // }
        // System.out.println();
        // for (ArrayList<String> list : List) {
        // for (String item : list) {
        // System.out.println(item);
        // }
        // }
        // String name = "trong";
        // sayHello(name);
        // int x = 10, y = 9, z = 20;
        // System.out.println(add(x, y));
        // System.out.println(add(x, y, z));
        // printf();
        final double PI = 3.14159;
        System.out.println(PI);
        System.err.println(MAX);
    }

    // Method
    static void sayHello(String name) {
        // JOptionPane.showMessageDialog(null, "Hello " + name);
        System.out.println("Hello " + name);
    }

    // Method
    static int add(int x, int y) {
        return x + y;
    }

    // Overload Method
    static int add(int x, int y, int z) {
        return x + y + z;
    }

    // PRINTF
    static void printf() {
        // [ conversion character ]
        System.out.printf("%s This is format String \n", 10);
        System.out.printf("This is format Integer %d\n", 10);
        System.out.printf("This is format Float %f\n", 10.0);
        System.out.printf("This is format String %b\n", false);

        // [ width ]
        System.out.printf("%-10s This is format String \n", 10);
        System.out.printf("%10s This is format String \n", 10);
        // [precision]
        System.out.printf("%-10.2f This is format String \n", 10.0);// 10.00 This...
        System.out.printf("%10.4f This is format String \n", 10.0);// 10.0000 This...
        System.out.printf("%+f This is format String \n", 10.0);// +10.000000 This...
        System.out.printf("%020f This is format String \n", 10.0);// -10.00 This...
    }

    // final
    static final int MAX = 10;

}