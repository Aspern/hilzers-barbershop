#Hilzer's Barbershop

Allen B. Downey beschreibt in seinem Buch _The Little Book of Semaphores_<sup>[1]</sup> eine Variante des von Dijkstra erfundenen _Sleeping barber problem_,
dass sogenannte _Hilzer’s Barbershop problem_. Der Friseurladen hat jetzt drei Friseure und drei Stühle, sprich es können jetzt immer drei Kunden gleichzeitig
abgearbeitet werden. Insegsamt sollen nur 20 Kunden den Shop betreten können, diesen 20 Kunden steht ein Warteraum mit einem Sofa zur Verfühung auf welchem 4 
Leute Platz finden. Ist der Friseurladen voll, wird er von keinem weiteren Kunden betreten. <br>
Ein Friseur nimmt immer den Kunden dran, der am längsten auf dem Sofa sitzt. Wird ein Platz auf dem Sofa frei, setzt sich der Kunde darauf, der bereits am
längstem im Laden steht. Nachdem der Kunde seinen Haarschnitt erhalten hat muss er diesen beim Friseur bezahlen, dafür steht genau eine Kasse zur verfügung,
die von allen drei Friseuren verwendet werden muss. Der Friseur wiederum muss auf die Zahlung des Kunden warten und diese anschließend aktzeptieren, bevor der
Vorgang beendet werden kann und der Kunde den Friseurladen wieder verlässt.

##Implementierung in Java

Für den Warteraum und das Sofa wurden jeweils eine <code>BlockingQueue</code> verwendet, zunächst wird der Warteraum betreten, ist dannach ein Platz auf dem Sofa frei
setzt sich der Kunde sofort hin. Zu Begin wird die Klasse <code>Barbershop</code> mit drei <code>Barber</code> intilialisert. Diese Schlafen und warten bis sich ein 
<code>Customer</code> auf das Sofa sitzt und nehmen diesen zu sich. <br>
Dannach wird eine Schleife gestartet, die alle zwei Sekunden einen <code>Customer</code> erzeugt. Der <code>Barber</code> schneidet dem Kunden die Haare und verwendet 
anschließend die einizige Kasse (<code>CashRegister</code>) um die Bezahlung mit dem Kunden zu synchronisieren. Dazu wird ein fairer<code>ReentrantLock</code> verwendet, damit
sich kein <code>Barber</code> vordrängeln kann. Damit irgendwann ein Überlauf im Wartezimmer erzugt wird, gibt es beim Kunden eine Unterscheidung zwischen <code>Man</code> und
<code>Woman</code>, dabei brauchen die beiden unterschieldich lange beim Haare schneiden.
 
---
<small>[1] Allen B. Downey: The Little Book of Semaphores, Free Software Foundation, 2008, http://greenteapress.com/semaphores/downey08semaphores.pdf, S. 133-137</small>