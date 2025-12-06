from scipy.signal import convolve2d
import numpy as np
file = open("data.txt", "r")

data = []
for line in file.read().splitlines():
    data.append(list(line))

data = np.array(data)
data = np.where(data != '.', 1, 0)

kernel = [[1, 1, 1],
          [1, 10, 1],
          [1, 1, 1]]
added_neighbours = convolve2d(data, kernel, mode = "same")

sum_matrix = np.subtract(added_neighbours, 10)
feasible = np.where(np.logical_and(sum_matrix < 4, sum_matrix >= 0), 1, 0)

result = np.count_nonzero(feasible)
print(result)