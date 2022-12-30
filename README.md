
# Exercise 1 observer pattern
## Overview

## Installation

1. Download the repository files (project) from the download section or clone this project by typing in the bash the
   following command:


    git clone https://github.com/eytan1998/OOP1.git


2. Imported it in Intellij IDEA or any other Java IDE.
3. Run the program :D

## observer pattern

Observer pattern is a software design pattern in which an object, named the subject (observable),
maintains a list of its members, called observers, and notifies them automatically of any state changes,
by calling the update method.

//The algorithm works so that there is one will follow that has many "followers".
//The "followers" will receive a notification when something changes in the one will follow!!! and they will be able to
//update the information they have with the latest information
//The algorithm will be implemented so that the follower contains a list of all the "followers" who receive the updates,
//and he is the one who can change the list.

## UML
![Calculator - screenshot](EX1_Diagram.png)
## [GroupAdmin](src/main/java/GroupAdmin.java) (observables)

The role of the GroupAdmin is to change the UndoableStringBuilder,notify the member when changes are made,and register/unregister Members.


GroupAdmin implement Sender


GroupAdmin contain:

UndoableStringBuilder

LinkedList<Member>

The LinkedList<Member> contains all the Members who "follow" the GroupAdmin

The UndoableStringBuilder is what the ConcreteMembers interested in him, and want to be notify when it changes

##central methods

### register
The purpose of the register is to add a member to our list of "followers" so that he will receive updates
ConcreteMember can follow a GroupAdmin several times,and when he is change, he will be notify for each of the
registrations.
If you register in the first time your UndoableStringBuilder will not be connected to the
GroupAdmin.UndoableStringBuilder until a notify is called

### unregister

When you're unregistered you don't get anymore notification and your UndoableStringBuilder won't change either.
If the ConcreteMember has register several times to unregister completely, you need unregister as many times as you register.
If we unregister completely inside to unregister we will create a new UndoableStringBuilder that will contain the
content of the GroupAdmin.UndoableStringBuilder and the ConcreteMember.UndoableStringBuilder will point to the new UndoableStringBuilder.

### notifyMembers

We inform all members that there is a new update, and they will updated themselves.

## [ConcreteMember](src/main/java/ConcreteMember.java) (observer)
   
The ConcreteMember "follows" the GroupAdmin and wants to be updated with the information stored there.

ConcreteMember implement Member
   
ConcreteMember contain

UndoableStringBuilder stringBuilder;

String name;

int nameIndex;

name and nameIndex their purpose is only so that we can identify different ConcreteMember(and we will use nameIndex when
no names have been entered for the ConcreteMember)
   
##central methods
   
### update
Replace the old UndoableStringBuilder with current UndoableStringBuilder.  
when you update in the first time all your UndoableStringBuilder history override by observable.
   
   
### resetUndoableString
   So that we can disconnect completly we must rest the UndoableStringBuilder by create a new UndoableStringBuilder
   

   
##sender
   the "sender" interface describes the update sender
   
   
##member
   the "Member" interface describes the update recipient
