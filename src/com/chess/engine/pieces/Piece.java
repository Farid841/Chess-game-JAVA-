package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.List;

public abstract class Piece {
    /** **/
    protected final int piece_position;
    protected final Alliance piece_alliance;

    Piece(final int piece_position, final Alliance piece_alliance){
        this.piece_position = piece_position;
        this.piece_alliance = piece_alliance;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);
}
