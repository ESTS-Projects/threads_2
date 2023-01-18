import java.util.ArrayList;

public class Exercice4 {
    private static ArrayList<Integer> buffer = new ArrayList<Integer>();
    private static final int MAX_SIZE = 5;

    static class Producteur extends Thread {
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == MAX_SIZE) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int value = (int) (Math.random() * 100);
                    buffer.add(value);
                    System.out.println("Produced: " + value);
                    buffer.notify();
                }
            }
        }
    }

    static class Consommateur extends Thread {
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == 0) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int value = buffer.remove(buffer.size() - 1);
                    System.out.println("Consumed: " + value);
                    buffer.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Producteur producteur = new Producteur();
        Consommateur consommateur = new Consommateur();

        producteur.start();
        consommateur.start();
    }
}