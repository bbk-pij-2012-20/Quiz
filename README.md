Quiz (Feb 2014 - March 2014)
-------------------------------------

Quiz
-----

This is the 2nd of 4 programming courseworks I completed (Feb - March 2014) as part of a conversion masters in Computer Science at Birkbeck College London.

The coursework task was to build a simple Quiz game (no GUI needed), that runs from a server and allows two clients to play via network programming such as RMI.

(This and the other 3 programming courseworks came after the introduction to programming with 'Java exercises' https://github.com/bbk-pij-2012-20/JavaExercises)



Description of how I built Quiz
-------------------------------

I began by building a non-networked quiz game, using TDD (most of the time). When all tests passed, the program worked well.

A quiz game was composed of 10 questions. Each question was composed of two parts (a predicate and a subject). Each question is randomly assembled from one of three predicates such as "How many colours has the flag of.." followed by the subject, randomly selected from a list of country names ..e.g. "Albania?". These two parts, the correct answer and 3 randomly generated wrong answers are stored in one array (held in 'QueAndAns').

The quiz was an array of 10 elements, each contain one of the aforementioned question and answer arrays. At the end of the quiz, the score was presented.

Network: This program was then altered significantly to attempt to place the code into new two server classes that would provide methods for two clients to call. The class which originally did most of the work 'QuizServerImpl', was renamed as 'QuizControllerImpl'.

MVC Attempting to produce something like a Model-View-Controller pattern, I left 'QuizDatabaseImpl' as is, considering it to be a good approximation of the Model part.

'QueAndAns' was also left pretty much as is, also part of the Model (but which builds itself). I wrote 5 new classes: 'QuizImpl', 'QuizFactoryImpl', 'QuizViewImpl' and the two Client classes.

After all these changes, the code has been left lacking in terms of all of the Javadoc being written correctly and the tests being refactored for the new layout and new classes.
The Quiz:

First, the setup client should activate quiz creation. It automatically builds 3 quizzes, each with its own unique id# and information about its current status.

The status should always be inactive at the start and should show the user got 0 correct out 0 attempted.

Next the player client should be able to access one of these quizzes from the menu to play.

The game only offers a menu of 3 different numbers of quiz questions (6, 8 or 10).

The questions are presented one at a time with multiple choice of 4 answers.

Only 1 answer is correct and all 4 answers should be unique and separated out.

The user should answer with a 1, 2, 3 or 4 (not the actual answer e.g. 4000 km).

At the end, the score should be presented.

The set up client should be able to stop a quiz in its tracks by passing the id number to the setUpServer.

Sadly, the code is not completely finished so there will likely be several holes in the functionality.

(All of this code is entirely my work)