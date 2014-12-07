package pl.lodz.wspolbiezne.lab06;

import java.util.Random;

public class Obliczenia {
	
	private final int SIZE = 1024;
	
	//NIESTETY MUSZÊ POWTÓRZYÆ TO TRZY RAZY, ABY PRZYPISA£O INNE ADRESY
	double[][] a = new double[SIZE][SIZE];
	double[][] b = new double[SIZE][SIZE];
	double[][] c = new double[SIZE][SIZE];
	
	public Obliczenia() {
		for (int i=0; i<SIZE; i++) {
			a[i] = new Random().doubles(SIZE).toArray();
			b[i] = new Random().doubles(SIZE).toArray();
			c[i] = new Random().doubles(SIZE).toArray();
		}
//		for( double[] a : array ) { 
//            System.out.println( Arrays.toString( a ));
//        }		
	}
	
	public static void main(String[] args) {
		new Obliczenia();
	}
}
