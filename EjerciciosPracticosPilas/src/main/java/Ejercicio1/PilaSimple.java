/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

/**
 * Implementación básica de pila con operaciones push y pop
 * Demuestra el funcionamiento fundamental de una pila LIFO
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */
import java.util.Scanner;

public class PilaSimple {
    
    // Clase Pila
    static class MiPila {
        private Nodo tope;
        private int tamaño;
        
        // Clase Nodo interna
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
            if (isEmpty()) {
                return -1; // Para indicar error
            }
            int dato = tope.dato;
            tope = tope.siguiente;
            tamaño--;
            return dato;
        }
        
        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return tope.dato;
        }
        
        public boolean isEmpty() {
            return tope == null;
        }
        
        public int size() {
            return tamaño;
        }
        
        public void mostrar() {
            if (isEmpty()) {
                System.out.println("Pila vacía");
                return;
            }
            
            System.out.print("Pila: ");
            Nodo actual = tope;
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiPila pila = new MiPila();
        
        System.out.println("=== EJERCICIO 1: PILA SIMPLE ===");
        
        // Insertar números 
        System.out.println("Vamos a insertar 4 números a la pila:");
        for (int i = 1; i <= 4; i++) {
            System.out.print("Ingresa el número " + i + ": ");
            int num = sc.nextInt();
            pila.push(num);
        }
        
        System.out.println("\n--- Estado de la pila ---");
        pila.mostrar();
        System.out.println("Tamaño: " + pila.size());
        
        // Eliminar elementos
        System.out.print("\n¿Cuántos elementos quieres eliminar? ");
        int eliminar = sc.nextInt();
        
        for (int i = 0; i < eliminar; i++) {
            int eliminado = pila.pop();
            if (eliminado != -1) {
                System.out.println("Eliminado: " + eliminado);
            }
        }
        
        System.out.println("\n--- Estado final de la pila ---");
        pila.mostrar();
        System.out.println("Tamaño: " + pila.size());
        
        sc.close();
    }
}
