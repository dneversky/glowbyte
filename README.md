## Тестовое задание для GlowByte
Сервис реализован с использованием фреймворка Spring Boot на языке Java с применением REST архитектуры. Протокол передачи данных для работы с сервисом - HTTP.
В сервисе используется документация Swagger для удобного взаимодействия с API, расположенном по адресу http://localhost:8080/ после старта приложения.
Авторизовавшись под тестовым пользователем с логином `Q3AM3UQ867SPQQA43P2F` и паролем `zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG` в распределенном хранилище https://play.min.io:9443/browser/glowtask можно найти сохраненные заявки.
### Запуск с помощью Gradle
- скачайте и установите JRE 17 версии и выше
- скачайте и распакуйте репозиторий на компьютер
- откройте командную консоль в распакованном каталоге
- выполните команду `start gradlew.bat bootRun` (заявки будут сохраняться в build/classes/java/main)
- или `start gradlew.bat bootRun --args='--storageDirectory=%UserProfile%\Downloads'`, где `%UserProfile%\Downloads` - директория для сохранения заявок (если не переопределить, то заявки будут сохраняться в загрузки)
### Запуск с помощью Docker
- скачайте образ командой `docker pull dneversky/glowbyte:latest`
- запустите контейнер командой `docker run -dp 8080:8080 --rm --name glowbyte dneversky/glowbyte:latest`
- чтобы завершить выполнение контейнера введите команду `docker stop glowbyte`
- важно: отсутствует возможность просмотра сохраненной заявки в контейнере, однако можно просмотреть в распределенном хранилище

## API Endpoints
Конечные точки позволят работать с приложением.
#### POST `http://localhost:8080/v1/appeals/calculate-total-price`

Получить полную стоимость ремонта автомобиля. Расчет осуществляется подсчетом всех стоимостей элементов авто, переданных в виде массива в теле заявки (Appeal).

___Request Body___

````
{
  "carAreas": [
    {
      "name": "Кузов",
      "carComponents": [
        {
          "name": "Передняя левая дверь",
          "comment": "Стекло немного поцарапано",
          "price": 3990,
          "carComponentAction": {
            "name": "Ремонт"
          }
        }
      ]
    }
  ]
}
````

___Response___

3990

#### POST `http://localhost:8080/v1/appeals`

Сохранить заявку в локальное файловое хранилище в виде текстового (.txt) файла, а затем спушить в распределенную файловую систему http://min.io Путь к директории и конфигурационные параметры удаленного хранилища можно указать в application.yaml

<b>Чтобы увидеть загруженные на min.io файлы нужно перейти на https://play.min.io:9443/browser/
под логином и паролем указанных в конфигурационном файле приложения application.yaml</b>

___Request Body___

````
{
  "carAreas": [
    {
      "name": "Кузов",
      "carComponents": [
        {
          "name": "Передняя левая дверь",
          "comment": "Стекло немного поцарапано",
          "price": 3990,
          "carComponentAction": {
            "name": "Ремонт"
          }
        }
      ]
    }
  ]
}
````

___Response___

`200 OK`
