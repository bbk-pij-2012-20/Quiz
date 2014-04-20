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

The Quiz:
---------
The game only offers a menu of 3 quizzes and they are the same other than the number of questions they are made up of.
The questions are presented one at a time with multiple choice of 4 answers. Only 1 answer is correct and all 4 answers
should be unique and separated out. The user should answer with a 1,2,3 or 4 (not the actual answer e.g. 4000km). 

