package TSP.implementations.automated;

import TSP.data.DataProvider;
import TSP.data.Matrix;
import TSP.file.FromFileReader;

public class SequenceFileDataProvider implements DataProvider {

    private final String[] filenames;
    private int actualIndex = 0;

    public SequenceFileDataProvider(String[] filenames) {
        this.filenames = filenames;
    }

    @Override
    public Matrix getData() {
        if(actualIndex == filenames.length)
            return null;

        return new FromFileReader().loadFromFile(filenames[actualIndex++]);
    }
}
