package org.group18;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class King extends Piece {
    private boolean hasMoved;
    private ImageView imageView;

    public King(boolean colour) {
        super(colour);
        hasMoved = false;
        if (colour) {
            this.pieceChar = 'k';
        } else {
            this.pieceChar = 'K';
        }
    }

    public void generateMoves(ChessBoard board, Tile piece) {
        ArrayList<Tile> moves = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Tile temp = board.getTile(x + i, y + j);
                if (checkForPiece(temp) < 2) {
                    moves.add(temp);
                }
            }
        }
        moves.addAll(castlingMoves(board, piece));

        this.moves = moves;
    }

    @Override
    public void move(GridPane board, Tile oldTile, Tile newTile) {
        // update the board and the tiles
        newTile.setPiece(this);
        oldTile.setEmpty(true);
        oldTile.setPiece(new EmptyPiece());

        // update the piece's position
        setHasMoved(true);

        // update the GUI
        ImageView oldImageView = oldTile.getImageView();
        if (oldImageView != null) {
            board.getChildren().remove(oldImageView);
        }
        board.add(getImageView(), newTile.getY(), newTile.getX());
    }


    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public ImageView getImageView() {
        if (imageView == null) {
            String imageName = this.getColour() ? "white_king" : "black_king";
            imageView = new ImageView("file:src/main/resources/" + imageName + ".png");
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
        }
        return imageView;
    }

    public ArrayList<Tile> castlingMoves(ChessBoard board, Tile piece) {
        ArrayList<Tile> moves = new ArrayList<>();
        King king = (King) piece.getPiece();
        int x = piece.getX();
        int y = piece.getY();
        Rook leftRook = new Rook(!king.getColour());
        Rook rightRook = new Rook(!king.getColour());

        if (board.getTile(0, y).getPiece().getPieceChar() == 'r' || board.getTile(0, y).getPiece().getPieceChar() == 'R') {
            leftRook = (Rook) board.getTile(0, y).getPiece();
        }
        if (board.getTile(7, y).getPiece().getPieceChar() == 'r' || board.getTile(7, y).getPiece().getPieceChar() == 'R') {
            rightRook = (Rook) board.getTile(7, y).getPiece();
        }

        char leftSquareOne = board.getTile(x + 1, y).getPiece().getPieceChar();
        char leftSquareTwo = board.getTile(x + 2, y).getPiece().getPieceChar();

        char rightSquareOne = board.getTile(x - 1, y).getPiece().getPieceChar();
        char rightSquareTwo = board.getTile(x - 2, y).getPiece().getPieceChar();
        char rightSquareThree = board.getTile(x - 1, y).getPiece().getPieceChar();
        if (!king.hasMoved) {
            if (!leftRook.hasMoved() && leftRook.getColour() == king.getColour()) {
                if (leftSquareOne == ' ' && leftSquareTwo == ' ') {
                    moves.add(board.getTile(x + 2, y));
                }
            }
            if (!rightRook.hasMoved() && rightRook.getColour() == king.getColour()) {
                if (rightSquareOne == ' ' && rightSquareTwo == ' ' && rightSquareThree == ' ') {
                    moves.add(board.getTile(x - 2, y));
                }
            }
        }
        return moves;
    }
}
