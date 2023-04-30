"""
작성자: 이지은
문제: 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
     또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
     적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
문제해결: BFS 사용.
작성일: 2023-04-29
"""
from collections import deque

n = int(input()) #nXn
board = [list(input()) for _ in range(n)] #입력받은 문자열을 리스트에 담음
queue = deque()
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def check(ny, nx):
    if 0<=ny<n and 0<=nx<n and not isVisited[ny][nx]:
        return True
    else:
        return False

def bfs(row, col):
    queue.append([row, col])
    isVisited[row][col] = True #방문처리

    while queue: #큐가 빌때까지
        y, x = queue.popleft()
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if check(ny, nx) and board[y][x] == board[ny][nx]: #같은 문자일 때
                isVisited[ny][nx] = True #방문처리
                queue.append([ny, nx]) #해당 좌표 큐에 삽입

cnt = [0, 0]
for i in range(2): #적록색약이 아닌 사람, 적록색약인 사람 각각의 구역의 수
    isVisited = [[False for _ in range(n)] for _ in range(n)]

    for row in range(n):
        for col in range(n):
            if not isVisited[row][col]: #False일 때만
                bfs(row, col)
                cnt[i] += 1

    #적록색약인 사람 체크 전 G를 R로 변환
    board = [["R" if x == "G" else x for x in row2 ] for row2 in board] #리스트 컴프리헨션 사용

print(' '.join(map(str, cnt))) #공백을 두고 출력