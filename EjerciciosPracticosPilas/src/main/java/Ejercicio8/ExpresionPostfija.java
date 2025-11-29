/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio8;

/**
 * Evaluador de expresiones en notación polaca inversa (postfija)
 * Utiliza una pila para evaluar expresiones matemáticas postfijas
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */


import java.util.Scanner;

public class ExpresionPostfija {
    
    static class MiPilaInt {
        private Nodo tope;
        
        class Nodo {
            int numero;
            Nodo siguiente;
            
            public Nodo(int numero) {
                this.numero = numero;
                this.siguiente = null;
            }
        }
        
        public MiPilaInt() {
            this.tope = null;
        }
        
        public void push(int numero) {
            Nodo nuevo = new Nodo(numero);
            nuevo.siguiente = tope;
            tope = nuevo;
        }
        
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Pila vacía");
            }
            int numero = tope.numero;
            tope = tope.siguiente;
            return numero;
        }
        
        public boolean isEmpty() {
            return tope == null;
        }
    }
    
    public static int evaluarPostfija(String expresion) {
        MiPilaInt pila = new MiPilaInt();
        String[] tokens = expresion.split(" ");
        
        System.out.println("\n--- EVALUANDO EXPRESIÓN ---");
        System.out.println("Expresión: " + expresion);
        
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            System.out.println("\nToken " + (i+1) + ": '" + token + "'");
            
            if (esNumero(token)) {
                int numero = Integer.parseInt(token);
                pila.push(numero);
                System.out.println("Número " + numero + " apilado");
            } else {
                System.out.println("Operador: " + token);
                int num2 = pila.pop();
                int num1 = pila.pop();
                System.out.println("Desapilados: " + num1 + " y " + num2);
                
                int resultado = realizarOperacion(token, num1, num2);
                System.out.println("Operación: " + num1 + " " + token + " " + num2 + " = " + resultado);
                
                pila.push(resultado);
                System.out.println("Resultado " + resultado + " apilado");
            }
        }
        
        return pila.pop();
    }
    
    private static boolean esNumero(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static int realizarOperacion(String operador, int a, int b) {
        switch (operador) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new ArithmeticException("División por cero");
                return a / b;
            default: throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== EJERCICIO 8: EXPRESIÓN POSTFIJA ===");
        System.out.println("Instrucciones:");
        System.out.println("- Escribe números y operadores separados por espacios");
        System.out.println("- Operadores válidos: + - * /");
        System.out.println("- Ejemplo: 5 3 + 8 2 - *");
        
        System.out.print("\nIngresa la expresión postfija: ");
        String expresion = sc.nextLine();
        
        try {
            int resultado = evaluarPostfija(expresion);
            System.out.println("\n--- RESULTADO FINAL ---");
            System.out.println("Expresión: " + expresion);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Ejemplos automáticos para probar
        System.out.println("\n--- EJEMPLOS AUTOMÁTICOS ---");
        String[] ejemplos = {
            "5 3 +",           // 5 + 3 = 8
            "10 5 2 * +",      // 10 + (5 * 2) = 20  
            "15 3 4 * + 2 -",  // 15 + (3 * 4) - 2 = 25
            "8 2 / 4 *"        // (8 / 2) * 4 = 16
        };
        
        for (String ejemplo : ejemplos) {
            try {
                int res = evaluarPostfija(ejemplo);
                System.out.println("'" + ejemplo + "' = " + res);
            } catch (Exception e) {
                System.out.println("'" + ejemplo + "' -> Error: " + e.getMessage());
            }
        }
        
        sc.close();
    }
}