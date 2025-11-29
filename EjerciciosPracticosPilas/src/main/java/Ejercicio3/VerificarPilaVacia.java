/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

/**
 * Demostración de verificación de estado de pila vacía
 * Muestra el uso del método isEmpty() en una pila personalizada
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */
 
import java.util.Scanner;

public class VerificarPilaVacia {
    
    static class MiPila {
        private Nodo tope;
        private int tamaño;
        
        class Nodo {
            int dato;
            Nodo siguiente;
            
            public Nodo(int dato) {
                this.dato = dato;
                this.siguiente = null;
            }
        }
        
        public MiPila() {
            this.tope = null;
            this.tamaño = 0;
        }
        
        public void push(int dato) {
            Nodo nuevo = new Nodo(dato);
            nuevo.siguiente = tope;
            tope = nuevo;
            tamaño++;
        }
        
        public int pop() {
            if (isEmpty()) return -1;
            int dato = tope.dato;
            tope = tope.siguiente;
            tamaño--;
            return dato;
        }
        
        public boolean isEmpty() {
            return tope == null;
        }
        
        public int size() {
            return tamaño;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiPila pila = new MiPila();
        
        System.out.println("=== EJERCICIO 3: VERIFICAR PILA VACÍA ===");
        
        System.out.println("¿La pila está vacía? " + pila.isEmpty());
        
        System.out.print("\n¿Quieres agregar un número? (s/n): ");
        String respuesta = sc.nextLine();
        
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Ingresa el número: ");
            int numero = sc.nextInt();
            pila.push(numero);
            
            System.out.println("\nNúmero agregado a la pila");
            System.out.println("¿La pila está vacía ahora? " + pila.isEmpty());
            System.out.println("Tamaño de la pila: " + pila.size());
        }
        
        System.out.print("\n¿Quieres eliminar un número? (s/n): ");
        sc.nextLine(); // Limpiar buffer
        respuesta = sc.nextLine();
        
        if (respuesta.equalsIgnoreCase("s")) {
            int eliminado = pila.pop();
            if (eliminado != -1) {
                System.out.println("Número eliminado: " + eliminado);
            } else {
                System.out.println("La pila está vacía, no se puede eliminar");
            }
            
            System.out.println("¿La pila está vacía ahora? " + pila.isEmpty());
        }
        
        sc.close();
    }
}
