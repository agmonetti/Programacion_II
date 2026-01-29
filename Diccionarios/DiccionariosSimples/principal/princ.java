package principal;

import implementacion.DiccionarioSimpleTF;
import interfaz.DiccionarioSimpleTDA;
import interfaz.ConjuntoTDAD;
public class princ {
	
	//metodo para mostrar diccionario, perdiendo el diccionario
	public static void mostrarDiccionario(DiccionarioSimpleTDA d) {
		if (d == null) return;
		
		ConjuntoTDAD claves = d.Claves();
		int valor;
		int clave;
		
		while(!claves.ConjuntoVacio()) {
			clave= claves.Elegir();
			valor = d.Recuperar(clave);
			System.out.println("Clave: " + clave +", Valor: " + valor);
			d.Eliminar(clave);
			claves.Sacar(clave);
		
		}
	}

	//metodo para copiar un diccionario
	public static DiccionarioSimpleTDA copiarDiccionario(DiccionarioSimpleTDA dicc) {
		
		ConjuntoTDAD claves = dicc.Claves();
		ConjuntoTDAD clavesAux = dicc.Claves();
		
		int valor;
		int clave;
		
		DiccionarioSimpleTDA aux = new DiccionarioSimpleTF();
		DiccionarioSimpleTDA copia = new DiccionarioSimpleTF();
		

		//yo tenia esto tamb en el while :"dicc != null " - va a estar vacio, pero nunca el diccionario va a ser nulo
		while(!claves.ConjuntoVacio()) {
			clave= claves.Elegir();
			valor = dicc.Recuperar(clave);
			
			aux.Agregar(clave, valor);
			copia.Agregar(clave, valor);
			
			dicc.Eliminar(clave);
			claves.Sacar(clave);
		}
		
		while(!clavesAux.ConjuntoVacio()) {
			clave= clavesAux.Elegir();
			valor = aux.Recuperar(clave);
			
			dicc.Agregar(clave, valor);
			
			aux.Eliminar(clave);
			clavesAux.Sacar(clave);
		}
		
		return copia;
	}
	
	//metodo para mostrar diccionario, pero sin perder el diccionario
	public static void mostrarDiccionarioClean(DiccionarioSimpleTDA d) {
		if (d == null) return;
		
		DiccionarioSimpleTDA copia = copiarDiccionario(d);
		
		ConjuntoTDAD claves = copia.Claves();
		int valor;
		int clave;
		
		while(!claves.ConjuntoVacio()) {
			clave= claves.Elegir();
			valor = copia.Recuperar(clave);
			System.out.println("Clave: " + clave +", Valor: " + valor);
			copia.Eliminar(clave);
			claves.Sacar(clave);
		
		}
	}
	
	public static void main(String[] args) {
		DiccionarioSimpleTDA dicc = new DiccionarioSimpleTF();
		dicc.Agregar(101, 250);
		dicc.Agregar(102, 350);
		dicc.Agregar(103, 450);
		dicc.Agregar(104, 550);
		dicc.Agregar(105, 650);
		
		mostrarDiccionarioClean(dicc);
//		mostrarDiccionarioClean(dicc);
		System.out.println();
		
		mostrarDiccionarioClean(dicc);
		
	}

}
