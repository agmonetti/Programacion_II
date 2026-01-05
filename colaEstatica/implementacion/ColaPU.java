package implementacion;

import interfaz.ColaTDA;

public class ColaPU implements ColaTDA {
		int[] arr;
		int cant;
		
		@Override
		public void InicializarCola() {
			arr = new int[20];
			cant = 0;
		}

		@Override
		public void Acolar(int x) {
			//voy agregando al inicio
			//tengo que ir corriendo todo, es decir, para dejar libre la posicion 0, corro todo uno a la derecha
			//tengo que empiezar por el ultimo y me voy corriendo hacia la izquierda
			for(int i = cant; i > 0; i--)
				arr[i] = arr[i-1]; //en la posición i guardo lo que había en la posición i-1
			arr[0] = x;
			cant++;
		}


		@Override
		public void Desacolar() {
			//el primero que entra, es el primero que sale
			//el ultimo que entra es el ultimo que sale
			cant --;
		}

		@Override
		public int Primero() {
			return arr[cant-1]; //es el ultimo (primero que agregue)
		}

		@Override
		public boolean ColaVacia() {
			return (cant == 0);
		}



}
