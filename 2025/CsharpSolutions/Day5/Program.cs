using Day5;

var path = "Data/data.txt";

var lines = File.ReadAllLines(path);

var intervals = new List<Interval>();
var ids = new List<long>();

foreach (var line in lines)
{
    if (string.IsNullOrEmpty(line.Trim())) continue;

    var splitBounds = line.Trim().Split('-');

    if (splitBounds.Length > 1)
    {
        intervals.Add(new Interval(long.Parse(splitBounds[0]), long.Parse(splitBounds[1])));
    }
    else
    {
        ids.Add(long.Parse(splitBounds[0]));
    }
}

var freshCount = 0;

foreach (var id in ids)
{
    if (intervals.Any(interval => interval.Includes(id))) freshCount++;
}

Console.WriteLine(freshCount);


// Part two
var intervalCollection = new IntervalCollection();
foreach (var interval in intervals)
{
    intervalCollection.AddInterval(interval);
}

Console.WriteLine(intervalCollection.Count());