package pl.lodz.wspolbiezne.lab06;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatricesTest {

	@Test
	public void test01() {
		Matrices matrices = new Matrices(
			new int[][]{{2,1},
						{0,7}},
			
			new int[][]{{6,1},
						{4,5}});
		
		int C00 = matrices.multiply(0, 0);
		assertEquals(16, C00);
	}
	
	@Test
	public void test02() {
		Matrices matrices = new Matrices(
			new int[][]{{2,1},
						{0,7}},
			
			new int[][]{{6,1},
						{4,5}});
		
		int C01 = matrices.multiply(0, 1);
		assertEquals(7, C01);
	}
	
	@Test
	public void test03() {
		Matrices matrices = new Matrices(
			new int[][]{{2,1},
						{0,7}},
			
			new int[][]{{6,1},
						{4,5}});
		
		int C10 = matrices.multiply(1, 0);
		assertEquals(28, C10);
	}
	
	@Test
	public void test04() {
		Matrices matrices = new Matrices(
			new int[][]{{2,1},
						{0,7}},
			
			new int[][]{{6,1},
						{4,5}});
		
		int C11 = matrices.multiply(1, 1);
		assertEquals(35, C11);
	}
	
	//3D
	@Test
	public void test05() {
		Matrices matrices = new Matrices(
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}},
			
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}});
		
		int C00 = matrices.multiply(0, 0);
		assertEquals(30, C00);
	}
	
	@Test
	public void test06() {
		Matrices matrices = new Matrices(
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}},
			
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}});
		
		int C33 = matrices.multiply(1, 1);
		assertEquals(81, C33);
	}
	
	@Test
	public void test07() {
		Matrices matrices = new Matrices(
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}},
			
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}});
		
		int C21 = matrices.multiply(2, 2);
		assertEquals(150, C21);
	}
	
	@Test
	public void test08() {
		Matrices matrices01 = new Matrices(
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}},
		
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}});
		
		Matrices matrices02 = new Matrices(
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}},
			
			new int[][]{	{1,2,3},
							{4,5,6},
							{7,8,9}});
		
		int matrixA = matrices01.multiply(2, 1);
		int matrixB = matrices02.multiply(2, 1);
		assertEquals(matrixA, matrixB);
	}
}