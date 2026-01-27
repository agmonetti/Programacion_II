package principal;

import implementacion.ColaLD;
import implementacion.ConjuntoLDA;
import implementacion.pilaTF;
import interfaz.ConjuntoTDAD;
import interfaz.ColaTDA;
import interfaz.pilaTDA;

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
	
	//Generar el conjunto de elementos que están tanto en la Pila P y en la Cola C
	// tengo una pila P = {1,2,3,4,5}
	// tengo una cola C = {1,2,3}
	// debo generar conjunto -> {1,2,3}
	public static ConjuntoTDAD conjPilaMasCola(pilaTDA pila, ColaTDA cola) {
		ConjuntoTDAD conjResultante = new ConjuntoLDA();
		conjResultante.InicializarConjunto();
		
		//estrategia:volcar toda la cola en un conjunto auxiliar, entonces, saco un valor de la pila y preg si pertenece
		ConjuntoTDAD colaAuxiliar = new ConjuntoLDA();
		colaAuxiliar.InicializarConjunto();
		while(!cola.ColaVacia()) {
			colaAuxiliar.Agregar(cola.Primero());
			cola.Desacolar();
		}
		//Ahora, recorro la pila, preguntando si pertenece dicho valor a la cola(ahora conjunto).
		int valor;
		while(!pila.PilaVacia()) {
			valor = pila.Tope();
			if(colaAuxiliar.Pertenece(valor))
				conjResultante.Agregar(valor);
			pila.Desapilar();
		}
		
		return conjResultante;
		
	}
	
	//Determinar si los elementos de una Pila P son los mismos que los de una Cola C.
		//No interesa el orden ni si están repetidos o no.
		//	Pila: { 1, 2, 2, 3 }
		//	Cola: { 3, 1, 2 }
		//	Resultado: TRUE
	public static boolean pilaIgualCola(ColaTDA cola, pilaTDA pila) {
		//estrategia: Volcar pila y cola en dos conjuntos distintos, luego uso metodo de conj iguales.
		ConjuntoTDAD conjuntoPila = new ConjuntoLDA();
		conjuntoPila.InicializarConjunto();
		while(!pila.PilaVacia()) {
			conjuntoPila.Agregar(pila.Tope());
			pila.Desapilar();
		}
		
		ConjuntoTDAD conjuntoCola = new ConjuntoLDA();
		conjuntoCola.InicializarConjunto();
		while(!cola.ColaVacia()) {
			conjuntoCola.Agregar(cola.Primero());
			cola.Desacolar();
		}
		
		return (conjIguales(conjuntoPila,conjuntoCola));
		
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
		
		pilaTDA pila = new pilaTF();
		ColaTDA cola = new ColaLD();
		pila.InicializarPila();
		cola.InicializarCola();
		
//		pila.Apilar(12);
//		pila.Apilar(25);//igual
//		pila.Apilar(35);
//		pila.Apilar(40);
//		pila.Apilar(500); //igual
//		pila.Apilar(1000);//igual
//		pila.Apilar(750); //igual
//		
//		cola.Acolar(25);
//		cola.Acolar(500); //igual
//		cola.Acolar(25); //igual
//		cola.Acolar(214);
//		cola.Acolar(548);
//		cola.Acolar(1000);//igual
//		cola.Acolar(750);//igual
		pila.Apilar(1);
		pila.Apilar(2);
		pila.Apilar(3);
		
		cola.Acolar(3);
		cola.Acolar(1);
		cola.Acolar(2);
	
//		ConjuntoTDAD conjEnConj = conjPilaMasCola(pila,cola);
//		mostrarConjuntoCopia(conjEnConj);
		System.out.println(pilaIgualCola(cola,pila));
		
	}

}






