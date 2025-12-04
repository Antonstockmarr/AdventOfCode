test = open("data.txt", "r")

joltage_sum = 0
for line in test:
    number = ""
    current_index = 0

    for i in range(1,13):
        digit = max(str(line[current_index:-1 - (12 - i)]))
        index = line[current_index:].find(digit)
        current_index += index + 1
        number += digit
    
    joltage_sum += int(number)

print(joltage_sum)
