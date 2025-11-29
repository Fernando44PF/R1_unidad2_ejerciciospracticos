/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio9;

/**
 * Utiliza una pila para revertir el orden de una lista de números
 * Demuestra el uso de pilas para operaciones de reversión de secuencias
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */

 
import java.util.Scanner;

public class ReveritrLista {
    
    static class MiPila {
        private Nodo tope;
        private int tamaño;
        
        class Nodo {
            int numero;
            Nodo siguiente;
            
            public Nodo(int numero) {
                this.numero = numero;
                this.siguiente = null;
            }
        }
        
        public MiPila() {
            this.tope = null;
            this.tamaño = 0;
        }
        
        public void push(int numero) {
            Nodo nuevo = new Nodo(numero);
            nuevo.siguiente = tope;
            tope = nuevo;
            tamaño++;
        }
        
        public int pop() {
            if (isEmpty()) {
                return -1;
            }
            int numero = tope.numero;
            tope = tope.siguiente;
            tamaño--;
            return numero;
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
        
        System.out.println("=== EJERCICIO 9: REVERTIR LISTA ===");
        
        System.out.print("¿Cuántos números quieres en tu lista? ");
        int cantidad = sc.nextInt();
        
        int[] listaOriginal = new int[cantidad];
        MiPila pila = new MiPila();
        
        // Leer números de la lista
        System.out.println("\nIngresa los " + cantidad + " números:");
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Número " + (i+1) + ": ");
            int numero = sc.nextInt();
            listaOriginal[i] = numero;
            pila.push(numero);
        }
        
        // Mostrar lista original
        System.out.println("\n--- LISTA ORIGINAL ---");
        System.out.print("Lista: ");
        for (int i = 0; i < listaOriginal.length; i++) {
            System.out.print(listaOriginal[i]);
            if (i < listaOriginal.length - 1) {
                System.out.print(", ");
            }
        }
        
        // Mostrar proceso de reversión
        System.out.println("\n\n--- PROCESO DE REVERSIÓN ---");
        System.out.println("Apilando números...");
        for (int numero : listaOriginal) {
            System.out.println("Apilado: " + numero);
        }
        
        System.out.println("\nDesapilando números...");
        System.out.print("Lista invertida: ");
        int[] listaInvertida = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            int numero = pila.pop();
            listaInvertida[i] = numero;
            System.out.print(numero);
            if (i < cantidad - 1) {
                System.out.print(", ");
            }
        }
        
        // Mostrar comparación
        System.out.println("\n\n--- COMPARACIÓN ---");
        System.out.print("Original:  [");
        for (int i = 0; i < listaOriginal.length; i++) {
            System.out.print(listaOriginal[i]);
            if (i < listaOriginal.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.print("Invertida: [");
        for (int i = 0; i < listaInvertida.length; i++) {
            System.out.print(listaInvertida[i]);
            if (i < listaInvertida.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        sc.close();
    }
}