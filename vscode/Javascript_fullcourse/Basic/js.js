// One step 
// console.log("I like progamming!");
// console.log("It's really good");
// window.alert("i like programming")

// TWO STEP 
// 1.Declaration(var,let, const)
// 2.Assinment (= assignment operator)

// let firstName= "trong";
// let student= true;
// let age=21;
// age=age+1 ;

// console.log("You are",age,"years old");
// console.log("hello", firstName);
// console.log("Enrolled",student);

// document.getElementById("p1").innerHTML="Hello "+ firstName;
// document.getElementById("p2").innerHTML="you are "+ age+"years old";
// document.getElementById("p3").innerHTML="Enrolled "+ student;


// let students= 32;
// students=students+1;
// students=students-1;
// students=students/1;
// students=students*1;

// students+=1;
// students-=1;
// students/=1;
// students*=1;

// console.log(students);

// operatoe precedence
// 1.Parenthesis (); 
// 2.Exponential ** 
// 3.Multiplication & divison
// 4.Additon & subjact
// let result=1+2*(3+4); 
// console.log(result);

//  How to accept  user input 
// EASY WAY with a window promt

// let username=window.prompt("What your's name? ");
// console.log(username);

// // DIFFICULT WAY HTML textbox 
// let username;
// document.getElementById("myButton").onclick = function(){
//     username = document.getElementById("myText").value;
//     console.log(username);
//     document.getElementById("myLabel").innerHTML="Hello"+username;
// }

// Type Conversion = change the datatype of a value to another 
// (strings,numbers,booleans)

// let age= window.prompt("How old are you?");
// console.log(typeof age);
// age=Number(age);
// console.log(typeof age);
// age+=1;
// console.log("Your age is ",age);

// let x; 
// let y;
// let z; 

// x=Number("3.14");
// y=String(3.14);
// z=Boolean("pizza");

// console.log(x,typeof x)
// console.log(y,typeof y)
// console.log(z,typeof z)

// const =a variable that can't be changed
// const Pi=3.14159;
// let radius ;
// let circumfence;
// radius=window.prompt("Enter the radius of a circle");
// radius=Number(radius);
// // let Pi=420.69;
// circumference=2*Pi*radius;
// console.log("The circumference",circumference);

// MATH 
// let x;
// let y =9;
// let z= 5;
// let maximum;
// let minimum; 

// maximum=Math.max(x,y,z);
// minimum=Math.min(x,y,z);

// x=Math.PI;

// x= Math.round(x); 
// x= Math.floor(x);
// x=Math.ceil(x)
// x= Math.pow(x,2);
// x=Math.sqrt(x);
// x=Math.abs(x);

// console.log(x);
// console.log(maximum);
// console.log(minimum);

// let a,b,c; 
// a=window.prompt("Enter side A");
// a=Number(a);

// b=window.prompt("Enter side B");
// b=Number(b);

// c=Math.pow(a,2)+Math.pow(b,2);
// c=Math.sqrt(c);
// console.log(c);

// document.getElementById("submitButton").onclick=function(){
//    a=document.getElementById("aTextbox").value;
//     a=Number(a);
    
//     b=document.getElementById("bTextbox").value;
//     b=Number(b);
    
    
//     c=Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
//     document.getElementById("clabel").innerHTML="Side c: "+c;
// }

// let count=0;
// document.getElementById("decreaseBtn").onclick=function(){
//     count--;
//     document.getElementById("countLabel").innerHTML=count;
// }

// document.getElementById("resetBtn").onclick=function(){
//     count=0;
//     document.getElementById("countLabel").innerHTML=count;
// }

// document.getElementById("insreaseBtn").onclick=function(){
//     count++;
//     document.getElementById("countLabel").innerHTML=count;
// }

// let x;
// let y;
// let z;
// console.log(x);
// console.log(y);
// console.log(z);

// document.getElementById("rollBtn").onclick=function(){
//     let x=Math.floor(Math.random()*6)+1;
//     let y=Math.floor(Math.random()*6)+1;
//     let z=Math.floor(Math.random()*6)+1;
//     document.getElementById("xLabel").innerHTML=x
//     document.getElementById("yLabel").innerHTML=y
//     document.getElementById("zLabel").innerHTML=z
// }

// String
// let username=" Nguyen Trong ";
// let numberPhone="123-456-7890";
// console.log(username.length);
// console.log(username.charAt(0));
// console.log(username.indexOf("g"));
// console.log(username.lastIndexOf("g"));
// console.log(username.trim());

