string path = "Data/data.txt";
//string path = "Data/test.txt";

var rawText = File.ReadAllText(path);

var intervals = rawText.Split(',');
var invalidIdSum = 0L;

foreach (var interval in intervals)
{
    var parts = interval.Split('-');
    var lower = long.Parse(parts[0]);
    var upper = long.Parse(parts[1]);
    
    for (long i = lower; i <= upper; i++)
    {
        //if (MatchesPattern(i)) invalidIdSum += i;
        if (MatchesPatternPartTwo(i)) invalidIdSum += i;
    }
}

bool MatchesPattern(long i)
{
    var id = i.ToString();
    var length = id.Length;
    // always valid id
    if (length % 2 != 0) return false;

    var firstNumber = id.Substring(0, length / 2);
    var secondNumber = id.Substring(length / 2, length / 2);

    return firstNumber.Equals(secondNumber);
}

bool MatchesPatternPartTwo(long i)
{
    var id = i.ToString();
    var length = id.Length;

    for (int j = 1; j < id.Length; j++)
    {
        // number length must be divisible by pattern length
        if (id.Length % j != 0) continue;

        var sequenceCandidate = id.Substring(0, j);
        var sequenceRepetitions = id.Length / j;
        // Split on the id to "Count" the occurrances
        if (id.Split(sequenceCandidate).Length == sequenceRepetitions + 1) return true;
    }

    return false;
}

Console.WriteLine(invalidIdSum);