package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BishopBlackTest {

    @Test
    public void whenStartPositionC8() {
        BishopBlack bishop = new BishopBlack(Cell.C8);
        Cell result = bishop.position();
        assertThat(result.equals(Cell.C8));
    }

    @Test
    public void whenStartPositionF8() {
        BishopBlack bishop = new BishopBlack(Cell.F8);
        Cell result = bishop.position();
        assertThat(bishop.position().equals(Cell.F8));
    }

    @Test
    public void whenCopy() {
        BishopBlack bishop = new BishopBlack(Cell.C8);
        Cell result = bishop.copy(Cell.D7).position();
        assertThat(result.equals(bishop.position()));
    }

    @Test
    public void whenWayFromC1ToG5() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] cells = new Cell[4];
        cells[0] = Cell.D2;
        cells[1] = Cell.E3;
        cells[2] = Cell.F4;
        cells[3] = Cell.G5;
        Cell[] result = bishop.way(Cell.G5);
        assertThat(result).containsExactly(cells);
    }

    @Test
    public void whenWayFromG5ToC1() {
        BishopBlack bishop = new BishopBlack(Cell.G5);
        Cell[] cells = new Cell[4];
        cells[0] = Cell.F4;
        cells[1] = Cell.E3;
        cells[2] = Cell.D2;
        cells[3] = Cell.C1;
        Cell[] result = bishop.way(Cell.C1);
        assertThat(result).containsExactly(cells);
    }

    @Test
    public void whenWayFromD4ToG1() {
        BishopBlack bishop = new BishopBlack(Cell.D4);
        Cell[] cells = new Cell[3];
        cells[0] = Cell.E3;
        cells[1] = Cell.F2;
        cells[2] = Cell.G1;
        Cell[] result = bishop.way(Cell.G1);
        assertThat(result).containsExactly(cells);
    }

    @Test
    public void whenWayFromE5ToB8() {
        BishopBlack bishop = new BishopBlack(Cell.E5);
        Cell[] cells = new Cell[3];
        cells[0] = Cell.D6;
        cells[1] = Cell.C7;
        cells[2] = Cell.B8;
        Cell[] result = bishop.way(Cell.B8);
        assertThat(result).containsExactly(cells);
    }

    @Test
    public void whenIsDiagonalFromC1ToH6() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        boolean result = bishop.isDiagonal(bishop.position(), Cell.H6);
        assertThat(result).isTrue();
    }

    @Test
    public void whenIsDiagonalFromH6ToC1() {
        BishopBlack bishop = new BishopBlack(Cell.H6);
        boolean result = bishop.isDiagonal(bishop.position(), Cell.C1);
        assertThat(result).isTrue();
    }

    @Test
    public void whenIsDiagonalFromD6ToH2() {
        BishopBlack bishop = new BishopBlack(Cell.D6);
        boolean result = bishop.isDiagonal(bishop.position(), Cell.H2);
        assertThat(result).isTrue();
    }

    @Test
    public void whenIsDiagonalFromD4ToA7() {
        BishopBlack bishop = new BishopBlack(Cell.D4);
        boolean result = bishop.isDiagonal(bishop.position(), Cell.A7);
        assertThat(result).isTrue();
    }

    @Test
    public void whenWayThenImpossibleMoveException()
            throws ImpossibleMoveException {
        BishopBlack bishop = new BishopBlack(Cell.C8);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            bishop.way(Cell.C1);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from C8 to C1");
    }
}