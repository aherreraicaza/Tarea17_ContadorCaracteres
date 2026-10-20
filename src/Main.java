import java.util.Scanner;

public class Main extends Thread {
    static String texto;
    static int total = 0;
    char letra;
    int cantidad = 0;

    Main(char letra) {
        this.letra = letra;
    }

    public void run() {
        for (char caracter : texto.toCharArray()) {
            if (caracter == letra) {
                cantidad = cantidad + 1;
            }
        }
        sumar(cantidad);
    }

    static synchronized void sumar(int cantidad) {
        total = total + cantidad;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        Main[] hilos = new Main[5];

        for (int i = 0; i < 5; i = i + 1) {
            System.out.print("Consonante " + (i + 1) + ": ");
            hilos[i] = new Main(teclado.nextLine().toLowerCase().charAt(0));
        }

        System.out.print("Texto: ");
        texto = teclado.nextLine().toLowerCase().repeat(2000);

        for (Main hilo : hilos) {
            hilo.start();
        }
        for (Main hilo : hilos) {
            hilo.join();
            System.out.println(hilo.letra + ": " + hilo.cantidad);
        }
        System.out.println("Total: " + total);
    }
}
