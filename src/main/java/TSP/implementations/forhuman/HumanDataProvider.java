package TSP.implementations.forhuman;

import TSP.config.GeneralConfig;
import TSP.data.DataPrinter;
import TSP.data.DataProvider;
import TSP.data.Matrix;
import TSP.data.RandomDataGenerator;
import TSP.file.FromFileReader;

import java.util.Scanner;

public class HumanDataProvider implements DataProvider {
    private Matrix matrix = null;

    @Override
    public Matrix getData() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Wczytaj dane z pliku ("+ GeneralConfig.file +")");
            System.out.println("2. Wygeneruj dane losowe");
            System.out.println("3. Wyświetl dane");
            System.out.println("4. Uruchom algorytm i wyświetl wyniki");
            System.out.println("5. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if(GeneralConfig.file == null) {
                        System.out.println("Ustaw plik w ustawieniach, następnie wróć do tego miejsca.");
                        return null;
                    }
                    matrix = (new FromFileReader()).loadFromFile(GeneralConfig.file);
                    break;
                case 2:
                    System.out.println("Podaj liczbę miast:");
                    matrix = (new RandomDataGenerator()).generateData(scanner.nextInt());
                    System.out.println("Dane zostały wygenerowane losowo.");
                    break;
                case 3: (new DataPrinter()).displayData(matrix);break;
                case 4: return matrix;
                case 5: return null;
                default: System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }


}
