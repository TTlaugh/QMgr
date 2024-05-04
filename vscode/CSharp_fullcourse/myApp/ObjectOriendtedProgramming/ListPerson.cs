using System.Collections;

namespace myApp.ObjectOriendtedProgramming
{
    public class ListPerson
    {
        ArrayList listPerson = new ArrayList();
        public ListPerson()
        {
            ArrayList list = new ArrayList();
            listPerson = list;
        }
        public void AddPerson(Person person)
        {
            listPerson.Add(person);
        }

        public ArrayList GetListPerson()
        {
            return listPerson;
        }

        public void RemovePerson(Person person)
        {
            listPerson.Remove(person);
        }

        public void ClearListPerson()
        {
            listPerson.Clear();
        }

        public int Count()
        {
            return listPerson.Count;
        }

        public Person GetPerson(int index)
        {
            return (Person)listPerson[index];
        }

        public void SetPerson(int index, Person person)
        {
            listPerson[index] = person;
        }

        public void PrintListPerson()
        {
            foreach (Person person in listPerson)
            {
                Console.WriteLine(person.ToString());
            }
        }

    }
}