using System.Reflection.Metadata;
using System.Runtime.ConstrainedExecution;

namespace myApp.ObjectOriendtedProgramming
{
    public class Person
    {
        private string _id;
        private string _name;
        private string _email;
        public static long MAX_LENGTH = 8000000000;

        public string Id
        {
            get { return _id; }
        }
        public string Name
        {
            get { return _name; }
        }
        public string Email
        {
            get { return _email; }
        }


        public void SetId(string id) { _id = id; }
        public void SetName(string name) { _name = name; }
        public void SetEmail(string email) { _email = email; }
        public Person(string id, string name, string email)
        {
            _id = id;
            _name = name;
            _email = email;
            MAX_LENGTH++;
        }
        public Person()
        {
            _id = "";
            _name = "";
            _email = "";
            MAX_LENGTH++;
        }
        // public string ToString()
        // {
        //     return _id + " " + _name + " " + _email;
        // }
    }
}