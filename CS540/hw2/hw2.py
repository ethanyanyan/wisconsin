import sys
import math


def get_parameter_vectors():
    '''
    This function parses e.txt and s.txt to get the  26-dimensional multinomial
    parameter vector (characters probabilities of English and Spanish) as
    descibed in section 1.2 of the writeup

    Returns: tuple of vectors e and s
    '''
    #Implementing vectors e,s as lists (arrays) of length 26
    #with p[0] being the probability of 'A' and so on
    e=[0]*26
    s=[0]*26

    with open('e.txt',encoding='utf-8') as f:
        for line in f:
            #strip: removes the newline character
            #split: split the string on space character
            char,prob=line.strip().split(" ")
            #ord('E') gives the ASCII (integer) value of character 'E'
            #we then subtract it from 'A' to give array index
            #This way 'A' gets index 0 and 'Z' gets index 25.
            e[ord(char)-ord('A')]=float(prob)
    f.close()

    with open('s.txt',encoding='utf-8') as f:
        for line in f:
            char,prob=line.strip().split(" ")
            s[ord(char)-ord('A')]=float(prob)
    f.close()

    return (e,s)

def shred(filename):
    #Using a dictionary here. You may change this to any data structure of
    #your choice such as lists (X=[]) etc. for the assignment
    X=dict()
    for i in range(65, 91):
        X[chr(i)] = 0
    with open (filename,encoding='utf-8') as f:
        # TODO: add your code here
        while 1:
            char = f.read(1)
            if not char:
                break
            if ord(char) > 96 and ord(char) < 123:
                char = chr(ord(char) - 32)
            if char in X:
                X.update({char:(X.get(char))+1})


    return X



# TODO: add your code here for the assignment
# You are free to implement it as you wish!
# Happy Coding!
print("Q1")
d = shred("letter.txt")
for key in d:
    print(key + " " + str(d[key]))

print("Q2")
q2E = d["A"]*math.log(0.0834417)
q2S = d["A"]*math.log(0.121649)
print("%.4f" % q2E)
print("%.4f" % q2S)

print("Q3")
e = get_parameter_vectors()[0]
s = get_parameter_vectors()[1]

sumE = 0
sumS = 0
for i in range(1,27):
    sumE = sumE + d[chr(i+64)] * math.log(e[i-1])
    sumS = sumS + d[chr(i+64)] * math.log(s[i-1])
outputE = math.log(0.6) + sumE
outputS = math.log(0.4) + sumS
print("%.4f" % outputE)
print("%.4f" % outputS)

print("Q4")
if (outputS - outputE) >= 100 :
    print("%.4f" % 0)
elif (outputS - outputE) <= -100 :
    print("%.4f" % 1)
else:
    finalOutput = 1/(1+pow(math.e,(outputS - outputE)))
    print("%.4f" % finalOutput)