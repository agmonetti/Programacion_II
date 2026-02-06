package implementacion;

import interfaz.ABBTDA;

public class ABB implements ABBTDA {

	class NodoABB{
		int info;
		ABBTDA hijoIzq;
		ABBTDA hijoDer;
	}
	
	NodoABB raiz; //punto de partida
	
	@Override
	public void InicializarArbol() {
		raiz = null;
	}

	@Override
	public int Raiz() { //devuelve el numero que esta en la raiz
		return raiz.info;
	}

	@Override
	public ABBTDA HijoIzq() { // devuevlve el primer hijo izq
		return raiz.hijoIzq;
	}

	@Override
	public ABBTDA HijoDer() {// devuevlve el primer hijo der
		return raiz.hijoDer;
	}

	@Override
	public void AgregarElem(int x) {
		//verifico si la raiz no existe, entonces si no existe creo hijos izq y der
		if (this.ArbolVacio()) { //equivale a -> if (raiz == null) 
			raiz = new NodoABB();
			raiz.info = x;
			
			//creo los hijos de cada lado
			raiz.hijoIzq = new ABB();
			raiz.hijoDer = new ABB();
			
			raiz.hijoIzq.InicializarArbol();
			raiz.hijoDer.InicializarArbol();
		
		//si la raiz no es null (arbol ya estaba creado)	
		//Entonces pregunto por los hijos
		} else if (raiz.info > x) { //equivale a this.Raiz()
			raiz.hijoIzq.AgregarElem(x);
			
			// si entra alguna de estas llamadas recursivas, tambien se revisa el primer if, porque puede ser hoja
			
		} else if (raiz.info < x) {
			raiz.hijoDer.AgregarElem(x);
		} 
	}

	@Override
	public void EliminarElem(int x) {
		if (raiz != null) { //equivale a (!this.ArbolVacio())
		
		//el primer if es para ver si es una hoja y si es el elemento que buscamos
		//si es una hoja es que no tiene nada abajo
			if (raiz.info == x && raiz.hijoIzq.ArbolVacio() && raiz.hijoDer.ArbolVacio()) {
				raiz = null;
			}
			//caso de si no es una hoja. Significa que tiene algo a la izq o algo a la dere
			//o ambos casos
			//primero checkeamos si es el q buscamos y si el hijo izq no esta vacio
			} else if (raiz.info == x && !raiz.hijoIzq.ArbolVacio()) {
				raiz.info = this.mayor(raiz.hijoIzq);// busco el elemento mas grande y lo pongo en la raiz
				raiz.hijoIzq.EliminarElem(raiz.info); //llamo de nuevo al metodo
					
			} else if (raiz.info == x && raiz.hijoIzq.ArbolVacio()) { //el && no hace falta - es redundante porque si no fuese
				//vacio, hubiese entrado al otro else if
				
				//busco el mas chico del lado derecho
				raiz.info = this.menor(raiz.hijoDer);
				raiz.hijoDer.EliminarElem(raiz.info);
				
				//checkeo si la raiz no es el x que quiero eliminar	
				//entonces lo sigo buscando a la izq y a la derecha
			} else if (raiz.info < x) {
				raiz.hijoDer.EliminarElem(x);
			} else { // si x es > la raiz
				raiz.hijoIzq.EliminarElem(x);
			}
		}

	@Override
	public boolean ArbolVacio() {
		return (raiz == null);
	}

//funciones privadas recursivas

	private int mayor (ABBTDA a) {
		// si no tenemos hijo derecho devuelvo la raiz
		if (a.HijoDer().ArbolVacio()) {
			return a.Raiz();
	} else {
			return mayor(a.HijoDer()); //equivale a mayor(!this.HijoDere())
		}
	}
	
	private int menor (ABBTDA a) {
		if (a.HijoIzq().ArbolVacio()) {
			return a.Raiz();
		} else {
			return menor(a.HijoIzq());
		}
	}
}