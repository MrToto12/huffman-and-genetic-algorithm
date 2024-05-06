package GeneticAlgorithm;

import java.util.Scanner;

public class GeneticMain {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        double tasaMutacion = 0;
        int tamanioPoblacion = 0;
        int opcionReemplazo = 0;
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico();

        //Menu
        while (true){
            System.out.println("Ingrese la tasa de mutacion (Entre 0 y 1, cualquier numero por encima de 1 significa un 100%): ");
            try {
                tasaMutacion = Double.parseDouble(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Porfavor ingrese un numero valido.");
                continue;
            }
            System.out.println("Ingrese el tamaño de la poblacion (Debe ser un numero par): ");
            try {
                tamanioPoblacion = Integer.parseInt(scanner.nextLine());
                if(tamanioPoblacion % 2 != 0){
                    System.out.println("Porfavor ingrese un numero par");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Porfavor ingrese un numero valido.");
                continue;
            }
            System.out.println("Seleccione la opcion de reemplazo: ");
            System.out.println("1. Reemplazar al peor de la poblacion");
            System.out.println("2. Algoritmo de Crowding Deterministico (DC)");
            try {
                opcionReemplazo = Integer.parseInt(scanner.nextLine());
                if(!(opcionReemplazo <= 2 && opcionReemplazo > 0)){
                    System.out.println("Porfavor ingrese una opcion valida (1 o 2)");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Porfavor ingrese un numero valido.");
                continue;
            }
            break;
        }

        int iteraciones = algoritmoGenetico.simularAlgoritmoGenetico(20,40,1000000, tasaMutacion, tamanioPoblacion, opcionReemplazo);

        System.out.println("Iteraciones necesarias: " + iteraciones);
        System.out.println("Cantidad de reemplazos realizados: " + algoritmoGenetico.cantReemplazos);
    }
}

