"""
1339. Gold 4 - 단어 수학

    시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
    2초            256MB          27895    12678     9605          45.170%


    문제
        민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.
        단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
        예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.
        N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.


    입력
        첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다. 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다. 단어는 알파벳 대문자로만 이루어져있다. 모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.


    출력
        첫째 줄에 주어진 단어의 합의 최댓값을 출력한다.


    예제 입력 1
        2
        AAA
        AAA
    예제 출력 1
        1998

    예제 입력 2
        2
        GCF
        ACDEB
    예제 출력 2
        99437

    예제 입력 3
        10
        A
        B
        C
        D
        E
        F
        G
        H
        I
        J
    예제 출력 3
        45

    예제 입력 4
        2
        AB
        BA
    예제 출력 4
        187


    알고리즘 분류
        그리디 알고리즘
"""


# 메모리 : 31256KB
# 시간 : 44ms
# 코드 길이 : 932B
# 정답

def calculate():
    for i in range(len(word)): # 단어 개수만큼 반복
        for j in range(len(word[i])): # 해당 단어의 길이만큼 반복
            multipleNum[ord(word[i][j]) - 65] += pow(10, len(word[i]) - j - 1) # 전에 나왔던 알파벳과 같은 알파벳이 나오면 기존의 자릿수에 현재 자릿수를 합한다.


N = int(input()) # 단어의 개수

word = [] # 입력한 단어를 담을 리스트
multipleNum = [0 for _ in range(26)] # 알파벳 26 가지의 자릿수를 담을 리스트

for n in range(N):
    word.append(input()) # 입력한 단어 리스트에 추가

calculate()
multipleNum.sort(reverse = True) # 내림차순 정렬

result = 0
coefficient = 9 # 계수(자릿수에 곱할 각각의 알파벳에 대응하는 숫자)
for r in range(len(multipleNum)):
    if multipleNum[r] != 0:
        result += (multipleNum[r]) * coefficient
        coefficient -= 1

print(result)
