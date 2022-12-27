package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16};

    Pawn(int piece_position, Alliance piece_alliance) {
        super(piece_position, piece_alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){

            int candidateDestinationCoordinate = this.piece_position + (this.piece_alliance.getDirection() *currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordiante(candidateDestinationCoordinate)){
                continue;
            }
            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                //TODO more work to do here (deal with promotion) !!!
                legalMoves.add( new MajorMove(board, this, candidateDestinationCoordinate) );
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                        (BoardUtils.SECOND_ROW[this.piece_position] && this.piece_alliance.isBlack()) ||
                        (BoardUtils.SECOND_ROW[this.piece_position] && this.piece_alliance.isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piece_position + (this.piece_alliance.getDirection() * 8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add( new Move.MajorMove(board, this, candidateDestinationCoordinate) );

                }
            } else if (currentCandidateOffset == 7 &&
                    !((BoardUtils.EIGHT_COLUMN[this.piece_position] && this.piece_alliance.isWhite()) ||
                        (BoardUtils.FIRST_COLUMN[this.piece_position] && this.piece_alliance.isBlack())) ) {
                final Piece pieceOnCandidate =board.getTile(candidateDestinationCoordinate).getPiece();

                if(this.piece_alliance!= pieceOnCandidate.getPieceAlliance()) {
                    //TODO more work
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 9 &&
                        !((BoardUtils.FIRST_COLUMN[this.piece_position] && this.piece_alliance.isWhite()) ||
                         (BoardUtils.EIGHT_COLUMN[this.piece_position] && this.piece_alliance.isBlack()))) {
                final Piece pieceOnCandidate =board.getTile(candidateDestinationCoordinate).getPiece();

                if(this.piece_alliance!= pieceOnCandidate.getPieceAlliance()){
                    //TODO more work
                    legalMoves.add( new MajorMove(board, this, candidateDestinationCoordinate) );
                }
            }

        }

        return Collections.unmodifiableList(legalMoves);
    }
}
