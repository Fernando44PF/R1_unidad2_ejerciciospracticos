/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

/**
 * Implementación de lista enlazada simple para números enteros positivos
 * Permite crear, recorrer y eliminar nodos según condición
 * 
 * @author Fernando Miguel Olvera Juárez
 * @Septiembre 2025
 *  
 */
import java.util.Scanner;
import java.util.Random;

public class ListaEnlazadaNumeros {
    
    // Clase Nodo interna y privada
    private static class Nodo {
        private int dato;
        private Nodo siguiente;
        
        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
        
        public int getDato() { return dato; }
        public Nodo getSiguiente() { return siguiente; }
        public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    }
    
    // Implementación profesional de Lista Enlazada
    public static class ListaEnlazada {
        private Nodo cabeza;
        private Nodo cola;
        private int tamaño;
        
        public ListaEnlazada() {
            this.cabeza = null;
            this.cola = null;
            this.tamaño = 0;
        }
        
        // Inserción al final - O(1) usando referencia al último nodo
        public void insertarAlFinal(int dato) {
            Nodo nuevoNodo = new Nodo(dato);
            
            if (estaVacia()) {
                cabeza = nuevoNodo;
                cola = nuevoNodo;
            } else {
                cola.setSiguiente(nuevoNodo);
                cola = nuevoNodo;
            }
            tamaño++;
        }
        
        // Recorrido de la lista
        public void recorrerLista() {
            if (estaVacia()) {
                System.out.println("La lista está vacía");
                return;
            }
            
            Nodo actual = cabeza;
            System.out.print("Lista: ");
            while (actual != null) {
                System.out.print(actual.getDato() + " → ");
                actual = actual.getSiguiente();
            }
            System.out.println("null");
        }
        
        // Eliminación de nodos mayores al valor dado
        public void eliminarMayoresQue(int valorLimite) {
            if (estaVacia()) return;
            
            // Eliminar nodos del inicio que cumplan la condición
            while (cabeza != null && cabeza.getDato() > valorLimite) {
                cabeza = cabeza.getSiguiente();
                tamaño--;
            }
            
            if (cabeza == null) {
                cola = null;
                return;
            }
            
            // Eliminar nodos del medio y final
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                if (actual.getSiguiente().getDato() > valorLimite) {
                    actual.setSiguiente(actual.getSiguiente().getSiguiente());
                    tamaño--;
                    
                    // Actualizar cola si eliminamos el último nodo
                    if (actual.getSiguiente() == null) {
                        cola = actual;
                    }
                } else {
                    actual = actual.getSiguiente();
                }
            }
        }
        
        // Métodos de utilidad
        public boolean estaVacia() { return cabeza == null; }
        public int getTamaño() { return tamaño; }
        
        @Override
        public String toString() {
            if (estaVacia()) return "Lista vacía";
            
            StringBuilder sb = new StringBuilder();
            Nodo actual = cabeza;
            while (actual != null) {
                sb.append(actual.getDato());
                if (actual.getSiguiente() != null) sb.append(" → ");
                actual = actual.getSiguiente();
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("=LISTA ENLAZADA DE NÚMEROS ENTEROS POSITIVOS=");
        
        // Crear lista enlazada
        ListaEnlazada lista = new ListaEnlazada();
        
        // 1. Generar números aleatorios e insertar al final
        System.out.print("¿Cuántos números aleatorios desea generar? ");
        int cantidad = scanner.nextInt();
        
        System.out.println("Generando " + cantidad + " números aleatorios...");
        for (int i = 0; i < cantidad; i++) {
            int numero = random.nextInt(100) + 1;
            lista.insertarAlFinal(numero);
        }
        
        // 2. Recorrer y mostrar la lista
        System.out.println("\n--- LISTA ORIGINAL ---");
        lista.recorrerLista();
        System.out.println("Tamaño: " + lista.getTamaño());
        
        // 3. Eliminar nodos mayores a un valor dado
        System.out.print("\nIngrese el valor límite para eliminar: ");
        int limite = scanner.nextInt();
        
        lista.eliminarMayoresQue(limite);
        
        System.out.println("\n--- LISTA DESPUÉS DE ELIMINAR ---");
        lista.recorrerLista();
        System.out.println("Tamaño: " + lista.getTamaño());
        
        scanner.close();
    }
}
