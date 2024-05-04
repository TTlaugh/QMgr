// // See https://aka.ms/new-console-template for more information
// using System;
// using System.ComponentModel.DataAnnotations;
// namespace myApp
// {
//     class Program
//     {
//         static void Main(string[] args)
//         {
//             // Data_Type();
//             // Input_Data();
//             // MathCLass();
//             // RandomNumber();
//             // MethodString();
//             // Switches();
//             // double x = Multiply(2.1, 5);
//             // double y = Multiply(2.1, 5, 3);
//             // Console.WriteLine(x);
//             // Console.WriteLine(y);

//             // string[] subs = { "English", "Math", "Physics", "Chemistry", "Biology" };
//             // Console.WriteLine(AllSubjects(subs));

//             // double checkOut = Checkout(4, 5, 6, 7, 8);
//             // Console.WriteLine(checkOut);
//             // bool checkOut = false;
//             // while (!checkOut)
//             // {
//             //     double x, y;
//             //     try
//             //     {
//             //         Console.Write("Enter x: ");
//             //         x = Convert.ToDouble(Console.ReadLine());
//             //         Console.Write("Enter y: ");
//             //         y = Convert.ToDouble(Console.ReadLine());
//             //         double z = x / y;
//             //         Console.WriteLine("Result: " + z);
//             //         checkOut = true;
//             //     }
//             //     catch (FormatException e)
//             //     {
//             //         Console.WriteLine("Error: " + e.Message);
//             //         Console.WriteLine("Enter only number");
//             //     }
//             //     // catch (DivideByZeroException e)
//             //     // {
//             //     //     Console.WriteLine("Error: " + e.Message);
//             //     //     Console.WriteLine("Enter number y != 0");
//             //     // }
//             //     catch (Exception e)
//             //     {
//             //         Console.WriteLine("Error: " + e.Message);
//             //     }
//             //     Console.ReadKey();
//             // }
//             // Console.Write("Enter temprature: ");
//             // double temprature = Convert.ToDouble(Console.ReadLine());
//             // string massage;
//             // massage = temprature > 35 ? "Hot" : "Cold";
//             // string firstName = "Nguyen";
//             // string lastName = "Trong";
//             // string massage = $"Hello {firstName} {lastName}";
//             // Console.WriteLine(massage);

//             string[] fords = { "ford1", "ford2", "ford3" };
//             string[] chevys = { "chevy1", "chevy2", "chevy3" };
//             string[] porsches = { "porsche1", "porsche2", "porsche3" };

//             string[,] parkingLot = { { "ford1", "ford2", "ford3" }, { "chevy1", "chevy2", "chevy3" }, { "porsche1", "porsche2", "porsche3" }, { "ford1", "ford2", "ford3" }, { "ford1", "ford2", "ford3" } };
//             // parkingLot[0, 2] = "ford4";
//             // foreach (string car in parkingLot)
//             // {
//             //     Console.WriteLine(car);
//             // }
//             for (int i = 0; i < parkingLot.GetLength(0); i++)
//             {
//                 for (int j = 0; j < parkingLot.GetLength(1); j++)
//                 {
//                     Console.WriteLine(i + " " + j);
//                     Console.WriteLine(parkingLot[i, j]);

//                     Console.WriteLine();
//                 }
//                 Console.WriteLine();
//             }
//             // Console.WriteLine(parkingLot.GetLength(0));
//             // Console.WriteLine(parkingLot.GetLength(1));

//         }
//         static void Data_Type()
//         {
//             int myAge = 20;
//             double myHeight = 1.71;
//             string myName = "Nguyen Thanh Trong";
//             bool myAlive = false;
//             char symbol = '@';
//             string g = "*";
//             string h = "true";
//             const double pi = 3.14;

//             Console.WriteLine("My name is " + myName);
//             Console.WriteLine("My age is " + myAge);
//             Console.WriteLine("My height is " + myHeight);
//             Console.WriteLine("My alive is " + myAlive);
//             Console.WriteLine("My symbol is " + symbol);
//             Console.WriteLine("My pi is " + pi + "\n");

//             string Converted_String = Convert.ToString(myAge);
//             int Converted_toInt = Convert.ToInt32(myHeight);
//             double Converted_toDouble = Convert.ToDouble(myAge);
//             char Converted_toChar = Convert.ToChar(g);
//             bool Converted_toBoolean = Convert.ToBoolean(h);

