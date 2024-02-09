def get_futures(futures, page_reqs, num_page_reqs):
    for i in range(num_page_reqs):
        curr_page = page_reqs[i]

        if curr_page not in futures.keys():
            futures[curr_page] = [i]
        else:
            futures[curr_page].append(i)


def get_faults(cache, futures, page_reqs, num_page_reqs, max_cache_len):
    faults = 0
    for i in range(num_page_reqs):
        curr_page = page_reqs[i]

        if curr_page not in cache and len(cache) < max_cache_len:
            faults += 1
            cache.append(curr_page)
        elif curr_page not in cache and len(cache) == max_cache_len:
            faults += 1
            ff_index = -1
            ff_page = 0

            for j in range(max_cache_len):
                fut_list = futures[cache[j]]
                if i >= max(fut_list):
                    ff_page = cache[j]
                    ff_index = num_page_reqs + 1
                    break

                for k in range(len(fut_list)):

                    if fut_list[k] <= i:
                        continue
                    elif fut_list[k] > ff_index:
                        ff_page = cache[j]
                        ff_index = fut_list[k]
                        break
                    else:
                        break
                
            cache.remove(ff_page)
            cache.append(curr_page)
        else:
            continue
    print(faults)


def main():
    num_instances = int(input().strip())

    for _ in range(num_instances):
        max_cache_len = int(input().strip())
        num_page_reqs = int(input().strip())
        cache = []
        futures = {}
        page_reqs = [int(x) for x in input().split()]

        get_futures(futures, page_reqs, num_page_reqs)

        get_faults(cache, futures, page_reqs, num_page_reqs, max_cache_len)



if __name__ == "__main__":
    main()

