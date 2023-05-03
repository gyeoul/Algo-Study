"""
16197. Gold 4 - 두 동전

    시간 제한       메모리 제한      제출      정답      맞힌 사람       정답 비율
    2초            512MB          9361      4129     2797          42.340%


    문제
        N×M 크기의 보드와 4개의 버튼으로 이루어진 게임이 있다. 보드는 1×1크기의 정사각형 칸으로 나누어져 있고, 각각의 칸은 비어있거나, 벽이다. 두 개의 빈 칸에는 동전이 하나씩 놓여져 있고, 두 동전의 위치는 다르다.
        버튼은 "왼쪽", "오른쪽", "위", "아래"와 같이 4가지가 있다. 버튼을 누르면 두 동전이 버튼에 쓰여 있는 방향으로 동시에 이동하게 된다.
            · 동전이 이동하려는 칸이 벽이면, 동전은 이동하지 않는다.
            · 동전이 이동하려는 방향에 칸이 없으면 동전은 보드 바깥으로 떨어진다.
            · 그 외의 경우에는 이동하려는 방향으로 한 칸 이동한다.이동하려는 칸에 동전이 있는 경우에도 한 칸 이동한다.
        두 동전 중 하나만 보드에서 떨어뜨리기 위해 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 20)
        둘째 줄부터 N개의 줄에는 보드의 상태가 주어진다.
            · o : 동전
            · . : 빈 칸
            · # : 벽
        동전의 개수는 항상 2개이다.


    출력
        첫째 줄에 두 동전 중 하나만 보드에서 떨어뜨리기 위해 눌러야 하는 버튼의 최소 횟수를 출력한다. 만약, 두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 한다면, -1을 출력한다.


    예제 입력 1
        1 2
        oo
    예제 출력 1
        1

    예제 입력 2
        6 2
        .#
        .#
        .#
        o#
        o#
        ##
    예제 출력 2
        4

    예제 입력 3
        6 2
        ..
        ..
        ..
        o#
        o#
        ##
    예제 출력 3
        3

    예제 입력 4
        5 3
        ###
        .o.
        ###
        .o.
        ###
    예제 출력 4
        -1

    예제 입력 5
        5 3
        ###
        .o.
        #.#
        .o.
        ###
    예제 출력 5
        3


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        백트래킹
"""


# 메모리 : 155968KB
# 시간 : 1356ms
# 코드 길이 : 1931B
# 정답

from collections import deque


def check(x, y): # 보드 안에 있는지 체크
    if 0 <= x < N and 0 <= y < M:
        return True

    return False


def move(ax, ay, bx, by, cnt): # move(동전 1 x 위치, 동전 2 y 위치, 동전 2 x 위치, 동전 2 y 위치, 버튼을 누른 횟수)
    que = deque()
    que.append([ax, ay, bx, by, cnt])

    while que:
        now = que.popleft()
        count = now[4]

        if count >= 10:
            return -1

        for d in range(4): # 두 동전이 이동할 위치 체크
            nx1 = now[0] + dx[d]
            ny1 = now[1] + dy[d]
            nx2 = now[2] + dx[d]
            ny2 = now[3] + dy[d]

            if check(nx1, ny1) and check(nx2, ny2): # 두 동전이 이동할 위치가 모두 보드 안에 있을 경우
                if board[nx1][ny1] == "#": # 동전 1이 이동할 위치가 벽일 경우
                    nx1 = now[0]
                    ny1 = now[1]

                if board[nx2][ny2] == "#": # 동전 2가 이동할 위치가 벽일 경우
                    nx2 = now[2]
                    ny2 = now[3]

                que.append([nx1, ny1, nx2, ny2, count + 1])

            elif check(nx2, ny2): # 동전 1이 떨어졌을 경우
                return count + 1

            elif check(nx1, ny1): # 동전 2가 떨어졌을 경우
                return count + 1

            else: # 동전 모두 떨어졌을 경우
                continue

    return -1


N, M = map(int, input().split()) # N × M 크기의 보드

board = [] # 보드의 상태를 저장할 리스트
coin = [] # 동전이 있는 보드의 위치를 저장할 리스트
for r in range(N):
    board.append(list(input()))

    for c in range(M):
        if board[r][c] == "o":
            coin.append((r, c))

dx = [-1, 1, 0, 0] # 상, 하, 좌, 우
dy = [0, 0, -1, 1] # 상, 하, 좌, 우

print(move(coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0))
