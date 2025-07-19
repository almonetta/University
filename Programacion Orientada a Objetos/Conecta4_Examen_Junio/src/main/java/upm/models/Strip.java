package upm.models;

import java.util.ArrayList;
import java.util.List;

public class Strip {
    public static final int CONSECUTIVE_COUNT = 4;
    private final int dimension;

    private final List<Color> colors;

    public Strip(int dimension) {
        this.dimension = dimension;
        this.colors = new ArrayList<>(this.dimension);
    }

    public boolean isFull() {
        return this.colors.size() >= this.dimension;
    }

    public void insert(Color color) {
        if (this.isFull()) {
            throw new UnsupportedOperationException("Si la columna esta llena, no se puede insertar fichas");
        }
        this.colors.add(color);
    }

    public Color getColor(int index) {
        if (index < this.colors.size()) {
            return this.colors.get(index);
        } else {
            return null;
        }
    }

    public Color winner() {
        int count = 1;
        for (int i = 0; i < this.colors.size() - 1; i++) {
            if (colors.get(i) != null && colors.get(i + 1) != null && colors.get(i).equals(colors.get(i + 1))) {
                count++;
                if (count == CONSECUTIVE_COUNT) {
                    return colors.get(i);
                }
            } else {
                count = 1;
            }
        }
        return null;
    }

    public int getDimension() {
        return dimension;
    }

    public void reset() {
        this.colors.clear();
    }
}
