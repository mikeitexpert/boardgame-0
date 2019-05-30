package boardgame.engine;

public class FullhouseModel {
    private  FullhouseCell m_grid[][];
    private int m_curRow = -1, m_curCol = -1;

    public FullhouseModel(int level){
        m_grid = FullhousePuzzels.getPuzzelLevel(level);

    }

    public void move(Direction dir){
        int dirRow = 0, dirCol = 0;
        FullhouseCell cellDir = null;

        switch (dir){
            case Up:
                dirRow = -1;
                dirCol = 0;
                cellDir = FullhouseCell.Up;
                break;
            case Down:
                dirRow = 1;
                dirCol = 0;
                cellDir = FullhouseCell.Down;
                break;
            case Left:
                dirRow = 0;
                dirCol = -1;
                cellDir = FullhouseCell.Left;
                break;
            case Right:
                dirRow = 0;
                dirCol = 1;
                cellDir = FullhouseCell.Right;
                break;
        }
        int row = m_curRow;
        int col = m_curCol;

        while (isInGrid(row, col) &&
                (m_grid[row][col] == FullhouseCell.Free ||
                        m_grid[row][col] == FullhouseCell.Cursor)){

            m_grid[row][col] = cellDir;


            row += dirRow;
            col += dirCol;
        }

        row -= dirRow;
        col -= dirCol;

        m_grid[row][col] = FullhouseCell.Cursor;
        m_curRow = row;
        m_curCol = col;
    }

    private boolean isInGrid(int row, int col){
        return (0 <= row && row < m_grid.length &&
                0 <= col && col < m_grid.length  );
    }

    public int getSize() {
        return m_grid.length;
    }

    public FullhouseCell getCellType(int row, int col) {
        return m_grid[row][col];
    }

    public void move(int row, int col) {
        Direction dir = null;
        if (m_curCol == col){
            if(m_curRow > row){
                dir = Direction.Up;
            }
            else if(m_curRow < row){
                dir = Direction.Down;
            }
        }
        else if (m_curRow == row){
            if(m_curCol > col){
                dir = Direction.Left;
            }
            else if(m_curCol < col){
                dir = Direction.Right;
            }
        }
        if (dir != null) {
            move(dir);
        }
    }

    public boolean isInitPointSet() {
        return m_curCol != -1 && m_curCol != -1;
    }

    public void setInitPoint(int row, int col) {
        m_curRow = row;
        m_curCol = col;
        m_grid[row][col] = FullhouseCell.Cursor;
    }

    public boolean canMoveAnyMore() {
        FullhouseCell upCell = (0 < (m_curRow - 1)  && (m_curRow - 1) < getSize() && 0 < m_curCol && m_curCol < getSize() ) ?
                m_grid[ m_curRow - 1 ][ m_curCol ] : null;

        FullhouseCell downCell = (0 < (m_curRow + 1)  && (m_curRow + 1) < getSize() && 0 < m_curCol && m_curCol < getSize() ) ?
                m_grid[ m_curRow + 1][ m_curCol ] : null;

        FullhouseCell leftCell = (0 < m_curRow  && m_curRow < getSize() && 0 < (m_curCol - 1) && (m_curCol - 1) < getSize() ) ?
                m_grid[ m_curRow ][ m_curCol - 1] : null;

        FullhouseCell rightCell = (0 < m_curRow  && m_curRow < getSize() && 0 < (m_curCol + 1) && (m_curCol + 1) < getSize() ) ?
                m_grid[ m_curRow ][ m_curCol + 1] : null;

        return upCell == FullhouseCell.Free ||
                downCell == FullhouseCell.Free ||
                leftCell == FullhouseCell.Free ||
                rightCell == FullhouseCell.Free;
    }

    public boolean isSuccessfullyComplete() {
        for (int row = 0; row < m_grid.length; row++){
            for(int col = 0; col < m_grid.length; col++){
                if (m_grid[row][col] == FullhouseCell.Free){
                    return false;
                }
            }
        }
        return true;
    }
}