// numberPhone=numberPhone.replaceAll("-","");

// console.log(numberPhone);


// slice() extracts a section of a string 
// and return it as a new string 
// without modifying the original string 

// let firstName="Nguyen";
// let lastName="Trong";
// let fullName="Nguyen Thanh Trong";

// firstName=fullName.slice(0,fullName.indexOf(" "));
// console.log(firstName);
// lastName=fullName.slice(fullName.lastIndexOf(" "));
// console.log(lastName);
// console.log(firstName.slice(0,4))
// console.log(fullName.slice(4));

// // Method 
// let username="trong";
// let letter=username.charAt(0).toUpperCase().trim();
// console.log(letter);

// document.getElementById("myButton").onclick=function(){
//    const myCheckBox =document.getElementById("myCheckBox");
//    const visaBtn=document.getElementById("visaBtn");
//    const mastercardBtn=document.getElementById("mastercardBtn");
//    const paypalBtn=document.getElementById("paypalBtn");

//     if(myCheckBox.checked)
//     {
//         console.log("You are subscribed");
//     }
//     else{
//         console.log("You are NOT subscribed");
//     }
//     if(visaBtn.checked){
//         console.log("You are paying with a Visa");
//         document.getElementById("result").innerHTML="You are paying with a Visa";
//     }
//     else if(mastercardBtn.checked){
//         console.log("You are paying with a Master Card");
//         document.getElementById("result").innerHTML="You are paying with a Master Card";
//     }
//     else if(paypalBtn.checked){
//         console.log("You are paying with a PayPal");
//         document.getElementById("result").innerHTML="You are paying with a PayPal";
//     }
// }

// FUNCTION
// let area;
// let height;
// let width;

// width=window.prompt("Enter your width : ");
// height=window.prompt("Enter your height : ");

// area=getArea(width,height);

// console.log("The area is", area);
// function getArea(){
//     let result=height*width;
//     return result;
// }
// getArea=function(){
//     let result=height*width;
//     return result;
// }


// happyBirthday();
// function happyBirthday()
// {
//     for(let i=0;i<5;i++)
//     {
//         console.log("Happy Birthday to you");
//     }
// }

// condition ? exprIfTrue : exprIfFalse;

// let adult = checkAge(12);

// console.log(adult);

// function checkAge(age)
// {
//     return age >=18 ? true : false;
// }

// checkWinner(true);
// function checkWinner(win){

//     win ? console.log("YOU WIN!") : console.log("YOU LOSE!")
// }

// let userName="Trong";
// let items=3 ;
// let total=75;

// console.log(`Hello ${userName}`);
// console.log(`You have  ${items} items in your card`);
// console.log(`Your total is $${total}`);

// let text=`Hello ${userName}
// you have ${items} in your card
// your total is ${total}$
// `;
// console.log(text);
// document.getElementById("myLabel").innerHTML=text;

//  toLocaleString(undifined,{style:"vd",vd:""})
// let myNum =1;
// myNum=myNum.toLocaleString("en-US") // US English
// myNum=myNum.toLocaleString("hi-IN") // Hindi
// myNum=myNum.toLocaleString("de-De") // standard German

//  CURRENCY
// myNum=myNum.toLocaleString("en-US",{style: "currency",currency:"USD"})
// myNum=myNum.toLocaleString("hi-IN",{style: "currency",currency:"INR"})
// myNum=myNum.toLocaleString("de-DE",{style: "currency",currency:"EUR "})

// PERCENT
// myNum=myNum.toLocaleString(undefined,{style:"percent" })

// CELSIUS
// myNum=myNum.toLocaleString(undefined,{style:"unit",unit:"celsius" })

// console.log(myNum);


// MINI GAME
// const answer= Math.floor(Math.random()*10+1);
// let guesses=0;
// document.getElementById("submitButton").onclick=function(){
//     let guess =  document.getElementById("guessField").value
//     guesses+=1;

//     if(guess==answer){
//         alert(`${answer} is the #. It took you ${guesses} guesses `)
//     }
//     else if(guess < answer)
//     {
//         alert("Too small!");
//     }
//     else
//     {
//         alert("Too large!");
//     }
// }

