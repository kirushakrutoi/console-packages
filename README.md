# ConsolePackages
Проект представляет собой систему для размещения посылок в кузовах машин и чтения файлов с уже погруженными машинами.

<h3>Система включает в себя следующие компоненты:</h3>

- Алгоритмы для размещения посылок в кузова машин с целью максимального использования пространства.
- Сервисы для чтения файлов формата JSON И TXT, содержащих данные о уже погруженных машинах и посылках соответсвенно.

<h3>Использование:</h3>

- Сначала выбираем успаковываем посылки или считаем посылки: 1 - сортируем, 2 счиатаем. 
- Если вырали 1
    - Введите путь к файлу с данными о посылках в формате TXT.
    - Введите количество доступных кузовов машин.
    - Выберите тип алгоритма размещения (u - равномерное, o - оптимальное).
    - Введите путь к папке для сохранения результатов.
    - Нажмите Enter.
    - Резульат:  Система выведет список заполненных кузовов машин.
      Данные о заполненных кузовах будут сохранены в указанной папке в формате JSON.
- Если выбрали 2
    - Введите путь к файлу с данными о кузовах в формате JSON.
    - Нажмите Enter.
    - Результат: Система выведет результат в формате - тип посылки - количество посылок,
      и так же выведет список кузовов машин.

<h3>Пример использования при сортировке</h3>
<h4>Пример входных данных</h4>

![alt-text](/img/example1.png)

<h4>Вывод на консоль<h4>

![alt-text](/img/output_example1.png)

<h4>Вывод в файл</h4>

![alt-text](/img/json_output_example1.png)

<h3>Пример использования при полсчете посылок</h3>
<h4>Пример входных данных</h4>

![alt-text](/img/example2.png)

<h4>Вывод на консоль<h4>

![alt-text](/img/output_example2.png)








