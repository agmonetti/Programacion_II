package principal;

import interfaz.ConjuntoTDAD;
import interfaz.DiccionarioMultipleTDA;
import implementacion.ConjuntoLDA;
import implementacion.DicMultipleL;

public class princ {
	
	//metodo para mostrar un diccionario multiple (no se rompe)
	public static void mostrarDicMultiple(DiccionarioMultipleTDA dicc) {
		//recupero todas las claves
		ConjuntoTDAD claves = dicc.Claves();
		ConjuntoTDAD valores;
		int clave;
		int valor;
		
		while(!claves.ConjuntoVacio()) {
			clave = claves.Elegir();
			System.out.print("Clave - "+ clave + ": ");
			valores = dicc.Recuperar(clave);
			System.out.print("{ ");
			while(!valores.ConjuntoVacio()) {
				valor = valores.Elegir();
				valores.Sacar(valor);
				System.out.print(valor +", ");
			}
			System.out.print("}");
			System.out.println();
			claves.Sacar(clave);
		}
		
	}
	
	//metodo para unir dos diccionarios multiples en uno tercero
	public static DiccionarioMultipleTDA unirDics(DiccionarioMultipleTDA dic1, DiccionarioMultipleTDA dic2) {
		DiccionarioMultipleTDA dicResultante = new DicMultipleL();
		dicResultante.InicializarDiccionario();
		
		//recupero todas las claves del diccionario 1
		ConjuntoTDAD claves = dic1.Claves();
		ConjuntoTDAD valores;
		int clave;
		int valor;
		
		//meto todo lo del dic1 y luego lo del dic 2
		while(!claves.ConjuntoVacio()) {
			clave = claves.Elegir();
			valores = dic1.Recuperar(clave);
			while(!valores.ConjuntoVacio()) {
				valor = valores.Elegir();
				dicResultante.Agregar(clave, valor);
				valores.Sacar(valor);
			}
			claves.Sacar(clave);
		}
		
		claves = dic2.Claves();
		while(!claves.ConjuntoVacio()) {
			clave = claves.Elegir();
			valores = dic2.Recuperar(clave);
			while(!valores.ConjuntoVacio()) {
				valor = valores.Elegir();
				dicResultante.Agregar(clave, valor);
				valores.Sacar(valor);
			}
			claves.Sacar(clave);
		}
		return dicResultante;
	}

	//metodo para intersecar dos diccionarios en un tercero
	//La idea es poner todas las claves pero solo los valores en comunes
	//esta estrategia sirve para el item (D) tambien
	public static DiccionarioMultipleTDA interValComunes(DiccionarioMultipleTDA dic1, DiccionarioMultipleTDA dic2) {
		DiccionarioMultipleTDA dicResultante = new DicMultipleL();
		dicResultante.InicializarDiccionario();
		
		//recupero todas las claves del diccionario 1 y 2
		ConjuntoTDAD claves = dic1.Claves();
		ConjuntoTDAD claves2 = dic2.Claves();
		ConjuntoTDAD valores;
		ConjuntoTDAD valores2;
		int clave;
		int valor;
		
		//Estrategia:
		// - Por cada clave de dic1 ppregunto si pertenece a d2
		// - Si pertence, traigo los valores asociados a la clave, de cada diccionario
		while(!claves.ConjuntoVacio()) {
			clave = claves.Elegir();
			if(claves2.Pertenece(clave)) {//no se puede tener "valores comunes" en una clave que no existe en el otro lado.
				//por eso es que sirve para el item d
				
				valores = dic1.Recuperar(clave);
				valores2 = dic2.Recuperar(clave);
				//Ahora tengo los valores de esa misma clave. Debo preguntar, si son iguales
				while(!valores.ConjuntoVacio()) {
					valor= valores.Elegir();
					if(valores2.Pertenece(valor)) 
						dicResultante.Agregar(clave, valor);
					valores.Sacar(valor);	
				}
			}
			claves.Sacar(clave);

		}
		return dicResultante;
	}
	