// DOI NHIET DO 
// document.getElementById("submitButton").onclick=function(){
//     let temp; 
//     if(document.getElementById("cButton").checked){
//         temp=document.getElementById("textBox").value;
//         temp=Number(temp);
//         temp=toCelsius(temp);
//         document.getElementById("tempLabel").innerHTML=temp+"*C";
//     }
//     else if(document.getElementById("fButton").checked)
//     {
//         temp=document.getElementById("textBox").value;
//         temp=Number(temp);
//         temp=toFahrenheit(temp);
//         document.getElementById("tempLabel").innerHTML=temp+"*F";
//     }
//     else{
//         document.getElementById("tempLabel").innerHTML="select a unit";
//     }
// }

// let temp=32;
//  temp = toCelsius(temp);
// temp = toFahrenheit(temp);
// console.log(temp);
// function toCelsius(temp)
// {
//     return (temp-32)*(5/9);
// }
// function toFahrenheit(temp){
// return (temp*9/5)+32;
// }

// ARRAY 1D
// let fruits=["apple","orange","banana"];

// // fruits[1]="coconut"; 
// fruits.push("lime");    // add an element
// fruits.pop();    // remove last element
// fruits.unshift("mango")       // add element to beginning 
// fruits.shift();  // remove element from beginning 

// let length= fruits.length;
// let index=fruits.indexOf("kiwi");

// console.log(index)

// console.log(fruits);

// let prices=[5,10,15,20];
// for(let i=prices.length-1;i>=0;i--){
//     console.log(prices[i]);
// }

// for(let price of prices){
//     console.log(price);
// }
// let fruits =["banana","apple","orange","mango"];

// fruits=fruits.sort().reverse( );
// for(let fruit of fruits){
//     console.log(fruit);
// }

// ARRAY 2D 
// let fruits =["apples","oranges","bananas"];
// let vegetables =["carrots","onions","potatoes"];
// let meats=["eggs","chickens","fishs"];

// let groceryList=[fruits ,vegetables,meats];
// groceryList[0][0]="mangoes";
// for(let list of groceryList){
//     for(let food of list)
//     {
//         console.log(food);
//     }
// }

// // spread operator
// let number=[1,2,3,4,5,6,7];

// let maximum =Math.max(...number);
// console.log(maximum );

// let class1=["Spongebob","Patrick","Sandy"];
// let class2=["Squidward","Mr.Krabs","Plankton"];

// class1.push(...class2);

// console.log(...class1);

// let a=1,b=2,c=3,d=4,e=5;
// console.log(sum(a,b,c,d,e));

// function sum(x ,y, ...numbers){
//     let total=0;
//     for(let number of numbers){
//         total+=number;
//     }
//     return total;
// }


// sum(2,3,displayDOM)

// function sum(a,b,Callback){
//     let result =a+b;
//     Callback(result);
// }

// function displayconsole(output){
//     console.log(output);
// }

// function displayDOM(output){
//     document.getElementById("myLabel").innerHTML= output ;
// }

// let students =["spongebob","patrick","squidward"];
// students.forEach(captialize);
// students.forEach(print);

// function captialize(element,index,array){
//     array[index]= element[0].toUpperCase()+element.substring(1);
// }
// function print(element){
//     console.log(element);
// }

// ARRAY MAP : tao 1 mang moi 
// let numbers=[1,2,3,4,5];
// let squares =numbers.map(square);
// let cubes=numbers.map(cube);
// cubes.forEach(print);
// // squares.forEach(print);
// displayDOM(cubes);
// function square(element){
//     return Math.pow(element,2);
// }
// function cube(element){
//     return Math.pow(element,3);
// }
// function print(element){
//     console.log (element);
// }

// function displayDOM(show){
//     document.getElementById("myLabel").innerHTML = show;
// }

// ARRAY FILTER : tao mot mang moi voi dk kiem tra trong ham 
// let ages =[18,20 ,39, 26, 34,17];
// let adults=ages.filter(checkAge);
// adults.forEach(print);
// function checkAge(element){
//     return element >=18;
// }
// function print(element)
// {
//     console.log(element);
// }


// ARRAY REDUCE : giam mang xuong 1 gia tri duy nhat
// let prices=[5,10,15,20,25,30];
// let total = prices.reduce(checkOut);

// console.log(`The total is $${total}`);
// function checkOut(total,element){
//     return total +element;
// }
// function print(element)
// {
//     console.log(element);
// }

// let grades=[100,10,90,50,70,60];
// grades=grades.sort(ascendingSort);

