package principal;

import implementacion.pilaTF; 
import interfaz.pilaTDA;


public class princ {

	//metodo para copiar la pila tal cual esta
	public static pilaTDA copiarPila(pilaTDA pila) {
		pilaTDA pilaAux = new pilaTF();
		pilaTDA pilaCopia = new pilaTF();
		
		pilaAux.InicializarPila();
		pilaCopia.InicializarPila();
		
		int x;
		
		while(!pila.PilaVacia()) {
			x = pila.Tope();
			pilaAux.Apilar(x);
			pila.Desapilar();
		}
		
		while(!pilaAux.PilaVacia()) {
			x = pilaAux.Tope();
			pilaCopia.Apilar(x);
			pila.Apilar(x);       //TENGO QUE APILAR DE NUEVO A LA ORIGINAL PORQUE SINO ROMPO EL PARAMETRO QUE LE PASE
			pilaAux.Desapilar();
		}
		
		
		return pilaCopia;
		
		
	}
	
	//metodo para mostrar una pila
	public static void mostrarPila(pilaTDA pila) {
		int valor;
		while(!pila.PilaVacia()) {
			valor = pila.Tope();
			System.out.println("valor: " + valor);
			pila.Desapilar();
		}
	}
	
	//metodo para mostrar una pila
	public static void mostrarPilaClean(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		int valor;
		while(!copia.PilaVacia()) {
			valor = copia.Tope();
			System.out.println("valor: " + valor);
			copia.Desapilar();
		}
	}
	
	//metodo para invertir pila
	public static pilaTDA invertirPila(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		pilaTDA pilaInvertida = new pilaTF();
		pilaInvertida.InicializarPila();
		
		int valor;
		
		while(!copia.PilaVacia()) {
			valor = copia.Tope();
			pilaInvertida.Apilar(valor);
			copia.Desapilar();
		}
		
		return pilaInvertida;
	}
	
	
	//metodo para contar elementos de la pila
	public static int contarElementos(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		int cont = 0;
		while(!copia.PilaVacia()) {
			cont ++;
			copia.Desapilar();
		}
		
		return cont;
	}
	
	
	//metodo para sumar elementos de la pila
	public static int sumarElementos(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		int suma = 0;
		while(!copia.PilaVacia()) {
			suma += copia.Tope();
			copia.Desapilar();
		}
		return suma;
	}
	
	
	//metodo para calc el promedio de elementos
	public static double promedioElementos(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		int suma = 0;
		int cant = 0;
		double prom;
		while(!copia.PilaVacia()) {
			suma += copia.Tope();
			cant ++;
			copia.Desapilar();
		}
		prom = (double) suma / cant;
		return prom;
	}
	
	
	
	//MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pilaTDA pila = new pilaTF();
		pila.InicializarPila();
		pila.Apilar(4);
		pila.Apilar(4);
		pila.Apilar(4);
		pila.Apilar(4);
		pila.Apilar(8);
		System.out.println("Procedemos a copiar la pila");
		pilaTDA copiaPila = copiarPila(pila);
		//copio antes de mostrar, porque el metodo de mostrar la pila la borra.
		System.out.println("pila1: ");
		System.out.println();
		mostrarPilaClean(pila);
		System.out.println();
		System.out.println("pila2: ");
		mostrarPilaClean(copiaPila);
		
		pilaTDA pilaInvertida = invertirPila(pila);
		System.out.println();
		System.out.println("pila invertida: ");
		mostrarPilaClean(pilaInvertida);
		System.out.println();
		System.out.println("cant elementos: " + contarElementos(pila));
		System.out.println();
		System.out.println("suma elementos: " + sumarElementos(pila));
		System.out.println();
		System.out.println("prom elementos: " + promedioElementos(pila));
	}

}
