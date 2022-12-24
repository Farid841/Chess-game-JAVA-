package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
/** Tile Chess, the board isn't used and the pieces are represented by tiles
 * for french peaple you can see Tile like "emplacement" **/
// the coordinate of the tile the table
    protected final int tile_coordinate;
    public static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        /** this function can't be modified any ways**/
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        /** to make that class immutable I chose to use the collection  **/
        return Collections.unmodifiableMap(emptyTileMap);
        //return ImmutableMap.copyOf(emptyTileMap);
        //return emptyTileMap;
    }

    public Tile createTile(final int tile_coordinate , final Piece piece){
        return piece != null ? new OccupiedTile(tile_coordinate, piece) : EMPTY_TILES.get(tile_coordinate);
    }

    Tile(final int tile_coordinate){
        this.tile_coordinate =tile_coordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends  Tile {
        EmptyTile(final int coordinate){
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {
        private final Piece piece_on_tile;

        OccupiedTile(int tile_coordinate, final Piece piece_on_tile){
            super(tile_coordinate);
            this.piece_on_tile = piece_on_tile;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.piece_on_tile;
        }
    }
}
