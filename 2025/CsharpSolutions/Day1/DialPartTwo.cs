namespace Day1
{
    public class DialPartTwo
    {
        public int zeroes = 0;
        private int number { get; set; } = 50;

        public DialPartTwo() { }

        private void Right(int value)
        {
            zeroes += CountZeros(number, +1, value);
            number = mod(number + value, 100);
        }

        private void Left(int value)
        {
            zeroes += CountZeros(number, -1, value);
            number = mod(number - value, 100);
        }

        public void Operate(Operation operation)
        {
            if (operation.Direction == Direction.L) Left(operation.Value);
            else Right(operation.Value);
        }

        private int CountZeros(int start, int sign, int steps)
        {
            int i0;
            if (sign == +1)
                i0 = (100 - start) % 100;
            else
                i0 = start % 100;

            if (i0 == 0) i0 = 100;

            if (steps < i0) return 0;

            return 1 + (steps - i0) / 100;
        }

        private int mod(int a, int b)
        {
            int r = a % b;
            if (r < 0) r += b;
            return r;
        }
    }
}
