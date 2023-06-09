# TP2 - String, StringBuilder, égalité, et expressions régulières
## GIBOZ Alexandre, INFO1 2022-2025
***

Chaque répertoire a pour nom le numéro d'exercice auquel il est rattaché. 
Ces répertoires contiennent les fichiers ".java" associés, ainsi que tout autre fichier en lien avec l'exercice en question.

[Consignes de rendu de TP](https://monge.univ-mlv.fr/ens/IR/IR1/2022-2023/Java/index.php)

[Énoncé](https://monge.univ-mlv.fr/ens/IR/IR1/2022-2023/Java/td02.php)
***

## Exercice 1 - Assignation, égalité, référence

1. **On considère le code suivant :**
```java
var s = "toto";
System.out.println(s.length());
```

**Quel est le type de s ? Comment le compilateur fait-il pour savoir qu'il existe une méthode length() sur s ?**

Le type de la variable s est `String`. 
Le compilateur, ne trouvant pas de type "déclaré", utilise le type de la valeur affectée à la variable.

Le compilateur va vérifier qu'une méthode `length()` existe bien dans la classe `String` (qui est la classe que le compilateur attribue à la variable).

2. **Qu'affiche le code suivant ? Expliquer.**
```java
var s1 = "toto";
var s2 = s1;
var s3 = new String(s1);

System.out.println(s1 == s2);
System.out.println(s1 == s3);
```

Le code ci-dessus affichera `true` dans un premier temps, puis `false`.

Le "==" permet de vérifier que deux variables pointent vers la même référence.

La première ligne affiche `true` car les deux variables `s1` et `s2` pointent vers la même référence.
La seconde ligne affiche `false` car les deux variables `s1` et `s3` pointent vers des références différentes.



3. **Quelle est la méthode à utiliser si l'on veut tester si le contenu des chaînes de caractères est le même ?**

Dans le cas où l'on souhaite comparer le contenu de deux String (ce qui est souvent le cas lorsqu'une comparaison entre deux chaînes est effectuée), il est préférable d'utiliser la méthode `equals()` plutôt que `==`.

Si l'on reprend l'exemple précédent, le procédé suivant est le bon :
```java
var s1 = "toto";
var s2 = s1;
var s3 = new String(s1);

System.out.println(s3.equals(s1));
```

4. **Qu'affiche le code suivant ? Expliquer**
```java
var s6 = "toto";
var s7 = "toto";
    
System.out.println(s6 == s7);
```

Le code ci-dessus affichera `true`.

Ce comportement est du au fait que ces chaînes de caractères sont litérales (déclarées avec des double quotes). 
Ces chaînes sont stockées dans un pool de chaînes de caractères, et sont donc partagées entre toutes les variables qui les utilisent.

L'objectif de cette technique est de ne stocker qu'une seule chaîne "toto" en mémoire. Ce procédé s'appelle "Interning". 

Les chaînes ayant le même contenu, elles partagent le même emplacement mémoire, ce qui explique que `s6 == s7` vaut `true`.

5. **Expliquer pourquoi il est important que java.lang.String ne soit pas mutable.**

On entend par "mutable" une classe dont les objets peuvent être modifiées après leur instanciation.

La classe "String" est immuable, ce qui signifie que les objets de cette classe ne peuvent pas être modifiées après leur instanciation.
L'objectif est, par mesures de sécurité, de ne pas permettre d'altérer une chaîne de caractère une fois instanciée.

L'alternative mutable de la classe "String" est la classe "StringBuilder". 
Un objet de cette classe peut être modifié une fois instanciée essentiellement grâce à la méthode `append()`.

Un objet de la classe StringBuilder pourra, une fois les opérations de modification effectuées, être converti en objet de la classe String grâce à la méthode `toString()`.

6. **Qu'affiche le code suivant ? Expliquer**
```java
var s8 = "hello";
s8.toUpperCase();
System.out.println(s8);
```

Le code ci-dessus affichera `hello`. 
Comme mentionné précédemment, une chaîne de caractères ne peut pas être modifiée après avoir été instanciée. Elle ne peut donc pas être convertie en majuscules.

Si l'on souhaite afficher la chaîne de caractères en majuscules, une des solutions serait de ré-instancier une nouvelle chaîne de caractères en majuscules (ce qui n'est pas une utilisation optimale de la mémoire) :
```java
```java
var s8 = "hello";
s8 = s8.toUpperCase();
System.out.println(s8);
```

<br>

## Exercice 2 - En morse. Stop.

**Écrire une classe Morse qui permet, lors de son exécution, d'afficher les chaînes de caractères prises en argument séparées par des "Stop.".**
```java
java Morse ceci est drole 
ceci Stop. est Stop. drole Stop.
```

1. **Utiliser dans un premier temps l'opérateur + qui permet la concaténation de chaînes de caractères.**

On ajoute le code suivant au fichier `Morse.java` :

```java
public class Morse {
  public static void main(String[] args) {
    var morseString = new String();
    for (String arg: args) {
      morseString = morseString + arg + " Stop. ";
    }

    System.out.println(morseString);
  }
}
```

2. **A quoi sert l'objet java.lang.StringBuilder ?**

La classe `StringBuilder` permet de construire une chaîne de caractères mutable, à l'inverse de la classe `String` qui est non-mutable.

**Pourquoi sa méthode append(String) renvoie-t-elle un objet de type StringBuilder ?**

La méthode `append(String)` renvoie un objet de type `StringBuilder` car elle permet de concaténer une chaîne de caractères à une chaîne de caractères mutable.

3. **Réécrire la classe Morse en utilisant un StringBuilder.**

On modifie le code présent dans le fichier `Morse.java` :
```java
public class Morse {
  public static void main(String[] args) {
    var morseString = new StringBuilder();
    for (String arg: args) {
      morseString = morseString.append(arg).append(" Stop. ");
    }

    System.out.println(morseString);
  }
}
```

**Quel est l'avantage par rapport à la solution précédente ?**

La solution précédente, utilisant la classe StringBuilder au lieu de la classe String, permet de concaténer des chaînes de caractères de manière plus efficace et en utilisant moins d'espace mémoire.

4. **Recopier le code suivant dans une classe de Test :**
```java
public static void main(String[] args) {
  var first = args[0];
  var second = args[1];
  var last = args[2];
  System.out.println(first + ' ' + second + ' ' + last);
}
```

**Pourquoi peut-on utiliser ' ' à la place de " " ?**

**Compiler le code puis utiliser la commande javap pour afficher le bytecode Java (qui n'est pas un assembleur) généré.**
```bash
javap -c Test
```

**Que pouvez-vous en déduire ?**

On peut utiliser des apostrophes pour délimiter une chaîne de caractères, ce qui permet de ne pas avoir à échapper les guillemets.

Le byte code retourné par la commande `javap -c Test` est le suivant :
```java
Compiled from "Test.java"
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: aload_0
       1: iconst_0
       2: aaload
       3: astore_1
       4: aload_0
       5: iconst_1
       6: aaload
       7: astore_2
       8: aload_0
       9: iconst_2
      10: aaload
      11: astore_3
      12: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
      15: aload_1
      16: aload_2
      17: aload_3
      18: invokedynamic #13,  0             // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      23: invokevirtual #17                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      26: return
}
```

On remarque, à partir de la ligne 18, que le bytecode généré par le compilateur utilise la méthode `makeConcatWithConstants` pour concaténer les chaînes de caractères.

Afin de comparer si un réel changement sépare l'utilisation de single quote et double quotes, on compare le bytecode ci-dessus à un bytecode généré em remplaçant les quotes.
On constate que le bytecode est similaire, ce qui indique implicitement que les quotes n'ont pas d'impact sur le bytecode généré.

Le bytecode généré par le compilateur, une fois reconstitué en fichier ".java" standard, sera similaire à cela :
```java
public class Test {
  public Test() {
  }

