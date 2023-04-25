# Отчет о тестировании cdr генератора

## Доработка cdr generator

- Был выявлен и исправлен баг, из-за которого абоненту генерировался один и тот же тип вызова для всех звонков.

## Тестирование cdr генератора
cdr генератор протестирован на корректность сгенерированных данных по следующим пунктам:
- время окончания вызова генерируется больше времени начала вызова.
- генерируются корректные телефоны абонентов. Под корректностью подразумевается что все номера имеют длину в `10` цифр + код страны `7`
- генерация включает в себя случаи, когда абонент заканчивает звонок на следующий день.
- генерируются правильные типы звонков. При этом у одного абонента есть звонки как исходящие так и входящие.
- формат записи генератора соответствует заявленным требованиям.

## Функциональные тесты
- Был создан функциональный тест на проверку метода для генерации телефона.