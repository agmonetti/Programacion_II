package interfaz;

public interface GrafoTDA {
	public void InicializarGrafo();
	public void AgregarVertice(int x);
	public void EliminarVertice(int x);
	public void AgregarArista(int o, int d, int p);
	public void EliminarArista(int o, int d);
	public int PesoArista(int o, int d);
	public boolean ExisteArista (int o, int d);
	public ConjuntoTDAD Vertices();
}