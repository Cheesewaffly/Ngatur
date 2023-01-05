public class Perft implements All {
    
    long nodes;
    Board board;

    long perftDriver(int depth) {

        if (depth == 0) {
            return 1;
        }

        MoveList moveList = new MoveList();
        board.generateMoves(moveList);

        for (int moves: moveList.list) {
            if (moves != 0) {
                
                long[] piecesCopy = new long[12];
                long[] occupancyCopy = new long[3];
                int sideCopy = BOTH;
                int castlingCopy = 0;
                int enPassantCopy = NO_SQ;

                for (int i = 0; i < 12; i++) {
                    piecesCopy[i] = board.pieces[i];
                }
                
                for (int i = 0; i < 3; i++) {
                    occupancyCopy[i] = board.occupancy[i];
                }

                sideCopy = board.side;
                castlingCopy = board.castling;
                enPassantCopy = board.enPassant;

                if (!board.makeMove(moves)) {
                    continue;
                }

                Perft nextPerft = new Perft(board);
                nodes += nextPerft.perftDriver(depth - 1);

                board.pieces = piecesCopy;
                board.occupancy = occupancyCopy;
                board.side = sideCopy;
                board.castling = castlingCopy;
                board.enPassant = enPassantCopy;
            }
        }

        return nodes;
    }

    public Perft(Board board) {
        this.board = board;
    }
}
