import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time
import random
import math
from sympy import isprime


def countdown(numbers):
    sortedNumbers: list[int] = sorted(numbers)
    incrementedNumbers = map(lambda x: x + 1, sortedNumbers)
    filteredNumbers = filter(isprime, incrementedNumbers)


def ver_1(numbers: list[int]):
    threads = []
    thread_count = 2
    size = len(numbers) // thread_count
    for i in range(thread_count):
        threads.append(
            threading.Thread(
                target=lambda: countdown(numbers[i * size : (i + 1) * size])
            )
        )

    for thread in threads:
        thread.start()

    for thread in threads:
        thread.join()


def ver_2(nums: list[int]):
    countdown(nums)
    countdown(nums)


def ver_3(numbers: list[int]):
    processes = []
    threads = 2
    size = math.floor(len(numbers) / threads)
    for i in range(threads):
        processes.append(
            multiprocessing.Process(
                target=lambda: countdown(numbers[i * size : (i + 1) * size])
            )
        )

    for process in processes:
        process.start()

    for process in processes:
        process.join()


def ver_4(numbers: list[int]):
    threads = 2
    size = math.floor(len(numbers) / threads)
    with ThreadPoolExecutor(max_workers=threads) as executor:
        for i in range(threads):
            future = executor.submit(
                lambda: countdown(numbers[i * size : (i + 1) * size])
            )


if __name__ == "__main__":
    numbers = random.sample(range(0, 100), 100)

    start = time.time()
    ver_1(numbers.copy())
    end = time.time()
    print("\n Timp executie pseudoparalelism cu GIL")
    print(end - start)

    start = time.time()
    ver_2(numbers.copy())
    end = time.time()
    print("\n Timp executie secvential")
    print(end - start)

    start = time.time()
    ver_3(numbers.copy())
    end = time.time()
    print("\n Timp executie paralela cu multiprocessing")
    print(end - start)

    start = time.time()
    ver_4(numbers.copy())
    end = time.time()
    print("\n Timp executie paralela cu concurrent.futures")
    print(end - start)
