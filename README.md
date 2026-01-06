# Kata Generic Rotating Buffer made by JAD

## Objectif de ce Kata

L'objectif est de fournir un composant générique de buffer circulaire.

Imaginez le buffer de votre clavier. La mémoire tampon du clavier est statique. C'est une puce
contenant quelques octets de mémoire. Cette mémoire n'est pas dynamique, le nombre de touches
pouvant être stockées avant traitement par l'ordinateur est donc fini. Vous avez déjà peut-être eu
le cas où votre clavier émet un bip si vous tapez trop de touches (au démarrage de l'ordi par
exemple). C'est une manière pour le poste de vous prévenir que sa mémoire tampon est pleine et que
toutes les touches que vous saisissez ne sont pas prises en compte.

Dans le cas d'un clavier d'ordinateur, le système émetteur (vous qui tapez sur le clavier) et le
récepteur (l'ordi qui doit traiter les touches) sont totalement asynchrones. Vous nêtes pas soumis à
la même temporalité que l'ordinateur. La mémoire tampon du clavier doit donc être accessible en
lecture et en écriture en même temps. De plus, un tableau classique ne serait pas efficace. En
effet, si vous avez rempli toute la mémoire tampon, vous devrez attendre que toutes les touches
aient été traitées par l'ordinateur (et que la mémoire soit vide) pour commencer à le
re-remplir. Il serait plus intéressant de re-remplir le tableau au début (à partir des touches déjà
traitées par l'ordi).

C'est ce que l'on appelle un buffer circulaire. C'est un tableau qui se remplit de manière
circulaire. Une petite image pour illustrer le fonctionnement :

![Illustration du buffer circulaire](https://github.com/Jean-Aymeric/RotatingBufferStart/blob/master/img/rotattingbuffer.png)

Le buffer circulaire est un tableau de taille fixe.

Il est composé de deux indices :

- un indice de lecture
- un indice d'écriture

Pour rendre le fonctionnement efficace, il vaut mieux sous-traiter la lecture et l'écriture.

## Voici le diagramme de classe de la situation initiale :

![Diagramme de classe de la situation initiale](https://github.com/Jean-Aymeric/RotatingBufferStart/blob/master/img/classdiagramrotatingbuffer.png)

Comme vous pouvez le voir, il n'y a pas de `Main`, mais il y a des tests unitaires.

Si vous lancez les tests, vous devez obtenir ceci.

```
java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.rotatingbuffer.RotatingBuffer.getSize(RotatingBuffer.java:19)
	at com.jad.rotatingbuffer.RotatingBuffer.<init>(RotatingBuffer.java:11)
	at com.jad.rotatingbuffer.RotatingBufferInitialTest.initEach(RotatingBufferInitialTest.java:19)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1311)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1840)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.rotatingbuffer.RotatingBuffer.getSize(RotatingBuffer.java:19)
	at com.jad.rotatingbuffer.RotatingBuffer.<init>(RotatingBuffer.java:11)
	at com.jad.rotatingbuffer.RotatingBufferInitialTest.initEach(RotatingBufferInitialTest.java:19)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1311)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1840)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.rotatingbuffer.RotatingBuffer.getSize(RotatingBuffer.java:19)
	at com.jad.rotatingbuffer.RotatingBuffer.<init>(RotatingBuffer.java:11)
	at com.jad.rotatingbuffer.RotatingBufferInitialTest.initEach(RotatingBufferInitialTest.java:19)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1311)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1840)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.rotatingbuffer.RotatingBuffer.getSize(RotatingBuffer.java:19)
	at com.jad.rotatingbuffer.RotatingBuffer.<init>(RotatingBuffer.java:11)
	at com.jad.rotatingbuffer.RotatingBufferInitialTest.initEach(RotatingBufferInitialTest.java:19)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1311)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1840)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.rotatingbuffer.RotatingBuffer.getSize(RotatingBuffer.java:19)
	at com.jad.rotatingbuffer.RotatingBuffer.<init>(RotatingBuffer.java:11)
	at com.jad.rotatingbuffer.RotatingBufferInitialTest.initEach(RotatingBufferInitialTest.java:19)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1311)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1840)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)


java.lang.UnsupportedOperationException: Not implemented yet

	at com.jad.rotatingbuffer.RotatingBuffer.getSize(RotatingBuffer.java:19)
	at com.jad.rotatingbuffer.RotatingBuffer.<init>(RotatingBuffer.java:11)
	at com.jad.rotatingbuffer.RotatingBufferInitialTest.initEach(RotatingBufferInitialTest.java:19)
	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.tryRemoveAndExec(ForkJoinPool.java:1350)
	at java.base/java.util.concurrent.ForkJoinTask.awaitDone(ForkJoinTask.java:422)
	at java.base/java.util.concurrent.ForkJoinTask.join(ForkJoinTask.java:651)
	at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1311)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1840)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)


Process finished with exit code -1
```

C'est tout à fait normal. Le constructeur de la classe `RotatingBuffer` n'est pas totalement
implémenté.

## Tests automatisés

Les tests sont automatisés, à chaque `push` dans votre dépôt. Chaque test est valorisé.

