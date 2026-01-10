package principal;

import interfaz.ColaPrioridadTDA;
import implementacion.pilaTF;
import interfaz.pilaTDA;
import implementacion.ColaPR;

public class princ {

	//metodo para mostrar colas con prioridad, se pierde la cola
	public static void mostrarColaPrioridad(ColaPrioridadTDA cola) {
		int cont = 1;
		while(!cola.ColaVacia()) {
			System.out.println("Nodo:" + cont + " - Valor: "+ cola.Primero()  + " - Prioridad: " + cola.Prioridad());
			cola.Desacolar();
			cont ++;
		}
	}
	
	//Metodo para copiar una cola
	public static ColaPrioridadTDA copiarColaPrioridad(ColaPrioridadTDA cola) {
		ColaPrioridadTDA aux = new ColaPR();
		aux.InicializarCola();
		
		ColaPrioridadTDA copia = new ColaPR();
		copia.InicializarCola();
		
		while(!cola.ColaVacia()) {
			aux.AcolarPrioridad(cola.Primero(), cola.Prioridad());
			cola.Desacolar();
		}
		
		while(!aux.ColaVacia()) {
			copia.AcolarPrioridad(aux.Primero(), aux.Prioridad());
			cola.AcolarPrioridad(aux.Primero(), aux.Prioridad());
			aux.Desacolar();
		}
		
		return copia;
	}
	
	//metodo para mostrar colas con prioridad, no se pierde la cola
	public static void mostrarColaPrioridadClean(ColaPrioridadTDA cola) {
		ColaPrioridadTDA copia = copiarColaPrioridad(cola);
		int cont = 1;
		while(!copia.ColaVacia()) {
			System.out.println("Nodo:" + cont + " - Valor: "+ copia.Primero()  + " - Prioridad: " + copia.Prioridad());
			copia.Desacolar();
			cont ++;
		}
	}
	
	
	//metodo para combinar dos colas con prioridad en una tercera cola
	public static ColaPrioridadTDA combinarColas(ColaPrioridadTDA CP1, ColaPrioridadTDA CP2 ) {
		ColaPrioridadTDA copia1 = copiarColaPrioridad(CP1);
		ColaPrioridadTDA copia2 = copiarColaPrioridad(CP2);
		
		//Tercera cola que se va a devolver
		ColaPrioridadTDA colaFinal = new ColaPR();
		colaFinal.InicializarCola();
		
		while(!copia1.ColaVacia() && !copia2.ColaVacia()) {
			if(copia1.Prioridad() > copia2.Prioridad()) {
				colaFinal.AcolarPrioridad(copia1.Primero(), copia1.Prioridad());
				copia1.Desacolar();
			}
			else if(copia1.Prioridad() < copia2.Prioridad()) {
				colaFinal.AcolarPrioridad(copia2.Primero(), copia2.Prioridad());
				copia2.Desacolar();
			}
			
			else{
				colaFinal.AcolarPrioridad(copia1.Primero(), copia1.Prioridad());
				copia1.Desacolar();
			}
		}
		
		// Ahora, caso de que alguna de las dos colas sigue teniendo elementos
		while(!copia1.ColaVacia()) {
	        colaFinal.AcolarPrioridad(copia1.Primero(), copia1.Prioridad());
	        copia1.Desacolar();
	    }

	    // algo en la copia2 
	    while(!copia2.ColaVacia()) {
	        colaFinal.AcolarPrioridad(copia2.Primero(), copia2.Prioridad());
	        copia2.Desacolar();
	    }
		
		return colaFinal;
		
	}
	
	//metodo para contar los elementos de una cola
	public static int contarElementos(ColaPrioridadTDA cola) {
		int cont = 0;
		ColaPrioridadTDA copia = copiarColaPrioridad(cola);
		
		while(!copia.ColaVacia()) {
			cont ++;
			copia.Desacolar();
		}
		return cont;
	}
	
	
	//metoodo para ver si dos colas son identicas, es decir, mismos valores y prioridades
	public static boolean sonIdenticas(ColaPrioridadTDA CP1, ColaPrioridadTDA CP2) {
		ColaPrioridadTDA copia1 = copiarColaPrioridad(CP1);
		ColaPrioridadTDA copia2 = copiarColaPrioridad(CP2);
		
		//primer descarte, distinto tamaño
		if((contarElementos(copia1) != contarElementos(copia2)))
			return false;	
		
		//tienen mismo tamaño, debo revisar los nodos
		else {
			while(!copia1.ColaVacia() || !copia2.ColaVacia()) {
				if((copia1.Primero() != copia2.Primero()) && (copia1.Prioridad() != copia2.Prioridad()))
					return false;
				copia1.Desacolar();
				copia2.Desacolar();
			}
			return true;
		}
		//metodo no muy eficiente, se recorren las colas 2 veces, una para contar los elementos y otra para recorrerlos
	}
	
