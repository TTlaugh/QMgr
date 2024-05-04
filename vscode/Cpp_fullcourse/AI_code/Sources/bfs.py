import support_function as spf
import time
import psutil
import os

TIME_OUT = 1800

'''
//========================//
//           BFS          //
//        ALGORITHM       //
//     IMPLEMENTATION     //
//========================//
'''

def BFS_search(board, list_check_point):
    start_time = time.time()
    
    ''' GIẢI PHÁP TÌM KIẾM BFS '''
    
    ''' NẾU BẮT ĐẦU LÀ TRẠNG THÁI KẾT THÚC HOẶC KHÔNG CÓ ĐIỂM KIỂM TRA '''
    if spf.check_win(board, list_check_point):
        print("Found Win")
        return [board]
    
    ''' KHỞI TẠO TRẠNG THÁI BẮT ĐẦU '''
    start_state = spf.state(board, None, list_check_point)
    
    ''' KHỞI TẠO 2 DANH SÁCH ĐƯỢC SỬ DỤNG CHO TÌM KIẾM BFS '''
    list_state = [start_state]
    list_visit = [start_state]
    
    ''' LẶP QUA DANH SÁCH ĐÃ DUỢC DUYỆT '''
    while len(list_visit) != 0:
        
        ''' LẤY TRẠNG THÁI HIỆN TẠI ĐỂ TÌM KIẾM '''
        now_state = list_visit.pop(0)
        
        ''' LẤY VỊ TRÍ HIỆN TẠI CỦA NGƯỜI CHƠI '''
        cur_pos = spf.find_position_player(now_state.board)
        
        ''' LẤY DANH SÁCH VỊ TRÍ MÀ NGƯỜI CHƠI CÓ THỂ DI CHUYỂN ĐẾN '''
        list_can_move = spf.get_next_pos(now_state.board, cur_pos)
        
        ''' TẠO TRẠNG THÁI MỚI TỪ DANH SÁCH CÓ THỂ DI CHUYỂN '''
        for next_pos in list_can_move:
            
            ''' TẠO BẢNG MỚI '''
            new_board = spf.move(now_state.board, next_pos, cur_pos, list_check_point)
            
            ''' NẾU BẢNG NÀY CHƯA TỒN TẠI TRONG DANH SÁCH TRẠNG THÁI THÌ BỎ QUA TRẠNG THÁI NÀY '''
            if spf.is_board_exist(new_board, list_state):
                continue
            
            ''' NẾU MỘT HOẶC NHIỀU HỘP BỊ KẸT TRONG GÓC THÌ BỎ QUA TRẠNG THÁI NÀY '''
            if spf.is_board_can_not_win(new_board, list_check_point):
                continue
            
            ''' NẾU TẤT CẢ HỘP BỊ KẸT THÌ BỎ QUA TRẠNG THÁI NÀY '''
            if spf.is_all_boxes_stuck(new_board, list_check_point):
                continue
            
            ''' TẠO TRẠNG THÁI MỚI '''
            new_state = spf.state(new_board, now_state, list_check_point)
            
            ''' KIỂM TRA XEM TRẠNG THÁI MỚI CÓ PHẢI LÀ TRẠNG THÁI KẾT THÚC KHÔNG '''
            if spf.check_win(new_board, list_check_point):
                print("\nBreadth First Search")
                print("Found Win")
                print("  Số trạng thái đã duyệt : {} ".format(len(list_state)))
                process = psutil.Process(os.getpid())
                memory_usage = process.memory_info().rss / (1024**2)
                print(f"  Bộ nhớ: {memory_usage} Mb")
                return (new_state.get_line(), len(list_state))
            
            ''' THÊM TRẠNG THÁI MỚI VÀO DANH SÁCH ĐÃ DUYỆT VÀ DANH SÁCH TRẠNG THÁI CẦN DUYỆT '''
            list_state.append(new_state)
            list_visit.append(new_state)
            process = psutil.Process(os.getpid())
            memory_usage = process.memory_info().rss / (1024**2)
            
            ''' TÍNH THỜI GIAN TIMEOUT '''
            end_time = time.time()
            if end_time - start_time > TIME_OUT:
                return []
        
        end_time = time.time()
        if end_time - start_time > TIME_OUT:
            return []
    
    ''' KHÔNG TÌM THẤY GIẢI PHÁP '''
    print("Not Found")
    return []
