if __name__ == "__main__":

    import csv
    import sys
    import numpy as np
    import matplotlib.pyplot as plt
    # Load file from argument at index 1, which should be hw5.csv
    with open(sys.argv[1], 'r') as file:
        dataArr = np.loadtxt(file, delimiter=',', skiprows = 1)
        xAxisData = np.array(dataArr[:,0], dtype = np.int64)
        yAxisData = np.array(dataArr[:,1], dtype = np.int64)
        xPoints = np.floor(xAxisData)
        plt.plot(xPoints,yAxisData)
        plt.xlabel('Year')
        plt.ylabel('Number of frozen days')
        plt.savefig("plot.jpg")
        #plt.show()

    # Concatenate column of 1s to x vector
    #np.set_printoptions(suppress=True)
    onesRow = np.ones((len(xAxisData),1),dtype = np.int64)
    xAxisData = np.reshape(xAxisData,(len(xAxisData),1))
    yVector = np.reshape(yAxisData,(len(yAxisData),1))
    xVector = np.concatenate((onesRow, xAxisData), axis = 1)
    print("Q3a:")
    print(xVector)

    print("Q3b:")
    print(np.transpose(yVector)[0])

    Z = np.dot(np.transpose(xVector),xVector)
    print("Q3c:")
    print(Z)

    Zinv = np.linalg.inv(Z)
    print("Q3d:")
    print(Zinv)

    PI = np.dot(Zinv,(np.transpose(xVector)))
    print("Q3e:")
    print(PI)

    hat_beta = PI@yVector
    print("Q3f:")
    print(np.transpose(hat_beta)[0])

    x_test = 2022
    y_test = hat_beta[0] + hat_beta[1]*x_test
    print("Q4: " + str(y_test[0]))

    symbol = ''
    explanation = ''
    if (hat_beta[1] < 0):
        symbol = '<'
        explanation = 'Number of days Mendota is frozen is decreasing every year, on average. As x_test increases, meaning as the years pass, y_hat would decrease as b_hat0 is constant and b_hat1 is negative'
    elif (hat_beta[1] > 0):
        symbol = '>'
        explanation = 'Number of days Mendota is frozen is increasing every year, on average. As x_test increases, meaning as the years pass, y_hat would increase as b_hat0 is constant and b_hat1 is positive'
    else:
        symbol = '='
        explanation = 'Number of days Mendota is frozen stays constant every year, on average. As x_test increases, meaning as the years pass, y_hat would remain constant since b_hat1 is zero'
    print("Q5a: " + symbol)
    print("Q5b: " + explanation)

    x_star = (-hat_beta[0])/(hat_beta[1])
    print("Q6a: " + str(x_star[0]))
    if (hat_beta[1] < 0 and x_star < 1855):
        explanation2 = ("Since hat_beta_1 is negative, the number of days generally decreases every year. Since answer to 6a < 1855, it is not a compelling prediction since it does not match the trend, years before 1855 would have days where Mendota is frozen.")
    elif (hat_beta[1] < 0 and x_star > 2021):
        explanation2 = ("Since hat_beta_1 is negative, and the x_star is after 2021, this matches the trend. Thus, it is a compelling prediction.")
    elif (hat_beta[1] > 0):
        explanation2 = ("Since hat_beta_1 is positive, the number of days generally increases every year. It is not a compelling prediction since the number of days where Mendota is frozen will keep increasing.")
    elif (hat_beta[1] == 0):
        explanation2 = ("Since hat_beta_1 is 0, the number of days Lake Mendota is frozen stays generally constant. Thus, it is not a compelling prediction.")
    else:
        explanation2 = ("The predicted year where Lake Mendota does not have a frozen day does not match the trend as it lies between 1855 and 2021, which has > 0 days of freezing. Thus, it is not a compelling prediction.")
    print("Q6b: " + explanation2)