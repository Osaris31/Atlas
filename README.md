# Atlas

Pour commencer, créez un Agent:
```
    Agent agent = new Agent();
    agent.nom = "Smith";
    agent.prenom = "John";
```

Faites le rejoindre une agence:
```
    agent.rejoindreAgence("Atlas");
```
Les agences correspondent au niveau de difficulté: chauqe agence propose une liste de mission dans son niveau, et quand suffisament de ces missions ont été réussies on peut rejoindre l'agence suivante.

Pour afficher les missions proposées, faire:
```
    agent.afficherMissionsDisponibles();
```

Puis en choisir une avec 
```
    agent.choisirMission("Cible prioritaire");
```

Cela affichera l'objectif de la mission choisit. Par exemple, "Pour cette mission, il y a deux cibles. Renvoyer le nom de la cible la plus proche des deux."

Il faut donc coder une solution à cette mission, pour cela créer une classe qui implémente Solution, puis faire:

    Solution solution = new ExempleSolutionMission1();

    agent.accomplirMission(solution);
    
