import numpy as np
import os
from colorama import Fore
from colorama import Style
from copy import deepcopy
import pygame
from pygame.constants import KEYDOWN
import bfs
import astar
import astar1
import time
''' Timeout của mỗi map là 30 phút  '''
TIME_OUT = 1800
''' lấy path của folder testcases và checkpoints '''
path_board = os.getcwd() + '\\..\\Testcases'
path_checkpoint = os.getcwd() + '\\..\\Checkpoints'

''' lấy data từ các testcase để trả lại các bảng gồm các map'''
def get_boards():
    os.chdir(path_board)
    list_boards = []
    for file in os.listdir():
        if file.endswith(".txt"):
            file_path = f"{path_board}/{file}"
            board = get_board(file_path)
            # in file 
            list_boards.append(board)
    return list_boards

''' truyền data từ các file checkpoint để trả lại vị trí các checkpoint trong map'''
def get_check_points():
    os.chdir(path_checkpoint)
    list_check_point = []
    for file in os.listdir():
        if file.endswith(".txt"):
            file_path = f"{path_checkpoint}/{file}"
            check_point = get_pair(file_path)
            list_check_point.append(check_point)
    return list_check_point

''' chuyển đổi các ký tự trong một hàng từ file TXT sang ký tự tượng trưng để hiện thị map trong game'''
def format_row(row):
    for i in range(len(row)):
        if row[i] == '1':
            row[i] = '#'
        elif row[i] == 'p':
            row[i] = '@'
        elif row[i] == 'b':
            row[i] = '$'
        elif row[i] == 'c':
            row[i] = '%'

''' chuyển đổi ký tự checkpoint từ file txt sao chép sang một mảng '''
def format_check_points(check_points):
    result = []
    for check_point in check_points:
        result.append((check_point[0], check_point[1]))
    return result

''' trả về bản đồ dưới dạng một mảng NumPy, trong đó các ký tự đã được chuyển đổi thành các ký tự thể hiện các phần tử của bản đồ.'''
def get_board(path):
    result = np.loadtxt(f"{path}", dtype=str, delimiter=',')
    for row in result:
        format_row(row)
    return result

'''trả về checkpoints dưới dạng một mảng NumPy, trong đó các ký tự đã được chuyển đổi thành các ký tự thể hiện các phần tử của checkpoint. '''
def get_pair(path):
    result = np.loadtxt(f"{path}", dtype=int, delimiter=',')
    return result

'''
//========================//
//     KHAI BÁO VÀ        //
//  KHỞI TẠO BẢN ĐỒ VÀ   //
//      ĐIỂM KIỂM TRA      //
//========================//
'''
maps = get_boards()  # Lấy bản đồ
check_points = get_check_points()  # Lấy điểm kiểm tra

'''
//========================//
//         PYGAME         //
//     KHỞI TẠO HÌNH ẢNH  //
//                        //
//========================//
'''
pygame.init()  # Khởi tạo Pygame
pygame.font.init()  # Khởi tạo font Pygame
screen = pygame.display.set_mode((640, 640))  # Khởi tạo màn hình với kích thước 640x640
pygame.display.set_caption('Sokoban')  # Đặt tiêu đề cửa sổ là 'Sokoban'
clock = pygame.time.Clock()  # Tạo một đồng hồ
BACKGROUND = (0, 0, 0)  # Màu nền đen
WHITE = (255, 255, 255)  # Màu trắng

'''
LẤY CÁC TÀI SẢN
'''
assets_path = os.getcwd() + "\\..\\Assets"  # Đường dẫn đến thư mục chứa tài sản
os.chdir(assets_path)  # Thay đổi thư mục làm việc hiện tại thành thư mục tài sản
player = pygame.image.load(os.getcwd() + '\\player.png')  # Tải hình ảnh người chơi
wall = pygame.image.load(os.getcwd() + '\\wall.png')  # Tải hình ảnh tường
box = pygame.image.load(os.getcwd() + '\\box.png')  # Tải hình ảnh hộp
point = pygame.image.load(os.getcwd() + '\\point.png')  # Tải hình ảnh điểm kiểm tra
space = pygame.image.load(os.getcwd() + '\\space.png')  # Tải hình ảnh không gian
arrow_left = pygame.image.load(os.getcwd() + '\\arrow_left.png')  # Tải hình ảnh mũi tên trái
arrow_right = pygame.image.load(os.getcwd() + '\\arrow_right.png')  # Tải hình ảnh mũi tên phải
init_background = pygame.image.load(os.getcwd() + '\\init_background.png')  # Tải hình ảnh nền khởi tạo
loading_background = pygame.image.load(os.getcwd() + '\\loading_background.png')  # Tải hình ảnh nền tải
notfound_background = pygame.image.load(os.getcwd() + '\\notfound_background.png')  # Tải hình ảnh nền không tìm thấy
found_background = pygame.image.load(os.getcwd() + '\\found_background.png')  # Tải hình ảnh nền tìm thấy

