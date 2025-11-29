/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6;

/**
 * Conversión de números decimales a binarios usando una pila
 * Implementa el algoritmo de división sucesiva para conversión numérica
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */
import java.util.Scanner;

public class DecimalABinario {
    
    static class MiPila {
        private Nodo tope;
        private int tamaño;
        
        class Nodo {
            int bit;
            Nodo siguiente;
            
            public Nodo(int bit) {
                this.bit = bit;
                this.siguiente = null;
            }
        }
        
        public MiPila() {
            this.tope = null;
            this.tamaño = 0;
        }
        
        public void push(int bit) {
            Nodo nuevo = new Nodo(bit);
            nuevo.siguiente = tope;
            tope = nuevo;
            tamaño++;
        }
        
        public int pop() {
            if (isEmpty()) return -1;
            int bit = tope.bit;
            tope = tope.siguiente;
            tamaño--;
            return bit;
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
        
        System.out.println("=== EJERCICIO 6: DECIMAL A BINARIO ===");
        
        System.out.print("Ingresa un número decimal: ");
        int decimal = sc.nextInt();
        
        int numero = decimal;
        MiPila pila = new MiPila();
        
        System.out.println("\n--- PROCESO DE CONVERSIÓN ---");
        System.out.println("Número decimal: " + decimal);
        
        if (numero == 0) {
            pila.push(0);
            System.out.println("División: 0 / 2 = 0, residuo: 0");
        } else {
            while (numero > 0) {
                int residuo = numero % 2;
                int division = numero / 2;
                System.out.println("División: " + numero + " / 2 = " + division + ", residuo: " + residuo);
                pila.push(residuo);
                numero = division;
            }
        }
        
        System.out.println("\n--- RESULTADO ---");
        System.out.print("Binario: ");
        while (!pila.isEmpty()) {
            System.out.print(pila.pop());
        }
        
        // Verificación
        System.out.println("\n\n--- VERIFICACIÓN ---");
        String binarioJava = Integer.toBinaryString(decimal);
        System.out.println("Usando Java: " + binarioJava);
        
        sc.close();
    }
}