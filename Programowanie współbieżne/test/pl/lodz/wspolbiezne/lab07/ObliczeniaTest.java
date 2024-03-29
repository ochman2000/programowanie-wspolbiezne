package pl.lodz.wspolbiezne.lab07;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

public class ObliczeniaTest {

//	@Test
//	public void test01() {
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 17, 18, 19, 20 }, 
//					{ 21, 22, 23, 24 }, 
//					{ 25, 26, 27, 28 },
//					{ 29, 30, 31, 32 } };
//		
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		
//		MacierzeDto block01 = obliczenia.getBlock(0, 2);
//		assertEquals(4, block01.getColumns().size());
//		int C00 = block01.getColumn(0).getValue(0);
//		assertEquals(1, C00);
//		int C01 = block01.getColumn(0).getValue(1);
//		assertEquals(5, C01);
//		int C02 = block01.getColumn(0).getValue(2);
//		assertEquals(9, C02);
//		int C03 = block01.getColumn(0).getValue(3);
//		assertEquals(13, C03);
//		
//		int C10 = block01.getRow(1).getValue(0);
//		assertEquals(21, C10);
//		
//		int C11 = block01.getRow(1).getValue(1);
//		assertEquals(22, C11);
//		
//		int C12 = block01.getRow(1).getValue(2);
//		assertEquals(23, C12);
//		
//		int C13 = block01.getRow(1).getValue(3);
//		assertEquals(24, C13);
//	}
//	
//	@Test
//	public void test02() {
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 17, 18, 19, 20 }, 
//					{ 21, 22, 23, 24 }, 
//					{ 25, 26, 27, 28 },
//					{ 29, 30, 31, 32 } };
//		
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		
//		MacierzeDto block01 = obliczenia.getBlock(2, 4);
//		assertEquals(4, block01.getColumns().size());
//		int C00 = block01.getColumn(2).getValue(0);
//		assertEquals(3, C00);
//		int C01 = block01.getColumn(2).getValue(1);
//		assertEquals(7, C01);
//		int C02 = block01.getColumn(2).getValue(2);
//		assertEquals(11, C02);
//		int C03 = block01.getColumn(2).getValue(3);
//		assertEquals(15, C03);
//		
//		int C10 = block01.getRow(1).getValue(0);
//		assertEquals(29, C10);
//		
//		int C11 = block01.getRow(1).getValue(1);
//		assertEquals(30, C11);
//		
//		int C12 = block01.getRow(1).getValue(2);
//		assertEquals(31, C12);
//		
//		int C13 = block01.getRow(1).getValue(3);
//		assertEquals(32, C13);
//	}
//	
//	@Test
//	public void test03() {
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 17, 18, 19, 20 }, 
//					{ 21, 22, 23, 24 }, 
//					{ 25, 26, 27, 28 },
//					{ 29, 30, 31, 32 } };
//		
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		
//		MacierzeDto block01 = obliczenia.getBlock(0, 4);
//		int index;
//		
//		index = block01.getColumn(0).getIndex();
//		assertEquals(0, index);
//		
//		index = block01.getColumn(1).getIndex();
//		assertEquals(1, index);
//		
//		index = block01.getRow(2).getIndex();
//		assertEquals(2, index);
//		
//		index = block01.getRow(3).getIndex();
//		assertEquals(3, index);
//		
//		index = block01.getRow(0).getIndex();
//		assertEquals(0, index);
//		
//		index = block01.getRow(1).getIndex();
//		assertEquals(1, index);
//		
//		index = block01.getRow(2).getIndex();
//		assertEquals(2, index);
//		
//		index = block01.getRow(3).getIndex();
//		assertEquals(3, index);
//	}
//	
//	@Test
//	public void test04() {
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 17, 18, 19, 20 }, 
//					{ 21, 22, 23, 24 }, 
//					{ 25, 26, 27, 28 },
//					{ 29, 30, 31, 32 } };
//		
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		
//		MacierzeDto block24 = obliczenia.getBlock(2, 4);
//		int index;
//		
//		index = block24.getColumn(0).getIndex();
//		assertEquals(0, index);
//		
//		index = block24.getColumn(1).getIndex();
//		assertEquals(1, index);
//		
//		index = block24.getRow(0).getIndex();
//		assertEquals(2, index);
//		
//		index = block24.getRow(1).getIndex();
//		assertEquals(3, index);
//		
//	}
//	
	private static final int N = 4;
