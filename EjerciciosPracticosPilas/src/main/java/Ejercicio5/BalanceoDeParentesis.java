/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio5;

/**
 * Verifica el balanceo de paréntesis en una expresión matemática
 * Utiliza una pila para rastrear la correspondencia de paréntesis
 * 
 * @author Fernando Miguel Olvera Juárez
 * @version 1.0
 * @since Septiembre 2024
 */


import java.util.Scanner;

public class BalanceoDeParentesis {
    
    static class MiPilaChar {
        private Nodo tope;
        
        class Nodo {
            char simbolo;
            Nodo siguiente;
            
            public Nodo(char simbolo) {
                this.simbolo = simbolo;
                this.siguiente = null;
            }
        }
        
        public MiPilaChar() {
            this.tope = null;
        }
        
        public void push(char simbolo) {
            Nodo nuevo = new Nodo(simbolo);
            nuevo.siguiente = tope;
            tope = nuevo;
        }
        
        public char pop() {
            if (isEmpty()) {
                return ' ';
            }
            char simbolo = tope.simbolo;
            tope = tope.siguiente;
            return simbolo;
        }
        
        public boolean isEmpty() {
            return tope == null;
        }
    }
    
    public static boolean estaBalanceada(String expresion) {
        MiPilaChar pila = new MiPilaChar();
        
        System.out.println("\nAnalizando expresión: " + expresion);
        
        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            
            if (c == '(') {
                pila.push(c);
                System.out.println("Encontrado '(' - Apilado");
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    System.out.println("Encontrado ')' pero la pila está vacía - NO balanceado");
                    return false;
                }
                pila.pop();
                System.out.println("Encontrado ')' - Desapilado");
            }
        }
        
        boolean balanceado = pila.isEmpty();
        System.out.println("Al final, la pila está vacía: " + balanceado);
        
        return balanceado;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== EJERCICIO 5: BALANCEO DE PARÉNTESIS ===");
        
        System.out.print("Ingresa una expresión con paréntesis: ");
        String expresion = sc.nextLine();
        
        boolean resultado = estaBalanceada(expresion);
        
        System.out.println("\n--- RESULTADO ---");
        if (resultado) {
            System.out.println("✓ La expresión ESTÁ balanceada");
        } else {
            System.out.println("✗ La expresión NO está balanceada");
        }
        
        // Probar algunos ejemplos automáticos
        System.out.println("\n--- EJEMPLOS AUTOMÁTICOS ---");
        String[] ejemplos = {"(2+3)*(4-1)", "((2+3)*5", ")(2+3)(", "((a+b)*(c-d))"};
        
        for (String ejemplo : ejemplos) {
            boolean res = estaBalanceada(ejemplo);
            System.out.println("'" + ejemplo + "' -> " + (res ? "Balanceado" : "No balanceado"));
        }
        
        sc.close();
    }
}
