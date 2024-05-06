import GeneticAlgorithm.GeneticMain;
import java.util.Scanner;
import HuffmanAlgorithm.HuffmanMain;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while(opcion != 3){
            System.out.println("Ingrese una opción a continuación");
            System.out.println("1. Ejecutar Algoritmo de Huffmann");
            System.out.println("2. Ejecutar Algoritmo Genético");
            System.out.println("3. Salir del programa");
            try {
                opcion = scanner.nextInt();
                if(opcion > 3 || opcion < 1){
                    throw new RuntimeException("Invalid Option");
                }

            } catch (Exception e) {
                System.out.println("Porfavor ingrese un numero valido.\n");
                scanner.nextLine();
                opcion = 0;
                continue;
            }
            switch (opcion){
                case 1:
                    HuffmanMain.start();
                    break;
                case 2:
                    GeneticMain.start();
                    break;
                case 3:
                    return;
                default:
                    System.exit(1);
            }
        }

    }
}
