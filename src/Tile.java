public abstract class Tile {
/** Tile Chess, the board isn't used and the pieces are represented by tiles  **/
    int tile_coordinate;
    Tile(int tile_coordinate){
        this.tile_coordinate =tile_coordinate;
    }
    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();
    public static final class EmptyTile extends  Tile {
        EmptyTile(int coordinate){
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

        Piece piece_on_tile;
        OccupiedTile(int tile_coordinate, Piece piece_on_tile){
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
