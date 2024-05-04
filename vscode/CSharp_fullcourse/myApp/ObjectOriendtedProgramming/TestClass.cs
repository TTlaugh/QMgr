using System.IO;
using System;
using System.Text.Json;
namespace myApp.ObjectOriendtedProgramming
{
    class TestClass
    {
        static void Main(string[] args)
        {
            // Vehicle vehicle = new Vehicle(100, 4);
            // Vehicle car = new Car("Ford", 80, 4);
            // Vehicle boat = new Boat("Boat", 70, 4);
            // Vehicle car2 = new Car("Ford", 80, 4);

            // car2.Drive();
            // vehicle.Drive();
            // car.Drive();
            // boat.Drive();

            // // Write file
            // string path = "data.txt";
            // //write file
            // FileWrite(path, vehicle.ToString());
            // // Read file all content
            // FileReadAll(path);
            // // Read file line by line
            // FileReadLine(path);

            // Student student = new Student(1);
            // Console.WriteLine(student.ToString());

            Student student = new Student { id = 1, name = "Thanh Trong" };

            Cat cat = new Cat { name = "Lylyana", age = 1 };

            // OBJ => JSON
            string jsonCat = JsonSerializer.Serialize(cat);

            Console.WriteLine(jsonCat);

            // JSON => OBJ
            Cat cat2 = JsonSerializer.Deserialize<Cat>(jsonCat);

            Console.WriteLine(cat2);

        }
        public class Cat
        {
            public string name;
            public int age;

            public string Name { get { return name; } set { name = value; } }
            public int Age { get { return age; } set { age = value; } }
            // public Cat(string name, int age)
            // {
            //     this.name = name;
            //     this.age = age;
            // }

            public override string ToString()
            {
                return name + " " + age;
            }
        }
        struct Student
        {
            public int id;
            public string name;
            public Student(int id, string name = "Thanh Trong")
            {
                this.id = id;
                this.name = name;
                Console.WriteLine(id + " " + name);
            }

            public override string ToString()
            {
                return id + " " + name;
            }
        }
        public static bool FileWrite(String path, String content)
        {
            try
            {
                File.WriteAllText(path, content);
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
                return false;
            }
        }

        public static bool FileReadAll(String path)
        {
            try
            {
                string content = File.ReadAllText(path);
                Console.WriteLine(content);
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
                return false;
            }
        }

        public static bool FileReadLine(String path)
        {
            try
            {
                using (StreamReader reader = new StreamReader(path))
                {
                    string line;
                    while ((line = reader.ReadLine()) != null)
                    {
                        Console.WriteLine(line);
                    }
                }
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
                return false;
            }
        }


    }
    enum dataType
    {
        Int,
        String,
        Double,
        Bool,
        Char,
        Float,
        Long,
        Short
    }
    enum Weekday
    {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }
    interface IActivities
    {
        public void operation();
        public void camera();
        public void music();
        public void video();
        public void internet();
        public void games();

    }
    // Abstraction
    abstract class smartPhone : IActivities
    {
        public abstract void Brand();

        public void camera()
        {
            throw new NotImplementedException();
        }

        public void games()
        {
            throw new NotImplementedException();
        }

        public void internet()
        {
            throw new NotImplementedException();
        }

        public abstract void Model();

        public void music()
        {
            throw new NotImplementedException();
        }


        public abstract void OperatingSystem();

        public void operation()
        {
            throw new NotImplementedException();
        }

        public void video()
        {
            throw new NotImplementedException();
        }

    }
    class Android : smartPhone
    {
        public override void Brand()
        {
            Console.WriteLine("Brand is Samsung");
            throw new NotImplementedException();
        }

        public override void Model()
        {
            Console.WriteLine("Model is S20");
            throw new NotImplementedException();
        }

        public override void OperatingSystem()
        {
            Console.WriteLine("Operating System is Android");
            throw new NotImplementedException();
        }
    }
    class IPhone : smartPhone
    {
        public override void Brand()
        {
            Console.WriteLine("Brand is Apple");
            throw new NotImplementedException();
        }

        public override void Model()
        {
            Console.WriteLine("Model is 15 Pro Max");
            throw new NotImplementedException();
        }

        public override void OperatingSystem()
        {
            Console.WriteLine("Operating System is IOS");
            throw new NotImplementedException();
        }
    }

    // Inheritance 
    class Vehicle
    {
        private int maxSpeed;
        private int wheel;
        public virtual int _maxSpeed { get { return maxSpeed; } set { maxSpeed = value; } }
        public virtual int _wheel { get { return wheel; } set { wheel = value; } }
        public Vehicle(int maxSpeedVehicle, int wheelVehicle)
        {
            this.maxSpeed = maxSpeedVehicle;
            this.wheel = wheelVehicle;
        }
        public virtual void Drive()
        {
            Console.WriteLine(maxSpeed + " " + wheel);
        }
        public override string ToString()
        {
            return _maxSpeed + " " + _wheel;
        }
    }
    class Car : Vehicle
    {
        private string carName;
        public string _carName { get { return carName; } set { carName = value; } }
        public Car(string carName, int maxSpeedCar, int wheelCar) : base(maxSpeedCar, wheelCar)
        {
            this.carName = carName;
        }
        public override int _maxSpeed { get => base._maxSpeed; set => base._maxSpeed = value; }
        public override int _wheel { get => base._wheel; set => base._wheel = value; }
        public override void Drive()
        {
            Console.WriteLine(base._maxSpeed + " " + base._wheel);
        }

    }
    sealed class Boat : Vehicle
    {
        private string boatName;
        public string _boatName { get { return boatName; } set { boatName = value; } }
        public Boat(string boatName, int maxSpeedBoat, int wheelBoat)
         : base(maxSpeedBoat, wheelBoat)
        {
            this.boatName = boatName;
        }
        public override int _maxSpeed { get => base._maxSpeed; set => base._maxSpeed = value; }
        public override int _wheel { get => base._wheel; set => base._wheel = value; }
        public override void Drive()
        {
            Console.WriteLine(base._maxSpeed + " " + base._wheel);
        }
    }
}