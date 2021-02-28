# Aplicacio Patata
------------
# Utilització
### Funcionalitats de l'aplicació
Un cop obrim l’aplicació, immediatament sortirà un missatge de benvinguda durant tres segons, i després la pantalla per fer login. Podem posar qualsevol usuari i la contrasenya ha de ser de més de 8 caràcters. Un cop hem fet login, l’aplicació principal es carrega i té diferents funcions: 

- **Llista de patates**. És una llista de totes les patates que tenim a la base de dades actualment, i podem desplaçar-nos amunt i avall per veure més. Mostra l'ID, tipus, data a sembrar, data a recollir i preu de la patata. A les cantonades inferiors de la pantalla tenim dos botons, un per cercar i l’altre per afegir.
- **Cercar patata**. Si li donem al botó per cercar ens sortirà una nova pantalla on tindrem una caixa de text i dos botons, un per fer la cerca i l’altre per tornar. Hem d’escriure l’ID de la patata que volem buscar i, si en troba, ens portarà a una altra pantalla on mostrarà tota la informació d’aquesta patata en detall. Si deixem la caixa en blanc o no troba l’ID, sortirà un missatge informant de l’error. 
- **Afegir patata**. Si li donem al botó per afegir, sortirà una nova pantalla on hi haurà múltiples caixes de text per escriure la informació rellevant, i dos botons per importar audio i/o una imatge. També tindrem dos botons per afegir o tornar enrere. Si l’ID escrit no existeix ja, s’afegeix la nova patata i surt un missatge de confirmació. Si ja existeix o deixem algun camp en blanc, sortirà un missatge indicant l’error. La importació de l'audio i la imatge són opcionals.
- **Resultat de la cerca**. Quan fem una cerca correcta, ens surt una pantalla nova on surt tota la informació corresponent a la patata cercada. Hi ha dos botons per reproduir o parar l'audio, i un espai en blanc per la imatge. Si no hi ha audio o la ruta no es correcta (o el audio ja no existeix), ens sortirá un missatge informatiu al premer el botó de reproduir. Si el audio ja s'està reproduint, al premer el botó reproduir ens avisa d'això. Si no hi ha imatge o la ruta no es correcta (o la imatge ja no existeix), surt un missatge informatiu i l'espai per a la imatge queda en blanc. Tenim un botó per tornar a la llista.

L’aplicació té una **icona**, **nom** i **colors** personalitzats, té una pantalla de **benvinguda** que dura 3 segons, cada fragment té el seu **títol** corresponent a la barra superior i té tres **idiomes** inclosos (Català, Anglès i Castellà).
