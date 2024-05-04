#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <stdio.h>
#include <math.h>
using namespace std;
int main()
{
    // ================== ĐỌC FILE ====================
    // CÚ PHÁP IFSTREAM : iftream <tên biến file >
    // BƯỚC 1: KHAI BÁO TÊN BIẾN ĐỂ ĐỌC FILE
    ifstream File;
    // BƯỚC 2: MỞ FILE CẦN ĐỌC DỮ LIỆU
    // OPEN <TÊN ĐƯỜNG DẪN ĐẾN FILE CẦN MỞ - ĐỂ ĐỌC - ĐÂY LÀ 1 CHUỖI
    //                         LƯU Ý
    //		   std::ios::out    (mở để ghi-nội dung cũ bị xóa khi ghi)
    //         std::ios::in     (mở để đọc)
    //         std::ios::app    (mở để chèn)
    //		   std::ios::ate    (Sau khi mở thì di chuyển về vị trí cuối file)
    //         std::ios::trunc	(Mở để ghi, tương tự mode out)
    //         std::ios::binary	(Mở file nhị phân)
    File.open("NUM.inp", std::ios::out);
    if (File.fail() == true)
    {
        cout << "\nFile cua ban khong ton tai ";
        return 0;
    }
    // BƯỚC 3: XỬ LÝ FILE - ĐỌC DỮ LIỆU TỪ FILE RA CHƯƠNG TRÌNH
    int x;
    int a[10];
    File >> x;
    for (int i = 0; i < x; i++)
    {
        File >> a[i];
    }
    cout << "\n so phan tu trong mang " << x << endl;
    for (int i = 0; i < x; i++)
    {
        cout << " gia tri a[" << i << "] =" << a[i] << endl;
    }
    // BƯỚC 4: ĐÓNG FILE LẠI SAU KHI XỬ LÝ
    File.close();
    return 0;
}
int chinhphuong(int n)
{
    return sqrt(n) == int(sqrt(n));
}
int nguyento(int n)
{
    if (n < 2)
        return 0;
    int k = sqrt(n);
    for (int i = 2; i <= k; i++)
        if (n % i == 0)
            return 0;
    return 1;
}
int nguyento2(int n)
{
    int count = 0;
    for (int i = 1; i <= sqrt(n); i++)
    {
        if (n % i == 0)
            ++count;
    }
    if (count == 2)
        return n;
}
int hoanchinh(int n)
{
    int s = 0;
    for (int i = 1; i <= n / 2; i++)
    {
        if (n % i == 0)
            s += i;
    }
    return s == n;
}