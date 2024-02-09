def maxValue(item_list, capacity):
    num_items = len(item_list)
    dp = [[0] * (capacity + 1) for _ in range(num_items + 1)]

    for i in range(1, num_items + 1):
        for j in range(1, (capacity + 1)):
            item_weight = item_list[i-1][0]
            item_value = item_list[i-1][1]
            if item_weight > j:
                dp[i][j] = dp[i-1][j]
                continue
            dp[i][j] = max(dp[i-1][j], dp[i-1][j - item_weight] + item_value)
    return dp[num_items][capacity]


def main():
    num_instances = int(input().strip())

    for _ in range(num_instances):
        items, capacity = map(int, input().strip().split())
        item_list = []

        for _ in range(items):
            weight, value = map(int, input().strip().split())
            item_list.append((weight, value))

        result = maxValue(item_list, capacity)
        print(result)

if __name__ == "__main__":
    main()

