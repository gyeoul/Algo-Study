"""
작성자: 이지은
문제: 게임을 좋아하는 큐브러버는 체스에서 사용할 새로운 말 "데스 나이트"를 만들었다.
     데스 나이트가 있는 곳이 (r, c)라면, (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)로 이동할 수 있다.
     크기가 N×N인 체스판과 두 칸 (r1, c1), (r2, c2)가 주어진다. 데스 나이트가 (r1, c1)에서 (r2, c2)로 이동하는 최소 이동 횟수를 구해보자.
     체스판의 행과 열은 0번부터 시작한다. 데스 나이트는 체스판 밖으로 벗어날 수 없다.
해결: BFS사용
작성일: 2023-04-25
"""
from collections import deque

n = int(input()) #nXn
r1, c1, r2, c2 = map(int, input().split()) #좌표 입력
newList = [[-1]*n for _ in range(n)] #이동할 수 없는 경우 -1을 출력하기 때문에

dx = [-1, 1, -2, 2, -1, 1]
dy = [-2, -2, 0, 0, 2, 2]

def check(y, x): #이동 가능한 방향인지 체크
    if 0<= y<n and 0<=x<n and newList[y][x] == -1:
        return True
    else:
        return False

def bfs(y, x):
    queue = deque() #deque 사용
    queue.append((y, x)) #좌표를 큐에 삽입
    newList[y][x] = 0

    while queue:
        now = queue.popleft()

        for i in range(6): #6개의 방향으로
            ny = now[0] + dy[i]
            nx = now[1] + dx[i]

            if check(ny, nx): #이동 가능할 때
                queue.append((ny, nx)) #큐에 삽입
                newList[ny][nx] = newList[now[0]][now[1]] + 1

bfs(r1, c1)
print(newList[r2][c2])