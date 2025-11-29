/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

/**
 * Cola de nombres que permite ingresar nombres y procesarlos en orden FIFO
 * Implementa una cola para gestión secuencial de datos
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */

import java.util.Scanner;


public class Simulación_de_supermercado {
    
    // Clase para los clientes
    static class Cliente {
        String nombre;
        boolean tieneCarrito;
        
        Cliente(String nombre) {
            this.nombre = nombre;
            this.tieneCarrito = false;
        }
    }
    
    // Mi cola para clientes
    static class MyColaClientes {
        
        class Nodo {
            Cliente cliente;
            Nodo siguiente;
            
            Nodo(Cliente cliente) {
                this.cliente = cliente;
                this.siguiente = null;
            }
        }
        
        private Nodo frente;
        private Nodo fin;
        private int tamaño;
        
        public MyColaClientes() {
            this.frente = null;
            this.fin = null;
            this.tamaño = 0;
        }
        
        public void encolar(Cliente cliente) {
            Nodo nuevo = new Nodo(cliente);
            
            if (estaVacia()) {
                frente = nuevo;
                fin = nuevo;
            } else {
                fin.siguiente = nuevo;
                fin = nuevo;
            }
            tamaño++;
        }
        
        public Cliente desencolar() {
            if (estaVacia()) {
                return null;
            }
            
            Cliente cliente = frente.cliente;
            frente = frente.siguiente;
            tamaño--;
            
            if (frente == null) {
                fin = null;
            }
            
            return cliente;
        }
        
        public boolean estaVacia() {
            return frente == null;
        }
        
        public int tamaño() {
            return tamaño;
        }
    }
    
    // Clase principal del supermercado
    static class Supermercado {
        private int carritosDisponibles;
        private final int TOTAL_CARRITOS = 25;
        private MyColaClientes[] cajas;
        private final int NUM_CAJAS = 3;
        
        public Supermercado() {
            this.carritosDisponibles = TOTAL_CARRITOS;
            this.cajas = new MyColaClientes[NUM_CAJAS];
            
            // Inicializar las cajas
            for (int i = 0; i < NUM_CAJAS; i++) {
                cajas[i] = new MyColaClientes();
            }
        }
        
        // Cuando llega un cliente
        public void llegaCliente(String nombre) {
            Cliente cliente = new Cliente(nombre);
            
            if (carritosDisponibles > 0) {
                cliente.tieneCarrito = true;
                carritosDisponibles--;
                System.out.println(nombre + " toma un carrito. Quedan: " + carritosDisponibles);
                asignarACaja(cliente);
            } else {
                System.out.println(nombre + " espera - No hay carritos");
            }
        }
        
        // Poner al cliente en la caja con menos gente
        private void asignarACaja(Cliente cliente) {
            int cajaMenosLlena = 0;
            int menorCantidad = cajas[0].tamaño();
            
            for (int i = 1; i < NUM_CAJAS; i++) {
                if (cajas[i].tamaño() < menorCantidad) {
                    menorCantidad = cajas[i].tamaño();
                    cajaMenosLlena = i;
                }
            }
            
            cajas[cajaMenosLlena].encolar(cliente);
            System.out.println("   " + cliente.nombre + " va a la caja " + (cajaMenosLlena + 1));
        }
        
        // Atender una caja
        public void atenderCaja(int numeroCaja) {
            if (numeroCaja < 1 || numeroCaja > NUM_CAJAS) return;
            
            MyColaClientes caja = cajas[numeroCaja - 1];
            if (!caja.estaVacia()) {
                Cliente cliente = caja.desencolar();
                if (cliente.tieneCarrito) {
                    carritosDisponibles++;
                }
                System.out.println(cliente.nombre + " paga en caja " + numeroCaja + 
                                 " - Carritos: " + carritosDisponibles);
            } else {
                System.out.println("Caja " + numeroCaja + " no tiene clientes");
            }
        }
        
        // Ver cómo está todo
        public void mostrarEstado() {
            System.out.println("\n--- ESTADO ACTUAL ---");
            System.out.println("Carritos: " + carritosDisponibles + "/25");
            for (int i = 0; i < NUM_CAJAS; i++) {
                System.out.println("Caja " + (i + 1) + ": " + cajas[i].tamaño() + " clientes");
            }
        }
    }
    
    // Programa principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Supermercado supermercado = new Supermercado();
        
        System.out.println("=== SIMULACIÓN DE SUPERMERCADO ===");
        System.out.println("Hay 25 carritos y 3 cajas");
        
        int numeroCliente = 1;
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n--- ¿QUÉ QUIERES HACER? ---");
            System.out.println("1. Llega un cliente");
            System.out.println("2. Atender caja 1");
            System.out.println("3. Atender caja 2");
            System.out.println("4. Atender caja 3");
            System.out.println("5. Ver estado");
            System.out.println("6. Salir");
            System.out.print("Elige (1-6): ");
            
            int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    String cliente = "Cliente" + numeroCliente;
                    supermercado.llegaCliente(cliente);
                    numeroCliente++;
                    break;
                    
                case 2:
                    supermercado.atenderCaja(1);
                    break;
                    
                case 3:
                    supermercado.atenderCaja(2);
                    break;
                    
                case 4:
                    supermercado.atenderCaja(3);
                    break;
                    
                case 5:
                    supermercado.mostrarEstado();
                    break;
                    
                case 6:
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("Opción no válida");
            }
        }
        
        System.out.println("\n--- FIN DE LA SIMULACIÓN ---");
        supermercado.mostrarEstado();
        sc.close();
    }
}