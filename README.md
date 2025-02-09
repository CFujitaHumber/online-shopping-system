# online-shopping-system
Submission for Assignment 1 for CPAN 131. 
This has been rewritten to not use dynamic arrays and generics as use of data structures not yet covered in class result in penalty marks. 

# specifics and run conditions
Uses JavaSE-23. Application will use `Scanner` from `java.util` from `System.in` to run.


![UML diagram of Assignment](https://github.com/CFujitaHumber/online-shopping-system/blob/main/docs/program-diagram.png?raw=true)


### Inputs
Reading from `System.in` program will take a variety of inputs:
- reading menu nagivation (1-4)
- reading quanities (any real)
- new prices (any real in `double` precision)

### Processing

#### main menu
1. Greet user
2. Display a menu and prompt for menu selection
3. read selection
4. if selection is 1
5. output cart
6. if selection is 2
7. run add product menu
8. if selection is 3
9. run remove product menu
10. if selection is 4
11. run checkout and end program
12. if selection is invalid
13. notify user and return to 2

#### add product menu

1. prompt for id
2. read id
3. if id is valid
4. prompt for amount
5. if amount is valid
6. add amount to existing amount of that product
7. if id is not valid
8. notify user return to 1
9. if amount is not valid
10. notify user
11. return to step 4
12. exit to main menu

#### remove product from menu

1. prompt for id
2. read id
3. if id is valid
4. prompt for amount
5. if amount is valid
6. remove amount to existing amunt of that product
7. if amount is less than or equal to zero
8. remove the amount
9. if id is not valid
10. notify user return to 1
11. if amount is not valid
12. notify user and return to 4
13. exit


## Note
Assignment requirement document is probbably copyrighted by Humber and will not be included in repository.


