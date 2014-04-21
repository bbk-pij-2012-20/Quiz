All of this code is entirely my work. 

The only part I got hands on help with was with incorporating the security policy into the servers so that it runs in Eclipse. And to 
separate out the classes into several different packages (Thanks to Joe Watkins).

I first made non-networked quiz game, using TDD (most of the time). When all tests passed, the program worked well.
A quiz game was composed of 10 questions. Each question was composed of two parts, a bit like predicate and subject 
(but possibly not..). Therefore a question is randomly assembled from one of three question starts such as "How many 
colours has the flag of.." followed by a random selection of a country name ..e.g. "Albania?". These two parts, the correct
answer and 3 randomly generated wrong answers are put into one array (held in QueAndAns). 
The quiz was an array of 10 elements, each contain one of the aforementioned question and answer arrays. At the end of 
the quiz, the score was presented. 
This program was then altered significantly to attempt to place code into new two server classes that would provide 
methods for the two clients to call. The class which originally did most of the work 'QuizServerImpl', was renamed QuizControllerImpl.
Attempting to produce something like a Model-View-Controller pattern, the QuizDatabaseImpl was left as is (and considered along the lines of the 'Model'), 
the QueAndAns was also left pretty much as is, also part of the Model (but which builds itself). New classes made: QuizImpl,
QuizFactoryImpl, QuizViewImpl and the two Client classes. 

After all these changes, the code has been left lacking in terms of all of the javadoc being written correctly and the 
tests being refactored for the new layout and new classes. 

The Quiz:
---------
First, the setup client should activate quiz creation. It automatically builds 3 quizzes, each with 
its own unique id# and information about its current status. The status should always be inactive at the start and
should show the user got 0 correct out 0 attempted.  

Next the player client should be able to access one of these quizzes from the menu to play. 

The game only offers a menu of 3 quizzes and they are the same, other than the number of questions (6 8 or 10).

The questions are presented one at a time with multiple choice of 4 answers. 

Only 1 answer is correct and all 4 answers should be unique and separated out. 

The user should answer with a 1,2,3 or 4 (not the actual answer e.g. 4000km). 

At the end, the score should be presented.

The set up client should be able to stop a quiz in its tracks by passing the id number to the setUpServer.

----------
Sadly, the code is not completely finished so there will likely be several holes in the functionality. 
