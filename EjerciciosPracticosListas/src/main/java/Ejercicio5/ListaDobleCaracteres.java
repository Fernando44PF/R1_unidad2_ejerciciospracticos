/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio5;

/**
 * Lista doblemente enlazada de caracteres con ordenamiento alfabético
 * Cada nodo tiene enlaces anterior y siguiente, se ordena con burbuja * 
 * @author Fernando Miguel Olvera Juárez
 * @Septiembre 2025
 *  
 */
import java.util.Scanner;

public class ListaDobleCaracteres {
    
    static class NodoDoble {
        char caracter;
        NodoDoble anterior;
        NodoDoble siguiente;
        
        public NodoDoble(char caracter) {
            this.caracter = caracter;
            this.anterior = null;
            this.siguiente = null;
        }
    }
    
    static class ListaDoblementeEnlazada {
        private NodoDoble cabeza;
        private NodoDoble cola;
        private int tamaño;
        
        public ListaDoblementeEnlazada() {
            this.cabeza = null;
            this.cola = null;
            this.tamaño = 0;
        }
        
        // Construir lista desde cadena
        public void construirDesdeCadena(String cadena) {
            for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);
                agregarCaracter(c);
            }
        }
        
        // Agregar carácter al final
        private void agregarCaracter(char c) {
            NodoDoble nuevoNodo = new NodoDoble(c);
            
            if (cabeza == null) {
                cabeza = nuevoNodo;
                cola = nuevoNodo;
            } else {
                cola.siguiente = nuevoNodo;
                nuevoNodo.anterior = cola;
                cola = nuevoNodo;
            }
            tamaño++;
        }
        
        // Ordenamiento por burbuja
        public void ordenarAlfabeticamente() {
            if (cabeza == null || cabeza.siguiente == null) return;
            
            boolean cambiado;
            do {
                cambiado = false;
                NodoDoble actual = cabeza;
                
                while (actual.siguiente != null) {
                    if (actual.caracter > actual.siguiente.caracter) {
                        // Intercambiar caracteres
                        char temp = actual.caracter;
                        actual.caracter = actual.siguiente.caracter;
                        actual.siguiente.caracter = temp;
                        cambiado = true;
                    }
                    actual = actual.siguiente;
                }
            } while (cambiado);
        }
        
        // Mostrar lista hacia adelante
        public void mostrarHaciaAdelante() {
            NodoDoble actual = cabeza;
            System.out.print("Hacia adelante: ");
            while (actual != null) {
                System.out.print(actual.caracter + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
        
        // Mostrar lista hacia atrás
        public void mostrarHaciaAtras() {
            NodoDoble actual = cola;
            System.out.print("Hacia atrás:    ");
            while (actual != null) {
                System.out.print(actual.caracter + " ");
                actual = actual.anterior;
            }
            System.out.println();
        }
        
        // Mostrar lista ordenada
        public void mostrarListaOrdenada() {
            System.out.println("\n--- LISTA ORDENADA ALFABÉTICAMENTE ---");
            mostrarHaciaAdelante();
            mostrarHaciaAtras();
            System.out.println("Total de caracteres: " + tamaño);
        }
        
        public int getTamaño() {
            return tamaño;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== ACTIVIDAD 5: LISTA DOBLEMENTE ENLAZADA DE CARACTERES ===");
        
        // Tarea 1: Leer cadena desde teclado
        System.out.print("Ingrese una cadena de texto: ");
        String cadena = scanner.nextLine();
        
        // Tarea 2: Construir lista doblemente enlazada
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();
        lista.construirDesdeCadena(cadena);
        
        System.out.println("\n--- LISTA ORIGINAL ---");
        lista.mostrarHaciaAdelante();
        lista.mostrarHaciaAtras();
        
        // Tarea 3: Ordenamiento alfabético
        System.out.println("\nOrdenando lista alfabéticamente...");
        lista.ordenarAlfabeticamente();
        
        // Tarea 4: Mostrar lista ordenada
        lista.mostrarListaOrdenada();
        
        scanner.close();
    }
}
