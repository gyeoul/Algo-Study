"""
1182. Silver 2 - 부분수열의 합

    시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
    2초            256MB          66896    30728     19976         44.108%


    문제
        N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.


    출력
        첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.


    예제 입력 1
        5 0
        -7 -3 -2 5 8
    예제 출력 1
        1


    알고리즘 분류
        브루트포스 알고리즘
        백트래킹
"""


# 메모리 : 31256KB
# 시간 : 1196ms
# 코드 길이 : 806B
# 정답

def subSet(num): # 부분 집합
    global count
    sumNum = 0
    flag = False

    if num == N:
        for n in range(N):
            if isSelected[n]:
                flag = True # 부분 집합으로 뽑은 원소가 있는지 체크
                sumNum += numList[n]

        if sumNum == S and flag: # 부분 집합 원소들(부분 수열)의 합이 S이고, 공집합이 아닐 경우
            count += 1

        return

    isSelected[num] = False # 해당 인덱스 선택 X
    subSet(num + 1)

    isSelected[num] = True # 해당 인덱스 선택 o
    subSet(num + 1)


N, S = map(int, input().split()) # S : 부분 수열의 합이 될 값
numList = list(map(int, input().split())) # N 개의 정수로 이루어진 수열
isSelected = [False] * N
count = 0

subSet(0)
print(count)