	//metodo para traer unir todos los valores de claves comunes entre dos diccionarios en uno tercero
	public static DiccionarioMultipleTDA interClaComunes(DiccionarioMultipleTDA dic1, DiccionarioMultipleTDA dic2) {
		DiccionarioMultipleTDA dicResultante = new DicMultipleL();
		dicResultante.InicializarDiccionario();
		
		//Estrategia:
		//recupero todas las claves del diccionario 1 y pregunto si pertenece a las claves de d2
		ConjuntoTDAD claves = dic1.Claves();
		ConjuntoTDAD claves2 = dic2.Claves();
		ConjuntoTDAD valores;
		ConjuntoTDAD valores2;
		int clave;
		int valor;
		
		while(!claves.ConjuntoVacio()) {
			clave = claves.Elegir();
			if(claves2.Pertenece(clave)) {
				
				valores = dic1.Recuperar(clave);
				valores2 = dic2.Recuperar(clave);
				
				//agrego todos los valores de cada diccionario
				while(!valores.ConjuntoVacio()) {
					valor=valores.Elegir();
					dicResultante.Agregar(clave, valor);					
					valores.Sacar(valor);
				}
				
				while(!valores2.ConjuntoVacio()) {
					valor=valores2.Elegir();
					dicResultante.Agregar(clave, valor);					
					valores2.Sacar(valor);
				}
				
			}
		claves.Sacar(clave);
		}
		return dicResultante;
	}
	
	public static void main(String[] args) {
//		DiccionarioMultipleTDA dicc = new DicMultipleL();
//		dicc.InicializarDiccionario();
//		
//		dicc.Agregar(10, 150);
//		dicc.Agregar(10, 160);
//		dicc.Agregar(10, 170);
//		
//		dicc.Agregar(20, 250);
//		dicc.Agregar(20, 450);
//		
//		dicc.Agregar(30, 350);
//	
//		dicc.Agregar(40, 450);
//		dicc.Agregar(40, 500);
//		dicc.Agregar(40, 550);
//		dicc.Agregar(40, 600);
//		System.out.println("Diccionario 1");
//		mostrarDicMultiple(dicc);
//	
//		DiccionarioMultipleTDA dicc2 = new DicMultipleL();
//		dicc2.InicializarDiccionario();
//		
//		dicc2.Agregar(10, 150);
//		dicc2.Agregar(10, 200);
//		dicc2.Agregar(10, 300);
//		
//		dicc2.Agregar(20, 250);
//		
//		dicc2.Agregar(30, 350);
//		dicc2.Agregar(30, 450);
//	
//		dicc2.Agregar(40, 4500);
//		dicc2.Agregar(40, 5000);
//		dicc2.Agregar(40, 550);
//		System.out.println();
//		System.out.println("Diccionario 2");
//		mostrarDicMultiple(dicc2);
//		System.out.println();
//		System.out.println("Ejercicio (A): Unir dos diccionarios en un tercero:");
//		DiccionarioMultipleTDA diccUnion = unirDics(dicc,dicc2);
//		mostrarDicMultiple(diccUnion);
		DiccionarioMultipleTDA dicc3 = new DicMultipleL();
		dicc3.InicializarDiccionario();
		
		dicc3.Agregar(10, 150);
		dicc3.Agregar(10, 160);
		
		dicc3.Agregar(20, 250);
		dicc3.Agregar(20, 450);
		
		dicc3.Agregar(30, 350);
	
		dicc3.Agregar(40, 450);
		dicc3.Agregar(40, 500);
		System.out.println("Diccionario 1");
		mostrarDicMultiple(dicc3);
		System.out.println();
		DiccionarioMultipleTDA dicc4 = new DicMultipleL();
		dicc4.InicializarDiccionario();
		
		dicc4.Agregar(10, 150);
		dicc4.Agregar(10, 180);
		
		dicc4.Agregar(20, 250);
		
		dicc4.Agregar(30, 350);
		dicc4.Agregar(30, 1000);
	
		dicc4.Agregar(40, 450);
		
		
		System.out.println("Diccionario 2");
		mostrarDicMultiple(dicc4);
		System.out.println();
		System.out.println("Ejercicio (B): Intersecar dos diccionarios en un tercero:");
		DiccionarioMultipleTDA diccInterValores = interValComunes(dicc3,dicc4);
		mostrarDicMultiple(diccInterValores);
		dicc4.Agregar(50, 450);
		dicc4.Agregar(60, 1000);
		
		dicc3.Agregar(100, 1050);
		dicc3.Agregar(150, 40);
		
		System.out.println("Ejercicio (C): Unir todos los valores de claves iguales:");
		DiccionarioMultipleTDA diccInterClaves = interClaComunes(dicc3,dicc4);
		mostrarDicMultiple(diccInterClaves);
	}

}
