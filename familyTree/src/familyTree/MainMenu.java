package familyTree;

import java.io.*;
import java.util.*;

public class MainMenu
{
	ArrayList<Person> personenListe = new ArrayList<Person>();
	static int currentMenu = 0;
	Person tmpPerson;
	Person elternTeil;
	int selection;
	int listIndex;
	
	public static void main(String[] args)
	{	
		MainMenu mMenu = new MainMenu();
		
		mMenu.LoadFile();
		
        try
        {
            while (true)
            {
            	currentMenu = mMenu.MenuManager(currentMenu);        	
            }
        }
        catch(IllegalStateException | NoSuchElementException e)
        {
            System.out.println("System.in was closed; exiting");
        }

	}
	
	@SuppressWarnings("unchecked")
	public void LoadFile()
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream("t.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			personenListe = (ArrayList<Person>)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int MenuManager(int menuID)
	{
        Scanner scanner = new Scanner(System.in);
        String line;
		
		switch(menuID) {
		case 0:
			System.out.println(
			"Stammbaum-Tool" + "\n" + "\n" +
			"1. Person erstellen" + "\n" +
			"2. Person bearbeiten" + "\n" +
			"3. Stammbaum anzeigen" + "\n" +
			"4. Personen filtern" + "\n" +
			"5. Personen suchen" + "\n" +
			"6. Alle Personen auflisten" + "\n" +
			"7. Speichern und beenden" + "\n" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 7);
			
			if (selection != -1)
			{
				menuID = selection * 10;
			}
			break;
		case 10:
			System.out.println(
			"Geben Sie den Vornamen ein.");
			line = scanner.nextLine();
			
			tmpPerson = new Person();
			tmpPerson.vorname = line;
			
			menuID = 11;
			break;
		case 11:
			System.out.println(
			"Geben Sie den Nachnamen ein.");
			line = scanner.nextLine();
			
			tmpPerson.nachname = line;
			
			menuID = 12;
			break;
		case 12:
			System.out.println(
			"Geben Sie das Geschlecht an (0 = m, 1 = w).");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 0, 1);
			
			if (selection == 0)
			{
				tmpPerson.geschlecht = false;
				menuID = 13;
			}
			else if (selection == 1)
			{
				tmpPerson.geschlecht = true;
				menuID = 13;
			}
			else
			{
				System.out.println("Falsche Eingabe! Darf nur 0 oder 1 sein!" + "\n");
				menuID = 12;
			}
			
			break;
		case 13:
			System.out.println(
			"Geben Sie die Generation ein (Generation 0 ist die jüngste. Alle älteren Generationen > 0).");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 0, 0);
			
			if (selection != -1)
			{
				tmpPerson.generation = selection;
				menuID = 14;
			}
			else
			{
				System.out.println("Falsche Eingabe! Muss >= 0 und ganzzahlig sein!" + "\n");
				menuID = 13;
			}
			
			break;
		case 14:
			System.out.println(
			"Geben Sie die ID der Person ein (ID > 0).");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 0, 0);
			
			if (selection != -1)
			{
				if (CheckUniqueID(selection, personenListe))
				{
					tmpPerson.id = selection;
					menuID = 15;
				}
				else
				{
					System.out.println("ID bereits vergeben. Wählen Sie eine andere ID!" + "\n");
					menuID = 14;
				}
			}
			else
			{
				System.out.println("Falsche Eingabe! Muss > 0 und ganzzahlig sein!" + "\n");
				menuID = 14;
			}
			
