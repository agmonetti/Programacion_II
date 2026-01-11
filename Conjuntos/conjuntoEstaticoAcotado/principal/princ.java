package principal;

import implementacion.ConjuntoTF;
import interfaz.ConjuntoTDA;

public class princ {

	//metodo para mostrar un conjunto
	public static void mostrarConjunto(ConjuntoTDA cj) {
		int valor;
		
		while(!cj.ConjuntoVacio()) {
			valor = cj.Elegir();
			System.out.println("Valor: "+ valor);
			cj.Sacar(valor);
		}
		
	}
	
	//metodo para copiar un conjunto
	public static ConjuntoTDA copiarConjunto(ConjuntoTDA cj) {
		ConjuntoTDA aux = new ConjuntoTF();
		ConjuntoTDA copia = new ConjuntoTF();
		
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
	public static void mostrarConjuntoCopia(ConjuntoTDA cj) {
		ConjuntoTDA copia = copiarConjunto(cj);
		int valor;
		
		while(!copia.ConjuntoVacio()) {
			valor = copia.Elegir();
			System.out.println("Valor: "+ valor);
			copia.Sacar(valor);
		}
		
	}
	
	
	public static void main(String[] args) {
		ConjuntoTDA conjunto1 = new ConjuntoTF();
		conjunto1.InicializarConjunto();
		
		conjunto1.Agregar(1);
		conjunto1.Agregar(2);
		conjunto1.Agregar(3);
		conjunto1.Agregar(4);
		
		mostrarConjuntoCopia(conjunto1);
//		mostrarConjuntoCopia(conjunto1);
		
	}

}
