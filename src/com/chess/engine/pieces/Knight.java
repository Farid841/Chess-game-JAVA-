package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.chess.engine.board.Move.*;


public class Knight extends Piece{

    /** list of legal move can knight can do  **/
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(int piece_position, Alliance piece_alliance) {
        super(piece_position, piece_alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        Collection<Move> legal_moves = new ArrayList<>();
        for(final int current_candidate_offset : CANDIDATE_MOVE_COORDINATES){
            final int candidate_destination_coordinate = this.piece_position + current_candidate_offset;
            if(BoardUtils.isValidTileCoordiante(candidate_destination_coordinate) ){
                if(isFirstColumnExclusion(this.piece_position, current_candidate_offset) ||
                    isSecondColumnExclusion(this.piece_position, current_candidate_offset)||
                    isSevenColumnExclusion(this.piece_position, current_candidate_offset) ||
                    isEightColumnExclusion(this.piece_position, current_candidate_offset)){
                    continue;
                }

                final Tile candidate_destination_tile = board.getTile(candidate_destination_coordinate);
                if(!candidate_destination_tile.isTileOccupied()){
                    legal_moves.add( new MajorMove(board, this, candidate_destination_coordinate) );
                }
                else{
                    final Piece piece_at_destination = candidate_destination_tile.getPiece();
                    final Alliance piece_alliance = piece_at_destination.getPieceAlliance();
                    if (this.piece_alliance != piece_alliance){
                        /* that means it is an enemy piece */
                        legal_moves.add(new AttackMove(board, this, candidate_destination_coordinate,
                                piece_at_destination));
                    }
                }
            }
        }
            return Collections.unmodifiableCollection(legal_moves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ( (candidateOffset == -17 ) || (candidateOffset == -10 ) ||
                (candidateOffset == 6) || (candidateOffset ==15) );
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return  BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || candidateOffset == 6);
    }

    private static boolean isSevenColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVEN_COLUMN[currentPosition] && ((candidateOffset == -6) ||candidateOffset == 10 );
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHT_COLUMN[currentPosition] && ((candidateOffset == -15) && (candidateOffset ==-6) ||
                (candidateOffset == 10) || (candidateOffset == 17) );
    }


}
