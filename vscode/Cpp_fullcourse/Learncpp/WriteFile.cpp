#include <iostream>
#include <fstream>
using namespace std;
int main()
{
    // Mở file để ghi
    string path = "C:\\vscode\\Cpp_fullcourse\\doc\\data.txt";
    ofstream outputFile(path, ios::app);

    // Kiểm tra xem file có mở thành công hay không
    if (outputFile.is_open())
    {
        // Ghi dữ liệu vào file
        outputFile << 2 << endl;
        outputFile << "jejej" << endl;

        // Đóng file
        outputFile.close();
    }
    else
    {
        cerr << "Lỗi khi mở file!" << endl;
    }

    return 0;
}
