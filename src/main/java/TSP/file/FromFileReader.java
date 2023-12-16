package TSP.file;

import TSP.data.Matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FromFileReader {
    public Matrix loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku z danymi:");
        String fileName = scanner.nextLine();

        return loadFromFile(fileName);
    }

    public Matrix loadFromFile(String fileName) {

        int[][] distanceMatrix = null;
        int actualRow = 0;
        int actualCol = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean readingSection = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DIMENSION")) {
                    int dimension = extractDimension(line);
                    distanceMatrix = new int[dimension][dimension];
                } else if (line.contains("EDGE_WEIGHT_SECTION")) {
                    readingSection = true;
                } else if (readingSection) {
                    String[] values = line.trim().split("\\s+");
                    for(int i = 0 ; i < values.length; i++) {
                        if (actualCol >= distanceMatrix.length) {
                            actualCol = 0;
                            actualRow++;
                        }
                        if(actualRow >= distanceMatrix.length) {
                            return new Matrix(distanceMatrix);
                        }
                        distanceMatrix[actualRow][actualCol] = Integer.parseInt(values[i]);
                        actualCol++;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Matrix(distanceMatrix);
    }

    private int extractDimension(String line) {
        String[] parts = line.split(":");
        if (parts.length >= 2) {
            try {
                return Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing dimension number: " + e.getMessage());
            }
        }
        return 0;
    }
}
