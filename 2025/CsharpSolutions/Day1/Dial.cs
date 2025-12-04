
namespace Day1
{
    public class Dial
    {
        public int zeroes = 0;
        private int number { get; set; } = 50;

        public Dial() { }

        private void Right(int value)
        {
            number = mod(number + value, 100);
            if (number == 0) zeroes++;
        }

        private void Left(int value)
        {
            number = mod(number - value, 100);
            if (number == 0) zeroes++;
        }

        public void Operate(Operation operation)
        {
            if (operation.Direction == Direction.L) Left(operation.Value);
            else Right(operation.Value);
        }

        private int mod(int a, int b)
        {
            return (a % b + b) % b;
        }
    }
}
