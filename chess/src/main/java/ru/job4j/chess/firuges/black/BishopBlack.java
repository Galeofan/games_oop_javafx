package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int x = position.getX();
        int y = position.getY();
        int deltaX = dest.getX() - x;
        int deltaY = dest.getY() - y;
        int size = Math.abs(dest.getX() - x);
        Cell[] steps = new Cell[size];
        for (int index = 0; index < size; index++) {
            x = deltaX > 0 ? x + 1 : x - 1;
            y = deltaY < 0 ? y - 1 : y + 1;
            steps[index] = Cell.findBy(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        int deltaX = dest.getX() - source.getX();
        int deltaY = dest.getY() - source.getY();
        return Math.abs(deltaX) == Math.abs(deltaY);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}