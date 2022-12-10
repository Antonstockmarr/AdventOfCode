namespace Day9
{
    internal class Position
    {
        public int row;
        public int col;

        public Position(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        public override bool Equals(Object? other)
        {
                Position? o = other as Position;
                return (o?.row == this.row && o?.col == this.col);
        }

        public override int GetHashCode()
        {
            return row*27 + col*31;
        }

        public Position Move(Direction direction) {
            return direction switch
            {
                Direction.Right => new Position(row, col + 1),
                Direction.Left => new Position(row, col - 1),
                Direction.Up => new Position(row + 1, col),
                Direction.Down => new Position(row - 1, col),
                _ => throw new NotImplementedException(),
            };
        }

        public bool Adjacent(Position tail)
        {
            return Math.Abs(tail.row - this.row) <= 1 &&
                Math.Abs(tail.col - this.col) <= 1;
        }

        public Position Copy()
        {
            return new Position(row, col);
        }

        public override string ToString()
        {
            return $"({row},{ col})";
        }
        public static Position operator +(Position p1, Position p2)
        {
            return new Position(p1.row + p2.row, p1.col + p2.col);
        }

        public static Position operator -(Position p1, Position p2)
        {
            return new Position(p1.row - p2.row, p1.col - p2.col);
        }
    }
}
