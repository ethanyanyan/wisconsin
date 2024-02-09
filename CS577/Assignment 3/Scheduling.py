def get_job_duration(job):
    return job[1]

def finishFirst(job_list):
    job_list.sort(key=get_job_duration)
    num_jobs = len(job_list)
    if num_jobs > 0:
        scheduled_jobs = 1
        current_job = job_list[0]

        for i in range(1, num_jobs):
            if job_list[i][0] >= current_job[1]:
                current_job = job_list[i]
                scheduled_jobs += 1

        return scheduled_jobs
    else:
        return 0

def main():
    num_instances = int(input().strip())

    for _ in range(num_instances):
        num_jobs = int(input().strip())
        job_list = []

        for _ in range(num_jobs):
            start, end = map(int, input().strip().split())
            job_list.append((start, end))

        result = finishFirst(job_list)
        print(result)

if __name__ == "__main__":
    main()

