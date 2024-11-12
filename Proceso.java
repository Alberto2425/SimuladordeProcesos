import java.util.Random;
public class Proceso {
	Random random=new Random();

	//atributos
	private boolean seEjecuto;
	static int idr=0;
    private int id;
	private int tiempoR;
	private int estado;
	private int prioridad;
	private int quantum;

	public Proceso(int quantum) {
		seEjecuto=false;
		idr++;
		this.id = idr;
		this.tiempoR = random.nextInt(8)+3;
		this.estado = random.nextInt(2)+1;
		this.prioridad=random.nextInt(4)+1;
		this.quantum=quantum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTiempoR() {
		return tiempoR;
	}

	public void setTiempoR(int tiempoR) {
		this.tiempoR = tiempoR;
		seEjecuto=true;
	}

	public int getEstado() {
		return estado;

	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public boolean getSeEjecuto(){
		return seEjecuto;
	}
	
}