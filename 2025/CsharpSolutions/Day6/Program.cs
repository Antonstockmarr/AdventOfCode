var path = "Data/data.txt";

var lines = File.ReadAllLines(path);

string[][] values = new string[lines.Length - 1][];
string[] operations = [];
var lineCount = lines.Length;
for (int i = 0; i < lineCount; i++)
{
    var elements = lines[i].Split(' ');
    elements = elements.Where(e => !string.IsNullOrWhiteSpace(e)).ToArray();
    if (elements[0] == "*" || elements[0] == "+") operations = elements;
    else
    {
        values[i] = elements;
    }
}

var results = new List<long>();

for (int j = 0; j < values[0].Length; j++)
{
    var operation = operations[j];
    long result = operation == "+" ? 0 : 1;
    for (int i = 0; i < lineCount - 1; i++)
    {
        if (operation == "+") result += long.Parse(values[i][j]);
        else result *= long.Parse(values[i][j]);
    }
    results.Add(result);
}

Console.WriteLine(results.Sum());
