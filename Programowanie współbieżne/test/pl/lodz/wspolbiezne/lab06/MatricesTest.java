package pl.lodz.wspolbiezne.lab06;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatricesTest {

	@Test
	public void test01() {
		Matrices matrices = new Matrices(2, 2);
		matrices.setA(new int[][]{{2,1},{0,7}});
		matrices.setB(new int[][]{{6,1},{4,5}});
		
		int C00 = matrices.multiply(0, 0);
		assertEquals(16, C00);
	}
	
	@Test
	public void test02() {
		Matrices matrices = new Matrices(2, 2);
		matrices.setA(new int[][]{{2,1},{0,7}});
		matrices.setB(new int[][]{{6,1},{4,5}});
		
		int C01 = matrices.multiply(0, 1);
		assertEquals(7, C01);
	}
	
	@Test
	public void test03() {
		Matrices matrices = new Matrices(2, 2);
		matrices.setA(new int[][]{{2,1},{0,7}});
		matrices.setB(new int[][]{{6,1},{4,5}});
		
		int C10 = matrices.multiply(1, 0);
		assertEquals(28, C10);
	}
	
	@Test
	public void test04() {
		Matrices matrices = new Matrices(2, 2);
		matrices.setA(new int[][]{{2,1},{0,7}});
		matrices.setB(new int[][]{{6,1},{4,5}});
		
		int C11 = matrices.multiply(1, 1);
		assertEquals(35, C11);
	}

}
