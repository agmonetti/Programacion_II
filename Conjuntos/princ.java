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
	
	//metodo para ver la diferencia simetrica entre dos conjuntos
	//DIF SIMETRICA:
	//Conjunto A = { 1, 2, 3 }
	//Conjunto B = { 3, 4, 5 }

	//DIF: {1,2,4,5}
	
	//SIN UTILIZAR OPERACIONES DE INSERCION, UNION E INTERSECCION
	public static ConjuntoTDAD difSimetrica(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		ConjuntoTDAD copia1 = copiarConjunto(c1);
		ConjuntoTDAD copia2 = copiarConjunto(c2);
		
		//logica: Tomo un numero de A, lo comparo con todos los de B
		
		ConjuntoTDAD cjResultante = new ConjuntoLDA();
		cjResultante.InicializarConjunto();
		
		int valor;
		
		while(!copia1.ConjuntoVacio()) {
			valor = copia1.Elegir();
			if(!c2.Pertenece(valor))
//				System.out.println("valor a agregar: " + valor);
				cjResultante.Agregar(valor);
			
			copia1.Sacar(valor);
		}
		//ahora, tomo un numero de B y lo comparo ocn todos los de A
		while(!copia2.ConjuntoVacio()) {
			valor =copia2.Elegir();
			if(!c1.Pertenece(valor))
//				System.out.println("valor a agregar: " + valor);
				cjResultante.Agregar(valor);
			
			copia2.Sacar(valor);
		}
		
		return cjResultante;
			
		
	}
	 
	//metodo para unir dos cj - Combino todo
	public static ConjuntoTDAD unir(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		ConjuntoTDAD copia1 = copiarConjunto(c1);
		ConjuntoTDAD copia2 = copiarConjunto(c2);
		
		ConjuntoTDAD cjResultante = new ConjuntoLDA();
		cjResultante.InicializarConjunto();
		int valor;
		while(!copia1.ConjuntoVacio()) {
			valor = copia1.Elegir();
			cjResultante.Agregar(valor);
			copia1.Sacar(valor);
		}
		while(!copia2.ConjuntoVacio()) {
			valor = copia2.Elegir();
			cjResultante.Agregar(valor);
			copia2.Sacar(valor);
		}
		
		return cjResultante;
		
	}
	
	//metodo para intersecar dos cj - Solo lo que tienen en común
	public static ConjuntoTDAD intersecar(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		ConjuntoTDAD copia1 = copiarConjunto(c1);		
		ConjuntoTDAD cjResultante = new ConjuntoLDA();
		cjResultante.InicializarConjunto();
		
		int valor;
		
		while(!copia1.ConjuntoVacio()) {
			valor = copia1.Elegir();
			if(c2.Pertenece(valor))
				cjResultante.Agregar(valor);
			
			copia1.Sacar(valor);
		}
		return cjResultante;
	}
	
	//metodo para restar dos cj - depende:
	//	A - B: Lo que A tiene, que no tiene B
	//	B - A: Lo que B tiene, que no tiene A
	public static ConjuntoTDAD restar(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		ConjuntoTDAD copia1 = copiarConjunto(c1);			
		ConjuntoTDAD cjResultante = new ConjuntoLDA();
		cjResultante.InicializarConjunto();
		
		int valor;
		
		while(!copia1.ConjuntoVacio()) {
			valor = copia1.Elegir();
			if(!c2.Pertenece(valor))
				cjResultante.Agregar(valor);
			
			copia1.Sacar(valor);
		}
		return cjResultante;
	}
	
	//metodo para calcular la diferencia simetrica, utilizando los metodos logicos
	public static ConjuntoTDAD difSimetricaLogica1(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		//CAMINO1 
		ConjuntoTDAD resta12 = restar(c1,c2); //tengo todo lo exclusivo de c1
		ConjuntoTDAD resta21 = restar(c2,c1); //tengo todo lo exclusivo de c2
		
		//uno las exclusividades
		ConjuntoTDAD cjResultante =unir(resta12,resta21);
		return cjResultante;
		
	}
	//metodo para calcular la diferencia simetrica, utilizando los metodos logicos
	public static ConjuntoTDAD difSimetricaLogica2(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		//CAMINO2
		ConjuntoTDAD unionCompleta = unir(c1,c2); //tengo el conjunto completo
		ConjuntoTDAD diferencia = intersecar(c1,c2); //tengo lo que se repite en los conjuntos
		
		//ahora calculo la resta. Es decir, lo que tengo en la union, que no esta en la diferencia
		ConjuntoTDAD cjResultante = restar(unionCompleta,diferencia);
		return cjResultante;
	}
	
	//metodo para ver si dos conjuntos son iguales
	public static boolean conjIguales(ConjuntoTDAD c1, ConjuntoTDAD c2) {
		int cantC1 = contar(c1);
		int cantC2 = contar(c2);
		
		//si cantidad es distinta nunca van a ser iguales
		if (cantC1 != cantC2)
			return false;
		
		//aca, puede ser que el orden de los valores no sea el mismo, por eso vlaido con "Pertenece()"
		else {
			ConjuntoTDAD copia1 = copiarConjunto(c1);
			int valor;
			
			//verifico si los valores de A estan en B
			while(!copia1.ConjuntoVacio()) {
				valor = copia1.Elegir();
				if(!c2.Pertenece(valor))
					return false;
				copia1.Sacar(valor);
						
			}
		}
		
		return true;
		
		
	}
	
	//metodo que devuleve la cardinalidad de un conjunto
	public static int contar(ConjuntoTDAD c1) {
		ConjuntoTDAD copia1 = copiarConjunto(c1);
		int cont = 0;
		int valor;
		while(!copia1.ConjuntoVacio()) {
			cont++;
			valor=copia1.Elegir();
			copia1.Sacar(valor);
		}
		return cont;
	}
	
	public static void main(String[] args) {
		ConjuntoTDAD conjunto1 = new ConjuntoLDA();
		conjunto1.InicializarConjunto();
		ConjuntoTDAD conjunto2 = new ConjuntoLDA();
		conjunto2.InicializarConjunto();
		
//		conjunto1.Agregar(1);
//		conjunto1.Agregar(2);
//		conjunto1.Agregar(3);
//		
//		
//		conjunto2.Agregar(3);
//		conjunto2.Agregar(4);
//		conjunto2.Agregar(5);
//		
//		System.out.println("conjunto 1:");
//		mostrarConjuntoCopia(conjunto1);
//		
//		System.out.println();
//		
//		System.out.println("conjunto 2:");
//		mostrarConjuntoCopia(conjunto2);
////		mostrarConjuntoCopia(conjunto1);
////		conjunto1.Agregar(2);
//		System.out.println();
//		System.out.println("Diferencia simetrica entre los conjuntos 1 y 2: ");
//		ConjuntoTDAD dif = difSimetrica(conjunto1, conjunto2);
//		mostrarConjuntoCopia(dif);
//		System.out.println();
//		System.out.println("unir dos conjutnos");
//		ConjuntoTDAD union = unir(conjunto1,conjunto2);
//		mostrarConjuntoCopia(union);
//		System.out.println();
//		System.out.println("intersecar dos conjutnos");
//		ConjuntoTDAD intersec = intersecar(conjunto1,conjunto2);
//		mostrarConjuntoCopia(intersec);
//		System.out.println();
//		System.out.println("restar dos conjutnos");
//		ConjuntoTDAD rest = restar(conjunto1,conjunto2);
//		mostrarConjuntoCopia(rest);
//		
//		System.out.println();
//		System.out.println("diferencia simetrica de dos conjutnos(camino 1 logico)");
//		ConjuntoTDAD dif1 = difSimetricaLogica1(conjunto1,conjunto2);
//		mostrarConjuntoCopia(dif1);
//		System.out.println();
//		System.out.println("diferencia simetrica de dos conjutnos(camino 2 logico)");
//		ConjuntoTDAD dif2 = difSimetricaLogica2(conjunto1,conjunto2);
//		mostrarConjuntoCopia(dif2);
		
		conjunto1.Agregar(1);
		conjunto1.Agregar(2);
		conjunto1.Agregar(3);
		
		conjunto2.Agregar(2);
		conjunto2.Agregar(3);
		conjunto2.Agregar(1);
		
		System.out.println("c1 y c2 son iguales? ->" + conjIguales(conjunto1,conjunto2));
		
	}

}
