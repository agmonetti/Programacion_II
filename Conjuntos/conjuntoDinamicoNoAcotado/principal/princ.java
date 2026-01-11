package principal;

import implementacion.ConjuntoLDA;
import interfaz.ConjuntoTDAD;

public class princ {

	//metodo para mostrar un conjunto
	public static void mostrarConjunto(ConjuntoTDAD cj) {
		int valor;
		
		while(!cj.ConjuntoVacio()) {
			valor = cj.Elegir();
			System.out.println("Valor: "+ valor);
			cj.Sacar(valor);
		}
		
	}
	
	//metodo para copiar un conjunto
	public static ConjuntoTDAD copiarConjunto(ConjuntoTDAD cj) {
		ConjuntoTDAD aux = new ConjuntoLDA();
		ConjuntoTDAD copia = new ConjuntoLDA();
		
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
	public static void mostrarConjuntoCopia(ConjuntoTDAD cj) {
		ConjuntoTDAD copia = copiarConjunto(cj);
		int valor;
		
		while(!copia.ConjuntoVacio()) {
			valor = copia.Elegir();
			System.out.println("Valor: "+ valor);
			copia.Sacar(valor);
		}
		
	}
	
	
	public static void main(String[] args) {
		ConjuntoTDAD conjunto1 = new ConjuntoLDA();
		conjunto1.InicializarConjunto();
		
		conjunto1.Agregar(1);
		conjunto1.Agregar(2);
		conjunto1.Agregar(3);
		conjunto1.Agregar(4);
		
		mostrarConjuntoCopia(conjunto1);
		mostrarConjuntoCopia(conjunto1);
		
	}

}
