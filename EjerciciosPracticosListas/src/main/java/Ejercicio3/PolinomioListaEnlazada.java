/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

/**
 * Representación y evaluación de polinomios usando listas enlazadas
 * Cada término tiene coeficiente y exponente, se evalúa en rango 0-5 * 
 * @author Fernando Miguel Olvera Juárez
 * @Septiembre 2025
 *  
 */
import java.util.Scanner;

public class PolinomioListaEnlazada {
    
    // Clase para el término del polinomio
    static class Termino {
        double coeficiente;
        int exponente;
        Termino siguiente;
        
        Termino(double coef, int exp) {
            this.coeficiente = coef;
            this.exponente = exp;
            this.siguiente = null;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== ACTIVIDAD 3: POLINOMIOS CON LISTAS ENLAZADAS ===");
        System.out.println("Ingresa los terminos del polinomio (coeficiente exponente)");
        System.out.println("Ejemplo: para 3x^4 - 4x^2 + 11 ingresa: 3 4, -4 2, 11 0");
        System.out.println("Termina con 0 0");
        
        Termino inicio = null;
        Termino ultimo = null;
        
        // 1. Leer términos del polinomio
        while (true) {
            System.out.print("Coeficiente y exponente: ");
            double coef = sc.nextDouble();
            int exp = sc.nextInt();
            
            if (coef == 0 && exp == 0) {
                break;
            }
            
            Termino nuevo = new Termino(coef, exp);
            
            if (inicio == null) {
                inicio = nuevo;
                ultimo = nuevo;
            } else {
                ultimo.siguiente = nuevo;
                ultimo = nuevo;
            }
        }
        
        // 2. Mostrar polinomio
        System.out.println("\n--- POLINOMIO INGRESADO ---");
        mostrarPolinomio(inicio);
        
        // 3. Evaluar y mostrar tabla
        System.out.println("\n--- TABLA DE VALORES ---");
        System.out.println(" x  |  P(x)");
        System.out.println("----|-------");
        
        for (double x = 0.0; x <= 5.0; x += 0.5) {
            double resultado = evaluarPolinomio(inicio, x);
            System.out.printf("%.1f | %.2f\n", x, resultado);
        }
        
        sc.close();
    }
    
    // Método para mostrar el polinomio
    static void mostrarPolinomio(Termino inicio) {
        Termino actual = inicio;
        boolean primero = true;
        
        System.out.print("P(x) = ");
        while (actual != null) {
            if (!primero) {
                if (actual.coeficiente > 0) {
                    System.out.print(" + ");
                } else {
                    System.out.print(" - ");
                }
            }
            
            double coef = Math.abs(actual.coeficiente);
            int exp = actual.exponente;
            
            if (exp == 0) {
                System.out.print(coef);
            } else if (exp == 1) {
                System.out.print(coef + "x");
            } else {
                System.out.print(coef + "x^" + exp);
            }
            
            primero = false;
            actual = actual.siguiente;
        }
        System.out.println();
    }
    
    // Método para evaluar el polinomio
    static double evaluarPolinomio(Termino inicio, double x) {
        double resultado = 0;
        Termino actual = inicio;
        
        while (actual != null) {
            resultado += actual.coeficiente * Math.pow(x, actual.exponente);
            actual = actual.siguiente;
        }
        
        return resultado;
    }
}