//             Console.WriteLine(Converted_toInt);
//             Console.WriteLine(Converted_toInt.GetType());

//             Console.WriteLine(Converted_toDouble);
//             Console.WriteLine(Converted_toDouble.GetType());

//             Console.WriteLine(Converted_String);
//             Console.WriteLine(Converted_String.GetType());

//             Console.WriteLine(Converted_toChar);
//             Console.WriteLine(Converted_toChar.GetType());

//             Console.WriteLine(Converted_toBoolean);
//             Console.WriteLine(Converted_toBoolean.GetType());

//         }
//         // static void Input_Data()
//         // {
//         //     Console.Write("Enter your name: ");
//         //     string name = Convert.ToString(Console.ReadLine());

//         //     Console.Write("Enter your age: ");
//         //     int age = Convert.ToInt32(Console.ReadLine());


//         //     Console.WriteLine("Hello " + name + "!");
//         //     Console.WriteLine(name + " is " + age + " years old.");
//         // }
//         static void MathCLass()
//         {
//             double x = 2.1;
//             double y = 5;

//             double a = Math.Pow(x, y);
//             double b = Math.Sqrt(y);
//             double c = Math.Round(x);
//             double d = Math.Ceiling(x);
//             double e = Math.Floor(x);
//             double g = Math.Max(x, y);
//             double h = Math.Min(x, y);

//             Console.WriteLine(d);
//         }
//         static void RandomNumber()
//         {
//             Random random = new Random();
//             // int num = random.Next(1, 100);
//             double num = random.NextDouble() * 100;
//             Console.WriteLine(num);

//         }
//         static void MethodString()
//         {
//             String fullname = "Nguyen Thanh Trong";
//             String myBirthday = "1/21/2004";

//             String name_upper = fullname.ToUpper();
//             String name_lower = fullname.ToLower();
//             String birthDay = myBirthday.Replace("/", "-");
//             String username = fullname.Insert(0, "@");
//             String lenghtName = fullname.Length.ToString();
//             String cutName = fullname.Substring(0, 5);//cut from 0 -> (<5)

//             Console.WriteLine(name_upper);
//             Console.WriteLine(name_lower);
//             Console.WriteLine(birthDay);
//             Console.WriteLine(username);
//             Console.WriteLine(lenghtName);
//             Console.WriteLine(cutName);
//         }
//         // static void Switches()
//         // {

//         //     Console.WriteLine("What day is it today");
//         //     String day = Convert.ToString(Console.ReadLine()); //Console.ReadLine();

//         //     switch (day)
//         //     {
//         //         case "Monday":
//         //             Console.WriteLine("Today is Monday");
//         //             break;
//         //         case "Tuesday":
//         //             Console.WriteLine("Today is Tuesday");
//         //             break;
//         //         case "Wednesday":
//         //             Console.WriteLine("Today is Wednesday");
//         //             break;
//         //         case "Thursday":
//         //             Console.WriteLine("Today is Thursday");
//         //             break;
//         //         case "Friday":
//         //             Console.WriteLine("Today is Friday");
//         //             break;
//         //         case "Saturday":
//         //             Console.WriteLine("Today is Saturday");
//         //             break;
//         //         case "Sunday":
//         //             Console.WriteLine("Today is Sunday");
//         //             break;
//         //         default:
//         //             Console.WriteLine(day + " is not a day of the week");
//         //             break;
//         //     }

//         // }
//         static double Multiply(double x, double y)
//         {
//             return x * y;
//         }
//         // overloading
//         static double Multiply(double x, double y, double z)
//         {
//             return x * y * z;
//         }
//         static string AllSubjects(params string[] subjects)
//         {
//             System.Text.StringBuilder builder = new System.Text.StringBuilder();
//             for (int i = 0; i < subjects.Length; i++)
//             {
//                 builder.Append(subjects[i]);
//                 builder.Append(" ");
//             }
//             return builder.ToString();
//         }
//         static double Checkout(params double[] prices)
//         {
//             double total = 0;
//             for (int i = 0; i < prices.Length; i++)
//             {
//                 total += prices[i];
//             }
//             return total;
//         }
//     }
// }