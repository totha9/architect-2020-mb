# Perzisztens réteg az architektúrában

## Ajánlott irodalom

* Core J2EE Patterns
* Patterns of Enterprise Application Architecture
* Real World Java EE Patterns

## Megfontolandók, problémák, architektúrális döntések

### Adatbázis

* Adatbázis függetlenség
* Üzleti logika az adatbázisban
* Adatmozgatás adatbázis és alkalmazás között
* Constraint, trigger, tárolt eljárások
* Tárolt eljárások módosítása és a connection pool kapcsolata
* Speciálisan tárolandó: fa, gráf, idősor, fájl
* Auditálás
* Adatbázis séma verziózás, séma migráció (Flyway, Liquibase)
* Időzónák kezelése

### Architektúra

* Java EE tervezési minták    
    * DAO
        * Cserélhetőség
        * Generálhatóság
        * ÜL és perzisztencia elkülönítése
    * Value Object, 2. kiadásban lett átnevezve Transfer Objectté
        * Maga a BusinessObject képes előállítani
        * Paraméterként kaphat is egyet a sok setter metódus helyett (Updatable DTO) - baj lehet a különbségek kikeresésével (1-n kapcsolatok esetén)
        * Multiple DTO - egy BusinessObject több DTO-t állít elő
        * Entity Inherits DTO - mivel azonos attribútumok, a DTO-ból leszármazó entitás
        * DTO factory - entity alapján különböző DTO-k legyártása - reflection
    * Composite entity
        * Entitás több finom szemcsézettségű objektumból álljon össze - `@Embeddable`
        * Mennyire ágyazhatók egymásba? (`@Embeddable`-ben lehet `@Embedded`)
        * Lazy load
    * VO/Transfer Object assembler
        * Külön osztály, mely létrehozza a TO-t
    * Value list handler
        * Egyszer lefuttatja a lekérdezést, szerver oldalon cache-eli, majd abban lehet lapozni
    * Domain store
        * Perzisztencia leválasztása az object modeltől
        * Saját vagy kész implementáció - ORM
    * Persistent Domain Object (Business Object)
        * Entitások, de ne anemic model, hanem legyenek valódi metódusai, ne a perzisztenciával kezdjünk
        * EntityManager használat az entitásból...
    * Paginator and Fast Lane Reader
        * Mikor nem tudott stream-elni a query, mindent betöltött a memóriába
        * Pl lapozással v. JDBC (ResultSet) - fetch size
    * Retired: TO assembler - JPA mindent tud, szerinte mozgathatunk detached entitásokat
    * Retired: Domain store - EntityManager egy standard implementációja
    * Retired: Value list handler - helyette Paginator
    * DAO: nem tartja indokoltnak, hiszen ott az EntityManager, ami a service-ből használható
    * DTO: nem feltétlen tartja indokoltnak - kódduplikáció - detached entitások jók
* Egységes konvenciók, interfész, automatikus implementáció generálás
* Anemic model
* Döntés kérdése: entitások az üzleti logika része?
* Entity <-> DTO átfordítás
* Tranzakciókezelés
* Elosztott tranzakciókezelés

### ORM

* Már meglévő adatbázis
* Összetett azonosító
* Lyuk az id-ban, id kiosztás performancia
* Elosztott rendszerben UUID
* Üzleti objektumok és táblák nem egy-egy megfeleltetése
* Módosítunk, majd lekérdezést végzünk. Ekkor a persistence provider próbál flush-ölni - performancia probléma
* JOIN-os lapozás
* Öröklődés
* Mi a különbség a kaszkádolt törlés és az orphan removal között (nem csak törléskor, hanem kapcsolat megszűntetésekor is törli a kapcsolt entitást)
* Dinamikus query (mire való a Metamodel API?)
* Query by example
* Támogatott a bean validation
* Batch esetén nem változik a managed entitás - saját tranzakciója legyen, vagy a tranzakció elején fusson, HA a presistence provider refresh-el - performancia probléma, ha nem - manuális refresh,
kaszkádolt kapcsolatokat nem törli, lock/verziózás
* Lockolás
    * Hol lehet megadni a lock módját? (`EntityManager.lock()`, `EntityManager.find()`, `EntityManager.refresh()`, `Query.setLockMode()`)
    * Mikor kell hívni a `lock()` metódust az `OPTIMISTIC` és az `OPTIMISTIC_FORCE_INCREMENT` paraméterekkel? (Másik entitás esetén, és annak verziószámát van nem emeli, vagy azonnal emeli.)
* First level cache (em szinten) és shared cache (emf szinten) fogalma, deklaratív cache (pu szinten)
* Auditálás: Envers

### Unit és integrációs tesztelés

* Hogyan biztosítsuk, hogy ne legyen túl sok `select`
* Nem unit tesztelhető
* Java EE-ben az integrációs tesztelés problémás (pl. tranzakciókezelés, autentikáció)
* Rendezés
* Adatbázis, mint állapot
* Arquillian, Selenium
