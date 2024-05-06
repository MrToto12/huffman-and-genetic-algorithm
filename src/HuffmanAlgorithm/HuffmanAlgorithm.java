package HuffmanAlgorithm;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanAlgorithm {

    public static String huffmanEncode(String input) {
        // Paso 1: Calcular la frecuencia de cada carácter en la cadena de entrada
        // usando la clase HashMap
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            // FrequencyMap deberia contar la cantidad de veces que aparece cada caracter
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Paso 2: Construir la cola de prioridad (min heap) usando los nodos Huffman
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            // agrega a la cola de prioridad c (el caracter) y su frecuencia (la cantidad de
            // veces que aparece))
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        // Paso 3: Construir el árbol de Huffman
        // construimos el arbol con el menor de los nodos a la derecha(lo que significa
        // que el prefijo del caracter que aparece mas veces en la String original
        // deberia empezar con un 0 al codificarlo)
        while (priorityQueue.size() > 1) {
            // priorityQueue.poll() devuelve el nodo con el menor peso
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            // Armamos el nodo combinado con los dos nodos anteriores, '\0' representa el
            // caracter vacio (Al ser un nodo interno, por definicion de Arbol de Huffman no
            // debe tener un caracter almacenado)
            HuffmanNode combinedNode = new HuffmanNode('\0', left.frequency + right.frequency);
            // asignamos los hijos al nodo combinado
            combinedNode.left = left;
            combinedNode.right = right;
            // agregamos el nodo a la cola
            priorityQueue.add(combinedNode);
        }

        // Paso 4: Construir el mapa de codificación
        HashMap<Character, String> encodingMap = new HashMap<>();
        // Funcion definida abajo
        buildEncodingMap(priorityQueue.peek(), "", encodingMap);

        // Paso 5: Codificar el mensaje original
        StringBuilder encodedMessage = new StringBuilder();
        // Volvemos a recorrer el String original
        for (char c : input.toCharArray()) {
            // Buscamos el prefijo en el mapa de codificación ya generado
            encodedMessage.append(encodingMap.get(c));
        }
        System.out.println("Valores de codificado: " + encodingMap);
        return encodedMessage.toString();
    }

    private static void buildEncodingMap(HuffmanNode node, String currentCode, HashMap<Character, String> encodingMap) {
        if (node != null) {
            // Si el nodo es hoja, le asignamos el codigo correspondiente
            if (node.left == null && node.right == null) {
                encodingMap.put(node.character, currentCode);
            }
            // Seteamos 0 o 1 dependiendo si esta a la izquierda o derecha del nodo
            buildEncodingMap(node.left, currentCode + "0", encodingMap);
            buildEncodingMap(node.right, currentCode + "1", encodingMap);
        }
    }
}
