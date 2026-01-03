package principal;

import implementacion.pilaTF; 
import interfaz.pilaTDA;


public class princ {
	
	//metodo para mostrar la pila, metodo que borra la pila
	public static void mostrarPila(pilaTDA pila)  {
		int valor;
		while(!pila.PilaVacia()) {
			valor = pila.Tope();
			System.out.println("valor: " + valor);
			pila.Desapilar();
		}
		
	}
	
	//mostramos la pila sin borrar la original
	public static void mostrarPilaClean(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		mostrarPila(copia);
	}
	
	//metodo para copiar la pila tal cual esta
	public static pilaTDA copiarPila(pilaTDA pilaOriginal) {
		pilaTDA pilaAux = new pilaTF();
		pilaTDA pilaCopia = new pilaTF();
		
		pilaAux.InicializarPila();
		pilaCopia.InicializarPila();
		
		int x;
		
		while(!pilaOriginal.PilaVacia()) {
			x = pilaOriginal.Tope();
			pilaAux.Apilar(x);
			pilaOriginal.Desapilar();
		}
		
		while(!pilaAux.PilaVacia()) {
			x = pilaAux.Tope();
			pilaCopia.Apilar(x);
			pilaOriginal.Apilar(x);       //TENGO QUE APILAR DE NUEVO A LA ORIGINAL PORQUE SINO ROMPO EL PARAMETRO QUE LE PASE
			pilaAux.Desapilar();
		}
		
		
		return pilaCopia;
		
			
	}
	
	//metodo para invertir una pila
	public static pilaTDA invertirPila(pilaTDA pilaOriginal) {
		pilaTDA pilaOriginalCopia = copiarPila(pilaOriginal);
		pilaTDA pilaInvertida = new pilaTF();
		pilaInvertida.InicializarPila();
		
		int valor;
		
		while(!pilaOriginalCopia.PilaVacia()) {
			valor = pilaOriginalCopia.Tope();
			pilaInvertida.Apilar(valor);
			pilaOriginalCopia.Desapilar();
		}
		
		return pilaInvertida;
		
	}
	
	//metodo para contar los elementos de una pila
	public static int contarElementos(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		int cont = 0;
		while(!copia.PilaVacia()) {
			cont ++;
			copia.Desapilar();
		}
		return cont;
	}
	
	//metodo para sumar los elementos de una pila
	public static int sumarElementos(pilaTDA pila) {
		pilaTDA copia = copiarPila(pila);
		int suma = 0;
	while(!copia.PilaVacia()) {
		suma += copia.Tope();
		copia.Desapilar();
	}
	return suma;
	}
	
	//metodo para calcular el promedio de los elementos de una pila

	public static void main(String[] args) {
		//hola
		pilaTDA pila = new pilaTF();
		pila.InicializarPila();
		pila.Apilar(1);
		pila.Apilar(2);
		pila.Apilar(3);
		pila.Apilar(4);
		System.out.println("Procedemos a copiar la pila");
		pilaTDA copiaPila = copiarPila(pila);
		//copio antes de mostrar, porque el metodo de mostrar la pila la borra.
		System.out.println("pila1: ");
		System.out.println();
		mostrarPilaClean(pila);
		System.out.println();
		System.out.println("pila2: ");
		mostrarPilaClean(copiaPila);
		
		System.out.println();
		System.out.println("Ahora queremos invertir la pila 2 (pila copiada): ");
		pilaTDA pilaInvertida = invertirPila(copiaPila);
		System.out.println();
		System.out.println("pila invertida: ");
		mostrarPilaClean(pilaInvertida);
		System.out.println();
		System.out.println("cant de elementos: " + contarElementos(pilaInvertida));
		System.out.println("suma elementos: " + sumarElementos(pilaInvertida));
	}

}
