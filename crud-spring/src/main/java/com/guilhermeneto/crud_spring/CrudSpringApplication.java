package com.guilhermeneto.crud_spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.guilhermeneto.crud_spring.models.Parceiros;
import com.guilhermeneto.crud_spring.repository.ParceirosRepository;

@SpringBootApplication
public class CrudSpringApplication {

	private final ParceirosRepository parceirosRepository;

	CrudSpringApplication(ParceirosRepository parceirosRepository) {
		this.parceirosRepository = parceirosRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ParceirosRepository repository) {

		return args -> {

			repository.deleteAll();

			List<Parceiros> parceiros = new ArrayList<>();

			parceiros.add(new Parceiros(null, 1, "Hydrogen", new BigDecimal("1.0079"), "H"));
			parceiros.add(new Parceiros(null, 2, "Helium", new BigDecimal("4.0026"), "He"));
			parceiros.add(new Parceiros(null, 3, "Lithium", new BigDecimal("6.941"), "Li"));
			parceiros.add(new Parceiros(null, 4, "Beryllium", new BigDecimal("9.0122"), "Be"));
			parceiros.add(new Parceiros(null, 5, "Boron", new BigDecimal("10.811"), "B"));
			parceiros.add(new Parceiros(null, 6, "Carbon", new BigDecimal("12.0107"), "C"));
			parceiros.add(new Parceiros(null, 7, "Nitrogen", new BigDecimal("14.0067"), "N"));
			parceiros.add(new Parceiros(null, 8, "Oxygen", new BigDecimal("15.9994"), "O"));
			parceiros.add(new Parceiros(null, 9, "Fluorine", new BigDecimal("18.9984"), "F"));
			parceiros.add(new Parceiros(null, 10, "Neon", new BigDecimal("20.1797"), "Ne"));
			parceiros.add(new Parceiros(null, 11, "Sodium", new BigDecimal("22.9898"), "Na"));
			parceiros.add(new Parceiros(null, 12, "Magnesium", new BigDecimal("24.305"), "Mg"));
			parceiros.add(new Parceiros(null, 13, "Aluminum", new BigDecimal("26.9815"), "Al"));
			parceiros.add(new Parceiros(null, 14, "Silicon", new BigDecimal("28.0855"), "Si"));
			parceiros.add(new Parceiros(null, 15, "Phosphorus", new BigDecimal("30.9738"), "P"));
			parceiros.add(new Parceiros(null, 16, "Sulfur", new BigDecimal("32.065"), "S"));
			parceiros.add(new Parceiros(null, 17, "Chlorine", new BigDecimal("35.453"), "Cl"));
			parceiros.add(new Parceiros(null, 18, "Argon", new BigDecimal("39.948"), "Ar"));
			parceiros.add(new Parceiros(null, 19, "Potassium", new BigDecimal("39.0983"), "K"));
			parceiros.add(new Parceiros(null, 20, "Calcium", new BigDecimal("40.078"), "Ca"));
			parceiros.add(new Parceiros(null, 21, "Scandium", new BigDecimal("44.9559"), "Sc"));
			parceiros.add(new Parceiros(null, 22, "Titanium", new BigDecimal("47.867"), "Ti"));
			parceiros.add(new Parceiros(null, 23, "Vanadium", new BigDecimal("50.9415"), "V"));
			parceiros.add(new Parceiros(null, 24, "Chromium", new BigDecimal("51.9961"), "Cr"));
			parceiros.add(new Parceiros(null, 25, "Manganese", new BigDecimal("54.938"), "Mn"));
			parceiros.add(new Parceiros(null, 26, "Iron", new BigDecimal("55.845"), "Fe"));
			parceiros.add(new Parceiros(null, 27, "Cobalt", new BigDecimal("58.9332"), "Co"));
			parceiros.add(new Parceiros(null, 28, "Nickel", new BigDecimal("58.6934"), "Ni"));
			parceiros.add(new Parceiros(null, 29, "Copper", new BigDecimal("63.546"), "Cu"));
			parceiros.add(new Parceiros(null, 30, "Zinc", new BigDecimal("65.38"), "Zn"));
			parceiros.add(new Parceiros(null, 31, "Gallium", new BigDecimal("69.723"), "Ga"));
			parceiros.add(new Parceiros(null, 32, "Germanium", new BigDecimal("72.63"), "Ge"));
			parceiros.add(new Parceiros(null, 33, "Arsenic", new BigDecimal("74.9216"), "As"));
			parceiros.add(new Parceiros(null, 34, "Selenium", new BigDecimal("78.96"), "Se"));
			parceiros.add(new Parceiros(null, 35, "Bromine", new BigDecimal("79.904"), "Br"));
			parceiros.add(new Parceiros(null, 36, "Krypton", new BigDecimal("83.798"), "Kr"));
			parceiros.add(new Parceiros(null, 37, "Rubidium", new BigDecimal("85.4678"), "Rb"));
			parceiros.add(new Parceiros(null, 38, "Strontium", new BigDecimal("87.62"), "Sr"));
			parceiros.add(new Parceiros(null, 39, "Yttrium", new BigDecimal("88.9059"), "Y"));
			parceiros.add(new Parceiros(null, 40, "Zirconium", new BigDecimal("91.224"), "Zr"));
			parceiros.add(new Parceiros(null, 41, "Niobium", new BigDecimal("92.9064"), "Nb"));
			parceiros.add(new Parceiros(null, 42, "Molybdenum", new BigDecimal("95.94"), "Mo"));
			parceiros.add(new Parceiros(null, 43, "Technetium", new BigDecimal("98"), "Tc"));
			parceiros.add(new Parceiros(null, 44, "Ruthenium", new BigDecimal("101.07"), "Ru"));
			parceiros.add(new Parceiros(null, 45, "Rhodium", new BigDecimal("102.9055"), "Rh"));
			parceiros.add(new Parceiros(null, 46, "Palladium", new BigDecimal("106.42"), "Pd"));
			parceiros.add(new Parceiros(null, 47, "Silver", new BigDecimal("107.8682"), "Ag"));
			parceiros.add(new Parceiros(null, 48, "Cadmium", new BigDecimal("112.411"), "Cd"));
			parceiros.add(new Parceiros(null, 49, "Indium", new BigDecimal("114.818"), "In"));
			parceiros.add(new Parceiros(null, 50, "Tin", new BigDecimal("118.71"), "Sn"));
			parceiros.add(new Parceiros(null, 51, "Antimony", new BigDecimal("121.76"), "Sb"));
			parceiros.add(new Parceiros(null, 52, "Tellurium", new BigDecimal("127.6"), "Te"));
			parceiros.add(new Parceiros(null, 53, "Iodine", new BigDecimal("126.9045"), "I"));
			parceiros.add(new Parceiros(null, 54, "Xenon", new BigDecimal("131.293"), "Xe"));
			parceiros.add(new Parceiros(null, 55, "Cesium", new BigDecimal("132.9055"), "Cs"));
			parceiros.add(new Parceiros(null, 56, "Barium", new BigDecimal("137.327"), "Ba"));
			parceiros.add(new Parceiros(null, 57, "Lanthanum", new BigDecimal("138.9055"), "La"));
			parceiros.add(new Parceiros(null, 58, "Cerium", new BigDecimal("140.116"), "Ce"));
			parceiros.add(new Parceiros(null, 59, "Praseodymium", new BigDecimal("140.9077"), "Pr"));
			parceiros.add(new Parceiros(null, 60, "Neodymium", new BigDecimal("144.242"), "Nd"));
			parceiros.add(new Parceiros(null, 61, "Promethium", new BigDecimal("145"), "Pm"));
			parceiros.add(new Parceiros(null, 62, "Samarium", new BigDecimal("150.36"), "Sm"));
			parceiros.add(new Parceiros(null, 63, "Europium", new BigDecimal("151.964"), "Eu"));
			parceiros.add(new Parceiros(null, 64, "Gadolinium", new BigDecimal("157.25"), "Gd"));
			parceiros.add(new Parceiros(null, 65, "Terbium", new BigDecimal("158.9254"), "Tb"));
			parceiros.add(new Parceiros(null, 66, "Dysprosium", new BigDecimal("162.5"), "Dy"));
			parceiros.add(new Parceiros(null, 67, "Holmium", new BigDecimal("164.9303"), "Ho"));
			parceiros.add(new Parceiros(null, 68, "Erbium", new BigDecimal("167.259"), "Er"));
			parceiros.add(new Parceiros(null, 69, "Thulium", new BigDecimal("168.9342"), "Tm"));
			parceiros.add(new Parceiros(null, 70, "Ytterbium", new BigDecimal("173.04"), "Yb"));
			parceiros.add(new Parceiros(null, 71, "Lutetium", new BigDecimal("174.967"), "Lu"));
			parceiros.add(new Parceiros(null, 72, "Hafnium", new BigDecimal("178.49"), "Hf"));
			parceiros.add(new Parceiros(null, 73, "Tantalum", new BigDecimal("180.9479"), "Ta"));
			parceiros.add(new Parceiros(null, 74, "Tungsten", new BigDecimal("183.84"), "W"));
			parceiros.add(new Parceiros(null, 75, "Rhenium", new BigDecimal("186.207"), "Re"));
			parceiros.add(new Parceiros(null, 76, "Osmium", new BigDecimal("190.23"), "Os"));
			parceiros.add(new Parceiros(null, 77, "Iridium", new BigDecimal("192.217"), "Ir"));
			parceiros.add(new Parceiros(null, 78, "Platinum", new BigDecimal("195.084"), "Pt"));
			parceiros.add(new Parceiros(null, 79, "Gold", new BigDecimal("196.9666"), "Au"));
			parceiros.add(new Parceiros(null, 80, "Mercury", new BigDecimal("200.59"), "Hg"));
			parceiros.add(new Parceiros(null, 81, "Thallium", new BigDecimal("204.3833"), "Tl"));
			parceiros.add(new Parceiros(null, 82, "Lead", new BigDecimal("207.2"), "Pb"));
			parceiros.add(new Parceiros(null, 83, "Bismuth", new BigDecimal("208.9804"), "Bi"));
			parceiros.add(new Parceiros(null, 84, "Polonium", new BigDecimal("209"), "Po"));
			parceiros.add(new Parceiros(null, 85, "Astatine", new BigDecimal("210"), "At"));
			parceiros.add(new Parceiros(null, 86, "Radon", new BigDecimal("222"), "Rn"));
			parceiros.add(new Parceiros(null, 87, "Francium", new BigDecimal("223"), "Fr"));
			parceiros.add(new Parceiros(null, 88, "Radium", new BigDecimal("226"), "Ra"));
			parceiros.add(new Parceiros(null, 89, "Actinium", new BigDecimal("227"), "Ac"));
			parceiros.add(new Parceiros(null, 90, "Thorium", new BigDecimal("232.0381"), "Th"));
			parceiros.add(new Parceiros(null, 91, "Protactinium", new BigDecimal("231.0359"), "Pa"));
			parceiros.add(new Parceiros(null, 92, "Uranium", new BigDecimal("238.0289"), "U"));
			parceiros.add(new Parceiros(null, 93, "Neptunium", new BigDecimal("237"), "Np"));
			parceiros.add(new Parceiros(null, 94, "Plutonium", new BigDecimal("244"), "Pu"));
			parceiros.add(new Parceiros(null, 95, "Americium", new BigDecimal("243"), "Am"));
			parceiros.add(new Parceiros(null, 96, "Curium", new BigDecimal("247"), "Cm"));
			parceiros.add(new Parceiros(null, 97, "Berkelium", new BigDecimal("247"), "Bk"));
			parceiros.add(new Parceiros(null, 98, "Californium", new BigDecimal("251"), "Cf"));
			parceiros.add(new Parceiros(null, 99, "Einsteinium", new BigDecimal("252"), "Es"));
			parceiros.add(new Parceiros(null, 100, "Fermium", new BigDecimal("257"), "Fm"));
			parceiros.add(new Parceiros(null, 101, "Mendelevium", new BigDecimal("258"), "Md"));
			parceiros.add(new Parceiros(null, 102, "Nobelium", new BigDecimal("259"), "No"));
			parceiros.add(new Parceiros(null, 103, "Lawrencium", new BigDecimal("262"), "Lr"));
			parceiros.add(new Parceiros(null, 104, "Rutherfordium", new BigDecimal("267"), "Rf"));
			parceiros.add(new Parceiros(null, 105, "Dubnium", new BigDecimal("270"), "Db"));
			parceiros.add(new Parceiros(null, 106, "Seaborgium", new BigDecimal("271"), "Sg"));
			parceiros.add(new Parceiros(null, 107, "Bohrium", new BigDecimal("270"), "Bh"));
			parceiros.add(new Parceiros(null, 108, "Hassium", new BigDecimal("277"), "Hs"));
			parceiros.add(new Parceiros(null, 109, "Meitnerium", new BigDecimal("278"), "Mt"));
			parceiros.add(new Parceiros(null, 110, "Darmstadtium", new BigDecimal("281"), "Ds"));
			parceiros.add(new Parceiros(null, 111, "Roentgenium", new BigDecimal("282"), "Rg"));
			parceiros.add(new Parceiros(null, 112, "Copernicium", new BigDecimal("285"), "Cn"));
			parceiros.add(new Parceiros(null, 113, "Nihonium", new BigDecimal("286"), "Nh"));
			parceiros.add(new Parceiros(null, 114, "Flerovium", new BigDecimal("289"), "Fl"));
			parceiros.add(new Parceiros(null, 115, "Moscovium", new BigDecimal("290"), "Mc"));
			parceiros.add(new Parceiros(null, 116, "Livermorium", new BigDecimal("293"), "Lv"));
			parceiros.add(new Parceiros(null, 117, "Tennessine", new BigDecimal("294"), "Ts"));
			parceiros.add(new Parceiros(null, 118, "Oganesson", new BigDecimal("294"), "Og"));

			parceiros.add(new Parceiros(null, 119,"Teste119", new BigDecimal("87.45"),"Ts"));

			parceirosRepository.saveAll(parceiros);

			System.out.println("Lista de parceiros salva com sucesso!");
		};
	}
}
