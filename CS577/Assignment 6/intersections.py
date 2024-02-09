def get_intersections(list1, list2):
    # combined = zip(list1, list2)
    # sorted1 = [x for x,_ in sorted(zip(list1, list2))]
    sorted2 = [x for _,x in sorted(zip(list1, list2))]
    get_inversions(sorted2)

def get_inversions(elements):
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

def get_list(e):
    return [int(input().strip()) for _ in range(e)]

def main():
    num_instances = int(input().strip())

    for _ in range(num_instances):
        num_elements = int(input().strip())
        list1 = get_list(num_elements)
        list2 = get_list(num_elements)

        get_intersections(list1, list2)

if __name__ == "__main__":
    main()
