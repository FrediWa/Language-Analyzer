# Projekt 2

### Boilerplate och färdiga klasser:

- En Main-klass med en tom main()-metod
- En uppsättning texter på olika språk i */assets/lang-samples/*
- En enum-klass (LangLabel) med språkkoder enligt ISO 639-1.

Alla övriga detaljer finns på itslearning.

### Våra/mina egna kommenterar om de olika skedena i projektet:
#### Analyserande av samples
Jag tyckte att det skulle vara smart och göra färdiga analyser av alla samples. När mina alla analys metoder var färdiga, körde jag alla text samples genom den och sparade objecten m.h.a. vår FileUtils saveObject metod. Då behöver programmet inte varje gång det körs gå igenom alla samples utan den får analysen färdigt bara med att läsa en fil.
#### Analysator klassen
Hela analysatorn innehåller 6 metoder; 3 för analyserna, 1 för att städa text, 1 för att jämföra 2 Maps och en för att sammanställa hela analysen.
#### Analyserna
Jag började med den jag trodde var lättast, enstaka bokstäverna. Jag gjorde den på ett lite dumt sätt men beslöt att lämna kvar det då det ändå var en lösning. De andra två hittade jag snabbt på att jag kan fylla en tom Map med att kolla om en key redan finns. Om det finns, sätts värdet av nuvarande nyckeln till att vara nuvarande nyckelns värde + 1. Om det inte finns, sätts nyckeln in i Mappen med värde 1.
#### RegEx
En metod använder sig av en hel massa RegEx för att städa upp texten. Det finns en som tar bort alla icke-unicode tecken förutom whitespaces. En annan tar bort whitespace. Dessa är skillda för att whitespace behövs för att räkna första tecknet i ord i texten. Den sista tar bort alla delar av texten som har 2 eller flera whitespaces efter varandra, minns inte varför det händer men det händer.
#### Compare
Denna function tar in 2 Hashmaps som parametrar. Först gör functionen en HashSet på basen av de två HashMapparna. Detta ser till att functionen itererar genom ALLA tecken i båda Mapparna. Metoden itererar igenom HashSettet och på basen av vad den hittar från de två Mapparna, sätts ett värde(skillnaden, eller t.ex. värdet från map1 ifall nyckeln inte finns i map2) in i en HashMap för skillanderna. Denna itereras igenom och det totala värdet returneras.
#### Analysen
Denna metod returnerar ett Analys object som består av gissningen i formen av en LangLabel enum och resultatet i form av en HashMap<String, Double>. Den helt enkelt går igenom en array med strängar som motsvarar språken och söker upp rätt analys object bland lokala filerna. Sedan jämför den de 3 olika analyserna (från användaren) med det 3 olika sparade analyserna per språk. Skillnaden som compare() metoden returneras och metoden kollar om det är det lägsta hittils. När metoden gått igenom alla språk har den gissat färdigt och Analysis objectet returneras.
#### UI
UIn tycker jag använder sig av väldigt stilig object orienterad kod. Den tar in ett Analysis object och med en show metod skriver ut den. UI klassen har också en metod för att läsa in information. All interaktion med användaren sköts alltså av UI klassen. Däremot presenterar det inte datan riktigt enligt uppgiftsbeskrivningen.
