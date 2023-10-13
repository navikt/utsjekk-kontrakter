# dp-kontrakter
Dagpenger sine kontrakter i form av dto'er brukt på tvers av apper/tjenester

## Ta i bruk kontraktene
Kontraktene publiseres som pakker i Github Package Registry. Hvis applikasjonen din bruker disse pakkene, må du
autentisere deg for å laste dem ned. For å autentisere deg må du ha et Personal Access Token (PAT) med scope read:packages.
Dersom du ikke allerede har et PAT med riktig scope, følg Github sin 
[guide](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-personal-access-token-classic)
for å opprette det. Husk å enable SSO for tokenet. Under følger lokalt oppsett for hhv. Gradle og Maven: 

### Gradle
Ved bruk av Gradle, kan man legge til følgene i build.gradle.kts:
```
val githubUser: String by project
val githubPassword: String by project
repositories {
    maven {
        credentials {
            username = githubUser
            password = githubPassword
        }
        setUrl("https://maven.pkg.github.com/navikt/REPO")
    }
}
```
`githubUser` og `githubPassword` er da properties som settes i `~/.gradle/gradle.properties`:

```
githubUser=x-access-token
githubPassword=<PAT>
```
Alternativt kan variablene kan også konfigureres som miljøvariabler, eller brukes i kommandolinjen:

* `ORG_GRADLE_PROJECT_githubUser`
* `ORG_GRADLE_PROJECT_githubPassword`

`./gradlew -PgithubUser=x-access-token -PgithubPassword=[token]`

### Maven
Legg følgende kodesnutt inn i din `~/.m2/settings.xml`:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    ...
    <servers>
        ...
        <server>
            <id>github</id>
            <username>[github brukernavn]</username>
            <password>[PAT]</password>
        </server>
        ...
    </servers>
    ...
</settings>
```

Videre må følgende kodesnutt legges inn i applikasjonens pom.xml:
```xml
<repositories>
    ...
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/navikt/dp-kontrakter</url>
    </repository>
    ...
</repositories>
```

