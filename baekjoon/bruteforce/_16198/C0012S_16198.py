"""
16198. Silver 1 - 에너지 모으기

    시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
    1초            512MB          4737      3524     2818          75.448%


    문제
        N개의 에너지 구슬이 일렬로 놓여져 있고, 에너지 구슬을 이용해서 에너지를 모으려고 한다.
        i번째 에너지 구슬의 무게는 Wi이고, 에너지를 모으는 방법은 다음과 같으며, 반복해서 사용할 수 있다.
            1. 에너지 구슬 하나를 고른다. 고른 에너지 구슬의 번호를 x라고 한다. 단, 첫 번째와 마지막 에너지 구슬은 고를 수 없다.
            2. x번째 에너지 구슬을 제거한다.
            3. W_(x - 1) × W_(x + 1)의 에너지를 모을 수 있다.
            4. N을 1 감소시키고, 에너지 구슬을 1번부터 N번까지로 다시 번호를 매긴다. 번호는 첫 구슬이 1번, 다음 구슬이 2번, ... 과 같이 매겨야 한다.

        N과 에너지 구슬의 무게가 주어졌을 때, 모을 수 있는 에너지 양의 최댓값을 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 에너지 구슬의 개수 N(3 ≤ N ≤ 10)이 주어진다.
        둘째 줄에는 에너지 구슬의 무게 W_1, W2, ..., WN을 공백으로 구분해 주어진다. (1 ≤ W_i ≤ 1,000)


    출력
        첫째 줄에 모을 수 있는 에너지의 최댓값을 출력한다.


    예제 입력 1
        4
        1 2 3 4
    예제 출력 1
        12

    예제 입력 2
        5
        100 2 1 3 100
    예제 출력 2
        10400

    예제 입력 3
        7
        2 2 7 6 90 5 9
    예제 출력 3
        1818

    예제 입력 4
        10
        1 1 1 1 1 1 1 1 1 1
    예제 출력 4
        8


    알고리즘 분류
        브루트포스 알고리즘
        백트래킹
"""


# 메모리 : 31256KB
# 시간 : 92ms
# 코드 길이 : 1137B
# 정답

def calculateEnergy(num, energy): # calculateEnergy(에너지 구슬 선택 횟수, 현재까지 선택된 에너지 구슬들의 에너지 합)
    global maxEnergy

    if num == N - 2: # 맨 앞의 에너지 구슬과 맨 뒤의 에너지 구슬만 남도록 에너지 구슬을 선택했을 경우
        maxEnergy = max(maxEnergy, energy)

        return

    for w in range(1, len(Weights) - 1):
        selectedEnergy = Weights[w] # 선택된 에너지 구슬의 에너지
        gatheredEnergy = Weights[w - 1] * Weights[w + 1] # 선택된 에너지 구슬을 기준으로 모은 에너지

        Weights.pop(w) # 선택한 에너지 구슬 제거  # Weights.remove(selectedEnergy) : 값으로 제거할 경우 오답
        calculateEnergy(num + 1, energy + gatheredEnergy)
        Weights.insert(w, selectedEnergy) # 제거했던 에너지 구슬을 원래 위치에 추가


N = int(input()) # 에너지 구슬의 개수 N(3 ≤ N ≤ 10)
Weights = list(map(int, input().split())) # 에너지 구슬의 무게 리스트
maxEnergy = 0 # 모을 수 있는 에너지 양의 최댓값

calculateEnergy(0, 0)
print(maxEnergy)