			break;
		case 15:
			System.out.println(
			"Geben Sie die ID des Vaters ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 0, 0);
			
			if (selection != -1)
			{
				tmpPerson.elternIds[0] = selection;
				menuID = 16;
			}
			else
			{
				tmpPerson.elternIds[0] = 0;
				System.out.println("ID des Vaters wird als <Unbekannt> gespeichert." + "\n");
			}
			
			break;
		case 16:
			System.out.println(
			"Geben Sie die ID der Mutter ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 0, 0);
			
			if (selection != -1)
			{
				tmpPerson.elternIds[1] = selection;
				
				personenListe.add(tmpPerson);		
				
				System.out.println("Personendaten erfolgreich gespeichert." + "\n");
				
				menuID = 0;
			}
			else
			{
				tmpPerson.elternIds[1] = 0;
				System.out.println("ID der Mutter wird als <Unbekannt> gespeichert." + "\n");
			}
			
			break;
		case 20:
			System.out.println(
			"Geben Sie die ID der zu bearbeitenden Person ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 1);
			
			if (selection == -1)
			{
				System.out.println("Falsche Eingabe! Muss > 0 und ganzzahlig sein!" + "\n");
			}
			else
			{
				if (PositionInListBySpecificID(selection, personenListe) == -1)
				{
					System.out.println("ID nicht gefunden. Geben Sie eine andere ID ein.");
				}
				else
				{
					listIndex = PositionInListBySpecificID(selection, personenListe);
					menuID = 21;
				}
			}
			
			break;
		case 21:
			System.out.println(
			"Bearbeiten der Personendaten" + "\n" + "\n" +
			"1. Neueingabe" + "\n" +
			"2. Person löschen" + "\n" +
			"3. Zurück zum Hauptmenü");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 3);
			
			if (selection == 1)
			{
				personenListe.remove(personenListe.get(listIndex));
				menuID = 10;
			}
			else if (selection == 2)
			{	
				personenListe.remove(personenListe.get(listIndex));
				System.out.println("Person gelöscht" + "\n");
				
				menuID = 0;
			}
			else if (selection == 3)
			{
				menuID = 0;
			}
			break;
		case 30:
			
			if (!CheckPersonListForErrors())
			{
				menuID = 0;
			}
			else
			{				
				System.out.println(
				"Aktueller Stammbaum:" + "\n");
						
				PrintFamilyTree();
				
				System.out.println();
				menuID = 0;
			}

			break;
		case 40:
			System.out.println(
			"Personen filtern." + "\n" + "\n" +
			"1. Nach Personen männlichen Geschlechts" + "\n" +
			"2. Nach Personen weiblichen Geschlechts" + "\n" +
			"3. Nach Generation" + "\n" +
			"4. Zurück zum Hauptmenü" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 4);
			
			if (selection != -1)
			{
				menuID = selection + 40;
			}
			break;
		case 41:
			System.out.println(
			"Alle Personen männlichen Geschlechts:" + "\n");
			
			personenListe = SortByGeneration(personenListe);
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (!personenListe.get(i).geschlecht)
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			menuID = 0;
			break;
		case 42:
			System.out.println(
			"Alle Personen weiblichen Geschlechts:" + "\n");
			
			personenListe = SortByGeneration(personenListe);
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (personenListe.get(i).geschlecht)
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			menuID = 0;
			break;
		case 43:
			System.out.println(
			"Geben Sie die gesuchte Generation ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 0, 0);
			
			if (selection == -1)
			{
				System.out.println("Fehler. Generation muss >= 0 sein.");
			}
			else
			{
				menuID = 44;
			}
			break;
		case 44:
			System.out.println(
			"Alle Personen der Generation " + selection + " :" + "\n");
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (personenListe.get(i).generation == selection)
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			menuID = 0;
			break;
		case 50:
			System.out.println(
			"Personen suchen" + "\n" + "\n" +
			"1. Nach Vornamen" + "\n" +
			"2. Nach Nachnamen" + "\n" +
			"3. Nach Verwandschaft einer Person suchen" + "\n" +
			"4. Zurück zum Hauptmenü" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 4);
			
			if (selection == 4)
			{
				menuID = 0;
			}
			else if (selection > 0 && selection < 4)
			{
				menuID = selection + 50;
			}
			break;
		case 51:
			System.out.println(
			"Bitte Vornamen eingeben.");
			line = scanner.nextLine();
			
			System.out.println(
			"Gefundene Person(en) mit Vornamen " + line + ":" + "\n");
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (Objects.equals(personenListe.get(i).vorname, line))
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			System.out.println();
			menuID = 0;
			break;	
		case 52:
			System.out.println(
			"Bitte Nachnamen eingeben.");
			line = scanner.nextLine();
			
			System.out.println(
			"Gefundene Person(en) mit Nachnamen " + line + ":" + "\n");
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (Objects.equals(personenListe.get(i).nachname, line))
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			System.out.println();
			menuID = 0;
			break;	
		case 53:
			System.out.println(
			"Bitte ID der Person eingeben.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 1);
			
			if (selection == -1)
			{
				System.out.println("Fehler. ID muss > 0 sein.");
			}
			else
			{
				tmpPerson = new Person();
				tmpPerson = personenListe.get(PositionInListBySpecificID(selection, personenListe));
				menuID = 530;
			}
			
			break;
		case 530:
			System.out.println(
			"Nach Verwandschaft von " + tmpPerson.vorname + " " + tmpPerson.nachname + " suchen" + "\n" + "\n" +
			"1. Nach Vater suchen" + "\n" +
			"2. Nach Mutter suchen" + "\n" +
			"3. Nach Großvätern suchen" + "\n" +
			"4. Nach Großmüttern suchen" + "\n" +
			"5. Nach Brüdern suchen" + "\n" +
			"6. Nach Schwestern suchen" + "\n" +
			"7. Zurück zum Hauptmenü" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.");
			line = scanner.nextLine();
			
			selection = CheckIntInRange(line, 1, 7);
			
			if (selection == 7)
			{
				menuID = 0;
			}
			else if (selection > 0 && selection < 7)
			{
				menuID = selection + 530;
			}
			break;	
		case 531:
			System.out.println(
			"Der Vater ist:" + "\n");
			
			listIndex = PositionInListBySpecificID(tmpPerson.elternIds[0], personenListe);
			
			if (listIndex != -1)
			{
				PrintPerson(personenListe.get(listIndex), false);
			}
			else
			{
				System.out.println("Unbekannt");
			}
			
			menuID = 0;
			break;	
		case 532:
			System.out.println(
			"Die Mutter ist:" + "\n");
			
			listIndex = PositionInListBySpecificID(tmpPerson.elternIds[1], personenListe);
			
			if (listIndex != -1)
			{
				PrintPerson(personenListe.get(listIndex), false);
			}
			else
			{
				System.out.println("Unbekannt");
			}

			menuID = 0;
			break;	
		case 533:
			System.out.println(
			"Die Großväter sind:" + "\n");
			
			int count = 0;
			
			while (count < 2)
			{
				listIndex = PositionInListBySpecificID(tmpPerson.elternIds[count], personenListe);
				
				if (listIndex != -1)
				{
					elternTeil = personenListe.get(listIndex);
					
					listIndex = PositionInListBySpecificID(elternTeil.elternIds[0], personenListe);
					
					if (listIndex != -1)
					{
						PrintPerson(personenListe.get(listIndex), false);
					}
					else
					{
						System.out.println("Unbekannt");
					}
				}
				else
				{
					System.out.println("Unbekannt");
				}
			
				count++;
			}
			
			menuID = 0;
			break;	
		case 534:
			System.out.println(
			"Die Großmütter sind:" + "\n");
			
			count = 0;
			
			while (count < 2)
			{
				listIndex = PositionInListBySpecificID(tmpPerson.elternIds[count], personenListe);
				
				if (listIndex != -1)
				{
					elternTeil = personenListe.get(listIndex);
					
					listIndex = PositionInListBySpecificID(elternTeil.elternIds[0], personenListe);
					
					if (listIndex != -1)
					{
						PrintPerson(personenListe.get(listIndex), false);
					}
					else
					{
						System.out.println("Unbekannt");
					}
				}
				else
				{
					System.out.println("Unbekannt");
				}
			
				count++;
			}
			
			menuID = 0;
			break;	
		case 535:
			System.out.println(
			"Die Brüder sind:" + "\n");
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (personenListe.get(i).elternIds == tmpPerson.elternIds && !personenListe.get(i).geschlecht)
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			menuID = 0;
			break;
		case 536:
			System.out.println(
			"Die Schwestern sind:" + "\n");
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				if (personenListe.get(i).elternIds == tmpPerson.elternIds && personenListe.get(i).geschlecht)
				{
					PrintPerson(personenListe.get(i), false);
				}
			}
			
			menuID = 0;
			break;
		case 60:
			System.out.println("Aktuelle Personenliste:" + "\n");
			
			for (int i = 0; i < personenListe.size(); i++)
			{
				PrintPerson(personenListe.get(i), false);
			}
			
			System.out.println("\n");
			
			menuID = 0;
			break;
		case 70:
			System.out.println(
			"Speichern und beenden");
			
			FileOutputStream fos;

			try {
				fos = new FileOutputStream("t.tmp");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(personenListe);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.exit(0);
			break;		
		default:
			System.out.println(
			"Dieser Fall existiert eh nicht.");
			break;
		}
		
		return menuID;
	}
	
	private int CheckIntInRange(String input, int min, int max)
	{
		int test;
		try
		{
			test = Integer.parseInt(input);
		}
		catch (NumberFormatException e)
		{
			return -1;
		}
		
		if (min == max)
		{
			if (test < min)
			{
				return -1;
			}
			else
			{
				return test;
			}
		}
		else
		{
			if (test > max || test < min)
			{
				return -1;
			}
			else
			{		
				return test;
			}
		}
	}
	
	private void PrintFamilyTree()
	{
		personenListe = SortByGeneration(personenListe);
		
		ArrayList<Person> baumListe = SortForFamilyTree(personenListe);
		
		for (int i = 0; i < baumListe.size(); i++)
		{
			PrintPerson(baumListe.get(i), true);
		}		
	}
	
	private void PrintPerson(Person givenPerson, boolean tab)
	{
		String geschlecht;
		
		if (givenPerson.geschlecht)
		{
			geschlecht = "w";
		}
		else
		{
			geschlecht = "m";
		}
		
		if (tab)
		{
			for (int i = 0; i <= givenPerson.generation; i++)
			{
				System.out.print("   ");
			}
		}
		
		System.out.print(
		givenPerson.vorname + " " +
		givenPerson.nachname + " " +
		"( " + geschlecht + "; " +
		"Generation: " + givenPerson.generation + "; " +
		"ID: " + givenPerson.id);
		
		if (tab)
		{
			System.out.print("; Kekule-Nr.: " + givenPerson.kekule);
		}
		
		System.out.print(" )" + "\n");
	}
	
	private boolean CheckUniqueID(int checkID, ArrayList<Person> givenList)
	{
		for (int i = 0; i < givenList.size(); i++)
		{
			if (givenList.get(i).id == checkID)
			{
				return false;
			}
		}
		
		if (checkID <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private int PositionInListBySpecificID(int checkID, ArrayList<Person> givenList)
	{
		int position = -1;
		
		for (int i = 0; i < givenList.size(); i++)
		{
			if (givenList.get(i).id == checkID)
			{
				position = i;
				break;
			}
		}
		
		return position;
	}
	
	static ArrayList<Person> SortByGeneration(ArrayList<Person> givenList)
	{		
		boolean finished = false;
		
		while (!finished)
		{
			finished = true;
			for (int i = 0; i < givenList.size() - 1; i++)
			{			
				if (givenList.get(i).generation > givenList.get(i+1).generation)
				{
					Collections.swap(givenList, i, i+1);
					finished = false;
					break;
				}
			}
		}
		
		return givenList;
	}
	
	private ArrayList<Person> SortForFamilyTree(ArrayList<Person> givenList)
	{
		ArrayList<Integer> myIDList = new ArrayList<Integer>();
		ArrayList<Person> myPersonList = new ArrayList<Person>();
		
		myIDList.add(givenList.get(0).id);
		
		if (givenList.get(0).elternIds[0] != 0)
		{
			myIDList.add(givenList.get(0).elternIds[0]);
		}
		
		if (givenList.get(0).elternIds[1] != 0)
		{
			myIDList.add(givenList.get(0).elternIds[1]);
		}		
		
		for (int i = 1 ; i < myIDList.size(); i++)
		{

			myIDList.add(GetParentIDByChildID(myIDList.get(i), false));
			Collections.swap(myIDList, myIDList.size()-1, i + 1);
		
			myIDList.add(GetParentIDByChildID(myIDList.get(i), true));
			Collections.swap(myIDList, myIDList.size()-1, i + 2);
			
			if (myIDList.get(i + 1) == 0)
			{
				myIDList.remove(i + 1);
			}
			
			if (myIDList.get(i + 1) == 0)
			{
				myIDList.remove(i + 1);
			}
		}
		
		for (int i = 0; i < myIDList.size(); i++)
		{
			listIndex = PositionInListBySpecificID(myIDList.get(i), givenList);
			if (listIndex >= 0)
			{
				myPersonList.add(givenList.get(listIndex));
			}
		}
		
		ArrayList<Person> kekuleList = new ArrayList<Person>();
		kekuleList.addAll(myPersonList);
		
		SetKekuleNumbers(kekuleList);
		
		return myPersonList;
	}
	
	private void SetKekuleNumbers(ArrayList<Person> givenList)
	{
		givenList = SortByGeneration(givenList);
		
		givenList.get(0).kekule = 1;	
		
		for (int i = 0; i < givenList.size(); i++)
		{
			listIndex = PositionInListBySpecificID(givenList.get(i).elternIds[0], givenList);
			if (listIndex != -1)
			{
				givenList.get(listIndex).kekule = givenList.get(i).kekule * 2;
			}
			listIndex = PositionInListBySpecificID(givenList.get(i).elternIds[1], givenList);
			if (listIndex != -1)
			{
				givenList.get(listIndex).kekule = givenList.get(i).kekule * 2 + 1;
			}			
		}
	}
	
	private int GetParentIDByChildID(int givenID, boolean mother)
	{
		int positionInList = PositionInListBySpecificID(givenID, personenListe);
		
		if (positionInList != -1)
		{
			if (mother)
			{
				if (PositionInListBySpecificID(personenListe.get(positionInList).elternIds[1], personenListe) != -1)
				{
					positionInList = personenListe.get(positionInList).elternIds[1];
				}
				else
				{
					positionInList = 0;
				}
			}
			else
			{
				if (PositionInListBySpecificID(personenListe.get(positionInList).elternIds[0], personenListe) != -1)
				{
					positionInList = personenListe.get(positionInList).elternIds[0];
				}
				else
				{
					positionInList = 0;
				}
			}
		}
		else
		{
			positionInList = 0;
		}
		
		return positionInList;
	}
	
	private boolean CheckPersonListForErrors()
	{
		boolean noErrors = true;
		Person Elternteil = new Person();
		int targetIndex;
		
		for (int i = 0; i < personenListe.size(); i++)
		{
			targetIndex = PositionInListBySpecificID(personenListe.get(i).elternIds[0], personenListe);
			
			if (targetIndex != -1)
			{
				Elternteil = personenListe.get(targetIndex);
				
				if (Elternteil.geschlecht)
				{
					System.out.println("Fehler! Biologischer Vater (ID: " + Elternteil.id + ") der Person (ID: " + personenListe.get(i).id + ") hat das falsche Geschlecht. Bitte ändern Sie die Personendaten und versuchen Sie es erneut.");
					noErrors = false;
				}
				else if (personenListe.get(i).generation >= Elternteil.generation)
				{
					System.out.println("Fehler! Generationsangabe des biologischen Vaters (ID: " + Elternteil.id + ") ist nicht korrekt. Bitte ändern Sie die Personendaten und versuchen Sie es erneut.");
					noErrors = false;
				}			
			}
			
			targetIndex = PositionInListBySpecificID(personenListe.get(i).elternIds[1], personenListe);
			
			if (targetIndex != -1)
			{
				Elternteil = personenListe.get(targetIndex);
				
				if (!Elternteil.geschlecht)
				{
					System.out.println("Fehler! Biologische Mutter (ID: " + Elternteil.id + ") der Person (ID: " + personenListe.get(i).id + ") hat das falsche Geschlecht. Bitte ändern Sie die Personendaten und versuchen Sie es erneut.");
					noErrors = false;
				}
				else if (personenListe.get(i).generation >= Elternteil.generation)
				{
					System.out.println("Fehler! Generationsangabe der biologischen Mutter (ID: " + Elternteil.id + ") ist nicht korrekt. Bitte ändern Sie die Personendaten und versuchen Sie es erneut.");
					noErrors = false;
				}
			}	
		}
		
		return noErrors;
	}
}
