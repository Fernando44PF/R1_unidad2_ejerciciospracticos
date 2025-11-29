/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio10;

/**
 * Verificador de palíndromos usando una pila de caracteres
 * Determina si una palabra o frase se lee igual en ambos sentidos
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */

import java.util.Scanner;

public class VerificarPalindromo  {
    
    static class MiPilaChar {
        private Nodo tope;
        
        class Nodo {
            char letra;
            Nodo siguiente;
            
            public Nodo(char letra) {
                this.letra = letra;
                this.siguiente = null;
            }
        }
        
        public MiPilaChar() {
            this.tope = null;
        }
        
        public void push(char letra) {
            Nodo nuevo = new Nodo(letra);
            nuevo.siguiente = tope;
            tope = nuevo;
        }
        
        public char pop() {
            if (isEmpty()) {
                return ' ';
            }
            char letra = tope.letra;
            tope = tope.siguiente;
            return letra;
        }
        
        public boolean isEmpty() {
            return tope == null;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=EJERCICIO 10: VERIFICAR PALÍNDROMO=");
        
        System.out.print("Ingresa una palabra o frase: ");
        String texto = sc.nextLine();
        
        // Limpiar el texto
        String textoLimpio = texto.replaceAll("[^a-zA-Z]", "").toLowerCase();
        
        MiPilaChar pila = new MiPilaChar();
        
        System.out.println("\n--- PROCESO DE VERIFICACIÓN ---");
        System.out.println("Texto original: '" + texto + "'");
        System.out.println("Texto limpio: '" + textoLimpio + "'");
        
        // Apilar cada letra
        System.out.println("\nApilando letras...");
        for (int i = 0; i < textoLimpio.length(); i++) {
            char letra = textoLimpio.charAt(i);
            pila.push(letra);
            System.out.println("Apilado: '" + letra + "'");
        }
        
        // Construir texto invertido
        System.out.println("\nDesapilando letras...");
        StringBuilder invertida = new StringBuilder();
        while (!pila.isEmpty()) {
            char letra = pila.pop();
            invertida.append(letra);
            System.out.println("Desapilado: '" + letra + "'");
        }
        
        String textoInvertido = invertida.toString();
        
        System.out.println("\n--- RESULTADO ---");
        System.out.println("Texto original: " + textoLimpio);
        System.out.println("Texto invertido: " + textoInvertido);
        
        if (textoLimpio.equals(textoInvertido)) {
            System.out.println("🎉 ¡ES UN PALÍNDROMO! 🎉");
        } else {
            System.out.println("❌ NO es un palíndromo");
        }
        
        // Probar algunos ejemplos conocidos
        System.out.println("\n--- EJEMPLOS CONOCIDOS ---");
        String[] ejemplos = {"reconocer", "anilina", "oso", "radar", "java", "casa"};
        
        for (String ejemplo : ejemplos) {
            String limpio = ejemplo.toLowerCase();
            MiPilaChar pilaEjemplo = new MiPilaChar();
            
            for (char c : limpio.toCharArray()) {
                pilaEjemplo.push(c);
            }
            
            StringBuilder invertido = new StringBuilder();
            while (!pilaEjemplo.isEmpty()) {
                invertido.append(pilaEjemplo.pop());
            }
            
            boolean esPalindromo = limpio.equals(invertido.toString());
            System.out.println("'" + ejemplo + "' -> " + (esPalindromo ? "SÍ" : "NO"));
        }
        
        sc.close();
    }
}