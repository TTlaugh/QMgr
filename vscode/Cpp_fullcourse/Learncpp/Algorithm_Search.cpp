#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;
void inputarray(int *a, int n);
void showarray(int *a, int n);
int linearsearch(const int array[], int length, const int &item);
int binarysearch(const int array[], int length, const int &item);
int main()
{
    int *a, n, count = 0, x;
    cout << " nhap so phan tu :";
    cin >> n;
    a = new int[n];

    return 0;
}
void inputarray(int *a, int n)
{
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
}
void showarray(int *a, int n)
{
    for (int i = 0; i < n; i++)
        cout << a[i] << "  ";
    cout << endl;
}
int linearsearch(const int array[], int length, const int &item)
{
    int loc;
    bool found = false;
    loc = 0;
    while (loc < length && !found)
    {
        if (array[loc] == item)
            found = true;
        else
            loc++;
    }
    if (found)
        return loc;
    else
        return -1;
} // end seqSearch
int binarysearch(const int array[], int length, const int &item)
{

    int first = 0; // 9 phan tu mid = 4 x=9  mid
    int last = length - 1;
    int mid;
    bool found = false;
    while (first <= last && !found)
    {
        mid = (first + last) / 2;
        if (array[mid] == item)
            found = true;
        else if (array[mid] > item)
            last = mid - 1;
        else // a[mid] < item
            first = mid + 1;
    }
    if (found)
        return mid;
    else
        return -1;
} // end binarySearch