// // grades.forEach(print);
// print(grades)
// function descendingSort(x,y)//sort giam dan 
// {
//     return y-x;
// }
// function ascendingSort(x,y)// sort tang dan 
// {
//     return x-y ;
// }
// function print(element){
//     console.log(element);
// }

// let count =0;
// function inscreseBtn(){
//     count++;
//     document.getElementById("myLabel").innerHTML=count;
// }
// function descreseBtn(){
//     count--;
//     document.getElementById("myLabel").innerHTML=count;
// }

// const greeting =(username)=>console.log(`${username}`);

// greeting()
// const percent =function(x,y){
//     return x/y*100;
// }

// console.log(`${percent(80,100)}%`)


// arrow function 

// grades.sort((x,y)=> y-x  );

// grades.forEach((element)=>console.log(element));


// Cards
// let cards=["A","2","3","4","5","6","7","8","9","10","J","Q","K"];

// shuffle(cards);

// console.log(cards);
// cards.forEach(card=>console.log(card))

// function shuffle(array){
//     let currentIndex=array.length;
//     while(currentIndex!=0)
//     {
//         let currentRandom=Math.floor(Math.random()*array.length);
//         currentIndex--; 

//         let temp=array[currentIndex];
//         array[currentIndex]=array[currentRandom];
//         array[currentRandom]=temp;
//     }
//     return array;
// }


// // nested fucntion 

// let userName="abc";
// let userInbox=0;

// login();

// function login(){
//    function displayUsername(){
//     console.log(`User name: ${userName}`);
//    }
//    function displayUserinbox(){
//     console.log(`User inbox: ${userInbox}`);
//    }
// }

// MAP 
// const store =new Map([
//    ["t-shirt",17],
//    ["sweater",20],
//    ["varsity",19],
//    ["polo",12]
// ])
// get: take value 
// set: add (key,value);
// has: check (key,value) ? true : false
// size: size data in map 

// let shoppingCart=0;
// shoppingCart+=store.get("t-shirt");
// shoppingCart+=store.get("polo");
// console.log(shoppingCart)

// store.set("jean",22);

// console.log(store.has("t-shirt"));

// console.log(store.size)

// store.forEach((value,key)=>console.log(`${key} $${value}`));

// object 
// properties thuoc tinh 
// methods : phuong thuc 

// const car={
//    classify:"Porsche",
//    color:"white",
//    year:2023,

//    drive:function ()
//    {
//    console.log("You drive the car");
//    },
//    brake:function(){
//    console.log("You step on the brake")
//    }
// }

// console.log(car.classify);
// console.log(car.color);
// console.log(car.year);
// car.brake();
// car.drive();

// const car2={
//    classify:"Audi",
//    color:"white",
//    year:2022,

//    drive:function ()
//    {
//    console.log(`You drive the ${this.classify}`);
//    },
//    brake:function(){
//    console.log("You step on the brake")
//    }
// }

// console.log(car2.classify);
// console.log(car2.color);
// console.log(car2.year);
// car2.drive();
// car2.brake();

// this = reference to a particular object 
//                       the object depends on the immediate context 
// this.name ="Fan Case CoolMoon";

// console.log(this.name);


// CLASS
// class player {
//    score =0;
//    pause(){
//       console.log("You paused the game ");
//    }
//    exit(){
//       console.log("You exited the game ");
//    }
// }

// const player1=new player()
// player1.score+=1;
// console.log(player1);
// player1.pause();
// player1.exit();

// CONSTRUCTOR 

// class Students{

//    constructor(name,age,gpa) {
//       this.name=name;
//       this.age=age;
//       this.gpa=gpa;
//    }
//    study(){
//       console.log(`${this.name} is studying`);
//    }
// }

// const person =new Students("Trong","19","2.0");
// console.log(person.name)
// person.study();

//    STATIC KEYWORD 
// class Car{
//    static numberOfcars=0;
//    constructor(model) {
//      this.model=model;
//      Car.numberOfcars+=1; 
//    }
//     startRace()
//    {
//       console.log("3...2...1....GOOOO!");
//    }
// }
// const car1 = new Car("Porsche");
// const car2 = new Car("Audi");
// const car3 = new Car("BMW");
// const car4 = new Car("Mes");

// // Car.startRace();
// // car1.startRace();
// console.log(Car.numberOfcars);

// inheritance : ke thua 

