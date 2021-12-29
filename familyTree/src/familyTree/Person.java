package familyTree;

import java.io.Serializable;
import java.util.*;

public class Person implements Serializable{
	int id;
	int[] elternIds = new int[2];
	String vorname;
	String nachname;
	boolean geschlecht;
	int generation;
	int kekule;
	
	Person()
	{
		id = 0;
		vorname = null;
		nachname = null;
		geschlecht = true;
		generation = 0;
		kekule = 0;
	}
	
	Person(int givenId, int[] eltern, String vorn, String nachn, boolean geschl, int gener)
	{
		id = givenId;
		elternIds = eltern;
		vorname = vorn;
		nachname = nachn;
		geschlecht = geschl;
		generation = gener;
		kekule = 0;
	}
}
