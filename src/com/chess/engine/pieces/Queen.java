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

public class Queen extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-8, -7, 7, 8, 9 };

    Queen(int piece_position, Alliance piece_alliance) {
        super(piece_position, piece_alliance);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES){

            int candidateDestinationCoordinate = this.piece_position;

            while(BoardUtils.isValidTileCoordiante(candidateDestinationCoordinate)){

                if(isFirstColumnExclusion(this.piece_position, candidateCoordinateOffset) ||
                        isEightColumnExclusion(this.piece_position, candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;

                if(BoardUtils.isValidTileCoordiante(candidateDestinationCoordinate)){
                    final Tile candidate_destination_tile = board.getTile(candidateDestinationCoordinate);
                    if(!candidate_destination_tile.isTileOccupied()){
                        legalMoves.add( new Move.MajorMove(board, this, candidateDestinationCoordinate) );
                    }
                    else{
                        final Piece piece_at_destination = candidate_destination_tile.getPiece();
                        final Alliance piece_alliance = piece_at_destination.getPieceAlliance();
                        if (this.piece_alliance != piece_alliance) {
                            /* that means it is an enemy piece */
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate,
                                    piece_at_destination));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset ){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 ||candidateOffset== -1 || candidateOffset == 7);
    }
    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset ){
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset ==1|| candidateOffset == 9);
    }
}