//	
//	@Test
//	public void test05() {
//		int LICZBA_PROCESOR�W=2;
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 17, 18, 19, 20 }, 
//					{ 21, 22, 23, 24 }, 
//					{ 25, 26, 27, 28 },
//					{ 29, 30, 31, 32 } };
//	
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		for (int proces = 0; proces < LICZBA_PROCESOR�W; proces++) {
//			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR�W);
//			int end = getEndOfInterval(proces, LICZBA_PROCESOR�W);
//			MacierzeDto block = obliczenia.getBlock(start, end);
//			assertEquals(N, block.getColumns().size());
//			assertEquals(N/LICZBA_PROCESOR�W, block.getRows().size());
//		}
//	}
//	@Test
//	public void test06() {
//		int LICZBA_PROCESOR�W=1;
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//		int[][] ab = new int[N][N];
//		
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		for (int proces=0; proces<LICZBA_PROCESOR�W; proces++) {
//			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR�W);
//			int end = getEndOfInterval(proces, LICZBA_PROCESOR�W);
//			MacierzeDto block = obliczenia.getBlock(start, end);
//			assertNotEquals(0, (long)block.getColumn(0).getValue(0));
//			ResultDto result = obliczenia.processInput(block);
//			System.out.println("Result "+proces+"\n"+result);
//			obliczenia.merge(result, ab);
//		}
//		System.out.println(Obliczenia.toString(ab));
//		int[][] C={ { 90, 100, 110, 120 },
//					{ 202, 228, 254, 280 },
//					{ 314, 356, 398, 440 },
//					{ 426, 484, 542, 600 } };
//		assertArrayEquals(C, ab);
//	}
//	
//	@Test
//	public void test07() {
//		int LICZBA_PROCESOR�W=2;
//		int[][] A={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//
//		int[][] B={ { 1, 2, 3, 4 },
//					{ 5, 6, 7, 8 },
//					{ 9, 10, 11, 12 },
//					{ 13, 14, 15, 16 } };
//		int[][] ab = new int[N][N];
//		
//		Obliczenia obliczenia = new Obliczenia(A, B);
//		for (int proces=0; proces<LICZBA_PROCESOR�W; proces++) {
//			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR�W);
//			int end = getEndOfInterval(proces, LICZBA_PROCESOR�W);
//			MacierzeDto block = obliczenia.getBlock(start, end);
//			assertNotEquals(0, (long)block.getColumn(0).getValue(0));
//			ResultDto result = obliczenia.processInput(block);
//			System.out.println("Result "+proces+"\n"+result);
//			obliczenia.merge(result, ab);
//		}
//		System.out.println(Obliczenia.toString(ab));
//		int[][] C={ { 90, 100, 110, 120 },
//					{ 202, 228, 254, 280 },
//					{ 314, 356, 398, 440 },
//					{ 426, 484, 542, 600 } };
//		assertArrayEquals(C, ab);
//	}
	
	@Test
	public void test08() {
		int LICZBA_PROCESOR�W=4;
		double[][] A={ { 1, 2, 3, 4 },
					{ 5, 6, 7, 8 },
					{ 9, 10, 11, 12 },
					{ 13, 14, 15, 16 } };

		double[][] B={ { 1, 2, 3, 4 },
					{ 5, 6, 7, 8 },
					{ 9, 10, 11, 12 },
					{ 13, 14, 15, 16 } };
		double[][] ab = new double[N][N];
		
		Obliczenia obliczenia = new Obliczenia(A, B);
		for (int proces=0; proces<LICZBA_PROCESOR�W; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR�W);
			int end = getEndOfInterval(proces, LICZBA_PROCESOR�W);
			MacierzeDto block = obliczenia.getBlock(start, end);
			assertNotEquals(0, block.getColumn(0).getValue(0));
			ResultDto result = obliczenia.processInput(block);
			System.out.println("Result "+proces+"\n"+result);
			obliczenia.merge(result, ab);
		}
		System.out.println(Obliczenia.toString(ab));
		double[][] C={ { 90, 100, 110, 120 },
					{ 202, 228, 254, 280 },
					{ 314, 356, 398, 440 },
					{ 426, 484, 542, 600 } };
		assertArrayEquals(C, ab);
	}
	
	@Test
	public void test09() {
		int LICZBA_PROCESOR�W=4;
		int N = 1024;
		
		//NIESTETY JAVA NIE JEST TAKA SPRYTNA I MUSZ� POWT�RZY� TO TRZY RAZY,
		//ABY KOMPILATOR PRZYPISA� INNE ADRESY
		double[][] A = new double[N][N];
		double[][] B = new double[N][N];
		double[][] C = new double[N][N];
		double[][] ab = new double[N][N];

		for (int i=0; i<N; i++) {
			A[i] = new Random().doubles(N).toArray();
			B[i] = new Random().doubles(N).toArray();
			C[i] = new Random().doubles(N).toArray();
		}
		
		Obliczenia obliczenia = new Obliczenia(A, B);
		for (int proces=0; proces<LICZBA_PROCESOR�W; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR�W);
			int end = getEndOfInterval(proces, LICZBA_PROCESOR�W);
			MacierzeDto block = obliczenia.getBlock(start, end);
			assertNotEquals(0, block.getColumn(0).getValue(0));
			ResultDto result = obliczenia.processInput(block);
			System.out.println("Przeprocesowano obliczenia na w�le "+(proces+1));
			obliczenia.merge(result, ab);
		}
	}
	
	@Test
	public void test10() {
		int LICZBA_PROCESOR�W=1;
		int N = 1024;
		
		//NIESTETY JAVA NIE JEST TAKA SPRYTNA I MUSZ� POWT�RZY� TO TRZY RAZY,
		//ABY KOMPILATOR PRZYPISA� INNE ADRESY
		double[][] A = new double[N][N];
		double[][] B = new double[N][N];
		double[][] C = new double[N][N];
		double[][] ab = new double[N][N];

		for (int i=0; i<N; i++) {
			A[i] = new Random().doubles(N).toArray();
			B[i] = new Random().doubles(N).toArray();
			C[i] = new Random().doubles(N).toArray();
		}
		
		Obliczenia obliczenia = new Obliczenia(A, B);
		for (int proces=0; proces<LICZBA_PROCESOR�W; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR�W);
			int end = getEndOfInterval(proces, LICZBA_PROCESOR�W);
			MacierzeDto block = obliczenia.getBlock(start, end);
			assertNotEquals(0, block.getColumn(0).getValue(0));
			ResultDto result = obliczenia.processInput(block);
			try {
				boolean si = false;
				System.out.println("Przeprocesowano obliczenia na w�le "+(proces+1));
				int sizeOfBlock = Obliczenia.sizeOf(block);
				String s = Obliczenia.humanReadableByteCount(sizeOfBlock, si);
				System.out.println("Block size: "+sizeOfBlock +" bytes ("+s+")");
				int sizeOfValues = Obliczenia.sizeOf(block.getColumn(1).getValues());
				s = Obliczenia.humanReadableByteCount(sizeOfValues, si);
				System.out.println("Block array size: "+sizeOfValues +" bytes ("+s+")");
				int sizeOfResult = Obliczenia.sizeOf(result);
				s = Obliczenia.humanReadableByteCount(sizeOfResult, si);
				System.out.println("Rozmiar pliku wynikowego: "+ sizeOfResult +" bytes ("+s+")");
			} catch (IOException e) {
				e.printStackTrace();
			}
			obliczenia.merge(result, ab);
		}
	}
	
	@Test
	public void test11() {
		String s = Obliczenia.humanReadableByteCount(1024*1024*1024, false);
		System.out.println(s);
	}
	
	public int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia� nie mo�e by� wi�kszy ni�: " + totalIntervals
							+ " a podano: " + interval);
		}
		double fraction = (double) interval / (double) totalIntervals;
		return (int) (fraction * N);
	}
	
	public int getEndOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia� nie mo�e by� wi�kszy ni�: " + totalIntervals
							+ " a podano: " + interval);
		}
		double rozmiarPrzedzialu = (double) N / (double) totalIntervals;
		double fraction = (double) interval / (double) totalIntervals;
		return (int) ((fraction * N) + rozmiarPrzedzialu);
	}
	
	
}