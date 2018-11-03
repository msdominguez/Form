# Form
Form (survey/test) maker &amp; manager\
Create, take, and edit surveys and tests\
Save and load by utilizing Serializable interface\
Question types: essay, multiple choice, short answer, ranking, matching, true/false

## Source Files:
Main.java (main driver)\
Survey.java\
Test.java\
IO.java\
Response.java\
Attempt.java\
RCA.java\
CA.java\
Question.java\
Essay.java\
Matching.java\
MultipleChoice.java\
Ranking.java\
ShortAnswer.java\
TrueFalse.java

## Sample Files:
survey1.ser\
test1.ser

## Development Environment:
Eclipse Oxygen

# User Manual
I used the Serializable interface to load/store files. The sample survey and test files are in the folder "form".\

When you take a survey or a test, the responses are recorded in a vector called "attempts," which corresponds to a certain test or survey. You can grade an attempt of a certain test. Tabulation for a certain survey or test is done by looking at all of the responses in the attempts vector for each question. Therefore, no additional files are created for attempts, it's saved in the original survey or test file. I supplied 3 attempts in my sample survey and test.\

No null responses are permitted, and you must type an integer when asked for an integer. There are boundaries for entering integers (i.e. Number of attempts: 4 Which attempt do you want to grade? - You can only enter a number between 1-4.)\

You must have a survey or test loaded first in order to display it. You must save a survey or test after you create it.\

Some questions may ask for multiple responses. For example, a multiple choice question that asks for 2 responses. In this case, the user enters 2 letters (on 2 different lines) and that's their full response for the question. So when it gets graded, they need to have both of the correct letters or their answer is incorrect. Likewise when all of the responses are tabulated, permutations are the same for multiple choice (i.e. A B = B A so it would say 2) A B if these were 2 different responses.) Ranking questions differ because the order matters. The permutations aren't accounted for either. (i.e. (1) 1 2 3 (2) 3 2 1 =/= (1) 3 2 1 (2) 1 2 3 for tabulation of multiple ranking answers)\

Permutations aren't accounted for in matching questions (i.e. A 1 B 2 C 3 =/= B 2 A 1 C 3) However, for multiple answers in matching questions, you can give the answers out of order. For example, the correct answer is: (1) A 1 B 2 C 3 and (2) B 2 A 1 C 3. If the users gives exactly both of these but enters (2) then (1), their answer is correct.\
