using Day1;

string path = "Data/data.txt";

var operations = new List<Operation>();

foreach (var line in File.ReadLines(path))
{
    if (string.IsNullOrWhiteSpace(line))
        continue;

    Direction dir = (Direction)Enum.Parse(typeof(Direction), line[0].ToString());
    int value = int.Parse(line.Substring(1));

    operations.Add(new Operation(dir, value));
}

var dial = new DialPartTwo();

foreach (var operation in  operations)
{
    dial.Operate(operation);
}

Console.WriteLine(dial.zeroes);