	//metoodo para ver si dos colas son identicas, es decir, mismos valores y prioridades
	//Pero, usando la misma logica de combinar las colas
	public static boolean sonIdenticasV2(ColaPrioridadTDA CP1, ColaPrioridadTDA CP2) {
	    ColaPrioridadTDA copia1 = copiarColaPrioridad(CP1);
	    ColaPrioridadTDA copia2 = copiarColaPrioridad(CP2);
	    
	 
	    while(!copia1.ColaVacia() && !copia2.ColaVacia()) {
	        // Si el valor es distinto ó la prioridad es distinta -> diferentes
	        if(copia1.Primero() != copia2.Primero() || copia1.Prioridad() != copia2.Prioridad()) {
	            return false;
	        }
	            
	        copia1.Desacolar();
	        copia2.Desacolar();
	    }
	    
	    // puede ser que alguna de las 2 todavia tenga elementos, entonces, se rompe la identidad
	    if(!copia1.ColaVacia() || !copia2.ColaVacia()) {
	        return false;
	    }
	    
	    return true;
	}
	
	
	//MAIN
	public static void main(String[] args) {
		ColaPrioridadTDA cola = new ColaPR();
		cola.InicializarCola();
		
		// nodo 1 con VALOR 10, prioridad 100
		cola.AcolarPrioridad(10, 100);
		
		
		// nodo 2 con VALOR 55, prioridad 50
		cola.AcolarPrioridad(55, 50);
		
		// nodo 3 con VALOR 11, prioridad 25
		cola.AcolarPrioridad(111, 25);
		
		//mostrarColaPrioridad(cola);
		//mostrarColaPrioridad(cola); no se puede hacer, estamos rompiendo la cola con ese metodo de mostrar
		//Ahora con el nuevo metodo deberia poder mostrar 2 veces la misma cola
		System.out.println("cola 1: ");
		mostrarColaPrioridadClean(cola);
		System.out.println();
		System.out.println("cola 2: ");
		
		
		ColaPrioridadTDA cola2 = new ColaPR();
		cola2.InicializarCola();
		
		// nodo 1 con VALOR 555, prioridad 75
		cola2.AcolarPrioridad(555, 75);
		
		
		// nodo 2 con VALOR 12, prioridad 60
		cola2.AcolarPrioridad(12,60);
		
		// nodo 3 con VALOR 2400, prioridad 10
		cola2.AcolarPrioridad(2400, 10);
		mostrarColaPrioridadClean(cola2);
		
		ColaPrioridadTDA colaCombinada = combinarColas(cola,cola2);
		System.out.println();
		System.out.println("cola 3 [combinacion de 1 y 2]: ");
		mostrarColaPrioridadClean(colaCombinada);
		
		System.out.println();
		System.out.println("cola 4: ");
		ColaPrioridadTDA cola4 = new ColaPR();
		cola4.InicializarCola();
		cola4.AcolarPrioridad(555, 75);
		cola4.AcolarPrioridad(12,60);
		mostrarColaPrioridadClean(cola4);
		
		System.out.println();
		System.out.println("cola 5: ");
		ColaPrioridadTDA cola5 = new ColaPR();
		cola5.InicializarCola();
		cola5.AcolarPrioridad(555, 75);
		cola5.AcolarPrioridad(12,60);
		mostrarColaPrioridadClean(cola5);
		
		System.out.println();
//		System.out.println("Son identicas la COLA 1 y la COLA 4?: " + sonIdenticas(cola,cola4));
//		System.out.println();
//		System.out.println("Son identicas la COLA 4 y la COLA 5?: " + sonIdenticas(cola4,cola5));
//		System.out.println();
		System.out.println("Son identicas la COLA 1 y la COLA 4?: " + sonIdenticasV2(cola,cola4));
		System.out.println();
		System.out.println("Son identicas la COLA 4 y la COLA 5?: " + sonIdenticasV2(cola4,cola5));
	}
}