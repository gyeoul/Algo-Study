"""
작성자: 이지은
문제 번호: 14395
문제: 정수 s가 주어진다. 정수 s의 값을 t로 바꾸는 최소 연산 횟수를 구하는 프로그램을 작성하시오.
     사용할 수 있는 연산은 아래와 같다. (+, -, *, /)(s가 0이 아닐때만 사용 가능)
     첫째 줄에 정수 s를 t로 바꾸는 방법을 출력한다. s와 t가 같은 경우에는 0을, 바꿀 수 없는 경우에는 -1을 출력한다. 가능한 방법이 여러 가지라면, 사전 순으로 앞서는 것을 출력한다.
문제해결:
작성일: 2023-04-30
"""
from collections import deque

MAX = 1000000000

def bfs(s, t):
    isVisited = set([s]) #방문체크할 배열
    queue = deque()
    queue.append([s, ""])

    while queue:
        num, operStr = queue.popleft()
        if num == t: #값이 동일하면
            return operStr

        for oper in ["*", "+", "-", "/"]:
            newNum = -1

            if oper == "+":
                newNum = num + num
            elif oper == "-":
                newNum = num - num
            elif oper == "*":
                newNum = num * num
            elif oper == "/" and num != 0:
                newNum = num / num

            if 0<= newNum<= MAX and newNum not in isVisited: #범위 안에 존재하고, 방문하지 않았을 때
                isVisited.add(newNum)
                queue.append([newNum, operStr+oper])

    return -1 #바꿀 수 없는 경우

s, t = map(int, input().split())
answer = 0 #s == t
if s != t: #값이 동일하지않을 때
    answer = bfs(s, t)

print(answer)