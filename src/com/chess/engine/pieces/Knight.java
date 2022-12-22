package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Knight extends Piece{

    /** list of legal move can knight can do  **/
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(int piece_position, Alliance piece_alliance) {
        super(piece_position, piece_alliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int candidate_destination_coordinate;
        List<Move> legal_moves = new ArrayList<>();
        for(final int current_candidate : CANDIDATE_MOVE_COORDINATES){
            candidate_destination_coordinate = this.piece_position + current_candidate;
            if(true /** is_valide_tile_coordiante**/ ){
                final Tile candidate_destination_tile = board.getTile(candidate_destination_coordinate);
                if(!candidate_destination_tile.isTileOccupied()){
                    legal_moves.add( new Move() );
                }
                else{
                    final Piece piece_at_destination = candidate_destination_tile.getPiece();
                    final Alliance piece_alliance = piece_at_destination.getPieceAlliance();
                    if (this.piece_alliance != piece_alliance){
                        /* that means it is an enemy piece */
                        legal_moves.add(new Move());
                    }

                }
            }
        }

        return Collections.unmodifiableMap(legal_moves);
    }
}
