#include <iostream>
#include <math.h>
using namespace std;
void nhap(int *a, int n);
void xuat(int *a, int n);
void caua(int *a, int &n);
void caub(int *a, int &n);
void cauc(int *a, int &n);
int songto(int n);
void max(int *a, int n);
int main()
{

    int n;
    cout << " so phan tu: ";
    cin >> n;
    int *a = new int[n];
    cout << endl;
    nhap(a, n);
    xuat(a, n);
    max(a, n);
    return 0;
}
void nhap(int *a, int n)
{
    for (int i = 0; i < n; i++)
        cin >> a[i];
}
void xuat(int *a, int n)
{
    for (int i = 0; i < n; i++)
        cout << a[i] << "  ";
    cout << endl;
}
void caua(int *a, int &n)
{
    int k;
    cout << " vi tri can xoa: ";
    cin >> k;
    cout << endl;
    for (int i = k; i < n - 1; i++)
        a[i] = a[i + 1];
    n--;
}
void caub(int *a, int &n)
{
    int k, x;
    cout << " vi tri can chen : ";
    cin >> k;
    cout << endl;
    cout << " gia tri can chen: ";
    cin >> x;
    cout << endl;
    for (int i = n; i > k; i--)
    {
        a[i + 1] = a[i];
    }
    a[k] = x;
    n++;
}
void xoa(int *a, int &n, int k)
{
    for (int i = k; i < n - 1; i++)
        a[i] = a[i + 1];
    n--;
}
void cauc(int *a, int &n)
{
    for (int i = 0; i < n; i++)
    {
        if (songto(a[i]))
            xoa(a, n, a[i]);
    }
}
int songto(int n)
{
    if (n < 2)
        return 0;
    int k = sqrt(n);
    for (int i = 2; i <= k; i++)
        if (n % i == 0)
            return 0;
    return 1;
}
int hoanchinh(int n)
{
    int s = 0;
    for (int i = 0; i < n / 2; i++)
    {
        if (n % i == 0)
            s += i;
    }
    return s = n;
}
void max(int *a, int n)
{
    int max = INT_MIN;
    for (int i = 0; i < n; i++)
    {
        if (max < *(a + i))
            max = *(a + i);
    }
    cout << max << endl;
}