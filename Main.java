import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Main {
	static Reporte r=new Reporte();
	static Random random = new Random();
	static I Comparator=new I();
	static ArrayList<Proceso> c2=new ArrayList<Proceso>();
	static int tiempoM=random.nextInt(11)+15;;
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
			System.out.println("El tiempo de simulacion es : "+tiempoM);
			imprimirProcesos(procesos);
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
				Ejecuto(procesos);
			r.imprimirReporte();
			imprimirProcesos(procesos);
			tiempoM=random.nextInt(11)+15;

			nProcesos = random.nextInt(10) + 1;
		}while(tipoPlanificacion!=3);
	
			leer.close();
		}
		

	//Round-Robin
	public static void roundRobin(ArrayList<Proceso>c,int tipoPlanificacion) {
		if(tipoPlanificacion==1/*Apropiativo */){
			//con cada iteracion entra un proceso nuevo.
			for(int i=0;i<c.size();i++){
				if(tiempoM<=0){break;}
				r.setD(1);
				System.out.print(r.getD()+". Entra Proceso "+c.get(i).getId()+" ");
				//se verifica que el proceso este listo
				if(c.get(i).getEstado()!=2){
					//si el proceso aun no esta listo se genera un random para saber si se desbloqueo
					c.get(i).setEstado(random.nextInt(2)+1);
				}
				if(c.get(i).getEstado()==2){
					//si el proceso esta listo se resta el valor del quantum al tiempo restante
					c.get(i).setTiempoR(c.get(i).getTiempoR()-c.get(i).getQuantum());
					tiempoM-=c.get(i).getQuantum();
					if(tiempoM<=0){
						c.get(i).setTiempoR(c.get(i).getTiempoR()-tiempoM);
						tiempoM=0;
					}
					if(c.get(i).getTiempoR()<=0/*El proceso termino*/){
						r.setA(c.get(i).getId());
						System.out.print(", se ejecuta "+(c.get(i).getQuantum()+c.get(i).getTiempoR())+" unidades y termina\n");
						tiempoM-=c.get(i).getTiempoR();/*si el proceso termino el tiempo de simulacion se le suma el resto del tiempo restante*/
						c.remove(i);/*se remueve el proceso de la lista*/
						i--;
					}else{
						System.out.print(", se ejecuta "+c.get(i).getQuantum()+" unidades\n");
						c.get(i).setQuantum(c.get(i).getQuantum()*2);/*Si el proceso no termino el quantum se dobla*/
					}
				}else{System.out.print(", no se ejcuta porque sigue bloqueado \n");/*el proceso nu uso la CPU */}
				if(i==c.size()-1){i=-1;}
			}
		}else{/*No Apropiativo */
			int x=0;
			int xaux=0;
			for(int i=0;i<c.size();i++){
				if(tiempoM<=0){break;}
				r.setD(1);
				System.out.print(". Entra Proceso "+c.get(i).getId()+" ");
				//se verifica que el proceso este listo
				if(c.get(i).getEstado()!=2){
					//si el proceso aun no esta listo se genera un random para saber si se desbloqueo
					c.get(i).setEstado(random.nextInt(2)+1);
				}
				if(c.get(i).getEstado()==2){
					//si el proceso esta listo se resta el valor un valor aleatorio al tiempo restante
					for(int j=0;j<200;j++){
						x=random.nextInt(3)+2;
						xaux+=x;
						c.get(i).setTiempoR(c.get(i).getTiempoR()-x);
						tiempoM-=x;
					if(tiempoM<=0){
						c.get(i).setTiempoR(c.get(i).getTiempoR()-tiempoM);
						xaux+=tiempoM;
						tiempoM=0;
					}
						if(c.get(i).getTiempoR()<=0/*El proceso termino*/){
							xaux+=c.get(i).getTiempoR();
							break;
						}
						c.get(i).setEstado(random.nextInt(2)+1);
						if(c.get(i).getEstado()!=2){
							c.get(i).setEstado(random.nextInt(2)+1);
							break;
						}
					}
					if(c.get(i).getTiempoR()<=0/*El proceso termino*/){
						System.out.print(", se ejecuta "+xaux+" unidades y termina\n");
						r.setA(c.get(i).getId());
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
				if(i==c.size()-1){i=-1;}
			}
		}
	}
	//método de Prioridades
	public static void prioridades(ArrayList<Proceso>c,int tipoPlanificacion) {
		Collections.sort(c);
		roundRobin(c, tipoPlanificacion);
	}

	//método de Múltiples colas de prioridad
	public static void prioridadesMultiples(ArrayList<Proceso>c,int tipoPlanificacion) {
		ArrayList<Proceso> p4=new ArrayList<Proceso>();
		ArrayList<Proceso> p3=new ArrayList<Proceso>();
		ArrayList<Proceso> p2=new ArrayList<Proceso>();
		ArrayList<Proceso> p1=new ArrayList<Proceso>();
		for(int i=0;i<c.size();i++){
			if(c.get(i).getPrioridad()==4){
				p4.add(c.get(i));
			}
			if(c.get(i).getPrioridad()==3){
				p3.add(c.get(i));
			}
			if(c.get(i).getPrioridad()==2){
				p2.add(c.get(i));
			}
			if(c.get(i).getPrioridad()==1){p1.add(c.get(i));}
		}
		roundRobin(p4, tipoPlanificacion);
		roundRobin(p3, tipoPlanificacion);
		roundRobin(p2, tipoPlanificacion);
		roundRobin(p1, tipoPlanificacion);

		for(int i=c.size()-1;i>-1;i--){
			c.remove(0);
		}
		for(int i=0;i<p4.size();i++){
			c2.add(p4.get(i));
		}
		for(int i=0;i<p3.size();i++){
			c2.add(p3.get(i));
		}
		for(int i=0;i<p2.size();i++){
			c2.add(p2.get(i));
		}
			for(int i=0;i<p1.size();i++){
			c2.add(p1.get(i));
		}
		for(int i=0;i<c2.size();i++){
			c.add(c2.get(i));
		}

	}

	//método de Proceso más corto primero
	public static void cortoF(ArrayList<Proceso>c,int tipoPlanificacion){
		Collections.sort(c,Comparator);
		roundRobin(c, tipoPlanificacion); 
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
	public static void Ejecuto(ArrayList<Proceso>c){
		for(int i=0;i<c.size();i++){
			if(c.get(i).getSeEjecuto()==true){
				r.setC(c.get(i).getId());
			}
			if(c.get(i).getSeEjecuto()==false){
				r.setB(c.get(i).getId());
			}
		}
	}
	public static void imprimirProcesos(ArrayList<Proceso>c){
		System.out.println("=================================================");
		System.out.println("|| proceso || TiempoRes || estado || prioridad ||");
		System.out.println("=================================================");
		for(int i=0;i<c.size();i++){
			System.out.println(c.get(i).toString());		
		}
		System.out.println("=================================================");
	}
} 
