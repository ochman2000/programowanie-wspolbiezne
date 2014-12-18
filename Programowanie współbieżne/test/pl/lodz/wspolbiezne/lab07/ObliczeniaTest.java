package pl.lodz.wspolbiezne.lab07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObliczeniaTest {

	@Test
	public void test01() {
		int[][] A={ { 1, 2, 3, 4 },
					{ 5, 6, 7, 8 },
					{ 9, 10, 11, 12 },
					{ 13, 14, 15, 16 } };

		int[][] B={ { 17, 18, 19, 20 }, 
					{ 21, 22, 23, 24 }, 
					{ 25, 26, 27, 28 },
					{ 29, 30, 31, 32 } };
		
		Obliczenia obliczenia = new Obliczenia(A, B);
		
		MacierzeDto block01 = obliczenia.getBlock(0, 1);
		int C00 = block01.getColumn(0).getValue(0);
		assertEquals(1, C00);
		int C01 = block01.getColumn(0).getValue(1);
		assertEquals(5, C01);
	}
	
	@Test
	public void test02() {
		int[][] A={ { 1, 2, 3, 4 },
					{ 5, 6, 7, 8 },
					{ 9, 10, 11, 12 },
					{ 13, 14, 15, 16 } };

		int[][] B={ { 17, 18, 19, 20 }, 
					{ 21, 22, 23, 24 }, 
					{ 25, 26, 27, 28 },
					{ 29, 30, 31, 32 } };
		
		Obliczenia obliczenia = new Obliczenia(A, B);
		
		MacierzeDto block01 = obliczenia.getBlock(0, 4);
		int index;
		
		index = block01.getColumn(0).getIndex();
		assertEquals(0, index);
		
		index = block01.getColumn(1).getIndex();
		assertEquals(1, index);
		
		index = block01.getRow(2).getIndex();
		assertEquals(2, index);
		
		index = block01.getRow(3).getIndex();
		assertEquals(3, index);
		
		index = block01.getRow(0).getIndex();
		assertEquals(0, index);
		
		index = block01.getRow(1).getIndex();
		assertEquals(1, index);
		
		index = block01.getRow(2).getIndex();
		assertEquals(2, index);
		
		index = block01.getRow(3).getIndex();
		assertEquals(3, index);
	}
}