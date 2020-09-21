import java.lang.reflect.Array;
import java.util.*;


class Vehicle {
    public int x;
    public int y;
    public int size;
    public int id;

    public Vehicle(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        size = x * y;
    }

    public void rotate() {
        int tmp = this.x;
        this.x = this.y;
        this.y = tmp;
    }
}

class Parking {
    public int height;
    public int width;
    int[][] place;

    public Parking(int h, int w) {
        height = h;
        width = w;
        place = new int[width][];
        for (int i = 0; i < width; i++) {
            place[i] = new int[height];

        }
    }
}

public class Main {

    public static boolean done(Parking p) {
        for (int i = 0; i < p.width; i++) {
            for (int j = 0; j < p.height; j++) {
                if (p.place[i][j] == 0)
                    return false;
            }
        }
        return true;
    }


    public static boolean sortingAlgorithm(Parking p, Vehicle v) {

        boolean empty = true;

        for (int i = 0; i < p.width; i++) {
            for (int j = 0; j < p.height; j++) {
                if (p.place[i][j] == 0 && i + v.x <= p.width && j + v.y <= p.height) {
                    for (int a = i; a < i + v.x; a++) {
                        for (int b = j; b < j + v.y; b++) {
                            if (p.place[a][b] != 0) {
                                empty = false;

                            }

                        }
                    }
                    if (empty) {
                        for (int a = i; a < i + v.x; a++) {
                            for (int b = j; b < j + v.y; b++) {
                                p.place[a][b] = v.id;
                            }
                        }
                        return true;

                    }
                }
                empty = true;
            }

        }

        return false;
    }


    public static void sort(Parking p, Vehicle v, ArrayList<Vehicle> vList) {
        if (!sortingAlgorithm(p, v)) {
            v.rotate();
            if (sortingAlgorithm(p, v)) {
                vList.add(v);
            }
            ;
        }

    }


    static class ComparatorBySize implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle a, Vehicle b) {
            return a.size < b.size ? 1 : a.size == b.size ? 0 : -1;
        }
    }

    public static void main(String[] args) {

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int width = in.nextInt();

        Parking park = new Parking(height, width);

        int pcs = in.nextInt();

        int x, y = 0;
        for (int i = 0; i < pcs; i++) {
            x = in.nextInt();
            y = in.nextInt();
            vehicles.add(new Vehicle(x, y, i + 1));
        }

        ComparatorBySize comparator = new ComparatorBySize();
        Collections.sort(vehicles, comparator);

        ArrayList<Vehicle> newList = new ArrayList<>(); //ez most nem csinál semmi hasznosat

        for (Vehicle v : vehicles) {

            sort(park, v, newList);

        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j != width - 1)
                    System.out.print(park.place[j][i] + "\t");
                else
                    System.out.print(park.place[j][i] + "\n");
            }
        }
    }
}