// class Animals{
//    alive =true;

//    eat(){
//       console.log(`${this.name} is eating`);
//    }
//    sleep(){
//       console.log(`${this.name} is sleeping`);
//    }
// }
// class Rabbit extends Animals{
//    name="rabbit";

//    run(){
//       console.log(`${this.name} is running`);
//    }
// }

// class Fish extends Animals{
//    name="fish";

//    swim(){
//       console.log(`${this.name} is swimming`);
//    }
// }
// class Hawk extends Animals{
//    name="hawk";
   
//    fly(){
//       console.log(`${this.name} is flying`);
//    }
// }

// const rabbit=new Rabbit();
// const fish=new Fish();
// const hawk= new Hawk();

// console.log(`Rabbit: ${rabbit.alive}`);

// class Animals{
//    constructor(name,age){
//       this.name=name;
//       this.age=age;
//    }
// }
// class Rabbit extends Animals{
//    constructor(name,age,runspeed){
//       super(name,age);
//       this.runspeed=runspeed;
//    }
// }

// class Fish extends Animals{
//    constructor(name,age,swimspeed){
//       super(name,age);
//       this.swimspeed=swimspeed;
//    }
// }

// class Hawk extends Animals{
//    constructor(name,age,flyspeed){
//       super(name,age);
//       this.flyspeed=flyspeed;
//    }
// }

// const rabbit=new Rabbit("rabbit","1",20);
// const fish=new Fish("Fish","1",40);
// const hawk=new Hawk("Hawk","2",90);

// console.log(rabbit.runspeed);


// GET

// class Car{
//    constructor(power)
//    {
//       this.Gas=100;
//       this.Power =power;
//    }
//    get power()
//    {
//       return `this power ${this.Power}HP`;
//    }
//    get gas()
//    {
//       return `this gas ${this.Gas}L (${this.Gas/50 *100}%)`; 
//    }
// }

// const car=new Car(468);
// console.log(car.Power);
// console.log(car.gas);

// class Car{
//    constructor(model,year,color)
//    {
      
//       this.model=model;
//       this.year=year;
//       this.color=color;
//    }
//    drive()
//    {
//       console.log(`You can drive ${this.model}`);
//    }
// }
// const car1 = new Car("1","2020","yellow");
// const car2 = new Car("2","2022","titan");
// const car3 = new Car("3","2020","gold");

// const cars=[car1,car2,car3];

// displayInf(car1);
// changeColor(car1,"pink");
// displayInf(car1);

// console.log(cars[0].model);
// console.log(cars[1].model);
// console.log(cars[2].model);

// cars[0].drive();
// cars[1].drive();
// cars[2].drive();
// function displayInf(car){
//    console.log(car.model)
//    console.log(car.year)
//    console.log(car.color);
//    car.drive();
// }
// function changeColor(car,color)
// {
//    car.color=color;
// }

// ANONYMOUS OBJECTS
// hearts :co
// diamond:ro
// clubes:chuon
// spades:bich

class Cards{
   constructor(value,suit) {
      this.value=value;
      this.suit=suit;
   }
}

// const car1=new Cards("A","HEARTS");
// const car2=new Cards("A","DIAMOND");
// const car3=new Cards("A","CLUBES");
// const car4=new Cards("A","SPADES");
// const car5=new Cards("2","HEARTS");
// const car6=new Cards("2","DIAMOND");
// const car7=new Cards("2","CLUBES");
// const car8=new Cards("2","SPADES");

// const cards=[car1,car2,car3,car4,car5,car6,car7,car8];

// console.log(`${car1.value}  ${car1.suit}`);
// console.log(`${car2.value}  ${car2.suit}`);
// console.log(`${car3.value}  ${car3.suit}`);

// const cards2=[ 
//       new Cards("A","HEARTS"),
//       new Cards("A","DIAMOND"),
//       new Cards("A","CLUBES"),
//       new Cards("A","SPADES"),
//       new Cards("2","HEARTS"),
//       new Cards("2","DIAMOND"),
//       new Cards("2","CLUBES"),
//       new Cards("2","SPADES")
//    ]
// console.log(cards2[0].value + " " + cards2[0].suit);
// cards2.forEach(card=> console.log(`${card.value} ${card.suit} `));

