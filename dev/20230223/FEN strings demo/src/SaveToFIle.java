
private static final String FILE_NAME = "game_output.txt";

    public static void SaveToFile(String fen, String board) {
        try {

            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write("FEN String\n");
            writer.write(fen + "\n\n");
            writer.write("Board: \n");
            writer.write(board);
            writer.close();
            System.out.println("Game recorded to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

