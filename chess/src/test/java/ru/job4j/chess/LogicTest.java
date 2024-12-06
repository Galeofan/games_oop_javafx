package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveOnFigureThenOccupiedCellException()
            throws OccupiedCellException {
        Logic logic = new Logic();
        Figure bishop1 = new BishopBlack(Cell.C8);
        Figure bishop2 = new BishopBlack(Cell.B7);
        logic.add(bishop1);
        logic.add(bishop2);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C8, Cell.B7);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move on occupied Cell");
    }

    @Test
    public void whenMoveThroughFigureThenOccupiedCellException()
            throws OccupiedCellException {
        Logic logic = new Logic();
        Figure bishop1 = new BishopBlack(Cell.C8);
        Figure bishop2 = new BishopBlack(Cell.B7);
        Figure bishop3 = new BishopBlack(Cell.A6);
        logic.add(bishop1);
        logic.add(bishop2);
        logic.add(bishop3);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C8, Cell.A6);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move on occupied Cell");
    }
}