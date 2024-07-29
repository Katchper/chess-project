import java.util.ArrayList;

/**
 * Author: wip24
 */

public class Check {

    public void checkCheck(Tile originalTile, Board originalBoard) {
        int originalX = originalTile.getX(); // Piece just moved
        int originalY = originalTile.getY();
        int kingX = 0; // King tile
        int kingY = 0;
        Tile kingTile = null;
        int pieceTeam = originalTile.getPiece().checkForPiece(originalTile); // What team the piece is on
        int opposingTeam;
        if (pieceTeam == 1) { // Set who is the opposing team in this situation
            opposingTeam = 2;
        } else {
            opposingTeam = 1;
        }
        System.out.println("checkCheck has been triggered.");
        int size = originalTile.getPiece().getAllMoves().size();
        Tile[] tileArray = new Tile[size];
        originalTile.getPiece().getAllMoves().toArray(tileArray);
        int x;
        int y;
        boolean checkBool = false;
        boolean checkMateBool = false;

        for (int i = 0; i < size; i++) { // Check
            x = tileArray[i].getX(); // Checking tiles the piece (that was just moved) can be moved to / can effect
            y = tileArray[i].getY();
            Tile currentTile = originalBoard.getTile(x, y);
            if (originalBoard.getTile(x, y).getPiece().checkWhichPiece(tileArray[i]) == 2) { // If piece is king
                if (originalBoard.getTile(x, y).getPiece().checkForPiece(currentTile) == opposingTeam) { // If the king is opposing team's king
                    checkBool = true; // king is in check
                    kingX = x;
                    kingY = y;
                    kingTile = currentTile;
                    i = size;
                    System.out.println("Check state is in affect.");
                    // Then move to check if checkmate
                }
            }
        }

            if (checkBool) { // Checkmate
                // Get all pieces moves, to determine if check or checkmate
                int kingSize = kingTile.getPiece().getAllMoves().size(); // Get King in check moves
                Tile[] kingMoveArray = new Tile[kingSize];
                kingTile.getPiece().getAllMoves().toArray(kingMoveArray);
                ArrayList<Tile> originalTeamMoves = new ArrayList<>(); // Get the original team's (team currently checking the king) moves
                ArrayList<Tile> kingTeamMoves = new ArrayList<>();
                for (int o = 0; o < 8; o++) { // Checking all tiles for pieces
                    for (int p = 0; p < 8; p++) {
                        Tile nameForTile = originalBoard.getTile(o, p);
                        if (nameForTile.getPiece().checkForPiece(nameForTile) == pieceTeam) { // If piece is OG team piece, add all moves
                            originalTeamMoves.addAll(nameForTile.getPiece().getAllMoves());
                        } else if (nameForTile.getPiece().checkForPiece(nameForTile) == opposingTeam) { // If piece is king team piece, add all moves
                            kingTeamMoves.addAll(nameForTile.getPiece().getAllMoves());
                        }
                    }
                }

                // Check if the king can move, get all enemy (of the king in check) moves, see if they match with the kings moves
                boolean notCheckMate = false; // Skip checking "friendly" pieces
                for (int k = 0; k < kingSize; k++) {
                    if (!originalTeamMoves.contains(kingMoveArray[k])) {
                        System.out.println("King can still move!");
                        k = kingSize;
                        notCheckMate = true;
                    }
                }

                // Finally check if any friend (of the king in check) can move to block, or take out the offending piece
                if (!notCheckMate) {
                    Tile[] unCheckTiles; // List of tiles between king & checking piece
                    int diff;
                    // Calculate move path to block
                    // x = 0 == left side
                    // y = 0 == bottom
                    if (kingX > originalX) { // King to the right
                        if (kingY > originalY) { // King above, king diagonal
                            diff = kingX - originalX;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX + l, originalY + l);
                            }
                        } else if (kingY < originalY) { // King below, king diagonal
                            diff = kingX - originalX;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX + l, originalY - l);
                            }
                        } else { // Same Y coordinate
                            diff = kingX - originalX;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX + l, originalY);
                            }
                        }
                    } else if (kingX < originalX) { // King to the left
                        if (kingY > originalY) { // King above, king diagonal
                            diff = originalX - kingX;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX - l, originalY + l);
                            }
                        } else if (kingY < originalY) { // King below, king diagonal,
                            diff = originalX - kingX;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX - l, originalY - l);
                            }
                        } else { // Same Y coordinate
                            diff = originalX - kingX;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX - l, originalY);
                            }
                        }
                    }
                    else { // Same X coordinate
                        if (kingY > originalY) { // If king is above
                            diff = kingY - originalY;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX, originalY + l);
                            }
                        } else { // King is below
                            diff = originalY - kingY;
                            unCheckTiles = new Tile[diff];
                            for (int l = 0; l < diff; l++) {
                                unCheckTiles[l] = originalBoard.getTile(originalX, originalY - l);
                            }
                        }
                    }

                    for (int j = 0; j < diff; j++) { // Check if any of king's team pieces can move onto the checker or the tiles between king & checker
                        if (kingTeamMoves.contains(unCheckTiles[j])) {
                            checkMateBool = false;
                            j = diff;
                        }
                        else {
                            checkMateBool = true;
                        }
                    }

                }


            }
        if (checkBool && !checkMateBool) { // Check is in play
            System.out.println("Check!");
        } else if (checkBool) { // It is checkmate
            System.out.println("Checkmate!");
        } else { // No check
            System.out.println("No check");
        }

        }
    }

