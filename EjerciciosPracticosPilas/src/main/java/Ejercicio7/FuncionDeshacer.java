/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

/**
 * Simulación de función Deshacer (Undo) usando una pila
 * Permite deshacer acciones en orden LIFO (Last In First Out)
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */


import java.util.Scanner;

public class FuncionDeshacer {
    
    static class MiPilaString {
        private Nodo tope;
        private int tamaño;
        
        class Nodo {
            String accion;
            Nodo siguiente;
            
            public Nodo(String accion) {
                this.accion = accion;
                this.siguiente = null;
            }
        }
        
        public MiPilaString() {
            this.tope = null;
            this.tamaño = 0;
        }
        
        public void push(String accion) {
            Nodo nuevo = new Nodo(accion);
            nuevo.siguiente = tope;
            tope = nuevo;
            tamaño++;
            System.out.println("✓ Acción agregada: '" + accion + "'");
        }
        
        public String pop() {
            if (isEmpty()) {
                return null;
            }
            String accion = tope.accion;
            tope = tope.siguiente;
            tamaño--;
            return accion;
        }
        
        public boolean isEmpty() {
            return tope == null;
        }
        
        public int size() {
            return tamaño;
        }
        
        public void mostrarHistorial() {
            if (isEmpty()) {
                System.out.println("No hay acciones en el historial");
                return;
            }
            
            System.out.println("\n--- HISTORIAL DE ACCIONES ---");
            Nodo actual = tope;
            int numero = 1;
            while (actual != null) {
                System.out.println(numero + ". " + actual.accion);
                actual = actual.siguiente;
                numero++;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiPilaString historial = new MiPilaString();
        
        System.out.println("=== EJERCICIO 7: FUNCIÓN DESHACER ===");
        System.out.println("Comandos:");
        System.out.println("- Escribe cualquier texto para agregar acción");
        System.out.println("- Escribe 'UNDO' para deshacer última acción"); 
        System.out.println("- Escribe 'HISTORIAL' para ver todas las acciones");
        System.out.println("- Escribe 'FIN' para terminar");
        
        while (true) {
            System.out.print("\n>>> ");
            String entrada = sc.nextLine();
            
            if (entrada.equalsIgnoreCase("FIN")) {
                break;
            }
            else if (entrada.equalsIgnoreCase("UNDO")) {
                String accionDeshecha = historial.pop();
                if (accionDeshecha != null) {
                    System.out.println("✗ Deshecho: '" + accionDeshecha + "'");
                } else {
                    System.out.println("No hay acciones para deshacer");
                }
            }
            else if (entrada.equalsIgnoreCase("HISTORIAL")) {
                historial.mostrarHistorial();
            }
            else {
                historial.push(entrada);
            }
            
            System.out.println("Acciones en pila: " + historial.size());
        }
        
        System.out.println("\n--- PROGRAMA TERMINADO ---");
        System.out.println("Total de acciones realizadas: " + historial.size());
        sc.close();
    }
}