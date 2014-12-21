Quiz
-----

This is the 2nd of 4 programming courseworks I completed (March/April 2014) as part of a conversion masters in Computer Science at Birkbeck College London.

The coursework task was to build a simple Quiz game (no GUI needed), that runs from a server and allows two clients to play via network programming such as RMI.

The requirements of the task are cut & pasted below. Below this is my description of what I did. 

(This and the other 3 programming courseworks came after the introduction to programming with 'Java exercises' https://github.com/bbk-pij-2012-20/JavaExercises)

---

###The problem description

You are asked to design and implement a simple on-line quiz game using Java RMI. This assignment is to be carried out individually.

###The quiz game

The quiz game system should consist of a central quiz server and two separate client programs: a set-up client and a player client.

The set-up client program should enable a user who wishes to organise an electronic quiz game to create a new quiz (i.e., a set of questions) on the server, with a given name for the quiz, and a set of possible answers for each question. This will return a quiz game id. At some point in the future, this client should be able to close the quiz game, quoting the game id. The outcome will be a notication of the winner together with full player details (which should be persisted on the server).

The player client program should enable players to play one of quizzes available on the server.

Firstly, the program should enable players to see a list of current quizzes and select one.
The players should then be able to answer each question of the quiz by choosing their answer amongst those suggested (i.e., multiple-choice).
At the end of the quiz the quiz server should return the score of the player for this quiz. 
The quiz game server should deal with (potentially concurrent) requests from the two client programs and maintain the state of the various ongoing quizzes in a consistent manner. The server should make use of persistence to store the quiz questions and the results of the various user attempts.

Please note: you are not expected to provide a sophisticated user interface for the two client programs (a simple text based interface will suffice).
Your grading will depend on the functionality of the system you provide; the above is a barebones description of the system and you should add additional functionaility as you feel appropriate.

-------------------------------------------------------------------------------------------------------------------------------------

Description of how I built my Quiz
----------------------------------

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