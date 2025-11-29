/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

/**
 * Sistema de gestión de palabras usando lista enlazada desde archivo
 * Permite leer, agregar, eliminar y guardar palabras * 
 * @author Fernando Miguel Olvera Juárez
 * @Septiembre 2025
 *  
 */
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class ListaPalabrasArchivo {
    
    private static class Nodo {
        private String palabra;
        private Nodo siguiente;
        
        public Nodo(String palabra) {
            this.palabra = palabra;
            this.siguiente = null;
        }
        
        public String getPalabra() { return palabra; }
        public Nodo getSiguiente() { return siguiente; }
        public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    }
    
    public static class ListaEnlazadaPalabras {
        private Nodo cabeza;
        private Nodo cola;
        private int tamaño;
        
        public ListaEnlazadaPalabras() {
            this.cabeza = null;
            this.cola = null;
            this.tamaño = 0;
        }
        
        // Insertar palabra al final
        public void insertarPalabra(String palabra) {
            if (palabra == null || palabra.trim().isEmpty()) return;
            
            Nodo nuevoNodo = new Nodo(palabra.trim());
            
            if (estaVacia()) {
                cabeza = nuevoNodo;
                cola = nuevoNodo;
            } else {
                cola.setSiguiente(nuevoNodo);
                cola = nuevoNodo;
            }
            tamaño++;
        }
        
        // Eliminar palabra específica
        public boolean eliminarPalabra(String palabraBuscada) {
            if (estaVacia() || palabraBuscada == null) return false;
            
            // Caso especial: eliminar la cabeza
            if (cabeza.getPalabra().equals(palabraBuscada)) {
                cabeza = cabeza.getSiguiente();
                tamaño--;
                if (cabeza == null) cola = null;
                return true;
            }
            
            // Buscar en el resto de la lista
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                if (actual.getSiguiente().getPalabra().equals(palabraBuscada)) {
                    actual.setSiguiente(actual.getSiguiente().getSiguiente());
                    tamaño--;
                    
                    if (actual.getSiguiente() == null) {
                        cola = actual;
                    }
                    return true;
                }
                actual = actual.getSiguiente();
            }
            
            return false;
        }
        
        // Recorrer y mostrar palabras
        public void mostrarPalabras() {
            if (estaVacia()) {
                System.out.println("No hay palabras en la lista");
                return;
            }
            
            Nodo actual = cabeza;
            int contador = 1;
            System.out.println("--- PALABRAS EN LA LISTA ---");
            while (actual != null) {
                System.out.printf("%2d. %s\n", contador++, actual.getPalabra());
                actual = actual.getSiguiente();
            }
            System.out.println("Total: " + tamaño + " palabras");
        }
        
        // Métodos de utilidad
        public boolean estaVacia() { return cabeza == null; }
        public int getTamaño() { return tamaño; }
        
        // Convertir a array para guardar en archivo
        public String[] toArray() {
            String[] array = new String[tamaño];
            Nodo actual = cabeza;
            int index = 0;
            
            while (actual != null) {
                array[index++] = actual.getPalabra();
                actual = actual.getSiguiente();
            }
            
            return array;
        }
    }
    
    // Métodos para manejo de archivos
    public static ListaEnlazadaPalabras cargarDesdeArchivo(String nombreArchivo) {
        ListaEnlazadaPalabras lista = new ListaEnlazadaPalabras();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(linea, " \t\n\r\f,.;:!?");
                while (tokenizer.hasMoreTokens()) {
                    lista.insertarPalabra(tokenizer.nextToken());
                }
            }
            System.out.println("Se cargaron " + lista.getTamaño() + " palabras desde el archivo.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return lista;
    }
    
    public static void guardarEnArchivo(ListaEnlazadaPalabras lista, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            String[] palabras = lista.toArray();
            for (int i = 0; i < palabras.length; i++) {
                writer.print(palabras[i]);
                if (i < palabras.length - 1) writer.print(" ");
            }
            System.out.println("Se guardaron " + palabras.length + " palabras en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombreArchivo = "palabras.txt";
        
        System.out.println("=== LISTA ENLAZADA DE PALABRAS DESDE ARCHIVO ===");
        
        // 1. Leer archivo y crear lista
        System.out.println("Cargando palabras desde '" + nombreArchivo + "'...");
        ListaEnlazadaPalabras lista = cargarDesdeArchivo(nombreArchivo);
        
        boolean ejecutar = true;
        while (ejecutar) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Mostrar todas las palabras");
            System.out.println("2. Agregar nueva palabra");
            System.out.println("3. Eliminar palabra específica");
            System.out.println("4. Guardar en archivo y salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    lista.mostrarPalabras();
                    break;
                case 2:
                    System.out.print("Ingrese la palabra a agregar: ");
                    String nueva = scanner.nextLine();
                    lista.insertarPalabra(nueva);
                    System.out.println("Palabra agregada: '" + nueva + "'");
                    break;
                case 3:
                    System.out.print("Ingrese la palabra a eliminar: ");
                    String eliminar = scanner.nextLine();
                    if (lista.eliminarPalabra(eliminar)) {
                        System.out.println("Palabra eliminada: '" + eliminar + "'");
                    } else {
                        System.out.println("La palabra '" + eliminar + "' no se encontró");
                    }
                    break;
                case 4:
                    guardarEnArchivo(lista, nombreArchivo);
                    ejecutar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        
        System.out.println("Programa terminado.");
        scanner.close();
    }
}
