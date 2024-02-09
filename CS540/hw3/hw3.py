from scipy.linalg import eigh
import numpy as np
import matplotlib.pyplot as plt

def load_and_center_dataset(filename):
    # Your implementation goes here!
    x = np.load(filename)
    x = np.array(x)
    xi = x - np.mean(x, axis=0)
    return xi
    

def get_covariance(dataset):
    # Your implementation goes here!
    divisor = (1/(len(dataset)-1))
    returnSet = (np.dot(np.transpose(dataset), dataset))
    covMatrix = divisor*returnSet
    return covMatrix

def get_eig(S, m):
    # Your implementation goes here!
    eigenValues, eigenVectors = eigh(S,subset_by_index=[len(S)-m, len(S)-1])
    index = np.argsort(eigenValues)[::-1]
    eigenValues = eigenValues[index]
    eigenVectors = eigenVectors[:,index]
    zeroMatrix = np.zeros((len(eigenValues),len(eigenValues)))
    for i in range(len(eigenValues)):
        zeroMatrix[i][i] = eigenValues[i]
    return zeroMatrix, eigenVectors

def get_eig_prop(S, prop):
    # Your implementation goes here!
    allEigenValues = eigh(S, eigvals_only = True)
    sumArr = np.sum(allEigenValues)
    bottomBound = sumArr*prop
    eigenValues, eigenVectors = eigh(S,subset_by_value=[bottomBound, np.inf])
    index = np.argsort(eigenValues)[::-1]
    eigenValues = eigenValues[index]
    eigenVectors = eigenVectors[:,index]
    zeroMatrix = np.zeros((len(eigenValues),len(eigenValues)))
    for i in range(len(eigenValues)):
        zeroMatrix[i][i] = eigenValues[i]
    return zeroMatrix, eigenVectors

def project_image(image, U):
    # Your implementation goes here!
    Ut = np.transpose(U)
    aij = Ut@image
    xipca = aij@Ut
    return xipca

def display_image(orig, proj):
    # Your implementation goes here!
    reshapedOrig = np.reshape(orig,(-1,32))
    reshapedOrig = np.transpose(reshapedOrig)
    reshapedProj = proj.reshape(32,32)
    reshapedProj = np.transpose(reshapedProj)
    f, (ax1, ax2) = plt.subplots(1, 2)
    ax1.set_title('Original')
    ax2.set_title('Projection')
    pos1 = ax1.imshow(reshapedOrig, cmap='viridis', interpolation='none', aspect='equal')
    pos2 = ax2.imshow(reshapedProj, cmap='viridis', interpolation='none', aspect='equal')
    f.colorbar(pos1, ax=ax1, shrink=0.5)
    f.colorbar(pos2, ax=ax2, shrink=0.5)
    plt.show()
    pass

x = load_and_center_dataset('YaleB_32x32.npy')
S = get_covariance(x)
Lambda, U = get_eig(S, 2)
print(U.shape)
