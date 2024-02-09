def get_inversions(num_elements, elements):
    sorted_array, num_inversions = count_sort(elements)
    print(num_inversions)

def count_sort(A):
    if len(A) == 1:
        return A, 0

    middle_index = len(A) // 2
    A1, c1 = count_sort(A[:middle_index])
    A2, c2 = count_sort(A[middle_index:])
    A, c = merge_count(A1, A2)

    return A, c + c1 + c2

def merge_count(A, B):
    S = []
    c = 0
    i = 0
    j = 0

    while i < len(A) and j < len(B):
        if A[i] <= B[j]:
            S.append(A[i])
            i += 1
        else:
            S.append(B[j])
            j += 1
            c += len(A) - i

    while i < len(A):
        S.append(A[i])
        i += 1

    while j < len(B):
        S.append(B[j])
        j += 1

    return S, c

def main():
    num_instances = int(input().strip())

    for _ in range(num_instances):
        num_elements = int(input().strip())
        elements = [int(x) for x in input().split()]
        get_inversions(num_elements, elements)

if __name__ == "__main__":
    main()
