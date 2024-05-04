#include <iostream>
#include <fstream>

int main()
{
    // Mở file để đọc
    std::string path = "C:\\vscode\\Cpp_fullcourse\\doc\\data.txt";
    std::ifstream inputFile(path);

    // Kiểm tra xem file có mở thành công hay không
    if (inputFile.is_open())
    {
        std::string line;

        // Đọc từng dòng trong file
        while (std::getline(inputFile, line))
        {
            // Xử lý dữ liệu trong dòng
            std::cout << line << std::endl;
        }

        // Đóng file
        inputFile.close();
    }
    else
    {
        std::cerr << "Lỗi khi mở file!" << std::endl;
    }
    return 0;
}
