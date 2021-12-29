package familyTree;

public class Trash {
	
	private static String ZeigeMenuText(int menuID)
	{
		String menuText;
		
		switch(menuID) {
		case 0:
			menuText =
			"StammbaumTool" + "\n" + "\n" +
			"1. Person Erstellen" + "\n" +
			"2. Stammbaum anzeigen" + "\n" +
			"3. Personen filtern" + "\n" +
			"4. Personen suchen" + "\n" +
			"5. Speichern und beenden" + "\n" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.";
			break;
		case 10:
			menuText =
			"Geben Sie den Vornamen ein.";
			break;
		case 11:
			menuText =
			"Geben Sie den Nachnamen ein.";
			break;
		case 12:
			menuText =
			"Geben Sie das Geschlecht an (m oder w).";
			break;
		case 13:
			menuText =
			"Geben Sie die Generation ein (Generation 0 ist die jüngste).";
			break;
		case 14:
			menuText =
			"Geben Sie die ID der Person ein.";
			break;
		case 15:
			menuText =
			"Geben Sie die IDs der Eltern - getrennt mit einem Leerzeichen - ein.";
			break;
		case 16:
			menuText =
			"Geben Sie die IDs aller Kinder - getrennt mit je einem Leerzeichen - ein.";
			break;
		case 20:
			menuText =
			"Aktueller Stammbaum sieht folgendermaßen aus:";
			break;
		case 30:
			menuText =
			"Personen filtern." + "\n" + "\n" +
			"1. Nach Geschlecht" + "\n" +
			"2. Nach Generation" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.";
			break;
		case 40:
			menuText =
			"Personen suchen" + "\n" + "\n" +
			"1. Nach Vornamen" + "\n" +
			"2. Nach Nachnamen" + "\n" +
			"3. Nach Verwandschaft einer Person suchen" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.";
			break;
		case 41:
			menuText =
			"Bitte Vornamen eingeben.";
			break;	
		case 42:
			menuText =
			"Bitte Nachnamen eingeben.";
			break;	
		case 43:
			menuText =
			"Bitte bekannte ID der Person eingeben.";
			break;	
		case 430:
			menuText =
			"Nach Verwandschaft einer Person suchen" + "\n" + "\n" +
			"1. Nach Vater suchen" + "\n" +
			"2. Nach Mutter suchen" + "\n" +
			"3. Nach Großvätern suchen" + "\n" +
			"4. Nach Großmüttern suchen" + "\n" +
			"5. Nach Brüdern suchen" + "\n" +
			"6. Nach Schwestern suchen" + "\n" +
			"Bitte geben Sie Ihre Auswahl ein.";
			break;	
		case 431:
			menuText =
			"Der Vater ist:";
			break;	
		case 432:
			menuText =
			"Die Mutter ist:";
			break;	
		case 433:
			menuText =
			"Die Großväter sind:";
			break;	
		case 434:
			menuText =
			"Die Großmütter sind:";
			break;	
		case 435:
			menuText =
			"Die Brüder sind:";
			break;
		case 436:
			menuText =
			"Die Schwestern sind:";
			break;
		case 50:
			menuText =
			"Speichern und beenden";
			break;		
		default:
			menuText =
			"Dieser Fall existiert eh nicht.";
			break;
		}	
				
		return menuText;
	}
}
