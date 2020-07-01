# Xemime

implemented in Scala

## Run

```bash
sbt core/run
```

## Test

```bash
sbt core/test
```

## Generate executable JAR

Using ``sbt-assembly`` plugin

```bash
sbt assembly
```

Location: ``dist/xemime.jar``

Now we can run the executable:

```bash
java -jar dist/xemime.jar
```
