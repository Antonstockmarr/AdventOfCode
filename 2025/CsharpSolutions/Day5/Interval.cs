
namespace Day5
{
    public class Interval
    {
        public long lower;
        public long upper;

        public Interval(long lower, long upper)
        {
            this.lower = lower;
            this.upper = upper;
        }

        public bool Includes(long value)
        {
            return value >= lower && value <= upper;
        }

        public long Count()
        {
            return upper - lower + 1;
        }

        public bool Overlaps(Interval other)
        {
            return (other.lower <= lower && other.upper >= lower)
                || (other.upper >= upper && other.lower <= upper);
        }

        public void Extend(Interval other)
        {
            if (other.lower < lower) lower = other.lower;
            if (other.upper > upper) upper = other.upper;
        }

        public override bool Equals(object? obj)
        {
            return Equals(obj as Interval);
        }

        private bool Equals(Interval? other)
        {
            return other is not null &&
                other.lower == lower &&
                other.upper == upper;
        }

        public override string ToString()
        {
            return $"{lower}-{upper}";
        }
    }
}
