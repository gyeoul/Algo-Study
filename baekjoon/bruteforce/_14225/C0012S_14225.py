"""
14225. Silver 1 - 부분수열의 합

    시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
    2초            512MB          9767     4632      3152          43.464%


    문제
        수열 S가 주어졌을 때, 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 구하는 프로그램을 작성하시오.
            예를 들어, S = [5, 1, 2]인 경우에 1, 2, 3(=1+2), 5, 6(=1+5), 7(=2+5), 8(=1+2+5)을 만들 수 있다. 하지만, 4는 만들 수 없기 때문에 정답은 4이다.


    입력
        첫째 줄에 수열 S의 크기 N이 주어진다. (1 ≤ N ≤ 20)
        둘째 줄에는 수열 S가 주어진다. S를 이루고있는 수는 100,000보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 출력한다.


    예제 입력 1
        3
        5 1 2
    예제 출력 1
        4

    예제 입력 2
        3
        2 1 4
    예제 출력 2
        8

    예제 입력 3
        4
        2 1 2 7
    예제 출력 3
        6


    알고리즘 분류
        브루트포스 알고리즘
"""


# 메모리 : 82836KB
# 시간 : 1484ms
# 코드 길이 : 1354B
# 정답

def subSet(num): # 부분 집합
    subSum = 0 # 해당 부분 집합(부분 수열)의 합을 저장할 변수

    if num == N:
        for n in range(N):
            if isSelected[n]:
                subSum += S[n]

        subSumList.append(subSum)

        return

    isSelected[num] = False
    subSet(num + 1)

    isSelected[num] = True
    subSet(num + 1)


def findAnswer(sumList):
    numList = [-1] * (max(sumList) + 1) # 부분 수열의 합의 개수를 체크해 줄 리스트
    minNum = max(sumList) + 1 # 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수  # 부분 수열의 합 리스트가 연속적인 수로 구성되었을 경우, 가장 작은 자연수는 부분 수열의 합 중 가장 큰 수 + 1이다.

    for s in range(len(sumList)): # 부분 수열의 합의 개수 체크
        numList[sumList[s]] += 1

    for r in range(len(numList)):
        if numList[r] == -1: # 부분 수열의 합 중 나오지 않은 수 체크
            minNum = min(minNum, r)

    print(minNum)


N = int(input()) # 수열 S의 크기 N (1 <= N <= 20)
S = list(map(int, input().split())) # 수열 S  # S를 이루고 있는 수는 100,000보다 작거나 같은 자연수이다.

isSelected = [False] * N
subSumList = [] # 부분 집합의 합을 저장할 리스트

subSet(0)
findAnswer(subSumList)
