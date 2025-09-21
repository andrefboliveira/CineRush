# CineRush
Pair project titled "CineRush" for the course "Computação Móvel" (Mobile Computing) at the school year 2016/2017 of the Master's degree in Informatics (Mestrado em Informática) at FCUL/ULisboa

## Project Stack
 Android, Java, Android Studio, Google Cloud, Firebase, Google App Engine, REST web services.
 
## Details
Pair project

Development a native Android application (Java) that displays  movies on exhibition on Portuguese movie theatres.

The backend uses Java and was hosted in Google Cloud and Google App Engine. 
It retrieves data from Sercultur REST web service (the data source used by Sapo).
It stores in a Firebase database to allow processing and asynchronous update.
The user interface of the mobile app filters results based on the user current location.

## Code structure
The general organization of the code is as follows:
- [backend](./backend) contains the code running in the Google App Engine to retrive and save the movies schedules data from the data source
  - the source code for the backend is stored at [/src/main](./backend/src/main/java/pt/ulisboa/ciencias/cinerush/backend)
- [app](./app) contains the Android app code
  - the source code for the Android app is stored at [/app/src/main](./app/src/main) and specifically at [cinerush](./app/src/main/java/pt/ulisboa/ciencias/cinerush)

## Contributors
- André Filipe Bernardes Oliveira
- Tomás Peixinho

Some of my personal contributions (André) to the pair project were:
- fully developed the backend to retrieve data from sercultur webservice and save to Firebased database
- implemented login in the Android app via Firebase Authentication 
- created Android activities to retrieve data from Firebase and display movie schedule data
