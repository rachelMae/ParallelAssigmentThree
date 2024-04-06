# ParallelAssigmentThree

Both programs are one file as labeled and can be run on the command line as typical for java or from an IDE set up for java. Java must be installed on your device either way.

1. Presents

One thing that could have led to more presents than “Thank you” notes is that when a servant started writing a thank you note for a present the present was in the lineup so another servant also started writing a thank you note, however, by the time the first finishes and removes the present the other servant will go and remove the present now in its place, however, no thank you note was written for that present. This could lead to multiple notes for one present and no notes for another present. One way to combat this issue and other race conditions that can occur is coordinating a bit between the threads and servants and removing a present from the list before writing a thank you note.

2. Weather

In order to simulate an hour of reading with 1 reading per minute, this program generates 60 readings and uses them accordingly. This program efficiently uses Java ArrayList features to transverse and sort arrays to print information about the top temperatures and lowest temperatures. The eight threads accurately and quickly generate 60 readings.
