package conc;

public class CvThread extends Thread {
	
	private double[] elements;

	private ConcurVector cv;
	
	private Operations op;
	
	private int rangoInicial;
	private int rangoFinal;
	
	public CvThread(double[] elements){
		this.elements = elements;
	}
	
	public void run(){
		
		while(true){
			
		}
		
	}

	public void loadRange(int a, int b) {
		this.rangoInicial=a;
		this.rangoFinal=b;
	}

	public void abs() {

		for (int i = rangoInicial ; i < this.rangoFinal; ++i)
			this.cv.set(i, Math.abs(cv.get(i)));
	}

	
	
	
	
}


