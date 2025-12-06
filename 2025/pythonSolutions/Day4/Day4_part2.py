from scipy.signal import convolve2d
import numpy as np

def calculate_removed_rolls(data):
    kernel = [[1, 1, 1],
            [1, 10, 1],
            [1, 1, 1]]
    added_neighbours = convolve2d(data, kernel, mode = "same")

    sum_matrix = np.subtract(added_neighbours, 10)
    removed_rolls = np.where(np.logical_and(sum_matrix < 4, sum_matrix >= 0), 1, 0)

    return removed_rolls


file = open("data.txt", "r")

data = []
for line in file.read().splitlines():
    data.append(list(line))

data = np.array(data)
data = np.where(data != '.', 1, 0)

removed_rolls_count = 0
iteration_removed_rolls_count = 1

while iteration_removed_rolls_count > 0:
    removed_rolls = calculate_removed_rolls(data)
    data = np.subtract(data, removed_rolls)

    iteration_removed_rolls_count = np.count_nonzero(removed_rolls)
    removed_rolls_count += iteration_removed_rolls_count

print(removed_rolls_count)

