from csv import DictReader
from scipy.cluster import hierarchy
import numpy as np
import matplotlib.pyplot as plt
import time

def load_data(filepath):
    fileName = filepath
    # open file in read mode
    with open(fileName, 'r') as f:
        
        dict_reader = DictReader(f)
        
        list_of_dict = list(dict_reader)
        for i in range(len(list_of_dict)):
            list_of_dict[i] = dict(list_of_dict[i]) # Ensure all elements in list are dict and not
                                                    # OrderedDict
    return list_of_dict

def calc_features(row):
    x1 = int(row['Attack'])
    x2 = int(row['Sp. Atk'])
    x3 = int(row['Speed'])
    x4 = int(row['Defense'])
    x5 = int(row['Sp. Def'])
    x6 = int(row['HP'])
    returnArr = np.array([[x1], [x2], [x3], [x4], [x5], [x6]], dtype = np.int64)
    returnArr = returnArr.reshape((6,))
    return returnArr

def hac(features):
    distMatrix = np.zeros((2*len(features) - 1, 2*len(features) - 1))
    for i in range(len(features)):
        for j in range(len(features)):
            distMatrix[i, j] = np.linalg.norm(features[i] - features[j])

    # dictionary of clusters
    C = {i:[features[i], 1] for i in range(len(features))}

    matrixZ = np.zeros((len(features)-1, 4))
    for i in range(len(matrixZ)):
        # Find min value with tie breaker rules
        minVal = np.inf
        for j in C:
            for k in C:
                if (j != k):
                    if (distMatrix[j, k] < minVal):
                        minVal = distMatrix[j, k]
                        vec1 = j
                        vec2 = k

        # Add new merged cluster
        C[len(features) + i] = [C[vec1][0]+C[vec2][0], C[vec1][1]+C[vec2][1]]

        # Edit distance matrix
        for j in C:
            distMatrix[j, len(features)+i] = max(distMatrix[j, vec1], distMatrix[j, vec2])
            distMatrix[len(features)+i, j] = max(distMatrix[j, vec1], distMatrix[j, vec2])
        matrixZ[i][0] = vec1
        matrixZ[i][1] = vec2
        matrixZ[i][2] = minVal
        matrixZ[i][3] = C[len(features) + i][1]
        C.pop(vec1)
        C.pop(vec2)
    return matrixZ

def imshow_hac(Z, names):
    dn1 = hierarchy.dendrogram(Z, orientation='top', labels = names, leaf_rotation = 90)
    plt.tight_layout()
    plt.show()

features_and_names = [(calc_features(row), row['Name']) for row in load_data('/Users/ethanyan01/Desktop/CS540 Intro to AI/hw4/Pokemon.csv')[:50]]
start = time.perf_counter()
Z = hac([row[0] for row in features_and_names])
end = time.perf_counter()
print(Z)
names = [row[1] for row in features_and_names]
imshow_hac(Z,names)
ms = (end-start) * 10**6
print(f"Elapsed {ms:.03f} micro secs.")