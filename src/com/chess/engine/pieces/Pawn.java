package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8};

    Pawn(int piece_position, Alliance piece_alliance) {
        super(piece_position, piece_alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){

            int candidateDestinationCoordinate = this.piece_position + (this.getPieceAlliance().getDirection() *currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordiante(candidateDestinationCoordinate)){
                continue;
            }
            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                //TODO more work to do here (deal with promotion) !!!
                legalMoves.add( new MajorMove(board, this, candidateDestinationCoordinate) );
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piece_position] && this.getPieceAlliance().isBlack()) ||
                    (BoardUtils.SECOND_ROW[this.piece_position] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piece_position + (this.piece_alliance.getDirection() * 8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add( new Move.MajorMove(board, this, candidateDestinationCoordinate) );

                }
            }

        }

        return legalMoves;
    }
}
