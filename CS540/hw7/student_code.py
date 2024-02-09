# python imports
import os
from tqdm import tqdm

# torch imports
import torch
import torch.nn as nn
import torch.optim as optim

# helper functions for computer vision
import torchvision
import torchvision.transforms as transforms


class LeNet(nn.Module):
    def __init__(self, input_shape=(32, 32), num_classes=100):
        super(LeNet, self).__init__()
        # First stage
        self.conv_layer1 = nn.Conv2d(in_channels=3, out_channels=6, kernel_size=5, stride=1)
        self.relu1 = nn.ReLU()
        self.max_pool1 = nn.MaxPool2d(kernel_size = 2, stride = 2)

        # Second stage
        self.conv_layer2 = nn.Conv2d(in_channels=6, out_channels=16, kernel_size=5, stride=1)
        self.relu2 = nn.ReLU()
        self.max_pool2 = nn.MaxPool2d(kernel_size = 2, stride = 2)

        # Third stage
        self.flatten1 = nn.Flatten(start_dim=1, end_dim=-1)

        # Fourth stage
        self.fc1 = nn.Linear(16*5*5, 256)
        self.relu3 = nn.ReLU()

        # Fifth stage
        self.fc2 = nn.Linear(256, 128)
        self.relu4 = nn.ReLU()

        # Sixth stage
        self.fc3 = nn.Linear(128, num_classes)

    def forward(self, x):
        shape_dict = {}
        
        # First stage
        out = self.conv_layer1(x)
        out = self.relu1(out)
        out = self.max_pool1(out)
        # Store [batch size, number of input channel, width of image, height of image]
        shape_dict[1] = [out.shape[0], out.shape[1], out.shape[2], out.shape[3]]
        
        # Second stage
        out = self.conv_layer2(out)
        out = self.relu2(out)
        out = self.max_pool2(out)
        shape_dict[2] = [out.shape[0], out.shape[1], out.shape[2], out.shape[3]]
        
        # Third stage
        out = self.flatten1(out)
        shape_dict[3] = [out.shape[0], out.shape[1]]
        
        # Fourth stage
        out = self.fc1(out)
        out = self.relu3(out)
        shape_dict[4] = [out.shape[0], out.shape[1]]
        
        # Fifth stage
        out = self.fc2(out)
        out = self.relu4(out)
        shape_dict[5] = [out.shape[0], out.shape[1]]
        
        # Sixth stage
        out = self.fc3(out)
        shape_dict[6] = [out.shape[0], out.shape[1]]
        
        return out, shape_dict

def count_model_params():
    '''
    return the number of trainable parameters of LeNet.
    '''
    model = LeNet()
    total = 0
    for name, param in model.named_parameters():
        if (len(param.shape) == 4):
            total = total + param.shape[2] * param.shape[3] * param.shape[1] * param.shape[0]
        if (len(param.shape) == 1):
            total = total + param.shape[0]
        if (len(param.shape) == 2):
            total = total + param.shape[0] * param.shape[1]
        
    model_params = total/1000000

    return model_params

def train_model(model, train_loader, optimizer, criterion, epoch):
    """
    model (torch.nn.module): The model created to train
    train_loader (pytorch data loader): Training data loader
    optimizer (optimizer.*): A instance of some sort of optimizer, usually SGD
    criterion (nn.CrossEntropyLoss) : Loss function used to train the network
    epoch (int): Current epoch number
    """
    model.train()
    train_loss = 0.0
    for input, target in tqdm(train_loader, total=len(train_loader)):
        ###################################
        # fill in the standard training loop of forward pass,
        # backward pass, loss computation and optimizer step
        ###################################

        # 1) zero the parameter gradients
        optimizer.zero_grad()
        # 2) forward + backward + optimize
        output, _ = model(input)
        loss = criterion(output, target)
        loss.backward()
        optimizer.step()

        # Update the train_loss variable
        # .item() detaches the node from the computational graph
        # Uncomment the below line after you fill block 1 and 2
        train_loss += loss.item()

    train_loss /= len(train_loader)
    print('[Training set] Epoch: {:d}, Average loss: {:.4f}'.format(epoch+1, train_loss))

    return train_loss


def test_model(model, test_loader, epoch):
    model.eval()
    correct = 0
    with torch.no_grad():
        for input, target in test_loader:
            output, _ = model(input)
            pred = output.max(1, keepdim=True)[1]
            correct += pred.eq(target.view_as(pred)).sum().item()

    test_acc = correct / len(test_loader.dataset)
    print('[Test set] Epoch: {:d}, Accuracy: {:.2f}%\n'.format(
        epoch+1, 100. * test_acc))

    return test_acc
