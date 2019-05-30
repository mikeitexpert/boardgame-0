package boardgame.engine;

public class FullhousePuzzels {
    public static final FullhouseCell puzzels[][][] = {
            new FullhouseCell[][]{
                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,  },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,  },

                {   FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,  },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,  },


                {   FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,  },

            },
            // level 12
            new FullhouseCell[][]{
                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Block,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Block,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },
            },
            // level 30
            new FullhouseCell[][]{
                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Block,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },

                {   FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Free,
                    FullhouseCell.Block,
                    FullhouseCell.Free,
                    FullhouseCell.Free,    },
            },
    };

    public static FullhouseCell[][] getPuzzelLevel(int level) {
        int puzzelSize = puzzels[level].length;
        FullhouseCell[][] puzzle = new FullhouseCell[puzzelSize][puzzelSize];

        for (int row = 0; row < puzzelSize; row++){
            for(int col = 0; col < puzzelSize; col++){
                puzzle[row][col] = puzzels[level][row][col];
            }
        }
        return puzzle;
    }
}
