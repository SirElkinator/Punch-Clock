#Punch Clock
Information:

This project is designed to implement a punch clock for a company, with the idea of eventually connecting it to several terminals
across a building and making an interface to simplify the process for employees. This was created for a Software Engineering I 
course by a team consisting of the following members: Chase Sparks, Richard Arredondo, Andrew Reese, Benjamin Baker and in the role
of ScrumMaster Raushaod Merritt. The class was instructed by Jay Snellen and this project served as a final project for the course,
taking about 2 months to create. The class was set up to develop and instill several different programming skills in the students. 
In the creation of this software the agile method of Scrum was applied, with four sprints consisting of two weeks apiece. GitHub and
Git were used for source control, and SQL was used as a database to hold the punches with the program MySQL. The project is written 
in Java and was worked on in the IDE NetBeans.

TESTS

There were a set of eight different features outlined that could be completed before the end of the semester, this project currently
has the first five complete and the sixth started. Each feature has a corresponding test file that checks it for accuracy, these were
made by Jay Snellen. There is a main function in this program, but it is not populated because individual classes were being called upon
by these test files.

PUNCH

This sets the parameters for Punch objects, which are employees clocking in and out of work. It consists of the punchtype 
  (clock in/clock out/timed out), the timestamp of the punch, and the badgeid of the employee. It also includes a function labeled 
  “adjust”, which will move an employee’s punch forward or backward fifteen minutes in time to certain sets of circumstances. It comes
  with several getter methods towards the bottom and print functions to print timestamps and adjusted timestamps.

SHIFT

Contains information on how lunches are handled. Shifts are also inserted and selected from the database. It sets what the appropriate
  start and end time of shifts are supposed to be, compared to the actual punches of the employees. This, as with the previous class, has
  getter methods for all variables.

BADGE

The badge class helps identify an employee, each badge object created is for a specific employee at the company, the badgeid is used 
  in the punch and database classes to find out who made certain clock ins and clock outs. There are no functions in this class, just 
  several parameters.

TASDATABASE

This is where a whole lot of the program is based, inserting and selecting the three objects created with the classes above into the
  SQL database. 

TASLOGIC

This class, as the name implies does different things with information from the database. The first function calculates the total 
  minutes accrued by an employee over the course of a pay period. The middle function takes the different punches made in a pay period 
  and prints them out as one uniform list. The last function is planned to eventually calculate an absenteeism percentage as described 
  below.

ABSENTEEISM

This class was intended to calculate absenteeism percentage of an employee over the course of a pay period and the semester ended 
  before this was fully implemented; however, it is expected to be eventually completed.
