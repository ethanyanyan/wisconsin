try:
    first_line = input().strip()
    num_lines = int(first_line)

    for i in range(num_lines):
        line = input().strip()
        print(f"Hello, {line}!")
except ValueError:
    print("The first line is not a valid integer.")
except Exception as e:
    print(f"An error occurred: {e}")