//ERROR 
// try{
//    let a =window.prompt("Enter a #");
//    a=Number(a);
//    if(isNaN(a))throw "That wasn't a number"
//    if(x=='') throw "That was empty"
//    console.log(`${a} is a number`);
// }
// catch(error){
//    console.log(error);
// }
// finally{
//    console.log("This always executes ");
// }

// setTimeout()
// let item ="cryptocurrency";
// let price =420.69;
// let timer1=setTimeout(firstMessage,3000,item,price);
// let timer2=setTimeout(secondMessage,6000);
// let timer3=setTimeout(thirdMessage,9000);
// function firstMessage(item,price)
// {
//    alert(`Buy this ${item} for $${price}!`);
// }
// function secondMessage()
// {
//    alert(`This is not a scam!`);
// }
// function thirdMessage(){
//    alert(`DO IT !!!`);
// }

// document.getElementById("myButton").onclick=function(){
//    clearTimeout(timer1);
//    clearTimeout(timer2);
//    clearTimeout(timer3);
//    alert(`DO IT !!!`);
// }

// setInterval()
// let count =0;
// let max=window.prompt("Count up to what #?");
// max=Number(max);

// const myTimer =setInterval(countUp,1000);

// function countUp(){
//    count+=1;
//    console.log(count);
//    if(count>=max){
//       clearInterval();
//    }
// }

// setTimeout(buy,1000);
// setInterval(buy,2000);

// function buy(){
//    console.log(`Thanks for buying`);
// }

// The Date object is used to work with dates &times 

// let date=new Date();
// let date=new Date(0);
// let date=new Date(2023,0,1,2,3,4,5);
// let date=new Date("October 1,2023 8:00:00 PM ")

// let year=date.getFullYear();// nam hien tai 
// let month=date.getMonth();// thang hien tai trong nam bat dau tu 0-11
// let dayOfMonth=date.getDate();// ngay hien tai trong thang 
// let dayOfWeek=date.getDay();// ngay thu may hien tai trong tuan bat dau tu 1-7
// let hours =date.getHours();
// let minutes =date.getMinutes()
// let second =date.getSeconds();
// let ms =date.getMilliseconds();

// date.setFullYear(2024);
// date.setMonth(11);
// date.setDate(12);


// date=date.toLocaleString();
// document.getElementById("myLabel").innerHTML=formatTime(date) ;

// console.log(date);
// document.getElementById("myLabel").innerHTML=year +" ";
// document.getElementById("myLabel").innerHTML+=dayOfMonth+" " ;
// document.getElementById("myLabel").innerHTML+=dayOfWeek+" ";
// document.getElementById("myLabel").innerHTML+=month+" ";
// document.getElementById("myLabel").innerHTML+=hours+" ";
// document.getElementById("myLabel").innerHTML+=minutes+" ";
// document.getElementById("myLabel").innerHTML+=second+" ";
// document.getElementById("myLabel").innerHTML+=ms+" ";
// function formatDate(date){
//    let year=date.getFullYear()
//    let month=date.getMonth();
//    month+=1;
//    let day=date.getDate();

//    return `${month}/${day}/${year}`;
// }

function formatTime(date){
   let hours=date.getHours();
   let minutes=date.getMinutes();
   let second=date.getSeconds();
   // let ms=date.getMillisecond();
   let amOrpm=hours >=12 ? "pm" : "am";

   hours =(hours%12) || 12;
   return `${hours}:${minutes}:${second} ${amOrpm}`
}

// CLOCK PROGRAM 

// const myLabel= document.getElementById("myLabel");

// update();
// setInterval(update,1000);

// function update(){
//    let date=new Date();
//    myLabel.innerHTML=formatTime(date);
   // function formatTime(date){
   //    let hours=date.getHours();
   //    let minutes=date.getMinutes();
   //    let second=date.getSeconds();
   //    // let ms=date.getMillisecond();
   //    let amOrpm=hours >=12 ? "pm" : "am";
   
   //    hours =(hours%12) || 12;
   //    hours=formatZeroes(hours);
   //    minutes=formatZeroes(minutes);
   //    seconds=formatZeroes(second);
      
   //    return `${hours}:${minutes}:${second} ${amOrpm}`
   // }
// s

// SYNCHRONUS : MA DONG BO 
// console.log("START")
// console.log("This is step is synchronus")
// console.log("END")
// ASYNCHRONUS : MA KHONG DONG BO
// console.log("START")
// setTimeout(()=>console.log("This is asynchronous"),5000)
// console.log("END")

