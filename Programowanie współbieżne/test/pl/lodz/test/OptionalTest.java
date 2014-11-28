package pl.lodz.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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
		Optional<AdresOpt> adresOpt = Optional.of(new AdresOpt());
		Optional<UlicaOpt> ulicaOpt = Optional.of(new UlicaOpt()); 
		adresOpt.ifPresent(p -> p.setUlicaOpt(ulicaOpt));
		String ulica = adresOpt.flatMap(p -> p.getUlicaOpt()).map(s -> s.getNazwaOpt()).map(t->t.toString()).orElse(null);
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
	
	@Test(expected=NullPointerException.class)
	public void test11() {
		ArrayList<Adres> lista = new ArrayList<Adres>(4);
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		String ulica = lista.stream().map(o -> Optional.ofNullable(o)).map(p -> p.map(s -> s.getUlica().getNazwa())).map(t -> t.toString()).findAny().orElse(null);
				System.out.println(ulica);
		assertNull(ulica);
	}
	
	
	@Test
	public void test12() {
		ArrayList<Adres> lista = new ArrayList<Adres>(4);
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		lista.add(new Adres());
		Optional<Object> ulica = lista.stream().map(o -> Optional.ofNullable(o)).map(p -> p.map(s -> s.getUlica())).findAny().orElse(null);
		assertNotNull(ulica);
	}
	
	
	@Test
	public void test13() {
		ArrayList<Optional<AdresOpt>> lista = new ArrayList<>(4);
		lista.add(Optional.of(new AdresOpt()));
		lista.add(Optional.of(new AdresOpt()));
		lista.add(Optional.of(new AdresOpt()));
		lista.add(Optional.of(new AdresOpt()));
		Optional<Optional<Object>> ulica = lista.stream().map(p -> p.map(s -> s.getUlicaOpt())).findAny();
		assertNotNull(ulica);
	}
	
	@Test(expected=NullPointerException.class)
	public void test14() {
		ArrayList<Optional<AdresOpt>> lista = new ArrayList<>(4);
		lista.add(Optional.of(new AdresOpt()));
		lista.add(Optional.of(new AdresOpt()));
		lista.add(Optional.of(new AdresOpt()));
		lista.add(Optional.of(new AdresOpt()));
		Optional<Optional<AdresOpt>> adresOpt = lista.stream().findAny();
		UlicaOpt ulicaOpt = (UlicaOpt) adresOpt.flatMap(p -> p.map(s -> s.getUlicaOpt())).orElse(null);
		String ulica = ulicaOpt.getNazwaOpt().orElse(null);
		assertNotNull(ulica);
	}
	
}
