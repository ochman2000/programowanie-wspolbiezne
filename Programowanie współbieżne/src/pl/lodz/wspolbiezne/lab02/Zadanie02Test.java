package pl.lodz.wspolbiezne.lab02;

import static org.junit.Assert.*;

import org.junit.Test;

public class Zadanie02Test {

	@Test
	public void test01() {
		int odp = Zadanie02.getBeginningOfInterval(0, 2);
		assertEquals(0, odp);
	}
	
	@Test
	public void test02() {
		int odp = Zadanie02.getBeginningOfInterval(1, 2);
		assertEquals(2_000_000, odp);
	}
	
	@Test
	public void test03() {
		int odp = Zadanie02.getEndOfInterval(0, 2);
		assertEquals(2_000_000, odp);
	}
	
	@Test
	public void test04() {
		int odp = Zadanie02.getEndOfInterval(1, 2);
		assertEquals(4_000_000, odp);
	}
	
	@Test
	public void test05() {
		int odp = Zadanie02.getBeginningOfInterval(0, 4);
		assertEquals(0, odp);
	}
	
	@Test
	public void test06() {
		int odp = Zadanie02.getBeginningOfInterval(3, 4);
		assertEquals(3_000_000, odp);
	}
	
	@Test
	public void test07() {
		int odp = Zadanie02.getEndOfInterval(0, 4);
		assertEquals(1_000_000, odp);
	}
	
	@Test
	public void test08() {
		int odp = Zadanie02.getEndOfInterval(3, 4);
		assertEquals(4_000_000, odp);
	}
}
