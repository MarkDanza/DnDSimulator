# DnDSimulator

DESCRIPTION:
This is a work-in-progress application for simulating the combat rules of the popular tabletop RPG 
Dungeons and Dragons (© Wizards of the Coast). Its purpose is to provide a game environment for players 
to test and develop their D&D characters in. This application is an interactible proof of concept that 
demonstrates some of the most basic features of D&D comabt using a PTUI and simple text commands. 
Features in development include additional combat rules, more diverse character creation options, and 
a reorganization of the source code following the MVC design pattern in preparation for an eventual 
switch from PTUI to GUI.

HOW TO USE:
The program will print a grid view of the combat map, then prompt for a command. Valid commands are as
follows:
  - (m)ove x1 y1 x2 y2: Move the piece at location (x1, y1) to location (x2, y2).
  - (a)ttack x1 y1 x2 y2: Make the piece at (x1, y1) attack the piece at (x2, y2).
  - (q)uit: terminate the program

Pieces on the grid are indicated by letters, while empty spaces are displayed as periods ('.'). Each
grid space represents a 5ft by 5ft square area.
Pieces may only move a distance up to their speed in a single move command. Currently, the speed of each
piece is 30ft.
Pieces may only make attacks against other pieces which are within their attack range (determined by
the attacker's weapon). Currently, the attack range of each piece is 5ft. 
Attacks only deal damage if they hit, and whether or not an attack hits is determined by a simulated dice 
roll using a random number generator. Currently, the attacker rolls a die with the possible results of 
1 to 20 (inclusive), then adds 2 to the result. If the final total is greater than or equal to 15, the 
attack hits and deals damage. 
Each piece currently has 10 hit points (meaning they can take 10 points of damage before dying).
When a piece is reduced to 0 hit points, it "dies" and is removed from the grid.
