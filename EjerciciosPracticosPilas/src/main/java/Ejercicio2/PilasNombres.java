/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

/**
 * Pila de nombres que permite ingresar nombres y mostrarlos en orden inverso
 * Implementa una pila LIFO para almacenamiento temporal de datos
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */
import java.util.Scanner;

public class PilasNombres  {
    
    static class MiPilaString {
        private Nodo tope;
        private int tamaño;
        
        class Nodo {
            String nombre;
            Nodo siguiente;
            
            public Nodo(String nombre) {
                this.nombre = nombre;
                this.siguiente = null;
            }
        }
        
        public MiPilaString() {
            this.tope = null;
            this.tamaño = 0;
        }
        
        public void push(String nombre) {
            Nodo nuevo = new Nodo(nombre);
            nuevo.siguiente = tope;
            tope = nuevo;
            tamaño++;
        }
        
        public String pop() {
            if (isEmpty()) {
                return null;
            }
            String nombre = tope.nombre;
            tope = tope.siguiente;
            tamaño--;
            return nombre;
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
        MiPilaString pila = new MiPilaString();
        
        System.out.println("=== EJERCICIO 2: PILA DE NOMBRES ===");
        System.out.println("Ingresa nombres (escribe 'FIN' para terminar)");
        
        int contador = 1;
        while (true) {
            System.out.print("Nombre " + contador + ": ");
            String nombre = sc.nextLine();
            
            if (nombre.equalsIgnoreCase("FIN")) {
                break;
            }
            
            pila.push(nombre);
            contador++;
        }
        
        System.out.println("\n--- Nombres en orden inverso ---");
        System.out.println("Total de nombres: " + pila.size());
        
        int numero = 1;
        while (!pila.isEmpty()) {
            String nombre = pila.pop();
            System.out.println(numero + ". " + nombre);
            numero++;
        }
        
        sc.close();
    }
}
