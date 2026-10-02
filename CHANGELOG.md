# Changelog

## [1.0.3](https://github.com/ahincho/nova-java-02-date-utils/compare/v1.0.2...v1.0.3) (2026-10-02)


### Bug Fixes

* **deps:** pin the patched versions the OWASP gate now reports ([1ec700f](https://github.com/ahincho/nova-java-02-date-utils/commit/1ec700f8c3d07397926399315e41a0ea9ba4d9db))

## [1.0.2](https://github.com/ahincho/nova-java-02-date-utils/compare/v1.0.1...v1.0.2) (2026-09-27)


### Documentation

* add a README and adopt EPL-2.0 ([3020118](https://github.com/ahincho/nova-java-02-date-utils/commit/3020118a9b109b8298621ae8cffa482dfbb60732))

## [1.0.1](https://github.com/ahincho/nova-java-date-utils/compare/v1.0.0...v1.0.1) (2026-07-13)


### Bug Fixes

* **ci:** add component + skip-snapshot + manifest-file (mask-utils pattern) ([ad6e9f8](https://github.com/ahincho/nova-java-date-utils/commit/ad6e9f884d90b0c5d902ffde439da1d597bee4c6))
* **ci:** add last-release-sha, include-component-in-tag: false, release-type: java to top-level config; pass manifest-file in wrapper ([04a62a6](https://github.com/ahincho/nova-java-date-utils/commit/04a62a697e927c61b43aa5a651a2a32256af5048))
* **ci:** use PAT fallback for release-please to enable tag-triggered workflows ([82344e5](https://github.com/ahincho/nova-java-date-utils/commit/82344e5636110c328d16533ea5fda047bc95a634))

## 1.0.0 (2026-07-10)


### Features

* **ci:** migrate to release-please + tag-based publish flow (NOVA-SEMVER-13) ([51fe88e](https://github.com/ahincho/nova-java-date-utils/commit/51fe88ee34741c4af8ca03786599331b8c55956b))
* **gradle:** add GPG signing plugin for Maven Central publishing (NOVA-SEMVER-10) ([0bd188d](https://github.com/ahincho/nova-java-date-utils/commit/0bd188d560b2b1291d10093227d64f5d94d5545f))
* **gradle:** enable Local Build Cache and Configuration Cache (NOVA-SEMVER-23-24) ([3708154](https://github.com/ahincho/nova-java-date-utils/commit/37081543c6c814dcd5fa625e0ad4ba0a4543c8be))
* initial commit - Date utilities (parsing, formatting, conversion, calculation, relative formatting, multi-locale) ([f223bfb](https://github.com/ahincho/nova-java-date-utils/commit/f223bfb0a99659215fb54114ad55563ff22bc2eb))


### Bug Fixes

* **ci:** inline publish-on-tag and remove dirty closure for Gradle 9.6.1 ([bf272fd](https://github.com/ahincho/nova-java-date-utils/commit/bf272fd12748830631991ce514a6550f07d7a3c2))
* **ci:** update reusable workflow refs to ahincho/nova-devops ([62496f9](https://github.com/ahincho/nova-java-date-utils/commit/62496f961b7c89e875da599fdcee0d9bd2a54357))
* **javadoc:** move [@throws](https://github.com/throws) tag from record-level to constructor Javadoc ([a6ea9d0](https://github.com/ahincho/nova-java-date-utils/commit/a6ea9d01dd1f0ff17d82313416a8ea6b5b680ac4))
* **quality:** add missing config/checkstyle/checkstyle.xml ([ac92769](https://github.com/ahincho/nova-java-date-utils/commit/ac92769af6937da1a4c11d62b6e03b8d825cb0aa))
