# INF138 Weather app
Η εφαρμογή αναπτύσσεται στα πλαίσια εργασίας στο μάθημα "ΠΡΟΗΓΜΕΝΕΣ ΜΕΘΟΔΟΙ ΑΝΑΠΤΥΞΗΣ ΛΟΓΙΣΜΙΚΟΥ"

### Contributors
* Βασίλειος Κωνσταντίνου. **Α.Μ:** p3312006
* Εμμανουήλ Σφενδουράκης **A.M.** p3312015
* Σώζων Λεβεντόπουλος **A.M.** p3312007

## Περιγραφή προβλήματος
Η υπηρεσία θα προσφέρει στους χρήστες τη δυνατότητα ενημέρωσης για προγνωστικά
καιρού για περιοχές της επικράτειας αξιοποιώντας πολλαπλές πλατφόρμες πρόγνωσης 
(π.χ. Accuweather, ΕΜΥ, Meteo.gr). Μετά την εγγραφή και δημιουργία του προφίλ του 
στο σύστημα, ο χρήστης θα μπορεί να αναζητεί στοιχεία πρόγνωσης καιρού για συγκεκριμένες
περιοχές.
Τα στοιχεία πρόγνωσης θα αφορούν τον τρέχοντα καιρό, ωριαία πρόγνωση για τις
επόμενες 3 ώρες και ημερήσια πρόγνωση για τις επόμενες 3 ημέρες με 3 προγνώσεις ανά
ημέρα (στις 08:00, 15:00 και 21:00). Ο χρήστης θα έχει τη δυνατότητα λήψης αναλυτικών
στοιχείων πρόγνωσης (η δυνατότητα λήψης αναλυτικών στοιχείων αναφέρεται στη λήψη μιας 
απόκρισης από το σύστημα που περιλαμβάνει δύο ή περισσότερες προγνώσεις, μια από κάθε 
υπηρεσία πρόγνωσης) ή συγκεντρωτικών που θα προκύπτουν από στάθμιση των δεδομένων των
επιμέρους υπηρεσιών (π.χ. επιστροφή της πρόγνωσης με τον υψηλότερο αριθμό αξιολογήσεων
από τους χρήστες). Ο χρήστης κατά τη δημιουργία του προφίλ του θα επιλέγει τις υπηρεσίες
πρόγνωσης που θέλει να χρησιμοποιήσει. Ο χρήστης θα έχει τη δυνατότητα να αξιολογεί 
εκάστοτε πρόβλεψη σε κλίμακα 1 (καθόλου ικανοποιημένος) έως 5 (απόλυτα ικανοποιημένος).
Οι αξιολογήσεις των χρηστών θα αξιοποιούνται για τον υπολογισμό της συγκεντρωτικής 
πρόβλεψης ανά περιοχή (ανάλογα με τις εκάστοτε αξιολογήσεις το σύστημα θα προτείνει στο 
χρήστη πρόβλεψη από την (ή τις) υπηρεσίες με το μεγαλύτερο σκορ).
Για την αποδοτική απόκριση στις αναζητήσεις, το σύστημα θα λαμβάνει περιοδικά και θα
αποθηκεύει τα στοιχεία προγνώσεων από όλες τις συνεργαζόμενες υπηρεσίες. Για το
σκοπό αυτό θα παρέχει κατάλληλο API το οποίο θα καλείται από τις συνεργαζόμενες
υπηρεσίες με σκοπό την ενημέρωση προγνώσεων ανά περιοχή, όταν προκύπτουν
κάποιες αλλαγές σε αυτές.
Τέλος, η εφαρμογή θα έχει την δυνατότητα αποστολής προειδοποιητικών μηνυμάτων
στο χρήστη σε περίπτωση κακοκαιρίας (μόνο σε περιπτώσεις κινδύνου ζωής) για όλες τις
περιοχές ενδιαφέροντος που έχει καταχωρίσει στο προφίλ τους

