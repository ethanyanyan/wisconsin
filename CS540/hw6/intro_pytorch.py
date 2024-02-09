import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
from torchvision import datasets, transforms

# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.

def get_data_loader(training = True):
    """
    INPUT: 
        An optional boolean argument (default value is True for training dataset)

    RETURNS:
        Dataloader for the training set (if training = True) or the test set (if training = False)
    """
    custom_transform=transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize((0.1307,), (0.3081,))
        ])
    
    train_set=datasets.FashionMNIST('./data',train=True, download=True,transform=custom_transform)
    test_set=datasets.FashionMNIST('./data', train=False, transform=custom_transform)
    if (training):
        data_set = train_set
        loader = torch.utils.data.DataLoader(data_set, batch_size = 64)
    else:
        data_set = test_set
        loader = torch.utils.data.DataLoader(data_set, batch_size = 64, shuffle = False)

    return loader


def build_model():
    """
    INPUT: 
        None

    RETURNS:
        An untrained neural network model
    """
    model = nn.Sequential(
        nn.Flatten(),
        nn.Linear(784, 128),
        nn.ReLU(),
        nn.Linear(128, 64),
        nn.ReLU(),
        nn.Linear(64, 10)
    )
    return model

def train_model(model, train_loader, criterion, T):
    """
    INPUT: 
        model - the model produced by the previous function
        train_loader  - the train DataLoader produced by the first function
        criterion   - cross-entropy 
        T - number of epochs for training

    RETURNS:
        None
    """
    criterion = nn.CrossEntropyLoss()
    opt = optim.SGD(model.parameters(), lr=0.001, momentum=0.9)
    for epoch in range(T):
        running_loss = 0.0
        total_count = 0
        correct_count = 0
        model.train()
        for images, labels in train_loader:
            # get the inputs; data is a list of [inputs, labels]
            batch_size = len(labels)
            total_count += batch_size
            # zero the parameter gradients
            opt.zero_grad()

            # forward + backward + optimize
            outputs = model(images)
            for j in range(batch_size):
                predicted_label = torch.argmax(outputs[j])
                if predicted_label == labels[j]:
                    correct_count += 1
            loss = criterion(outputs, labels)
            loss.backward()
            opt.step()

            # print statistics
            running_loss += loss.item() * batch_size
        print(f'Train Epoch: {epoch}  Accuracy: {correct_count}/{total_count}({correct_count*100/total_count :.2f}%) Loss: {running_loss/total_count :.3f}')
        running_loss = 0.0

def evaluate_model(model, test_loader, criterion, show_loss = True):
    """
    INPUT: 
        model - the the trained model produced by the previous function
        test_loader    - the test DataLoader
        criterion   - cropy-entropy 

    RETURNS:
        None
    """
    criterion = nn.CrossEntropyLoss()
    running_loss = 0.0
    total_count = 0
    correct_count = 0
    model.eval()
    with torch.no_grad():
        for images, labels in test_loader:
            # get the inputs; data is a list of [inputs, labels]
            batch_size = len(labels)
            total_count += batch_size

            # forward + backward + optimize
            outputs = model(images)
            for j in range(batch_size):
                predicted_label = torch.argmax(outputs[j])
                if predicted_label == labels[j]:
                    correct_count += 1
            loss = criterion(outputs, labels)

            # print statistics
            running_loss += loss.item() * batch_size
    if show_loss == True:
        print(f'Average Loss: {running_loss/total_count :.4f}\nAccuracy: {correct_count*100/total_count :.2f}%')
    else:
        print(f'Accuracy: {correct_count*100/total_count :.2f}%')
    
def predict_label(model, test_images, index):
    """
    INPUT: 
        model - the trained model
        test_images   -  a tensor. test image set of shape Nx1x28x28
        index   -  specific index  i of the image to be tested: 0 <= i <= N - 1


    RETURNS:
        None
    """
    image = test_images[index]
    logits = model(image)
    prob = F.softmax(logits, dim=1)
    top3, indices = torch.topk(prob, 3)
    class_names = ['T-shirt/top','Trouser','Pullover','Dress','Coat','Sandal','Shirt','Sneaker','Bag','Ankle Boot']
    for i in range(len(indices[0])):
        print(f'{class_names[indices[0][i]]}: {top3[0][i]*100 :.2f}%')

if __name__ == '__main__':
    '''
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    '''
    torch.manual_seed(0)
    train_loader = get_data_loader()
    model = build_model()
    criterion = nn.CrossEntropyLoss()
    train_model(model,train_loader,criterion,5)
