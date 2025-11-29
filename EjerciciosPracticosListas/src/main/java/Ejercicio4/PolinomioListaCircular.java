/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

/**
  * Polinomio implementado con lista enlazada circular
 * Último nodo apunta al primero, optimizando recorrido continuo * 
 * @author Fernando Miguel Olvera Juárez
 * @Septiembre 2025
 *  
 */
import java.util.Scanner;

public class PolinomioListaCircular {
    
    static class TerminoPolinomio {
        double coeficiente;
        int exponente;
        TerminoPolinomio siguiente;
        
        public TerminoPolinomio(double coeficiente, int exponente) {
            this.coeficiente = coeficiente;
            this.exponente = exponente;
            this.siguiente = null;
        }
    }
    
    static class PolinomioCircular {
        private TerminoPolinomio ultimo; // Referencia al último nodo
        
        public PolinomioCircular() {
            this.ultimo = null;
        }
        
        // Agregar término (siempre al final, manteniendo circularidad)
        public void agregarTermino(double coeficiente, int exponente) {
            TerminoPolinomio nuevoTermino = new TerminoPolinomio(coeficiente, exponente);
            
            if (ultimo == null) {
                // Lista vacía - el nodo se apunta a sí mismo
                ultimo = nuevoTermino;
                ultimo.siguiente = ultimo;
            } else {
                // Insertar después del último
                nuevoTermino.siguiente = ultimo.siguiente;
                ultimo.siguiente = nuevoTermino;
                ultimo = nuevoTermino;
            }
        }
        
        // Recorrido circular del polinomio
        public void mostrarPolinomioCircular() {
            if (ultimo == null) {
                System.out.println("Polinomio vacío");
                return;
            }
            
            TerminoPolinomio actual = ultimo.siguiente; // Empezar desde el primero
            TerminoPolinomio primero = actual;
            boolean primerTermino = true;
            
            System.out.print("P(x) = ");
            do {
                double coef = actual.coeficiente;
                int exp = actual.exponente;
                
                if (!primerTermino) {
                    if (coef > 0) {
                        System.out.print(" + ");
                    } else {
                        System.out.print(" - ");
                        coef = -coef;
                    }
                }
                
                if (exp == 0) {
                    System.out.print(coef);
                } else if (exp == 1) {
                    if (coef == 1) {
                        System.out.print("x");
                    } else {
                        System.out.print(coef + "x");
                    }
                } else {
                    if (coef == 1) {
                        System.out.print("x^" + exp);
                    } else {
                        System.out.print(coef + "x^" + exp);
                    }
                }
                
                primerTermino = false;
                actual = actual.siguiente;
            } while (actual != primero); // Parar cuando volvamos al inicio
            
            System.out.println();
        }
        
        // Verificar estructura circular
        public void verificarCircularidad() {
            if (ultimo == null) {
                System.out.println("Lista vacía");
                return;
            }
            
            System.out.println("Verificando circularidad...");
            System.out.println("Último nodo apunta a: " + (ultimo.siguiente == ultimo ? "sí mismo" : "otro nodo"));
            
            TerminoPolinomio actual = ultimo.siguiente;
            int contador = 0;
            int maxIteraciones = 10; // Para evitar loops infinitos
            
            System.out.print("Recorrido: ");
            while (actual != null && contador < maxIteraciones) {
                System.out.print("(" + actual.coeficiente + "x^" + actual.exponente + ") -> ");
                actual = actual.siguiente;
                contador++;
                
                if (actual == ultimo.siguiente) {
                    System.out.println("[COMPLETO - Lista circular]");
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PolinomioCircular polinomio = new PolinomioCircular();
        
        System.out.println("=== ACTIVIDAD 4: POLINOMIO CON LISTA ENLAZADA CIRCULAR ===");
        System.out.println("Ingrese los términos del polinomio (coeficiente exponente)");
        System.out.println("Terminar con coeficiente 0 y exponente 0");
        
        // Entrada del polinomio
        while (true) {
            System.out.print("Coeficiente y exponente: ");
            double coeficiente = scanner.nextDouble();
            int exponente = scanner.nextInt();
            
            if (coeficiente == 0 && exponente == 0) {
                break;
            }
            
            if (coeficiente != 0) {
                polinomio.agregarTermino(coeficiente, exponente);
            }
        }
        
        // Mostrar polinomio con recorrido circular
        System.out.println("\n--- POLINOMIO (LISTA CIRCULAR) ---");
        polinomio.mostrarPolinomioCircular();
        
        // Verificar estructura circular
        System.out.println("\n--- VERIFICACIÓN DE CIRCULARIDAD ---");
        polinomio.verificarCircularidad();
        
        scanner.close();
    }
}