'''
HIỂN THỊ BẢN ĐỒ CHO TRÒ CHƠI
'''
def renderMap(board):
    width = len(board[0])
    height = len(board)
    indent = (640 - width * 32) / 2.0  # Tính khoảng cách lề
    for i in range(height):
        for j in range(width):
            screen.blit(space, (j * 32 + indent, i * 32 + 250))  # Hiển thị không gian
            if board[i][j] == '#':
                screen.blit(wall, (j * 32 + indent, i * 32 + 250))  # Hiển thị tường
            if board[i][j] == '$':
                screen.blit(box, (j * 32 + indent, i * 32 + 250))  # Hiển thị hộp
            if board[i][j] == '%':
                screen.blit(point, (j * 32 + indent, i * 32 + 250))  # Hiển thị điểm kiểm tra
            if board[i][j] == '@':
                screen.blit(player, (j * 32 + indent, i * 32 + 250))  # Hiển thị người chơi

			
'''
KHỞI TẠO CÁC BIẾN
'''
# Mức độ bản đồ
mapNumber = 0
# Thuật toán giải trò chơi
algorithm = "Euclidean Distance Heuristic"
# Trạng thái cảnh, bao gồm:
# init để chọn bản đồ và thuật toán
# loading để hiển thị cảnh "đang tải"
# executing để giải quyết vấn đề
# playing để hiển thị trò chơi
sceneState = "init"
loading = False

''' HÀM SOKOBAN '''
start_time = 0
end_time = 0
steps = 0
initial_memory = 0

def sokoban():
    running = True
    global sceneState
    global loading
    global algorithm
    global list_board
    global mapNumber
    stateLength = 0
    currentState = 0
    found = True

    while running:
        screen.blit(init_background, (0, 0))
        if sceneState == "init":
            initGame(maps[mapNumber])
        if sceneState == "executing":
            list_check_point = check_points[mapNumber]
            # Bắt đầu đếm thời gian
            start_time = time.time()
            if algorithm == "Euclidean Distance Heuristic":
                list_board = astar1.AStar_Search1(maps[mapNumber], list_check_point)
            elif algorithm == "Manhattan Distance Heuristic":
                list_board = astar.AStar_Search(maps[mapNumber], list_check_point)
            else:
                list_board = bfs.BFS_search(maps[mapNumber], list_check_point)
            # Dừng đếm thời gian
            end_time = time.time()
            if len(list_board) > 0:
                sceneState = "playing"
                stateLength = len(list_board[0])
                currentState = 0
                elapsed_time = end_time - start_time
                print(f"  Map: Level {mapNumber + 1} ")
                #  thời gian giải thuật
                print(f"  Thời gian: {elapsed_time} seconds")
                    
            else:
                sceneState = "end"
                found = False

        if sceneState == "loading":
            loadingGame()
            sceneState = "executing"

        if sceneState == "end":
            if found:
                foundGame(list_board[0][stateLength - 1])
            else:
                notfoundGame()

        if sceneState == "playing":
            clock.tick(2)
            renderMap(list_board[0][currentState])
            currentState = currentState + 1
            if currentState == stateLength:
                sceneState = "end"
                found = True
                steps = stateLength  # Đặ số bước là chiều dài trạng thái 

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RIGHT and sceneState == "init":
                    if mapNumber < len(maps) - 1:
                        mapNumber = mapNumber + 1
                if event.key == pygame.K_LEFT and sceneState == "init":
                    if mapNumber > 0:
                        mapNumber = mapNumber - 1
                if event.key == pygame.K_RETURN:
                    if sceneState == "init":
                        sceneState = "loading"
                    if sceneState == "end":
                        sceneState = "init"
                if event.key == pygame.K_SPACE and sceneState == "init":
                    if algorithm == "Euclidean Distance Heuristic":
                        algorithm = "Manhattan Distance Heuristic"
                    elif algorithm == "Manhattan Distance Heuristic":
                        algorithm = "BFS"
                    else:
                        algorithm = "Euclidean Distance Heuristic"

        pygame.display.flip()

    pygame.quit()

