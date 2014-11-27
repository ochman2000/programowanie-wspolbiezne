package pl.lodz.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	@Test
	public void test01() {
		Optional<Adres> adres = Optional.of(new Adres());
		Optional<String> ulica = adres.map(p -> p.getUlica()).map(p -> p.getNazwa());
		System.out.println(ulica);
		assertNotNull(ulica);
	}
	
	@Test
	public void test02() {
		Optional<Adres> adres = Optional.ofNullable(new Adres());
		Optional<String> ulica = adres.map(p -> p.getUlica()).map(p -> p.getNazwa());
		System.out.println(ulica);
		assertNotNull(ulica);
	}

	@Test
	public void test03() {
		Optional<Adres> adres = Optional.of(new Adres());
		String ulica = adres.map(p -> p.getUlica()).map(p -> p.getNazwa()).orElse(null);
		System.out.println(ulica);
		assertNull(ulica);
	}
	
	@Test
	public void test04() {
		Optional<Adres> adres = Optional.ofNullable(new Adres());
		String ulica = adres.map(p -> p.getUlica()).map(p -> p.getNazwa()).orElse(null);
		System.out.println(ulica);
		assertNull(ulica);
	}
	
	@Test
	public void test05() {
		Adres adres = new Adres();
		adres.setUlica(new Ulica());
		String ulica = adres.getUlica().getNazwa();
		System.out.println(ulica);
		assertNotNull(ulica);
	}
	
	@Test(expected=NullPointerException.class)
	public void test06() {
		Optional<AdresOpt> adres = Optional.of(new AdresOpt());
		String ulica = adres.flatMap(p -> p.getUlicaOpt()).map(s -> s.getNazwaOpt()).map(t->t.toString()).orElse(null);
				System.out.println(ulica);
	}
	
	@Test(expected=NullPointerException.class)
	public void test07() {
		Optional<AdresOpt> adres = Optional.ofNullable(new AdresOpt());
		String ulica = adres.flatMap(p -> p.getUlicaOpt()).map(s -> s.getNazwaOpt()).map(t->t.toString()).orElse(null);
				System.out.println(ulica);
	}
	
	@Test
	public void test08() {
		Optional<AdresOpt> adres = Optional.of(new AdresOpt());
		adres.ifPresent( p -> p.setUlicaOpt(Optional.of(new UlicaOpt())) );
		String ulica = adres.flatMap(p -> p.getUlicaOpt()).map(s -> s.getNazwaOpt()).map(t->t.toString()).orElse(null);
				System.out.println(ulica);
		assertNotNull(ulica);
	}
	
	@Test(expected=NullPointerException.class)
	public void test09() {
		ArrayList<Adres> lista = new ArrayList<Adres>(4);
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		String ulica = lista.stream().map(p -> p.getUlica()).map(p -> p.getNazwa()).findAny().orElse(null);
				System.out.println(ulica);
	}
	
	@Test
	public void test10() {
		ArrayList<Adres> lista = new ArrayList<Adres>(4);
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		String ulica = lista.stream().filter(Objects::nonNull).map(p -> p.getUlica()).filter(Objects::nonNull).map(p -> p.getNazwa()).findAny().orElse(null);
				System.out.println(ulica);
		assertNull(ulica);
	}
	
}