  public static void main(String[] var0) {
    String var1 = var0[0];
    String var2 = var0[1];
    String var3 = var0[2];
    System.out.println(var1 + " " + var2 + " " + var3);
  }
}
```

Un constructeur canonique a été automatiquement créé.

On constate que les apostrophes ont été remplacées par des guillemets, ce qui n'affecte donc en aucun cas le code, qui a bien concaténé les chaînes.

5. **Compiler le code de la question 1, puis utiliser la commande javap pour afficher le bytecode Java généré.**

Le bytecode généré par la commande `javap -c Morse` est le suivant :
```java
Compiled from "Morse.java"
public class Morse {
  public Morse();
  Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
  Code:
       0: new           #7                  // class java/lang/StringBuilder
       3: dup
       4: invokespecial #9                  // Method java/lang/StringBuilder."<init>":()V
       7: astore_1
       8: aload_0
       9: astore_2
      10: aload_2
      11: arraylength
      12: istore_3
      13: iconst_0
      14: istore        4
      16: iload         4
      18: iload_3
      19: if_icmpge     46
      22: aload_2
      23: iload         4
      25: aaload
      26: astore        5
      28: aload_1
      29: aload         5
      31: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      34: ldc           #14                 // String  Stop.
      36: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      39: astore_1
      40: iinc          4, 1
      43: goto          16
      46: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
      49: aload_1
      50: invokevirtual #22                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      53: return
}
```

**Que pouvez-vous en déduire ?**

On en déduit donc que l'utilisation de StringBuilder permet de concaténer des chaînes de caractères de manière plus efficace, car la mémoire est allouée une seule fois, et non à chaque concaténation.

Lors d'une concaténation par "+", le bytecode généré par le compilateur utilise la méthode `makeConcatWithConstants` pour concaténer les chaînes de caractères.

**Dans quel cas doit-on utiliser StringBuilder.append() plutôt que le + ?**

Lorsque l'on doit concaténer plusieurs chaînes de caractères, il est préférable d'utiliser StringBuilder.append() plutôt que le +, car l'utilisation de + génère un nouveau StringBuilder à chaque concaténation, ce qui est très coûteux en mémoire.

**Et pourquoi est-ce que le chargé de TD va me faire les gros yeux si j'écris un + dans un appel à la méthode append?**

Le but en utilisant la méthode "append" est de ne pas remplir la mémoire d'objets temporaires. Le fait d'utiliser le "+" dans un appel à la méthode "append" va créer un nouvel objet StringBuilder, ce qui annule l'effort d'optimisation et revient à faire la concaténation sans la méthode "append".

<br>

## Exercice 3 - Reconnaissance de motifs

Le but de cet exercice est la manipulation d'expressions régulières en java. 
Les expressions régulières sont un moyen de décrire simplement une infinité de chaîne de caractères. 
Si vous avez besoin de vous rafraichir la mémoire, vous pouvez lire la page Wikipédia. 
En Java, nous utiliserons pour cela les classes du paquetage java.util.regex. 
Vous pouvez lire le guide avant de creuser la javadoc.

1. **A quoi servent la classe java.util.regex.Pattern et sa méthode compile ?**

La classe java.util.regex.Pattern permet de définir un motif à rechercher dans une chaîne de caractères. 
La méthode statique compile permet de compiler une expression régulière en un objet "Pattern".

**A quoi sert la classe java.util.regex.Matcher ?**

La classe java.util.regex.Matcher permet de rechercher un motif dans une chaîne de caractères. 
Elle permet de déterminer si le motif est présent dans la chaîne, et de récupérer les sous-chaînes correspondant au motif.

2. **Reprenez la petite calculatrice Calc de l'exercice 1. 
On veut que si l'utilisateur saisisse autre chose qu'un nombre, le programme lui demande de saisir une autre valeur. 
Pour cela on remplacera l'appel scanner.nextInt par scanner.nextLine qui renvoie la chaîne de caractères saisie par l'utilisateur. 
N'oubliez pas de remplacer scanner.hasNextInt() par scanner.hasNextLine().
Attention, n'oubliez pas que l'utilisateur doit pouvoir saisir un nombre négatif.**

Voici le code de la classe Calc :
```java
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Calc {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] values = new int[2];

    Pattern pattern = Pattern.compile("-?[0-9]+");
    Matcher matcher;

    for (int i = 0; i < 2; i++) {
      System.out.print("Please provide an integer value : ");
      String input = scanner.nextLine();
      matcher = pattern.matcher(input);
      if (matcher.find()) {
        values[i] = Integer.parseInt(input);
      } else {
        System.out.println("Invalid input, please try again.");
        i--;
      }
    }

    System.out.println(values[0] + " + " + values[1] + " = " + (values[0] + values[1]));
    System.out.println(values[0] + " - " + values[1] + " = " + (values[0] - values[1]));
    System.out.println(values[0] + " / " + values[1] + " = " + (values[0] / values[1]));
    System.out.println(values[0] + " % " + values[1] + " = " + (values[0] % values[1]));
  }
}
```

On importe les classes suivantes :

- java.util.Scanner : permet de lire des données depuis l'entrée standard
- java.util.regex.Pattern : permet de définir un motif à rechercher dans une chaîne de caractères
- java.util.regex.Matcher : permet de rechercher un motif dans une chaîne de caractères

3. **En partant du code ci-dessous, on veut écrire un programme qui va lire un fichier HTML dont le chemin est pris sur la ligne de commande et qui va afficher la liste des URLs de tous les liens contenus dans le fichier.**

On complète le code qui nous est donné :
```java
public class LinkExtractor {
  public static void main(String[] args) throws IOException {
    var path = Path.of(args[0]);
    var lines = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
    Pattern pattern = Pattern.compile("<a href=\"(.*?)\"");
    // reads all lines of the file opened in Latin-1
    for(var line : lines){
      Matcher matcher = pattern.matcher(line);
      while(matcher.find()) {
        System.out.println(line.substring(matcher.start(), matcher.end()));
        System.out.println("===>" + matcher.group(1));
      }
    }
  }
}
```

4. **Modifiez le programme pour ne conserver que les liens contenant le mot java ou Java.**

On remplace le pattern par celui-ci :
```java
Pattern pattern = Pattern.compile("<a href=\"(.*?java.*?)\"");
```