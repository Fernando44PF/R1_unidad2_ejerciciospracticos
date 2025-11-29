/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

/**
 * Cola de números enteros con interfaz de usuario interactiva
 * Permite encolar, desencolar y consultar el estado de la cola
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */

import java.util.Scanner;


public class Simulación_de_atencion_al_cliente {
    
    // Clase para los clientes
    static class Cliente {
        int id;
        int minutoLlegada;
        int tiempoAtencion;
        
        Cliente(int id, int minutoLlegada, int tiempoAtencion) {
            this.id = id;
            this.minutoLlegada = minutoLlegada;
            this.tiempoAtencion = tiempoAtencion;
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
        private int maxTamano;
        
        public MyColaClientes() {
            this.frente = null;
            this.fin = null;
            this.tamaño = 0;
            this.maxTamano = 0;
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
            
            if (tamaño > maxTamano) {
                maxTamano = tamaño;
            }
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
        
        public int getMaxTamano() {
            return maxTamano;
        }
    }
    
    // Clase para las cajas
    static class Caja {
        String nombre;
        Cliente clienteActual;
        int minutoFin;
        int clientesAtendidos;
        
        Caja(String nombre) {
            this.nombre = nombre;
            this.clienteActual = null;
            this.minutoFin = -1;
            this.clientesAtendidos = 0;
        }
        
        boolean estaLibre() {
            return clienteActual == null;
        }
        
        void atenderCliente(Cliente cliente, int minutoActual) {
            this.clienteActual = cliente;
            this.minutoFin = minutoActual + cliente.tiempoAtencion;
        }
        
        boolean terminarAtencion(int minutoActual) {
            if (clienteActual != null && minutoActual >= minutoFin) {
                clientesAtendidos++;
                clienteActual = null;
                return true;
            }
            return false;
        }
    }
    
    // Sistema principal de simulación
    static class SistemaAtencion {
        private MyColaClientes fila;
        private Caja[] cajas;
        private int totalAtendidos;
        private int maxEspera;
        private Integer minutoCuartaCaja;
        private boolean cuartaAbierta;
        private int minutoActual;
        private final int TOTAL_MINUTOS = 420; // 7 horas
        private int idCliente;
        
        public SistemaAtencion() {
            this.fila = new MyColaClientes();
            this.cajas = new Caja[4];
            
            // Crear las 3 cajas normales
            for (int i = 0; i < 3; i++) {
                cajas[i] = new Caja("Caja " + (i + 1));
            }
            cajas[3] = new Caja("Caja 4");
            
            this.totalAtendidos = 0;
            this.maxEspera = 0;
            this.minutoCuartaCaja = null;
            this.cuartaAbierta = false;
            this.minutoActual = 0;
            this.idCliente = 1;
        }
        
        // Avanzar un minuto
        public void avanzarMinuto() {
            minutoActual++;
            
            // Llegan clientes (50% de probabilidad)
            if (Math.random() < 0.5 && minutoActual <= TOTAL_MINUTOS) {
                int tiempoAtencion = 2 + (int)(Math.random() * 7); // 2-8 minutos
                Cliente nuevo = new Cliente(idCliente, minutoActual, tiempoAtencion);
                fila.encolar(nuevo);
                System.out.println("Llega Cliente " + idCliente + " (atiende en " + tiempoAtencion + " min)");
                idCliente++;
            }
            
            // Abrir cuarta caja si hay mucha gente
            if (!cuartaAbierta && fila.tamaño() > 20) {
                cuartaAbierta = true;
                minutoCuartaCaja = minutoActual;
                System.out.println("¡SE ABRE LA CUARTA CAJA! (Minuto " + minutoActual + ")");
            }
            
            // Atender clientes
            atenderClientes();
            
            // Mostrar cada media hora
            if (minutoActual % 30 == 0) {
                mostrarEstado();
            }
        }
        
        private void atenderClientes() {
            int cajasActivas = cuartaAbierta ? 4 : 3;
            
            // Primero ver si terminaron atención
            for (int i = 0; i < cajasActivas; i++) {
                if (cajas[i].terminarAtencion(minutoActual)) {
                    System.out.println(cajas[i].nombre + " terminó de atender");
                }
            }
            
            // Luego asignar nuevos clientes
            for (int i = 0; i < cajasActivas; i++) {
                if (cajas[i].estaLibre() && !fila.estaVacia()) {
                    Cliente cliente = fila.desencolar();
                    cajas[i].atenderCliente(cliente, minutoActual);
                    
                    int espera = minutoActual - cliente.minutoLlegada;
                    if (espera > maxEspera) {
                        maxEspera = espera;
                    }
                    
                    System.out.println(cajas[i].nombre + " atiende Cliente " + cliente.id + 
                                     " (esperó " + espera + " min)");
                    totalAtendidos++;
                }
            }
        }
        
        public void mostrarEstado() {
            System.out.println("\n--- Minuto " + minutoActual + " ---");
            System.out.println("Clientes en fila: " + fila.tamaño());
            System.out.println("Total atendidos: " + totalAtendidos);
            System.out.println("Cajas activas: " + (cuartaAbierta ? 4 : 3));
        }
        
        public void mostrarEstadisticas() {
            System.out.println("\n=== ESTADÍSTICAS FINALES ===");
            System.out.println("Total clientes atendidos: " + totalAtendidos);
            System.out.println("Tamaño máximo de fila: " + fila.getMaxTamano());
            
            double medio = fila.getMaxTamano() > 0 ? fila.getMaxTamano() / 2.0 : 0;
            System.out.println("Tamaño medio de fila: " + String.format("%.1f", medio));
            
            System.out.println("Tiempo máximo de espera: " + maxEspera + " minutos");
            
            if (minutoCuartaCaja != null) {
                System.out.println("Cuarta caja abierta en minuto: " + minutoCuartaCaja);
            } else {
                System.out.println("No se abrió la cuarta caja");
            }
            
            // Mostrar por caja
            System.out.println("\nPor caja:");
            int activas = cuartaAbierta ? 4 : 3;
            for (int i = 0; i < activas; i++) {
                System.out.println(cajas[i].nombre + ": " + cajas[i].clientesAtendidos + " clientes");
            }
        }
        
        public boolean terminado() {
            return minutoActual >= TOTAL_MINUTOS && fila.estaVacia();
        }
    }
    
    // Programa principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAtencion sistema = new SistemaAtencion();
        
        System.out.println("=== SIMULACIÓN DE ATENCIÓN ===");
        System.out.println("Duración: 7 horas (420 minutos)");
        System.out.println("Se abre 4ta caja si hay más de 20 clientes");
        
        boolean ejecutar = true;
        
        while (ejecutar && !sistema.terminado()) {
            System.out.println("\n--- OPCIONES ---");
            System.out.println("1. Avanzar 1 minuto");
            System.out.println("2. Avanzar 5 minutos");
            System.out.println("3. Ver estadísticas");
            System.out.println("4. Terminar");
            System.out.print("Elige: ");
            
            int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    sistema.avanzarMinuto();
                    break;
                case 2:
                    for (int i = 0; i < 5; i++) {
                        sistema.avanzarMinuto();
                        if (sistema.terminado()) break;
                    }
                    break;
                case 3:
                    sistema.mostrarEstadisticas();
                    break;
                case 4:
                    ejecutar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        
        System.out.println("\n--- FIN SIMULACIÓN ---");
        sistema.mostrarEstadisticas();
        sc.close();
    }
}