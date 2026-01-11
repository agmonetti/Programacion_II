package interfaz;

public interface ConjuntoAC {
	void InicializarConjunto();
	void Agregar(int x);
	void Sacar(int x);
	int Elegir();
	boolean Pertenece(int x);
	boolean ConjuntoVacio();
}
