# JavaSQL

Jag har index.jsp där man får välja vilken databas man ska lägga till i (med lite javascript 
för att ändra formuläret). Har en controller som tar hand om anropet och skickar vidare till
en statisk klass som hanterar databasen. jsp-sidan hämtar också en lista över städer och länder
som den använder för att ge alternativ (och för att de sparas i databasen som ett ID).
