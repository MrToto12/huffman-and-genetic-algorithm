
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ejemplo
        String inputText = "aaaaaaabbbbbbcc";


        System.out.println("Mensaje Ejemplo: " + inputText);
        String encodedMessage = HuffmanAlgorithm.huffmanEncode(inputText);
        System.out.println("Mensaje de ejemplo Codificado: " + encodedMessage);
        System.out.println("Bits Utilizados: " + encodedMessage.length());

        //Ingreso por consola
        System.out.println("\nIngrese el texto a codificar: ");
        String inputText2 = scanner.nextLine();

        System.out.println("Mensaje Original: " + inputText2);
        String encodedMessage2 = HuffmanAlgorithm.huffmanEncode(inputText2);
        System.out.println("Mensaje codificado: " + encodedMessage2);
        System.out.println("Bits Utilizados: " + encodedMessage2.length());
    }

}