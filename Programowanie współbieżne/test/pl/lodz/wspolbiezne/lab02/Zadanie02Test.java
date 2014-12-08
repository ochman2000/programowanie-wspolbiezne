package pl.lodz.wspolbiezne.lab02;

import static org.junit.Assert.assertEquals;

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
	
	@Test
	public void test09() {
		int odp = Zadanie02.getBeginningOfInterval(0, 3);
		assertEquals(0, odp);
	}
	
	@Test
	public void test10() {
		int odp = Zadanie02.getBeginningOfInterval(1, 3);
		assertEquals(1_333_333, odp);
	}
	
	@Test
	public void test11() {
		int odp = Zadanie02.getBeginningOfInterval(2, 3);
		assertEquals(2_666_666, odp);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test12() {
		Zadanie02.getBeginningOfInterval(3, 3);
	}
	
	
	@Test
	public void test13() {
		int odp = Zadanie02.getEndOfInterval(0, 3);
		assertEquals(1_333_333, odp);
	}
	
	@Test
	public void test14() {
		int odp = Zadanie02.getEndOfInterval(1, 3);
		assertEquals(2_666_666, odp);
	}
	
	@Test
	public void test15() {
		int odp = Zadanie02.getEndOfInterval(2, 3);
		assertEquals(4_000_000, odp);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test16() {
		Zadanie02.getEndOfInterval(3, 3);
	}
	
	@Test
	public void test17() {
		int odp = Zadanie02.getBeginningOfInterval(0, 6);
		assertEquals(0, odp);
	}
	
	@Test
	public void test18() {
		int odp = Zadanie02.getBeginningOfInterval(1, 6);
		assertEquals(666_666, odp);
	}
	
	@Test
	public void test19() {
		int odp = Zadanie02.getBeginningOfInterval(5, 6);
		assertEquals(3_333_333, odp);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test20() {
		Zadanie02.getBeginningOfInterval(6, 6);
	}
	
	
	@Test
	public void test21() {
		int odp = Zadanie02.getEndOfInterval(0, 6);
		assertEquals(666_666, odp);
	}
	
	@Test
	public void test22() {
		int odp = Zadanie02.getEndOfInterval(1, 6);
		assertEquals(1_333_333, odp);
	}
	
	@Test
	public void test23() {
		int odp = Zadanie02.getEndOfInterval(5, 6);
		assertEquals(4_000_000, odp);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test24() {
		Zadanie02.getEndOfInterval(6, 6);
	}
}
