package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class King extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    King(int piece_position, Alliance piece_alliance) {
        super(piece_position, piece_alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){

            final int candidateDestinationCoordinate = this.piece_position + currentCandidateOffset;

            if(isFirstColumnExclusion(this.piece_position, currentCandidateOffset) ||
                    isEightColumnExclusion(this.piece_position, currentCandidateOffset)){
                continue;
            }

            if(!BoardUtils.isValidTileCoordiante(candidateDestinationCoordinate)){
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add( new Move.MajorMove(board, this, candidateDestinationCoordinate) );
                }
                else{
                    final Piece piece_at_destination = candidateDestinationTile.getPiece();
                    final Alliance piece_alliance = piece_at_destination.getPieceAlliance();
                    if (this.piece_alliance != piece_alliance){
                        /* that means it is an enemy piece */
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate,
                                piece_at_destination));
                    }
                }
            }



        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ( (candidateOffset == -9 ) || (candidateOffset == -1 ) ||
                (candidateOffset == 7) );
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return  BoardUtils.EIGHT_COLUMN[currentPosition] && ((candidateOffset == -7) || candidateOffset == 1 ||
                 candidateOffset ==9);
    }


}
