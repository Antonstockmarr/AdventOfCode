
using System.Text;

namespace Day5
{
    public class IntervalCollection
    {
        private readonly List<Interval> _intervals = [];

        public long Count()
        {
            return _intervals.Sum(i => i.Count());
        }

        public void AddInterval(Interval interval)
        {
            if (_intervals.Any(i => i.Equals(interval))) return;

            _intervals.Add(interval);
            while (TrySimplifyIntervals()) {  }
        }

        private bool TrySimplifyIntervals()
        {
            Interval? mergeCandidate1 = null, mergeCandidate2 = null;
            foreach (Interval interval in _intervals)
            {
                var otherInterval = _intervals.FirstOrDefault(i => i.Overlaps(interval) && i != interval);
                if (otherInterval is not null)
                {
                    mergeCandidate1 = interval;
                    mergeCandidate2 = otherInterval;
                    break;
                }
            }

            if (mergeCandidate1 is not null && mergeCandidate2 is not null)
            {
                mergeCandidate1.Extend(mergeCandidate2);
                _intervals.Remove(mergeCandidate2);

                return true;
            }
            return false;
        }

        public override string ToString()
        {
            var sb = new StringBuilder();
            foreach (Interval interval in _intervals)
            {
                sb.Append(interval.ToString() + ", ");
            }
            return sb.ToString();
        }
    }
}
