package principal;

import implementacion.ConjuntoLDAC;
import interfaz.ConjuntoAC;

public class princ {

	//metodo para mostrar un conjunto
	public static void mostrarConjunto(ConjuntoAC cj) {
		int valor;
		
		while(!cj.ConjuntoVacio()) {
			valor = cj.Elegir();
			System.out.println("Valor: "+ valor);
			cj.Sacar(valor);
		}
		
	}
	
	//metodo para copiar un conjunto
	public static ConjuntoAC copiarConjunto(ConjuntoAC cj) {
		ConjuntoAC aux = new ConjuntoLDAC();
		ConjuntoAC copia = new ConjuntoLDAC();
		
		aux.InicializarConjunto();
		copia.InicializarConjunto();
		int valor;
		
		while(!cj.ConjuntoVacio()) {
			valor = cj.Elegir();
			aux.Agregar(valor);
			cj.Sacar(valor);
		}
		
		while(!aux.ConjuntoVacio()) {
			valor = aux.Elegir();
			copia.Agregar(valor);
			cj.Agregar(valor);
			aux.Sacar(valor);
		}
		
		return copia;
		
	}
	
	//metodo para mostrar un conjunto sin perderlo
	public static void mostrarConjuntoCopia(ConjuntoAC cj) {
		ConjuntoAC copia = copiarConjunto(cj);
		int valor;
		
		while(!copia.ConjuntoVacio()) {
			valor = copia.Elegir();
			System.out.println("Valor: "+ valor);
			copia.Sacar(valor);
		}
		
	}
	
	
	public static void main(String[] args) {
		ConjuntoAC conjunto1 = new ConjuntoLDAC();
		conjunto1.InicializarConjunto();
		
		conjunto1.Agregar(1);
		conjunto1.Agregar(2);
		conjunto1.Agregar(3);
		conjunto1.Agregar(4);
		
		mostrarConjuntoCopia(conjunto1);
//		mostrarConjuntoCopia(conjunto1);
		
	}

}
