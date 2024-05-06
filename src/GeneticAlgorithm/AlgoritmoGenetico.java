package GeneticAlgorithm;

import java.util.Random;

public class AlgoritmoGenetico {
    public int cantReemplazos = 0;

    // Función objetivo H(x) = Σ x*2
    public int funcionObjetivo(int[] cadena) {
        int resultado = 0;
        for (int i = 0; i < cadena.length; i++) {
            resultado += cadena[i] * 2;
        }
        return resultado;
    }

    // Genera una cadena de caracteres aleatoria de longitud especificada
    public int[] generarCadenaAleatoria(int longitud) {
        int[] cadena = new int[longitud];
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            cadena[i] = random.nextInt(2); // Genera 0 o 1
        }
        return cadena;
    }

    // Realiza una mutación en la cadena
    public void mutarCadena(int[] cadena, double tasaMutacion) {
        Random random = new Random();
        for (int i = 0; i < cadena.length; i++) {
            if (random.nextDouble() < tasaMutacion) {
                cadena[i] = 1 - cadena[i]; // Cambia 0 a 1 o 1 a 0
            }
        }
    }

    // Realiza un cruce entre dos cadenas
    public int[] cruzarCadenas(int[] padre1, int[] padre2) {
        int puntoCruce = new Random().nextInt(padre1.length);
        int[] descendiente = new int[padre1.length];

        for (int i = 0; i < puntoCruce; i++) {
            descendiente[i] = padre1[i];
        }

        for (int i = puntoCruce; i < padre1.length; i++) {
            descendiente[i] = padre2[i];
        }

        return descendiente;
    }

    // Método que realiza la simulación del algoritmo genético
    public int simularAlgoritmoGenetico( int longitudCadena, int objetivo, int maxIteraciones, double tasaMutacion, int tamanoPoblacion, int opcionReemplazo) {
        // Inicialización: generación aleatoria de la población inicial
        int[][] poblacion = new int[tamanoPoblacion][longitudCadena];
        for (int i = 0; i < tamanoPoblacion; i++) {
            poblacion[i] = generarCadenaAleatoria(longitudCadena);
        }

        // Iteraciones del algoritmo genético
        for (int iteracion = 0; iteracion < maxIteraciones; iteracion++) {
            // Evaluación de la población
            int[] valoresPoblacion = new int[tamanoPoblacion];
            for (int i = 0; i < tamanoPoblacion; i++) {
                valoresPoblacion[i] = funcionObjetivo(poblacion[i]);
            }

            // Mostrar mejor cadena actual y su valor
            int mejorIndice = obtenerMejorIndice(valoresPoblacion);
            int mejorValor = valoresPoblacion[mejorIndice];
            System.out.println("Iteración " + (iteracion + 1) + ": " + cadenaToString(poblacion[mejorIndice]) +
                    ", H(x) = " + mejorValor);

            // Comprobar si hemos alcanzado el objetivo
            if (mejorValor == objetivo) {
                System.out.println("¡Solución óptima encontrada!");
                return iteracion+1;
//                break;
            }

            // Aplicar cruce y mutación a la población
            for (int i = 0; i < tamanoPoblacion; i += 2) {
                int[] descendiente = cruzarCadenas(poblacion[i], poblacion[i + 1]);
                mutarCadena(descendiente, tasaMutacion);

                switch (opcionReemplazo){
                    case 1:
                        poblacion = reemplazoRW(valoresPoblacion, poblacion, descendiente);
                        break;
                    case 2:
                        poblacion = reemplazoDC(valoresPoblacion, poblacion, descendiente);
                        break;
                }
            }
        }
        return -1;
    }

    // Método auxiliar para imprimir una cadena
    public String cadenaToString(int[] cadena) {
        StringBuilder sb = new StringBuilder();
        for (int bit : cadena) {
            sb.append(bit).append(" ");
        }
        return sb.toString().trim();
    }

    // Método auxiliar para obtener el índice del individuo con el mejor valor en la población
    public int obtenerMejorIndice(int[] valores) {
        int mejorIndice = 0;
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] > valores[mejorIndice]) {
                mejorIndice = i;
            }
        }
        return mejorIndice;
    }

    // Método auxiliar para obtener el índice del individuo con el peor valor en la población
    public int obtenerPeorIndice(int[] valores) {
        int peorIndice = 0;
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] < valores[peorIndice]) {
                peorIndice = i;
            }
        }
        return peorIndice;
    }

    // Función de reemplazo al peor de la población (RW)
    private int[][] reemplazoRW(int[] valoresPoblacion, int[][]poblacion, int[] descendiente ){
        // Reemplazar a los peores individuos en la población
        int peorIndice = obtenerPeorIndice(valoresPoblacion);
        if(funcionObjetivo(descendiente) <= funcionObjetivo(poblacion[peorIndice])){
            //Si es menor o igual no lo cambio, como esta expresado en la consigna.
            return poblacion;
        } else{
            this.cantReemplazos++;
            poblacion[peorIndice] = descendiente;
            return poblacion;
        }

    }

    // Función de reemplazo mediante Algoritmo de Crowding Determinístico (DC)
    public int[][] reemplazoDC(int[] valoresPoblacion, int[][]poblacion, int[] descendiente) {
        // Seleccionar al padre más parecido al descendiente
        int padreMasParecido = seleccionarPadreMasParecido(poblacion, descendiente);

        if(funcionObjetivo(descendiente) <= funcionObjetivo(poblacion[padreMasParecido])){
            //Si es menor o igual no lo cambio, como esta expresado en la consigna.
            return poblacion;
        } else{
            // Reemplazar al padre más parecido con el descendiente
            this.cantReemplazos++;
            poblacion[padreMasParecido] = descendiente;
            return poblacion;
        }
    }

    // Función auxiliar para seleccionar al padre más parecido al descendiente
    private int seleccionarPadreMasParecido(int[][] poblacion, int[] descendiente) {
        int indicePadreMasParecido = 0;
        int valorMasParecido = Integer.MAX_VALUE;

        for (int i = 0; i < poblacion.length; i++) {
            int valorPadre = funcionObjetivo(poblacion[i]);
            int diferencia = Math.abs(valorPadre - funcionObjetivo(descendiente));

            if (diferencia < valorMasParecido) {
                valorMasParecido = diferencia;
                indicePadreMasParecido = i;
            }
        }

        return indicePadreMasParecido;
    }
}
