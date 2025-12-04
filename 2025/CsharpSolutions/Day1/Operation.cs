public enum Direction
{
    L,
    R
}

public record Operation(Direction Direction, int Value);
