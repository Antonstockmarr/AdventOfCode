// See https://aka.ms/new-console-template for more information
using Day9;

public class Program
{
    public static void Main()
    {
        String file = File.ReadAllText("../../../../../../../data/day9.txt");
        String[] lines = file.Split("\n");

//        HashSet<Position> positions = GetTailPositionsPartOne(lines);
        HashSet<Position> positions = GetTailPositionsPartTwo(lines);

        Console.WriteLine(positions.Count);
    }

    private static HashSet<Position> GetTailPositionsPartOne(String[] lines)
    {
        Position head = new(0, 0);
        Position tail = new(0, 0);
        HashSet<Position> positions = new() { head };

        foreach (var line in lines)
        {
            if (line.Equals("")) continue;
            String[] parts = line.Split(" ");
            Direction d = GetDirection(parts[0]);
            int steps = int.Parse(parts[1]);
            for (int i = 0; i < steps; i++)
            {
                tail = MoveTail(head, d, tail);
                head = head.Move(d);
                positions.Add(tail);
            }
        }
        return positions;
    }

    private static Position MoveTail(Position head, Direction d, Position tail)
    {
        return (head.Move(d).Adjacent(tail))
            ? tail
            : head.Copy();
    }

    private static Direction GetDirection(String d)
    {
        return d switch
        {
            "R" => Direction.Right,
            "L" => Direction.Left,
            "U" => Direction.Up,
            "D" => Direction.Down,
            _ => throw new Exception(d + " is not a Direction")
        };
    }

    private static HashSet<Position> GetTailPositionsPartTwo(String[] lines)
    {
        int length = 10;
        Position[] knots = Enumerable.Repeat(new Position(0, 0), length).ToArray();
        HashSet<Position> positions = new() { knots[0] };

        foreach (var line in lines)
        {
            if (line.Equals("")) continue;
            String[] parts = line.Split(" ");
            Direction d = GetDirection(parts[0]);
            int steps = int.Parse(parts[1]);
            for (int i = 0; i < steps; i++)
            {
                knots[0] = knots[0].Move(d);
                for (int j = 1; j < length; j++)
                {
                    knots[j] = MoveKnots(knots[j], knots[j - 1]);
                }
                positions.Add(knots[length - 1]);
            }
        }
        return positions;
    }

    private static Position MoveKnots(Position currentKnot, Position nextKnot)
    {
        if (nextKnot.Adjacent(currentKnot)) return currentKnot;

        Position difference = nextKnot - currentKnot;
        return currentKnot + new Position(Math.Sign(difference.row), Math.Sign(difference.col));
    }
}