## Σύνδεσμοι σε αρχεία προδιαγραφών.
* [SoftwareRequirements](/src/site/markdown/SoftwareRequirementsSpecification.md)
* [Identification use case 1](/src/site/markdown/uc1-user-identification.md)
* [Profile administration use case 2](/src/site/markdown/uc2-user-profile-administration.md)
* [Weather search use case 3](/src/site/markdown/uc3-weather-search.md)
* [Weather search summary  use case 4](/src/site/markdown/uc4-weather-search-summary.md)
* [Forcast rating use case 5](/src/site/markdown/uc5-service-rating.md)
* [update weather data use case 6](/src/site/markdown/uc6-update-weather-data.md)
## Σύνδεσμοι σε διαγράμματα ροής
* [Forecast sequence diagram](/src/site/markdown/uml/reqs/forecast-sequence-diagram.png)
* [Forecast now sequence diagram](/src/site/markdown/uml/reqs/forecast-now.png)
* [Forecast next 3 hours sequence diagram](/src/site/markdown/uml/reqs/forecas-next-3-hours.png)
* [Forecast next 3 days sequence diagram](/src/site/markdown/uml/reqs/fore-cast-next-3-days.png)
* [User Login sequence diagram](/src/site/markdown/uml/reqs/uc1_userCreation.png)
* [User rating sequence diagram](/src/site/markdown/uml/reqs/sequence-user-rating.png)


## Use case diagram
![usecase diagram](/src/site/markdown/uml/reqs/use-case-diagram.png)
## Domain Model diagram
![Domain model](/src/site/markdown/uml/reqs/domain-model.png)
## Class diagram
![Class Diagram](/src/site/markdown/uml/reqs/class-diagram.png)
## Χρήσιμες εντολές:

Η διαχείριση της οικοδόμησης του έργου μπορεί να γίνει με μια σειρά βασικών εντολών:
- `mvn`: εκτελεί τον προκαθορισμένο κύκλο οικοδόμησης
- `mvn test`: εκτελεί τα unit tests του project
- `mvn site`: παράγει την τεκμηρίωση του project σε μορφή HTML. Τα παραγόμενα αρχεία 
  είναι διαθέσιμα στην τοποθεσία `target/site/`
- `mvn umlet:convert -Dumlet.targetDir=src/site/markdown/uml`: παράγει αρχεία εικόνας png για όλα τα διαγράμματα που βρίσκονται στην τοποθεσία `src/site/markdown/uml`. Συστήνεται η κλήση της εντολής πριν την υποβολή μιας νέας έκδοσης διαγραμμάτων στο git repository (`git commit`). Ως αποτέλεσμα τα παραγόμενα αρχεία εικόνας των διαγραμμάτων συνοδεύουν τα πηγαία αρχεία έτσι ώστε να είναι εύκολη η πλοήγηση στην τεκμηρίωση του project  μέσω του github.  

EDIT FOR NEW ASSIGMENTS


## Migrating to microsservices

The application will be split into 3 microservices. UserService, ForcastProviderService and ForecastService.
The following schema shows the 3 microservices.


## Forecast Service

### Location Endpoint

* addNewLocation **POST locations/{name}**

* getLocationByName **GET locations/{name}**

* getAllLocations **GET locations**

### Forecasts endpoints

* getCurrentForecastForeUser **GET forecasts/{name}?username=aaa**

* addForecast **POST forecast/**

* getNextThreeHoursForecast **GET forecasts/hours/{name}?usrname=aaa**

* getNextThreeDays **GET forecasts/days/{name}?usrname=aaa**

* getNextThreeDaysSummary **GET forecasts/days/summary/{name}?username=aaa**

* getWarningsForToday **GET forecasts/warn/username**

* getForecastByLocation **GET forecasts/location/{forecast_id}**

* deleteForecast **DELETE forecasts/{forecast_id}**

* getForecastRating **GET forecasts/rating/{location_id}**


### Rating endpoints

* addRatingToForecast **POST rate**

* deleteRating **DELETE rate/{id}**

## Forevast Provider Service

* addNewForcastProvider **POST forecast_providers/{name}**

* changeForcastProviderName **PUT forecast_providers/{oldName}**

* getAllServices **GET forecast_providers**

* getServiceByName **GET forecast_providers/{name}**

* deleteProvider **DELETE forecast_providers/{name}**


## User Service

* getUser **GET users/{username}**

* login **GET users/login**

* register **POST users**

* addLocation **POST users/location**

* addForecastProviders **POST user/forecast_providers**

* getLocation **GET users/location/{username}**

* getForecastProviders **GET forecast_providers/{username}**