''' HIỂN THỊ MÀN HÌNH CHÍNH '''
# HIỂN THỊ MÀN HÌNHHÌNH BAN ĐẦU
def initGame(map):
    titleSize = pygame.font.Font('gameFont.ttf', 60)
    titleText = titleSize.render('Sokoban', True, WHITE)
    titleRect = titleText.get_rect(center=(320, 80))
    screen.blit(titleText, titleRect)

    desSize = pygame.font.Font('gameFont.ttf', 20)
    desText = desSize.render('Chọn Map:', True, WHITE)
    desRect = desText.get_rect(center=(320, 140))
    screen.blit(desText, desRect)

    desSize = pygame.font.Font('gameFont.ttf', 20)
    desText = desSize.render('Nhấn SPACE để đổi thuật toán', True, WHITE)
    desRect = desText.get_rect(center=(320, 550))
    screen.blit(desText, desRect)

    mapSize = pygame.font.Font('gameFont.ttf', 30)
    mapText = mapSize.render(" Map: " + str(mapNumber + 1) + " ", True, WHITE)
    mapRect = mapText.get_rect(center=(320, 200))
    screen.blit(mapText, mapRect)

    screen.blit(arrow_left, (240, 188))
    screen.blit(arrow_right, (376, 188))

    algorithmSize = pygame.font.Font('gameFont.ttf', 30)
    algorithmText = algorithmSize.render(str(algorithm), True, WHITE)
    algorithmRect = algorithmText.get_rect(center=(320, 600))
    screen.blit(algorithmText, algorithmRect)
    renderMap(map)

''' CẢNH TẢI '''
# HIỂN THỊ CẢNH TẢI
def loadingGame():
    screen.blit(loading_background, (0, 0))

    fontLoading_1 = pygame.font.Font('gameFont.ttf', 40)
    text_1 = fontLoading_1.render('ĐANG TẢI', True, WHITE)
    text_rect_1 = text_1.get_rect(center=(320, 60))
    screen.blit(text_1, text_rect_1)

    fontLoading_2 = pygame.font.Font('gameFont.ttf', 20)
    text_2 = fontLoading_2.render('Đang tìm lời giải............', True, WHITE)
    text_rect_2 = text_2.get_rect(center=(320, 100))
    screen.blit(text_2, text_rect_2)

def foundGame(map):
    screen.blit(found_background, (0, 0))

    font_1 = pygame.font.Font('gameFont.ttf', 30)
    text_1 = font_1.render('Yeah! Đã tìm thấy lời giải!!!', True, WHITE)
    text_rect_1 = text_1.get_rect(center=(320, 100))
    screen.blit(text_1, text_rect_1)

    font_2 = pygame.font.Font('gameFont.ttf', 20)
    text_2 = font_2.render('Nhấn ENTER để tiếp tục', True, WHITE)
    text_rect_2 = text_2.get_rect(center=(320, 600))
    screen.blit(text_2, text_rect_2)

    renderMap(map)

def notfoundGame():
    screen.blit(notfound_background, (0, 0))
    font_1 = pygame.font.Font('gameFont.ttf', 40)
    text_1 = font_1.render('Không thể tìm ra lời giải', True, WHITE)
    text_rect_1 = text_1.get_rect(center=(320, 100))
    screen.blit(text_1, text_rect_1)

    font_2 = pygame.font.Font('gameFont.ttf', 20)
    text_2 = font_2.render('Nhấn ENTER để tiếp tục', True, WHITE)
    text_rect_2 = text_2.get_rect(center=(320, 600))
    screen.blit(text_2, text_rect_2)

def main():
    sokoban()

if __name__ == "__main__":
    main()
