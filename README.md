# Corda
This corda application was an assignment for the Basics of Grid and Cloud Computing course. 

# Legend
There are libraries in your town, which can lend books to authorized users. 

For that, they mark down user's name, book's title and author's name in the system.

A user can check what kind of books he had borrowed from which libraries. 

# How to deploy
* Clone this repository

* Do commands clean, build, jar and deployNodes using Gradle

* Open the folder build\nodes in the terminal and run command runnodes.bat

![](https://github.com/MilonLemon/Corda/raw/master/materials/1.jpg)

* Go to Run->Edit Configurations and change Program arguments to this:

![](https://github.com/MilonLemon/Corda/raw/master/materials/2.jpg)

    The value of config.rpc.port depends on which node you want to work with:
    
    Library1 - 10006, Library2 - 10008, User1 - 10011, User2 - 10013

# How it works

* If you are Library1 you can lend books to users by going to this address:

http://localhost:10050/lendBook?library=Library1&user=User1&author=A_S_Pushkin&title=Eugene_Onegin

![](https://github.com/MilonLemon/Corda/raw/master/materials/Library1.gif)

Also, as shown in the gif, if you enter as Library 1, you cannot lend books as Library2 - you will get an exception.

***

* If you are Library2 you can lend books to users by going to this address:

http://localhost:10050/lendBook?library=Library1&user=User2&author=A_S_Pushkin&title=Capitans_Daughter

![](https://github.com/MilonLemon/Corda/raw/master/materials/Library2.gif)

If you go to http://localhost:10050/borrowedBooks while being a Library, you will see all the books you have lended.

***

* As a user, you can check from which libraries you borrowed which book. 

So after entering with the right user node, you need to go to this address: 

http://localhost:10050/borrowedBooks

![](https://github.com/MilonLemon/Corda/raw/master/materials/Users.gif)