// CONSOLE.TIME()
// start 
// console.time("response time");

// alert("CLICK THE OK BUTTON")
// setTimeout(()=>console.log("Hello"),5000)

//end
// console.timeEnd("response time")

//PROMISE 

// synchronous 
// const promise=new Promise((resolve,reject)=>{
//    let fileLoaded =false ;
//    if(fileLoaded)
//    {
//       resolve("File loaded");
//    }
//    else{
//       reject("File NOT loaded")
//    }
// });
// promise.then(value=>console.log(value))
// .catch(error=>console.log(error));

// const wait = time => new Promise( resolve=>{
//    setTimeout(resolve,time);
// })
// wait(3000).then(()=>console.log("Thanks for watching!"))

// ASYNCHRONOUS == makes a function return Promise

// WAY 
// async function loadFile()
// {
//    let fileLoaded=false;
//    if(fileLoaded)
//    {
//       return "File loaded";
//    }
//    else
//    throw "File NOT loaded"
// }
// loadFile().then(value=>console.log(value))
// .catch(error=>console.log(error));

// Different ways
// async function loadFile()
// {
//    let fileLoaded=false;
//    if(fileLoaded)
//    {
//       return Promise.resolve("File loaded");
//    }
//    else{
//       return Promise.reject("File NOT loaded");
//    }
// }
// loadFile().then(value=>console.log(value))
// .catch(error=>console.log(error));



// WAIT= MAKES AN ASYNC FUNCITON WAIT FOR A PROMISE

// async function loadFile(){
//    let loadedFile=false ;
//    if(loadedFile)
//    return "FILE LOADED";
//    else 
//    throw "FILE NOT LOADED"
// }
// async function startProcess(){
//    try{
//       let message= await loadFile();
//       console.log(message);
//    }
//    catch(error){
//       console.log(error);
//    }
// }
// startProcess();


// add diffirence source js in source main
// import{PI,getCircumfence,getArea}from "./math_util";
// console.log(PI);
// let circumfence=getCircumfence(10);

// console.log(circumfence);

// let area=getArea(10);

// console.log(area);

// import * as Math from "./mathutil.js"
// console.log(Math.PI);


// DOM = Document Object Model (API)

// console.log(document);
// console.dir(document);

// console.log(document.title);
// console.log(document.URL)

// document.title=" Title goes here ";
// document.location="http://www.google.com";

// document.body.style.backgroundColor="skyblue";
// document.getElementById("myDiv").innerHTML="Hello"

// let element =document.getElementById("myTitle");
// element.style.backgroundColor="lightGreen";

// let fruits = document.getElementsByName("fruits");

// fruits.forEach(fruit =>{
//    if(fruit.checked){
//       console.log(fruit.value)
//    }
// })

// let vegetables=document.getElementsByTagName("li");
// vegetables[0].style.backgroundColor="darkRed";

// let dessert=document.getElementsByClassName("desserts");
// dessert[0].style.backgroundColor="lightPink";

// let elements= document.querySelector("#myTitle");
// let elements= document.querySelector(".desserts");
// let elements= document.querySelector("[for]");

// elements.style.backgroundColor="lightgrey"

// let elements=document.querySelectorAll("li");
// elements.forEach(element =>{
//    element.style.backgroundColor="lightgrey"
// })


// .firstElementChild
// let element=document.body;
// let child=element.firstElementChild;
// child.style.backgroundColor="lightgrey";

// .parentElements
// let element =document.querySelector("#vegetables");
// let parent=element.parentElement;
// parent.style.backgroundColor="lightgreen";

// .previousElementSibling 
// let element =document.querySelector("#vegetables");
// let nextSibling =element.nextElementSibling;
// let previousSibling=element.previousElementSibling; 
// nextSibling.style.backgroundColor="lightgreen";
// previousSibling.style.backgroundColor="lightgrey";

// children[]
// let element =document.querySelector("#vegetables");
// let child =element.children[0];
// child.style.backgroundColor="lightgreen";

// .Array.from(.children) 
// let element =document.querySelector("#vegetables");
// let children =Array.from(element.children);
// children.forEach(child =>
//    child.style.backgroundColor="lightgreen")



// .firstElementChild
// .lastElementChild
// .parentElements
// .nextElementSibling
// .previousElementSibling 
// .chidlren[]
// .Array.from(.children)

// add/change HTML element
// .innerHTML (vulnerable to XSS attacks)
// .textContent (more secure)

