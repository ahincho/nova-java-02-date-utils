# Nova Date Utils

Date handling that services keep rewriting: formatting for a locale,
parsing input that arrives in six shapes, "3 hours ago", and the
arithmetic between two instants. Plain Java on top of `java.time`, no
framework, no Joda.

## What's inside

| Package | Type | Does |
|---|---|---|
| `format` | `DateFormatter` | Locale- and pattern-aware formatting |
| `format` | `RelativeFormatter` | "hace 3 horas" / "3 hours ago" |
| `parse` | `DateParser` | Parsing with a pattern or by trial |
| `calc` | `DateCalculator`, `DateRange` | Differences and arithmetic |
| `convert` | `DateConverter` | Between `LocalDate`, `LocalDateTime`, `ZonedDateTime`, `Instant` |
| `pattern` | `DatePatterns`, `PatternValidator` | Named patterns instead of loose strings |
| `config` | `DateConfig` | Default locale, zone and pattern |

## Install

Published to GitHub Packages, so the repository needs to be declared and
authenticated with a token that has `read:packages`.

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/ahincho/nova-java-date-utils")
        credentials {
            username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
            password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("pe.edu.nova.java.libs:nova-date-utils:0.1.0-SNAPSHOT")
}
```

## Use

Patterns are constants, so a typo is a compile error rather than a
runtime surprise:

```java
import pe.edu.nova.java.libs.date.utils.format.DateFormatter;
import pe.edu.nova.java.libs.date.utils.pattern.DatePatterns;

DateFormatter.format(date, DatePatterns.DD_MM_YYYY);              // 14/03/2026
DateFormatter.format(date, DatePatterns.DD_MMMM_YYYY_ES, ES);     // 14 de marzo de 2026
DateFormatter.format(date, DatePatterns.MMMM_DD_YYYY_EN, EN);     // March 14, 2026
```

Relative time picks the unit for you:

```java
RelativeFormatter.format(LocalDateTime.now().minusHours(3));      // 3 hours ago
```

Arithmetic reads as what it is:

```java
DateCalculator.daysBetween(start, end);
DateCalculator.monthsBetween(start, end);
DateCalculator.addWeeks(date, 2);
```

## Errors

`DateParseException`, `DateFormatException` and `DateConversionException`,
all under `DateException`.

## Requirements

Java 25.

## License

Eclipse Public License 2.0 — see [LICENSE](LICENSE).

Copyright © 2026 Angel Hincho.
