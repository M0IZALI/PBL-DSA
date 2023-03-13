# PBL-DSA
Dataset: https://www.kaggle.com/datasets/usgs/earthquake-database

Step 1: Use https://www.daniel-braun.com/technik/reverse-geocoding-library-for-java/ library to find 
out the city and country from the given coordinates and store them in yearly earthquake collection along 
with magnitude. (Collection of each year means 52 collections) 

Step 2: Make a queue storing biggest (with highest magnitude) quake of each year with magnitude and 
country, starting from 1965 to 2016. (52 elements in the queue approx.). 

Step 3: Make a stack from the collections, one for each country which stores earthquake and its magnitude 
in the order of the event (the most recent event on top). 

Step 4: Make a linked list which saves the one most recent earthquake with magnitude and country name 
from each country (use the stack from step 3). 

Problem 1: How to find the average number of earthquakes per year for each country and which country is 
most vulnerable to earthquakes (which country has the most number of earth quakes)? 

Problem 2: Which are the biggest earthquakes from 2005 to 2015 and occurred and in which country (use 
step 2)? 

Problem 3: How to determine the recent 5 earthquakes from each country? 

Problem 4: How to find the most recent above 6 magnitude earthquakes (use step 4). 