// const tagname =document.createElement("h1");
// tagname.textContent=window.prompt("Enter name");
// document.body.append(tagname)

// const myList=document.querySelector("#fruits");
// const listItem=document.createElement("li");
// listItem.textContent="mango";
// // myList.append(listItem); //them o cuoi  
// // myList.prepend(listItem) //them o dau

// myList.insertBefore(listItem,myList.getElementsByTagName("li")[2]); // them vi tri bat ki 

// const element=document.getElementById("myButton");
// element.onclick=doSomething;

// let element=document.body;
// element.onload=doSomething;

// let element =document.getElementById("myText");
// element.onchange=doSomething;

// let element =document.getElementById("myDiv");
// element.onmouseover=doSomething;
// element.onmouseout=doSomethingElse;

// element.onmousedown=doSomething;
// element.onmouseup=doSomethingElse;
// function doSomething(){
//    element.style.backgroundColor="red";
// }
// function doSomethingElse ()
// {  
//    element.style.backgroundColor="green";
// }

// let innerDiv=document.getElementById("innerDiv");

// innerDiv.addEventListener("mouseover",changeRed);
// innerDiv.addEventListener("mouseout",changeGreen);

// function changeRed(){
//    innerDiv.style.backgroundColor="red";
// }
// function changeGreen() {
//    innerDiv.style.backgroundColor="green";
// }

// const myButton= document.querySelector("#mybutton");
// const myImg=document.querySelector("#myimg");

// myButton.addEventListener("click",()=>{
//    // console.log(myImg.style.visibility   )
//    if(myImg.style.visibility=="hidden")
//    {
//       myImg.style.visibility  ="visible";
//    }
//    else {
//       myImg.style.visibility  ="hidden"
//    }
// })

// window.addEventListener('keydown',event=>console.log(event.key));

// const myDiv =document.getElementById("myDiv");
// window.addEventListener('keydown',move);
// let x=0,y=0;
// function move(event)
// {
//    switch(event.key)
//    {
//       case "ArrowDown":
//          y+=5;
//          myDiv.style.top=y+"px";
//          break;
//       case "ArrowUp":
//          y-=5;
//          myDiv.style.top=y+"px";
//          break;
//       case "ArrowRight":
//          x+=5;
//          myDiv.style.left=x+"px";
//          break;
//       case "ArrowLeft":
//          x-=5;
//          myDiv.style.left=x+"px";
//          break;
//    }

// }

// AINMATION 
// const myAnimation=document.getElementById("myDiv");
// const  myButton=document.getElementById("myButton");

// myButton.addEventListener('click',begin);

// function begin()
// {
//    let timerId=null;
//    let degrees=0;

//    timerId=setInterval(fram,5);

//    function fram(){
//       if(degrees >= 360 )
//       {
//          clearInterval(timerId);
//       }
//       else {
//          degrees+=1;
//          myAnimation.style.transform="rotateX("+degrees+"deg)";
         
//       }
//    }
// }

// CANVAS API  

let canvas= document.getElementById("myCanvas");
let context=canvas.getContext("2d");

// context.beginPath();
// context.moveTo(0,0);
// context.lineTo(250,250)
// context.lineTo(250,500)
// context.moveTo(500,0);
// context.lineTo(250,250);
// context.moveTo(500,500);
// context.lineTo(250,250);
// context.lineTo(250,0);
// context.moveTo(250,250);
// context.lineTo(500,250);
// context.stroke();

// drawtriangle : hinh tam giac 
// context.strokeStyle="grey";
// context.fillStyle="yellow";
// context.linewidth=10;
// context.beginPath();
// context.moveTo(250,0);
// context.lineTo(0,250);
// context.lineTo(500,250);
// context.moveTo(250,0);
// context.lineTo(500,250);

// context.stroke();
// context.fill();

//drawrectangle : hcn
// context.strokeRect(scalex ,scaley,x,y);
// context.fillStyle="yellow" // mau dinh dang 
// context.fillRect(0,0,150,150) // mau gan len hinh 
// context.strokeStyle="red";
// context.strokeRect(0,0,150,150);

// draw circle 
// context.arc(x,y,r,0,2*Math.PI=so vong)
// context.fillStyle="blue";
// context.beginPath();
// context.arc(250,250,200,0,2*Math.PI)
// context.stroke();
// context.fill();

// LOCAL STORAGE

