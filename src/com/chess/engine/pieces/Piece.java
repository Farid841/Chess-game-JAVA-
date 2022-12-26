package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    /** **/
    protected final int piece_position;
    protected final Alliance piece_alliance;
    protected final boolean  isFirstMove;

    Piece(final int piece_position, final Alliance piece_alliance){
        this.piece_position = piece_position;
        this.piece_alliance = piece_alliance;
        //TODO work to do here
        this.isFirstMove = false;
    }

    public Alliance getPieceAlliance(){
        return this.piece_alliance;
    }

    public boolean isFirstMove(){
        return isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
