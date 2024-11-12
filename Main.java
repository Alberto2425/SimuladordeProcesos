
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Main {
	static Reporte r=new Reporte();
	static Random random = new Random();
		static int tiempoM=random.nextInt(11)+15;
		public static void main(String[] args) {
			Scanner leer=new Scanner(System.in);
			String cadena;
			int tipoPlanificacion;
			int tipoAlgoritmo;
			Random random = new Random();
			int nProcesos = random.nextInt(10) + 1;
			ArrayList<Proceso>procesos=new ArrayList<Proceso>();
			int quantum=random.nextInt(4)+1;
	
			//menu
			do{
				
				for(int i=0; i<nProcesos; i++) {
					Proceso p=new Proceso(quantum);
					procesos.add(p);
				}
	
			System.out.println("Que tipo de algoritmo de planificacion usar por default?");
			System.out.println("1.- apropiativo \n2.- no apropiativo\n3.- Cerrar programa");
	
					do{
						cadena=leer.nextLine();
						if(isNumeric(cadena)==true){
							tipoPlanificacion = Integer.parseInt(cadena);
						}else{
							tipoPlanificacion=3;
						}
						 if(tipoPlanificacion<1 || tipoPlanificacion>3){
							System.out.println("Agregue un dato entero entre 1 y 3 para continuar.\n1.- apropiativo n\2.- no apropiativo\2.- Cerrar Programa");
						 }
					}while(tipoPlanificacion<1 || tipoPlanificacion>3);
					if(tipoPlanificacion==3){break;}
				
			System.out.println("Que algoritmo usaras?");
			System.out.println("1.- Round-robin\n2.- Prioridades\n3.- Multiples colar de prioridad\n4.- Proceso mas corto primero\n5.-Boletos de Loteria\n6.- Participacion equitativa");
			do{
				cadena=leer.nextLine();
				if(isNumeric(cadena)==true){
					tipoAlgoritmo = Integer.parseInt(cadena);
				}else{
					tipoAlgoritmo=7;
				}
				 if(tipoAlgoritmo<1 || tipoAlgoritmo>6){
					System.out.println("Agregue un dato entero entre 1 y 6.");
					System.out.println("1.- Round-robin\n2.- Prioridades\n3.- Multiples colar de prioridad\n4.- Proceso mas corto primero\n5.-Boletos de Loteria\n6.- Participacion equitativa");
				 }
			}while(tipoAlgoritmo<1 || tipoAlgoritmo>6);
	
			switch (tipoAlgoritmo) {
				case 1:
					//Llamar al metodo RoundRobin
					roundRobin(procesos, tipoPlanificacion);
					break;
				case 2:
					// Llamar al método de Prioridades
					prioridades(procesos, tipoPlanificacion);
					break;
				case 3:
					// Llamar al método de Múltiples colas de prioridad
					prioridadesMultiples(procesos, tipoPlanificacion);
					break;
				case 4:
					// Llamar al método de Proceso más corto primero
					cortoF(procesos,tipoPlanificacion);
					break;
				case 5:
					// Llamar al método de Planificacion Garantizada
					planificacionG(procesos, tipoPlanificacion);
					break;
				case 6:
					// Llamar al método de Boletos de loteria
					boletos(procesos, tipoPlanificacion);
					break;
				default:
					System.out.println("Opción no válida");
			}
			siEjecuto(procesos);
			noEjecuto(procesos);
			r.imprimirReporte();
		}while(tipoPlanificacion!=3);
	
			leer.close();
		}
		
		//Round-Robin
		public static void roundRobin(ArrayList<Proceso>c,int tipoPlanificacion) {
			if(tipoPlanificacion==1){
				//con cada iteracion eb¿ntra un proceso nuevo.
				for(int i=0;i<c.size();i++){
					System.out.print(". Entra Proceso "+c.get(i).getId()+" ");
					//se verifica que el proceso este listo
					if(c.get(i).getEstado()!=2){
						//si el proceso aun no esta listo se genera un random para saber si se desbloqueo
						c.get(i).setEstado(random.nextInt(2)+1);
					}
					if(c.get(i).getEstado()==2){
						//si el proceso esta listo se resta el valor del quantum al tiempo restante
						c.get(i).setTiempoR(c.get(i).getTiempoR()-c.get(i).getQuantum());
						if(c.get(i).getTiempoR()<=0/*El proceso termino*/){
							System.out.print(", se ejecuta "+(c.get(i).getQuantum()+c.get(i).getTiempoR())+" unidades y termina\n");
							r.setB(c.get(i).getId());
							tiempoM=tiempoM-c.get(i).getTiempoR();/*si el proceso termino el tiempo de simulacion se le suma el resto del tiempo restante*/
                            c.remove(i);/*se remueve el proceso de la lista*/
							i--;
						}else{
							System.out.print(", se ejecuta "+c.get(i).getQuantum()+" unidades\n");
							c.get(i).setQuantum(c.get(i).getQuantum()*2);/*Si el proceso no termino el quantum se dobla*/
						}

					}else{System.out.print(", no se ejcuta porque sigue bloqueado \n");/*el proceso nu uso la CPU */}
				}
				tiempoM=random.nextInt(11)+15;
			}else{
				int x=0;
				int xaux=0;
				for(int i=0;i<c.size();i++){
					System.out.print(". Entra Proceso "+c.get(i).getId()+" ");
					//se verifica que el proceso este listo
					if(c.get(i).getEstado()!=2){
						//si el proceso aun no esta listo se genera un random para saber si se desbloqueo
						c.get(i).setEstado(random.nextInt(2)+1);
					}
					if(c.get(i).getEstado()==2){
						//si el proceso esta listo se resta el valor del quantum al tiempo restante
						for(int j=0;j<200;j++){
							x=random.nextInt(3)+2;
							xaux+=x;
							c.get(i).setTiempoR(c.get(i).getTiempoR()-x);
							tiempoM-=x;
							if(c.get(i).getTiempoR()<=0/*El proceso termino*/){
								break;
							}
							c.get(i).setEstado(random.nextInt(2)+1);
							if(c.get(i).getEstado()!=2){
								c.get(i).setEstado(random.nextInt(2)+1);
								break;
							}
						}
						
						if(c.get(i).getTiempoR()<=0/*El proceso termino*/){
							System.out.print(", se ejecuta "+(xaux+c.get(i).getTiempoR())+" unidades y termina\n");
							r.setB(c.get(i).getId());
							tiempoM=tiempoM-c.get(i).getTiempoR();/*si el proceso termino el tiempo de simulacion se le suma el resto del tiempo restante*/
                            c.remove(i);/*se remueve el proceso de la lista*/
							i--;
						}else{
							System.out.print(", se ejecuta "+xaux+" unidades\n");
							/*Si el proceso no termino*/
						}

					}else{System.out.print(", no se ejcuta porque sigue bloqueado \n");/*el proceso nu uso la CPU */}
					x=0;
					xaux=0;
				}
				tiempoM=random.nextInt(11)+15;

			}
    }

	//método de Prioridades
	public static void prioridades(ArrayList<Proceso>C,int tipoPlanificacion) {
		if(tipoPlanificacion==1){

		}else{

		}
    }
	//método de Múltiples colas de prioridad
	public static void prioridadesMultiples(ArrayList<Proceso>C,int tipoPlanificacion) {
		if(tipoPlanificacion==1){

		}else{

		}
    }
	//método de Proceso más corto primero
	public static void cortoF(ArrayList<Proceso>C,int tipoPlanificacion){
		if(tipoPlanificacion==1){

		}else{

		}
    }
	//método de Planificacion Garantizada
	public static void planificacionG(ArrayList<Proceso>C,int tipoPlanificacion) {
		if(tipoPlanificacion==1){

		}else{

		}
    }
	//método de Boletos de loteria
	public static void boletos(ArrayList<Proceso>C,int tipoPlanificacion) {
		if(tipoPlanificacion==1){

		}else{

		}
    }
	//método de Proceso más corto primero
	public static void CortoF(ArrayList<Proceso>C,int tipoPlanificacion) {
		if(tipoPlanificacion==1){

		}else{

		}
    }
	//método de Participacion Equitativa
	public static void participacionEquitativa(ArrayList<Proceso>C,int tipoPlanificacion) {
		if(tipoPlanificacion==1){

		}else{

		}
    }



	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
	public static void noEjecuto(ArrayList<Proceso>c){
		for(int i=0;i<c.size();i++){
			if(c.get(i).getSeEjecuto()==false){
				r.setB(c.get(i).getId());
			}
		}
	}
	public static void siEjecuto(ArrayList<Proceso>c){
		for(int i=0;i<c.size();i++){
			if(c.get(i).getSeEjecuto()==true){
				r.setC(c.get(i).getId());
			}
		}
	}
} 
