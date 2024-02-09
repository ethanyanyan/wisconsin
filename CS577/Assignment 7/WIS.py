def finishFirst(job_list):
    job_list.sort(key=lambda x: x[1]) # Sort by finish time
    num_jobs = len(job_list)
    # print(job_list)
    if num_jobs > 0:
        dp = [0] * (num_jobs)
        dp[0] = job_list[0][2]
        for i in range(1, num_jobs):
            index = -1
            current_job = job_list[i]
            if current_job[0] <= job_list[0][0]:
                dp[i] = max(dp[i - 1], current_job[2])
                continue
            for j in range(i-1,-1,-1):
                if job_list[j][1] <= current_job[0]:
                    index = j
                    break
            if index != -1:
                dp[i] = max(dp[i - 1], dp[index] + current_job[2])
            else:
                dp[i] = max(dp[i - 1], current_job[2])
        
        # print(dp)


        return dp[num_jobs-1]
    else:
        return 0

def main():
    num_instances = int(input().strip())

    for _ in range(num_instances):
        num_jobs = int(input().strip())
        job_list = []

        for _ in range(num_jobs):
            start, end, weight = map(int, input().strip().split())
            job_list.append((start, end, weight))

        result = finishFirst(job_list)
        print(result)

if __name__ == "__main__":
    main()

