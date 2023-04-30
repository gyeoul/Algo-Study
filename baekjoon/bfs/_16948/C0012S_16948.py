"""
16948. Silver 1 - 데스 나이트

    시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
    2초            512MB          5679      3877     3246          69.137%


    문제
        게임을 좋아하는 큐브러버는 체스에서 사용할 새로운 말 "데스 나이트"를 만들었다. 데스 나이트가 있는 곳이 (r, c)라면, (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)로 이동할 수 있다.
        크기가 N×N인 체스판과 두 칸 (r1, c1), (r2, c2)가 주어진다. 데스 나이트가 (r1, c1)에서 (r2, c2)로 이동하는 최소 이동 횟수를 구해보자. 체스판의 행과 열은 0번부터 시작한다.
        데스 나이트는 체스판 밖으로 벗어날 수 없다.


    입력
        첫째 줄에 체스판의 크기 N(5 ≤ N ≤ 200)이 주어진다. 둘째 줄에 r1, c1, r2, c2가 주어진다.


    출력
        첫째 줄에 데스 나이트가 (r1, c1)에서 (r2, c2)로 이동하는 최소 이동 횟수를 출력한다. 이동할 수 없는 경우에는 -1을 출력한다.


    예제 입력 1
        7
        6 6 0 1
    예제 출력 1
        4

    예제 입력 2
        6
        5 1 0 5
    예제 출력 2
        -1

    예제 입력 3
        7
        0 3 4 3
    예제 출력 3
        2


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
"""


# 메모리 : 34176KB
# 시간 : 80ms
# 코드 길이 : 1556B
# 정답

from collections import deque


def check(x, y): # 이동 가능한 좌표인지 체크
    if x < 0 or x >= N or y < 0 or y >= N: # 체스판을 벗어난 경우
        return False

    if chessBoard[x][y] != 0: # 처음 방문하는 좌표가 아닐 경우
        return False

    return True


def move(r, c):
    que = deque() # 큐(Queue) 구현을 위해 deque 라이브러리 사용
    que.append((r, c))
    chessBoard[r][c] = 1 # 시작 좌표 이동 체크

    while que: # 큐가 빌 때까지 반복
        now_r, now_c = que.popleft() # 현재 좌표

        for d in range(6): # 데스 나이트가 이동 가능한 6 가지 방향
            nx = now_r + dx[d] # 현재 좌표에서 이동 가능한 x 좌표
            ny = now_c + dy[d] # 현재 좌표에서 이동 가능한 y 좌표

            if check(nx, ny): # 이동 가능한 좌표라면
                chessBoard[nx][ny] = chessBoard[now_r][now_c] + 1
                que.append((nx, ny))

    return chessBoard[r2][c2] - 1 # 도착해야 하는 좌표까지 이동한 횟수 - 시작 좌표에서 카운트한 횟수


N = int(input()) # 체스판의 크기 N(5 ≤ N ≤ 200)
r1, c1, r2, c2 = map(int, input().split()) # 시작 좌표 (r1, c1), 도착 좌표 (r2, c2)

chessBoard = [[0] * N for _ in range(N)] # 체스판  # 각 좌표까지 가기 위한 이동 횟수 기록
dx = [-2, -2, 0, 0, 2, 2] # 데스 나이트가 이동 가능한 6 가지 x 방향
dy = [-1, 1, -2, 2, -1, 1] # 데스 나이트가 이동 가능한 6 가지 y 방향

print(move(r1, c1))
