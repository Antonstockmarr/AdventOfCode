test = open("data.txt", "r")

joltage_sum = 0
for line in test:
    first_digit = max(str(line[:-2]))
    index = line.find(first_digit)
    second_digit = max(str(line[index+1:-1]))
    number = first_digit + second_digit
    joltage_sum += int(number)

print(joltage_sum)
