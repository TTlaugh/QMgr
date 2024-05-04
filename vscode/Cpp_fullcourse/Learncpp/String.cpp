#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s;
    cout << "Nhap chuoi s: ";
    getline(cin, s);

    // Dem so ky tu chu hoa va chu thuong
    int countUpper = 0, countLower = 0, number = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] >= 'A' && s[i] <= 'Z')
        {
            countUpper++;
        }
        else if (s[i] >= 'a' && s[i] <= 'z')
        {
            countLower++;
        }
        else if (s[i] >= '0' && s[i] <= '9')
        {
            number++;
        }
    }
    cout << "So ky tu chu hoa: " << countUpper << endl;
    cout << "So ky tu chu thuong: " << countLower << endl;
    cout << " So trong chuoi: " << number << endl;
    // In ma ASCII cua moi ky tu trong chuoi
    cout << "Ma ASCII cua moi ky tu trong chuoi:\n";
    for (int i = 0; i < s.length(); i++)
    {
        cout << s[i] << ": " << int(s[i]) << endl;
    }

    // Chuyen ky tu dau moi tu thanh chu hoa, cac ky tu khac thanh chu thuong
    for (int i = 0; i < s.length(); i++)
    {
        if (i == 0 || s[i - 1] == ' ')
        {
            s[i] = toupper(s[i]);
        }
        else
        {
            s[i] = tolower(s[i]);
        }
    }
    cout << "Chuoi sau khi chuyen doi: " << s << endl;

    // Dem so luong tu trong chuoi
    int countWord = 1; // Khong co khoang trang dau tien, nen so tu hien tai la 1
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == ' ')
        {
            countWord++;
        }
    }
    cout << "So tu trong chuoi: " << countWord << endl;

    // Dem so ky tu cua moi tu trong chuoi
    int countChar = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == ' ')
        {
            cout << "So ky tu cua moi tu trong chuoi: " << countChar << endl;
            countChar = 0;
        }
        else
        {
            countChar++;
        }
    }

    // Tim tu dau tien ben trai va ben phai
    int left = 0, right = s.length() - 1;
    while (s[left] == ' ')
    {
        left++; // Bo qua khoang trang dau tien
    }
    while (s[right] == ' ')
    {
        right--; // Bo qua khoang trang cuoi cung
    }
    while (s[left] != ' ' && left > 0)
    {
        left--;
    }
    while (s[right] != ' ' && right < s.length() - 1)
    {
        right++;
    }
    if (left == 0)
    {
        cout << "Tu dau tien ben trai khong ton tai.\n";
    }
    else
    {
        left++;
        cout << "Tu dau tien ben trai: ";
        for (int i = left; i < right; i++)
        {
            cout << s[i];
        }
        cout << endl;
    }
    if (right == s.length() - 1)
    {
        cout << "Tu dau tien ben phai khong ton tai.\n";
    }
    else
    {
        cout << "Tu dau tien ben phai: ";
        for (int i = right + 1; i < s.length(); i++)
        {
            cout << s[i];
        }
        cout << endl;
    }

    return 0;
}